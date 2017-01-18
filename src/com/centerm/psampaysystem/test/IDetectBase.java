package com.centerm.psampaysystem.test;

import com.centerm.iccard.ETC;

import android.content.Context;

public abstract class IDetectBase {
	public abstract int detect();
	public Context mContext = null;
	
	ETC etc = ETC.getInstance();
	
	public void setContext( Context context )
	{
		mContext = context;
	}
	
	public void powerOn(){ //上电
		String device = "/dev/ttyS1";
		byte[] data = device.getBytes();
		int result = etc.PSAM_Core_InitLib(data, data.length, 9600);
		System.out.println("lib初始化结果：" + result);
		int result2 = etc.PSAM_Core_ConnectCard(0x10);
	}

	public void powerOff(){ //下电
		int result = etc.PSAM_Core_UnConnectCard();
		etc.PSAM_Core_UnInitLib();
	}
}
