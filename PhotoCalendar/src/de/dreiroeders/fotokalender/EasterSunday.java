package de.dreiroeders.fotokalender;

import java.util.GregorianCalendar;

public class EasterSunday extends GregorianCalendar {

	private static final long serialVersionUID = -6904488540885137357L;

	public static GregorianCalendar createEasterSunday(int year) {
		/* copied from  http://www.ptb.de/de/org/4/44/441/oste.htm : */
		int X = year;
		int K = X/100;
		int M = 15 + ((3*K+3)/4) - ((8*K+13)/25);
		int S = 2 - ((3*K+3)/4); 
		int A = X % 19;
		int D = (19*A+M) % 30;
		int R = D/29 + (D/28 - D/29) * (A/11); 
		int OG = 21 + D - R;
		int SZ = 7 - (X+ (X/4) +S) % 7;
		int OE = 7 - (OG-SZ) % 7; 
		int OS = OG + OE; 
		
		GregorianCalendar retVal = new GregorianCalendar(year, GregorianCalendar.MARCH, 1);
		retVal.add(GregorianCalendar.DAY_OF_MONTH, OS-1);
		return retVal;
	}
}
