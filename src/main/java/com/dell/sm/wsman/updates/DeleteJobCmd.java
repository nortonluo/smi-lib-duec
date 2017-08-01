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
package com.dell.sm.wsman.updates;

import javax.xml.soap.SOAPBody;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import java.util.logging.Logger;

import com.dell.sm.wsman.updates.LifeCycleJob;
import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.utility.WSManBaseCommand;
import com.dell.sm.wsman.utility.WSManMethodEnum;
import com.dell.sm.wsman.utility.WSManageSession;
//import com.dell.pg.tetris.business.common.CommonConstants;
import com.sun.ws.management.addressing.Addressing;
import java.util.logging.Level;

public class DeleteJobCmd extends WSManBaseCommand {

    private WSManageSession session = null;
    private static final Logger logger = Logger.getLogger(DeleteJobCmd.class.getName());

    private String jobIDToDelete = null;
    private boolean sleepAtClearAll = true;
    private boolean deleteAnyJob = false;

    public boolean isDeleteAnyJob() {
        return deleteAnyJob;
    }

    public void setDeleteAnyJob(boolean deleteAnyJob) {
        this.deleteAnyJob = deleteAnyJob;
    }

    public DeleteJobCmd(String ipAddr, String port, String userName, String passwd, String jobIDToDelete) {
        super(ipAddr, port, userName, passwd);
        this.jobIDToDelete = jobIDToDelete;

        session = super.getSession();
        session.setResourceUri(getResourceURI());
        this.addSelectors();
        session.setInvokeCommand(WSManMethodEnum.DELETE_JOB_QUEUE.toString());
        session.addUserParam("JobID", jobIDToDelete);
        sleepAtClearAll = true;

        logger.log(Level.INFO, "Exiting constructor: DeleteJobCmd()");
    }

    /*
     * This method will return the return value returned from the command.
     * Output looks like below.
     <n1:DeleteJobQueue_OUTPUT>
     <n1:Message>The specified job was deleted</n1:Message>
     <n1:MessageID>SUP020</n1:MessageID>
     <n1:ReturnValue>0</n1:ReturnValue>
     </n1:DeleteJobQueue_OUTPUT>
     * 
     */
    @Override
    public Integer execute() throws Exception {
        logger.log(Level.INFO, "Entering function: execute()");

        Addressing response = session.sendInvokeRequest();

        // Get the jobID out of the response.
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        SOAPBody soapBody = response.getBody();

        String returnValue = xpath.evaluate("//*[local-name()='ReturnValue']", soapBody);

        if (!"0".equalsIgnoreCase(returnValue)) {
            if (this.getLCErrorStr() != null || this.getLCErrorCode() != null) {
                logger.log(Level.SEVERE, "WSMan command failed to delete job queue with return code: " + returnValue + ", error [" + this.getLCErrorCode() + ": " + this.getLCErrorStr() + "]");
            } else {
                logger.log(Level.INFO, "WSMan command failed to delete job queue with return code: " + returnValue);
            }
        }

        Integer returnVal = Integer.parseInt(returnValue);
        logger.log(Level.INFO, "Exiting function: execute()");
        return returnVal;

    }

    public final String SYSTEM_CREATION_CLASSNAME = "SystemCreationClassName";
    public final String CREATION_CLASSNAME = "CreationClassName";
    public final String SYSTEM_NAME = "SystemName";
    public final String NAME = "Name";

    protected void addSelectors() {
        session.setResourceUri(getResourceURI());
        session.addSelector(this.CREATION_CLASSNAME, WSManClassEnum.DCIM_JobService.toString());
        session.addSelector(this.SYSTEM_NAME, "Idrac");
        session.addSelector(this.NAME, "JobService");
        session.addSelector(this.SYSTEM_CREATION_CLASSNAME, WSManClassEnum.DCIM_ComputerSystem.toString());
    }

    private String getResourceURI() {
        StringBuilder sb = new StringBuilder(
                WSCommandRNDConstant.WSMAN_BASE_URI);

        sb.append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE).append(
                WSManClassEnum.DCIM_JobService);

        return sb.toString();
    }

}
