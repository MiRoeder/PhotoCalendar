package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeIoUtil;

public class BuffrdImgSetPixelD extends BuffrdImgGetPixelD implements ISetPixImg {

	public BuffrdImgSetPixelD() {
		super();
	}
	
	public BuffrdImgSetPixelD(String strImageFileName) {
		File imageFileName = new File(strImageFileName);
		init(imageFileName);
	}
	
	public BuffrdImgSetPixelD(BufferedImage image) {
		super(image);
	}

	public BuffrdImgSetPixelD(int width, int height, int imageType) {
		super();
		init(width, height, imageType);
	}
	
	public void init(int width, int height, int imageType) {
		BufferedImage image = new BufferedImage(width, height, imageType);
		this.mX0 = 0;
		this.mY0 = 0;
		this.mImage = image;
		this.mWidth = image.getWidth();
		this.mHeight= image.getHeight();
	}
	
	public static int v0_v255(int color1Val) {
		return Math.max(0, Math.min(color1Val, 255));
	}
	
	public static int v0_v255(float color1Val) {
		return Math.max(0, Math.min((int)(color1Val+0.5f), 255));
	}
	
	public static int v0_v255(double color1Val) {
		return (int)(Math.max(0, Math.min(color1Val, 255))+0.5);
	}
	
	@Override
	public void setPixel(int x1, int y1, float[] rgb) {
		int rgb1 = 0xFF;
		int x2 = x1 - mX0;
		int y2 = y1 - mY0;
		if (0 <= x2 && x2 < mWidth && 0 <= y2 && y2 < mHeight) {
			for (int iCol = 2; iCol >= 0; --iCol) {
				int i1Col = (int)((rgb[iCol]+1f)*(255f/2f)+0.5f);
				if (Math.abs(rgb[iCol]) > 1.0f) {
					System.out.println("Problem in BuffrdImgSetPixelD: x="+ x1 +", y="+ y1 +", iCol="+ iCol +" : col="+ rgb[iCol] +", i1Col="+ i1Col);
				}
				i1Col = v0_v255(i1Col);
				rgb1 = (rgb1 << 8) + i1Col;
			}
			mImage.setRGB(x2, y2, rgb1);
		} else {
			System.out.println("Problem in BuffrdImgSetPixelD: x="+ x1 +", y="+ y1);
		}
	}

	@Override
	public void setPixel(int x1, int y1, float alpha, float[] rgb) {
		int x2 = x1 - mX0;
		int y2 = y1 - mY0;
		if (0 <= x2 && x2 < mWidth && 0 <= y2 && y2 < mHeight) {
			int rgb1 = v0_v255(alpha*255f);
			for (int iCol = 2; iCol >= 0; --iCol) {
				int i1Col = (int)((rgb[iCol]+1f)*(255f/2f)+0.5f);
				if (Math.abs(rgb[iCol]) > 1.99f) {
					System.out.println("Problem in BuffrdImgSetPixelD: x="+ x2 +", y="+ y2 +", iCol="+ iCol +" : col="+ rgb[iCol] +", i1Col="+ i1Col);
				}
				i1Col = v0_v255(i1Col);
				rgb1 = (rgb1 << 8) + i1Col;
			}
			mImage.setRGB(x2, y2, rgb1);
		} else {
			System.out.println("Problem in BuffrdImgSetPixelD: x="+ x1 +", y="+ y1);
		}
	}

	@Override
	public void setAlpha(int x1, int y1, float alpha) {
		int x2 = x1 - mX0;
		int y2 = y1 - mY0;
		if (0 <= x2 && x2 < mWidth && 0 <= x2 && x2 < mHeight) {
			int iAlpha = v0_v255(alpha*255f) << 24;
			int iRgb = mImage.getRGB(x2, y2);
			iRgb = (iAlpha & 0xFF000000) | (iRgb & 0x00FFFFFF);
			mImage.setRGB(x2, y2, iRgb);
		} else {
			System.out.println("Problem in BuffrdImgSetPixelD: x="+ x1 +", y="+ y1);
		}
	}

	public void write(File filOutput) {
		try {
			ImageIO.write(mImage, MiRoeIoUtil.getFileExt(filOutput), filOutput);
		} catch (IOException ex) {
			System.out.println();
			System.out.flush();
			if (filOutput != null) {
				System.err.println("Problem with "+ filOutput.getAbsolutePath());
			} else {
				System.err.println("no File !");
			}
			ex.printStackTrace();
		}
	}
}
