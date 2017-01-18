package com.centerm.psampaysystem.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.centerm.iccard.ETC;

public class IDetectBaseTest1 extends IDetectBase {
	
	private static String TAG = IDetectBaseTest1.class.getSimpleName();
	
	ETC etc = ETC.getInstance();
	
	int allCount = 0;
	int errCount = 0;
	
	@Override
	public int detect() {
		allCount++;
		byte[] uuid = new byte[1024];
		int result1 = etc.PSAM_Core_GetUUID(uuid);
		
		if(result1 < 0){ //Ê§°Ü
			errCount++;
		}
		SharedPreferences share = mContext.getSharedPreferences("pressureTest", Activity.MODE_PRIVATE);
		share.edit().putString("test1", errCount +"/"+allCount).commit();
		
		return result1;
	}
}
