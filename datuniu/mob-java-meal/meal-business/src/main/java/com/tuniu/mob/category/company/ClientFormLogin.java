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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tuniu.mob.category.util.IOUtil;

/**
 * This example demonstrates how HttpClient can be used to perform form-based
 * logon.
 * <p>
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 */
@Component
public class ClientFormLogin {
    public static final String User_Agent = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:37.0) Gecko/20100101 Firefox/37.0";
    private static final Logger LOG = LoggerFactory.getLogger(ClientFormLogin.class);

    public static String get(String geturl, CloseableHttpClient httpclient, BasicCookieStore cookieStore) {
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
            throw new RuntimeException(e);
        } finally {
            IOUtil.close(resp);
        }
    }

    public static Map<String, String> createGetHeaders() {
        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("User-Agent", User_Agent);
        mapHeaders.put("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        mapHeaders.put("Accept-Encoding", "gzip, deflate");
        mapHeaders.put("Accept-Language", "en-US,en;q=0.5");
        mapHeaders.put("Connection", "keep-alive");
        return mapHeaders;
    }

    public static String post(String posturl, Map<String, String> mapHeaders,
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
            throw new RuntimeException(e);
        } finally {
            IOUtil.close(resp);
        }
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
            LOG.info("None cookie");
            return;
        }
        for (Cookie ck : cookies) {
            LOG.info("- " + ck.toString());
        }
    }


    public String fetchAndGroupMeals(List<String> listDeptId, List<String> listDeptName) throws Exception {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                builder.build());

        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).setDefaultCookieStore(cookieStore)
                .build();

        prepareCookies(httpclient, cookieStore);


        List<MealInfo> listAllMealInfo = new ArrayList<>();
        for (String deptId : listDeptId) {
            listAllMealInfo.addAll(fetchMealByDeptId(deptId, httpclient, cookieStore));
        }
        Set<MealInfo> setAllMealInfo = new HashSet<>();
        setAllMealInfo.addAll(listAllMealInfo);

        int allCountInList = listAllMealInfo.size();
        int allCountInSet = setAllMealInfo.size();
        LOG.info("allCountInList={}, allCountInSet={}", allCountInList, allCountInSet);
        String verifyInfo = allCountInList == allCountInSet ?
                "Good, allCountInList == allCountInSet" : "Should never happen, allCountInList != allCountInSet";
        LOG.info(verifyInfo);

        Map<String, List<MealInfo>> mapSaler2ListMeal = groupMeals(setAllMealInfo);
        return Beautiful.beautiful(setAllMealInfo.size(), listDeptName, mapSaler2ListMeal);
    }

    private static List<MealInfo> fetchMealByDeptId(String deptId, CloseableHttpClient httpclient, BasicCookieStore cookieStore) throws Exception {
        String firstPageUrl = getFirstPageUrl(deptId);
        String firstPageContent = get(firstPageUrl, httpclient, cookieStore);

        List<MealInfo> listAll = parseXML2GetMeal(firstPageContent);

        String[] pageUrls = getPageUrls(firstPageContent, deptId);
        for (int i = 1; i < pageUrls.length; i++) {
            List<MealInfo> listOnePageMealInfo = parseXML2GetMeal(get(pageUrls[i], httpclient, cookieStore));
            listAll.addAll(listOnePageMealInfo);
        }

        int countFromNgboss = parse2GetTotalCount(firstPageContent);
        int countFromParseXML = listAll.size();

        LOG.info("deptId={}, countFromNgboss={}, countFromParseXML={}", deptId, countFromNgboss, countFromParseXML);
        String verifyInfo = countFromNgboss == countFromParseXML ?
                "Good, countFromNgboss == countFromParseXML" : "Should never happen, countFromNgboss != countFromParseXML";
        LOG.info(verifyInfo);

        return listAll;
    }

    private static Map<String, List<MealInfo>> groupMeals(Collection<MealInfo> allMeals) {
        Map<String, List<MealInfo>> mapSaler2ListMeal = new HashMap<>();

        for (MealInfo m : allMeals) {
            List<MealInfo> list = mapSaler2ListMeal.get(m.saler);
            if (list == null) {
                list = new ArrayList<>();
                mapSaler2ListMeal.put(m.saler, list);
            }
            list.add(m);
        }

        for (List<MealInfo> aGroupMeal : mapSaler2ListMeal.values()) {
            Collections.sort(aGroupMeal);
        }
        return mapSaler2ListMeal;
    }

    private static List<MealInfo> parseXML2GetMeal(String html) {
        List<MealInfo> list = new ArrayList<>();
        Node tbody = parse2GetRoot(html);
        NodeList trList = tbody.getChildNodes();
        for (int i = 0; i < trList.getLength(); i++) {
            Node tr = trList.item(i);
            if (tr.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            MealInfo m = tr2MealInfo(tr);
            if (m != null) {
                list.add(m);
            }
        }
        return list;
    }

    // html转成XML
    private static Node parse2GetRoot(String html) {
        html = preProcess(html);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(html.getBytes("UTF-8"));
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(bis);
            Element root = doc.getDocumentElement();
            return root;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String preProcess(String htmlContent) {
        String startKey = "<table class=\"listtable\" width=\"1024\">";
        String endKey = "</table>";
        return extract(htmlContent, startKey, endKey);
    }

    private static boolean isTH(Node tr) {
        NodeList tdList = tr.getChildNodes();
        for (int i = 0; i < tdList.getLength(); i++) {
            Node td = tdList.item(i);
            if (td.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (((Element) td).getTagName().equalsIgnoreCase("th")) {
                return true;
            }
        }
        return false;
    }

    private static MealInfo tr2MealInfo(Node tr) {
        if (isTH(tr)) {
            return null;
        }

        NodeList tdList = tr.getChildNodes();
        // if 0 record, table still has one tr row, with only 1 td in this tr
        if (tdList.getLength() < 10) {
            return null;
        }

        MealInfo m = new MealInfo();
        int whichTD = 0;
        for (int i = 0; i < tdList.getLength(); i++) {
            Node td = tdList.item(i);
            if (td.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            setMealInfoItem(whichTD, m, td);
            whichTD++;
        }
        return m;
    }

    private static void setMealInfoItem(int whichTD, MealInfo m, Node td) {
        Node tdSon = td.getFirstChild();
        String strValue = (tdSon == null) ? "" :
                ((tdSon.getNodeValue() == null) ? "" : tdSon.getNodeValue().trim());

        //LOG.info(strValue);
        switch (whichTD) {
            case 0:
                m.sequence = strValue;
                break;
            case 1:
                if (strValue.equals("")) {
                    m.saler = "Not Found";
                } else {
                    m.saler = strValue;
                }
                break;
            case 2:
                m.owner = strValue;
                break;
            case 3:
                m.bookDate = strValue;
                break;
            case 4:
                m.offWorkTime = strValue;
                break;
            case 5:
                m.where = strValue;
                break;
            case 6:
                NodeList dept = td.getChildNodes();
                for (int i = 0; i < dept.getLength(); i++) {
                    Node div = dept.item(i);
                    if (div.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    m.deptName = div.getFirstChild().getNodeValue().trim();
                    break;
                }
                break;
            case 7:
                m.addUser = strValue;
                break;
            case 8:
                m.addTimestamp = strValue;
                break;
            case 9:
                m.operation = strValue;
                break;
            default:
                break;
        }
    }

    private static void prepareCookies(CloseableHttpClient httpclient, BasicCookieStore cookieStore) {
        get("http://crm.tuniu.com/main.php?do=new_crm_main", httpclient, cookieStore);
        get("http://crm.tuniu.com/main.php?do=new_crm", httpclient, cookieStore);
        String htmlContent = get("https://sso2.tuniu.org/cas/login?service=http%3A%2F%2Fcrm.tuniu.com%2Fmain.php%3Fdo%3Dnew_crm",
                httpclient, cookieStore);
        doLogin(httpclient, cookieStore, htmlContent);
    }

    private static String getFirstPageUrl(String deptId) {
        return createGetMealUrl(1, deptId);
    }

    private static String[] getPageUrls(String firstPageContent, String deptId) {
        int totalCount = parse2GetTotalCount(firstPageContent);
        int[] pageNumbers = calcPageNumbers(totalCount);
        String[] pageUrls = new String[pageNumbers.length];

        for (int i = 0; i < pageUrls.length; i++) {
            pageUrls[i] = createGetMealUrl(pageNumbers[i], deptId);
        }
        return pageUrls;
    }

    private static int parse2GetTotalCount(String page1Content) {
        String startKey = "共<font color=\"red\"><b>";
        String endKey = "</b>";
        String strTotalCount = extract(page1Content, startKey, endKey).trim();
        LOG.info("Total count=" + strTotalCount);
        return Integer.valueOf(strTotalCount);
    }

    public static int[] calcPageNumbers(int totalCount) {
        int pageSize = 20;
        int pageCount = totalCount / pageSize;
        pageCount = (totalCount % pageSize == 0) ? pageCount : pageCount + 1;
        // even totalCount == 0, pageCount == 1
        pageCount = (pageCount == 0) ? 1 : pageCount;

        int[] pageNumbers = new int[pageCount];
        for (int i = 1; i <= pageCount; i++) {
            pageNumbers[i - 1] = i;
        }
        return pageNumbers;
    }

    private static String createGetMealUrl(int p, String deptId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(new Date());
//        String strDate = "2015-07-23";

        StringBuilder sb = new StringBuilder();
        sb.append("http://crm.tuniu.com/oa/index.php?");
        sb.append("p=" + p);
        sb.append("&m=OaTuniuMeal%2Cadmin&class_id=2&where_cond=&food_id=0&area_id=");
        sb.append("&begin_date=" + strDate + "&end_date=" + strDate);
        sb.append("&dep_id=" + deptId);
        sb.append("&dep_name=&saler_name=&add_user_name=");

        return sb.toString();
    }


    private static String createLoginUrl(BasicCookieStore cookieStore) {
        String loginHeader = "https://sso2.tuniu.org/cas/login;jsessionid=";
        String loginTail = "?service=http%3A%2F%2Fcrm.tuniu.com%2Fmain.php%3Fdo%3Dnew_crm";

        for (Cookie ck : cookieStore.getCookies()) {
            String name = ck.getName();
            String value = ck.getValue();
            if ("JSESSIONID".equalsIgnoreCase(name)) {
                String loginUrl = loginHeader + value + loginTail;
                LOG.info("JSESSIONID=" + value + ", loginUrl=" + loginUrl);
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

    public static Map<String, String> createPostHeaders(String referer) {
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
        byte[] a = new byte[]{117, 115, 101, 114, 110, 97, 109, 101};
        byte[] b = new byte[]{108, 105, 98, 105, 110, 103, 50};
        byte[] c = new byte[]{112, 97, 115, 115, 119, 111, 114, 100};
        byte[] d = new byte[]{83, 117, 115, 97, 110, 50, 54, 53};
        kv.put(new String(a), new String(b));
        kv.put(new String(c), new String(d));
        kv.put("lt", extract(htmlContent, "<input type=\"hidden\" name=\"lt\" value=\"", "\""));
        kv.put("execution", extract(htmlContent, "<input type=\"hidden\" name=\"execution\" value=\"", "\""));
        kv.put("_eventId", "submit");
        kv.put("submit", "%E7%99%BB%E5%BD%95");
        return kv;
    }

    public static String extract(String htmlContent, String startKey, String endKey) {
        int len = startKey.length();
        int pos = htmlContent.indexOf(startKey);
        int valueStart = pos + len;
        int valueEnd = htmlContent.indexOf(endKey, valueStart);
        String value = htmlContent.substring(valueStart, valueEnd);
        LOG.info("startKey=" + startKey + ", endKey=" + endKey + " --> value=" + value);
        return value;

    }
}
