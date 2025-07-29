package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeIoUtil;


public class WorkingOnImagesApp {

	public static void main(String[] args) {
		try {
			extractByColor2();
			extractByColor1();
		} catch (Throwable ex) {
			MiRoeIoUtil.logException("", ex);
		}
	}

	public static void extractByColor1() {
		try {
			File inFile  = new File("C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik6/imgWithoutCrown.png");
			String outFile = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik6/imgWithout6Crown.png";
			String strFile6 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik6/img6_0C.png";
			String strFile9 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik6/img9_0C.png";
			final int iLeft = 450;
			final int iTop =380;
			final int iWidth = 567-iLeft;
			final int iHeight = 510-iTop;
			BufferedImageSetPixImg_ABGR img = new BufferedImageSetPixImg_ABGR(inFile);
			BufferedImageSetPixImg_ABGR img6 = new BufferedImageSetPixImg_ABGR(iWidth, iHeight, 1);
			BufferedImageSetPixImg_ABGR img9 = new BufferedImageSetPixImg_ABGR(iWidth, iHeight, 1);
			for (int iX= 0; iX<iWidth; ++iX)  {
				int iSX = iX+iLeft;
				for (int iY= 0; iY<iHeight; ++iY)  {
					int iSY = iY+iTop;
					int pix = img.getPixel(iSX, iSY);
					boolean bExt = toBeExtracted(pix);
					int iExtNeighbors =
							(toBeExtracted(img.getPixel(iSX-1, iSY)) ? 1 : 0)
						  + (toBeExtracted(img.getPixel(iSX, iSY-1)) ? 1 : 0)
  						  + (toBeExtracted(img.getPixel(iSX+1, iSY)) ? 1 : 0)
						  + (toBeExtracted(img.getPixel(iSX, iSY+1)) ? 1 : 0) ;
					if (iExtNeighbors >= 4 || bExt && iExtNeighbors >= 1) {
						if ((pix & 0xFF000000) != 0) {
							img.setAlpha(iSX, iSY, 0);
							img6.getImage(0).setRGB(iX, iY, pix);
							img9.getImage(0).setRGB(iWidth-1-iX, iHeight-1-iY, pix);
						}
					}
				} /* end for (iY...) */
			} /* end for (iX...) */
			img6.write(strFile6);
			img9.write(strFile9);
			img.write(outFile);
		} catch (Exception ex) {
			MiRoeIoUtil.logException("", ex);
		}
	}

	public static void extractByColor2() {
		try {
			File inFile  = new File("C:\\Users\\MiRoe\\Pictures\\WhatsApp\\WhatsApp Bild 2025-04-06 um 15.22.57_635f807d.jpg");
			String outFile = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik6/imgWithoutCrown.png";
			String strFileCrown = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik6/Crown.png";
			final int iLeft = 435;
			final int iTop =344;
			final int iWidth = 510-iLeft;
			final int iHeight = 400-iTop;
			BufferedImageSetPixImg_ABGR img = new BufferedImageSetPixImg_ABGR(inFile);
			BufferedImageSetPixImg_ABGR img6 = new BufferedImageSetPixImg_ABGR(iWidth, iHeight, 1);
			for (int iX= 0; iX<iWidth; ++iX)  {
				int iSX = iX+iLeft;
				for (int iY= 0; iY<iHeight; ++iY)  {
					int iSY = iY+iTop;
					int pix = img.getPixel(iSX, iSY);
					boolean bExt = toBeExtracted2(pix);
					int iExtNeighbors =
							(toBeExtracted2(img.getPixel(iSX-1, iSY)) ? 1 : 0)
						  + (toBeExtracted2(img.getPixel(iSX, iSY-1)) ? 1 : 0)
						  + (toBeExtracted2(img.getPixel(iSX+1, iSY)) ? 1 : 0)
						  + (toBeExtracted2(img.getPixel(iSX, iSY+1)) ? 1 : 0) ;
					if (iExtNeighbors >= 4 || bExt && iExtNeighbors >= 1) {
						if ((pix & 0xFF000000) != 0) {
							img.setAlpha(iSX, iSY, 0);
							img6.getImage(0).setRGB(iX, iY, pix);
						}
					}
				} /* end for (iY...) */
			} /* end for (iX...) */
			img6.write(strFileCrown);
			img.write(outFile);
		} catch (Exception ex) {
			MiRoeIoUtil.logException("", ex);
		}
	}

	public static boolean toBeExtracted(int iPix) {
		return (iPix & 0xFF000000) == 0x00000000	// already extracted
		    || (   MiRoesDraw.getRed(iPix) > MiRoesDraw.getBlue(iPix)
				&& MiRoesDraw.getRed(iPix) > MiRoesDraw.getGreen(iPix)*1.1f
		        && MiRoesDraw.getBlue(iPix)*2> MiRoesDraw.getGreen(iPix) ) ;
	}

	public static boolean toBeExtracted2(int iPix) {
		return (iPix & 0xFF000000) == 0x00000000	// already extracted
				|| (   MiRoesDraw.getRed(iPix)   > MiRoesDraw.getBlue(iPix)*1.1f
				    && MiRoesDraw.getGreen(iPix) > MiRoesDraw.getBlue(iPix)*1.1f
				    && MiRoesDraw.getRed(iPix)   < MiRoesDraw.getGreen(iPix)*1.4f
				    && MiRoesDraw.getRed(iPix)*1.4f> MiRoesDraw.getGreen(iPix) ) ;
	}
}
