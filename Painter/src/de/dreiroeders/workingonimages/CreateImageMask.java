package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CreateImageMask {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String strName = "";
			final String strExt = ".jpg";
			final String strPath = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Camera\\20191023_145713";
			File inFile = new File(strPath + strName + strExt);
			BufferedImage maskImg = createMaskFromRed(inFile);
			strName = "\\0";
			File outFile = new File(strPath + strName +" mask1" + strExt);
			ImageIO.write(maskImg, "jpg", outFile);
			BuffrdImgGetPixelD biGP1 = new BuffrdImgGetPixelD(maskImg);
			BuffrdImgSetPixelD biSPD = new BuffrdImgSetPixelD();
			BuffrdImgSetPixelD biSPB = new BuffrdImgSetPixelD();
			MiRoesDraw.showSinglePixels(biGP1, biSPD, biSPB, 135, 136);
			biSPD.write(new File(strPath + strName +" mask10" + strExt));
			biSPB.write(new File(strPath + strName +" mask12" + strExt));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void createMaskFromLightness() throws IOException {
		final String strName = "Fussball WM Ru";
		final String strExt = ".jpg";
		File inFile = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\res\\"+ strName + strExt);
		BufferedImage inImage = ImageIO.read(inFile);
		BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
												   BufferedImage.TYPE_USHORT_565_RGB );
		Graphics2D mPainter = outImage.createGraphics();
		mPainter.setColor(Color.WHITE);
		mPainter.drawRect(0, 0, inImage.getWidth()-1, inImage.getHeight()-1);
		mPainter.dispose();
		mPainter = null;

		for (int ym = 1; ym < inImage.getHeight()-1; ++ym) {
			int x0 = 0;
			for (int xm = 1; xm < inImage.getWidth()-1; ++xm) {
				int srcPixel = inImage.getRGB(xm, ym);
				boolean isBright = getBlue(srcPixel) + getGreen(srcPixel) + getRed(srcPixel) > 0x280;
				if (isBright) {
					if (x0 == 0 || xm >= inImage.getWidth()-x0) {
						srcPixel = 0xFFFFFF;
					} else {
						srcPixel = 0x808080;
					}
				} else {
					if (x0 == 0) {
						x0 = xm;
					}
					srcPixel = 0;
				}
				outImage.setRGB(xm, ym, srcPixel);
			}
		}
		File outFile = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\res\\"+ strName +" mask" + strExt);
		ImageIO.write(outImage, "jpg", outFile);			
	}
	
	public static BufferedImage createMaskFromAlphas(File inFile) throws IOException {
		BufferedImage inImage = ImageIO.read(inFile);
		BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
												   BufferedImage.TYPE_BYTE_GRAY );
		Graphics2D mPainter = outImage.createGraphics();
		mPainter.setColor(Color.WHITE);
		mPainter.drawRect(0, 0, inImage.getWidth()-1, inImage.getHeight()-1);
		mPainter.dispose();
		mPainter = null;

		for (int ym = 1; ym < inImage.getHeight()-1; ++ym) {
			for (int xm = 1; xm < inImage.getWidth()-1; ++xm) {
				int srcPixel = inImage.getRGB(xm, ym);
				int nTransparent = 255-getAlpha(srcPixel);
				srcPixel = (nTransparent << 16) | (nTransparent << 8) | nTransparent;
				outImage.setRGB(xm, ym, srcPixel);
			}
		}
		return outImage;
	}
	
	public static BufferedImage createMaskFromRed(File inFile) throws IOException {
		BufferedImage inImage = ImageIO.read(inFile);
		BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
												   BufferedImage.TYPE_BYTE_GRAY );
		Graphics2D mPainter = outImage.createGraphics();
		mPainter.setColor(Color.GRAY);
		mPainter.drawRect(0, 0, inImage.getWidth()-1, inImage.getHeight()-1);
		mPainter.dispose();
		mPainter = null;

		for (int ym = 0; ym < inImage.getHeight(); ++ym) {
			for (int xm = 0; xm < inImage.getWidth(); ++xm) {
				int srcPixel = inImage.getRGB(xm, ym);
				int trgPixel = 0xFFFFFF;
				if (getRed(srcPixel) > getBlue(srcPixel) && getRed(srcPixel) > getGreen(srcPixel)) {
					trgPixel = 0x000000;
				}
				outImage.setRGB(xm, ym, trgPixel);
			}
		}
		return outImage;
	}
	
	
	public static int getAlpha(int srcPixel) {
		return (srcPixel >> 24) & 0xFF;
	}
	
	public static int getRed(int srcPixel) {
		return MiRoesDraw.getC3(srcPixel);
	}
	
	public static int getGreen(int srcPixel) {
		return MiRoesDraw.getC2(srcPixel);
	}
	
	public static int getBlue(int srcPixel) {
		return MiRoesDraw.getC1(srcPixel);
	}
	
}
