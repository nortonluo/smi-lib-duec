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
package com.dell.sm.wsman.utility;

import com.dell.sm.downloader.DSMProxy;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;

import java.util.logging.Logger;

import com.sun.ws.management.Management;
import com.sun.ws.management.addressing.Addressing;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.logging.Level;

/**
 *
 * @author Dan_Phelps
 *
 */
public class HttpConnection {

    private HttpsURLConnection connection = null;
    private String destination = null;
    private String destIP = null;
    private int timeout = 60000; // default timeout is 60 seconds
    private static final int MAX_AUTHENTICATION_RETRY = 2;
    private boolean bCheckCertificate = false; // default policy for cert check is false

    // set up a logger
    private static final Logger logger = Logger.getLogger(HttpConnection.class.getName());

    private static String WSMAN_CERTS = "/usr/lib/jvm/java/jre/lib/security/wsmancerts";
    private static String WSMAN_CERTS_PWD = "changeit";
    private static KeyStore truststore = null; // making this static since we want to load keystore
    // only once
    private X509Certificate[] certChain = null;

    public SSLContext sslContext = null;

    private DSMProxy mProxy;

    /**
     *
     * @param dest dest is target URL
     * @param destIP destIP is target IP
     * @param bCheckCert bCheckCert is for checking the certificate
     */
    public HttpConnection(String dest, String destIP, boolean bCheckCert, DSMProxy proxy) {
        destination = dest;
        this.destIP = destIP;
        this.bCheckCertificate = bCheckCert;
        this.mProxy = proxy;
    }

    public void setTimeout(int to) {
        timeout = to;
    }

