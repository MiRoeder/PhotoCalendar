package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.GregorianCalendar;

public class DaysBackgroundVFlag extends DaysBackground1 {

	public Color[] mCols = null;

	public DaysBackgroundVFlag(float highBackgroudIcos, float deltaY,
			Color[] cols) {
		super(highBackgroudIcos, deltaY);
		this.mCols = cols;
	}

	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		try {
			Graphics2D backP = sheet.getPainter();
			int y0 = sheet.getYLine(iY + mDeltaY - mHighBackgroudIcos/2/sheet.getLinesPerDay() + .4f);
			int desH = (int)(mHighBackgroudIcos * sheet.getLineHeight()+0.5f);
			int desW1 = (int)(mHighBackgroudIcos * sheet.getLineHeight()/2f+0.5f);
			for (int iC = 0; iC < mCols.length; ++iC) {
				backP.setColor(mCols[iC]);
				backP.fillRect(ix9, y0, desW1, desH);
				ix9 += desW1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ix9;
	}

}
