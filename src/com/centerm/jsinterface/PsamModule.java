package com.centerm.jsinterface;

import org.json.JSONObject;

import com.centerm.iccard.ETC;
import com.centerm.psampaysystem.MainActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/03/23.
 */
public class PsamModule extends ReactContextBaseJavaModule {

	private static final String TAG = PsamModule.class.getSimpleName();
	private static final String URL = "http://a.counect.com/p/c.html?data="; //支付网址前缀
	ETC etc = ETC.getInstance();

	private static final String COMMIT_SUC = "0";					// 提交成功
	private static final String COMMIT_ERR = "-1";					// 提交失败

	private Callback commitCallback;  	//提交结果回调方法
	private String commitTime = "";   	//提交时间
	private String formNo = "";			//预填单号
	private String errMsg = "";			//预填单号

	public PsamModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return "PsamModule";
	}

	@ReactMethod
	public void getQrUrl(String amount, Callback commitCallback) { //获取支付网址  amount金钱数额,该接口有大概0.1失败率
		this.commitCallback = commitCallback;
		final String mAmount = amount;
		new Thread(){
			public void run() {
				//获取金额
				try{
					double amount = Double.parseDouble(mAmount);
					Log.i(TAG, "amount:" + amount);
					//获取加密数据扩展
					powerOn();
					byte[] data = new byte[1024];
					int result = etc.PSAM_Core_GetQRCodeEx(amount, data);
					powerOff();
					Log.i(TAG, "PSAM_Core_GetQRCodeEx result:" + result);

					if(result < 0){ //生成失败
						backCommitResult(COMMIT_ERR, null);
						return;
					}

					byte[] tempData = new byte[72];
					System.arraycopy(data, 0, tempData, 0, 72);

					//base64
					String strBase64 = new String(Base64.encode(tempData, Base64.DEFAULT));
					strBase64 = strBase64.replace("\n", "");
					//urlencode
					String tempUrl = URLEncode(strBase64);
					final String url  = URL + tempUrl;
					System.out.println("支付url:" + url);
					backCommitResult(COMMIT_SUC, url);
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	public void VerifyResult(String code, Callback commitCallback){
		final String mCode = code;
		new Thread(){
			public void run() {
				powerOn();
				int result = etc.PSAM_Core_VerifyQRCode(mCode.getBytes());
				powerOff();
				if(result == 0){ //成功
					backCommitResult(COMMIT_SUC, null);
				} else {
					backCommitResult(COMMIT_ERR, null);
				}
			};
		}.start();
	}

	/**
	 * 返回提交结果
	 * @param resultCode 结果码   0：读取成功	-1：读取失败
	 */
	private void backCommitResult(String resultCode, String result){
		WritableMap map = Arguments.createMap();
		map.putString("resultCode", result);
		map.putString("result", result);
		commitCallback.invoke(map);
	}

	private void powerOn(){ //上电
		String device = "/dev/ttyS1";
		byte[] data = device.getBytes();
		int result = etc.PSAM_Core_InitLib(data, data.length, 9600);
		System.out.println("lib初始化结果：" + result);
		int result2 = etc.PSAM_Core_ConnectCard(0x10);
		Log.i(TAG, "card slot power on：" + result2);
	}

	private void powerOff(){ //下电
		int result = etc.PSAM_Core_UnConnectCard();
		etc.PSAM_Core_UnInitLib();
		Log.i(TAG, "card slot power off：" + result);
	}

	String URLEncode(String strUrl)
	{
		String strDes = "";
		for (int nIndex = 0; nIndex < strUrl.length(); nIndex++)
		{
			if (strUrl.charAt(nIndex) == '+')
			{
				strDes += "%2B";
			}
			else if(strUrl.charAt(nIndex) == '/')
			{
				strDes += "%2F";
			}
			else if(strUrl.charAt(nIndex) == '=')
			{
				strDes += "%3D";
			}
			else
			{
				strDes += strUrl.charAt(nIndex);
			}
		}
		return strDes;
	}
}

