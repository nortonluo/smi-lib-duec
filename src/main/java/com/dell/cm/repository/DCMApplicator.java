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

import com.dell.sm.wsman.updates.CommandResponse;
import com.dell.sm.wsman.updates.InstallFromRepositoryCmd;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.wsman.inventory.CollectInventory;
import com.dell.sm.wsman.updates.CreateRebootJobCmd;
import com.dell.sm.wsman.updates.DeleteJobCmd;
import com.dell.sm.wsman.updates.GetRepoBasedUpdateListCmd;
import com.dell.sm.wsman.updates.InstallFromURICmd;
import com.dell.sm.wsman.updates.JobStatusCmd;
import com.dell.sm.wsman.updates.LifeCycleJob;
import com.dell.sm.wsman.updates.SetupJobQueueCmd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DCMApplicator {

    private String jobId;
    private String iDracIpAddr;
    private String port;
    private DSMAuthenticationParameters authentication;

    private static final Logger logger = Logger
            .getLogger(DCMApplicator.class.getName());

    public DCMApplicator(String iDracIpAddr, String port, DSMAuthenticationParameters authentication) {

        logger.log(Level.INFO, "Inside DCMApplicator constructor ");

        this.iDracIpAddr = iDracIpAddr;
        this.port = port;
        this.authentication = authentication;

        logger.log(Level.INFO, "End of DCMApplicator constructor ");
    }

    /**
     * Method for updating the firmwares from repository
     *
     * @param shareIpAddr specifies the share ip address
     * @param shareName specifies the share name
     * @param mountPoint specifies the share mount point
     * @param catalogFileName specifies the catalog file name
     * @param shareType specifies the share type
     * @param shareUserName specifies the share user name
     * @param sharePasswd specifies the share password
     * @param applyUpdate specifies the update to apply
     * @param rebootNeeded specifies whether reboot is required or not
     * @return HashMap contains the success or failure and list of the jobs
     * created after repository update
     */
    public HashMap<Integer, String> updateFromRepo(String shareIpAddr, String shareName, String mountPoint,
            String catalogFileName, int shareType, String shareUserName,
            String sharePasswd, int applyUpdate, boolean rebootNeeded) {

        logger.log(Level.INFO, "Inside updateFromRepo method ");

        InstallFromRepositoryCmd repoCmd;
        CommandResponse response;
        String status = new String();
        HashMap<Integer, String> updateMap = new HashMap<>();

        try {
            // Passing the repository information.
            repoCmd = new InstallFromRepositoryCmd(iDracIpAddr, port, authentication.getUserName(), authentication.getPassword(), shareIpAddr, shareName, mountPoint, catalogFileName, shareType, shareUserName, sharePasswd, applyUpdate, rebootNeeded);
            // Calling execute() to apply updates through iDRAC.
            response = (CommandResponse) repoCmd.execute();

            if (response.getLCErrorCode() != null && response.getLCErrorStr() != null) {
                jobId = null;
                updateMap.put(DCMErrorCodes.FAILURE, jobId);
                return updateMap;
            }

            jobId = response.getJobID(); //jobid for the Repository update job
            status = getStatus(jobId);
            if (status.matches("Failed")) {
                updateMap.put(DCMErrorCodes.FAILURE, jobId);
            } else {
                updateMap.put(DCMErrorCodes.SUCCESS, jobId);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while executing InstallFromRepo Updates ", e.getMessage());
            jobId = null;
            updateMap.put(DCMErrorCodes.FAILURE, jobId);
        }
        return updateMap;
    }

    /**
     * Method to provide the list of the latest jobs cerated by Repository
     * update
     *
     * @return jobList List of the job ID's
     */
    public List<String> getJobsList() {
        List<String> jobList = new ArrayList<String>();
        GetRepoBasedUpdateListCmd updates = new GetRepoBasedUpdateListCmd(iDracIpAddr, port, authentication.getUserName(), authentication.getPassword(), false);
        try {
            Map<String, String> jobMap = updates.execute();
            for (String key : jobMap.keySet()) {
                String jd = jobMap.get(key);
                if (jd.equalsIgnoreCase("")) {
                    return null;
                } else {
                    jobList.add(jd);
                }
            }
        } catch (Exception ex) {
            logger.log(Level.INFO, "Jobs List Error: ", ex.getMessage());
            jobList = null;
        }
        return jobList;
    }

    /**
     * Method for providing the status of Jobs created.
     *
     * @param jobId Specifies the jodID for the status.
     * @return Job status as string
     */
    public String getStatus(String jobId) {

        logger.log(Level.INFO, "Inside getStatus method ");

        JobStatusCmd jobStatus;
        String status = new String();
        if (jobId != null) {
            try {
                jobStatus = new JobStatusCmd(iDracIpAddr, port, authentication.getUserName(), authentication.getPassword(), jobId);
                List<LifeCycleJob> lcjList = jobStatus.execute();
                for (LifeCycleJob lcj : lcjList) {
                    logger.log(Level.INFO, "Status of {0}: {1}", new Object[]{jobId, lcj.getJobStatus()});
                    status = lcj.getJobStatus();
                }

                return status;
            } catch (Exception ex) {
                logger.log(Level.INFO, "Error in getting job status ", ex.getMessage());
                status = "Error in getting the job";
                return status;
            }
        } else {
            logger.log(Level.INFO, "No jobs");
            status = "No job ID provided";
            return status;
        }
    }

    /**
     * Method for Deleting Jobs in Job Queue.
     *
     * @param jobId
     * @return Success or failure
     */
    public int deleteJob(String jobId) {

        logger.log(Level.INFO, "Inside deleteJob method ");
        if (jobId != null) {
            DeleteJobCmd deleteJob;
            try {
                String status = this.getStatus(jobId); //to get the status of a particular job
                if (status.matches("Completed") || status.matches("Failed")) {
                    deleteJob = new DeleteJobCmd(iDracIpAddr, port, authentication.getUserName(), authentication.getPassword(), jobId);
                    int deleteStatus = deleteJob.execute(); //delete the job if completed
                    if (deleteStatus != 0) {
                        logger.log(Level.INFO, "Error while Deleting the job");
                        return DCMErrorCodes.FAILURE;
                    } else {
                        logger.log(Level.INFO, "Job Deleted Successfully");
                    }
                } else {
                    logger.log(Level.INFO, "Job not completed, cannot be deleted");
                    return DCMErrorCodes.FAILURE;
                }
            } catch (Exception ex) {
                logger.log(Level.INFO, ex.getMessage());
                return DCMErrorCodes.FAILURE;
            }
        } else {
            logger.log(Level.INFO, "Job Id not provided");
            return DCMErrorCodes.FAILURE;
        }
        return DCMErrorCodes.SUCCESS;
    }

    /**
     * Method for updating a single component using Component Id
     *
     * @param dupFilePath specifies the location of dup
     * @param rebootRequired specifies whether reboot is required or not for
     * that component
     * @param componentId specifies the component id
     * @return HashMap with success or failure and list of the jobId.
     */
    public HashMap<Integer, List> updateComponentWithComponentId(String dupFilePath, boolean rebootRequired, String componentId) {

        logger.log(Level.INFO, "Inside updateComponentWithComponentId method");
        HashMap<Integer, List> updateMap = new HashMap<>();

        try {
            String instanceId = getInstanceId(componentId, null, null, null, null);

            if (instanceId == null) {
                logger.log(Level.SEVERE, "Unable to get the instance ID");
                updateMap.put(DCMErrorCodes.FAILURE, null);
                return updateMap;
            } else {
                updateMap = updateComponent(instanceId, dupFilePath, rebootRequired);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while executing updating the component ", e.getMessage());
            updateMap.put(DCMErrorCodes.FAILURE, null);
        }

        return updateMap;
    }

    /**
     * Method for updating a single component using Device Id
     *
     * @param dupFilePath specifies the location of dup
     * @param rebootRequired specifies whether reboot is required or nor for
     * that component
     * @param vendorId specifies the vendorId
     * @param deviceId specifies the deviceId
     * @param subVendorId specifies the subVendorId
     * @param subDeviceId specifies the subDeviceId
     * @return HashMap success or failure with jodID
     */
    public HashMap<Integer, List> updateComponentWithDeviceId(String dupFilePath, boolean rebootRequired, String vendorId, String deviceId, String subVendorId, String subDeviceId) {
        logger.log(Level.INFO, "Inside updateComponentWithDeviceId method");

        HashMap<Integer, List> updateMap = new HashMap<>();
        try {
            String instanceId = getInstanceId("", vendorId, deviceId, subVendorId, subDeviceId);

            if (instanceId == null) {
                logger.log(Level.SEVERE, "Unable to get the instance ID");
                updateMap.put(DCMErrorCodes.FAILURE, null);
                return updateMap;
            } else {
                updateMap = updateComponent(instanceId, dupFilePath, rebootRequired);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while executing updating the component ", e.getMessage());
            updateMap.put(DCMErrorCodes.FAILURE, null);
        }
        return updateMap;
    }

    /**
     * Method to get the instanceId for the component
     *
     * @param componentId
     * @param vendorId
     * @param deviceId
     * @param subVendorId
     * @param subDeviceId
     * @return
     * @throws Exception
     */
    private String getInstanceId(String componentId, String vendorId, String deviceId, String subVendorId, String subDeviceId) {

        logger.log(Level.INFO, "Inside getInstanceId method");
        CollectInventory inv = new CollectInventory(this.iDracIpAddr, this.authentication.getUserName(), this.authentication.getPassword(), "443");
        String instanceId;
        try {
            instanceId = inv.getInstanceId(componentId, vendorId, deviceId, subVendorId, subDeviceId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Cannot get the instance ID", ex.getMessage());
            instanceId = null;
        }
        return instanceId;
    }

    /**
     * Method to update the component
     *
     * @param instanceId specifies the instance id
     * @param dupFilePath specifies the dup file path
     * @param rebootRequired specifies true or false for reboot
     * @return HashMap contains the success or failure and job list
     * @throws Exception
     */
    public HashMap<Integer, List> updateComponent(String instanceId, String dupFilePath, boolean rebootRequired) throws Exception {

        List<String> listOfJobIds = new ArrayList<>();
        HashMap<Integer, List> updateMap = new HashMap<>();
        logger.log(Level.INFO, "Inside UpdateComponent");
        if (instanceId == null || instanceId.equals("")) {
            logger.log(Level.SEVERE, "InstanceId cannot be null");
            listOfJobIds = null;
            updateMap.put(DCMErrorCodes.FAILURE, listOfJobIds);
        } else {
            InstallFromURICmd installUri = new InstallFromURICmd(this.iDracIpAddr, this.port,
                    this.authentication.getUserName(), this.authentication.getPassword(), dupFilePath, instanceId);
            CommandResponse res = (CommandResponse) installUri.execute();
            listOfJobIds.add(res.getJobID());
            if (rebootRequired) {
                CreateRebootJobCmd rebootCmd = new CreateRebootJobCmd(this.iDracIpAddr,
                        this.port, this.authentication.getUserName(), this.authentication.getPassword(), true);
                res = (CommandResponse) rebootCmd.execute();
                listOfJobIds.add(res.getJobID());
                logger.log(Level.INFO, "SetupJob Cmd");
                List <String>jobIds = getJobsList();
                if (!jobIds.isEmpty()) {
                    SetupJobQueueCmd setupJobCmd = new SetupJobQueueCmd(this.iDracIpAddr, this.port,
                            this.authentication.getUserName(), this.authentication.getPassword(), jobIds);
                    setupJobCmd.execute();
                } else {
                    logger.log(Level.SEVERE, "No Jobs in the setup");
                }
            }
            updateMap.put(DCMErrorCodes.SUCCESS, listOfJobIds);
        }
        return updateMap;
    }
}
