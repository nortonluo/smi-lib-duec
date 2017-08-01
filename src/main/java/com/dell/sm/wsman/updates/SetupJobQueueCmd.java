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


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dell.sm.wsman.utility.WSManageSession;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.xpath.XPathExpressionException;

import java.util.logging.Logger;

import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.utility.WSManMethodEnum;
import java.util.logging.Level;
//import com.dell.pg.spectre.common.protocol.wsman.WSManBaseCommand;
//import com.dell.pg.spectre.common.protocol.wsman.WSManageSession;

/**
 *
 * @author Matthew_G_Stemen
 */
public class SetupJobQueueCmd extends UpdateBaseCmd {

    private WSManageSession session = null;
    private int port;
    private static final Logger logger = Logger.getLogger(SetupJobQueueCmd.class.getName());

//	public static final String PREFIX = "n1";
    /**
     *
     * @param DRACIP specifies idrac ip address
     * @param DRACPort specifies idrac port number
     * @param DRACUser specifies idrac username
     * @param DRACPassword specifies idrac password
     * @param jobIDs specifies list of job id's
     * @throws SOAPException throws exception if any error
     */
    public SetupJobQueueCmd(String DRACIP, String DRACPort, String DRACUser, String DRACPassword, List<String> jobIDs) throws SOAPException {

        super(DRACIP, Integer.parseInt(DRACPort), DRACUser, DRACPassword, false);
        logger.log(Level.INFO, "Entering constructor: SetJobQueueCmd");
        // set the WSMan Session
        session = this.getSession();
        session.setResourceUri(getResourceURI());
        session.addSelector("CreationClassName", WSManClassEnum.DCIM_JobService.toString());
        session.addSelector("SystemCreationClassName", WSManClassEnum.DCIM_ComputerSystem.toString());
        session.addSelector("SystemName", WSCommandRNDConstant.SYSTEM_NAME);
        session.addSelector("Name", WSCommandRNDConstant.JOB_SERVICE);

        SOAPFactory factory = SOAPFactory.newInstance();

        SOAPElement element = null;
        for (String id : jobIDs) {
//			element = factory.createElement("JobArray", PREFIX, getResourceURI());
            element = factory.createElement("JobArray");
            element.setValue(id);
            session.addUserParam(id, element);
        }

//		element = factory.createElement("StartTimeInterval", PREFIX, getResourceURI());
        element = factory.createElement("StartTimeInterval");
        element.setValue("TIME_NOW");
        session.addUserParam("StartTimeInterval", element);

        session.setInvokeCommand(WSManMethodEnum.SETUP_JOB_QUEUE.toString());
        logger.log(Level.INFO, "Exiting constructor: SetJobQueueCmd()");

    }

    private String getResourceURI() {
        StringBuilder sb = new StringBuilder(
                WSCommandRNDConstant.WSMAN_BASE_URI);

        sb.append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE).append(
                WSManClassEnum.DCIM_JobService);

        return sb.toString();
    }

    @Override
    public String execute() throws IllegalArgumentException, SOAPException, JAXBException, DatatypeConfigurationException, IOException, XPathExpressionException {
        logger.log(Level.INFO, "Entering function: execute()");
        String result = session.sendInvokeRequest().toString();
        if (result != null) {
            if (result.toLowerCase().contains("ReturnValue>0".toLowerCase())) {
                result = "0";
            } else {
                result = this.getLCErrorStr() + "@" + this.getLCErrorCode();
            }
        } else {
            result = "Failed scheduling update";
        }
        logger.log(Level.INFO, "Exiting function: execute()");
        return result;

    }
}
