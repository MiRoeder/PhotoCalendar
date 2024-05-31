package de.dreiroeders.fotokalender;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MyFussballBundesliga {

	public static void addMyFussballBundesliga(FotoKalender cal) {
		// previous list copied from https://media.dfl.de/sites/2/2022/06/Bundesliga_Spielplan_2022_2023-1.pdf
        // SPIELPLAN
        // BUNDESLIGA
        // SAISON 2023/2024
        // 30.06.2023
        // ©DFL Deutsche Fußball Liga e.V.
        // Sämtliche Rechte an dem Spielplan liegen bei DFL Deutsche Fußball Liga e.V. 
        // Änderungen des Spielplans bleiben vorbehalten.
        // Alle weiteren Informationen zur Saison 2023/2024 unter bundesliga.de
        addTermin(cal, 18, 8,18, 8,2023,"1 1 SV Werder Bremen FC Bayern München");
        addTermin(cal, 19, 8,19, 8,2023,"1 2 Borussia Dortmund 1. FC Köln");
        addTermin(cal, 20, 8,20, 8,2023,"1 3 1. FC Union Berlin 1. FSV Mainz 05");
        addTermin(cal, 19, 8,20, 8,2023,"1 4 Bayer 04 Leverkusen RB Leipzig");
        addTermin(cal, 19, 8,20, 8,2023,"1 5 Eintracht Frankfurt SV Darmstadt 98");
        addTermin(cal, 19, 8,20, 8,2023,"1 6 VfL Wolfsburg 1. FC Heidenheim 1846");
        addTermin(cal, 19, 8,20, 8,2023,"1 7 TSG Hoffenheim Sport-Club Freiburg");
        addTermin(cal, 19, 8,20, 8,2023,"1 8 FC Augsburg Borussia Mönchengladbach");
        addTermin(cal, 19, 8,20, 8,2023,"1 9 VfB Stuttgart VfL Bochum 1848");

        addTermin(cal, 26, 8,26, 8,2023,"2 18 SV Darmstadt 98 1. FC Union Berlin");
        addTermin(cal,  3, 9, 3, 9,2023,"3 20 1. FC Union Berlin RB Leipzig");
        addTermin(cal, 16, 9,16, 9,2023,"4 31 VfL Wolfsburg 1. FC Union Berlin");
        addTermin(cal, 23, 9,23, 9,2023,"5 39 1. FC Union Berlin TSG Hoffenheim");
        addTermin(cal, 30, 9,30, 9,2023,"6 53 1. FC Heidenheim 1846 1. FC Union Berlin");
        addTermin(cal,  7,10, 7,10,2023,"7 56 Borussia Dortmund 1. FC Union Berlin");
        addTermin(cal, 21,10,21,10,2023,"8 65 1. FC Union Berlin VfB Stuttgart");
        addTermin(cal, 27,10,29,10,2023,"9 78 SV Werder Bremen 1. FC Union Berlin");
        addTermin(cal,  3,11, 5,11,2023,"10 83 1. FC Union Berlin Eintracht Frankfurt");
        addTermin(cal, 10,11,12,11,2023,"11 93 Bayer 04 Leverkusen 1. FC Union Berlin");
        addTermin(cal, 24,11,26,11,2023,"12 101 1. FC Union Berlin FC Augsburg");
        addTermin(cal,  1,12, 3,12,2023,"13 109 FC Bayern München 1. FC Union Berlin");
        addTermin(cal,  8,12,10,12,2023,"14 119 1. FC Union Berlin Borussia Mönchengladbach");
        addTermin(cal, 15,12,17,12,2023,"15 133 VfL Bochum 1848 1. FC Union Berlin");
        addTermin(cal, 19,12,20,12,2023,"16 137 1. FC Union Berlin 1. FC Köln");
        addTermin(cal, 12, 1,14, 1,2024,"17 147 Sport-Club Freiburg 1. FC Union Berlin");
        addTermin(cal, 19, 1,21, 1,2024,"18 157 1. FSV Mainz 05 1. FC Union Berlin");
        addTermin(cal, 28, 1,28, 1,2024,"19 164 1. FC Union Berlin SV Darmstadt 98");
        addTermin(cal,  2, 2, 4, 2,2024,"20 173 RB Leipzig 1. FC Union Berlin");
        addTermin(cal,  9, 2,11, 2,2024,"21 182 1. FC Union Berlin VfL Wolfsburg");
        addTermin(cal, 16, 2,18, 2,2024,"22 195 TSG Hoffenheim 1. FC Union Berlin");
        addTermin(cal, 24, 2,24, 2,2024,"23 201 1. FC Union Berlin 1. FC Heidenheim 1846");
        addTermin(cal,  2, 3, 2, 3,2024,"24 208 1. FC Union Berlin Borussia Dortmund");
        addTermin(cal,  8, 3,10, 3,2024,"25 225 VfB Stuttgart 1. FC Union Berlin");
        addTermin(cal, 16, 3,16, 3,2024,"26 227 1. FC Union Berlin SV Werder Bremen");
        addTermin(cal, 30, 3,31, 3,2024,"27 238 Eintracht Frankfurt 1. FC Union Berlin");
        addTermin(cal,  5, 4, 7, 4,2024,"28 245 1. FC Union Berlin Bayer 04 Leverkusen");
        addTermin(cal, 12, 4,14, 4,2024,"29 259 FC Augsburg 1. FC Union Berlin");
        addTermin(cal, 19, 4,21, 4,2024,"30 263 1. FC Union Berlin FC Bayern München");
        addTermin(cal, 26, 4,28, 4,2024,"31 276 Borussia Mönchengladbach 1. FC Union Berlin");
        addTermin(cal,  5, 5, 5, 5,2024,"32 281 1. FC Union Berlin VfL Bochum 1848");
        addTermin(cal, 11, 5,11, 5,2024,"33 294 1. FC Köln 1. FC Union Berlin");
        addTermin(cal, 18, 5,18, 5,2024,"- Sa 15:30 34 299 1. FC Union Berlin Sport-Club Freiburg");
		
		if (cal.THIS_YEAR == 2024) {
			//BUNDESLIGA START am Freitag 23. August 2024
			PersonalDate sp;
			sp = PersonalDate.create2BitmapsBackground(new File("res/FC Bayern München.jpg"), " ... ", new File("res/RB Leipzig.png"), 0.7f, 0f, 24, 8, 2024);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(new File("res/Bayer Leverkusen.png"), " ... ", new File("res/Borussia Mönchengladbach.jpg"), 0.8f, 0f, 23, 8, 2024);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(new File("res/BVB 09 Dortmund.jpg"), "? ... ", new File("res/UnionBerlin.png"), 0.8f, 0.5f, 24, 8, 2024);
			cal.mDates.addCalEvent(sp);
		}
		if (cal.THIS_YEAR == 2023) {
			File unionIco = new File("res/UnionBerlin.png");
			PersonalDate sp;
			sp = PersonalDate.create2BitmapsBackground(unionIco, " : ", new File("res/Sp. Braga.png"), 2f, 0f, 3, 10, 2023);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(unionIco, " : ", new File("res/SSC Neapel.png"), 2f, 0f, 24, 10, 2023);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(unionIco, " : ", new File("res/Real Madrid.png"), 2f, 0f, 12, 12, 2023);
			cal.mDates.addCalEvent(sp);
		}
	}
	

	enum HinRückRunde {
		HinRunde,
		RückRunde
	}


	public static void addTermin(
		FotoKalender cal,
		int nRükDays,
		int nRükLastDay,
		int nRükMonth, /* January = 1, ... December = 12 */
		int nRükYear,
		int nHinDays,
		int nHinLastDay,
		int nHinMonth, /* January = 1, ... December = 12 */
		int nHinYear,
		String str
	) {
		if (nHinYear == cal.THIS_YEAR) {
			addTermin(cal, HinRückRunde.HinRunde, nHinDays, new GregorianCalendar(nHinYear, nHinMonth-1, nHinLastDay), str);
		}
		if (nRükYear == cal.THIS_YEAR) {
			addTermin(cal, HinRückRunde.RückRunde,nRükDays, new GregorianCalendar(nRükYear, nRükMonth-1, nRükLastDay), str);
		}
	}
	

	public static void addTermin(
			FotoKalender cal,
			int n1stDay,
			int n1stMon, /* January = 1, ... December = 12 */
			int nLastDay,
			int nLastMon, /* January = 1, ... December = 12 */
			int nYear,
			String str
		) {
			GregorianCalendar cal1 = new GregorianCalendar(nYear, n1stMon-1, n1stDay);
			GregorianCalendar cal2 = new GregorianCalendar(nYear, nLastMon-1,nLastDay);
			int nDaysPlusMinus = (int)((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / 86400000);
			addTermin(cal, HinRückRunde.HinRunde, nDaysPlusMinus, cal2, str);
		}
		

	public static void addTermin(
		FotoKalender cal,
		HinRückRunde hinRück,
		int nDays,
		GregorianCalendar lastDay,
		String str
	) {
		GregorianCalendar firstDay;
		if (nDays == 0) {
			firstDay = lastDay;
		} else {
			firstDay = (GregorianCalendar)lastDay.clone();
			firstDay.add(Calendar.DAY_OF_MONTH, -nDays);
		}
		System.out.println("Am Bundesligaspieltag "
		                   + firstDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) +" "+ firstDay.get(Calendar.DAY_OF_MONTH) +"."+ (firstDay.get(Calendar.MONTH)+1)
						   + " ... " 
		                   + lastDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())  +" "+  lastDay.get(Calendar.DAY_OF_MONTH) +"."+ ( lastDay.get(Calendar.MONTH)+1) );
		String strNames[] = str.split(" ");
		Club clubs[] = new Club[strNames.length];
		int nClubs = 0;
		for (int iB = 0; iB < strNames.length; ++iB) {
			 Club cb = findClub(strNames[iB]);
			 clubs[iB] = cb;
			 if (cb != null) {
				 ++nClubs;
			 } else {
				 if (strNames[iB].length() > 3) {
					 System.out.println("Ignoriere Name: " + strNames[iB]);
				 }
			 }
		}
		System.out.println("Spielen "+ nClubs +" Vereine");
		Club selHin = null;
		Club selRük = null;
		float fValBestmatch = 0.95f;
		for (int iB = 0; iB < strNames.length-1; ++iB) {
			if (clubs[iB] != null) {
				Club rückClub = null;
				int jB;
				for (jB = iB+1; jB < strNames.length && rückClub == null; ++jB) {
					rückClub = clubs[jB];
				}
				if (rückClub != null) {
					float fCurMatch = hinRück == HinRückRunde.HinRunde ? clubs[iB].mfImpHeim * rückClub.mfImpAusw : clubs[iB].mfImpAusw * rückClub.mfImpHeim;
					if (fCurMatch > fValBestmatch) {
						selHin = hinRück == HinRückRunde.HinRunde ? clubs[iB] : rückClub;
						selRük = hinRück == HinRückRunde.HinRunde ? rückClub : clubs[iB];
						fValBestmatch = fCurMatch;
					}
					iB = jB+1;
				}
			}
		}
		if (selHin != null && selRük != null) {
			System.out.println("Ausgewähltes Spiel: "+ selHin.mStrIcoName +" - "+ selRük.mStrIcoName);
			addTermin(cal, nDays, firstDay, lastDay, selHin.mStrIcoName, selRük.mStrIcoName, fValBestmatch);
		}
	}
		
	
	public static void addTermin(
			FotoKalender cal,
			int nDays,
			GregorianCalendar firstDay,
			GregorianCalendar lastDay,
			String strHeimIcoName,
			String strAuswIcoName,
			float factor
		) {
		int nWeekDay = Calendar.SATURDAY;
		float deltaY;
		if (nDays == 0) {
			deltaY = 0f;
		} else {
			nWeekDay = lastDay.get(Calendar.DAY_OF_WEEK);
			if      (nDays == 1 && nWeekDay == Calendar.WEDNESDAY) deltaY = -4f/9f;  // 4 Spiele am Dienstag
			else if (nDays == 1 && nWeekDay == Calendar.SUNDAY)    deltaY = -6f/8f;  // 6 Spiele am Samstag
			else if (nDays == 2 && nWeekDay == Calendar.SUNDAY)    deltaY = -(2*1+6)/9f; // 1 Spiel am Freitag + 6 Spiele am Samstag
			else if (nDays == 2 && nWeekDay == Calendar.MONDAY)    deltaY = -(2*6+2)/9f; // 6 Spiele am Samstag + 2 Spiele am Sonntag
			else if (nDays == 3 && nWeekDay == Calendar.MONDAY)    deltaY = -(3*1+2*5+2)/9f; // 1 Spiel am Freitag + 5 Spiele am Samstag + 2 Spiele am Sonntag
			else												   deltaY = -nDays/2f;
		}
		File filHeim = new File("res/"+strHeimIcoName);
		File filAusw = new File("res/"+strAuswIcoName);
		if (firstDay.get(Calendar.MONTH) == lastDay.get(Calendar.MONTH)) {
			ArrayList<PersonalDate> alreadyEvents = cal.mDates.getCalEvents(lastDay);
			deltaY += alreadyEvents.size() * 0.45f;
			PersonalDate sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, factor, deltaY, lastDay);
			cal.mDates.addCalEvent(sp);
			for (int iD = 1; iD <= nDays; ++iD) {
				cal.mDates.addCalEvent01(PersonalDate.createDate("{0,date,EE} ", sp.getDate(), -iD, (byte)0));
			}
		} else {
			int n1 = 0;
			int n2 = 0;
			final int lDayIMth = lastDay.get(Calendar.DAY_OF_MONTH);
			for (int iD = 0; iD <= nDays; ++iD) {
				if (iD < lDayIMth) {
					n2 += getNSpielePerDay(nDays, nWeekDay-iD);
				} else {
					n1 += getNSpielePerDay(nDays, nWeekDay-iD);
				}
				assert(n1 > 0 && n2 > 0);
			}
			final int fDayIMth = firstDay.get(Calendar.DAY_OF_MONTH);
			float deltaY1 = Math.min(fDayIMth + nDays + deltaY, 31.25f) - fDayIMth;
			float deltaY2 = Math.max(deltaY, -lDayIMth+0.75f);
			float fac1 = factor * (float)Math.sqrt((float)n1/(n1+n2));
			float fac2 = factor * (float)Math.sqrt((float)n2/(n1+n2));
			PersonalDate sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, fac1, deltaY1, firstDay);
			cal.mDates.addCalEvent(sp);
			sp = PersonalDate.create2BitmapsBackground(filHeim, " : ", filAusw, fac2, deltaY2, lastDay);
			cal.mDates.addCalEvent(sp);
			for (int iD = 1; iD < nDays; ++iD) {
				cal.mDates.addCalEvent01(PersonalDate.createDate("{0,date,EE} ", sp.getDate(), -iD, (byte)0));
			}
		}
	}

	
	public static int getNSpielePerDay(int nDays, int nWeekDay1) {
		int nWeekDay = nWeekDay1;
		if (nWeekDay < 1) {
			nWeekDay += 7;
		}
		if (nDays == 1) {
			switch (nWeekDay) {
			    case Calendar.TUESDAY:  return 4;
				case Calendar.WEDNESDAY:return 5;
				case Calendar.SATURDAY: return 6;
				case Calendar.SUNDAY:   return 2;
			}
		}
		if (nDays == 2) {
			switch (nWeekDay) {
				case Calendar.FRIDAY:   return 1;
				case Calendar.SATURDAY: return 6;
				case Calendar.SUNDAY:   return 2;
			    case Calendar.MONDAY:   return 1;
			}
		}
		if (nDays == 3) {
			switch (nWeekDay) {
				case Calendar.FRIDAY:   return 1;
				case Calendar.SATURDAY: return 5;
				case Calendar.SUNDAY:   return 2;
				case Calendar.MONDAY:   return 1;
			}
		}
		return (9+nDays)/(nDays+1);
	}
	
	
	private static class Club {
		
		public final String mStrIcoName;
		public final float mfImpHeim;
		public final float mfImpAusw;
		
		public Club(String strIcoName, float fImpHeim, float fImpAusw) {
			super();
			this.mStrIcoName = strIcoName;
			this.mfImpHeim = fImpHeim;
			this.mfImpAusw = fImpAusw;
		}
		
		public boolean isClub(String strName) {
			boolean bResult = strName != null && strName.length() > 3;
			if (bResult) {
				int iRes = mStrIcoName.indexOf(strName);
				bResult = iRes >= 0 && iRes+strName.length()+8 >= mStrIcoName.length();
			}
			return bResult; 
		}
	}
		
	
	private static final Club[] sClubs = {
			new Club("1.FC_Nürnberg.png", 0f, 1f),
			new Club("Arminia_Bielefeld.png", 0f, 1f),
			new Club("Bayer Leverkusen.png", 0f, 1f),
			new Club("Borussia Mönchengladbach.jpg", 0f, 1f),
			new Club("BVB 09 Dortmund.jpg", 0f, 1f),
			new Club("Dynamo Dresden.png", .92f, 1f),
			new Club("Eintr Frankfurt.jpg", 0f, 1f),
			new Club("Eintracht Braunschweig.png", 0f, 1f),
			new Club("FC Augsburg.jpg", 0f, 1f),
			new Club("FC Bayern München.jpg", 0f, 1f),
			new Club("FC Heidenheim 1846.png", 0f, 1f),
			new Club("FC Ingolstadt 04.jpg", 0f, 1f),
			new Club("FC Köln.jpg", 0f, 1f),
			new Club("FC Kaiserslautern.png", 0f, 1f),
			new Club("FC Magdeburg.png", .93f, 1f),
			new Club("FC St. Pauli.png", .92f, 1f),
			new Club("Fortuna_Düsseldorf.svg.png", 0f, 1f),
			new Club("FSV Mainz 05.jpg", 0f, 1f),
			new Club("Hamburger SV.jpg", .93f, 1f),
			new Club("Hannover 96.jpg", .94f, 1f),
			new Club("Hansa Rostock.png", .93f, 1f),
			new Club("Hertha BSC.jpg", 2f, 1.1f),
			new Club("Holstein Kiel.png", 0f, 1f),
			new Club("Karlsruher SC.png", .93f, 1f),
			new Club("RB Leipzig.png", 0f, 1f),
			new Club("SC Freiburg.jpg", 0f, 1f),
			new Club("SC Paderborn.png", 0f, 1f),
			new Club("Schalke 04.jpg", 0.91f, 1f),
			new Club("SpVgg_Greuther_Fürth.png", 0f, 1f),
			new Club("SV Darmstadt 98.jpg", 0f, 1f),
			new Club("SV Elversberg.png", 0f, 1f),
			new Club("SV Wehen Wiesbaden.png", 0f, 1f),
			new Club("SV Werder Bremen.jpg", .93f, 1f),
			new Club("TSG 1899 Hoffenheim.jpg", 0f, 1f),
			new Club("UnionBerlin.png", 1.01f, 1f),
			new Club("VfB Stuttgart.png", 0f, 1f),
			new Club("VfL Osnabrück.png", .93f, 1f),
			new Club("VfL Wolfsburg.jpg", .93f, 1f),
			new Club("VfL_Bochum.png", 0f, 1f),
	};
	
	
	private static Club findClub(String strName) {
		Club result = null;
		int iC = 0;
		do {
			Club res1 = sClubs[iC++];
			if (res1.isClub(strName)) {
				result = res1;
			}
		} while (result == null && iC < sClubs.length);
		return result;
	}
}