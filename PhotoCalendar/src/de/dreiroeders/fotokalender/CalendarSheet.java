package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.imageio.ImageIO;

import de.dreiroeders.workingonimages.Ambilight;
import de.dreiroeders.workingonimages.Draw1ImageA;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.MiRoesDraw;
import de.dreiroeders.workingonimages.MiRoesImageObserver;
import de.dreiroeders.workingonimages.Poster16Adrianas;
import de.dreiroeders.workingonimages.SourceImage;


public class CalendarSheet {

	/* for fWeight : */
	public static final float FWeight_posterXXL_Background_A2_hoch = 0.9997f;
	public static final float FWeight_whitewall_A2_hoch_facX1 = 1f/7f;
	public static final float FWeight_whitewall_A2_hoch = (17.3f/21f)/(1+FWeight_whitewall_A2_hoch_facX1); // de.whitewall.com MARKANT (WEISS) A2 hoch
	public static final float FWeight_A3_landscape_0 = 1.26f; // for Kalender DIN A3 quer ohne sonstige Texte
	
	public static int maxWidth = 20000;
	public static int maxHeight= 20000;
	
	public static Color sBackBackgroundCol;
	public Color mBackBackgroundCol;
	protected Color mColBack = null;
	public static float fWeight = 2.00f; //1.92f or a little bit more; // fotoquelle.de Vordergrundbild
	//public static float fWeight = 1.68f; // whitewall.com Vordergrundbild
	//public static float fWeight = 42f/24.3f; // posterXXL.de Vordergrundbild 42 x 29,7 cm
	//public static float fWeight = 1.41f; // normales A3 Blatt
	protected final int THIS_YEAR;
	protected final int nMonth;
	protected PersonalDates mDates;
	public Graphics2D mPainter;
	protected Font dayNumberFont;
	protected Font smallerFont;
	protected BufferedImage mOutImage;
	protected int mLineHeight;
	protected float mLinesPerDay;
	protected int mX0;
	protected int mX01 = 0;
	protected int mX1;
	protected int mX5;
	protected int mX9;
	protected int mY0;
	protected int mY5;
	protected int mY9;
	protected String mStrDayFormat;
	protected Color mColDefaultText = null;
	protected int mLastX;
	protected int mLastY;

	
	public CalendarSheet(int nYear, int nMonth, PersonalDates mDates) {
		THIS_YEAR = nYear < 100 ? nYear+2000 : nYear;
		this.nMonth = nMonth;
		this.mDates = mDates;
		this.mLinesPerDay = 1;
		this.mStrDayFormat = "EEEE";
		this.mLastX = 0;
		this.mLastY = 0;
	}
	
	public void prepareImage(int usuableWidth) {
		prepareImage(usuableWidth, getDefaultBackgroundCol());
	}
	
	public void prepareImage(double usuableWidth) {
		int usuWidth = (int)Math.ceil(usuableWidth);
		System.out.println("usuableWidth = "+usuWidth);
		prepareImage(usuWidth, getDefaultBackgroundCol());
	}
	
	public void prepareImage(int usuableWidth, Color colBack) {
		prepareImage(usuableWidth, 1, colBack);
	}
	
