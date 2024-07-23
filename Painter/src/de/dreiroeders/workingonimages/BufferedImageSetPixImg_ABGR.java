package de.dreiroeders.workingonimages;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeIoUtil;

public class BufferedImageSetPixImg_ABGR extends BufferedImageGetPixImg implements ISetPixImg {

	private float mMaxColorValue;
	private BufferedImage mImage2 = null;
	
	public BufferedImageSetPixImg_ABGR(int width, int height, float maxColorValue) {
		mImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		mMaxColorValue = maxColorValue;
		if (maxColorValue >= 255.5f/255f) {
			mImage2 = new BufferedImage(mImage.getWidth(), mImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		}
	}

	public BufferedImageSetPixImg_ABGR(int width, int height, IGetPixImg img1, float img1Fac, IGetPixImg img2, float img2Fac) {
		super(null);
		mImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		mMaxColorValue = Math.abs(img1Fac) + Math.abs(img2Fac);
		if (mMaxColorValue >= 255.5f/255f) {
			mImage2 = new BufferedImage(mImage.getWidth(), mImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		}
		float[] col1 = new float[3];
		float[] col2 = new float[3];
		float[] colO = new float[3];
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				float al1 = img1.getAlpha(x, y);
				float al2 = img2.getAlpha(x, y);
				col1 = img1.getPixel(x, y, col1);
				col2 = img2.getPixel(x, y, col2);
				for (int iC = 0; iC < 3; ++iC) {
					colO[iC] = (col1[iC]*al1*img1Fac) + (col2[iC]*al2*img2Fac);
				}
				setAlpha(x, y, al1+al2);
				setPixel(x, y, colO);
			}
		}
	}

	public BufferedImageSetPixImg_ABGR(String strFileName) throws IOException {
		this(new SourceImage(strFileName));
	}
	
	public BufferedImageSetPixImg_ABGR(File strFile) throws IOException {
		this(ImageIO.read(strFile), 1f);
	}
	
	public BufferedImageSetPixImg_ABGR(SourceImage file) throws IOException {
		if (file.isOk()) {
			mImage = file.getImage();
			init(1f, true);
		} else {
			mImage = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
			mImage.setRGB(0, 0, 0);
		}
	}
	
	public BufferedImageSetPixImg_ABGR(BufferedImage image, float maxColorValue) {
		super(image);
		init(maxColorValue, false);
	}
	
