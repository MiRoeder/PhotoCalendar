package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;


public class BufferedImageGetPixImg implements IGetPixImg {

	protected BufferedImage mImage;
	
	public BufferedImageGetPixImg() {
		this.mImage = null;
	}
	
	public BufferedImageGetPixImg(BufferedImage image) {
		this.mImage = image;
	}
	
	@Override
	public float[] getPixel(int x, int y, float[] result) {
		float[] res = result;
		if (res == null || res.length < 3) {
			res = new float[3];
		}
		int iRgb = mImage.getRGB(x, y);
		for (int iCol = 0; iCol < 3; ++iCol) {
			int i1Col = MiRoesDraw.getCi(iRgb, iCol);
			res[iCol] = (i1Col-127.5f)/127.5f;
		}
		return res;
	}

	public int getPixel(int x, int y) {
		return  mImage.getRGB(x, y);
	}

	public float getBright(int x, int y, float facRed, float facGreen, float facBlue) {
		int iRgb = mImage.getRGB(x, y);
		float res;
		res = MiRoesDraw.getC3(iRgb)*facRed + MiRoesDraw.getC2(iRgb)*facGreen + MiRoesDraw.getC1(iRgb)*facBlue;
		res = res/(facRed+facGreen+facBlue);
		res = (res-127.5f)/127.5f;
		return res;
	}

	@Override
	public float getAlpha(int x, int y) {
		int iAlp = mImage.getRGB(x, y) >> 24;
		return (iAlp & 0xFF)/255f;
	}

	public int getWidth() {
		return mImage.getWidth();
	}

	public int getHeight() {
		return mImage.getHeight();
	}

}
