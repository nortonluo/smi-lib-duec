/* 
 * Copyright 2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dell.cm.repository;

import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.cm.updateinformationmodel.DCMJobStatus;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.downloader.DSMCIFSDownloader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

/**
 *
 * @author Rahul_Ranjan2
 */
public class CMCUpdates {

    private static final Logger logger = Logger
            .getLogger(CMCUpdates.class.getName());

    /**
     * @param args the command line arguments for racadm
     */
    String path;
    private String osName=new String();
    private static final String RACADM_COMMAND_FW_UPDATE = new String("fwupdate -pud");
    private static final String RACADM_COMMAND_FW_UPDATE_TFTP=new String("fwupdate -g -u -a");
    private static final String RACADM_COMMAND_FW_UPDATE_FTP=new String("fwupdate -f");
    private static final String RACADM_COMMAND_MODULE = new String("-m");
    private static final String RACADM_COMMAND_SOURCE_PATH=new String("-d");
    private static final String ACTIVE_CMC_IDENTIFIER = new String("cmc-active");
    private static final String STANDBY_CMC_IDENTIFIER = new String("cmc-standby");
    private static final String RACADM_COMMAND_FW_UPDATE_STATUS = new String("fwupdate -s");
    private static final String CMC_STANDBY_NOT_PRESENT = new String("ERROR: 'cmc-standby' not listed in inventory");
    private static final String CMC_NOTAVAILABLE = new String("ERROR: Unable to connect to RAC at specified IP address");
    private static final String RACADM_FW_UPDATE_INITIATED = new String("Firmware update has been initiated");
    private static final String RACADM_FW_UPDATE_READY = new String("Ready for firmware update");
    private static final String RACADM_FW_UPDATE_IN_PROGRESS = new String("Firmware update in progress");
    private static final String RACADM_FW_UPDATE_RESETTING = new String("Resetting the CMC");
    private static final String RACADM_DETECT=new String("ERROR: Insufficient privilege level");
    private static final String RACADM_FW_UPDATE_FAIL=new String("Firmware update operation failed");
    
    private static final char FORWARD_SLASH = '/';
    private static final char BACKWARD_SLASH = '\\';
    private NtlmPasswordAuthentication mAuthenticationParameters;
    private DSMAuthenticationParameters inAuthenticationParameters;

    public CMCUpdates(){
        osName=System.getProperty("os.name");
        osName=osName.toLowerCase();
        inAuthenticationParameters = new DSMAuthenticationParameters();
    }

    /**
     * Copying racadm from share folder into local temp folder
     *
     * @param racadmpath specifies path for the share location of racadm
     * @param shareUsername
     * @param sharePassword
     * @return SUCCESS or FAILURE
     * @throws Exception
     */
    public int copyIntoLocal(String racadmpath, String shareUsername, String sharePassword) throws Exception {
        logger.log(Level.INFO,"Copying Racadm to local system in temporary directory");
        String urlString = "smb://" + racadmpath;
        DSMCIFSDownloader instance=new DSMCIFSDownloader();
        inAuthenticationParameters.setPassword(sharePassword);
        inAuthenticationParameters.setUserName(shareUsername);
        instance.setAuthenticationParameters(inAuthenticationParameters);
        File dir = Files.createTempDirectory("Racadm").toFile();
        if (dir.exists()) {
            deleteDir(dir);
        }
        dir.mkdirs();
        this.path=dir.getPath();
        urlString = urlString.replace(BACKWARD_SLASH, FORWARD_SLASH);
        urlString = urlString.replaceFirst("smb://", "");
            if (urlString.startsWith("//")) {
                urlString = urlString.replaceFirst("//", "");
            }
        urlString = "smb://" + urlString;
        mAuthenticationParameters =new NtlmPasswordAuthentication(null,inAuthenticationParameters.getUserName(),inAuthenticationParameters.getPassword());
        SmbFile smbFile=new SmbFile(urlString, mAuthenticationParameters);
        SmbFile[] files = smbFile.listFiles();
        int status = DCMErrorCodes.SUCCESS;
        for(int i =0 ; i< files.length; ++i){
            SmbFile file = files[i];
            int statusVal;
            String tempdir;
            if(file.isDirectory()){
                SmbFile[] subDirFiles = file.listFiles();
                
                for(int j=0;j<subDirFiles.length;++j){
                    SmbFile subDirFile = subDirFiles[j];
                    tempdir= subDirFile.getPath().replace(urlString, "");
                    tempdir= tempdir.replace(FORWARD_SLASH, BACKWARD_SLASH);
                    statusVal = instance.downloadFile(subDirFile.getPath(), this.path+File.separator+tempdir);
                }
            }
            tempdir= file.getPath().replace(urlString, "");
            status = instance.downloadFile(file.getPath(),this.path+File.separator+tempdir);
        }
        if(this.path == null){
            return DCMErrorCodes.FAILURE;
        }
        this.path+="\\racadm";
        return status;
    }

