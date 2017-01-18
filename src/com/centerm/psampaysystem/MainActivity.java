package com.centerm.psampaysystem;

import com.centerm.iccard.ETC;
import com.centerm.psampaysystem.R;
import com.centerm.psampaysystem.test.PressureTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/*
 * @author by jqj
 * @data 2016.7.27
 */

public class MainActivity extends Activity implements View.OnClickListener{

	private Button oneType;
	private Button twoType;
	private Button threeType;
	private Button powerOn;

	private Button one;
	private Button two;
	private Button three;
	private Button four;
	private Button five;
	private Button six;
	private Button seven;
	private Button eight;
	private Button nine;
	private Button zero;
	private Button clean;
	private Button ok;
	private Button makeQR;

	private EditText okCodeEdit;
	private EditText coreID;
	private EditText coreSN;
	private EditText coreVer;
	private EditText amountEdit;

	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final String FOUR = "4";
	private static final String FIVE = "5";
	private static final String SIX = "6";
	private static final String SEVEN = "7";
	private static final String EIGHT = "8";
	private static final String NINE = "9";
	private static final String ZERO = "0";
	private static final String CLEAN = "";

	private ImageView img;

	ETC etc;
	PressureTest pressureTest;


	byte[] uuid = new byte[1024];
	byte[] sn = new byte[1024];
	byte[] version = new byte[1024];

