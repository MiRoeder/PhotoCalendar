package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Poster16Adrianas {

	//public static final String BilderDir1 = "D:\\Pictures\\";
	public static final String BilderDir1 = "J:\\Bilder\\";
	private static int mX1;
	private static int mY1;
	private static int mTotalHeight;
	private static int mTotalWidth;
	private static int mWc;
	private static int mHc;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final int b1cX0 = 44; // left central picture left margin
			mWc = (1744-b1cX0+1350)*2;
			mHc = 1189*2;
			mTotalWidth = 75*120;
			mTotalHeight = 50*120;
			mX1 = (mTotalWidth-mWc)/2;
			mY1 = (mTotalHeight-mHc)/2;
			BufferedImage outImage = new BufferedImage(mTotalWidth, mTotalHeight, BufferedImage.TYPE_3BYTE_BGR );
			Graphics2D mPainter = outImage.createGraphics();
			mPainter.setColor(Color.WHITE);
			mPainter.fillRect(0, 0, outImage.getWidth(), outImage.getHeight());			
			mPainter.dispose();
			mPainter = null;

			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\0//I033_0002.jpg",
					b1cX0, 0, 1744, 1189, (1744-b1cX0)*2, mX1, mHc, mY1 );
			drawPic(outImage, "F:\\DOCS\\OurHomepageAtStrato\\V4//2009-04Familie.jpg",
					100, 774, 1450      , 1189+774, 1350*2, mX1+(1744-b1cX0)*2, mHc, mY1 );
			
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\0//118_0001.jpg", 0, 0, 250, 0, 0);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\1//9702kdg_0002.jpg", 1, 0, 620, 0, 1280);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\2/France3_0002.jpg", 2, 200, 220, 1450, 1100);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\3/Menorca1_0008.jpg", 3, 0, 100, 0, 0);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\4/I434_0002.jpg", 4, 0, 0, 0, 0);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\5/LeonieAdriana_0006.jpg", 5, 0, 0, 0, 0);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\Adriana\\6/Einschulung_0001.jpg", 6, 0, 0, 0, 0);
			drawPic(outImage, BilderDir1+"Urlaub 2003/Familie von Hoffotograf fotografiert.jpg", 7);
			drawPic(outImage, BilderDir1+"Amerika/16183452O.jpg", 8);
			drawPic(outImage, BilderDir1+"Adriana und Leonie Mai 2005 in Mellendorf/IMG_1013.jpg", 9, 1100, 0, 2100, 0);
			drawPic(outImage, BilderDir1+"Fussball WM 2006/P1000814.jpg", 10, 700, 0, 2200, 0);
			drawPic(outImage, BilderDir1+"R‹DIFEST2007/P1020048.jpg", 11);
			drawPic(outImage, "E:\\Java\\workspace\\FotoKalender\\in\\2008-01 Adrianas Selbstportrait Kopie.jpg", 12);
			drawPic(outImage, BilderDir1+"2009-02 Adriana am Computer//P1020872.jpg", 13);
			drawPic(outImage, BilderDir1+"2010-06-13 Adrianas Konfirmation/Groﬂ.jpg", 14);
			drawPic(outImage, BilderDir1+"2011Nordsee\\P1070668.jpg", 15, 200, 0, 1800, 0);
			/* */
			File outFile = new File("out\\Poster75x50.JPG");
			ImageIO.write(outImage, "jpg", outFile);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	static void drawPic(BufferedImage output, String inFileName, int trgInd) {
		drawPic(output, inFileName, trgInd, 0, 0, 0, 0);
	}

	static void drawPic(BufferedImage output, String inFileName, int trgInd, int x0, int y0, int sWid, int sHei) {
		try {
			int wid, xI, hei, yI;
			if (trgInd <= 2) {
				wid = mTotalWidth/5;
				hei = mY1;
				xI = (trgInd+2)*wid;
				yI = 0;			
			} else
			if (trgInd < 6) {
				wid = mX1;
				hei = mHc/3;
				xI = mTotalWidth-wid;
				yI = (trgInd-3)*hei+mY1;				
			} else
			if (trgInd <= 10) {
				wid = mTotalWidth/5;
				hei = mY1;
				xI = (10-trgInd)*wid;
				yI = mTotalHeight - mY1;				
			} else
			if (trgInd < 14) {
				wid = mX1;
				hei = mHc/3;
				xI = 0;
				yI = (13-trgInd)*hei+mY1;	;				
			} else
			{
				assert(trgInd <= 15);
				wid = mTotalWidth/5;
				hei = mY1;
				xI = (trgInd-14)*wid;
				yI = 0;				
			}
			final int rand = 1;
			wid -= 2*rand;
			xI += rand;
			hei -= 2*rand;
			yI += rand;
			drawPic(output, inFileName, x0, y0, sWid, sHei, wid, xI, hei, yI);
		} catch (Exception ex) {
			System.err.println("Problem with "+inFileName);
			ex.printStackTrace();
		}
	}
	

	public static void drawPic(
					    BufferedImage output, String inFileName, int x0, int y0, int sWid, int sHei,
						int tWid, int txI, int tHei, int tyI ) {
		try {
			File inFile = new File(inFileName);
			System.out.println(inFile.getAbsolutePath());
			BufferedImage inImage = ImageIO.read(inFile);
			sWid = sWid <= 0 ? inImage.getWidth() : Math.min(sWid, inImage.getWidth());
			sHei = sHei <= 0 ? inImage.getHeight(): Math.min(sHei, inImage.getHeight());
			sWid -= x0;
			sHei -= y0;
			double fx = (double)tWid/sWid;
			double fy = (double)tHei/sHei;
			double fak = Math.max(fx, fy);
			int usedW = (int)(tWid/fak+0.5);
			int usedH = (int)(tHei/fak+0.5);
			int xm = -(usedW - sWid)/2;
			int ym = -(usedH - sHei)/2;
			AffineTransform transformer = new AffineTransform(fak, 0, 0, fak, txI, tyI);
			drawPic(output, inImage, x0+xm, y0+ym, usedW, usedH, transformer);
		} catch (Exception ex) {
			System.err.println("Problem with "+inFileName);
			ex.printStackTrace();
		}
	}
	
	static void drawPic(BufferedImage output, BufferedImage inImage, int sx, int sy, int wid, int hei, AffineTransform transformer) {
		System.out.println("("+ sx +","+ sy +")..("+ (sx+wid) +","+ (sy+hei) +") ==> "+ transformer);
		BufferedImage srcImage = inImage.getSubimage(sx, sy, wid, hei);
		Graphics2D painter = output.createGraphics();
		painter.drawImage(srcImage, transformer, null);
	}
	
	static void drawPic(BufferedImage output, String inFileName, int x0, int y0) {
		try {
			File inFile = new File(inFileName);
			BufferedImage inImage = ImageIO.read(inFile);
			int wid = inImage.getWidth();
			int hei = inImage.getHeight();
			drawPic(output, inImage, x0, y0, x0+wid, y0+hei, 0, 0, wid, hei);
		} catch (Exception ex) {
			System.err.println("Problem with "+inFileName);
			ex.printStackTrace();
		}
	}

	static void drawPic(Image output, Image input, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		try {
			Graphics painter = output.getGraphics();
			boolean bFinished =
				painter.drawImage(input, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null) ;
			assert(bFinished);
			painter.dispose();
		} catch (Exception ex) {
			System.err.println("Problem at "+ dx1 +" "+ dy1);
			ex.printStackTrace();
		}
	}

}
