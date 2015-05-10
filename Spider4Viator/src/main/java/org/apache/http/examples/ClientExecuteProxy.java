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

package org.apache.http.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * How to send a request via proxy.
 * 
 * @since 4.0
 */
public class ClientExecuteProxy {
	static class IPPort {
		private final String ip;
		private final int port;

		public IPPort(String ip, int port) {
			this.ip = ip;
			this.port = port;
		}

		@Override
		public String toString() {
			return "[ ip=" + ip + ", port=" + port + " ]";
		}
	}

	private static List<IPPort> getIPPort() throws Exception {
		List<IPPort> list = new ArrayList<IPPort>();

		BufferedReader br = new BufferedReader(new FileReader(PROXY_FULL_PATH));
		String line = null;
		while ((line = br.readLine()) != null) {
			int dot = line.indexOf(" ");
			if (dot != -1) {
				String ip = line.substring(0, dot);
				String strPort = line.substring(dot);
				int port = Integer.parseInt(strPort.trim());
				IPPort ippt = new IPPort(ip, port);
				list.add(ippt);
				System.out.println(ippt);
			}
		}
		br.close();
		return list;
	}

	public static final String PROXY_FULL_PATH = "/home/marvin/Eden/gitworkspace/letscode/Spider4Viator/httpproxy.txt";

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		List<IPPort> list = getIPPort();
		for (IPPort ipPort : list) {

			HttpHost proxy = new HttpHost(ipPort.ip, ipPort.port, "http");
			RequestConfig config = RequestConfig.custom().setProxy(proxy)
					.build();

			CloseableHttpResponse response = null;
			try {
				HttpHost target = new HttpHost("www.google.com", 80, "https");
				HttpGet request = new HttpGet("/#safe=off&q=marvin");
				request.setConfig(config);
				System.out.println("Executing request "
						+ request.getRequestLine() + " to " + target + " via "
						+ proxy);
				response = httpclient.execute(target, request);

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				EntityUtils.consume(response.getEntity());
				System.out.println("Success!!!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					response.close();
				}
			}
		}

		httpclient.close();
	}
}