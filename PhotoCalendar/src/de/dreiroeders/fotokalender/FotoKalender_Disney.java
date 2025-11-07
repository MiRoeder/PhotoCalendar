package de.dreiroeders.fotokalender;

import de.dreiroeders.io.MiRoeIoUtil;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.IHintsDrawImages;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FotoKalender_Disney extends FotoKalender2 {

    public FotoKalender_Disney(int year) {
        super(year);
    }

    public static void main(String[] args) {
        try {
            Date curTime = new Date();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            Logger.getGlobal().log(Level.INFO, "Procedure " + FotoKalender_Disney.class.getName() + ".main( ) started creating FotoKalender: " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            createEmptyTmpDir();
            makeFamilyCal(0);
            FotoKalenderOpt trgOpt = makeFamilyCal(1);
            if (trgOpt.m_bFreeTmpDirAfterRun) {
                waitForAllThreadsToFinish();
                createEmptyTmpDir();
            }
            int nThreads = Thread.activeCount() - 1;
            if (nThreads != 0) {
                System.out.println("Procedure " + FotoKalender_Disney.class.getName() + ".main( ) finished, but still " + nThreads + " other threads are running at " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            } else {
                System.out.println("Procedure " + FotoKalender_Disney.class.getName() + ".main( ) finished: " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            }
        } catch (Exception ex) {
            MiRoeIoUtil.logException("", ex);
        }
    }

    public static FotoKalenderOpt makeFamilyCal(int nDeltaYear) throws Exception {
        FotoKalenderOpt trgOpt = FotoKalenderOpt.Current(nDeltaYear);
        FotoKalender_Disney mainObj = new FotoKalender_Disney(trgOpt.m_nYear);
        for (int iMonth = -1; iMonth <= Calendar.DECEMBER; ++iMonth) {
            if (trgOpt.bDoIt(iMonth)) {
                mainObj.makeFamilyCal(trgOpt);
                break;
            }
        }
        return trgOpt;
    }

    @SuppressWarnings("ReassignedVariable")
    public void makeFamilyCal(FotoKalenderOpt trgOpt) throws Exception {
        String strOutDir = trgOpt.m_strOutDir;

        m_trgOpt = trgOpt;

        addFamilyEvents();
        addStephansFamilyEvents();
        addBerlinHolidays();
        addVacations(trgOpt.m_bBerlinVacations, false);
        addDaylightSavingTimeChanges();
        addAdvents();
        addBerlinGrueneWoche();
        addSundays4Buying();
        addHouseParty14197();
        if (trgOpt.m_bBundesliga) {
            MyFussballBundesliga.addMyFussballBundesliga(this);
        }
        if (trgOpt.m_bBundesliga2) {
            MyFussballBundesliga2.addMyFussballBundesliga2(this);
        }
        addSoccerIntEvent();
        addOlympiade();
        addSpecialEvents();
        addMuttertag();

        if (FotoKalenderOpt.MONTH1 == Calendar.DECEMBER && trgOpt.bDoIt(Calendar.DECEMBER) && THIS_YEAR == FotoKalenderOpt.YEAR1)
            try {
                MakeSheetFeuerwerke.startMaking(trgOpt.m_nYear, Calendar.DECEMBER, mDates, strOutDir, 1);
                trgOpt.m_bDoIt[Calendar.DECEMBER] = false; // because already created.
            } catch (Exception ex) {
                MiRoeIoUtil.logException("Problem with FotoKalender_Disney 1st sheet", ex);
            }

        for (int nMonth = Calendar.JANUARY; nMonth <= Calendar.DECEMBER; ++nMonth) {
            if (trgOpt.bDoIt(nMonth)) {
                try {
                    var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, nMonth, mDates);
                    Random randomGen = new Random(THIS_YEAR * 12L + nMonth);
                    float flSel = .01f;
                    do {
                        addDisneyParkPics(sheet, null, randomGen, flSel);
                        flSel *= 2f;
                    } while (sheet.getNumberOfImage() <= 0 && flSel < 2f);
                    sheet.startMakingIt(strOutDir);
                    trgOpt.m_bDoIt[nMonth] = false; // because already created.
                } catch (Exception ex) {
                    MiRoeIoUtil.logException("Problem with month " + (nMonth + 1), ex);
                }
            }
        }

        System.out.println("Kalender " + THIS_YEAR + " creating / created in " + strOutDir);
    } /* end of makeFamilyCal(FotoKalenderOpt trgOpt) */

    @SuppressWarnings("ReassignedVariable")
    public void addDisneyParkPics(ICalendarSheetAddImage sheet,
                                  IHintsDrawImages hints,
                                  Random randomGen,
                                  float maxRandomToPaint) {
        final String sDir1 = "C:\\Users\\MiRoe\\Pictures\\S_K\u00F6hler_2025-09\\Disney Photos\\DisneylandParis-id";
        Draw1ImageI img;
        final int nSkipImages = 3;
        int iLastAdded = 0; // if an image is chosen, we skip the next images, because they may be very similar.

        for (int iF = 79051317; iF <= 79051405; ++iF) {
            if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
                img = new Draw1ImageI(sDir1 + iF +".jpg");
                if (img.isOk()) {
                    if (img.getWidth() < img.getHeight()*7/5) {
                        img.setCenterPoint(.5f, .45f);
                    }
                    sheet.addImage(img, hints);
                    iLastAdded = nSkipImages;
                }
            }
        }
    }
}
