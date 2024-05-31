package de.dreiroeders.fotokalender;

import java.util.GregorianCalendar;
import java.awt.Color;
import java.awt.Graphics2D;


public class DaysBackgroundJapanFlag extends DaysBackground1 {

	private float mWidth;

	public DaysBackgroundJapanFlag(float highBackgroudIcos, float deltaY, float width) {
		super(highBackgroudIcos, deltaY);
		this.mWidth = width;
	}

	@Override
	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		try {
			Graphics2D backP = sheet.getPainter();
			int y0 = sheet.getYLine(iY + mDeltaY - mHighBackgroudIcos/2/sheet.getLinesPerDay() + .4f);
			int desH1 = (int)(mHighBackgroudIcos * sheet.getLineHeight() +0.5f);
			int desW = (int)(desH1*mWidth+0.5f);
			backP.setColor(Color.WHITE);
			backP.fillRect(ix9, y0, desW, desH1);
			backP.setColor(new Color(188, 0, 45));
			int iRad = Math.min(desH1, desW)*3/10;
			backP.fillOval(ix9+desW/2-iRad, y0+desH1/2-iRad, iRad*2, iRad*2);
			ix9 += desW;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ix9;
	}

}
