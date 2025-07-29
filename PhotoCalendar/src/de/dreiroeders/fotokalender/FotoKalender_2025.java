package de.dreiroeders.fotokalender;

import de.dreiroeders.io.MiRoeIoUtil;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.IHintsDrawImages;

import java.awt.Rectangle;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class FotoKalender_2025 extends FotoKalender2 {

    public FotoKalender_2025(int year) {
        super(year);
    }

    public static void main(String[] args) {
        try {
            Date curTime = new Date();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            System.out.println("Procedure " + FotoKalender_2025.class.getName() + ".main( ) started creating FotoKalender: " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            createEmptyTmpDir();
            makeFamilyCal(0);
            FotoKalenderOpt trgOpt = makeFamilyCal(1);
            if (trgOpt.m_bFreeTmpDirAfterRun) {
                waitForAllThreadsToFinish();
                createEmptyTmpDir();
            }
            int nThreads = Thread.activeCount() - 1;
            if (nThreads != 0) {
                System.out.println("Procedure " + FotoKalender_2025.class.getName() + ".main( ) finished, but still " + nThreads + " other threads are running at " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            } else {
                System.out.println("Procedure " + FotoKalender_2025.class.getName() + ".main( ) finished: " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            }
        } catch (Exception ex) {
            MiRoeIoUtil.logException("", ex);
        }
    }

    public static FotoKalenderOpt makeFamilyCal(int nDeltaYear) throws Exception {
        FotoKalenderOpt trgOpt = FotoKalenderOpt.Current(nDeltaYear);
        FotoKalender_2025 mainObj = new FotoKalender_2025(trgOpt.m_nYear);
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
        //addOlafsFamilyEvents();
        addBerlinHolidays();
        //addBavarianPublicHolidays();
        addVacations(trgOpt.m_bBerlinVacations, false);
        addDaylightSavingTimeChanges();
        addAdvents();
        addBerlinGrueneWoche();
        addSundays4Buying();
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
        //addMunichWiesn(2);

        if (trgOpt.bDoIt(FotoKalenderOpt.MONTH1) && THIS_YEAR == FotoKalenderOpt.YEAR1) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, FotoKalenderOpt.MONTH1, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Documents\\F\\docs\\2025_Norwegen\\Bordfotograf\\Ziele_korrigiert.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + FotoKalenderOpt.MONTH1);
            add2025_NorwayImages1(sheet, null, randomGen, .03f);
            add2025_NorwayImages2(sheet, null, randomGen, .04f);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[FotoKalenderOpt.MONTH1] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 1st sheet", ex);
        }

        if (trgOpt.bDoIt(Calendar.JANUARY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JANUARY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Documents\\F\\docs\\2025_Norwegen\\Bordfotograf\\Schiff2.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.JANUARY);
            add2025_NorwayImages1(sheet, null, randomGen, .01f);
            add2025_NorwayImages2(sheet, null, randomGen, .01f);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.JANUARY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.JANUARY", ex);
        }

        if (trgOpt.bDoIt(Calendar.JUNE)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JUNE, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Documents\\F\\docs\\2025_Norwegen\\Bordfotograf\\Brücke.jpg");
            img.dRot = 180;
            sheet.setImage1(img);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.JUNE] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.JUNE", ex);
        }

        if (trgOpt.bDoIt(Calendar.SEPTEMBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.SEPTEMBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Documents\\F\\docs\\2025_Norwegen\\Bordfotograf\\Willkommen.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.SEPTEMBER);
            add2025_NorwayImages1(sheet, null, randomGen, .01f);
            add2025_NorwayImages2(sheet, null, randomGen, .01f);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.SEPTEMBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2025 Calendar.SEPTEMBER", ex);
        }

        for (int nMonth = Calendar.JANUARY; nMonth <= Calendar.DECEMBER; ++nMonth) {
            if (trgOpt.bDoIt(nMonth)) {
                try {
                    var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, nMonth, mDates);
                    Random randomGen = new Random(THIS_YEAR * 12L + nMonth);
                    float flSel = .001f;
                    do {
                        add2025_NorwayImages1(sheet, null, randomGen, flSel);
                        add2025_NorwayImages2(sheet, null, randomGen, flSel);
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
    public void add2025_NorwayImages1(ICalendarSheetAddImage sheet,
                                      IHintsDrawImages hints,
                                      Random randomGen,
                                      float maxRandomToPaint) {
        final String sDir1 = "N:\\ConniesS9\\Phone\\DCIM\\Camera\\";
        Draw1ImageI img;
        final int nSkipImages = 5;
        int iLastAdded = 0; // if an image is chosen, we skip the next images, because they may be very similar.

        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI(sDir1 + "20250621_102538.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_120141.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_120206.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_124610.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_171248.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_171256.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_171737.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172734.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172750.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172910.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172916.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172917.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172922.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_172947.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_173008.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_173039.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_173105.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250610_173126.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250611_154242.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250611_154307.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250611_154431.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250611_154432.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250611_154510.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250611_154545.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_073406.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_073411.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_073415.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_081114.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_081305.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_081310.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_094701.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_101017.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_101108.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_101113.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_101337.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_101415.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_101421.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102454.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102458.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102502.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102725.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102733.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102742.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102759.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102903.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_102914.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103640.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103647.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103652.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103653.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103724.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103852.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103858.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103919.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103937.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_103948.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_104110.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_104503.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_104509.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_104522.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_104526.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_104624.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105130.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105133.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105157.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105202.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105221.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105326.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105328.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105337.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105359.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105409.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_105414.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111008.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111023.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111128.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111133.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111151.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111152.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111551.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_111745.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_114648.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_114717.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_114720.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_114724.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_120647.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_120648.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_121136.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_121147.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_122119.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250612_123851.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_055134.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_055150.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_055159.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_081727.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_081758.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_081802.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_082532.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083006.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083012.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083108.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083117.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083126.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083133.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083302.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083553.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083629.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083751.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083758.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083930.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083941.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_083946.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084611.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084623.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084631.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084651.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084658.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084752.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084818.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084913.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084926.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_084940.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090432.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090512.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090554.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090601.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090648.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090658.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_090704.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091133.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091147.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091150.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091204.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091227.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091239.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091246.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091335.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091354.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091508.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091914.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091926.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_091927.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092019.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092120.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092156.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092205.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092242.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092318.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092446.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092520.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092530.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092558.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092616.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092656.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092724.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092828.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_092836.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_093327.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_093351.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_093414.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_093905.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_093945.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_094110.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_100147.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_100207.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_100903.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_100906.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_100949.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_100955.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_101029.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_101042.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_101044.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_101150.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_102426.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_102432.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_102507.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104042.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104220.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104246.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104316.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104546.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104547.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104613.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104615.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104706.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104812.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104836.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104901.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104902.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_104948.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_105006.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_113354.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_113442.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_113456.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_113552.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_113604.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_113611.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_114320.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_114327.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_114331.jpg");
            img.dRot = 90;
            img.setCenterPoint(.4f, .5f);
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_120457.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_120826.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_123335.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_124735.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_124858.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125105.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125116.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125336.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125737.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125821.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125844.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125846.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125906.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_125910.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_130404.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_130408.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_130409.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_130550.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_132827.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_135916.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_140011.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_140049.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_145940.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_153652.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_153658.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_155021.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_155024.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_155100.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_155144.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_155158.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_231500.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_231529.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_231543.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_231557.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_231601.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250613_231640.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_080416.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_080421.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_093144.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_093238.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_093736.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_094114.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_094202.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_094401.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_094534.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_094948.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_094959.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_095222.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_095226.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_095228.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_095232.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_095256.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_101331.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_101334.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_103955.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104100.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104213.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104341.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104436.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104523.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104922.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_104955.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_132731.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_132740.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_164707.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_164933.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_164938.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_164955.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_165017.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_165808.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_165815.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_170402.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_170742.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_170902.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250614_172747.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111722.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111727.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111728.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111729.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111731.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111732.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111733.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111734.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_111807.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_113640.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_113649.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_114020.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_114026.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_120325.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_120355.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_120500.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_120505.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_120512.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_120640.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_224322.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_224333.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250615_224337.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_001414.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_001423.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_001450.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_001455.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_001508.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_081823.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_081834.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_081840.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_081903.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_083028.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_083242.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_083350.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_084806.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_084811.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_084813.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_084903.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_084926.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_085046.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_085107.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_085417.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_085424.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_085921.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090004.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090217.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090226.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090448.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090653.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090716.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090722.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090812.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090836.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090840.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090846.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_090847.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_091156.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_091319.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_091325.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_091443.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_091620.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092351.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092353.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092358.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092402.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092707.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092718.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092726.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092731.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092750.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092759.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092846.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092856.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092904.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092907.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_092952.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_093340.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_094106.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_094108.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_095623.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_095743.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_095756.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_095848.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_095938.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100007.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100055.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100101.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100451.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100514.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100619.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100814.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_100821.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_101920.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_101928.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_101935.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_101947.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102058.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102118.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102121.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102203.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102215.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102236.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102242.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102246.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102402.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102518.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102737.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_102847.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_103426.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_103909.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_103958.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104601.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104619.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104622.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104632.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104712.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104729.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104738.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104750.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104810.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104838.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104844.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104857.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104908.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104931.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_104945.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105002.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105006.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105023.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105031.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105103.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105137.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105144.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105211.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105434.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105439.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105446.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105525.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105534.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105612.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_105740.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110018.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110044.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110052.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110107.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110111.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110126.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110138.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110203.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110327.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110400.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110405.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110432.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110702.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_110704.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114232.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114236.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114304.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114305.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114434.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114452.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114523.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114653.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114703.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114707.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_114900.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_123436.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_123441.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_174123.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_174129.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250616_174141.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_005634.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_005708.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_005836.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_005918.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010031.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010046.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010053.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010159.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010216.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010227.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010238.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010240.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010252.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010302.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010321.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010325.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010341.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010353.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010403.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010408.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010411.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010456.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010500.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010505.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010618.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010630.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_010643.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_164448.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_164451.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_164454.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_165410.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_165552.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_165802.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_165812.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_165841.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170035.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170042.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170151.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170215.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170225.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170711.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170721.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_170934.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171032.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171239.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171246.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171253.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171304.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171308.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171356.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171406.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_171624.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_205640.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_205736.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_205753.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_205857.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_210115.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_210547.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_212906.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_212924.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_213140.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_213337.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_213341.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_214156.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_214534.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_214640.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_214937.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215007.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215037.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215216.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215543.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215552.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215600.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215607.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215639.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215651.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215654.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215656.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215658.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215700.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215703.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_215705.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_220242.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_220907.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_220921.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_220929.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_220931.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_221153.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_221235.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_221238.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_221246.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_221249.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_224623.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_234131.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_234143.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_234209.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_234228.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_234642.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250617_234652.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_101711.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_135439.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_154448.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_154511.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_154632.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_155244.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_155306.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_155708.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_155732.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_155929.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_160116.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_160212.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_160431.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_160442.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_160546.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_161740.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_161751.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_161939.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_162005.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_162127.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_162344.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_162403.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_162953.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_163103.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_163338.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_163414.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_163907.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_165931.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170040.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170144.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170148.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170157.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170445.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170448.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170612.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170842.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170856.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_170940.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_171107.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_171300.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_175344.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_175402.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_180428.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_180554.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_180614.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_181832.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_181837.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_181850.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250618_183000.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_030055.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_081318.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_081328.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_082544.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_082545.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_082556.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_082612.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_091454.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_092155.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_125636.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_125646.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_171903.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_172401.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_172438.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_172556.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_172816.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_172907.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173014.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173033.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173110.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173121.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173137.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173149.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173201.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173208.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173329.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250619_173458.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_123318.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_123354.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_123610.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_123658.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_133235.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_133421.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_133523.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_133619.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_133635.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_134655.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_135431.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_135448.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_135649.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_140632.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_140814.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_141528.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_141925.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_143503.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_143646.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_143846.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_143935.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_144204.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_144216.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_144325.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_144440.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250620_144858.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_090754.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_091332.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_091502.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_091540.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_091615.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_091621.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_091902.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_092017.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_092039.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_092107.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_092127.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_092216.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_092309.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_093254.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_093315.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_093540.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_094105.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_102635.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_103052.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_103316.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_103502.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_104551.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_110417.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_112817.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_112824.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_140031.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_140046.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_140245.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_140454.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_140516.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_213424.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_213429.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250621_213508.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_105644.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_111431.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_111435.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_111444.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_111655.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_111804.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_112258.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_112516.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_112624.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_114400.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_120414.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_121250.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_121810.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_121818.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_121828.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122336.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122347.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122427.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122503.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122606.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122650.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122726.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_122849.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_123245.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_123310.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_125202.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_125209.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_125440.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_125810.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_130856.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250622_154230.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250623_105610.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250623_105912.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250624_084121.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250624_084122.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250624_084123.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250624_084131.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(sDir1 + "20250624_121744.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
    } /* end  add2025_NorwayImages1(CalendarSheetCenterImage sheet, Random randomGen, float maxRandomToPaint) */

    @SuppressWarnings("ReassignedVariable")
    public void add2025_NorwayImages2(ICalendarSheetAddImage sheet,
                                      IHintsDrawImages hints,
                                      Random randomGen,
                                      float maxRandomToPaint) {
        final String sDir1 = "N:\\ConniesS9\\Phone\\DCIM\\Camera\\";
        Draw1ImageI img;
        final int nSkipImages = 5;
        int iLastAdded = 0; // if an image is chosen, we skip the next images, because they may be very similar.

        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI(inDirS23 + "20250622_161627.jpg.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250622_161739.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250622_161806.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250622_161852.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_092538.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_092541.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_092542.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_092546.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_124056.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_124105.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_171450.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_171500.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_171902.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250623_171908.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072611(0).jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072611.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072614.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072617.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072619.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072622.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072624.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072626.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072627.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072629(0).jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_072629.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_075144.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080235.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080237.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080243.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080743.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080745.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080747.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080748.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080749.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI(inDirS23 + "20250624_080751.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
    } /* end of add2025_NorwayImages2(....) */


}

