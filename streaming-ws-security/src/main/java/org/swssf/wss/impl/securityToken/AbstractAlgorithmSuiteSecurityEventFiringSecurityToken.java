/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.swssf.wss.impl.securityToken;

import org.swssf.wss.ext.WSSecurityContext;
import org.swssf.wss.securityEvent.AlgorithmSuiteSecurityEvent;
import org.swssf.wss.securityEvent.SecurityEvent;
import org.swssf.xmlsec.crypto.Crypto;
import org.swssf.xmlsec.ext.SecurityContext;
import org.swssf.xmlsec.ext.XMLSecurityConstants;
import org.swssf.xmlsec.ext.XMLSecurityException;

import javax.security.auth.callback.CallbackHandler;
import java.security.Key;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractAlgorithmSuiteSecurityEventFiringSecurityToken extends AbstractSecurityToken {

    private boolean fireSecretKeySecurityEvent = true;
    private boolean firePublicKeySecurityEvent = true;
    private Map<String, XMLSecurityConstants.KeyUsage> firedSecretKeyAlgorithmEvents = new HashMap<String, XMLSecurityConstants.KeyUsage>();

    private SecurityContext securityContext;

    public AbstractAlgorithmSuiteSecurityEventFiringSecurityToken(SecurityContext securityContext, Crypto crypto, CallbackHandler callbackHandler, String id, Object processor) {
        super(crypto, callbackHandler, id, processor);
        this.securityContext = securityContext;
    }

    public AbstractAlgorithmSuiteSecurityEventFiringSecurityToken(SecurityContext securityContext, String id, Object processor) {
        super(null, null, id, processor);
        this.securityContext = securityContext;
    }

    public AbstractAlgorithmSuiteSecurityEventFiringSecurityToken(SecurityContext securityContext, String id) {
        super(id);
        this.securityContext = securityContext;
    }

    public Key getSecretKey(String algorithmURI, XMLSecurityConstants.KeyUsage keyUsage) throws XMLSecurityException {
        if (fireSecretKeySecurityEvent) {
            XMLSecurityConstants.KeyUsage firedKeyUsage = firedSecretKeyAlgorithmEvents.get(algorithmURI);
            if (keyUsage == null || !keyUsage.equals(firedKeyUsage)) {
                AlgorithmSuiteSecurityEvent algorithmSuiteSecurityEvent = new AlgorithmSuiteSecurityEvent(SecurityEvent.Event.AlgorithmSuite);
                algorithmSuiteSecurityEvent.setAlgorithmURI(algorithmURI);
                algorithmSuiteSecurityEvent.setKeyUsage(keyUsage);
                ((WSSecurityContext) securityContext).registerSecurityEvent(algorithmSuiteSecurityEvent);
                firedSecretKeyAlgorithmEvents.put(algorithmURI, keyUsage);
            }
        }
        return null;
    }

    public PublicKey getPublicKey(XMLSecurityConstants.KeyUsage keyUsage) throws XMLSecurityException {
        return null;
    }
}