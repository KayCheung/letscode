package com.syniverse.xroads.notification.model.remotedata;

public class RemoteDateServiceProvider {
	public static RemoteDateService getService() {
		RemoteDateService INSTANCE = RemoteDateServiceImpl.getService();
		return INSTANCE;
	}
}
