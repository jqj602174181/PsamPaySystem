package com.centerm.psampaysystem.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.centerm.iccard.ETC;

public class IDetectBaseTest3 extends IDetectBase {

	private static String TAG = IDetectBaseTest3.class.getSimpleName();
	
	ETC etc = ETC.getInstance();
	
	int allCount = 0;
	int errCount = 0;
	
	@Override
	public int detect() {
		allCount++;
		byte[] version = new byte[1024];
		int result3 = etc.PSAM_Core_GetVersion(version);
		
		if(result3 < 0){ //Ê§°Ü
			errCount++;
		}
		SharedPreferences share = mContext.getSharedPreferences("pressureTest", Activity.MODE_PRIVATE);
		share.edit().putString("test3", errCount +"/"+allCount).commit();
		return result3;
	}
}
