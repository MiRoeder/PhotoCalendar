package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeIoUtil;


public class UnReflexion1 {

	public static final int MAX_X = 4427;
	
	public static void main(String[] args) {
		try {
			main4();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main2() throws Exception {
		File inFile;
		inFile = new File("C:/Users/Michael/Documents/E/Dev/Java/workspace/WorkingOnImages/tryRemoveMirrorImages.data/DSC04651.jpg");
		if (!inFile.exists()) {
			inFile.getParentFile().mkdir();
			inFile = new File("E:\\Bilder\\2015-Amerika\\DSC04651.jpg");
		}
		BufferedImage inImage = ImageIO.read(inFile);
		BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
				   BufferedImage.TYPE_3BYTE_BGR );
		makeDiff(inImage, 99, 99, 99, 99, (99+1)*(99+1)-1, 
				 outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif_99.jpg"),
				 new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR ),
				 new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Brights_99.png"),
				 new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR ),
				 new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Darks_99.png") );
	}
	
	public static void main1() throws Exception {
		File inFile;
		inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\DSC04651.jpg");
		if (!inFile.exists()) {
			inFile.getParentFile().mkdir();
			inFile = new File("E:\\Bilder\\2015-Amerika\\DSC04651.jpg");
		}
		BufferedImage inImage = ImageIO.read(inFile);
		float fakColors[] = new float[3];
		BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
				   BufferedImage.TYPE_3BYTE_BGR );
		for (int iReCol = 0; iReCol < 3; ++iReCol) {
			for (int iCol = 0; iCol < 3; ++iCol) {
				fakColors[iCol] = iCol == iReCol ? 1f : 0f;
			}
			reColor(inImage, fakColors, outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\ColQ"+iReCol+".png"));
			for (int iCol = 0; iCol < 3; ++iCol) {
				fakColors[iCol] = iCol != iReCol ? 1f : 0f;
			}
			reColor(inImage, fakColors, outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\ColN"+iReCol+".png"));
		}
		makeDiff(inImage, 9, 6, 9, 6, 99, outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif9696.png"));
		BufferedImage outImage2 = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
				   BufferedImage.TYPE_3BYTE_BGR );
		makeFullColorFul(outImage, new AllPixels(), outImage2, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\DFC9696.png"));
	}

	public static void main3() throws Exception {
		File inFile;
		inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif_99.jpg");
		BufferedImage inImage = ImageIO.read(inFile);
		BufferedImage outImage = new BufferedImage(MAX_X, inImage.getHeight(),
				   BufferedImage.TYPE_3BYTE_BGR );
		makeMask1(inImage, new RedPixels(),         outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\DifRed99.png"));
		makeMask1(inImage, new LightBrightPixels(), outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\DifBri99.png"));
	}
	
	public static void main4() throws Exception {
		File inFile;
		inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif_99.jpg");
		BufferedImage inImage = ImageIO.read(inFile);
		BufferedImage outImage = new BufferedImage(MAX_X, inImage.getHeight(),
				   BufferedImage.TYPE_3BYTE_BGR );
		MiRoesDraw.fillImage(outImage, Color.GRAY);
		FindLine findLine = new FindLine(new RedPixels(), outImage);
		makeMask1(inImage, findLine, outImage, new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\DifRedLines99.png"));
	}
	
	public static void reColor(
			BufferedImage inImage,
			float fakColors[],
			BufferedImage outImage,
			File outFile
			) throws IOException {
		for (int ym = 0; ym < inImage.getHeight(); ++ym) {
			for (int xm = 0; xm < inImage.getWidth(); ++xm) {
				int inPx = inImage.getRGB(xm, ym);
				int[] inCols = MiRoesDraw.getCs(inPx, null);
				for (int iCol = 0; iCol < 3; ++iCol) {
					inCols[iCol] = (int)((inCols[iCol]-128) * fakColors[iCol]) +128;
					if (inCols[iCol] < 0) {
						System.out.println("Zu dunkler Punkt: X="+ xm +" Y="+ ym +" iColor="+ iCol +":"+ inCols[iCol] +" in "+ outFile.getPath());
						inCols[iCol] = 0;
					}
					if (255 < inCols[iCol]) {
						System.out.println("Zu heller Punkt: X="+ xm +" Y="+ ym +" iColor="+ iCol +":"+ inCols[iCol] +" in "+ outFile.getPath());
						inCols[iCol] = 255;
					}
				}
				int outRGB = MiRoesDraw.makeRGB(inCols);
				outImage.setRGB(xm, ym, outRGB);
			} /* end for (int xm ...) */
		} /* end for (int ym ...) */
		ImageIO.write(outImage, "png", outFile);
	}
	
	
	public static void makeDiff(
			BufferedImage inImage,
			int idL,
			int idT,
			int idR,
			int idB,
			int maxDistQuad,
			BufferedImage outImage,
			File outFile
			) throws IOException {
		makeDiff(inImage, idL, idT, idR, idB, maxDistQuad, outImage, outFile, null, null, null, null);
	}


	public static void makeDiff(
			BufferedImage inImage,
			int idL,
			int idT,
			int idR,
			int idB,
			int maxDistQuad,
			BufferedImage difImage,    File outDifFile,
			BufferedImage brightImage, File outBrightFile,
			BufferedImage darkImage,   File outDarkFile
		) throws IOException {
		int wid = inImage.getWidth();
		int hei = inImage.getHeight();
		for (int ym = 0; ym < hei; ++ym) {
			for (int xm = 0; xm < wid; ++xm) {
				int inPx = inImage.getRGB(xm, ym);
				int[] inCols = MiRoesDraw.getCs(inPx, null);
				int[] othPxs = new int[3];
				int[] othPx1 = new int[3];
				int nOthPxs = 0;
				for (int yd = -idT; yd <= idB; ++yd) {
					for (int xd = -idL; xd <= idR; ++xd) {
						int quadDist = xd*xd + yd*yd;
						int x2 = xm+xd;
						int y2 = ym+yd;
						if (   0 < quadDist && quadDist <= maxDistQuad
							&& 0 <= x2 && x2 < wid
							&& 0 <= y2 && y2 < hei ) {
							int inOPx = inImage.getRGB(x2, y2);
							MiRoesDraw.getCs(inOPx, othPx1);
							++nOthPxs;
							for (int iCol = 0; iCol < 3; ++iCol) {
								othPxs[iCol] += othPx1[iCol];
							}
						}
					} /* end for (int xd ...) */
				}
				int outPx[] = new int[3];
				int briPx[] = new int[3];
				int darPx[] = new int[3];
				for (int iCol = 0; iCol < 3; ++iCol) {
					int othCol1 = (othPxs[iCol]+nOthPxs/2)/nOthPxs;
					int outCol1 = inCols[iCol] - othCol1;
					briPx[iCol] = 255-Math.max(outCol1, 0);
					darPx[iCol] = 255+Math.min(outCol1, 0);
					if (outCol1 < -127) {
						System.out.println("Scharfe Kante: X="+ xm +" Y="+ ym +" iColor="+ iCol +" Schärfe:"+ outCol1 +" in "+ outDifFile.getPath());
						outCol1 = -127;
					}
					if (127 < outCol1) {
						System.out.println("Scharfe Kante: X="+ xm +" Y="+ ym +" iColor="+ iCol +" Schärfe:"+ outCol1 +" in "+ outDifFile.getPath());
						outCol1 = 127;
					}
					outPx[iCol] = outCol1+128;
				}
				int outRGB = MiRoesDraw.makeRGB(outPx);
				difImage.setRGB(xm, ym, outRGB);
				if (brightImage != null) {
					int rgb = MiRoesDraw.makeRGB(briPx);
					brightImage.setRGB(xm, ym, rgb);
				}
				if (darkImage != null) {
					int rgb = MiRoesDraw.makeRGB(darPx);
					darkImage.setRGB(xm, ym, rgb);
				}
			} /* end for (int xm ...) */
			if (System.in.available() > 0) {
				System.out.println(ym);
				do {
					System.in.read();
				} while (System.in.available() > 0);
				MiRoesDraw.diagOut(difImage);
			}
		} /* end for (int ym ...) */
		ImageIO.write(difImage, MiRoeIoUtil.getFileExt(outDifFile), outDifFile);
		if (brightImage != null && outBrightFile != null) {
			ImageIO.write(brightImage, MiRoeIoUtil.getFileExt(outBrightFile), outBrightFile);
		}
		if (darkImage != null && outDarkFile != null) {
			ImageIO.write(darkImage, MiRoeIoUtil.getFileExt(outDarkFile), outDarkFile);
		}
	}
	
	public static void makeFullColorFul(
			BufferedImage inImage,
			IMaskPixel masker,
			BufferedImage outImage,
			File outFile
	) throws IOException {
		int Height = Math.min(inImage.getHeight(), outImage.getHeight());
		int Width = Math.min(inImage.getWidth(), outImage.getWidth());
		for (int ym = 0; ym < Height; ++ym) {
			for (int xm = 0; xm < Width; ++xm) {
				int outRGB;
				int inPx = inImage.getRGB(xm, ym);
				if (masker.DoItOnThisPixel(inImage, xm, ym)) {
					int[] inCols = MiRoesDraw.getCs(inPx, null);
					int brighestCol = -1;
					int iBrightestCol = -1;
					int darkestCol = 256;
					int iDarkestCol = -1;
					for (int iCol = 0; iCol < 3; ++iCol) {
						if (inCols[iCol] > brighestCol) {
							iBrightestCol = iCol;
							brighestCol = inCols[iCol];
						}
						if (inCols[iCol] < darkestCol) {
							iDarkestCol = iCol;
							darkestCol = inCols[iCol];
						}
					}
					int dCol = Math.min(255-brighestCol, darkestCol);
					inCols[iBrightestCol] += dCol;
					inCols[iDarkestCol]   -= dCol;
					outRGB = MiRoesDraw.makeRGB(inCols);
				} else {
					outRGB = inPx;
				}
				outImage.setRGB(xm, ym, outRGB);
			} /* end for (int xm ...) */
		} /* end for (int ym ...) */
		ImageIO.write(outImage, MiRoeIoUtil.getFileExt(outFile), outFile);
	}
	
	public static void makeMask1(
			BufferedImage inImage,
			IMaskPixel masker,
			BufferedImage outImage,
			File outFile
	) throws IOException {
		int Height = Math.min(inImage.getHeight(), outImage.getHeight());
		int Width = Math.min(inImage.getWidth(), outImage.getWidth());
		for (int ym = 0; ym < Height; ++ym) {
			for (int xm = 0; xm < Width; ++xm) {
				int outRGB;
				if (masker.DoItOnThisPixel(inImage, xm, ym)) {
					outRGB = Color.RED.getRGB();
				} else {
					outRGB = Color.LIGHT_GRAY.getRGB();
				}
				outImage.setRGB(xm, ym, outRGB);
			} /* end for (int xm ...) */
			if (System.in.available() > 0) {
				System.out.println(ym);
				do {
					System.in.read();
				} while (System.in.available() > 0);
				MiRoesDraw.diagOut(outImage);
			}
		} /* end for (int ym ...) */
		ImageIO.write(outImage, MiRoeIoUtil.getFileExt(outFile), outFile);
	}
	
	private static class AllPixels implements IMaskPixel {

		@Override
		public boolean DoItOnThisPixel(BufferedImage img, int x, int y) {
			return true;
		}
		
	}
	
	private static class LightBrightPixels implements IMaskPixel {

		@Override
		public boolean DoItOnThisPixel(BufferedImage img, int x, int y) {
			int rgb = img.getRGB(x, y);
			int br = 0;
			int nBr = 0;
			for (int iCol = 0; iCol < 3; ++iCol) {
				int nCol = MiRoesDraw.getCi(rgb, iCol);
				br += nCol;
				nBr += nCol > 128 ? 1 : 0;
			}
			return br > 3*128 && nBr >= 3;
		}
		
	}

	private static class RedPixels implements IMaskPixel {

		@Override
		public boolean DoItOnThisPixel(BufferedImage img, int x, int y) {
			int rgb = img.getRGB(x, y);
			int red = MiRoesDraw.getRed(rgb);
			int gre = MiRoesDraw.getGreen(rgb);
			int blu = MiRoesDraw.getBlue(rgb);
			return red >= 144 && red >= gre+48 && red >= blu+48;
		}
		
	}

}
