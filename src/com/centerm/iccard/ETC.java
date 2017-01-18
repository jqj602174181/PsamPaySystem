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

	//��ʼ���豸
	public native int PSAM_Core_InitLib(byte[] device, int length, int nBaud);

	//ע���豸
	public native int PSAM_Core_UnInitLib();

	//��ȡ������
	public native void PSAM_Core_GetLastError(byte[] errMsg);

	//�����豸
	public native int PSAM_Core_ConnectCard(int nIcFlag);

	//�Ͽ��豸����
	public native int PSAM_Core_UnConnectCard();

	//��ȡ�豸���к�
	public native int PSAM_Core_GetSN(byte[] sn);

	//��ȡ�豸UUID
	public native int PSAM_Core_GetUUID(byte[] uuid);

	//��ȡ����������չ
	public native int PSAM_Core_GetQRCodeEx(double amount, byte[] dataOut);

	//��ȡ��������
	public native int PSAM_Core_GetQRCode(double amount, int transNum, byte[] dataOut);

	//��֤PIN��
	public native int PSAM_Core_VerifyQRCode(byte[] PinData);

	//���ָ��PIN��
	public native int PSAM_Core_CancleQRCode(int Index);

	//����URL�ַ���
	public native int PSAM_Core_GetTransQueryURL(byte[] DataOut);

	//����URL�ַ���
	public native int PSAM_Core_GetCashAssistURL(byte[] DataOut);

	//��ȡ�汾��Ϣ
	public native int PSAM_Core_GetVersion(byte[] version);
}
