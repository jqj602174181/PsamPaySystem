package com.centerm.psampaysystem.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;

import com.centerm.iccard.ETC;

//压力测试类 by jqj
public class PressureTest {

	ETC etc = ETC.getInstance();
	private Context mContext = null;

	public Timer mTimer = new Timer(); //定时器

	public PressureTest(Context context) {
		mContext = context;
		reistAllDevDetect();
	}

	public void reistAllDevDetect() {
		IDetectBase[] detectArray = {
				new IDetectBaseTest2(),
				new IDetectBaseTest3(),};
		// 注册
		for (int i = 0; i < detectArray.length; i++) {
			registDev(detectArray[i]);
		}
		mItr = mDevDetArray.iterator();
	}

	// 注册单个设备检测
	private void registDev(IDetectBase detect) {
		detect.setContext(mContext);
		mDevDetArray.add(detect);
	}

	private ArrayList<IDetectBase> mDevDetArray = new ArrayList<IDetectBase>();//检测设备信息array
	Iterator<IDetectBase> mItr; //单项

	public void doNextDev()
	{
		IDetectBase detect;
		if ( mItr.hasNext() )
		{
			detect = mItr.next();
			detect.powerOn();
			detect.detect();
			detect.powerOff();
			SystemClock.sleep(50);
		}
		else
		{
			//从头开始检测
			mItr = mDevDetArray.iterator();
			if ( mItr.hasNext() )
			{
				detect = mItr.next();
				detect.powerOn();
				detect.detect();
				detect.powerOff();
				SystemClock.sleep(50);
			}
		}
	}

	private TimerTask mtimeTask = new TimerTask() {
		@Override
		public void run() {
			doNextDev();
		}
	};

	public void stopTest(){
		mTimer.cancel();//取消
	}

	public void startTest(){
		mTimer.schedule(mtimeTask, 0, 1000);//间隔1S重复执行
	}
}