    public Addressing sendHttp(Addressing msg) throws IOException, JAXBException, SOAPException {
        Addressing reply = null;
        long connectionTimeoutTime = System.currentTimeMillis() + timeout;
        String sOutput = "";
        StringBuilder result = new StringBuilder();
        int unauthorizedCount = 0;

        while (true) {
            OutputStream out = null;
            InputStream in = null;
            BufferedReader bufferedReader = null;
            InputStreamReader inputStringReader = null;
            ByteArrayInputStream byteArrayInputStream = null;
            try {
                initializeConnection();
                result.setLength(0);
                logger.log(Level.INFO, " Doing HTTP Connect(Addressing)...");
                connection.connect();
                logger.log(Level.INFO, " Doing HTTP Connect Successful");
                out = connection.getOutputStream();
                msg.writeTo(out);
                in = connection.getInputStream();

                inputStringReader = new InputStreamReader(in);
                bufferedReader = new BufferedReader(inputStringReader);
                String inputLine = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    result.append(inputLine.trim());
                }

                int c;
                for (int index = 0; index < result.length(); index++) {
                    c = result.charAt(index);
                    if (c < 32) {
                        result.setCharAt(index, ' ');
                    }
                }

                sOutput = result.toString();
                checkResponse();
                byteArrayInputStream = new ByteArrayInputStream(sOutput.getBytes());
                reply = new Addressing(byteArrayInputStream);
                connection.disconnect();
                break;
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, "Caught IO exception: ", ioe);
                int rc = this.checkResponse();
                if (rc == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    if (++unauthorizedCount >= MAX_AUTHENTICATION_RETRY) {
                        logger.log(Level.SEVERE, ioe.getMessage());
                        IOException ioegen = new IOException("The IDRAC credentials entered are invalid");
                        ioegen.initCause(ioe);
                        throw ioegen;
                    }
                }
                if (System.currentTimeMillis() > connectionTimeoutTime) {
                    logger.log(Level.SEVERE, ioe.getMessage());
                    throw ioe;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
            } catch (SOAPException soe) {
                logger.log(Level.SEVERE, soe.getMessage());
                // dump raw data to log file
                sOutput = sOutput.replaceAll("&gt;", ">");
                sOutput = sOutput.replaceAll("&lt;", "<");
                logger.log(Level.SEVERE, soe.getMessage());
                // throw exception to top level code
                throw soe;
            } finally {
                Utilities.closeStreamQuietly(in);
                Utilities.closeStreamQuietly(out);
                Utilities.closeStreamQuietly(inputStringReader);
                Utilities.closeStreamQuietly(bufferedReader);
                Utilities.closeStreamQuietly(byteArrayInputStream);
            }
        }
        return reply;
    }

    public Addressing sendHttp(Management msg) throws IOException, JAXBException, SOAPException {

        Addressing reply = null;
        String sOutput = "";
        long connectionTimeoutTime = System.currentTimeMillis() + timeout;
        StringBuilder result = new StringBuilder();
        int unauthorizedCount = 0;

        while (true) {
            OutputStream out = null;
            InputStream in = null;
            BufferedReader buffer = null;
            InputStreamReader inputStreamReader = null;
            ByteArrayInputStream byteArrayInputStream = null;

            try {
                initializeConnection();
                result.setLength(0);
                logger.log(Level.INFO, " Doing HTTP Connect(Management)...");
                connection.connect();
                logger.log(Level.INFO, " Doing HTTP Connect Successful");
                out = connection.getOutputStream();
                msg.writeTo(out);
                in = connection.getInputStream();
                inputStreamReader = new InputStreamReader(in);
                buffer = new BufferedReader(inputStreamReader);
                String inputLine = "";
                while ((inputLine = buffer.readLine()) != null) {
                    result.append(inputLine.trim());
                }

                int c;
                for (int index = 0; index < result.length(); index++) {
                    c = result.charAt(index);
                    if (c < 32) {
                        result.setCharAt(index, ' ');
                    }
                }

                sOutput = result.toString();
                checkResponse();
                byteArrayInputStream = new ByteArrayInputStream(sOutput.getBytes());
                reply = new Addressing(byteArrayInputStream);
                connection.disconnect();
                break;
            } catch (IOException ioe) {
                logger.log(Level.SEVERE, ioe.getMessage());
                int rc = this.checkResponse();
                if (rc == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    if (++unauthorizedCount >= MAX_AUTHENTICATION_RETRY) {
                        logger.log(Level.SEVERE, ioe.getMessage());
                        IOException ioegen = new IOException("IDRAC credentials entered are invalid");
                        ioegen.initCause(ioe);
                        throw ioegen;
                    }
                }
                if (System.currentTimeMillis() > connectionTimeoutTime) {
                    logger.log(Level.SEVERE, ioe.getMessage());
                    throw ioe;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, e.getMessage());
                }
            } catch (SOAPException soe) {
                logger.log(Level.SEVERE, "SOAPException sendHttp: " + destIP, soe);
                // dump raw data to log file
                sOutput = sOutput.replaceAll("&gt;", ">");
                sOutput = sOutput.replaceAll("&lt;", "<");
                logger.log(Level.SEVERE, "SOAPException sendHttp: ", sOutput);
                // throw exception to top level code
                throw soe;
            } finally {
                Utilities.closeStreamQuietly(inputStreamReader);
                Utilities.closeStreamQuietly(buffer);
                Utilities.closeStreamQuietly(in);
                Utilities.closeStreamQuietly(out);
                Utilities.closeStreamQuietly(byteArrayInputStream);
            }
        }
        return reply;
    }

    private void initializeConnection() throws IOException {

        URL url = new URL(destination);
        Proxy proxy;
        if (null != this.mProxy) {
            if (null != this.mProxy.getURL()) {
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(mProxy.getURL(), mProxy.getPortNumber()));
                connection = (HttpsURLConnection) url.openConnection(proxy);
            }
        } else {
            connection = (HttpsURLConnection) url.openConnection();
        }

        connection.setSSLSocketFactory(sslContext.getSocketFactory());

        if (this.bCheckCertificate == true && truststore != null) {
            // Now check if this is the first time we are making a connection.
            try {
                logger.log(Level.INFO, "Checking if SSL Cert for " + destIP + " is present in keystore.");
                if (truststore.containsAlias(destIP) == false) // if this destIP not present in
                // keystore then add it.
                {
                    AddWsmanCertToKeyStore(destIP);
                } else {
                    logger.log(Level.INFO, "SSL Cert for host " + destIP + " already present in keystore.");

                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in initializeConnection while checking/adding cert: " + e.getMessage(), e);
            }
        }
        // else no cert check requsted so no need to do anything.

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setAllowUserInteraction(false);
        connection.setInstanceFollowRedirects(false);
        connection.setReadTimeout(timeout);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/soap+xml;charset=utf-8");
        connection.setRequestProperty("User-Agent", "https://wiseman.dev.java.net");
        connection.setRequestProperty("Accept", "application/soap+xml;charset=utf-8, application/soap+xml;charset=utf-16");
    }

    private int checkResponse() throws IOException {
        int respCode = 0;
        try {
            respCode = connection.getResponseCode();
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            return HttpURLConnection.HTTP_INTERNAL_ERROR;
        }
        switch (respCode) {
            case HttpURLConnection.HTTP_OK:
                logger.log(Level.SEVERE, "HTTP response: OK");
                break;
            case HttpURLConnection.HTTP_BAD_REQUEST:
                logger.info("HTTP response: Bad Request");
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                logger.info("HTTP response: Unauthorized");
                break;
            case HttpURLConnection.HTTP_BAD_METHOD:
                logger.info("HTTP response: Method Not Allowed");
                break;
            case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                logger.info("HTTP response: Request Time-out");
                break;
            case HttpURLConnection.HTTP_UNAVAILABLE:
                logger.info("HTTP response: Service Unavailable");
                break;
            default:
                logger.info("HTTP response: Unknown Reason");
                break;
        }
        return respCode;

    }

    public synchronized void AddWsmanCertToKeyStore(String destIP) {
        SSLSocket sslSocket = null;
        this.certChain = null; // set chain to NULL to clear any previous chains stored there
        // strIPAddress = vCenter.getIpAddress();

        logger.info("Trying connection to " + destIP + " to get SSL cert");
        if (truststore == null) {
            logger.info("Trust store is NULL");
            return;
        }

        try {
            sslSocket = (SSLSocket) sslContext.getSocketFactory().createSocket(destIP, 443);
            sslSocket.setSoTimeout(timeout);
            try {
                sslSocket.startHandshake(); // this call should give us the certificate. It will
                // also throw exception
            } catch (Exception e) {
                logger.info("Exception: " + e.getMessage());
            }

            if (this.certChain == null) {
                logger.log(Level.INFO, "No certificate returned from server " + destIP);
            } else {
                for (int j = 0; j < this.certChain.length; j++) {
                    logger.info("WSMAN: Server certificate information:");
                    logger.info("  Subject DN: " + this.certChain[j].getSubjectDN());
                    logger.info("  Issuer DN: " + this.certChain[j].getIssuerDN());
                    logger.info("  Serial number from server: " + this.certChain[j].getSerialNumber());
                    logger.info("");
                    if (j == 0) {
                        truststore.setCertificateEntry(destIP, this.certChain[j]);
                    } else {
                        truststore.setCertificateEntry(destIP + "." + j, this.certChain[j]);
                    }
                }
                WriteTrustStore();
                //String msg = FriendlyLogMessage.createLogMessage("SECURITY_CERTIFICATE_ADDED", destIP);
                //logger.log(SpectreLogLevel.USER_FACING_SUCCESS, msg);

                logger.info("Resetting socket factory to use the new keystore values.");

                TrustManagerFactory tmFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmFactory.init(truststore);
                X509TrustManager tm = (X509TrustManager) tmFactory.getTrustManagers()[0];
                TrustManagerInterceptor tmInterceptor = new TrustManagerInterceptor(tm, this);
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{
                    tmInterceptor
                }, new SecureRandom());
                connection.setSSLSocketFactory(sslContext.getSocketFactory());
            }

        } catch (Exception e2) {
            logger.log(Level.SEVERE, "Exception while checking for cert " + e2.getMessage(), e2);
        } finally {
            try {
                if (sslSocket != null) {
                    sslSocket.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static synchronized void InitializeTrustStore() {
        if (truststore == null) // initialize trust store
        {

            logger.info("Initializing Trust Store for WSMAN connections.");
            // if file does not exist then the following may fail...this is okay since we will write
            // back the file.
            InputStream input = null;
            try {
                try {
                    input = new FileInputStream(WSMAN_CERTS);
                } catch (FileNotFoundException e) {
                    logger.log(Level.SEVERE, "File  " + WSMAN_CERTS + "Not Found: " + e.getMessage(), e);
                }

                // if "input" is null then a new keystore will be created
                try {
                    truststore = KeyStore.getInstance(KeyStore.getDefaultType());
                    truststore.load(input, WSMAN_CERTS_PWD.toCharArray());
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Exception in InitializeTrustStore " + e.getMessage(), e);
                }
            } finally {
                Utilities.closeStreamQuietly(input);
            }
        }
    }

    public static synchronized void WriteTrustStore() {
        if (truststore != null) // initialize trust store
        {
            logger.info("Writing Trust Store to file");
            OutputStream output = null;
            try {
                // Now write back the trust store
                output = new FileOutputStream(WSMAN_CERTS);
                truststore.store(output, WSMAN_CERTS_PWD.toCharArray());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception in WriteTrustStore " + e.getMessage(), e);
            } finally {
                Utilities.closeStreamQuietly(output);
            }
        }
    }

    public void setTrustManager()
            throws NoSuchAlgorithmException, KeyManagementException {
        if (this.bCheckCertificate == true) // users wants to check certificate
        {
            logger.info("Certificate checking is enabled for this connection.");
            if (truststore == null) // initialize trust store
            {
                InitializeTrustStore();
            }

            if (truststore != null) {
                try {
                    TrustManagerFactory tmFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    tmFactory.init(truststore);
                    X509TrustManager tm = (X509TrustManager) tmFactory.getTrustManagers()[0];
                    TrustManagerInterceptor tmInterceptor = new TrustManagerInterceptor(tm, this);
                    sslContext = SSLContext.getInstance("SSL");
                    sslContext.init(null, new TrustManager[]{
                        tmInterceptor
                    }, new SecureRandom());
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "setTrustManager: " + e.getMessage(), e);
                }
            }
        } else // users does not want to do check certificate
        {
            logger.info("Certificate checking is disabled for this connection.");
            TrustManager[] tm2
                    = {
                        tmNoCheck
                    };
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, tm2, new SecureRandom());
        }

    }

    public void setHostnameVerifier(HostnameVerifier hv) {
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    // Trust Manager which will do certificate checks
    static class TrustManagerInterceptor implements X509TrustManager {

        private X509TrustManager tm;
        private HttpConnection conn;

        TrustManagerInterceptor(X509TrustManager tm, HttpConnection c) {
            this.tm = tm;
            this.conn = c;

        }

//		@Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // TODO Auto-generated method stub
        }

//		@Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // TODO Auto-generated method stub
            conn.certChain = arg0;
            logger.info("Spectre: Doing SSL Certificate checking for this connection.");

            tm.checkServerTrusted(arg0, arg1);
        }

//		@Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    };

    // Trust Manager which will NOT do any Cert checking
    static X509TrustManager tmNoCheck = new X509TrustManager() {

//		@Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // TODO Auto-generated method stub
        }

//		@Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            // TODO Auto-generated method stub
            logger.info("Spectre: Skipping SSL Certificate checking for this connection.");
        }

//		@Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    };
}
