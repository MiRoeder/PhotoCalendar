package de.dreiroeders.fotokalender;

import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DaysBackgroundText extends DaysBackground1 {

	public  String			  mBackgroundText = null;
	private PersonalDate      mEvent = null;

	public DaysBackgroundText(float highBackgroudIcos, float deltaY, PersonalDate event,
			String backgroundText) {
		super(highBackgroudIcos, deltaY);
		this.mEvent = event;
		this.mBackgroundText = backgroundText;
	}
	
	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		try {
			Font font = new Font(Font.SERIF, Font.PLAIN, (int)((sheet.getLineHeight()-4)*this.mHighBackgroudIcos+1));
			int y0 = sheet.getYLine(iY+mDeltaY+.5f);
			int dx = sheet.drawText(mBackgroundText, font, mEvent.getColor(sheet.getBackgroundCol(), cal.get(Calendar.MONTH)), iX, y0);
			ix9 = iX+dx;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ix9;		
	}

}
