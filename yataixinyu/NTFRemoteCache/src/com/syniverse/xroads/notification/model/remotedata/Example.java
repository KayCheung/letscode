package com.syniverse.xroads.notification.model.remotedata;

public class Example {
	public void demoRemoteDate() {
		RemoteDateService rds = RemoteDateServiceProvider.getService();
		rds.getPermByOrg(null, null);
	}
}
