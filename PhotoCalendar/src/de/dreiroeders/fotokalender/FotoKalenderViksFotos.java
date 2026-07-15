package de.dreiroeders.fotokalender;

import de.dreiroeders.io.MiRoeIoUtil;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.IHintsDrawImages;
import de.dreiroeders.workingonimages.SourceImage;

import java.awt.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class FotoKalenderViksFotos extends FotoKalender1 {

    public FotoKalenderViksFotos(int year) {
        super(year);
    }

    public static void main(String[] args) {
        try {
            Date curTime = new Date();
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            System.out.println("Procedure " + FotoKalenderViksFotos.class.getName() + ".main( ) started creating FotoKalender: " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            createEmptyTmpDir();
            makeFamilyCal(0);
            FotoKalenderOpt trgOpt = makeFamilyCal(1);
            if (trgOpt.m_bFreeTmpDirAfterRun) {
                waitForAllThreadsToFinish();
                createEmptyTmpDir();
            }
            int nThreads = Thread.activeCount() - 1;
            if (nThreads != 0) {
                System.out.println("Procedure " + FotoKalenderViksFotos.class.getName() + ".main( ) finished, but still " + nThreads + " other threads are running at " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            } else {
                System.out.println("Procedure " + FotoKalenderViksFotos.class.getName() + ".main( ) finished: " + dateFormatter.format(curTime) + " " + curTime.getTime() + " ms.");
            }
        } catch (Exception ex) {
            MiRoeIoUtil.logException("", ex);
        }
    }

    public static FotoKalenderOpt makeFamilyCal(int nDeltaYear) throws Exception {
        FotoKalenderOpt trgOpt = FotoKalenderOpt.Current(nDeltaYear);
        FotoKalenderViksFotos mainObj = new FotoKalenderViksFotos(trgOpt.m_nYear);
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

        if (trgOpt.bDoIt(Calendar.FEBRUARY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.FEBRUARY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155258.jpg");
            int wid = img.getWidth();
            int hei = img.getHeight();
            img.setSourceBounds(new Rectangle(wid/7, 0, wid*6/7-1, hei-1));
            img.dRot = 90;
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.FEBRUARY);
            add_Viks_Images1(sheet, null, randomGen, 0.006f, 5);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.FEBRUARY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.FEBRUARY", ex);
        }

        if (trgOpt.bDoIt(Calendar.MARCH) && THIS_YEAR == FotoKalenderOpt.YEAR1) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.MARCH, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\S_K\u00F6hler_2025-09\\Fotografie mit Vik\\IMG_8178.JPG");
            img.dRot = -90;
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.MARCH);
            add_Viks_Images1(sheet, null, randomGen, 0.03f, 5);
            add_Viks_Images2(sheet, null, randomGen, 0.03f, 5);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.MARCH] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2026 Calendar.MARCH", ex);
        }

        if (trgOpt.bDoIt(Calendar.MARCH) && THIS_YEAR > FotoKalenderOpt.YEAR1) try {
            CalendarSheet sheet = new CalendarSheet(THIS_YEAR, Calendar.MARCH, this.mDates);
            sheet.prepareImage(5000);
            SourceImage img;
            img = new SourceImage("C:\\Users\\MiRoe\\Pictures\\S_K\u00F6hler_2025-09\\Fotografie mit Vik\\IMG_8133a.jpg");
            sheet.drawImage(img, .5f, .5f, 0, 0, 0, 0.494, 1);
            img = new SourceImage("C:\\Users\\MiRoe\\Pictures\\S_K\u00F6hler_2025-09\\Fotografie mit Vik\\IMG_8097.jpg");
            sheet.drawImage(img, .5f, .55f, 0, .5, 0, 0.5, .49);
            img = new SourceImage("C:\\Users\\MiRoe\\Pictures\\S_K\u00F6hler_2025-09\\Fotografie mit Vik\\IMG_8187.jpg");
            sheet.drawImage(img, .5f, .5f, 0, .5, .5, 0.5, .5);
            sheet.drawCalDates();
            sheet.writeInDir(strOutDir);
            trgOpt.m_bDoIt[Calendar.MARCH] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender_2027 Calendar.MARCH", ex);
        }

        if (trgOpt.bDoIt(Calendar.APRIL)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.APRIL, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_174923.jpg");
            img.dRot = 90;
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.APRIL);
            add_Viks_Images1(sheet, null, randomGen, 0.017f, 5);
            add_Viks_Images2(sheet, null, randomGen, 0.017f, 5);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.APRIL] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.APRIL", ex);
        }

        if (trgOpt.bDoIt(Calendar.MAY)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.MAY, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_172739.jpg");
            int wid = img.getWidth();
            int hei = img.getHeight();
            img.setSourceBounds(new Rectangle(wid/2, hei/3, wid/2-1, hei*2/3-1));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.MAY);
            add_Viks_Images1(sheet, null, randomGen, 0.02f, 5);
            add_Viks_Images2(sheet, null, randomGen, 0.02f, 5);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.MAY] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.MAY", ex);
        }

        if (trgOpt.bDoIt(Calendar.AUGUST)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.AUGUST, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150223.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.AUGUST);
            add_Viks_Images1(sheet, null, randomGen, 0.16f, 4);
            add_Viks_Images2(sheet, null, randomGen, 0.16f, 4);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.AUGUST] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.AUGUST", ex);
        }

        if (trgOpt.bDoIt(Calendar.SEPTEMBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.SEPTEMBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154848.jpg");
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.SEPTEMBER);
            add_Viks_Images1(sheet, null, randomGen, 0.17f, 5);
            add_Viks_Images2(sheet, null, randomGen, 0.17f, 5);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.SEPTEMBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.SEPTEMBER", ex);
        }

        if (trgOpt.bDoIt(Calendar.OCTOBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.OCTOBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_143939b\\20250928_143939_4.jpg");
            sheet.setImage1(img);
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_144143\\20250928_144143_4.jpg");
            img.setCenterPoint(.5f, .3f);
            img.dRot = .18f;
            sheet.addImage2(img);
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_143939b\\20250928_143939_6.jpg");
            sheet.addImage1(img);
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_143939\\20250928_143939_1.jpg");
            sheet.addImage2(img);
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_143939b\\20250928_143939_1.jpg");
            sheet.addImage1(img);
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_143939\\20250928_143939_17.jpg");
            sheet.addImage1(img);
            img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20250928_143939\\20250928_143939_18.jpg");
            sheet.addImage2(img);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.OCTOBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.OCTOBER", ex);
        }

        if (trgOpt.bDoIt(Calendar.DECEMBER)) try {
            var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.DECEMBER, mDates);
            Draw1ImageI img;
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155504.jpg");
            int wid = img.getWidth();
            int hei = img.getHeight();
            img.setSourceBounds(new Rectangle(0, hei/7, wid*25/34, hei*6/7-1));
            sheet.setImage1(img);
            Random randomGen = new Random(THIS_YEAR * 12L + Calendar.DECEMBER);
            add_Viks_Images1(sheet, null, randomGen, 0.05f, 5);
            add_Viks_Images2(sheet, null, randomGen, 0.05f, 5);
            sheet.startMakingIt(strOutDir);
            trgOpt.m_bDoIt[Calendar.DECEMBER] = false; // because already created.
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem with FotoKalender Calendar.DECEMBER", ex);
        }

        for (int nMonth = Calendar.JANUARY; nMonth <= Calendar.DECEMBER; ++nMonth) {
            if (trgOpt.bDoIt(nMonth)) {
                try {
                    var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, nMonth, mDates);
                    Random randomGen = new Random(THIS_YEAR * 12L + nMonth);
                    float flSel = .001f;
                    do {
                        add_Viks_Images1(sheet, null, randomGen, flSel, 5);
                        add_Viks_Images2(sheet, null, randomGen, flSel, 5);
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
    public void add_Viks_Images1(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint, int nSkipImages) {
        Draw1ImageI img;
        int iLastAdded = 0; // if an image is chosen, we skip the next images, because they may be very similar.

        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172555.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170103.jpg");
            img.dRot = 180;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170106.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170124.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170240.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170252.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170308.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint / 2)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170431.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170451.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170609.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170611.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170613.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170615.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170707.jpg");
            img.setCenterPoint(.6f, .6f);
            img.dRot = -60;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170741.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170831.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_170929.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_171601.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_171729.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_171833.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_171941.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172215.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172243.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172259.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172334.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172356.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240721_172419.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_172739.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150139.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150223.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150255.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150310.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150344.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150438.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150446.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150619.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150639.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint / 2)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150812.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150831.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150832.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_150918.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_151614.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_151619.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_151622.jpg");
            img.setCenterPoint(.6f, .5f);
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_172359.jpg");
            img.dRot = 2.55f;
            img.setSourceBounds(new Rectangle(1500, 1000, 1300, 1300));
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_172509.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20240901_172708.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_175057.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_174657.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_174916.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_174920.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_174923.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_174933.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250315_175030.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250327_115313.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250327_114944.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250327_114946.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250327_115247.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250327_115305.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164534.jpg");
            img.dRot = 180;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164557.jpg");
            img.dRot = 180;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164621.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164732.jpg");
            img.dRot = 180;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164812.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164924.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_164947.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165007.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165008.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165039.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165113.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165146.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165218.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165320.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165446.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165506.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165509.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165550.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165702.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165815.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165901.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165909.jpg");
            img.setCenterPoint(.4f, .5f);
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165933.jpg");
            img.setCenterPoint(.55f, .6f);
            img.dRot = 180;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_165948.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170033.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170036.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170056.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170121.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170758.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170814.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_170915.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_171126.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_171444(0).jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_171444.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_171559.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_182436.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250719_185045.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155258.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154300.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154409.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154410.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154419.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154656.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154809.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154835.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154842.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154848.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154905.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154913.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154933.jpg");
            img.setCenterPoint(.6f, .5f);
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_154934.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155130.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155134.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155135.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155214.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155934.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155415.jpg");
            img.setCenterPoint(.6f, .5f);
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155417.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155457.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155504.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155512.jpg");
            img.dRot = 20;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155556.jpg");
            img.setCenterPoint(.6f, .6f);
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155558.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155613.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint / 4)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155615.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155617.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155711.jpg");
            img.dRot = 30;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155819.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155824.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155855.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155914.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155929.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155933.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160206.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_155938.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160000.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160004.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160027.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160041.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160111.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160115.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_160127.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164651.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164419.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164423.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164433.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164434.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint / 4)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164513.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164530.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164538.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164606.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250831_164617.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_171423.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_143054.jpg");
            img.dRot = 180;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_143441.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_143451.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_143517.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_143526.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_143637.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20250928_171406.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260106_191637.jpg");
            img.setCenterPoint(.35f, .5f);
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260106_191433.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260106_191504.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260106_191518.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260106_191532.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260106_191621.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8187.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8093.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8094.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8095.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8096.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8097.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8098.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8099.jpg");
            img.dRot = 90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8100.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8101.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8102.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8103.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8104.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8105.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8106.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8107.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8108.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8109.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8110.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8111.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8113.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8114.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8115.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8116.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8117.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8118.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8119.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8120.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8122.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8123.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8124.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8125.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8126.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8127.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8128.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8129.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8130.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8131.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8132.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8133.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8134.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8135.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8136.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8137.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8143.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8144.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8147.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8148.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8149.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8150.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8151.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8152.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8153.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8154.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8158.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8159.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8160.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8162.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8163.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8164.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8165.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8166.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8167.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8168.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8169.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8170.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8172.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8173.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8174.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8175.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8176.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8177.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8178.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8179.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8180.jpg");
            img.dRot = -90;
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8185.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (--iLastAdded < 0 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/S_K\u00F6hler_2025-09/Fotografie mit Vik/IMG_8186.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
    } /* end  add_Viks_Images1(CalendarSheetCenterImage sheet, Random randomGen, float maxRandomToPaint, int nSkipImages) */

    @SuppressWarnings("ReassignedVariable")
    public void add_Viks_Images2(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint, int nSkipImages) {
        Draw1ImageI img;
        int iLastAdded = 0; // if an image is chosen, we skip the next images, because they may be very similar.

        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173330.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173353.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173358.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173518.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint / 2) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173522.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173525.jpg");
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260205_074404.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_171714.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_171715.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_172033.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_172152.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_172318.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173025.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173027.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173030.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173054.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173056.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173058.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173113.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173141.jpg");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
        if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
            img = new Draw1ImageI("C:/Users/MiRoe/Pictures/SamsungGalS23/DCIM/Camera/20260204_173153.mp4");
            img.autoRotate();
            sheet.addImage(img, hints);
            iLastAdded = nSkipImages;
        }
    }

}

