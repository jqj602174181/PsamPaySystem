package com.centerm.psampaysystem.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.centerm.iccard.ETC;

public class IDetectBaseTest4 extends IDetectBase {
	
	private static String TAG = IDetectBaseTest4.class.getSimpleName();

	ETC etc = ETC.getInstance();
	
	int allCount = 0;
	int errCount = 0;
	
	@Override
	public int detect() {
		allCount++;
		
		double amount = Double.parseDouble("0.01");
		Log.i(TAG, "amount:" + amount);
		//获取加密数据扩展
		byte[] data = new byte[1024];
		int result4 = etc.PSAM_Core_GetQRCodeEx(amount, data);
		
		if(result4 < 0){ //失败
			errCount++;
		}
		SharedPreferences share = mContext.getSharedPreferences("pressureTest", Activity.MODE_PRIVATE);
		share.edit().putString("test4", errCount +"/"+allCount).commit();
		return result4;
	}
}
