package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.GregorianCalendar;

public class DaysBackgroundScottishFlag extends DaysBackground1 {

	public DaysBackgroundScottishFlag(float highBackgroudIcos, float deltaY) {
		super(highBackgroudIcos, deltaY);
	}

	@Override
	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		try {  // https://en.wikipedia.org/wiki/Flag_of_Scotland
			Graphics2D backP = sheet.getPainter();
			int y0 = sheet.getYLine(iY + mDeltaY - mHighBackgroudIcos/2/sheet.getLinesPerDay() + .4f);
			int desH1 = (int)(mHighBackgroudIcos * sheet.getLineHeight() +0.5f);
			int desW = (desH1*5+2)/3;
			backP.setColor(new Color(0, 0x5E, 0xB8));
			backP.fillRect(ix9, y0, desW, desH1);
			backP.setColor(Color.WHITE);
			int wLine = (desW+4)/8;
			for (int xLine = 0; xLine <= wLine; ++xLine) {
				int yLine = (xLine*desH1+desW/2)/desW;
				backP.drawLine(ix9+xLine, y0,             ix9+desW,       y0+desH1-yLine);
				backP.drawLine(ix9      , y0      +yLine, ix9+desW-xLine, y0+desH1);
				backP.drawLine(ix9+xLine, y0+desH1,       ix9+desW,       y0      +yLine);
				backP.drawLine(ix9      , y0+desH1-yLine, ix9+desW-xLine, y0);
			}
			ix9 += desW;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ix9;
	}

}
