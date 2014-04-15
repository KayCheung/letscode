/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.glassfish.json.tests;

import junit.framework.TestCase;

import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.*;
import java.net.URL;

/**
 * JsonParser Tests using twitter search API
 *
 * @author Jitendra Kotamraju
 */
public class TwitterSearchTest extends TestCase {

    public void test() {
        // dummy test so that junit doesn't complain
    }

    public void xtestStreamTwitter() throws Exception {
        URL url = new URL("http://search.twitter.com/search.json?q=%23java&rpp=100");
        InputStream is = url.openStream();
        JsonParser parser = Json.createParser(is);

        while(parser.hasNext()) {
            Event e = parser.next();
            if (e == Event.KEY_NAME) {
                if (parser.getString().equals("from_user")) {
                    parser.next();
                    System.out.print(parser.getString());
                    System.out.print(": ");
                } else if (parser.getString().equals("text")) {
                    parser.next();
                    System.out.println(parser.getString());
                    System.out.println("---------");
                }
            }
        }
        parser.close();
	}

    public void xtestObjectTwitter() throws Exception {
        URL url = new URL("http://search.twitter.com/search.json?q=%23java&rpp=100");
        InputStream is = url.openStream();
        JsonReader rdr = Json.createReader(is);
        JsonObject obj = rdr.readObject();
        JsonArray results = obj.getJsonArray("results");
        for(JsonObject result : results.getValuesAs(JsonObject.class)) {
            System.out.print(result.get("from_user"));
            System.out.print(": ");
            System.out.println(result.get("text"));
            System.out.println("-----------");
        }
        rdr.close();
    }

}
