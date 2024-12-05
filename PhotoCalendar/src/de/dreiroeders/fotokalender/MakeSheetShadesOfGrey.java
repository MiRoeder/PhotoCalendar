package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.LineMetrics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Random;

import de.dreiroeders.workingonimages.CalcDefaultPicPos;
import de.dreiroeders.workingonimages.MiRoesDraw;
import de.dreiroeders.workingonimages.MiRoesFont;


public class MakeSheetShadesOfGrey extends Thread {

	public static int normCols(/*in/out:*/ int[] cols) {
		int retVal = 0;
		int[] dif = new int[cols.length];
		for (int iC = 0; iC < cols.length; ++iC) {
			int col1 = cols[iC];
			if (col1 < 0) {
				cols[iC] = 0;
				dif[iC] = col1;
				retVal -= col1;
			} else
		    if (col1 > 0xFF) {
				cols[iC] = 0xFF;
				dif[iC] = col1 - 0xFF;
				retVal += dif[iC];
		    } else {
		    	dif[iC] = 0;
		    }
		}
		for (int iC = 0; iC < cols.length; ++iC) {
			if (dif[iC] != 0) {
				for (int jC = 0; jC < cols.length; ++jC) {
					if (iC != jC) {
						cols[jC] = cols[jC]-dif[iC]/2;
					}
				}
			}
		}
		for (int iC = 0; iC < cols.length; ++iC) {
			cols[iC] = Math.max(0, Math.min(0xFF, cols[iC]));
		}
		return retVal;
	}
	
	public static String toHex(int num) {
		String strRetVal = Integer.toHexString(num);
		if (strRetVal.length() < 2) {
			strRetVal = "0" + strRetVal;
		}
		return strRetVal;
	}
	
	public static MakeSheetShadesOfGrey startMaking(int nNoShades, int nYear, int month, PersonalDates mDates, String strOutDir) {
		MakeSheetShadesOfGrey executer = new MakeSheetShadesOfGrey(nNoShades, nYear, month, mDates, strOutDir);
		executer.start();
		return executer;
	}
	
	private final int NO_Shades;
	private final int THIS_YEAR;
	private final int nMonth;
	private final PersonalDates CAL_Dates;
	private final String StrOUT_DIR;
	private final short mnLastCol;
	private HashSet<Color> mAlreadyUsedColors;
	
	public MakeSheetShadesOfGrey(int nNoShades, int nYear, int month, PersonalDates mDates, String strOutDir) {
		NO_Shades = nNoShades;
		THIS_YEAR = nYear;
		nMonth    = month;
		CAL_Dates = mDates;
		StrOUT_DIR= strOutDir;
		mnLastCol = (short)Math.max(0xC0, Math.min(0xFF, NO_Shades/2+128));
		setName("MakeSheetShadesOfGrey"+nYear+"-"+month);
		mAlreadyUsedColors = new HashSet<Color>(NO_Shades+2);
		mAlreadyUsedColors.add(Color.BLACK);
		mAlreadyUsedColors.add(Color.WHITE);
	}

	@Override
	public void run() {
		System.out.println("Begin of MakeSheetShadesOfGrey for "+ THIS_YEAR +"-"+ nMonth);
		try {
			makeShadesOfGrey();
			System.out.println("End of MakeSheetShadesOfGrey for "+ THIS_YEAR +"-"+ nMonth);
		} catch (Throwable ex) {
			System.err.println("\nProblem when MakingSheetShadesOfGrey for "+ THIS_YEAR +"-"+ nMonth);
			ex.printStackTrace();
		}
	}

	public void makeShadesOfGrey() throws IOException {
		CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, CAL_Dates);
		sheet.prepareImage(NO_Shades < 100 ? 3000 : 4000, Color.LIGHT_GRAY);
		float y2 = 0.16f;
		String strTitle;
		if (NO_Shades == 50) {
			strTitle = "Fifty";
		} else
		if (NO_Shades == 51) {
			strTitle = "Fifty-one";
		} else {
			int d50 = NO_Shades/50;
			if (d50*50 == NO_Shades) {
				strTitle = String.valueOf(d50) + "x Fifty";
			} else {
				strTitle = String.valueOf(NO_Shades);
			}
		}
		sheet.drawText(strTitle +" Shades of Grey", Color.DARK_GRAY, "Times Roman", 0, 0f, 0f, 1f, y2);
		sheet.diagOnKey();
		
		CalcDefaultPicPos calcPos = new CalcDefaultPicPos();
		calcPos.setStegWH(0);
		calcPos.mMainPaintReg = new Rectangle(sheet.getX(0f), sheet.getY(y2), sheet.getUsuableWidth(), sheet.getDY(1f-y2));
		calcPos.mPicRelWidth = 4.5f;
		calcPos.mNoPics = NO_Shades;
		
		short iCol = (short)(0xFF-mnLastCol);
		Color curCol = new Color(iCol, iCol, iCol);

		Random rnd = new Random(nMonth+12*THIS_YEAR);
		Rectangle curRect = new Rectangle();
		
