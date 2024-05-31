package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;



public class PersonalDates {

	public final int THIS_YEAR;
	public static final int MILLIS_PER_DAY = 24*60*60*1000;
	
	@SuppressWarnings("unchecked")
	private ArrayList<PersonalDate> calEvents[] = new ArrayList[366];
	
	public PersonalDates(int nYear) {
		THIS_YEAR = nYear;
		for (int iD = 0; iD < calEvents.length; ++iD) {
			calEvents[iD] = new ArrayList<PersonalDate>(0);
		}
	}
	
	public ArrayList<PersonalDate> getCalEvents (int nYear, int nMonth, int nDay) {
		GregorianCalendar date = new GregorianCalendar(nYear, nMonth, nDay);
		return getCalEvents(date);
	}

	public ArrayList<PersonalDate> getCalEvents (GregorianCalendar date) {
		int iDay = date.get(GregorianCalendar.DAY_OF_YEAR)-1;
		if (0 <= iDay && iDay < calEvents.length) {
			return calEvents[iDay];
		} else {
			return null;
		}
	}
	
	public void addCalEvent01(PersonalDate calEvent) {
		int iDay = calEvent.getDayOfYear();
		if (0 <= iDay && iDay < calEvents.length && calEvent.isThisYear(THIS_YEAR)) {
			if (calEvents[iDay].isEmpty()) {
				addCalEvent(calEvent);
			}
		}
	}

	public void addCalEvent(PersonalDate calEvent) {
		int iDay = calEvent.getDayOfYear();
		if (0 <= iDay && iDay < calEvents.length && calEvent.isThisYear(THIS_YEAR)) {
			
			PersonalDate similarDate = null;
			if (calEvent.getStrText().length() > 14
					&& calEvent.getStrText().substring(0, 13).equals("Schulfrei in ") )
			{
				similarDate = findDate((int)iDay, "Schulfrei in ");
			}
			if (similarDate != null) {
				similarDate.addPrio(calEvent.getPrio());
				similarDate.addStrText(" und " + calEvent.getStrText().substring(12));
			} else {
				calEvents[iDay].add(calEvent);
			}
		} else {
			System.out.println("Kalenderereignis "+calEvent+" wird ignoriert.");
		}
	}

	public void addLowPrioCalEvent(PersonalDate calEvent) {
		int iDay = calEvent.getDayOfYear();
		if (0 <= iDay && iDay < calEvents.length && calEvent.isThisYear(THIS_YEAR)) {
			ArrayList<PersonalDate> calEvts = calEvents[iDay];
			if (calEvts.isEmpty()) {
				calEvts.add(calEvent);
			}
		} else {
			System.out.println("Kalenderereignis "+calEvent+" wird ignoriert.");
		}
	}

	private PersonalDate findDate(int iTime, String strStartText) {
		ArrayList<PersonalDate> cals = calEvents[iTime];
		for (PersonalDate dat : cals) {
			String strText = dat.getStrText(); 
			if (strText.length() >= strStartText.length()
					&& strText.substring(0, strStartText.length()).equals(strStartText) )
			{
				return dat;
			}
		}
		return null;
	}
	
	public int getPrio(GregorianCalendar date) {
		int iWeekDay = date.get(GregorianCalendar.DAY_OF_WEEK);
		int retVal = 0;
		if (iWeekDay == GregorianCalendar.SATURDAY) {
			retVal |= PersonalDate.PRIO_SATURDAY;
		}
		if (iWeekDay == GregorianCalendar.SUNDAY) {
			retVal |= PersonalDate.ALL_HAVE_FREE;
		}
		ArrayList<PersonalDate> calEvs = getCalEvents(date);
		if (calEvs != null) {
			for (PersonalDate calEv : calEvs) {
				retVal |= calEv.getPrio();
			}
		}
		return retVal;
	}

	public int getPrio(ArrayList<PersonalDate> calendarEvents) {
		int retVal = 0;
		for (PersonalDate calEv : calendarEvents) {
			retVal |= calEv.getPrio();
		}
		return retVal;
	}

	public Object getDateColor(GregorianCalendar date, Color colBack) {
		int iPrio = getPrio(date);
		return PersonalDate.getPrioColor(iPrio, colBack, date.get(Calendar.MONTH));
	}
}
