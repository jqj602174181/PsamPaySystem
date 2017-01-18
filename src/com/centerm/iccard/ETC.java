package com.centerm.iccard;

public class ETC {

	private static ETC instance;

	private ETC(){

	}

	public static ETC getInstance(){
		if(instance == null){
			instance = new ETC();
		}
		return instance;
	}

	static{
		System.loadLibrary("PsamCard");
	}

	//初始化设备
	public native int PSAM_Core_InitLib(byte[] device, int length, int nBaud);

	//注销设备
	public native int PSAM_Core_UnInitLib();

	//获取错误码
	public native void PSAM_Core_GetLastError(byte[] errMsg);

	//连接设备
	public native int PSAM_Core_ConnectCard(int nIcFlag);

	//断开设备连接
	public native int PSAM_Core_UnConnectCard();

	//获取设备序列号
	public native int PSAM_Core_GetSN(byte[] sn);

	//获取设备UUID
	public native int PSAM_Core_GetUUID(byte[] uuid);

	//获取加密数据扩展
	public native int PSAM_Core_GetQRCodeEx(double amount, byte[] dataOut);

	//获取加密数据
	public native int PSAM_Core_GetQRCode(double amount, int transNum, byte[] dataOut);

	//验证PIN码
	public native int PSAM_Core_VerifyQRCode(byte[] PinData);

	//清除指定PIN码
	public native int PSAM_Core_CancleQRCode(int Index);

	//产生URL字符串
	public native int PSAM_Core_GetTransQueryURL(byte[] DataOut);

	//产生URL字符串
	public native int PSAM_Core_GetCashAssistURL(byte[] DataOut);

	//获取版本信息
	public native int PSAM_Core_GetVersion(byte[] version);
}
