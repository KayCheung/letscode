package com.syniverse.xroads.notification.model.remotedata;

public class Example {
	public void demoRemoteDate() {
		RemoteDateService rds = RemoteDateServiceProvider.getService();
		// Dummy implementation always return constant values for each methods,
		// you can pass into any parameter
		System.out.println(rds.getPermByOrg(null, null));
	}

	public static void main(String[] args) {
		new Example().demoRemoteDate();
	}
}
