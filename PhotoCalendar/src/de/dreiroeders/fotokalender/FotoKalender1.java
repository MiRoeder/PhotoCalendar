package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import de.dreiroeders.workingonimages.BufferedImageSetPixImg_ABGR;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.Draw1ImageT;
import de.dreiroeders.workingonimages.Draw1ImageWithTxt;
import de.dreiroeders.workingonimages.IHintsDrawImages;
import de.dreiroeders.workingonimages.MiRoesDraw;
import de.dreiroeders.workingonimages.SourceImage;


public class FotoKalender1 extends FotoKalender {

	final String inDirAndre2023 = "C:\\Users\\MiRoe\\Pictures\\2023 Famile Senior Köhler\\";

	public static void main(String[] args) {
		try {
			Date curTime = new Date();
			DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
			System.out.println("Procedure "+ FotoKalender1.class.getName() +".main(args) started creating FotoKalender: "+ dateFormatter.format(curTime) +" "+ curTime.getTime() + " ms.");
			createEmptyTmpDir();
			makeFamilyCal(0);
			FotoKalenderOpt trgOpt = makeFamilyCal(1);
			if (trgOpt.m_bFreeTmpDirAfterRun) {
				waitForAllThreadsToFinish();
				createEmptyTmpDir();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
		
	public static FotoKalenderOpt makeFamilyCal(int nDeltaYear) throws Exception {
		FotoKalenderOpt trgOpt = FotoKalenderOpt.Current(nDeltaYear);
		FotoKalender1 mainObj = new FotoKalender1(trgOpt.m_nYear);
		for (int iMonth = -1; iMonth <= Calendar.DECEMBER; ++iMonth) {
			if (trgOpt.bDoIt(iMonth)) {
				mainObj.makeFamilyCal(trgOpt);
				break;
			}
		}
		return trgOpt;
	}
	
	public void makeFamilyCal(FotoKalenderOpt trgOpt) throws Exception {
		String strOutDir = trgOpt.m_strOutDir;

		final String inDirG = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Camera\\";
		final String inDirWhatsAp0 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\";
		final String inDirRBB = "C:\\Users\\MiRoe\\Videos\\rbb Fernsehen Weihnachtssingen an der Alten Försterei\\";

		m_trgOpt = trgOpt;
		
		addFamilyEvents();
		addStephansFamilyEvents();
		//addOlafsFamilyEvents();
		addBerlinHolidays();
		//addBavarianPublicHolidays();
		addVacations(trgOpt.m_bBerlinVacations, false);
		addDaylightSavingTimeChanges();
		addAdvents();
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

		if (trgOpt.bDoIt(-1)) try { /* Titelbild */
			CalendarSheetCenterImage sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JANUARY, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirRBB+"vlcsnap-2023-07-06-13h04m41s722_PS.jpg");
			sheet.setImage1(img);
			addAndres2023Images(sheet, null, null, 1);
			sheet.startMakingIt(strOutDir+"0001.jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.JANUARY)) try {
			var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.JANUARY, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirMsGal23+"20240101_001137.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20231229_233122.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20231227_165047.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20231229_164946.jpg");
			sheet.addImage(img);
			sheet.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.FEBRUARY)) try {
			CalendarSheetCenterImage sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.FEBRUARY, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirMsGal23+"20231213_161310.jpg");
			int iW = img.getWidth();
			int iH = img.getHeight();
			int iL = iW/10;
			int iT = iH/3;
			int iB = iH/9;
			img.setSourceBounds(new Rectangle(iL, iT, iW-iL, iH-iT-iB));
			img.dRot = 90;
			sheet.setImage1(img);
			img = new Draw1ImageI(inDirMsGal23+"20231219_103628.jpg");
			sheet.addImage1(img);
			img = new Draw1ImageI(inDirMsGal23+"20240112_195820.jpg");
			sheet.addImage1(img);
			img = new Draw1ImageI(inDirMsGal23+"20231213_163001.jpg");
			img.setCenterPoint(.47f, .5f);
			img.dRot = 90;
			sheet.addImage1(img);
			img = new Draw1ImageI(inDirMsGal23+"20240105_111528.jpg");
			img.setCenterPoint(.44f, .4f);
			img.dRot = 90;
			sheet.addImage2(img);
			sheet.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.MARCH)) {
			try {
				if (trgOpt.m_bAndreMarlies) {
					 makeViksGeburtstag(Calendar.MARCH, strOutDir);
				} else {
					CalendarSheetCenterImage sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.MARCH, mDates);
					Draw1ImageI img;
					img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_03b1c05a.jpg");
					img.setSourceBounds(new Rectangle(875, 200, 380, 670));
					sheet.setImage1(img);
					img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_ee28eca1.jpg");
					img.setCenterPoint(.5f, .45f);
					sheet.addImage1(img);
					img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.53_5c64e910.jpg");
					img.setCenterPoint(.45f, .5f);
					sheet.addImage1(img);
					img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 14.31.24_66612c5d.jpg");
					sheet.addImage2(img);
					img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_0cabe4a1.jpg");
					img.setCenterPoint(.5f, .22f);
					sheet.addImage2(img);
					img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170252.jpg");
					sheet.addImage2(img);
					sheet.startMakingIt(strOutDir);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}  
		}