    /**
     * deleting the directories
     *
     * @param file specifies which file to be deleted
     */
    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    /**
     * Checking whether racadm is present or not
     *
     * @param cmd specifies the command to detect
     * @return
     * @throws Exception
     */
    private boolean detectRacadm() {

        Runtime rt = Runtime.getRuntime();
        Process proc;
        int exitVal=0;
        try{
            proc=rt.exec(this.path);
            proc.waitFor();
            exitVal=proc.exitValue();
            BufferedReader stdError =new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String stdOut=readOutput(stdError);
            if(exitVal==1 && !stdOut.contains(RACADM_DETECT)){
                logger.log(Level.INFO,"Cannot Detect racadm in the path specified");
                logger.log(Level.INFO, "displaying exitcode status:{0}",exitVal);
                return false;
            }
        }catch(IOException | InterruptedException ex){
            logger.log(Level.SEVERE,"Exception occured while detecting the Racadm",ex);
            return false;
        }
        logger.log(Level.INFO,"racadm detected successfully");
        //logger.log(Level.INFO, "displaying exitcode status:{0}",exitVal);
        return true;
    }

    private boolean isNetworkLocation(String path) {
        path = path.replace(BACKWARD_SLASH, FORWARD_SLASH);

        return path.startsWith("//");
    }

