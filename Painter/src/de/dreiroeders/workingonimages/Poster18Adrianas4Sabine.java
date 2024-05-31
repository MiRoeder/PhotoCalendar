package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Poster18Adrianas4Sabine {

	public static final String BilderDir1 = "F:\\Pictures\\";
	public static final String BilderDir2 = "E:\\Bilder\\";
	
	/* Properties of the center picture: */
	final static int b1OrgWidth = 4224;
	final static int b1OrgHeight = 2946;
	final static int b1cX0 = 600; // left border to cut
	final static int b1cX9 = b1OrgWidth-3736; // right cut
	final static int b1cY0 = 320; // top border to cut
	final static int b1cY9 = b1OrgHeight-2726; // bottom cut

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
					
			mWc = b1OrgWidth  - b1cX0 - b1cX9;
			mHc = b1OrgHeight - b1cY0 - b1cY9;
			mTotalWidth = 60*120;
			mTotalHeight = 40*120;
			mX1 = (mTotalWidth-mWc)/2;
			mY1 = (mTotalHeight-mHc)/2;
			System.out.println("left/right pictures width: " + mX1 + " top/botton pictures height: "+ mY1);
			BufferedImage outImage = new BufferedImage(mTotalWidth, mTotalHeight, BufferedImage.TYPE_3BYTE_BGR );
			Graphics2D mPainter = outImage.createGraphics();
			mPainter.setColor(Color.WHITE);
			mPainter.fillRect(0, 0, outImage.getWidth(), outImage.getHeight());			
			mPainter.dispose();
			mPainter = null;

			Poster16Adrianas.drawPic(outImage, "C:\\Users\\Michael\\Pictures\\1990-Hochzeitsbild-mit-Eltern_2.jpg",
					b1cX0, b1cY0, mWc+b1cX0, mHc+b1cY0, mWc, mX1, mHc, mY1 );
			
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\0//I034.jpg", 0, 0, 0, 0, 0);
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\1//Sabine_mit_Adi.jpg", 1, 0, 400, 0, 0);
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\2/98_5A_0001.jpg", 2, 000, 00, 0, 0);
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\3/Schaukel_0002.jpg", 3, 0, 0, 0, 0);
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\4/Adriana raeumt auf.jpg", 4, 0, 0, 0, 0);
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\5/LeonieAdriana_0010.jpg", 5, 0, 0, 0, 0);
			drawPic(outImage, "..\\FotoKalender\\in\\Adriana\\6/Einschulung_0001.jpg", 6, 0, 0, 0, 0);
			drawPic(outImage, BilderDir2+"Urlaub 2003/Familie von Hoffotograf fotografiert.jpg", 7, 60, 0, 0, 0);
			drawPic(outImage, BilderDir2+"Amerika/14101126C.jpg", 8, 800, 200, 1600, 1200);
			drawPic(outImage, BilderDir2+"Prettin Ostern 2005\\697CANON\\IMG_9789.jpg", 9, 500, 300, 1150, 0);
			drawPic(outImage, BilderDir2+"Fussball WM 2006/P1000814.jpg", 10, 700, 0, 2200, 0);
			drawPic(outImage, BilderDir2+"Paris 2007\\FotoKalender\\in\\Paris 2007/P1010458.jpg", 11);
			drawPic(outImage, BilderDir2+"Urlaub 2008 Peleponnes\\102_PANA\\P1020519.jpg", 12);
			drawPic(outImage, BilderDir1+"2009 Sommerurlaub auf Korfu//P1040313.jpg", 13, 0, 0, 2000, 0);
			drawPic(outImage, BilderDir1+"2010-06-13 Adrianas Konfirmation/Groß.jpg", 14);
			drawPic(outImage, BilderDir1+"2011 Nordsee\\P1070643.jpg", 15, 400, 0, 2000, 0);
			drawPic(outImage, BilderDir1+"2012 Italien\\P1090779.jpg", 16, 500, 0, 2800, 0);
			drawPic(outImage, BilderDir1+"2013 Südtirol\\DSC01432.jpg", 17, 0, 0, 0, 0);
			/* */
			File outFile = new File("out\\Poster4Sabine2013.JPG");
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
				wid = mTotalWidth/6;
				hei = mY1;
				xI = (trgInd+3)*wid;
				yI = 0;			
			} else
			if (trgInd <= 3) {
				wid = mX1;
				hei = mHc;
				xI = mTotalWidth-wid;
				yI = (trgInd-3)*hei+mY1;				
			} else
			if (trgInd <= 13) {
				wid = mTotalWidth/10;
				hei = mY1;
				xI = (13-trgInd)*wid;
				yI = mTotalHeight - mY1;				
			} else
			if (trgInd <= 14) {
				wid = mX1;
				hei = mHc;
				xI = 0;
				yI = (14-trgInd)*hei+mY1;	;				
			} else
			{
				assert(trgInd <= 15);
				wid = mTotalWidth/6;
				hei = mY1;
				xI = (trgInd-15)*wid;
				yI = 0;				
			}
			final int rand = 1;
			wid -= 2*rand;
			xI += rand;
			hei -= 2*rand;
			yI += rand;
			Poster16Adrianas.drawPic(output, inFileName, x0, y0, sWid, sHei, wid, xI, hei, yI);
		} catch (Exception ex) {
			System.err.println("Problem with "+inFileName);
			ex.printStackTrace();
		}
	}
	
}
