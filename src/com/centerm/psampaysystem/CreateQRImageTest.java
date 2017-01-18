package com.centerm.psampaysystem;

import java.util.Hashtable;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class CreateQRImageTest
{
	private ImageView sweepIV;
	private int QR_WIDTH = 250, QR_HEIGHT = 250;

	private static CreateQRImageTest instance;

	private CreateQRImageTest(){
	}

	public static CreateQRImageTest getInstance(){
		if(instance == null){
			instance = new CreateQRImageTest();
		}
		return instance;
	}

	public void setImageView(ImageView iv){
		this.sweepIV = iv;
	}

	/**
	 * @��������˵��: ���ɶ�ά��ͼƬ,ʵ��ʹ��ʱҪ��ʼ��sweepIV,��Ȼ�ᱨ��ָ�����
	 * @����:������
	 * @ʱ��:2013-4-18����11:14:16
	 * @����: @param url Ҫת���ĵ�ַ���ַ���,����������
	 * @return void
	 * @throws
	 */

	//Ҫת���ĵ�ַ���ַ���,����������
	public void createQRImage(String url)
	{
		try
		{
			//�ж�URL�Ϸ���
			if (url == null || "".equals(url) || url.length() < 1)
			{
				return;
			}
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//ͼ������ת����ʹ���˾���ת��
			BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			//			bitMatrix = deleteWhite(bitMatrix);//ɾ���ױ�
			//			QR_WIDTH = bitMatrix.getWidth();
			//			QR_HEIGHT = bitMatrix.getHeight();

			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			//�������ﰴ�ն�ά����㷨��������ɶ�ά���ͼƬ��
			//����forѭ����ͼƬ����ɨ��Ľ��
			for (int y = 0; y < QR_HEIGHT; y++)
			{
				for (int x = 0; x < QR_WIDTH; x++)
				{
					if (bitMatrix.get(x, y))
					{
						pixels[y * QR_WIDTH + x] = 0xff000000;
					}
					else
					{
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			//���ɶ�ά��ͼƬ�ĸ�ʽ��ʹ��ARGB_8888
			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);

			int width = bitmap.getWidth();
			int height = bitmap.getHeight();

			Log.i(CreateQRImageTest.class.getSimpleName(), "width, height:"+width+","+height);

			//��ʾ��һ��ImageView����
			sweepIV.setImageBitmap(bitmap);
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
	}

	private BitMatrix deleteWhite(BitMatrix matrix) {
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2];
		int resHeight = rec[3];

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}
}
