package com.syniverse.xroads.notification.model.remotedata;

public class RemoteDataServiceProvider {
	public static RemoteDataService getService() {
		RemoteDataService INSTANCE = RemoteDataServiceImpl.getService();
		return INSTANCE;
	}
}
