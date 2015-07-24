/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.tuniu.mob.category.company;

import com.tuniu.mob.category.util.IOUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This example demonstrates how HttpClient can be used to perform form-based
 * logon.
 * <p>
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 */
public class ClientFormLogin {
    public static final String User_Agent = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:37.0) Gecko/20100101 Firefox/37.0";


    public static void main(String[] args) throws Exception {
        fdfdfdfdfdfdfdf();
    }

    public static void fdfdfdfdfdfdfdf() throws Exception {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                builder.build());
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).setDefaultCookieStore(cookieStore)
                .build();

        get("http://crm.tuniu.com/main.php?do=new_crm_main", httpclient, cookieStore);
        get("http://crm.tuniu.com/main.php?do=new_crm", httpclient, cookieStore);
        String htmlContent = get("https://sso2.tuniu.org/cas/login?service=http%3A%2F%2Fcrm.tuniu.com%2Fmain.php%3Fdo%3Dnew_crm",
                httpclient, cookieStore);
        doLogin(httpclient, cookieStore, htmlContent);


        get("http://crm.tuniu.com/oa/index.php?m=OaTuniuMeal%2Cadmin&class_id=2&where_cond=&food_id=0&area_id=&begin_date=2015-07-23&end_date=2015-07-23&dep_id=1333&dep_name=&saler_name=&add_user_name=", httpclient, cookieStore);

    }

    private static String get(String geturl, CloseableHttpClient httpclient, BasicCookieStore cookieStore) {
        HttpGet httpget = new HttpGet(geturl);
        // set up http header
        for (Entry<String, String> e : createGetHeaders().entrySet()) {
            httpget.addHeader(e.getKey(), e.getValue());
        }

        CloseableHttpResponse resp = null;
        try {
            resp = httpclient.execute(httpget);
            String statusLine = resp.getStatusLine().toString();
            String htmlContent = EntityUtils.toString(resp.getEntity());

            displayResult("Get", geturl, statusLine, htmlContent);
            displayCookies(cookieStore);
            IOUtil.writeToFile(IOUtil.createBufferedWriter("c:/a.txt", "UTF-8", true), htmlContent);
            return htmlContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(resp);
        }
        return null;
    }

    private static Map<String, String> createGetHeaders() {
        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("User-Agent", User_Agent);
        mapHeaders.put("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        mapHeaders.put("Accept-Encoding", "gzip, deflate");
        mapHeaders.put("Accept-Language", "en-US,en;q=0.5");
        mapHeaders.put("Connection", "keep-alive");
        return mapHeaders;
    }

    private static String displayResult(String method, String url, String statusLine, String htmlContent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Method: " + method);
        sb.append("\nURL: " + url);
        sb.append("\nStatus Line: " + statusLine);
        sb.append("\nhtmlContent: " + htmlContent);
        return sb.toString();
    }

    private static void displayCookies(BasicCookieStore cookieStore) {
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None cookie");
            return;
        }
        for (Cookie ck : cookies) {
            System.out.println("- " + ck.toString());
        }
    }

    private static String createLoginUrl(BasicCookieStore cookieStore) {
        String loginHeader = "https://sso2.tuniu.org/cas/login;jsessionid=";
        String loginTail = "?service=http%3A%2F%2Fcrm.tuniu.com%2Fmain.php%3Fdo%3Dnew_crm";

        for (Cookie ck : cookieStore.getCookies()) {
            String name = ck.getName();
            String value = ck.getValue();
            if ("JSESSIONID".equalsIgnoreCase(name)) {
                String loginUrl = loginHeader + value + loginTail;
                System.out.println("JSESSIONID=" + value + ", loginUrl=" + loginUrl);
                return loginUrl;
            }
        }
        return null;
    }

    private static String doLogin(CloseableHttpClient httpclient, BasicCookieStore cookieStore,
                                  String htmlContent) {
        Map<String, String> mapHeaders = createPostHeaders("https://sso2.tuniu.org/cas/login?service=http%3A%2F%2Fcrm.tuniu.com%2Fmain.php%3Fdo%3Dnew_crm");
        Map<String, String> mapParameters = createPostParameters(htmlContent);
        return post(createLoginUrl(cookieStore), mapHeaders, httpclient, cookieStore, mapParameters);
    }

    private static Map<String, String> createPostHeaders(String referer) {
        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("User-Agent", User_Agent);
        mapHeaders.put("Referer", referer);
        mapHeaders.put("X-Requested-With", "XMLHttpRequest");
        mapHeaders.put("Pragma", "no-cache");
        mapHeaders.put("Content-Type",
                "application/x-www-form-urlencoded; charset=utf-8");
        mapHeaders.put("Connection", "keep-alive");
        mapHeaders.put("Accept-Encoding", "gzip, deflate");
        mapHeaders.put("Cache-Control", "no-cache");
        mapHeaders.put("Accept", "text/plain, */*; q=0.01");
        mapHeaders.put("Accept-Language", "en-US,en;q=0.5");
        mapHeaders.put("Pragma", "no-cache");
        return mapHeaders;
    }

    private static Map<String, String> createPostParameters(
            String htmlContent) {
        Map<String, String> kv = new HashMap<>();
        kv.put("username", "libing2");
        kv.put("password", "Susan265");
        kv.put("lt", extract(htmlContent, "<input type=\"hidden\" name=\"lt\" extract=\""));
        kv.put("execution", extract(htmlContent, "<input type=\"hidden\" name=\"execution\" extract=\""));
        kv.put("_eventId", "submit");
        kv.put("submit", "%E7%99%BB%E5%BD%95");
        return kv;
    }

    private static String extract(String htmlContent, String prefixKey) {
        int len = prefixKey.length();
        int pos = htmlContent.indexOf(prefixKey);
        int valueStart = pos + len;
        int valueEnd = htmlContent.indexOf("\"", valueStart);
        String value = htmlContent.substring(valueStart, valueEnd);
        System.out.println("prefixKey=" + prefixKey + ", value=" + value);
        return value;

    }

    private static String post(String posturl, Map<String, String> mapHeaders,
                               CloseableHttpClient httpclient, BasicCookieStore cookieStore, Map<String, String> mapParameters) {
        CloseableHttpResponse resp = null;
        try {
            RequestBuilder rb = RequestBuilder.post().setUri(new URI(posturl));
            // set up post parameter
            for (Entry<String, String> e : mapParameters.entrySet()) {
                rb.addParameter(e.getKey(), e.getValue());
            }
            HttpUriRequest req = rb.build();
            // set up http header
            for (Entry<String, String> e : mapHeaders.entrySet()) {
                req.addHeader(e.getKey(), e.getValue());
            }
            resp = httpclient.execute(req);
            String statusLine = resp.getStatusLine().toString();
            String htmlContent = EntityUtils.toString(resp.getEntity());

            displayResult("Post", posturl, statusLine, htmlContent);
            displayCookies(cookieStore);
            IOUtil.writeToFile(IOUtil.createBufferedWriter("c:/a.txt", "UTF-8", true), htmlContent);
            return htmlContent;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(resp);
        }
        return null;
    }

}