    /**
     * Updating firmware on CMC
     *
     * @param racadmPath specifies the racadm path
     * @param firmwareFileIP firmware file IP or Domain name
     * @param firmwareFilePath Relative path for the file excluding IP
     * @param ftpUsername username for ftp if any
     * @param cmcIpAddr specifies CMC ip address
     * @param ftpPassword password for ftp if any
     * @param userName specifies the CMC username
     * @param password specifies the CMC password
     * @param shareUsername
     * @param sharePassword
     * @return SUCCESS or FAILURE
     */
    public int updateCMC(String racadmPath, String firmwareFileIP, String firmwareFilePath,String ftpUsername,String ftpPassword, String cmcIpAddr, String userName, String password,
            String shareUsername, String sharePassword) {
        try {

            if(osName.contains("windows")){
                boolean isNetworkLoc = isNetworkLocation(racadmPath);
                if (isNetworkLoc) {
                    int copied = copyIntoLocal(racadmPath, shareUsername, sharePassword);
                    if(copied==DCMErrorCodes.SUCCESS)
                        logger.log(Level.INFO,"Racadm copy action successful");
                    else
                        logger.log(Level.INFO,"Racadm copy action not successful");
                } else {
                    this.path = racadmPath+"\\racadm";
                }
            }
            else{
                this.path=racadmPath;
            }
            // detecting racadm
            boolean exitCode = detectRacadm();

            if (exitCode) {
                // execute firmware update Cmd
                int firmwareUpdateStatus = executeRacadmCmd(cmcIpAddr, userName, password, firmwareFileIP,firmwareFilePath,ftpUsername,ftpPassword);

                logger.log(Level.INFO, "displaying firmware update status: {0}" ,firmwareUpdateStatus);

                if (firmwareUpdateStatus == DCMErrorCodes.SUCCESS) {
                    return DCMErrorCodes.SUCCESS;
                } else {
                    return DCMErrorCodes.FAILURE;
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception while updating the firmware", e);
        }
        logger.log(Level.INFO,"End of updateCMC method");
        return DCMErrorCodes.SUCCESS;
    }
    /**
     * Method to create the racadm command
     * @param firmwareFileIP
     * @param firmwareFilePath
     * @param ftpUsername
     * @param ftpPassword
     * @param cmcIdentifier
     * @return 
     */
    private String createCmd(String firmwareFileIP,String firmwareFilePath,String ftpUsername, String ftpPassword,String cmcIdentifier){
        
        String cmd;

        if(ftpUsername==null || "".equals(ftpUsername) && ftpPassword==null || "".equals(ftpPassword)){
            cmd=RACADM_COMMAND_FW_UPDATE_TFTP+" "+firmwareFileIP;
        }
        else
            cmd=RACADM_COMMAND_FW_UPDATE_FTP+" "+firmwareFileIP+" "+ftpUsername+" "+ftpPassword;
        cmd=cmd+" "+RACADM_COMMAND_SOURCE_PATH +" "+firmwareFilePath+" "+RACADM_COMMAND_MODULE+" "+cmcIdentifier;
        if(cmcIdentifier.equals(STANDBY_CMC_IDENTIFIER))
            cmd=cmd+" "+RACADM_COMMAND_MODULE+" "+ACTIVE_CMC_IDENTIFIER;
        return cmd;
    }


    /**
     * Executing racadm command to update both ACTIVE and STAND BY
     *
     * @param cmcIpAddr specifies CMC ip address
     * @param Username specifies CMC Username
     * @param password specifies CMC Password
     * @param CMCFile specifies the firmware file
     * @return SUCCESS or FAILURE
     * @throws Exception
     */
   
    private int executeRacadmCmd(String cmcIpAddr, String Username, String password, String firmwareFileIP,String firmwareFilePath,String ftpUsername, String ftpPassword) throws Exception {

        String commonCLPrefix = " -r " + cmcIpAddr + " ";
        commonCLPrefix += "-u " + Username + " ";
        commonCLPrefix += "-p " + password + " ";

        String cmcUpdateCommand = commonCLPrefix + " " + createCmd(firmwareFileIP,firmwareFilePath,ftpUsername,ftpPassword,STANDBY_CMC_IDENTIFIER);
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(this.path + cmcUpdateCommand);

        proc.waitFor();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // read any errors from the attempted 
        logger.log(Level.INFO, "Reading output of the command");
        
        String outPut = readOutput(stdInput);

        if (outPut.equals(CMC_STANDBY_NOT_PRESENT)) {
            logger.log(Level.INFO,"Executing racadm command in cmc-active");
           int status= executeActiveCMCCmd(cmcIpAddr, Username, password,firmwareFileIP, firmwareFilePath, ftpUsername,  ftpPassword);
           if(status == DCMErrorCodes.FAILURE)
               return DCMErrorCodes.FAILURE;
        }
        else if(!outPut.equals(RACADM_FW_UPDATE_INITIATED))
            return DCMErrorCodes.FAILURE;
        else{
            outPut=readOutput(stdError);
            if(outPut.equals(CMC_NOTAVAILABLE))
                return DCMErrorCodes.FAILURE;
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Executing racadm command to update ACTIVE CMC
     *
     * @param cmcIpAddr specifies CMC ip address
     * @param Username specifies CMC Username
     * @param password specifies CMC Password
     * @param CMCFile specifies firmware files
     * @param stdInput specifies the input of the command
     * @param stdError specifies if any error for the given command
     * @throws Exception
     */
    private int executeActiveCMCCmd(String cmcIpAddr, String Username, String password, String firmwareFileIP,String firmwareFilePath,String ftpUsername, String ftpPassword) throws Exception {
        String commonCLPrefix = " -r " + cmcIpAddr + " ";
        commonCLPrefix += "-u " + Username + " ";
        commonCLPrefix += "-p " + password + " ";

        String cmcUpdateCommand = commonCLPrefix + " " +createCmd(firmwareFileIP,firmwareFilePath,ftpUsername,ftpPassword,ACTIVE_CMC_IDENTIFIER);

        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(this.path + cmcUpdateCommand);

        proc.waitFor();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        
        String outPut=readOutput(stdInput);
        
        if(!outPut.equals(RACADM_FW_UPDATE_INITIATED))
            return DCMErrorCodes.FAILURE;
        else{
            outPut=readOutput(stdError);
            if(outPut!=null && !"".equals(outPut))
                if(outPut.equals(CMC_NOTAVAILABLE))
                return DCMErrorCodes.FAILURE;
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for providing the status of the Job
     *
     * @param cmcIpAddr specifies the CMC ip address
     * @param Username specifies the CMC Username
     * @param Password specifies the CMC Password
     * @return Status of the Job
     * @throws Exception Exception
     */
    public DCMJobStatus getStatus(String cmcIpAddr, String Username, String Password) throws Exception {
        //this.path = "C:\\Users\\CREATOR\\AppData\\Local\\Temp\\4\\Racadm";// this is for testing purpose it has to be removed.

        logger.log(Level.INFO, "Beggining of getStatus method");

        String commonCLPrefix = " -r " + cmcIpAddr + " ";
        commonCLPrefix += "-u " + Username + " ";
        commonCLPrefix += "-p " + Password + " ";

        String CMCUpdateStatusCommand = commonCLPrefix + " " + RACADM_COMMAND_FW_UPDATE_STATUS+" "+RACADM_COMMAND_MODULE+" "+ACTIVE_CMC_IDENTIFIER;
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(this.path + CMCUpdateStatusCommand);

        proc.waitFor();
        logger.log(Level.INFO,"Command Executed to get the status");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        // read the output from the command
        logger.log(Level.INFO, "Reading standard output of the command");

        String stdOut = readOutput(stdInput);
        logger.log(Level.INFO,"Standard output: {0}", new Object[]{stdOut});
        // read any errors from the attempted command
        logger.log(Level.INFO, "Reading standard error of the command");
        String err = readOutput(stdError);
        logger.log(Level.INFO,"Standard error: {0}",new Object[]{stdError});

        if (stdOut != null && !"".equals(stdOut)) {
            if (stdOut.equals(RACADM_FW_UPDATE_READY)) {
                return DCMJobStatus.COMPLETED;
            }
            else if (stdOut.equals(RACADM_FW_UPDATE_IN_PROGRESS)) {
                return DCMJobStatus.INPROGRESS;
            }
            else if (stdOut.equals(RACADM_FW_UPDATE_RESETTING)) {
                return DCMJobStatus.INPROGRESS;
            }
            else if(!stdOut.equals(RACADM_FW_UPDATE_FAIL))
                return DCMJobStatus.COMPLETED;
        }

        if (err != null && !"".equals(err)) {
            if (err.equals(CMC_NOTAVAILABLE)) {
                return DCMJobStatus.CMCNotAvailable;
            }
        }
        return DCMJobStatus.FAILED;
    }
    /**
     * Method to read the output
     * @param std
     * @return string
     */
    private String readOutput(BufferedReader std){
        String s=new String();
        String stdOut=new String();
        try{
            while((s=std.readLine())!=null){
                stdOut=stdOut+s;
            }
            if(stdOut!=null && !"".equals(stdOut)){
                if(stdOut.contains(CMC_STANDBY_NOT_PRESENT))
                    return CMC_STANDBY_NOT_PRESENT;
                else if(stdOut.contains(CMC_NOTAVAILABLE))
                    return CMC_NOTAVAILABLE;
                else if(stdOut.contains(RACADM_FW_UPDATE_INITIATED))
                    return RACADM_FW_UPDATE_INITIATED;
                else if(stdOut.contains(RACADM_FW_UPDATE_READY))
                    return RACADM_FW_UPDATE_READY;
                else if(stdOut.contains(RACADM_FW_UPDATE_IN_PROGRESS))
                    return RACADM_FW_UPDATE_IN_PROGRESS;
                else if(stdOut.contains(RACADM_FW_UPDATE_RESETTING))
                    return RACADM_FW_UPDATE_RESETTING;
                else if(stdOut.contains(RACADM_DETECT))
                    return RACADM_DETECT;
                else 
                    return stdOut;
            }
        }catch(Exception ex){
            stdOut=null;
            logger.log(Level.SEVERE,"Error in reading the output/error",ex.getMessage());
        }
        return stdOut;
    }
}