	protected void init(float maxColorValue, boolean bCopyImg) {
		int iTyp = mImage.getType();
		if (bCopyImg || iTyp != BufferedImage.TYPE_4BYTE_ABGR && iTyp != BufferedImage.TYPE_4BYTE_ABGR_PRE) {
			BufferedImage oldImage = mImage;
			mImage = new BufferedImage(mImage.getWidth(), mImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			Graphics painter = mImage.getGraphics();
			painter.drawImage(oldImage, 0, 0, new MiRoesImageObserver());
			painter.dispose();
		}
		mMaxColorValue = maxColorValue;
		if (maxColorValue >= 255.5f/255f) {
			mImage2 = new BufferedImage(mImage.getWidth(), mImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		}
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
	public void setPixel(int x, int y, float[] rgb) {
		int rgb1 = 0xFF;
		int rgb2 = 0xFF;
		for (int iCol = 2; iCol >= 0; --iCol) {
			int i1Col = (int)((rgb[iCol]+1f)*(255f/2f)+0.5f);
			if (Math.abs(rgb[iCol]) > 1f && mImage2 == null) {
				System.out.println("BufferedImageSetPixImg_ABGR: x="+ x +", y="+ y +", iCol="+ iCol +" : col="+ rgb[iCol] +", i1Col="+ i1Col);
			}
			i1Col = v0_v255(i1Col);
			rgb1 = (rgb1 << 8) + i1Col;
			if (mImage2 != null) {
				i1Col = (int)((rgb[iCol]/mMaxColorValue+1f)*(255f/2f)+0.5f);
				if (i1Col < 0 || 255 < i1Col) {
					System.out.println("BufferedImageSetPixImg_ABGR: x="+ x +", y="+ y +", iCol="+ iCol +" : col="+ rgb[iCol] +", i1Col="+ i1Col);
				}
				i1Col = v0_v255(i1Col);
				rgb2 = (rgb2 << 8) + i1Col;
			}
		}
		mImage.setRGB(x, y, rgb1);
		if (mImage2 != null) {
			mImage2.setRGB(x, y, rgb2);
		}
	}

	@Override
	public void setPixel(int x, int y, float alpha, float[] rgb) {
		int rgb1 = v0_v255(alpha*255f);
		int rgb2 = rgb1;
		for (int iCol = 2; iCol >= 0; --iCol) {
			int i1Col = (int)((rgb[iCol]+1f)*(255f/2f)+0.5f);
			if (Math.abs(rgb[iCol]) > 1f && mImage2 == null) {
				System.out.println("Problem in BufferedImageSetPixImg_ABGR: x="+ x +", y="+ y +", iCol="+ iCol +" : col="+ rgb[iCol] +", i1Col="+ i1Col);
			}
			i1Col = v0_v255(i1Col);
			rgb1 = (rgb1 << 8) + i1Col;
			if (mImage2 != null) {
				i1Col = (int)((rgb[iCol]/mMaxColorValue+1f)*(255f/2f)+0.5f);
				if (i1Col < 0 || 255 < i1Col) {
					System.out.println("Problem in BufferedImageSetPixImg_ABGR: x="+ x +", y="+ y +", iCol="+ iCol +" : col="+ rgb[iCol] +", i1Col="+ i1Col);
					i1Col = v0_v255(i1Col);
				}
				rgb2 = (rgb2 << 8) + i1Col;
			}
		}
		mImage.setRGB(x, y, rgb1);
		if (mImage2 != null) {
			mImage2.setRGB(x, y, rgb2);
		}
	}

	@Override
	public void setAlpha(int x, int y, float alpha) {
		int iAlpha = v0_v255(alpha*255.5f) << 24;
		int iRgb = mImage.getRGB(x, y);
		iRgb = (iAlpha & 0xFF000000) | (iRgb & 0x00FFFFFF);
		mImage.setRGB(x, y, iRgb);
		if (mImage2 != null) {
			iRgb = mImage2.getRGB(x, y);
			iRgb = (iAlpha & 0xFF000000) | (iRgb & 0x00FFFFFF);
			mImage2.setRGB(x, y, iRgb);
		}
	}

	public BufferedImage getImage(int iI) {
		if (iI == 0) return mImage;
		if (iI == 1) return mImage2;
		return null;
	}
	
	public void write(String strFilesOutput) {
		File fil1 = null;
		File fil2 = null;
		String strFileExt = null;
		try {
			strFileExt = MiRoeIoUtil.getFileExt(strFilesOutput);
			if (strFileExt == null || strFileExt.length() < 1) {
				strFileExt = "png";
			}
			if (strFilesOutput != null) {
				if (strFilesOutput.contains("{0")) {
					fil1 = new File(MessageFormat.format(strFilesOutput, Integer.valueOf(1)));
					fil2 = new File(MessageFormat.format(strFilesOutput, Integer.valueOf(2)));
				} else {
					if (mImage2 != null) {
						fil2 = new File(strFilesOutput);
					} else {
						fil1 = new File(strFilesOutput);
					}
				}
			} else {
				if (mImage2 != null) {
					fil1 = File.createTempFile("BufferedImageSetPixImg_ABGR", ".1.png");
					fil2 = File.createTempFile("BufferedImageSetPixImg_ABGR", ".2.png");
				} else {
					fil1 = File.createTempFile("BufferedImageSetPixImg_ABGR", ".png");
				}
			}
			if (fil1 != null) {
				ImageIO.write(mImage, strFileExt, fil1);
			}
			if (fil2 != null && mImage2 != null) {
				ImageIO.write(mImage2, strFileExt, fil2);
			}
		} catch (IOException ex) {
			System.out.println();
			System.out.flush();
			System.err.println("Problem with "+ strFileExt +" "+ (fil1 != null ? fil1.getAbsolutePath() : "file1") +" or "+ (fil2 != null ? fil2.getAbsolutePath() : "file2"));
			ex.printStackTrace();
		}
	}

}