		calcPos.setIter0();
		for (int iY = 0; calcPos.hasNextLine(); ++iY) {
			calcPos.nextLine();
			if ((iY & 1) == 0) {
				while (calcPos.hasNextCol()) {
					calcPos.nextPic(curRect);
					paintAShadeOfGrey(sheet.mPainter, curRect, curCol);
					sheet.diagOnKey();
					curCol = calcNext1Color(rnd, curCol, calcPos.mIPic);
				}
			} else {
				ArrayList<Rectangle> rects = new ArrayList<Rectangle>(calcPos.mNCols);
				while (calcPos.hasNextCol()) {
					curRect = new Rectangle();
					calcPos.nextPic(curRect);
					rects.add(curRect);
				}
				ListIterator<Rectangle> iter = rects.listIterator(rects.size());
				while (iter.hasPrevious()) {
					curRect = iter.previous();
					paintAShadeOfGrey(sheet.mPainter, curRect, curCol);
					sheet.diagOnKey();
					curCol = calcNext1Color(rnd, curCol, calcPos.mIPic);
				}
			}
		}
	
		sheet.drawCalDates();
		sheet.writeInDir(StrOUT_DIR);
    }

	private void paintAShadeOfGrey(Graphics2D painter, Rectangle curRect, Color col1) {
		painter.setColor(col1);
		painter.fillRect(curRect.x, curRect.y, curRect.width, curRect.height);
		MiRoesFont fontColTxt = MiRoesDraw.getFont("#", "Courier", 0, painter, (int)(curRect.getWidth()*3/4), (int)(curRect.getHeight()*3/4));
		painter.setFont(fontColTxt.mFont);
		LineMetrics metr = fontColTxt.mFont.getLineMetrics("#0859BE", painter.getFontRenderContext());
		int wT1 = fontColTxt.mWeight+(int)(metr.getUnderlineThickness()+0.5f);
		int x11 = (curRect.width-wT1*7)/2;
		int y11 = (int)((curRect.height+metr.getAscent()-metr.getDescent()-metr.getLeading())/2+0.5f);
		// Diag: System.out.println("Zeichenbreite: "+ (float)wT1/fontColTxt.mHeight);
		if (col1.getRed() + col1.getGreen() + col1.getBlue() < 0x180) {
			painter.setColor(new Color(col1.getRed()+0x20, col1.getGreen()+0x20, col1.getBlue()+0x20));
		} else {
			painter.setColor(new Color(col1.getRed()-0x20, col1.getGreen()-0x20, col1.getBlue()-0x20));
		}
		painter.drawString("#", curRect.x+x11, curRect.y+y11);
		int[] cols = new int[] {col1.getRed()+0x40, col1.getGreen()-0x20, col1.getBlue()-0x20};
		normCols(cols);
		painter.setColor(new Color(cols[0], cols[1], cols[2]));
		painter.drawString(toHex(col1.getRed()), curRect.x+x11+wT1  , curRect.y+y11);
		cols = new int[] {col1.getRed()-0x20, col1.getGreen()+0x40, col1.getBlue()-0x20};
		normCols(cols);
		painter.setColor(new Color(cols[0], cols[1], cols[2]));
		painter.drawString(toHex(col1.getGreen()), curRect.x+x11+wT1*3, curRect.y+y11);
		cols = new int[] {col1.getRed()-0x20, col1.getGreen()-0x20, col1.getBlue()+0x40};
		normCols(cols);
		painter.setColor(new Color(cols[0], cols[1], cols[2]));
		painter.drawString(toHex(col1.getBlue()), curRect.x+x11+wT1*5, curRect.y+y11);
	}
	
    private Color calcNext1Color(Random rnd, Color col1, int iNo) {
    	int iCols[] = new int[3];
		iCols[0] = calcNext1Color(rnd, col1.getRed(), iNo);
		iCols[1] = calcNext1Color(rnd, col1.getGreen(), iNo);
		iCols[2] = calcNext1Color(rnd, col1.getBlue(), iNo);
		Color retVal = new Color(iCols[0], iCols[1], iCols[2]);
		int iSafeCounter = 0;
		while (mAlreadyUsedColors.contains(retVal) && ++iSafeCounter < 100) {
			int iRnd = rnd.nextInt(6);
			if (iRnd < 3) {
				iCols[iRnd] -= (iSafeCounter/4+1);
			} else {
				iCols[iRnd-3] += (iSafeCounter/4+1);
			}
			normCols(iCols);
			retVal = new Color(iCols[0], iCols[1], iCols[2]);
		}
		mAlreadyUsedColors.add(retVal);
    	return retVal;
	}
	
    private short calcNext1Color(Random randomer, int i1Col, int iNo) {
    	float fMx = (float)((mnLastCol-i1Col)*2)/Math.max(NO_Shades-iNo, 1);
    	int sign = +1;
    	if (fMx < 0) {
    		sign = -1;
    		fMx = -fMx;
    	}
    	int iMx = (int)fMx;
    	float fMx2 = fMx - iMx;
    	if (fMx2 >= randomer.nextFloat()) {
    		++iMx;
    	}
    	int dx = sign*randomer.nextInt(iMx+1);
    	return (short)Math.min(0xFF, i1Col+dx);
    }
}
