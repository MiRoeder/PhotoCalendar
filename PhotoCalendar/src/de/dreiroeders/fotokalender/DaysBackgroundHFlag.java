package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.GregorianCalendar;

public class DaysBackgroundHFlag extends DaysBackground1 {

	private Color[] mCols = null;
	private float mWidth;

	public DaysBackgroundHFlag(float highBackgroudIcos, float deltaY,
			Color[] cols, float width) {
		super(highBackgroudIcos, deltaY);
		this.mCols = cols;
		this.mWidth = width;
	}

	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		try {
			Graphics2D backP = sheet.getPainter();
			int y0 = sheet.getYLine(iY + mDeltaY - mHighBackgroudIcos/2/sheet.getLinesPerDay() + .4f);
			int desH1 = (int)(mHighBackgroudIcos * sheet.getLineHeight() / mCols.length+0.5f);
			int desW = (int)(desH1*mCols.length*mWidth+0.5f);
			for (int iC = 0; iC < mCols.length; ++iC) {
				backP.setColor(mCols[iC]);
				backP.fillRect(ix9, y0+desH1*iC, desW, desH1);
			}
			ix9 += desW;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ix9;
	}

}
