package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.util.Calendar;
import de.dreiroeders.workingonimages.MiRoesDraw;

public class FotoKalenderOpt {

	public static final int YEAR1 = 2024;
	public static final int MONTH1 = Calendar.JUNE;
	public static final boolean FINAL = true; // use false, if you want to create a single page to check its appearance or a draft version
	
	public              boolean m_bFinal;
	
	public int m_nYear;
	
	public boolean m_bAndreMarlies;
	
	public boolean m_bBerlinVacations;
	
	public boolean m_bBundesliga;
	public boolean m_bBundesliga2;

	public String m_strOutDir;
	
	public boolean[] m_bDoIt; /* Für die 12 Monate eines Jahres und das Titelblatt */
	/* java.util.Calendar :
	 * public static final int JANUARY = 0;
	 * ....
	 * public static final int DECEMBER = 11;
	 */
	 public static final int TITLE_SHEET = 12; 	
	
	public FotoKalenderOpt() {
	}

	public static FotoKalenderOpt Current(int nDeltaYear) {
		int year = nDeltaYear+YEAR1;
		FotoKalenderOpt result = ForMichael(year);
		if (year == YEAR1) {
			if (FINAL) {
				result.init_m_bDoIt(MONTH1, Calendar.DECEMBER);
			} else {
				result.init_m_bDoIt(FINAL);
				//todo or not todo: result.init_m_bDoIt(Calendar.AUGUST, Calendar.AUGUST);
			}
		}
		if (year == YEAR1+1) {
			if (FINAL) {
				result.init_m_bDoIt(Calendar.JANUARY, MONTH1);
			} else {
				result.init_m_bDoIt(FINAL);
				//todo or not todo: result.init_m_bDoIt(Calendar.JANUARY, Calendar.JANUARY);
			}
		}
		MiRoesDraw.bDoDiagOut = !FINAL;
		return result;
	}
	

	public static FotoKalenderOpt ForMichael(int year) {
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
			result.m_strOutDir = "results/" + YEAR1 + "/"+ CalendarSheet.fWeight +"/";
		}
		return result;
	}

	public static FotoKalenderOpt ForConny(int year) {
		var result = ForMichael(year);
		result.m_strOutDir = "results/2023_24/Conny(ohne Union)/"+ CalendarSheet.fWeight +"/";
		result.m_bBundesliga = false;
		return result;
		
	}
	
	public static FotoKalenderOpt ForAdriana(int year) {
		CalendarSheet.fWeight = 1.65f; // myposter.de BIG BLANCO DIN A3 quer or https://www.myposter.de/wandkalender/blanko
		CalendarSheet.sBackBackgroundCol = Color.WHITE;
		//CalendarSheet.fWeight = 1.70f; //for whitewall.com Kalender dezent DIN A3 quer
		//CalendarSheet.fWeight = 2.00f; //1.92f or a little bit more; // fotoquelle.de Vordergrundbild
		//CalendarSheet.fWeight = 1.73f; // posterXXL.de Vordergrundbild
		//attrCalendarSheet.fWeight = CalendarSheet.FWeight_whitewall_A2_hoch;
		//CalendarSheet.fWeight = 28f/13f*26f/22f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE
		//CalendarSheet.fWeight = 1.41f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE ohne Vorlage
		CalendarSheet.sBackBackgroundCol = Color.WHITE;
		FotoKalenderOpt result = new FotoKalenderOpt();
		result.m_bFinal = FINAL;
		result.m_bBerlinVacations = true;
		result.m_bBundesliga = true;
		result.m_bBundesliga2 = true;
		result.m_bAndreMarlies = true;
		if (!result.m_bFinal) {
			String strBackBackgroundCol;
			if (CalendarSheet.sBackBackgroundCol.equals(Color.WHITE)) {
				strBackBackgroundCol = "W";
			} else {
				strBackBackgroundCol = Integer.toHexString(CalendarSheet.sBackBackgroundCol.hashCode());
			}
			result.m_strOutDir = "tmp/2024_Adri/#"+ strBackBackgroundCol +"_"+ CalendarSheet.fWeight +"/";
		} else {
			result.m_strOutDir = "results/2024_01/Adri/"+ CalendarSheet.fWeight +"/";
			if (year == 2024) {
				result.init_m_bDoIt(Calendar.JANUARY, Calendar.DECEMBER);
			}
			if (year == 2025) {
				result.m_bDoIt[Calendar.JANUARY] = FINAL;		
			}
		}
		return result;
	}
	
	public static FotoKalenderOpt ForAndreMarlies() {
		CalendarSheet.fWeight = 2.00f; //1.92f or a little bit more; // fotoquelle.de Vordergrundbild
		//CalendarSheet.fWeight = 1.73f; // posterXXL.de Vordergrundbild
		//attrCalendarSheet.fWeight = CalendarSheet.FWeight_whitewall_A2_hoch;
		//CalendarSheet.fWeight = 28f/13f*26f/22f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE
		//CalendarSheet.fWeight = 1.41f; // https://foto-shop.tchibo.de/shop/composer/callComposer.do?productId=4478&lang=de-DE ohne Vorlage
		CalendarSheet.sBackBackgroundCol = Color.WHITE;
		FotoKalenderOpt result = new FotoKalenderOpt();
		result.m_bFinal = FINAL;
		result.m_bBundesliga = false;
		result.m_bBerlinVacations = true;
		result.m_bAndreMarlies = true;
		if (!result.m_bFinal) {
			String strBackBackgroundCol;
			if (CalendarSheet.sBackBackgroundCol.equals(Color.WHITE)) {
				strBackBackgroundCol = "W";
			} else {
				strBackBackgroundCol = Integer.toHexString(CalendarSheet.sBackBackgroundCol.hashCode());
			}
			result.m_strOutDir = "tmp/AndreMarlies/#"+ strBackBackgroundCol +"_"+ CalendarSheet.fWeight +"/";
		} else {
			result.m_strOutDir = "results/2023/AndreMarlies/"+ CalendarSheet.fWeight +"/";
		}
		return result;
	}
	
	public static FotoKalenderOpt ForRolfChrista(int year) {
		CalendarSheet.fWeight = CalendarSheet.FWeight_whitewall_A2_hoch;
		CalendarSheet.sBackBackgroundCol = Color.WHITE;
		FotoKalenderOpt result = new FotoKalenderOpt();
		result.m_bFinal = FINAL;
		result.m_bBundesliga = false;
		result.m_bBerlinVacations = false;
		result.m_bAndreMarlies = false;
		if (!result.m_bFinal) {
			String strBackBackgroundCol;
			if (CalendarSheet.sBackBackgroundCol.equals(Color.WHITE)) {
				strBackBackgroundCol = "W";
			} else {
				strBackBackgroundCol = Integer.toHexString(CalendarSheet.sBackBackgroundCol.hashCode());
			}
			result.m_strOutDir = "tmp/RolfChrista/#"+ strBackBackgroundCol +"_"+ CalendarSheet.fWeight +"/";
		} else {
			result.m_strOutDir = "results/2023/RolfChrista/"+ CalendarSheet.fWeight +"/";
		}
		result.init_m_bDoIt(result.m_bFinal && year == 2023);
		result.m_bDoIt[Calendar.JANUARY] = result.m_bFinal;
		result.m_bDoIt[Calendar.JULY] = year == 2023;
		result.m_bDoIt[12] = false;
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
