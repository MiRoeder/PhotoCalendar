package de.dreiroeders.fotokalender;

import de.dreiroeders.io.MiRoeIoUtil;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.IHintsDrawImages;

import java.awt.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FotoKalender_Disney extends FotoKalender2 {

    final String sDir1 = "C:\\Users\\MiRoe\\Pictures\\S_K\u00F6hler_2025-09\\Disney Photos\\DisneylandParis-id";

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

        if (trgOpt.bDoIt(Calendar.JANUARY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JANUARY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051385.jpg");
            img.setCenterPoint(.51f, .45f);
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.JANUARY);
            addDisneyParkPics(sheet, null, randomGen, .05f, 4);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.JANUARY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.JANUARY", ex);
        }

        if (trgOpt.bDoIt(Calendar.FEBRUARY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.FEBRUARY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051375.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.FEBRUARY);
            addDisneyParkPics(sheet, null, randomGen, .4f, 1);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.FEBRUARY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.FEBRUARY", ex);
        }

        if (trgOpt.bDoIt(Calendar.APRIL)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.APRIL, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051331.jpg");
            img.setSourceBounds(new Rectangle(1100, 200, 4300, 4200));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.APRIL);
            addDisneyParkPics(sheet, null, randomGen, .04f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.APRIL] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.APRIL", ex);
        }

        if (trgOpt.bDoIt(Calendar.MAY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.MAY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051319.jpg");
            img.setSourceBounds(new Rectangle(1500, 250, 3800, 3900));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.MAY);
            addDisneyParkPics(sheet, null, randomGen, .05f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.MAY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.MAY", ex);
        }

        if (trgOpt.bDoIt(Calendar.JUNE)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JUNE, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051349.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.JUNE);
            addDisneyParkPics(sheet, null, randomGen, .04f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.JUNE] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.JUNE", ex);
        }

        if (trgOpt.bDoIt(Calendar.JULY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JULY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051374.jpg");
            int wid = img.getWidth();
            int hei = img.getHeight();
            img.setSourceBounds(new Rectangle(wid/8, hei/15, wid*6/8, hei*9/10));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.JULY);
            addDisneyParkPics(sheet, null, randomGen, .14f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.JULY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.JULY", ex);
        }

        if (trgOpt.bDoIt(Calendar.SEPTEMBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.SEPTEMBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051391.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.SEPTEMBER + 12000);
            addDisneyParkPics(sheet, null, randomGen, .06f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.SEPTEMBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.SEPTEMBER", ex);
        }

        if (trgOpt.bDoIt(Calendar.OCTOBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.OCTOBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051320.jpg");
            int wid = img.getWidth();
            int hei = img.getHeight();
            img.setSourceBounds(new Rectangle(wid/20, hei/20, wid*9/10, hei*17/20));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.OCTOBER + 1200);
            addDisneyParkPics(sheet, null, randomGen, .05f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.OCTOBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.OCTOBER", ex);
        }

        if (trgOpt.bDoIt(Calendar.DECEMBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.DECEMBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI(sDir1 + "79051402.jpg");
            img.setSourceBounds(new Rectangle(300, 400, 4000, 5700));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.DECEMBER);
            addDisneyParkPics(sheet, null, randomGen, .1f, 3);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.DECEMBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.DECEMBER", ex);
        }

        for (int nMonth = Calendar.JANUARY; nMonth <= Calendar.DECEMBER; ++nMonth) {
            if (trgOpt.bDoIt(nMonth)) {
                try {
                    var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, nMonth, mDates);
                    Random randomGen = new Random(THIS_YEAR * 12L + nMonth);
                    float flSel = .01f;
                    do {
                        addDisneyParkPics(sheet, null, randomGen, flSel, 3);
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
                                  float maxRandomToPaint, int nSkipImages) {
        Draw1ImageI img;
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
