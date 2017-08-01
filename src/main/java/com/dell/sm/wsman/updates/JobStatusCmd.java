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

import com.dell.sm.wsman.utility.WSManageSession;
import com.dell.sm.wsman.utility.WSManBaseCommand;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.dell.cm.inventory.WSMan.XMLTool;
import com.dell.sm.wsman.utility.WSCommandRNDConstant;
import com.dell.sm.wsman.utility.WSManClassEnum;
import com.dell.sm.wsman.inventory.XMLTool;
import com.dell.sm.wsman.inventory.XMLTool.NameScope;
import com.sun.ws.management.addressing.Addressing;
import java.util.logging.Level;

/**
 *
 * @author Matthew_G_Stemen
 */
public class JobStatusCmd extends WSManBaseCommand {

    private WSManageSession session = null;
    private String jobId = null;

    private static final Logger logger = Logger.getLogger(JobStatusCmd.class.getName());

//	private static String resourceUri ="http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/root/dcim/DCIM_LifecycleJob";
    public JobStatusCmd(String DRACIP, String DRACPort, String DRACUser, String DRACPassword, String jobId) {

        // set the WSMan Session
        super(DRACIP, DRACPort, DRACUser, DRACPassword);
        logger.log(Level.INFO, "Entering constructor: JobStatusCmd(String DRACIP - %s, String DRACPort - %s, String DRACUser - %s, String DRACPassword - %s, String jobId - %s");
        session = this.getSession();
        this.jobId = jobId;
        logger.log(Level.INFO, "Exiting constructor: JobStatusCmd()");
    }

    public JobStatusCmd(String DRACIP, String DRACPort, String DRACUser, String DRACPassword) {

        // set the WSMan Session
        super(DRACIP, DRACPort, DRACUser, DRACPassword);
        session = this.getSession();
        logger.log(Level.INFO, "Exiting constructor: JobStatusCmd()");
    }

    public String getResourceURI() {
        StringBuilder sb = new StringBuilder(
                WSCommandRNDConstant.WSMAN_BASE_URI);

        sb.append(WSCommandRNDConstant.WS_OS_SVC_NAMESPACE).append(
                WSManClassEnum.DCIM_LifecycleJob);

        return sb.toString();
    }

    @Override
    public List<LifeCycleJob> execute() throws Exception {

        logger.log(Level.INFO, "Entering function: execute()");
        session.setResourceUri(getResourceURI());
        Addressing returnDoc = null;
        //jobId = "JID_001281437210";
        if (jobId != null) {
            session.addSelector("InstanceID", jobId);
            returnDoc = session.sendGetRequest();
        } else {
            returnDoc = session.sendRequestEnumeration();
        }

        Document retDoc = returnDoc.getBody().extractContentAsDocument();
        //XMLTool.printToSysOut(retDoc);
        List<LifeCycleJob> lcj = getJobStatus(retDoc);

        logger.log(Level.INFO, "Exiting function: execute()");
        return lcj;
    }

    public void setJobID(String jobId) {
        this.jobId = jobId;
    }

    /**
     * Create job from document
     *
     * @param doc
     * @return LifeCycleJob
     */
    private List<LifeCycleJob> getJobStatus(Document doc) {

        List<LifeCycleJob> retArray = new ArrayList<LifeCycleJob>();
        XMLTool xmlTool = new XMLTool(doc);
        xmlTool.setSearchScope(NameScope.LocalName);
        NodeList nl = xmlTool.getTargetNodes("DCIM_LifecycleJob");

        for (int i = 0; i < nl.getLength(); i++) {
            Node lcjNode = nl.item(i);
            LifeCycleJob lcj = new LifeCycleJob();

            lcj.setInstanceID(xmlTool.getTextValueFromNode(lcjNode, "InstanceID"));
            lcj.setJobStartTime(xmlTool.getTextValueFromNode(lcjNode, "JobStartTime"));
            lcj.setJobStatus(xmlTool.getTextValueFromNode(lcjNode, "JobStatus"));
            if (lcj.getJobStatus() != null && lcj.getJobStatus().equals("New")) {
                lcj.setJobStatus("New Update Job");
            }
            lcj.setJobUntilTime(xmlTool.getTextValueFromNode(lcjNode, "JobUntilTime"));
            lcj.setMessage(xmlTool.getTextValueFromNode(lcjNode, "Message"));
            lcj.setMessageID(xmlTool.getTextValueFromNode(lcjNode, "MessageID"));
            lcj.setJobName(xmlTool.getTextValueFromNode(lcjNode, "Name"));
            retArray.add(lcj);
        }
        return retArray;
    }
}
