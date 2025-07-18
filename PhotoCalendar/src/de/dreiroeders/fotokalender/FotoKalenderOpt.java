package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import de.dreiroeders.workingonimages.MiRoesDraw;

public class FotoKalenderOpt {

	public static int YEAR1 = 0;
	public static int MONTH1 = Calendar.JANUARY;
	public static final boolean FINAL = true; // use false, if you want to create a single page to check its appearance or a draft version
	
	public              boolean m_bFinal;
	
	public int m_nYear;
	
	public boolean m_bAndreMarlies;
	
	public boolean m_bBerlinVacations;
	
	public boolean m_bBundesliga;
	public boolean m_bBundesliga2;

	public String m_strOutDir;
	
	public boolean m_bFreeTmpDirAfterRun;
	
	public boolean[] m_bDoIt; /* F�r die 12 Monate eines Jahres und das Titelblatt */
	/* java.util.Calendar :
	 * public static final int JANUARY = 0;
	 * ....
	 * public static final int DECEMBER = 11;
	 */
	public static final int TITLE_SHEET = 12;

	public static void initYEAR1() {
		if (YEAR1 == 0) {
			GregorianCalendar startDate = new GregorianCalendar();
			startDate.add(Calendar.MONTH, 1);
			YEAR1 = startDate.get(Calendar.YEAR);
			MONTH1 = startDate.get(Calendar.MONTH);
		}
	}

	public FotoKalenderOpt() {
		initYEAR1();
	}

	public static FotoKalenderOpt Current(int nDeltaYear) {
		initYEAR1();
		int year = nDeltaYear+YEAR1;
		FotoKalenderOpt result = forMichael(year);
		if (!FINAL) {
			CalendarSheet.maxWidth = 3000; // to save space and time for draft versions
		}
		if (year == YEAR1) {
			if (FINAL) {
				result.init_m_bDoIt(MONTH1, Calendar.DECEMBER);
			} else {
				result.init_m_bDoIt(FINAL);
				//todo or not todo: result.init_m_bDoIt(Calendar.AUGUST, Calendar.AUGUST);
				//result.m_bDoIt[Calendar.DECEMBER] = true;
			}
		}
		if (year == YEAR1+1) {
			if (FINAL) {
				result.init_m_bDoIt(Calendar.JANUARY, MONTH1);
			} else {
				result.init_m_bDoIt(FINAL);
				//todo or not todo:	result.init_m_bDoIt(Calendar.FEBRUARY, Calendar.FEBRUARY);
				result.m_bDoIt[Calendar.JANUARY] = true;
			}
		}
		MiRoesDraw.bDoDiagOut = !FINAL;
		return result;
	}
	

	private static FotoKalenderOpt forMichael(int year) {
		CalendarSheet.fWeight = 1.65f; // myposter.de BIG BLANCO DIN A3 quer or https://www.myposter.de/wandkalender/blanko
		CalendarSheet.sBackBackgroundCol = Color.WHITE;
		//CalendarSheet.fWeight = 1.75f; //for whitewall.com Kalender dezent DIN A3 quer
		//CalendarSheet.fWeight = 1.92f; //1.92f ... 2.00f ; // fotoquelle.de Vordergrundbild
		//CalendarSheet.fWeight = 1.73f; // posterXXL.de Vordergrundbild
		//CalendarSheet.fWeight = CalendarSheet.FWeight_whitewall_A2_hoch;
		//CalendarSheet.fWeight = 28f/13f*26f/22f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE
		//CalendarSheet.fWeight = 1.41f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE ohne Vorlage
		//CalendarSheet.fWeight = 2.5f; // Titelbild for Facebook
		//CalendarSheet.sBackBackgroundCol = Color.WHITE;
		FotoKalenderOpt result = new FotoKalenderOpt();
		result.m_nYear = year;
		result.m_bFinal = FINAL;
		result.m_bBerlinVacations = true;
		result.m_bBundesliga = true;
		result.m_bBundesliga2 = true;
		result.m_bAndreMarlies = false;
		result.m_bFreeTmpDirAfterRun = result.m_bFinal;
		if (!result.m_bFinal) {
			String strBackBackgroundCol;
			if (CalendarSheet.sBackBackgroundCol == null) {
				strBackBackgroundCol = "N";
			} else if (CalendarSheet.sBackBackgroundCol.equals(Color.WHITE)) {
				strBackBackgroundCol = "W";
			} else {
				strBackBackgroundCol = Integer.toHexString(CalendarSheet.sBackBackgroundCol.hashCode());
			}
			result.m_strOutDir = "tmp/" + YEAR1 + "_Public/#"+ strBackBackgroundCol +"_"+ CalendarSheet.fWeight +"/";
			result.init_m_bDoIt(FINAL);
		} else {
			result.m_strOutDir = "results/" + YEAR1 +"-"+ (MONTH1+1) +"/"+ CalendarSheet.fWeight +"/";
		}
		return result;
	}

