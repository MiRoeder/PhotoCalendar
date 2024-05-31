package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class BuffrdImgGetPixelD implements IGetPixImg {

	protected int mX0;
	protected int mY0;
	protected int mWidth;
	protected int mHeight;
	
	protected BufferedImage mImage;
	
	protected BuffrdImgGetPixelD() {		
	}
	
	public BuffrdImgGetPixelD(String strImageFileName) {
		File imageFileName = new File(strImageFileName);
		init(imageFileName);
	}
	
	public BuffrdImgGetPixelD(File imageFileName) {
		init(imageFileName);
	}
	
	protected void init(File imageFileName) {
		try {
			this.mX0 = 0;
			this.mY0 = 0;
			this.mImage = ImageIO.read(imageFileName);
			this.mWidth = this.mImage.getWidth();
			this.mHeight= this.mImage.getHeight();
		} catch (Exception ex) {
			System.out.flush();
			System.err.println();
			System.err.println("Problem with "+ imageFileName.getAbsolutePath());
			ex.printStackTrace();
			this.mImage = null;
			this.mWidth = 0;
			this.mHeight= 0;
		}
	}
	
	public BuffrdImgGetPixelD(BufferedImage image) {
		this.mX0 = 0;
		this.mY0 = 0;
		this.mImage = image;
		this.mWidth = image.getWidth();
		this.mHeight= image.getHeight();
	}

	public int getWidth() {
		return mWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public BufferedImage getImage() {
		return mImage;
	}

	@Override
	public float[] getPixel(int x1, int y1, float[] result) {
		float[] res = result;
		if (res == null || res.length < 3) {
			res = new float[3];
		}
		int x2 = x1 - mX0;
		int y2 = y1 - mY0;
		if (0 <= x2 && x2 < mWidth && 0 <= y2 && y2 < mHeight) {
			int iRgb = mImage.getRGB(x2, y2);
			for (int iCol = 0; iCol < 3; ++iCol) {
				int i1Col = MiRoesDraw.getCi(iRgb, iCol);
				res[iCol] = (i1Col-127.5f)/127.5f;
			}
		} else {
			for (int iCol = 0; iCol < 3; ++iCol) {
				res[iCol] = 0f;
			}
		}
		return res;
	}

	public float getBright(int x1, int y1, float facRed, float facGreen, float facBlue) {
		int x2 = x1 - mX0;
		int y2 = y1 - mY0;
		float res;
		if (0 <= x2 && x2 < mWidth && 0 <= y2 && y2 < mHeight) {
			int iRgb = mImage.getRGB(x2, y2);
			res = MiRoesDraw.getC3(iRgb)*facRed + MiRoesDraw.getC2(iRgb)*facGreen + MiRoesDraw.getC1(iRgb)*facBlue;
			res = res/(facRed+facGreen+facBlue);
			res = (res-127.5f)/127.5f;
		} else {
			res = 0f;
		}
		return res;
	}

	@Override
	public float getAlpha(int x1, int y1) {
		int x2 = x1 - mX0;
		int y2 = y1 - mY0;
		if (0 <= x2 && x2 < mWidth && 0 <= y2 && y2 < mHeight) {
			int iAlp = mImage.getRGB(x2, y2) >> 24;
			return (iAlp & 0xFF)/255f;
		} else {
			return 0f;
		}
	}


}