		if (trgOpt.bDoIt(Calendar.APRIL)) try {
			makeGeniessenAutobahnVsZug(Calendar.APRIL, strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.MAY)) try {
			var sheetCI = new CalendarSheetCenterImage(THIS_YEAR,  Calendar.MAY, mDates);
			var img0 = new SourceImage("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170240.jpg");
			int iW = img0.getWidth();
			int iH = img0.getHeight();
			int iL = iW/8;
			img0.setSourceBounds(new Rectangle(iL, 0, iW-iL, iH));
			Draw1ImageWithTxt img1;
			img1 = new Draw1ImageWithTxt(img0,
					                     "Fotografin: Sophie Viktoria Köhler (5 Jahre)",
					                     new Color(224, 32, 128), Font.SERIF, Font.ITALIC, 200 );
			sheetCI.setImage1(img1);
			addImagesByVik202407(sheetCI, null, new Random(THIS_YEAR*12+Calendar.MAY), .6f);
			sheetCI.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.JUNE)) try {
			if (trgOpt.m_bAndreMarlies) {
				CalendarSheetCenterImage sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JUNE, mDates);
				Draw1ImageI img;
				img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_03b1c05a.jpg");
				img.setSourceBounds(new Rectangle(875, 200, 380, 670));
				sheet.setImage1(img);
				img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_0cabe4a1.jpg");
				img.setCenterPoint(.5f, .3f);
				sheet.addImage1(img);
				img = new Draw1ImageI(inDirAndre2023+"20230512_165329.jpg");
				sheet.addImage1(img);
				img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 14.31.24_66612c5d.jpg");
				sheet.addImage2(img);
				img = new Draw1ImageI(inDirAndre2023+"20230513_103938.jpg");
				sheet.addImage2(img);
				img = new Draw1ImageI(inDirAndre2023+"20230514_173327.jpg");
				img.dRot = 180;
				sheet.addImage2(img);
				sheet.startMakingIt(strOutDir);
			} else {
				CalendarSheet sheet;
				sheet = new CalendarSheet(THIS_YEAR, Calendar.JUNE, mDates);
				sheet.prepareImage(3750/0.9927248677248678-1);
				final float hSteg = 0.008f;
				final float wSteg = hSteg/CalendarSheet.fWeight;
				final float hCenterPic = .2f;
				final float wCenterPic = .2f;
				final float xCenterPic = (1f-wCenterPic)/2f;
				final float yCenterPic = (1f-hCenterPic)/2f;
				var img1 = new SourceImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.29.31.jpg");
				img1.setSourceBounds(new Rectangle(270, 680, 540, 350));
				sheet.drawImage(img1.getImage(),                                              1f/2f, 1f/2f, 0, xCenterPic+wSteg     ,yCenterPic+hSteg     ,          wCenterPic-2*wSteg,         hCenterPic-2*hSteg);
				sheet.drawImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.15.18.jpg",      1f/2f, 4f/5f, 0, 0f                   ,                   0f, xCenterPic+wCenterPic-wSteg,yCenterPic                 );
				sheet.drawImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.15.49.jpg",      1f/2f, 1f/2f, 0, xCenterPic+wCenterPic,                   0f, 1f-xCenterPic-wCenterPic   ,yCenterPic+hCenterPic-hSteg);
				sheet.drawImage("Examples.res/351535115_1761041277631953_2772123894799380056_n.jpg",.5f,.5f,0, xCenterPic+wSteg     ,yCenterPic+hCenterPic,    1f-xCenterPic+wSteg     ,1f-yCenterPic-hCenterPic   );
				sheet.drawImage("Examples.res/351525974_776193984009985_4460773923173507186_n.jpg" ,.5f,.6f,0, 0f                   ,yCenterPic+hSteg     , xCenterPic                 ,1f-yCenterPic-hSteg        );
				sheet.drawCalDates();
				sheet.writeInDir(strOutDir);
			}
		} catch (Exception ex) {
			ex.printStackTrace();    
		}

		if (trgOpt.bDoIt(Calendar.JULY)) try {
			var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.JULY, mDates);
			Draw1ImageI img; 
			img = new Draw1ImageI(inDirMsGal23+"20240611_205308.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240606_164949a.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240604_155404a.jpg");
			img.setCenterPoint(.5f, .41f);
			img.dRot = -0.01f;
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240603_152716.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240607_110330.jpg");
			img.setCenterPoint(.6f, .52f);
			sheet.addImage(img);
			sheet.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.AUGUST)) {
			boolean bIsTitleSheet = THIS_YEAR == 2025;
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.AUGUST, mDates);
			addAndres2023Images(sheet, null, new Random(THIS_YEAR*12+Calendar.AUGUST+100000), bIsTitleSheet ? .5f : .15f);
			addManyImages2024Vik(sheet, null, new Random(THIS_YEAR*12+Calendar.AUGUST+100000), bIsTitleSheet ? .5f : .15f);
			if (bIsTitleSheet) {
				addImages2024_Winter_Vik(sheet, null, null, 1f);
			}
			Thread thr = sheet.startMakingIt(strOutDir);
			if (bIsTitleSheet) {
				thr.join();
				renameToTitle(strOutDir, "2508.jpg");
			}
		}
		
		if (trgOpt.bDoIt(Calendar.SEPTEMBER)) try {
			final String strSchool = "C:\\Users\\MiRoe\\Pictures\\GrundschuleMahlsdorf\\";
			final String inDirVik2 = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2022_01_01 Viktoria in 2022\\";
			final String inDirV21= "C:\\Users\\MiRoe\\Pictures\\2021 Vik\\";
			var sheet = new CalendarSheet(THIS_YEAR,  Calendar.SEPTEMBER, mDates);
			sheet.prepareImage(5000);
			SourceImage img1;
			img1 = new SourceImage("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 18.04.24_79c869c8.jpg");
			SourceImage img2 = new SourceImage("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-19 um 17.18.37_2321601f.jpg");
			int iSteg = sheet.getUsuableHeight()/200;
			int iWC = img2.getWidth();
			int iHC = img2.getHeight();
			int iH2 = sheet.getUsuableHeight()*10/26;
			int iW2 = iH2 * iWC / iHC;
			int iW1 = (sheet.getUsuableWidth() -iW2-iSteg)/2;
			int iH1 = (sheet.getUsuableHeight()-iH2-iSteg)/2;
			int iX2 = iW1+iSteg;
			int iX3 = iX2+iW2+iSteg;
			int iY2 = iH1+iSteg;
			int iY3 = iY2+iH2+iSteg;
			sheet.drawImage(inDirAndre2023+"20230519_192036.jpg",     .5,.5, 0, 0  , 0  , iW1, iH1);
			sheet.drawImage(inDirAndre2023+"IMG-20230517-WA0019.jpg", .5,.6, 0, iX2, 0  , iW2, iH1);
			sheet.drawImage(inDirVik2+"20220511_074609.jpg",          .5,.5,90, iX3, 0  , iW2, iH1+iH2+iSteg);
			sheet.drawImage(inDirV21+"WhatsApp Image 2021-08-21 at 20.13.53 (2).jpeg",
																	  .5,.5, 0, 0  , iY2, iW1, iH2);
			sheet.drawImage(img2,                                     .5,.5, 0, iX2, iY2, iW2, iH2);
			sheet.drawImage(strSchool+"Kletterhaeuser.jpg",           .5,.5, 0, 0  , iY3, iW1, iH1);
			sheet.drawImage(img1,                                     .5,.5, 0, iX2, iY3, iW2, iH1);
			sheet.drawImage(strSchool+"22_Commputerraum-1024x680.jpg",.5,.5, 0, iX3, iY3, iW1, iH1);
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.OCTOBER)) try {
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.OCTOBER, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirMsGal23+"20240414_165444(0).jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240414_164712(0).jpg");
			img.setCenterPoint(.3f, .5f);
			img.dRot = 90;
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240414_164117.jpg");
			img.dRot = 90;
			sheet.addImage(img);
			img = new Draw1ImageI(inDirMsGal23+"20240414_175021.jpg");
			img.setSourceBounds(new Rectangle(1900, 1300, 1300, 800));
			img.dRot = -15;
			sheet.addImage(img);
			sheet.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.NOVEMBER)) try {
			var sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.NOVEMBER, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20231217_125530.jpg");
			sheet.setImage1(img);
			addManyImages(new File("C:\\Users\\MiRoe\\Pictures\\2021 Vik"), "jpeg", sheet, null, new Random(THIS_YEAR*12+Calendar.NOVEMBER), 0.2f, 2f);
			sheet.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.DECEMBER)) try {
			var sheet = new CalendarSheet(THIS_YEAR, Calendar.DECEMBER, mDates);
			sheet.prepareImage(2000/0.899074074074074);
			float h1 = .7f;
			sheet.drawImage(inDirRBB+"vlcsnap-2023-07-06-13h04m24s808_auto_PhotoShop.png",.67,.5,0,0,  0, .497, h1);
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-23 um 21.09.36.jpg",.5, .5,  0,.503,  0, .497, h1);
			float y2 = h1+0.006f*CalendarSheet.fWeight;
			float h2 = 1f-y2;
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-23 um 21.05.59.jpg",.46, .53,0,   0, y2, .25 , h2);
			if (trgOpt.m_bAndreMarlies) {
				sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-24 um 22.08.38.jpg",.5 , .5, 0,.256, y2, .17 , h2);
				sheet.drawImage(inDirG +                           "20221224_180530.jpg",.45, .6, 0,.432, y2, .136, h2);
				sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-24 um 22.08.44.jpg",.55, .5, 0,.574, y2, .17 , h2);
				sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-25 um 12.11.54_3.jpg",.5,.5, 0, .75 , y2,.25 , h2);
			} else {
				float wSteg = 0.006f;
				sheet.drawImage(inDirMsGal23 + "20231224_135039.jpg"          ,.5 , .5, 90,         .256, y2, .16 , h2);
				float x4 = sheet.getflLastX();
				float w5 = (1-x4)/2 - wSteg;
				sheet.drawImage(inDirMsGal23 + "20231224_145748.jpg"          ,.5 , .5, 0, x4+  wSteg   , y2,  w5 , h2);
				sheet.drawImage(inDirMsGal23 + "20231224_155821.jpg"          ,.5 , .5, 0, x4+2*wSteg+w5, y2,  w5 , h2);
			}
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Kalender "+ THIS_YEAR +" creating / created in "+ strOutDir);
	} /* end of makeFamilyCal(int year) */

	
	public FotoKalender1(int year) {
		super(year);
	}

	public void makeCorona1(int nMonth, String strOutDir) {
		try {
			final String inDirVK = "C:\\Users\\MiRoe\\Pictures\\2020 Vik\\Viktoria 2020\\";
			final String inDir1  = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, this.mDates);
			sheet.prepareImage(3262);
			float high = sheet.getUsuableHeight();
			float widt = sheet.getUsuableWidth();
			float hW = (high+1)/(widt+1);
			System.out.println("makeCorona1 : "+ widt +" x "+ high +" rel High: "+ hW);
			
			float h2 = Math.min(1f / hW * 1592 / 3264, 0.95f);
			float w1, h1;
			if (h2 < 0.75f) {
				w1 = Math.min((1f-h2) * hW * 4 / 3 - 0.005f, 0.33f);
			} else {
				w1 = 0.33f;
			}
			h1 = Math.min(w1/hW/4*3, 0.99f);
			
			sheet.drawImage(inDir1+"FB_IMG_1587658131117.jpg",   0.5, 0.5, 0, 0.00, 0.00, w1, h1);
			sheet.drawText("und was bringt", sheet.getDefaultTextCol(), "Times New Roman", 0,
				      													  w1+0.01f, 0.00, 0.99f-2*w1, h1/3-0.01f );
			int wt2 = sheet.drawText("2021", sheet.getDefaultTextCol(), "Times New Roman", Font.BOLD,
				      													  w1+0.01f, h1/3, 0.99f-2*w1, h1/3*2 );
			sheet.drawText("?", sheet.getDefaultTextCol(), "Times New Roman", 0,
					                                                (widt+wt2)/2+1, h1*.43,h1/3*hW  , h1/2 );
						   
			if (h1 + 2*h2 <= 1.1f) {
				h2 = (.98f-h1)/2;
				final String inDirCl = "C:\\Users\\MiRoe\\Pictures\\Cal2021\\";
				sheet.drawImage(inDirCl+"20210102_123520_8.jpg",.5,.5, 0, 0.00, h1+.01f,    1.00, h2);
				sheet.drawImage(inDirVK+"20200919_153245.jpg", .5, .5, 0, 0.00, h1+.02f+h2, 1.00, h2);
			} else 
			if (h1 + h2 <= 1f) {
				sheet.drawImage(inDirVK+"20200919_153245.jpg", .5, .5, 0, 0.00, h1+.01f, 1.00, .99f-h1);
			} else {
				h2 = Math.min(0.66f / hW * 1592 / 3264, 0.95f);
				sheet.drawImage(inDirVK+"20200919_153245.jpg", .5, .5, 0, 0.34, 1-h2,    0.66, h2);
			}

			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makePilotinConstanze(int nMonth, String strOutDir) {
		try {
			final String inDirVK = "C:\\Users\\MiRoe\\Pictures\\2020 Vik\\Viktoria 2020\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, mDates);
			sheet.prepareImage(3111);
			sheet.drawSerifText("Wie Reisen wir "+ THIS_YEAR +"? Vielleicht nur noch virtuell?",             0,   0.00, 1d  , 0.045 );
			sheet.drawSerifText("Berlin \u2192 Kapstadt \u2192 San Francisco in einer Stunde",               0,   0.05, 1d  , 0.045 );
			sheet.drawSerifText("mit unserer Hyper-Pilotin Constanze Rï¿½der",                                 0,   0.10, 1d  , 0.045 );
			if (CalendarSheet.fWeight < 1) {
				sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\2020-08 Flugsimulator.jpg",          .5, .5, 0, 0,   0.15, 1f  , 0.6);
				sheet.drawSerifText("Die Passagiere sind begeistert.",                                       0,   0.755,1f  , 0.04);
				sheet.drawImage(inDirVK+"20200728_173524.jpg",                                   .45, .5, 0, 0,   0.8 , .33,  0.2 );
				sheet.drawPartImage("C:\\Users\\MiRoe\\Pictures\\Hambug Dungeons.jpg", 3650,1750,1000,500,0, .335,0.8 , .33,  0.2 );
				sheet.drawPartImage("C:\\Users\\MiRoe\\Pictures\\Hambug Dungeons.jpg", 1250,1400,1000,500,0, .67, 0.8 , .33,  0.2 );
			} else {
				sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\2020-08 Flugsimulator.jpg",          .5, .5, 0, 0,   0.15, .75 , 0.85);
				sheet.drawSerifText("Die Passagiere",                                                        .76, 0.15, .24 , 0.035 );
				sheet.drawSerifText("sind begeistert.",                                                      .76, 0.19, .24 , 0.035 );
				sheet.drawImage(inDirVK+"20200728_173524.jpg",                                   .45, .5, 0, .76, 0.23, .24,  0.25 );
				sheet.drawPartImage("C:\\Users\\MiRoe\\Pictures\\Hambug Dungeons.jpg", 3650,1750,1000,500,0, .76, 0.49, .24,  0.25 );
				sheet.drawPartImage("C:\\Users\\MiRoe\\Pictures\\Hambug Dungeons.jpg", 1250,1400,1000,500,0, .76, 0.75, .24,  0.25 );
			}
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void makeViktoriaImPool(int nMonth, String strOutDir) {
		try {
			final String srcImgW = "C:\\Users\\MiRoe\\Pictures\\2019 Sophie Viktoria\\WhatsApp\\";
			final String inDirG = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Camera\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, mDates);
			sheet.prepareImage(3000);
			Draw1ImageT pic;
			float fW = CalendarSheet.fWeight > 1 ? 1f : 1.6f;
			
			pic = new Draw1ImageT(srcImgW + "WhatsApp Image 2019-08-15 at 20.32.12_Cal.jpg",  0    ,  0 , 1    , 1 , 0 , 0 , 0 , 0);
			pic.setSourceBounds(new Rectangle(2500, 300, 1000, 1500));
			sheet.drawImageA(pic);

			pic = new Draw1ImageT(inDirG  + "20201025_134640.jpg"                          ,  0    ,  0 , .4*fW, .6, 0 , 0 , 0 , 0.2);
			pic.setCenterPoint(.4f, .6f);
			sheet.drawImageA(pic);
			
			pic = new Draw1ImageT(inDirG  + "20200731_145022.jpg"                          , .3*fW ,  0 , .55*fW,.6,.18, 0 , 0 , 0);
			pic.setCenterPoint(.55f, .6f);
			sheet.drawImageA(pic);
			
			BufferedImageSetPixImg_ABGR img3;
			int h3, w3, h32;
			
			if (CalendarSheet.fWeight > 1) {
				img3 = new BufferedImageSetPixImg_ABGR(inDirG+"20191023_145713.jpg");
				h3 = img3.getWidth();
				w3 = img3.getHeight();
				h32 = h3/2;
				for (int ym = 0; ym < h32; ++ym) {
					for (int xm = 0; xm < h32-ym; ++xm) {
						float fAS = (float)(ym+xm)/h32;
						img3.setAlpha(ym, w3-1-xm, fAS);
					}
				}
				MiRoesDraw.diagOut(img3.getImage(0));
				pic = new Draw1ImageT(img3.getImage(0)                                     , .65,  0 , .35,.6, .2,  0,  0, 0);
				pic.dRot = 90;
				pic.setCenterPoint(.5f, .5f);
				sheet.drawImageA(pic);
			}
			
			pic = new Draw1ImageT(inDirG + "20220527_131357.jpg",
																							 .3*fW  , .4 , .4*fW, .6, .2 , .2, .2, 0);
			pic.dRot = 90;
			sheet.drawImageA(pic);

			SourceImage siPic = new SourceImage(inDirG + "20220527_131418.jpg");
			img3 = new BufferedImageSetPixImg_ABGR(siPic.getImage(), 1f);
			w3 = img3.getWidth();
			h3 = img3.getHeight();
			h32 = h3*3/5;
			for (int ym = 0; ym < h32; ++ym) {
				for (int xm = 0; xm < h32-ym; ++xm) {
					float fAS = (float)(ym+xm)/h32;
					img3.setAlpha(w3-1-xm, ym, fAS);
				}
			}
			MiRoesDraw.diagOut(img3.getImage(0));
			pic = new Draw1ImageT(img3.getImage(0)                                         , 0*fW  , .4 , .35*fW, .6, 0 , .2, .1, 0);
			pic.dRot = 90;
			sheet.drawImageA(pic);
			
			pic = new Draw1ImageT(inDirG  + "20200731_145022.jpg"                          , .3*fW ,  0 , .55*fW, .6, .3, .3, .3,.3);
			pic.setCenterPoint(.55f, .6f);
			sheet.drawImageA(pic);

			if (CalendarSheet.fWeight > 1) {
				pic = new Draw1ImageT(inDirG+"20191023_145713\\1.png"                      , .65,  0 , .35,.6, .2,  0, 0, 0);
				pic.setCenterPoint(.5f, .5f);
				sheet.drawImageA(pic);
			}

			pic = new Draw1ImageT(inDirG  + "20220527_131357 partly transparent.png"       , .3*fW  , .4 , .4*fW, .6, .1 , .1, .1, 0);
			sheet.drawImageA(pic);

			if (CalendarSheet.fWeight > 1) {
				pic = new Draw1ImageT(srcImgW + "WhatsApp Image 2019-11-27 at 17.18.18.jpeg",.6  , .4 , .4, .6 ,.2, .2, 0, 0);
				pic.setCenterPoint(.5f, .45f);
				sheet.drawImageA(pic);
			}
			
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	void addManyImages(CalendarSheetCenterImage sheet, Random randomGen, float maxRandomToPaint) {
		final String inDirG = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Camera\\";
		final String inDirV21= "C:\\Users\\MiRoe\\Pictures\\2021 Vik\\";
		final String inDirCo = "C:\\Users\\MiRoe\\Pictures\\Constanzess\\Camera\\";
		final String inDirWhatsAp = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Media\\WhatsApp Images\\";
		final String inDirVik2 = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2022_01_01 Viktoria in 2022\\";
		final String inDirWeihAll = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2022_11_19 Weihnachen für alle\\";
		final String inDirMartinUmzug = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2022_11_11 St Martins Umzug mit der Kita\\";
		final String inDirWhatsAp1 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\";
		final String inDirStep = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2021_12_30 Ausflug Erpetal\\";
		final String inDirZooE = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2021_12_27   Zoo Eberswalde\\primaryDCIMCamera";
		Draw1ImageI img;
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWeihAll+"20221119_093344.jpg");
			img.dRot = 0;
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirVik2+"20220707_165439.jpg");
			img.dRot = 0;
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirVik2+"20220416_204733.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWeihAll+"20221119_103907.jpg");
			img.dRot = 0;
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirMartinUmzug+"20221111_162822.jpg");
			img.dRot = 0;
			img.setCenterPoint(0.48f, 0.5f);
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirMartinUmzug+"20221111_163125.jpg");
			img.dRot = 0;
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirMartinUmzug+"20221111_155402.jpg");
			img.dRot = 0;
			img.setCenterPoint(0.45f, .5f);
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirMartinUmzug+"20221111_164612.jpg");
			img.dRot = 0;
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirG+"20221203_115906.jpg");
			img.dRot = 90;
			img.setCenterPoint(0.4f, 0.5f);
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirG+"20221009_182025.jpg");
			img.dRot = 0;
			img.setCenterPoint(0.5f, 0.6f);
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			if (CalendarSheet.fWeight < .5f || 2 < CalendarSheet.fWeight) {
				img = new Draw1ImageI(inDirG+"20221203_105820.jpg");
				img.dRot = 180;
				sheet.addImage1(img);
				img = new Draw1ImageI(inDirG+"20221202_114333.jpg");
				img.dRot = 0;
				sheet.addImage2(img);
			}
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirG+"20210108_094950.jpg");
			img.dRot = 0;
			img.setCenterPoint(8.2f/12f, 0.5f);
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirCo+"20210723_101737.jpg");
			img.setCenterPoint(.3f, .5f);
			img.dRot = 90;
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirG+"20210308_103506.jpg");
			img.dRot = 90;
			img.setCenterPoint(.4f, .5f);
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirG+"20210308_103522.jpg");
			img.dRot = 90;
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirCo+"20210720_161347.jpg");
			img.dRot = 90;
			img.setCenterPoint(.4f, .5f);
			sheet.addImage1(img);
		}
		if (CalendarSheetCenterImage.fWeight > 1.9f) {
			for (int iI = 1; iI <= 4; ++iI) {
				if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
					img = new Draw1ImageI(inDirV21+"WhatsApp Image 2021-03-22 at 12.48.41 ("+ iI +").jpeg");
					sheet.addImage2(img);
				}
			}
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirVik2+"20220619_141506.jpg");
			img.setCenterPoint(.25f, .5f);
			img.dRot = 90;
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirVik2+"20220619_083248.jpg");
			img.dRot = 90;
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp1+"WhatsApp Bild 2023-07-02 um 11.48.53");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20160911-WA0000.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20161008-WA0007.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20161008-WA0011.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20170413-WA0000.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20170611-WA0005.jpg");
			img.setCenterPoint(.5f, .6f);
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20170620-WA0000.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20171217-WA0004.jpg");
			img.setCenterPoint(.5f, .7f);
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20190331-WA0008.jpg");
			img.setCenterPoint(.5f, .3f);
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20190430-WA0002");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirWhatsAp+"IMG-20190824-WA0001.jpg");
			sheet.addImage1(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			// in Kalender für Adriana Januar 2023
			img = new Draw1ImageI(inDirStep+"20211230_103440.jpg");
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			// in Kalender für Michael Februar 2023
			img = new Draw1ImageI(inDirZooE+"20211227_110035.jpg");
			img.dRot = 90;
			img.setCenterPoint(.4f, .5f);
			sheet.addImage2(img);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			// in Kalender für Michael Februar 2023
			img = new Draw1ImageI(inDirVik2+"20220331_163804.jpg");
			img.dRot = 90;
			sheet.addImage2(img);
		}
	} /* end  addManyImages(CalendarSheetCenterImage sheet, Random randomGen, float maxRandomToPaint) */

	void addAndres2023Images(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint) {
		Draw1ImageI img;
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_055736.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_064748.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_065322.jpg");
			img.dRot = 180;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_090757.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_100901.jpg");
			img.setCenterPoint(.45f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_165329.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230512_190937a.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230513_103938.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230513_171143.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230513_193516a.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230514_173327.jpg");
			img.dRot = 180;
			img.setCenterPoint(.52f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230514_181910.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen != null && randomGen.nextFloat() < maxRandomToPaint*.75f) {
			img = new Draw1ImageI(inDirAndre2023+"20230518_160208.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230518_185741.jpg");
			img.dRot = 0;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230519_150301.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230519_185654.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"20230519_192036.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"IMG-20230517-WA0019.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI(inDirAndre2023+"VideoCapture_20230514-150439.jpg");
			sheet.addImage(img, hints);
		}
	} /* end  addAndres2023Images(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint)  */

	void addManyImages2024Vik(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint) {
		Draw1ImageI img;
		boolean bAddedImage = false;
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-13 um 12.32.13_28967e48.jpg");
			img.setCenterPoint(.6f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 14.31.24_14f1d972.jpg");
			img.setCenterPoint(.5f, .3f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 14.31.24_66612c5d.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint/2) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 14.31.25_2ec8d15a.jpg");
			img.setCenterPoint(.5f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 14.31.25_97703e13.jpg");
			img.setCenterPoint(.45f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-06-27 um 18.04.24_79c869c8.jpg");
			img.setCenterPoint(.45f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-04 um 19.21.06_529e8da9.jpg");
			img.setCenterPoint(.55f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-04 um 19.22.04_0e12eede.jpg");
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-08 um 12.55.31_0c2a71bc.jpg");
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-08 um 12.55.31_5bbc7308.jpg");
			sheet.addImage(img, hints);
		}
		boolean bAddVik1 = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddVik1) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_03b1c05a.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_33bfe06e.jpg");
			sheet.addImage(img, hints);
		}
		/* The next two pictures are very similar, so only one of them is enough */
		bAddedImage = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedImage) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_652ed2a6.jpg");
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img, hints);
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_637516d8.jpg");
			img.setCenterPoint(.6f, .45f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.48_ba70e503.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_28cfac0c.jpg");
			sheet.addImage(img, hints);
		}
		/* Some of the next pictures are very similar, so only one of them is enough */
		boolean bAddedAdrSteph = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedAdrSteph) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_284886d2.jpg");
			sheet.addImage(img, hints);
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_23401443.jpg");
			sheet.addImage(img, hints);
		}
		if (!bAddVik1 && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_c8fefea7.jpg");
			sheet.addImage(img, hints);
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_ee28eca1.jpg");
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.49_ff229a69.jpg");
			sheet.addImage(img, hints);
		}
		/* The next nine pictures are very similar, so only one of them is enough */
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.51_0ab75f79.jpg");
			img.setCenterPoint(.52f, .35f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.51_14c8f1d6.jpg");
			img.setCenterPoint(.52f, .35f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.51_5873f787.jpg");
			img.setCenterPoint(.45f, .5f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.51_e56be3e4.jpg");
			img.setCenterPoint(.5f, .2f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_0cabe4a1.jpg");
			img.setCenterPoint(.5f, .25f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_0fafdc70.jpg");
			img.setCenterPoint(.45f, .5f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_1f647e82.jpg");
			img.setCenterPoint(.52f, .35f);
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_5af1927b.jpg");
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		if (!bAddedAdrSteph && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_6dffb8cc.jpg");
			sheet.addImage(img, hints);
			bAddedAdrSteph = true;
		}
		/* The next two pictures are very similar, so only one of them is enough */
		bAddedImage = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedImage) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_d1e53a26.jpg");
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img, hints);
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.52_ec1adaac.jpg");
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img, hints);
		}
		/* The next three pictures are very similar, so only one of them is enough */
		bAddedImage = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedImage) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.53_5c64e910.jpg");
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img, hints);
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.53_511676e9.jpg");
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-12 um 21.47.53_f295cce5.jpg");
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\2024_Vik\\WhatsApp Bild 2024-07-13 um 11.50.36_738278c7.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240520_174924.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		bAddedImage = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedImage) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170240.jpg");
			img.setCenterPoint(.6f, .5f);
			sheet.addImage(img, hints);
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172356.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172419.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170707.jpg");
			img.dRot = -60;
			img.setCenterPoint(.7f, .7f);
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170252.jpg");
			img.setCenterPoint(.5f, .7f);
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
	} /* end  addManyImages2024Vik(CalendarSheetCenterImage sheet, Random randomGen, float maxRandomToPaint) */
	
	
	void addImages2024_Winter_Vik(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint) {
		Draw1ImageI img;
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240105_111528.jpg");
			img.setCenterPoint(.45f, .5f);
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
	}

	
	void addImagesByVik202407(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint) {
		Draw1ImageI img;
		boolean bAddedImage = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedImage) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170831.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170929.jpg");
			img.dRot = 180;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_171601.jpg");
			img.dRot = 0;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_171729.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_171833.jpg");
			img.dRot = 0;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_171941.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172215.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172243.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172259.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172334.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172356.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172419.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172510.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_172555.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170103.jpg");
			img.dRot = 180;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170106.jpg");
			img.dRot = 180;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170124.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170240.jpg");
			var rect = new Rectangle(200, 0, img.getWidth()-200, img.getHeight());
			img.setSourceBounds(rect);
			img.setCenterPoint(.5f, .55f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170252.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170308.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170431.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170451.jpg");
			sheet.addImage(img, hints);
		}
		bAddedImage = randomGen == null || randomGen.nextFloat() < maxRandomToPaint;
		if (bAddedImage) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170609.jpg");
			sheet.addImage(img, hints);
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170611.jpg");
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170613.jpg");
			sheet.addImage(img, hints);
			bAddedImage = true;
		}
		if (!bAddedImage && (randomGen == null || randomGen.nextFloat() < maxRandomToPaint)) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170615.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170707.jpg");
			img.dRot = -60;
			img.setCenterPoint(.7f, .7f);
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\20240721_170741.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
	} /* end  addImagesByVik202407(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint)  */


}

