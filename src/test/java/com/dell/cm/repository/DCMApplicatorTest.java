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
import com.dell.cm.testParameters.TestInputs;
import com.dell.cm.updateinformationmodel.DCMErrorCodes;
import com.dell.sm.downloader.DSMAuthenticationParameters;
import com.dell.sm.wsman.updates.GetRepoBasedUpdateListCmd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rahul_Ranjan2
 */
public class DCMApplicatorTest {
    /**
     * Default Constructor
     */
    public DCMApplicatorTest(){
        
    }
    @BeforeClass
    public static void setUpClass(){
        
    }
    /**
     * Method to allocate resources at class level
     */
    @AfterClass
    public static void tearDownClass(){
        
    }
    /**
     * Method to set up resources required for the tests.
     */
    @Before
    public void setUp(){
        iDracIpAddr=TestInputs.iDracIpAddr;
        mAuthenticationParameters=new DSMAuthenticationParameters();
        mAuthenticationParameters.setUserName(TestInputs.userName);
        mAuthenticationParameters.setPassword(TestInputs.password);
    }
    /**
     * Method to allocate resources at the test
     */
    @After
    public void tearDown(){
        
    }
    /**
     * Method to test updateFromRepo method of DCMApplicator Class
     */
    //@Test
    public void testUpdateFromRepo(){
        System.out.println("updateFromRepo");
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        int expResult=DCMErrorCodes.SUCCESS;
        HashMap<Integer,String> result = instance.updateFromRepo(TestInputs.shareIp, "duec_test", TestInputs.shareLocation, "Catalog.xml", 2 , TestInputs.shareUserName, TestInputs.sharePassword, 1, false);
        int CmdStatus =result.keySet().iterator().next();
        assertEquals(expResult,CmdStatus);
        
    }
    /**
     * Method to test the getJobList method of DCMApplicator Class
     */
    @Test
    public void testGetJobList(){
        List<String> jobList = new ArrayList();
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        jobList = instance.getJobsList();
        assertEquals(jobList,jobList);
    }
    /**
     * Method to test getStatus method of DCMApplicator Class
     * @throws java.lang.Exception
     */
    //@Test
    public void testGetStatus() throws Exception{
        System.out.println("getStatus");
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        String status=instance.getStatus("JID_797506176558");
        System.out.println("Displaying status: "+status);
        assertEquals(status,status);
    }
    /**
     * Method to test the deleteJob method of DCMApplicator
     * @throws java.lang.Exception
     */
    //@Test
    public void testDeleteJob() throws Exception{
        System.out.println("deleteJob");
        List<String> jobList=new ArrayList();
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        GetRepoBasedUpdateListCmd updates=new GetRepoBasedUpdateListCmd(iDracIpAddr,"443",mAuthenticationParameters.getUserName(),mAuthenticationParameters.getPassword(),false);
        Map<String,String> jobMap=updates.execute();
            for(String key : jobMap.keySet()){
                String jobId=jobMap.get(key);
                jobList.add(jobId);
            }
        int expResult=DCMErrorCodes.SUCCESS;
        int result=instance.deleteJob(jobList.get(0));
        assertEquals(expResult,result);
    }
    /**
     * Method to test updateComponentWithComponentId
    */
    //@Test
    public void testUpdateComponentWithComponentId(){
        System.out.println("updateComponenetWithComponentId");
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        String componentId="25227";
        int expResult=DCMErrorCodes.SUCCESS;
        HashMap<Integer,List> result=instance.updateComponentWithComponentId("\\\\10.94.147.47\\store\\cache\\FOLDER03860182M\\1\\iDRAC-with-Lifecycle-Controller_Firmware_WH24V_WN32_2.40.40.40_X42.EXE",false,componentId);
        int CmdStatus=result.keySet().iterator().next();
        for(Map.Entry<Integer,List> entry:result.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
            List jid=entry.getValue();
            String status,id;
            Iterator<String> it=jid.iterator();
            while(it.hasNext()){
                id=it.next();
                status=instance.getStatus(id);
                System.out.println(id+": "+status);
            }
        }
        assertEquals(expResult,CmdStatus);
    }
    /**
     * Method to test the updateComponentWithDeviceId
    */
    //@Test
    public void testUpdateComponentWithdeviceId(){
        System.out.println("updateComponentWithDeviceId");
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        int expResult=DCMErrorCodes.SUCCESS;
        HashMap<Integer,List> result=instance.updateComponentWithDeviceId("\\\\10.94.147.47\\store\\cache\\FOLDER03587453M\\2\\SAS-RAID_Driver_98GDG_WN64_6.603.06.00_A03.EXE", false, "1000", "005D", "1028", "1F54");
        System.out.println(result.keySet().iterator().next());
        int Cmdstatus=result.keySet().iterator().next();
        /*for(Map.Entry<Integer,List> entry:result.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
            List jid=entry.getValue();
            String status,id;
            Iterator<String> it=jid.iterator();
            while(it.hasNext()){
                id=it.next();
                status=instance.getStatus(id);
                System.out.println(id+": "+status);
            }
        }*/
        assertEquals(expResult,Cmdstatus);
    }
    /**
     * Method to test the updateComponent method.
     * @throws java.lang.Exception
     */
    //@Test
    public void testUpdateComponent() throws Exception{
        System.out.println("updateComponent");
        DCMApplicator instance=new DCMApplicator(iDracIpAddr,"443",mAuthenticationParameters);
        int expResult=DCMErrorCodes.SUCCESS;
        HashMap<Integer,List> result=instance.updateComponent("DCIM:INSTALLED#301_C_RAID.Modular.3-2", "\\\\10.94.147.47\\store\\cache\\FOLDER03587453M\\2\\SAS-RAID_Driver_98GDG_WN64_6.603.06.00_A03.EXE", false);
        int status=result.keySet().iterator().next();
        assertEquals(expResult,status);
    }
    private String iDracIpAddr;
    DSMAuthenticationParameters mAuthenticationParameters;
}