	public void prepareImage(int usuableWidth0, int usuableHeight0, Color colBack) {
		int usuableWidth = usuableWidth0;
		if (usuableWidth < 1500) {
			if (usuableWidth < 15) {
				usuableWidth = 2000;
			}
			int fac = 3000/usuableWidth;
			usuableWidth = fac*usuableWidth;
			System.out.println("Wanted weight "+ usuableWidth0 +" pixels, but poor quality, replaced by: "+ usuableWidth);
		}
		if (usuableWidth < 2000) {
			usuableWidth = usuableWidth*3/2;
			System.out.println("Wanted weight "+ usuableWidth0 +" pixels, but poor quality, replaced by: "+ usuableWidth);
		}
		if (usuableWidth > maxWidth) {
			usuableWidth = maxWidth;
			System.out.println("Wanted weight "+ usuableWidth0 +" pixels, but limited for this test to "+ usuableWidth);
		}
		int usuableHeight = usuableHeight0;
		if (usuableHeight > maxHeight) {
			usuableHeight = maxHeight;
			usuableWidth = 1;
			System.out.println("Wanted height "+ usuableHeight0 +" pixels, but limited for this test to "+ usuableHeight);
		}
		if (nMonth < Calendar.JANUARY || Calendar.DECEMBER +1 < nMonth) {
			int w2 = Math.max((int)(usuableWidth), (int)(usuableHeight*fWeight+.5f));
			int h2 = (int)(w2/fWeight+.9);
			mX0 = 0;
			mY0 = 0;
			mX1 = 0;
			mX9 = mX0;
			mY9 = 0;
			mX5 = w2;
			mY5 = h2;
			mLinesPerDay = 0f;
			this.mStrDayFormat = "";
		} else
		if (fWeight == FWeight_whitewall_A2_hoch) {
			// de.whitewall.com MARKANT (WEISS) A2 hoch 
			int w2 = Math.max((int)(usuableWidth), (int)(usuableHeight*fWeight))+1;
			float facX0 = 1.01f + 115f/7744;
			float facSize= (1+FWeight_whitewall_A2_hoch_facX1)*facX0*1.06f;
			int totSize = (int)(w2*w2*facSize/fWeight);
			if (totSize > 5E7) {
				System.out.println("Wanted height "+ usuableHeight0 +" pixels, but de.whitewall.com höchstens 50 MPixels.");
				System.out.println("Wanted width " + usuableWidth0  +" pixels, but de.whitewall.com höchstens 50 MPixels.");
				w2 = (int)Math.sqrt(5E7/facSize*fWeight);
			}
			--w2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2*(facX0-1));
			mY0 = h2/50;
			mX1 = (int)(w2*FWeight_whitewall_A2_hoch_facX1);
			mX9 = mX0;
			mY9 = mY0;
			mX5 = w2;
			mY5 = h2;
			mLinesPerDay = 2.1f;
			this.mStrDayFormat = "EE";			
		} /* (fWeight == FWeight_whitewall_A2_hoch) */ else
		if (fWeight == FWeight_posterXXL_Background_A2_hoch) {	// for  posterXXL.de  XXL Kalender DIN A2 hoch Hintergrundbild
			// for  posterXXL.de  XXL Kalender DIN A2 hoch
			int w2 = Math.max((int)(usuableWidth*10/9), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/200f+0.99);
			mY0 = (int)(w2/200f+0.99);
			mX1 = w2/10+1;
			mX9 = mX0;
			mY9 = h2*4/10;
			mX5 = w2*9/10;
			mY5 = h2;
			mLinesPerDay = 2.1f;
			this.mStrDayFormat = "EE";
		} else
		if (fWeight < 0.95f) {
			// de.whitewall.com  A2 hoch Rustikal
			if (usuableWidth > 5400) {
				usuableWidth = 5400;
				// but 2451 sind wieder zu wenig
				System.out.println("Wanted height "+ usuableHeight0 +" pixels, but de.whitewall.com  höchstens 50 MPixels, deshalb :"+ usuableHeight);
				System.out.println("Wanted width " + usuableWidth0  +" pixels, but de.whitewall.com  höchstens 50 MPixels, deshalb :"+ usuableWidth);
			}
			int w2 = Math.max((int)(usuableWidth*10/9), (int)(usuableHeight*fWeight))+2;
			float facSize = 1/fWeight*(1f+1f/40+1f/40+1/8+1/40+1/80);
			int totSize = (int)(w2*w2*facSize);
			if (totSize > 5E7) {
				System.out.println("Wanted height "+ usuableHeight0 +" pixels, but de.whitewall.com höchstens 50 MPixels."+ usuableHeight);
				System.out.println("Wanted width " + usuableWidth0  +" pixels, but de.whitewall.com höchstens 50 MPixels."+ usuableWidth);
				w2 = (int)Math.sqrt(5E7/facSize);
			}
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/40f+0.99);
			mY0 = (int)(w2/40f+0.99);
			mX1 = w2/8+1;
			mX9 = mX0;
			mY9 = mY0/2;
			mX5 = w2*9/10;
			mY5 = h2;
			mLinesPerDay = 2.1f;
			this.mStrDayFormat = "EE";
		} else
		if (fWeight < 1.001f) {
			// for  posterXXL.de  XXL Kalender DIN A2 hoch
			int w2 = Math.max((int)(usuableWidth*10/9), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/200f+0.99);
			mY0 = (int)(w2/200f+0.99);;
			mX1 = w2/10+1;
			mX9 = mX0;
			mY9 = mY0;
			mX5 = w2*9/10;
			mY5 = h2;
			mLinesPerDay = 2.1f;
			this.mStrDayFormat = "EE";
		} else
		if (fWeight < 1.4201f) { // for Kalender DIN A3 quer ohne sonstige Texte
			int w2 = Math.max((int)(usuableWidth), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/20f+0.99);
			mY0 = (int)(w2/20f+0.99);;
			mX1 = w2/6+1;
			mX9 = mX0;
			mY9 = mY0;
			mX5 = w2;
			mY5 = h2;
		} else
		if (fWeight < 1.651f) {	// for  myposter.de BIG BLANCO DIN A3 quer or https://www.myposter.de/wandkalender/blanko
			int w2 = Math.max((int)(usuableWidth*8/7), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/30f+0.99);
			mY0 = (int)(w2/30f+0.99);;
			mX1 = w2/8+1;
			mX9 = mX0;
			mY9 = mY0*3;
			mX5 = w2*7/8;
			mY5 = h2;
		} else
		if (fWeight < 1.731f) {	// for  posterXXL.de  XXL Kalender DIN A3 quer
			int w2 = Math.max((int)(usuableWidth*8/7), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/20f+0.99);
			mY0 = (int)(w2/20f+0.99);;
			mX1 = w2/8+1;
			mX9 = mX0;
			mY9 = mY0*3;
			mX5 = w2*7/8;
			mY5 = h2;
		} else
		if (fWeight < 1.799f) {	// for whitewall.com Kalender dezent DIN A3 quer
			int w2 = Math.max((int)(usuableWidth*8/7), (int)(usuableHeight*fWeight))+2;
			if (w2 > 8000) {
				usuableWidth = 7000;
				w2 = (int)(usuableWidth*8/7);
				// but 2451 sind wieder zu wenig
				System.out.println("Wanted height "+ usuableHeight0 +" pixels, but de.whitewall.com  höchstens 50 MPixels, deshalb :"+ usuableWidth/fWeight);
				System.out.println("Wanted width " + usuableWidth0  +" pixels, but de.whitewall.com  höchstens 50 MPixels, deshalb :"+ usuableWidth);
			}
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/50f+0.99);
			mY0 = (int)(w2/25f+0.99);
			mX1 = w2/8+1;
			mX9 = mX0;
			mY9 = mY0;
			mX5 = w2*7/8;
			mY5 = h2;
		} else
		if (fWeight < 1.801f) {	// for  posterXXL.de  XXL Kalender DIN A2 quer
			int w2 = Math.max((int)(usuableWidth*8/7), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/100f+0.99);
			mY0 = (int)(w2/50f+0.99);;
			mX1 = w2/8+1;
			mX9 = mX0;
			mY9 = mY0*3;
			mX5 = w2*7/8;
			mY5 = h2;
		} else {	// for  fotoquelle.de Kalender DIN A3 quer
			int w2 = Math.max((int)(usuableWidth*8/7), (int)(usuableHeight*fWeight))+2;
			int h2 = (int)(w2/fWeight+.9);
			mX0 = (int)(w2/50f+0.99);
			mY0 = (int)(w2/17f+0.99);
			mX1 = w2/8+1;
			mX9 = mX0;
			mY9 = mY0;
			mX5 = w2*7/8;
			mY5 = h2;
		}
		if (fWeight > 2.001f) { // for example: CalendarSheet.fWeight = 28f/13f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE
			mY0 = mY0*2;
			mY9 = mY9*2;
		}
		if (mY9 < 0) {
			System.out.println("mY9 = "+ mY9);
			mY9 = 0;
		}
		{	/* begin diagnosis output to stdout: */
			StringBuilder diag = new StringBuilder();
			diag.append(nMonth+1);
			diag.append(", wanted usuableWidth = ").append(usuableWidth);
			diag.append(", usuableHeight = ").append(usuableHeight);
			diag.append(", mX0 = ").append(mX0);
			diag.append(", mX1 = ").append(mX1);
			diag.append(", mX5 = ").append(mX5);
			diag.append(", mX9 = ").append(mX9);
			diag.append(", mY0 = ").append(mY0);
			diag.append(", mY5 = ").append(mY5);
			diag.append(", mY9 = ").append(mY9);
			diag.append("; [ ").append(mX0+mX1+mX5+mX9);
			diag.append(" x ").append(mY0+mY5+mY9).append(" ]");
			diag.append(" = ").append((mX0+mX1+mX5+mX9) * (mY0+mY5+mY9) / 1E6d).append(" Mega Pixels");
			System.out.println(diag);
		}   /* end diagnosis output to stdout: */
		mLineHeight = (int)(mY5/32);
		mOutImage = new BufferedImage(mX0+mX1+mX5+mX9, mY0+mY5+mY9, BufferedImage.TYPE_3BYTE_BGR);
		mPainter = mOutImage.createGraphics();
		mPainter.setColor(colBack);
		mColBack = colBack;
		mPainter.fillRect(0, 0, mX0+mX1+mX5+mX9, mY0+mY5);
		for (int y8 = mY0+mY5; y8 < mY0+mY5+mY9; ++y8) {
			float f7 = (float)(y8-(mY0+mY5))/mY9;
			float f8 = 1f-f7;
			Color backBackgroundCol = getDefaultBackBackgroundCol();
			int nRed = (int)(backBackgroundCol.getRed()  *f7 + mColBack.getRed()  *f8 +0.5f);
			int nGrn = (int)(backBackgroundCol.getGreen()*f7 + mColBack.getGreen()*f8 +0.5f);
			int nBlu = (int)(backBackgroundCol.getBlue() *f7 + mColBack.getBlue() *f8 +0.5f);
			mPainter.setColor(new Color(nRed, nGrn, nBlu));
			mPainter.drawLine(0, y8, mX0+mX1+mX5+mX9, y8);
		}
	}

	public Color getDefaultBackgroundCol() {
		if (mBackBackgroundCol == null) {
			mBackBackgroundCol = sBackBackgroundCol;
		}
		if (mBackBackgroundCol == null) {
			mBackBackgroundCol = Color.WHITE;
		}
		final int cG = 16;
		int nG = (int)(Math.cos((this.nMonth-Calendar.APRIL)/6d*Math.PI)*cG+calcBasisColor(mBackBackgroundCol.getGreen(), cG));
		assert(0 <= nG && nG <= 255);
		final int cR = 24;
		int nR = (int)(Math.cos((this.nMonth-Calendar.AUGUST)/6d*Math.PI)*cR+calcBasisColor(mBackBackgroundCol.getRed(), cR));
		assert(0 <= nR && nR <= 255);
		final int cB = 24;
		int nB = (int)(Math.cos((this.nMonth-Calendar.DECEMBER)/6d*Math.PI)*cB+calcBasisColor(mBackBackgroundCol.getBlue(), cB));
		assert(0 <= nB && nB <= 255);
		return new Color(nR, nG, nB);
	}
	
	public Color getDefaultBackBackgroundCol() {
		if (mBackBackgroundCol == null) {
			mBackBackgroundCol = sBackBackgroundCol;
		}
		if (mBackBackgroundCol == null) {
			mBackBackgroundCol = Color.WHITE;
		}
		return mBackBackgroundCol;
	}
	
	public int calcBasisColor(int nCol, int nFak) {
		assert(0 <= nFak);
		assert(nFak < 128);
		return Math.max(nFak, Math.min(nCol, 255-nFak));
	}
	
	public Graphics2D getPainter() {
		if (mPainter == null && mOutImage != null) {
			mPainter = mOutImage.createGraphics();
		}
		return mPainter;
	}

	public void drawCheckLines(Color col, int borderDistance) {
		if (0 <= borderDistance && borderDistance < mOutImage.getHeight()/2) {
			mPainter.setColor(col);
			mPainter.drawRoundRect(borderDistance, borderDistance,
					mOutImage.getWidth()-2*borderDistance, mOutImage.getHeight()-2*borderDistance,
					100, 100 );
		}
	}
	
	public void drawCheckLines() {
		drawCheckLines(Color.GRAY, 0);
		drawCheckLines(new Color(255, 255, 224), mX0/2-1);
		drawCheckLines(new Color(255, 224, 255), mY0/2+1);
		drawCheckLines(new Color(255, 255, 192), mX0-1);
		drawCheckLines(new Color(255, 192, 255), mY0+1);
		drawCheckLines(new Color(224, 255, 192), 2*mX0-1);
		drawCheckLines(new Color(224, 192, 255), 2*mY0+1);
		drawCheckLines(new Color(192, 255, 192), 3*mX0-1);
		drawCheckLines(new Color(192, 192, 255), 3*mY0+1);
	}
	
	public int getTextWidth(String strText, Font font) {
		mPainter.setFont(font);
		Rectangle2D shape = font.getStringBounds(strText, mPainter.getFontRenderContext());
		int wid = (int)Math.ceil(shape.getWidth());
		return wid;
	}
	
	public int getX1() {
		return mX0+mX1;
	}

	public int getY1() {
		return mY0;
	}

	public int getUsuableWidth() {
		return mX5;
	}

	public int getUsuableHeight() {
		return mY5;
	}

	public int getTotalHeight() {
		return mOutImage.getHeight();
	}

	public int getTotalWidth() {
		return mOutImage.getWidth();
	}

	public int getDX(double x1) {
		return (int)(x1 * mX5 +0.5);
	}
	
	public int getX(double x1) {
		return (int)(x1 * mX5 +0.5)+mX0+mX1;
	}
	
	public int getY(double y1) {
		return (int)(y1 * mY5 +0.5)+mY0;
	}
	
	public int getDY(double y1) {
		return (int)(y1 * mY5 +0.5);
	}
	
	public int getYLine(double y1) {
		return (int)((y1+.5f/mLinesPerDay) * mLineHeight)+mY0;
	}
	
	public int getLineHeight() {
		return (int)(mLineHeight/mLinesPerDay);
	}
	
	public float getLinesPerDay() {
		return mLinesPerDay;
	}

	private int getX01() {
		if (mX01 < 1) {
			mX01 = mX0 + getTextWidth("28 ", dayNumberFont);
		}
		return mX01;
	}
	
	public int getLastX() {
		return mLastX-mX0-mX1;
	}
	
	public int getLastY() {
		return mLastY-mY0;
	}
	
	public float getflLastX() {
		return (float)(mLastX-mX0-mX1)/mX5;
	}
	
	public float getflLastY() {
		return (float)(mLastY-mY0)/mY5;
	}
	
	
	public Color getBackgroundCol() {
		return mColBack;
	}
	
	public Color getDefaultTextCol() {
		if (mColDefaultText == null) {
			int nRedText = Math.min(224, Math.max(32, (mColBack.getRed()  -128) & 255));
			int nGrnText = Math.min(224, Math.max(32, (mColBack.getGreen()-128) & 255));
			int nBluText = Math.min(224, Math.max(32, (mColBack.getBlue() -128) & 255));
			mColDefaultText = new Color(nRedText, nGrnText, nBluText);			
		}
		return mColDefaultText;
	}
	
	public void drawMasked(BufferedImage inImage, BufferedImage maskImage, float maskFac, int targetX0, int targetY0) throws Exception {
		MiRoesDraw.drawMasked(inImage, maskImage, maskFac, mOutImage, targetX0, targetY0);
	}

	public void drawImageAsFooter(String strInJpg) {
		try {
			File inFile = new File(strInJpg);
			BufferedImage inImage = ImageIO.read(inFile);
			double width = inImage.getWidth();
			double fac = mOutImage.getWidth() / width;
			int height = (int)(inImage.getHeight()*fac) +1;
			drawImage(fac, 0.0, 0, 0,
					  0, getUsuableHeight() - height,
					  mOutImage.getWidth(), height, inImage );
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	public boolean drawNextImageR(
			String strInFileName,
			double centerPointX,
			double centerPointY,
			int nBildZwischenraum,
			double ty0,
			double tHeight
			) {
		if (mLastX-mX0-mX1 + 3*nBildZwischenraum < mX5) {
			drawImage(strInFileName, centerPointX, centerPointY, 0f, mLastX-mX0-mX1+nBildZwischenraum, ty0, 0f, tHeight);
			return mLastX-mX0-mX1 + 3*nBildZwischenraum < mX5;
		} else {
			System.out.println("Image \""+ strInFileName +"\" will be ignored, because no space in this row.");
			return false;
		}
	}

	public boolean drawNextImageB(
			String strInFileName,
			double centerPointX,
			double centerPointY,
			int nBildZwischenraum,
			double tx0,
			double tWidth
			) {
		if (mLastY-mY0 + 3*nBildZwischenraum < mY5) {
			drawImage(strInFileName, centerPointX, centerPointY, 0f, tx0, mLastY-mY0+nBildZwischenraum, tWidth, 0f);
			return mLastY-mY0 + 3*nBildZwischenraum < mY5;
		} else {
			System.out.println("Image \""+ strInFileName +"\" will be ignored, because no space in this row.");
			return false;
		}
	}

	
	/**
	 * 
	 * @param strInFileName File name of the source image
	 * @param centerPointX  the X-Coord of the center point. Usually, you will put here 0.5f.
	 * @param centerPointY  the Y-Coord of the center point. Usually, you will put here 0.5f.
	 * 			It should be the point where the most interesting thing in the image is located.
	 *          If the source image will be rotated or truncated, these parameters will be used.
	 *          You can enter a number between 0 and 1 here. Then the value will be multiplied by the actual width and height of the source image.
	 *          Or you enter exactly the center point measured in pixels.
	 * @param rotator       Usually you will put here 0.0.
	 *          It defines the clockwise rotation of the source image.
	 *          If you enter a value between -10 (counter clockwise) and +10 (clockwise), the value will be measured in radians. (Means 2*Pi is a complete circle)
	 *          If you enter a value lower than -10 or higher than +10 the value be measured as degrees. (360 degrees is a circle).
	 *          But if you enter 360, the method will rotate the source image in an other way. The image will be rotated, so that as much as possible fits into the target area.
	 * @param tx0		Left Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param ty0		Top Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param tWidth    Width of the target area. If you want to fill the whole target area, put here 1.0
	 * @param tHeight   Height of the tareget area. If you want to fill the whole target area, put here 1.0
	 * 			For the four parameters for the target area, you can enter a number between 0 and 1 here. Then the value will be multiplied by the actual size of the target area.
	 * 			Or you can enter the exact value measured in pixels.
	 */
	public void drawImage(
			String strInFileName,
			double centerPointX,
			double centerPointY,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			) {
		try {
			SourceImage src = new SourceImage(strInFileName);
			BufferedImage inImage = src.getImage();
			drawImage(inImage, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		} catch (Exception ex) {
			System.err.println("Problem with " + strInFileName);
			ex.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @param strInFileName File name of the source image
	 * @param centerPointX  the X-Coord of the center point. Usually, you will put here 0.5f.
	 * @param centerPointY  the Y-Coord of the center point. Usually, you will put here 0.5f.
	 * 			It should be the point where the most interesting thing in the image is located.
	 *          If the source image will be rotated or truncated, these parameters will be used.
	 *          You can enter a number between 0 and 1 here. Then the value will be multiplied by the actual width and height of the source image.
	 *          Or you enter exactly the center point measured in pixels.
	 * @param rotator       Usually you will put here 0.0.
	 *          It defines the clockwise rotation of the source image.
	 *          If you enter a value between -10 (counter clockwise) and +10 (clockwise), the value will be measured in radians. (Means 2*Pi is a complete circle)
	 *          If you enter a value lower than -10 or higher than +10 the value be measured as degrees. (360 degrees is a circle).
	 *          But if you enter 360, the method will rotate the source image in an other way. The image will be rotated, so that as much as possible fits into the target area.
	 * @param clip			Must use the coordinates of the whole CalendarSheet.
	 */
	public void drawImage(
			String strInFileName,
			double centerPointX,
			double centerPointY,
			double rotator,
			Shape  clip
			) {
		try {
			SourceImage src = new SourceImage(strInFileName);
			BufferedImage inImage = src.getImage();
			drawImage(inImage, centerPointX, centerPointY, rotator, clip);
		} catch (Exception ex) {
			System.err.println("Problem with " + strInFileName);
			ex.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @param src           the source image
	 * @param centerPointX  the X-Coord of the center point. Usually, you will put here 0.5f.
	 * @param centerPointY  the Y-Coord of the center point. Usually, you will put here 0.5f.
	 * 			It should be the point where the most interesting thing in the image is located.
	 *          If the source image will be rotated or truncated, these parameters will be used.
	 *          You can enter a number between 0 and 1 here. Then the value will be multiplied by the actual width and height of the source image.
	 *          Or you enter exactly the center point measured in pixels.
	 * @param rotator       Usually you will put here 0.0.
	 *          It defines the clockwise rotation of the source image.
	 *          If you enter a value between -10 (counter clockwise) and +10 (clockwise), the value will be measured in radians. (Means 2*Pi is a complete circle)
	 *          If you enter a value lower than -10 or higher than +10 the value be measured as degrees. (360 degrees is a circle).
	 *          But if you enter 360, the method will rotate the source image in an other way. The image will be rotated, so that as much as possible fits into the target area.
	 * @param tx0		Left Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param ty0		Top Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param tWidth    Width of the target area. If you want to fill the whole target area, put here 1.0
	 * @param tHeight   Height of the tareget area. If you want to fill the whole target area, put here 1.0
	 * 			For the four parameters for the target area, you can enter a number between 0 and 1 here. Then the value will be multiplied by the actual size of the target area.
	 * 			Or you can enter the exact value measured in pixels.
	 */
	public void drawImage(
			SourceImage src,
			double centerPointX,
			double centerPointY,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			) {
		try {
			BufferedImage inImage = src.getImage();
			drawImage(inImage, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		} catch (Exception ex) {
			System.err.println("Problem with " + src.toString());
			ex.printStackTrace();
		}
	}

	
	/**
	 * 
	 * @param inImage   	The source image
	 * @param centerPointX  the X-Coord of the center point. Usually, you will put here 0.5f.
	 * @param centerPointY  the Y-Coord of the center point. Usually, you will put here 0.5f.
	 * 			It should be the point where the most interesting thing in the image is located.
	 *          If the source image will be rotated or truncated, these parameters will be used.
	 *          You can enter a number between 0 and 1 here. Then the value will be multiplied by the actual width and height of the source image.
	 *          Or you enter exactly the center point measured in pixels.
	 * @param rotator       Usually you will put here 0.0.
	 *          It defines the clockwise rotation of the source image.
	 *          If you enter a value between -10 (counter clockwise) and +10 (clockwise), the value will be measured in radians. (Means 2*Pi is a complete circle)
	 *          If you enter a value lower than -10 or higher than +10 the value be measured as degrees. (360 degrees is a circle).
	 *          But if you enter 360, the method will rotate the source image in an other way. The image will be rotated, so that as much as possible fits into the target area.
	 * @param tx0		Left Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param ty0		Top Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param tWidth    Width of the target area. If you want to fill the whole target area, put here 1.0
	 * @param tHeight   Height of the tareget area. If you want to fill the whole target area, put here 1.0
	 * 			For the four parameters for the target area, you can enter a number between 0 and 1 here. Then the value will be multiplied by the actual size of the target area.
	 * 			Or you can enter the exact value measured in pixels.
	 */
	public void drawImage(
			BufferedImage inImage,
			double centerPointX,
			double centerPointY,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			) {
		Draw1ImageA img = new Draw1ImageA(inImage, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		drawImageA(img);
	}
	
	public void drawImage(
			BufferedImage inImage,
			double centerPointX,
			double centerPointY,
			double rotator,
			Shape  clip
			) {
		Draw1ImageA img = new Draw1ImageA(inImage, centerPointX, centerPointY, rotator, clip);
		drawImageA(img);
	}
	
	public void drawImageA(Draw1ImageA img) {
		if (img.iCenterPoint == null) {
			double centerPointX, centerPointY;
			if (img.aCenterPoint != null) {
				centerPointX = img.aCenterPoint.getX();
				centerPointY = img.aCenterPoint.getY();
			} else {
				centerPointX = 0.5;
				centerPointY = 0.5;
			}
			int iCPX;
			if (centerPointX < 1.0) {
				int imW = img.getImage().getWidth();
				iCPX = (int)(centerPointX*imW+0.5);
			} else {
				iCPX = (int)(centerPointX+0.5);
			}
			
			int iCPY;
			if (centerPointY < 1.0) {
				int imH = img.getImage().getHeight();
				iCPY = (int)(centerPointY*imH+0.5);
			} else {
				iCPY = (int)(centerPointY+0.5);
			}
			img.iCenterPoint = new Point(iCPX, iCPY);
		} /* end if (img.iCenterPoint == null) */
		
		int itX0;
		int itY0;
		if (img.iOutRect == null) {
			double rotator = img.dRot;
			double tx0     = img.aOutRect.getX();
			double ty0     = img.aOutRect.getY();
			double tWidth  = img.aOutRect.getWidth();
			double tHeight = img.aOutRect.getHeight();
			int itW;
			if (tWidth < 0.000001) {
				if (Math.abs(rotator) > 0.1) {
					System.err.println("Warning: Automatic calculation of target width cannot consider rotation!");
				}
				int itH;
				if (tHeight < 1.5) {
					itH = (int)(tHeight*mY5+0.5);
				} else {
					itH = (int)(tHeight+0.5);
				}
				int inW = 2*Math.min(img.getImage().getWidth() -img.iCenterPoint.x, img.iCenterPoint.x);
				int inH = 2*Math.min(img.getImage().getHeight()-img.iCenterPoint.y, img.iCenterPoint.y);
				itW = itH * inW / inH;
			} else
			if (tWidth < 1.5) {
				itW = (int)(tWidth*mX5+0.5);
			} else {
				itW = (int)(tWidth+0.5);
			}
			
			if (-2.0 < tx0 && tx0 < 0.999999) {
				itX0 = (int)(tx0*mX5+0.5);
			} else
			if (tx0 < 1.000001) { 
				itX0 = mX5-itW;
			} else {
				itX0 = (int)(tx0+0.5);
			}
			int itH;
			if (tHeight < 0.000001) {
				if (Math.abs(rotator) > 0.1) {
					System.out.println("Warning: Automatic calculation of target width cannot consider rotation!");
				}
				int inW = 2*Math.min(img.getImage().getWidth() -img.iCenterPoint.x, img.iCenterPoint.x);
				int inH = 2*Math.min(img.getImage().getHeight()-img.iCenterPoint.y, img.iCenterPoint.y);
				itH = itW * inH / inW;
			} else
			if (tHeight < 1.5) {
				itH = (int)(tHeight*mY5+0.5);
			} else {
				itH = (int)(tHeight+0.5);
			}
			
			if (-2.0 < ty0 && ty0 < 0.999999) {
				itY0 = (int)(ty0*mY5+0.5);
			} else
			if (ty0 < 1.000001) {
				itY0 = (int)(mY5-itH);
			} else {
				itY0 = (int)(ty0+0.5);
			}

			img.iOutRect = new Rectangle(itX0+mX0+mX1, itY0+mY0, itW, itH);
		} else /* !(img.iOutRect == null) */ {
			itX0 = img.iOutRect.x - mX0-mX1;
			itY0 = img.iOutRect.y - mY0;
		}

		if (itX0 + img.iOutRect.width > mX5) {
			img.iOutRect.width = mX5-itX0;
		}
		if (itY0 + img.iOutRect.height > mY5) {
			img.iOutRect.height = mY5-itY0;
		}			

		if (img.iOutRect.x >= 0 && img.iOutRect.y >= 0 && img.iOutRect.width > 0 && img.iOutRect.height > 0) {
			drawImage(img);
		} else {
			System.out.println("Target rectangle out of bounds or empty! "+ img.iOutRect);
		}
		MiRoesDraw.diagOut(mOutImage);
	}

	/* will be overwritten by MaskedCalendarSheet */
	public void drawImage(Draw1ImageI img) {
		img.outputImage = this.mOutImage;
		MiRoesDraw.drawImage(img);
		this.mLastX = img.iOutRect.x + img.iOutRect.width;
		this.mLastY = img.iOutRect.y + img.iOutRect.height;
	}

	/* will be overwritten by MaskedCalendarSheet */
	public void drawImage(
		BufferedImage inImage,
		AffineTransform target
		) {
		mPainter.drawImage(inImage, target, new MiRoesImageObserver());
	}

	public void drawImage(String strInJpg, double factor, double rotate, int sx, int sy, int dx, int dy, int width, int height) {
		try {
			File inFile = new File(strInJpg);
			BufferedImage inImage = ImageIO.read(inFile);
			drawImage(factor, rotate, sx, sy, dx, dy, width, height, inImage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void drawImage(double factor, double rotate, int sx, int sy,
			int dx, int dy, int width, int height, BufferedImage inImage) {
		rotate *= Math.PI/180;
		double sin = Math.sin(rotate)*factor;
		double cos = Math.cos(rotate)*factor;
		AffineTransform transform = new AffineTransform(cos, sin, -sin, cos, 0, 0);
		Point2D[] vertexes = new Point2D[]{new Point2D.Double(0, 0),
										   new Point2D.Double(inImage.getWidth(), 0),
										   new Point2D.Double(0, inImage.getHeight()),
										   new Point2D.Double(inImage.getWidth(), inImage.getHeight()) };
		transform.transform(vertexes, 0, vertexes, 0, 4);
		int x0 = (int)Math.floor(Math.min(Math.min(vertexes[0].getX(), vertexes[1].getX()), Math.min(vertexes[2].getX(), vertexes[3].getX())));
		int y0 = (int)Math.floor(Math.min(Math.min(vertexes[0].getY(), vertexes[1].getY()), Math.min(vertexes[2].getY(), vertexes[3].getY())));
		int x9 = (int)Math.ceil (Math.max(Math.max(vertexes[0].getX(), vertexes[1].getX()), Math.max(vertexes[2].getX(), vertexes[3].getX())));
		int y9 = (int)Math.ceil (Math.max(Math.max(vertexes[0].getY(), vertexes[1].getY()), Math.max(vertexes[2].getY(), vertexes[3].getY())));
		BufferedImage image2 = new BufferedImage(x9-x0, y9-y0, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D painter2 = image2.createGraphics();
		painter2.drawImage(inImage, transform, null);
		painter2.dispose();
		boolean bFinished = mPainter.drawImage(image2, 
											   dx, dy, dx+width-1, dy+height-1,
											   sx, sy, sx+width-1, sy+height-1,
											   null );
		assert(bFinished);
	}

	public void drawImage(String strInJpg, int sx, int sy, int dx, int dy, int width, int height) {
		try {
			if (mPainter != null) {
				mPainter.dispose();
				mPainter = null;
			}
			File inFile = new File(strInJpg);
			BufferedImage inImage = ImageIO.read(inFile);
			for (int iX = 0; iX < width; ++iX) {
				for (int iY = 0; iY < height; ++iY) {
					mOutImage.setRGB(iX+dx, iY+dy, inImage.getRGB(iX+sx, iY+sy));
				} /* end for (int iY = ...) */
			} /* end for (int iX = ...) */
		} catch (Throwable ex) {
			System.err.println();
			System.err.println(strInJpg);
			ex.printStackTrace();
		}
		if (mPainter == null) {
			mPainter = mOutImage.createGraphics();
		}
	}

	public void drawImage(String strInJpg, float dx0, float dy0, float t_width, float t_height) {
		Poster16Adrianas.drawPic(mOutImage, strInJpg, 0, 0, 0, 0,
								 (int)(mOutImage.getWidth()*t_width),
								 (int)(mOutImage.getWidth()*dx0),
								 (int)(mOutImage.getHeight()*t_height),
								 (int)(mOutImage.getHeight()*dy0) );
	}

	public void drawPartImage(
			String strInFileName,
			double centerPointX,
			double centerPointY,
			double minWidth,
			double minHeight,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			) {
		try {
			int itW;
			if (tWidth < 1.5) {
				itW = (int)(tWidth*mX5+0.5);
			} else {
				itW = (int)(tWidth+0.5);
			}
			int itX0;
			if (tx0 < 1.0) {
				itX0 = (int)(tx0*mX5+0.5);
			} else {
				itX0 = (int)(tx0+0.5);
			}
			int itH;
			if (tHeight < 1.5) {
				itH = (int)(tHeight*mY5+0.5);
			} else {
				itH = (int)(tHeight+0.5);
			}
			int itY0;
			if (ty0 < 1.0) {
				itY0 = (int)(ty0*mY5+0.5);
			} else {
				itY0 = (int)(ty0+0.5);
			}
			MiRoesDraw.drawPartImage(strInFileName, centerPointX, centerPointY, minWidth, minHeight, rotator,
									 mOutImage, itX0+mX0+mX1, itY0+mY0, itW, itH);
			this.mLastX = itX0+mX0+mX1 + itW;
			this.mLastY = itY0+mY0     + itH;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void centerAndCutImage(String strInJpg, int dx0, int dy0, int t_width, int t_height ) {
		Poster16Adrianas.drawPic(mOutImage, strInJpg, 0, 0, 0, 0, t_width, mX0+dx0, t_height, dy0);
	}

	public void drawImage(String strInJpg, int x0, int y0, int sWid, int sHei,
			  			  float dx0, float dy0, float t_width, float t_height ) {
			Poster16Adrianas.drawPic(mOutImage, strInJpg, x0, y0, sWid, sHei,
									 (int)(mOutImage.getWidth()*t_width),
									 (int)(mOutImage.getWidth()*dx0),
									 (int)(mOutImage.getHeight()*t_height),
									 (int)(mOutImage.getHeight()*dy0) );
	}

	public void drawCalDates() {
		if (Calendar.JANUARY <= nMonth && nMonth <= Calendar.DECEMBER && mX1 > 0) {
			drawCalDates30();
		}
	}
	
	public void drawCalDates30() {
		int y0 = mY0 + getLineHeight();
		int hDayNumberFont = (getLineHeight()-4)*3/4;
		dayNumberFont = new Font("Arial", Font.PLAIN, hDayNumberFont);
		smallerFont = new Font("Arial", Font.PLAIN, (getLineHeight()-4)*2/4);
		mPainter.setFont(dayNumberFont);
		DecimalFormat dayOfMonthFormatter = new DecimalFormat("00");
		/* write week numbers */
		GregorianCalendar cal = new GregorianCalendar(THIS_YEAR, nMonth, 1);
		drawWeekNumber(cal);
		while (cal.get(GregorianCalendar.MONTH) == nMonth) {
			drawWeekNumber(cal);
		}
		/* write Hertha Spiele als Hintergrund in Tagesleiste: */
		cal = new GregorianCalendar(THIS_YEAR, nMonth, 1);
		cal.add(GregorianCalendar.DAY_OF_MONTH, -1);
		cal.add(GregorianCalendar.DAY_OF_MONTH, +1);
		int iD;
		do {
			iD = cal.get(GregorianCalendar.DAY_OF_MONTH);
			drawBackgroundDays(cal, iD);
			cal.add(GregorianCalendar.DAY_OF_MONTH, +1);
		} while (cal.get(GregorianCalendar.MONTH) == nMonth);
		/* write days */
		cal = new GregorianCalendar(THIS_YEAR, nMonth, 1);
		
		SimpleDateFormat monthNameAsTitleFormatter;
		if (FotoKalender.LOCALE_MONTH != null && FotoKalender.LOCALE_MONTH.length() > 0) {
			Locale loc = new Locale(FotoKalender.LOCALE_MONTH);
			monthNameAsTitleFormatter = new SimpleDateFormat("MMMM yyyy", loc);
		} else {
			monthNameAsTitleFormatter = new SimpleDateFormat("MMMM yyyy");
		}
		String strMonthYear = monthNameAsTitleFormatter.format(cal.getTime());
		System.out.println(strMonthYear+" ...");
		drawText(strMonthYear, dayNumberFont, Color.GRAY, mX0, y0);
		while (cal.get(GregorianCalendar.MONTH) == nMonth) {
			iD = cal.get(GregorianCalendar.DAY_OF_MONTH);
			String strDay = dayOfMonthFormatter.format(iD);
			int ix = drawText(strDay, dayNumberFont, mDates.getDateColor(cal, mColBack), mX0, y0+iD*mLineHeight);
			ix += mX0 + getTextWidth(" ", dayNumberFont);
			if (mX01 != ix) {
				if (Math.abs(mX01 - ix) > 2) {
					System.out.println("X-Coord right of day number: Changed from "+ mX01 +" to "+ ix);
				}
				mX01 = ix;
			}
			ArrayList<PersonalDate> events = mDates.getCalEvents(cal);
			int yThisLine = 0;
			for (PersonalDate ev : events) {
				Font font = (ev.getPrio() & PersonalDate.PRIO_BIRTHDAY) != 0
					? dayNumberFont : smallerFont ;
				String strText = ev.getStrText();
				strText = MessageFormat.format(strText, cal.getTime());
				if (strText != null && strText.startsWith("Wingdings:")) try {
					font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Windows\\Fonts\\wingding.ttf"));
					font = font.deriveFont(Font.TRUETYPE_FONT, getLineHeight()-1);
					strText = strText.substring(10);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if (strText != null && strText.length() > 0) {
					if (ev.getPrio() != 0 || ix < 2*mLineHeight+mX0 && yThisLine < 1) {
						int dx = drawText(strText, font, ev.getColor(mColBack, nMonth), ix, y0+iD*mLineHeight+yThisLine);
						ix += dx+mLineHeight;
					}
				}
				if (ix+ix > mX1 && mLinesPerDay > 2) {
					/* Schreibe weiter in der nächsten Zeile. */
					yThisLine += hDayNumberFont;
					ix = mX01;
				}
			}
			if (ix < 2*mLineHeight+mX0 && yThisLine < 1 && mStrDayFormat != null) {
				SimpleDateFormat weekDayFormatter = new SimpleDateFormat(mStrDayFormat);
				strDay = weekDayFormatter.format(cal.getTime());
				drawText(strDay, smallerFont, mDates.getDateColor(cal, mColBack), ix, y0+iD*mLineHeight);
			}
			cal.add(GregorianCalendar.DAY_OF_MONTH, +1);
		}
	}
	
	
	private void drawWeekNumber(GregorianCalendar cal) {
		int iD1 = cal.get(GregorianCalendar.DAY_OF_MONTH);
		int iWN = cal.get(GregorianCalendar.WEEK_OF_YEAR);
		do {
			cal.add(GregorianCalendar.DAY_OF_MONTH, +1);
		} while (iWN == cal.get(GregorianCalendar.WEEK_OF_YEAR) && cal.get(GregorianCalendar.MONTH) == nMonth);
		cal.add(GregorianCalendar.DAY_OF_MONTH, -1);
		int iD7 = cal.get(GregorianCalendar.DAY_OF_MONTH);
		cal.add(GregorianCalendar.DAY_OF_MONTH, +1);
		Font weekNumberFont = new Font("Arial", Font.PLAIN, (mLineHeight)*5*mStrDayFormat.length()/4);
		AffineTransform transformer = new AffineTransform(1, 0, 0, (iD7-iD1+1f)/mStrDayFormat.length(), 0, 0);
		weekNumberFont = weekNumberFont.deriveFont(transformer);
		DecimalFormat numberFormatter = new DecimalFormat("00");
		String str = numberFormatter.format(iWN);
		int nRed = this.mColBack.getRed()-32;
		if (nRed < 0) {
			nRed += 64;
		}
		int nGren = this.mColBack.getGreen()-32;
		if (nGren < 0) {
			nGren += 64;
		}
		Color col = new Color(nRed, nGren, this.mColBack.getBlue());
		int iShadow = (int)(getLineHeight()/8f+0.5f);
		int iY8 = 0;
		if (iD1+2 > iD7) {
			iY8 = (iD1+2-iD7)*iShadow;
		}
		int ix = getX01() + getTextWidth("M", smallerFont);
		int iY7 = mY0+(int)((iD7+1/Math.max(mLinesPerDay, 1f))*mLineHeight);
		MiRoesDraw.drawTextCol(str, weekNumberFont, iShadow, col, mPainter, ix, iY7-iShadow+iY8);
		try {
			Thread.sleep(500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void drawBackgroundDays(GregorianCalendar cal, int iY) {
		ArrayList<PersonalDate> events = mDates.getCalEvents(cal);
		for (PersonalDate ev : events) {
			if (ev.mBackgrounds != null) {
				int ix = getX01() + getTextWidth("So ", smallerFont);
				for (IDaysBackground backg1 : ev.mBackgrounds) {
					ix = backg1.draw(this, cal, ix, iY);
				}
			} /* end if (ev.mBackgrounds != null) */
		} /* end for (PersonalDate ev : events) */
	}

	
	/**
	 * 
	 * @param strText	Text to be printed on the sheet.
	 * @param tx0		Left Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param ty0		Top Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param tWidth    Width of the target area. If you want to fill the whole target area, put here 1.0
	 * @param tHeight   Height of the tareget area. If you want to fill the whole target area, put here 1.0
	 * 			For the four parameters for the target area, you can enter a number between 0 and 1 here. Then the value will be multiplied by the actual size of the target area.
	 * 			Or you can enter the exact value measured in pixels.
	 */
	public void drawSerifText (
			String strText,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			)
	{
		drawText(strText, getDefaultTextCol(), Font.SERIF, 0, tx0, ty0, tWidth, tHeight);
	}
	

	/**
	 * 
	 * @param strText	Text to be printed on the sheet.
	 * @param colorText
	 * @param strFontName
	 * @param iFontStyle
	 * @param tx0		Left Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param ty0		Top Coord of the target area. If you want to fill the whole target area, put here 0.0
	 * @param tWidth    Width of the target area. If you want to fill the whole target area, put here 1.0
	 * @param tHeight   Height of the tareget area. If you want to fill the whole target area, put here 1.0
	 * 			For the four parameters for the target area, you can enter a number between 0 and 1 here. Then the value will be multiplied by the actual size of the target area.
	 * 			Or you can enter the exact value measured in pixels.
	 * @return The width of the whole text measured in pixels
	 */
	public int drawText (
			String strText,
			Color colorText,
			String strFontName,
			int iFontStyle,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			)
	{
		int itW;
		if (tWidth < 1.5) {
			itW = (int)(tWidth*mX5+0.5);
		} else {
			itW = (int)(tWidth+0.5);
		}
		int itX0;
		if (tx0 < 1.0) {
			itX0 = (int)(tx0*mX5+0.5);
		} else {
			itX0 = (int)(tx0+0.5);
		}
		int itH;
		if (tHeight < 1.5) {
			itH = (int)(tHeight*mY5+0.5);
		} else {
			itH = (int)(tHeight+0.5);
		}
		int itY0;
		if (ty0 < 1.0) {
			itY0 = (int)(ty0*mY5+0.5);
		} else {
			itY0 = (int)(ty0+0.5);
		}
		return MiRoesDraw.drawText(strText, colorText, strFontName, iFontStyle, mPainter, itX0+mX0+mX1, itY0+mY0, itW, itH);
	}
	
	public int drawText(String strText, Font font, Object color, int x0, int y0) {
		return MiRoesDraw.drawText(strText, font, color, mPainter, x0, y0);
	}
	
	public void blearVertical(int y0, int x1, int x9) {
		int len = (x9-x1)/2-1;
		int x5 = (x9+x1)/2;
		for (int xD = len; xD >= 0; --xD) {
			mixRGB(x5-xD-1, y0, x5-xD, y0, x5-xD+1, y0);
			mixRGB(x5+xD-1, y0, x5+xD, y0, x5+xD+1, y0);
		}
	}
	
	public void mixRGB(int x1, int y1, int x2, int y2, int x3, int y3) {
		int p1 = (mOutImage.getRGB(x1, y1) & 0xFCFCFCFC) >> 2;
		int p2 = (mOutImage.getRGB(x2, y2) & 0xFEFEFEFE) >> 1;
		int p3 = (mOutImage.getRGB(x3, y3) & 0xFCFCFCFC) >> 2;
		int pm = p1 + p2 + p3;
		int r1 = (int)(Math.random()*4);
		int r2 = r1*(0x1010101);
		mOutImage.setRGB(x2, y2, pm|r2);
	}
	
	
	public static double sum_importances = 0d;
	
	public void drawAmbiLightH(int x0, int x9, int y0, int y9, int defStructSize) {
		try {
			if (mPainter != null) {
				mPainter.dispose();
				mPainter = null;
			}
			Ambilight ambi = new Ambilight();
			ambi.mOutImage = this.mOutImage;
			ambi.mColBack =  this.mColBack;
			ambi.drawAmbiLightH(x0, x9, y0, y9, defStructSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (mPainter == null) {
			mPainter = mOutImage.createGraphics();
		}
	} /* end drawAmbiLightH(int x0, int x9, int y0, int y9, int defStructSize) */
	
	public void writeInDir(String strOutDir) throws IOException {
		int nNumber = (THIS_YEAR - 2000)*100 + nMonth+1;
		writeJpg(strOutDir+ nNumber +".jpg");
	}
	
	public void writeJpg(String strOutJpg) throws IOException {
		File outFile = new File(strOutJpg);
		writeJpg(outFile);
	}
	
	public void writeJpg(File outFile) throws IOException {
		disposePainter();
		outFile.getParentFile().mkdirs();
		ImageIO.write(mOutImage, "jpg", outFile);
		System.out.println(outFile.getAbsolutePath() + " finished.");
		System.out.println("===========================================================");
	}
	
	public void writeExJpg(String strOutJpg) throws IOException {
		if (strOutJpg.endsWith(".jpg") || strOutJpg.endsWith(".Jpg") || strOutJpg.endsWith(".JPG")) {
			File outFile = new File(strOutJpg);
			if (outFile.isDirectory()) {
				writeInDir(strOutJpg);
			} else {
				writeJpg(outFile);
			}
		} else {
			writeInDir(strOutJpg);
		}
	}
	
	public void diag() {
		MiRoesDraw.diagOut(mOutImage);
	}
	
	public void diagOnKey() {
		MiRoesDraw.diagOutOnKey(mOutImage);
	}
	
	public void disposePainter() {
		if (mPainter != null) {
			mPainter.dispose();
			mPainter = null;
		}
	}
	
}
