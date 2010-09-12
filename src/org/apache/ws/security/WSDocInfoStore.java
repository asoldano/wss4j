/*
 * Copyright  2003-2004 The Apache Software Foundation.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.ws.security;

/**
 * WSDocInfoStore store WSDocInfo structure in a static Hash. 
 * 
 * Also the access methods are static. Thus it is possible to exchange
 * WSDocInfo between otherwise unrelated functions/methods.
 * The main usage for this is (are) the transformation functions that
 * are called during Signature/Verfication process. 
 * 
 * @author Werner Dittmann (Werner.Dittmann@apache.org)
 */

import java.util.Hashtable;

import org.w3c.dom.Document;

public class WSDocInfoStore {

    static Hashtable storage = new Hashtable(10);

    public static WSDocInfo lookup(Document doc) {
        return (WSDocInfo) storage.get(doc);
    }

    public static boolean store(WSDocInfo info) {
        return storage.put(info.getDocument(), info) == null;
    }

    public static void delete(WSDocInfo info) {
        storage.remove(info.getDocument());
    }
}