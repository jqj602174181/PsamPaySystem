package com.centerm.psampaysystem;

import com.centerm.iccard.ETC;

import android.app.Application;

public class MyApp extends Application {

	//	private ETC etc;

	@Override
	public void onCreate() {
		super.onCreate();

		//		etc = ETC.getInstance();
		//		String device = "/dev/ttyS1";
		//		int result = etc.PSAM_Core_InitLib(device.getBytes(), 9600);
		//		System.out.println("lib��ʼ�������" + result);
		//		result = etc.PSAM_Core_ConnectCard(0x10);
		//		System.out.println("�������ӽ����" + result);
	}

}
