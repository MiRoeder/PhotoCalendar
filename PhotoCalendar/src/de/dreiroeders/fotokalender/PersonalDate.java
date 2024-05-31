package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class PersonalDate {
	
	public static final byte PRIO_BIRTHDAY = 1;
	public static final byte MICHAEL_HAS_FREE = 2;
	public static final byte ADRIANA_HAS_FREE = 4;	// Ferien in Berlin
	public static final byte ALL_HAVE_FREE = 8|ADRIANA_HAS_FREE|MICHAEL_HAS_FREE;	// Sonntags (auch Verkaufsoffene Sonntage) und Feiertage
	public static final byte SUNDAY4BUYING = 16|ADRIANA_HAS_FREE|MICHAEL_HAS_FREE;	// verkaufsoffener Sonntag in Berlin
	public static final byte HALFSUNDAY = 32|ADRIANA_HAS_FREE|MICHAEL_HAS_FREE;	// Heiligabend und Silvester und Fastnachtsdienstag in Bayern
	public static final byte PRIO_SATURDAY = 64|ADRIANA_HAS_FREE|MICHAEL_HAS_FREE;	
	public static final byte PRIO_PUBLIC_BAVARIAN_HOLIDAY = MICHAEL_HAS_FREE;
	public static final byte PRIO_PUBLIC_GERMAN_HOLIDAY = ADRIANA_HAS_FREE|MICHAEL_HAS_FREE|ALL_HAVE_FREE;
	
	public static final Color RED = new Color(224, 32, 32);
	public static final Color BLUE = new Color(32, 32, 224);
	public static final Color GREEN = new Color(36, 216, 36);
	public static final Color CYAN = new Color(32, 177, 177);
	public static final Color ORANGE = new Color(177, 177, 32);
	public static final Color GRAY = Color.GRAY;
	public static final Color DARK_GRAY = Color.DARK_GRAY;
	public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
	public static final Color DARK_RED = new Color(184, 32, 32);
	public static final Color DARK_GREEN = new Color(32, 184, 32);
	private static final BufferedImage sColorsSaturday = new BufferedImage(1, 2, BufferedImage.TYPE_3BYTE_BGR);
	private static final BufferedImage sColorsHalfSunday = new BufferedImage(1, 3, BufferedImage.TYPE_3BYTE_BGR);
	private static final BufferedImage sColorsSunday4Buying = new BufferedImage(1, 4, BufferedImage.TYPE_3BYTE_BGR);
	private static final BufferedImage sColorsFastnachtsDienstag = new BufferedImage(1, 5, BufferedImage.TYPE_3BYTE_BGR);
	
	private GregorianCalendar mDate;
	private String			  nStrText;
	private byte 			  mPrio;
	List<IDaysBackground>     mBackgrounds = null;

	static {
		sColorsSaturday.setRGB(0, 0, GRAY.getRGB());
		sColorsSaturday.setRGB(0, 1, RED.getRGB());
		sColorsHalfSunday.setRGB(0, 0, GRAY.getRGB());
		sColorsHalfSunday.setRGB(0, 1, RED.getRGB());
		sColorsHalfSunday.setRGB(0, 2, RED.getRGB());
		sColorsSunday4Buying.setRGB(0, 0, RED.getRGB());
		sColorsSunday4Buying.setRGB(0, 1, RED.getRGB());
		sColorsSunday4Buying.setRGB(0, 2, GRAY.getRGB());
		sColorsSunday4Buying.setRGB(0, 3, RED.getRGB());
		sColorsFastnachtsDienstag.setRGB(0, 0, GRAY.getRGB());
		sColorsFastnachtsDienstag.setRGB(0, 1, GRAY.getRGB());
		sColorsFastnachtsDienstag.setRGB(0, 2, GRAY.getRGB());
		sColorsFastnachtsDienstag.setRGB(0, 3, BLUE.getRGB());
		sColorsFastnachtsDienstag.setRGB(0, 4, BLUE.getRGB());
	}
	
	public static Object getPrioColor(int iPrio, Color colBack, int nMonth) {
		if ((iPrio & SUNDAY4BUYING) == SUNDAY4BUYING) {
			return sColorsSunday4Buying;
		} else
		if ((iPrio & ALL_HAVE_FREE) == ALL_HAVE_FREE) {
			return RED;
		} else
		if (   (iPrio & (PersonalDate.HALFSUNDAY|PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY)) == (PersonalDate.HALFSUNDAY|PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY)
		    && nMonth < Calendar.DECEMBER ) {
			return sColorsFastnachtsDienstag;
		} else
		if ((iPrio & HALFSUNDAY) == HALFSUNDAY) {
			return sColorsHalfSunday;
		} else
		if ((iPrio & PRIO_SATURDAY) == PRIO_SATURDAY) {
			return sColorsSaturday;
		} else {
			switch (iPrio) {
			case 0:
				return GRAY;
			case PersonalDate.ALL_HAVE_FREE:
				return RED;
			case PersonalDate.ADRIANA_HAS_FREE:
				return GREEN;
			case PersonalDate.MICHAEL_HAS_FREE:
				return BLUE;
			case PersonalDate.ADRIANA_HAS_FREE+PersonalDate.MICHAEL_HAS_FREE:
				return CYAN;
			case PersonalDate.PRIO_BIRTHDAY:
				return colBack.getGreen() > 127 ? DARK_GRAY : LIGHT_GRAY;
			case PersonalDate.PRIO_BIRTHDAY+PersonalDate.ALL_HAVE_FREE:
				return DARK_RED;
			case PersonalDate.PRIO_BIRTHDAY+PersonalDate.ADRIANA_HAS_FREE:
				return DARK_GREEN;
			default:
				System.out.println("Undefinded color for "+iPrio);
				return ORANGE;
			}
		}
	}

	public PersonalDate(String strText, GregorianCalendar date, byte prio) {
		mDate = date;
		nStrText = strText;
		mPrio = prio;
	}
	
	public PersonalDate(String strText, GregorianCalendar date, int prio) {
		mDate = date;
		nStrText = strText;
		mPrio = (byte)prio;
	}
	
	public PersonalDate(String strText, int nDay, int nMonth, int nYear, byte prio) {
		mDate = new GregorianCalendar(nYear, nMonth-1, nDay);
		nStrText = strText;
		mPrio = prio;
	}

	public static PersonalDate create1BitmapsBackground(String strBGText1, File icoFile1, String strBGText2, float high, float deltaY, int nDay, int nMonth, int nYear) {
		PersonalDate retVal = new PersonalDate("{0,date,EE} ", nDay, nMonth, nYear, (byte)0);
		retVal.mBackgrounds = new ArrayList<IDaysBackground>(3);
		DaysBackgroundText txt = new DaysBackgroundText(high, deltaY, retVal, strBGText1);
		retVal.mBackgrounds.add(txt);
		DaysBackgroundImage img = new DaysBackgroundImage(high, deltaY, icoFile1, 1f);
		retVal.mBackgrounds.add(img);
		txt = new DaysBackgroundText(high, deltaY, retVal, strBGText2);
		retVal.mBackgrounds.add(txt);
		return retVal;
	}
	
	public static PersonalDate create2BitmapsBackground(File icoFile1, String strBGText1, File icoFile2, float high, float deltaY, int nDay, int nMonth, int nYear) {
		return create2BitmapsBackground(icoFile1, strBGText1, icoFile2, high, deltaY, new GregorianCalendar(nYear, nMonth-1, nDay));
	}
	
	public static PersonalDate create2BitmapsBackground(File icoFile1, String strBGText1, File icoFile2, float high, float deltaY, GregorianCalendar date) {
		PersonalDate retVal = new PersonalDate("{0,date,EE} ", date, (byte)0);
		retVal.mBackgrounds = new ArrayList<IDaysBackground>(3);
		DaysBackgroundImage img = new DaysBackgroundImage(high, deltaY, icoFile1, 0.7f);
		retVal.mBackgrounds.add(img);
		DaysBackgroundText txt = new DaysBackgroundText(high, deltaY, retVal, strBGText1);
		retVal.mBackgrounds.add(txt);
		img = new DaysBackgroundImage(high, deltaY, icoFile2, 0.7f);
		retVal.mBackgrounds.add(img);
		return retVal;
	}
	
	public static PersonalDate createBirthday(String strText, int nDay, int nMonth, int nYear) {
		PersonalDate retVal = new PersonalDate(strText, nDay, nMonth, nYear, PRIO_BIRTHDAY);
		return retVal;
	}
	
	public static PersonalDate createHoliday(String strText, int nDay, int nMonth, int nYear, byte prio) {
		PersonalDate retVal = new PersonalDate(strText, nDay, nMonth, nYear, prio);
		return retVal;
	}
	
	public static PersonalDate createFixedHoliday(String strText, int nDay, int nMonth, int nYear, byte prio) {
		PersonalDate retVal = new PersonalDate(strText, nDay, nMonth, nYear, prio);
		if (prio == HALFSUNDAY && retVal.mDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			retVal.mPrio = (byte)(retVal.mPrio & (byte)(~HALFSUNDAY) | PRIO_PUBLIC_GERMAN_HOLIDAY);
		}
		return retVal;
	}
	
	public static PersonalDate createDate(String strText, int nDay, int nMonth, int nYear, byte prio) {
		PersonalDate retVal = new PersonalDate(strText, nDay, nMonth, nYear, prio);
		return retVal;
	}
	
	public static PersonalDate createDate(String strText, GregorianCalendar base, int nAddDays, byte prio) {
		GregorianCalendar date = new GregorianCalendar(base.get(GregorianCalendar.YEAR),
													   base.get(GregorianCalendar.MONTH),
													   base.get(GregorianCalendar.DAY_OF_MONTH) );
		date.add(GregorianCalendar.DAY_OF_YEAR, nAddDays);
		PersonalDate retVal = new PersonalDate(strText, date, prio);
		return retVal;
	}
	
	public GregorianCalendar getDate() {
		return mDate;
	}

	public byte getPrio() {
		return mPrio;
	}

	public void addPrio(byte prio) {
		mPrio |= prio;
	}

	public String getStrText() {
		return nStrText;
	}

	public void addStrText(String strAddText) {
		nStrText += strAddText;
	}

	public long getTimeInMillis() {
		return mDate.getTimeInMillis();
	}
	
	public int getDayOfYear() {
		return mDate.get(GregorianCalendar.DAY_OF_YEAR)-1;
	}
	
	public boolean isThisYear(int nYear) {
		return mDate.get(GregorianCalendar.YEAR) == nYear;
	}
	
	public Object getColor(Color colBack, int nMonth) {
		return getPrioColor(mPrio, colBack, nMonth);
	}

	@Override
	public String toString() {
		DateFormat dateFormatter = DateFormat.getDateInstance();
		return nStrText +" "+ dateFormatter.format(mDate.getTime());
	}
}