	private static FotoKalenderOpt forConny(int year) {
		var result = forMichael(year);
		result.m_strOutDir = "results/2023_24/Conny(ohne Union)/"+ CalendarSheet.fWeight +"/";
		result.m_bBundesliga = false;
		return result;
		
	}
	
	private static FotoKalenderOpt forAdriana(int year) {
		CalendarSheet.fWeight = 1.65f; // myposter.de BIG BLANCO DIN A3 quer or https://www.myposter.de/wandkalender/blanko
		CalendarSheet.sBackBackgroundCol = Color.WHITE;
		//CalendarSheet.fWeight = 1.75f; //for whitewall.com Kalender dezent DIN A3 quer
		//CalendarSheet.fWeight = 1.92f; //1.92f ... 2.00f ; // fotoquelle.de Vordergrundbild
		//CalendarSheet.fWeight = 1.73f; // posterXXL.de Vordergrundbild
		//CalendarSheet.fWeight = CalendarSheet.FWeight_whitewall_A2_hoch;
		//CalendarSheet.fWeight = 28f/13f*26f/22f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE
		//CalendarSheet.fWeight = 1.41f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE ohne Vorlage
		//CalendarSheet.fWeight = 2.5f; // Titelbild for Facebook
		//CalendarSheet.sBackBackgroundCol = Color.WHITE;
		FotoKalenderOpt result = new FotoKalenderOpt();
		result.m_nYear = year;
		result.m_bFinal = FINAL;
		result.m_bBerlinVacations = true;
		result.m_bBundesliga = true;
		result.m_bBundesliga2 = true;
		result.m_bAndreMarlies = false;
		result.m_bFreeTmpDirAfterRun = result.m_bFinal;
		if (!result.m_bFinal) {
			String strBackBackgroundCol;
			if (CalendarSheet.sBackBackgroundCol == null) {
				strBackBackgroundCol = "N";
			} else if (CalendarSheet.sBackBackgroundCol.equals(Color.WHITE)) {
				strBackBackgroundCol = "W";
			} else {
				strBackBackgroundCol = Integer.toHexString(CalendarSheet.sBackBackgroundCol.hashCode());
			}
			result.m_strOutDir = "tmp/Pub_Adr_" + YEAR1 +"-"+ (MONTH1+1) + "/#"+ strBackBackgroundCol +"_"+ CalendarSheet.fWeight +"/";
			result.init_m_bDoIt(FINAL);
		} else {
			result.m_strOutDir = "results/Adr_" + YEAR1 +"-"+ (MONTH1+1) +"/"+ CalendarSheet.fWeight +"/";
		}
		return result;
	}
	
	private void init_m_bDoIt(boolean bInitVal) {
		m_bDoIt = new boolean[13];
		for (int iM = 0; iM < 13; ++iM) {
			m_bDoIt[iM] = bInitVal;
		}
	}
	
	private void init_m_bDoIt(int nMonth1, int nMonth2) {
		m_bDoIt = new boolean[13];
		for (int iM = 0; iM < 12; ++iM) {
			m_bDoIt[iM] = nMonth1 <= iM && iM <= nMonth2;
		}
		m_bDoIt[12] = false;
	}
	
	public boolean bDoIt(int month) {
		if (0 <= month && month < 12) {
			return m_bDoIt[month];
		} else {
			return m_bDoIt[12];
		}
	}	
	
}
