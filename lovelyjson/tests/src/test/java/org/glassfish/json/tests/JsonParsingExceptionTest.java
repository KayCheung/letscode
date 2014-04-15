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

import javax.json.Json;
import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;

/**
 * JsonParsingException Tests
 *
 * @author Jitendra Kotamraju
 */
public class JsonParsingExceptionTest extends TestCase {

    public void testWrongJson() {
        testMalformedJson("", null);
    }

    public void testWrongJson1() {
        testMalformedJson("{}{}", null);
    }

    public void testWrongJson2() {
        testMalformedJson("{", null);
    }

    public void testWrongJson3() {
        testMalformedJson("{[]", null);
    }

    public void testWrongJson4() {
        testMalformedJson("{]", null);
    }

    public void testWrongJson5() {
        testMalformedJson("{\"a\":[]]", null);
    }

    public void testWrongJson6() {
        testMalformedJson("[ {}, [] }", null);
    }

    public void testWrongJson61() {
        testMalformedJson("[ {}, {} }", null);
    }

    public void testWrongJson7() {
        testMalformedJson("{ \"a\" : {}, \"b\": {} ]", null);
    }

    public void testWrongJson8() {
        testMalformedJson("{ \"a\" : {}, \"b\": [] ]", null);
    }

    public void testWrongUnicode() {
        testMalformedJson("[ \"\\uX00F\" ]", null);
        testMalformedJson("[ \"\\u000Z\" ]", null);
        testMalformedJson("[ \"\\u000\" ]", null);
        testMalformedJson("[ \"\\u00\" ]", null);
        testMalformedJson("[ \"\\u0\" ]", null);
        testMalformedJson("[ \"\\u\" ]", null);
        testMalformedJson("[ \"\\u\"", null);
        testMalformedJson("[ \"\\", null);
    }

    public void testControlChar() {
        testMalformedJson("[ \"\u0000\" ]", null);
        testMalformedJson("[ \"\u000c\" ]", null);
        testMalformedJson("[ \"\u000f\" ]", null);
        testMalformedJson("[ \"\u001F\" ]", null);
        testMalformedJson("[ \"\u001f\" ]", null);
    }

    public void testLocation1() {
        testMalformedJson("x", new MyLocation(1, 1, 0));
        testMalformedJson("{]", new MyLocation(1, 2, 1));
        testMalformedJson("[}", new MyLocation(1, 2, 1));
        testMalformedJson("[a", new MyLocation(1, 2, 1));
        testMalformedJson("[nuLl]", new MyLocation(1, 4, 3));
        testMalformedJson("[falsE]", new MyLocation(1, 6, 5));
        testMalformedJson("[][]", new MyLocation(1, 3, 2));
        testMalformedJson("[1234L]", new MyLocation(1, 6, 5));
    }

    public void testLocation2() {
        testMalformedJson("[null\n}", new MyLocation(2, 1, 6));
        testMalformedJson("[null\r\n}", new MyLocation(2, 1, 7));
        testMalformedJson("[null\n, null\n}", new MyLocation(3, 1, 13));
        testMalformedJson("[null\r\n, null\r\n}", new MyLocation(3, 1, 15));
    }

    private void testMalformedJson(String json, JsonLocation expected) {
        JsonParser parser = Json.createParser(new StringReader(json));
        try {
            while(parser.hasNext()) {
                parser.next();
            }
            fail("Expected to throw JsonParsingException for "+json);
        } catch(JsonParsingException je) {
            // Expected
            if (expected != null) {
                JsonLocation got = je.getLocation();
                assertEquals(expected.getLineNumber(), got.getLineNumber());
                assertEquals(expected.getColumnNumber(), got.getColumnNumber());
                assertEquals(expected.getStreamOffset(), got.getStreamOffset());
            }
        } finally {
            parser.close();
        }
    }

    private static class MyLocation implements JsonLocation {
        private final long columnNo;
        private final long lineNo;
        private final long streamOffset;

        MyLocation(long lineNo, long columnNo, long streamOffset) {
            this.lineNo = lineNo;
            this.columnNo = columnNo;
            this.streamOffset = streamOffset;
        }

        @Override
        public long getLineNumber() {
            return lineNo;
        }

        @Override
        public long getColumnNumber() {
            return columnNo;
        }

        @Override
        public long getStreamOffset() {
            return streamOffset;
        }
    }

}
