package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.GregorianCalendar;

public class DaysBackgroundSwissFlag extends DaysBackground1 {

	public DaysBackgroundSwissFlag(float highBackgroudIcos, float deltaY) {
		super(highBackgroudIcos, deltaY);
	}

	@Override
	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		try {  // https://en.wikipedia.org/wiki/Flag_of_Switzerland
			Graphics2D g2d = sheet.getPainter();
			int y0 = sheet.getYLine(iY + mDeltaY - mHighBackgroudIcos/Math.sqrt(2)/sheet.getLinesPerDay() + .4f);
			int desW = (int)(mHighBackgroudIcos * sheet.getLineHeight() * Math.sqrt(2) +0.5f);
			int whtWC = (desW*5+4)/8; // total length and high of white cross in the center of the flag
			int wh1WC = (whtWC+1)/3; // width of a a line in the cross
			int xyWC = (desW-whtWC)/2;
	        g2d.setColor(Color.RED);
	        g2d.fillRect(ix9, y0, desW, desW);
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(ix9+xyWC,       y0+xyWC+wh1WC, whtWC, wh1WC);
	        g2d.fillRect(ix9+xyWC+wh1WC, y0+xyWC      , wh1WC, whtWC);

			ix9 += desW;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ix9;
	}

}
