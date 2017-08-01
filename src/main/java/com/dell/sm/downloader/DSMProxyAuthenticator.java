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
package com.dell.sm.downloader;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is custom authentication class created by passing proxy_username and
 * proxy_password. It authenticate the connection once only and afterwards it
 * passed null and hence it will return null. This check is necessary because if
 * proxy authentication fails, proxy server keeps on asking and it will run in
 * an infinite loop.
 *
 * @author punit_ghodasara
 *
 */
public class DSMProxyAuthenticator extends Authenticator {

    private static final Logger LOG = Logger
            .getLogger(DSMProxyAuthenticator.class.getName());

    /**
     * Proxy username, with no setter method.
     */
    private DSMProxy mProxy;
    /**
     * Proxy username, with no setter method.
     */
    private String serverUsername;
    /**
     * Proxy password, with no setter method.
     */
    private String serverPassword;
    /**
     * Proxy server, with no setter method.
     */
    private String server;
    /**
     * If it is for proxy OR server.
     */
    private RequestorType requestorType;

    /**
     * Volatile variable to check if if connection is authenticated once.
     */
    private volatile boolean isAuthReturned = false;

    /**
     * Volatile variable to check if if connection is authenticated once.
     */
    private volatile boolean isProxyReturned = false;

    private String requestingScheme = "";

    public DSMProxyAuthenticator(DSMProxy proxyServer, String server, String serverUsername,
            String serverPassword) {
        super();
        requestorType = RequestorType.PROXY;

        this.mProxy = proxyServer;
        this.server = server;
        this.serverUsername = serverUsername;
        this.serverPassword = serverPassword;
    }

    public DSMProxyAuthenticator(DSMProxy proxyServer, boolean forProxy) {
        super();
        if (forProxy) {
            requestorType = RequestorType.PROXY;
        } else {
            requestorType = RequestorType.SERVER;
        }
        this.mProxy = proxyServer;
        this.server = null;

    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {

        LOG.log(Level.INFO, "Request for {0} authenticate of type {1} from {2}",
                new Object[]{this.getRequestorType(), this.getRequestingScheme(), this.getRequestingHost()});

        if (requestingScheme.equals(this.getRequestingScheme())) {
            LOG.log(Level.WARNING,
                    "Password request has happened already for this scheme, returning null.");
            return null;
        }
        requestingScheme = this.getRequestingScheme();

        if (this.getRequestorType() == RequestorType.PROXY
                && this.getRequestingHost().equalsIgnoreCase(mProxy.getURL())) {
            if (isProxyReturned || (mProxy == null)) {
                LOG.log(Level.INFO,
                        "Password Authenticate has happened once or no proxy set, returning null.");
                return null;
            }
            isProxyReturned = true;
            return new PasswordAuthentication(mProxy.getProxyUserName(),
                    mProxy.getProxyPassword().toCharArray());
        }
        if (this.getRequestorType() == requestorType
                && null == server) {
            isAuthReturned = true;
            return new PasswordAuthentication(mProxy.getProxyUserName(),
                    mProxy.getProxyPassword().toCharArray());
        }
        if (this.getRequestorType() == RequestorType.SERVER
                && this.getRequestingHost().equalsIgnoreCase(server)) {
            isAuthReturned = true;
            return new PasswordAuthentication(serverUsername,
                    serverPassword.toCharArray());
        }

        if (isAuthReturned) {
            LOG.log(Level.INFO,
                    "Password Authenticate has happened once, returning null.");
            return null;
        }

        return null;
    }
}