	private static final String TAG = MainActivity.class.getSimpleName();
	//	private static final String URL = "http://b.counect.com/dsm/"; //支付网址前缀
	private static final String URL = "http://a.counect.com/p/c.html?data="; //支付网址前缀

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 1){
				coreID.setText(new String(uuid).trim());
				coreSN.setText(new String(sn).trim());
				coreVer.setText(new String(version).trim());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.main);

		etc = ETC.getInstance();

		initViews();

		//						pressureTest = new PressureTest(MainActivity.this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		//获取信息
		//		getBaseInfo();

		//						pressureTest.startTest();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		//		pressureTest.stopTest();
	}

	private void initViews() {
		okCodeEdit = getView(R.id.okCodeEdit);
		coreID = getView(R.id.coreID);
		coreSN = getView(R.id.coreSN);
		coreVer = getView(R.id.coreVer);
		amountEdit = getView(R.id.amount);
		amountEdit.addTextChangedListener(new LimitMoneyTextWatcher(2, amountEdit));

		img = getView(R.id.img);

		oneType = getView(R.id.oneType);
		twoType = getView(R.id.twoType);
		threeType = getView(R.id.threeType);
		powerOn = getView(R.id.powerOn);

		one = getView(R.id.one);
		two = getView(R.id.two);
		three = getView(R.id.three);
		four = getView(R.id.four);
		five = getView(R.id.five);
		six = getView(R.id.six);
		seven = getView(R.id.seven);
		eight = getView(R.id.eight);
		nine = getView(R.id.nine);
		zero = getView(R.id.zero);
		clean = getView(R.id.clean);
		ok = getView(R.id.ok);
		makeQR = getView(R.id.makeQR);

		oneType.setOnClickListener(this);
		twoType.setOnClickListener(this);
		threeType.setOnClickListener(this);
		powerOn.setOnClickListener(this);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		five.setOnClickListener(this);
		six.setOnClickListener(this);
		seven.setOnClickListener(this);
		eight.setOnClickListener(this);
		nine.setOnClickListener(this);
		zero.setOnClickListener(this);
		clean.setOnClickListener(this);
		ok.setOnClickListener(this);
		makeQR.setOnClickListener(this);
	}

	private void setQRImage(String url){
		CreateQRImageTest test = CreateQRImageTest.getInstance();
		test.setImageView(img);
		test.createQRImage(url);

		//		QRCodeUtil test = QRCodeUtil.getInstance();
		//		Drawable drawable = Drawable.createFromPath("/mnt/internal_sd/logo.png");
		//		BitmapDrawable bd = (BitmapDrawable) drawable;
		//		Bitmap logo = bd.getBitmap();
		//		Bitmap bm = test.createImage(url, 200, 200, logo);
		//		img.setImageBitmap(bm);
	}

	@Override
	public void onClick(View v) {
		String code;
		switch(v.getId()){
		case R.id.one:
			code = okCodeEdit.getText().toString();
			code = code+ONE;
			okCodeEdit.setText(code);
			break;
		case R.id.two:
			code = okCodeEdit.getText().toString();
			code = code+TWO;
			okCodeEdit.setText(code);
			break;
		case R.id.three:
			code = okCodeEdit.getText().toString();
			code = code+THREE;
			okCodeEdit.setText(code);
			break;
		case R.id.four:
			code = okCodeEdit.getText().toString();
			code = code+FOUR;
			okCodeEdit.setText(code);
			break;
		case R.id.five:
			code = okCodeEdit.getText().toString();
			code = code+FIVE;
			okCodeEdit.setText(code);
			break;
		case R.id.six:
			code = okCodeEdit.getText().toString();
			code = code+SIX;
			okCodeEdit.setText(code);
			break;
		case R.id.seven:
			code = okCodeEdit.getText().toString();
			code = code+SEVEN;
			okCodeEdit.setText(code);
			break;
		case R.id.eight:
			code = okCodeEdit.getText().toString();
			code = code+EIGHT;
			okCodeEdit.setText(code);
			break;
		case R.id.nine:
			code = okCodeEdit.getText().toString();
			code = code+NINE;
			okCodeEdit.setText(code);
			break;
		case R.id.zero:
			code = okCodeEdit.getText().toString();
			code = code+ZERO;
			okCodeEdit.setText(code);
			break;
		case R.id.clean:
			okCodeEdit.setText(CLEAN);
			break;
		case R.id.ok:
			doOk();
			break;
		case R.id.oneType:
			powerOn();
			byte[] uuid = new byte[1024];
			int result1 = etc.PSAM_Core_GetUUID(uuid);
			powerOff();
			coreID.setText(new String(uuid));
			System.out.println("获取到uuid的值：" + result1+","+new String(uuid));
			break;
		case R.id.twoType:
			powerOn();
			byte[] sn = new byte[1024];
			int result2 = etc.PSAM_Core_GetSN(sn);
			powerOff();
			coreSN.setText(new String(sn));
			System.out.println("获取到sn result：" + result2+","+new String(sn));
			break;
		case R.id.threeType:
			powerOn();
			byte[] version = new byte[1024];
			int result3 = etc.PSAM_Core_GetVersion(version);
			powerOff();
			coreVer.setText(new String(version));
			System.out.println("获取到version的值：" + result3+","+new String(version));
			break;
		case R.id.makeQR:
			makeQR();
			break;
		case R.id.powerOn:
			//						powerOn();
			break;
		}
	}

	private void doOk() {
		String code = okCodeEdit.getText().toString().trim(); //确认码
		powerOn();
		int result = etc.PSAM_Core_VerifyQRCode(code.getBytes());
		powerOff();
		if(result == 0){ //成功
			Toast.makeText(MainActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(MainActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
		}

		okCodeEdit.setText(CLEAN);
	}

	private void makeQR() {
		if(amountEdit.getText().toString().equals("")){
			Toast.makeText(MainActivity.this, "请输入充值金额", Toast.LENGTH_SHORT).show();
			return;
		}
		new Thread(){
			public void run() {
				//获取金额
				String mAmount = amountEdit.getText().toString().trim();
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
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(MainActivity.this, "生成二维码失败,请重新尝试", Toast.LENGTH_SHORT).show();
							}
						});
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
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							setQRImage(url);
						}
					}); 
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}.start();
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

	private void getBaseInfo(){
		new Thread(){
			public void run() {
				int result1 = 10; //正数值
				int result2 = 10;
				int result3 = 10;
				powerOn();
				result1 = etc.PSAM_Core_GetUUID(uuid);
				result2 = etc.PSAM_Core_GetSN(sn);
				result3 = etc.PSAM_Core_GetVersion(version);
				powerOff();
				Log.i(TAG, "getBaseInfo result:"+result1+","+result2+","+result3);
				if(result1 <= 0 && result2 <= 0 && result3 <= 0){
					handler.sendEmptyMessage(1);
				}
			};
		}.start();
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

	//可以减少许多次强制类型转换
	@SuppressWarnings("unchecked")
	private <T extends View> T getView(int resID) {
		return (T) findViewById(resID);
	}
}
