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
package org.swssf.wss.securityEvent;

import org.opensaml.common.SAMLVersion;
import org.swssf.wss.impl.securityToken.SAMLSecurityToken;

/**
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class SamlTokenSecurityEvent extends IssuedTokenSecurityEvent {

    public SamlTokenSecurityEvent() {
        super(Event.SamlToken);
    }

    public SAMLVersion getSamlVersion() {
        return ((SAMLSecurityToken) getSecurityToken()).getSamlVersion();
    }

    @Override
    public String getIssuerName() {
        return ((SAMLSecurityToken) getSecurityToken()).getIssuer();
    }
}
