package com.example.baidumap.base;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class BaseApplication extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SDKInitializer.initialize(this);
	}
}
