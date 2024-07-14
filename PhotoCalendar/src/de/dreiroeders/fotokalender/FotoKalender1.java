package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
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

		final String inDirV21= "C:\\Users\\MiRoe\\Pictures\\2021 Vik\\";
		final String inDirG = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Camera\\";
		final String inDirGP = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Pictures\\";
		final String inDirCo = "C:\\Users\\MiRoe\\Pictures\\Constanzess\\Camera\\";
		final String inDirWhatsApp= "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\WhatsApp\\Media\\WhatsApp Images\\";
		final String inDirWhatsAp = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Media\\WhatsApp Images\\";
		final String inDirWhatsAp0 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\";
		final String inDirFb1 = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\";
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
			MakeSheetFeuerwerke.startMaking(trgOpt.m_nYear, Calendar.JANUARY, mDates, strOutDir, 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.FEBRUARY)) try {
			final String inWelcomeVik = "C:\\Users\\MiRoe\\Pictures\\2019 Sophie Viktoria\\2019-10-05 Willkommensfeier\\";
			CalendarSheet sheet;
			sheet = new CalendarSheet(THIS_YEAR, Calendar.FEBRUARY, mDates);
			sheet.prepareImage(5000/0.8022486772486772);
			float h1 = .7f;
			sheet.drawImage(inWelcomeVik+"20191005_144940.jpg",.5 , .5,  0,   0,  0, .497, h1);
			sheet.drawImage(inDirCo+"20210514_131018c.jpg",    .45, .5,  0,.503,  0, .497, h1);
			float y2 = h1+0.006f*CalendarSheet.fWeight;
			float h2 = 1f-y2;
			sheet.drawImage(inDirCo+"20211125_133159.jpg",     .5 , .5,-90,   0, y2, .2  , h2);
			sheet.drawImage(inDirG +"20230618_163202.jpg",     .5 , .5, 90,.206, y2, .17 , h2);
			sheet.drawImage(inDirG +"20230629_183230.jpg",     .5 , .5,  0,.382, y2, .236, h2);
			sheet.drawImage(inDirG +"20230629_182954.jpg",     .5 , .5, 90,.624, y2, .17 , h2);
			sheet.drawImage(inDirG +"20230629_173651.jpg",     .35, .5,  0,.8  , y2, .2  , h2);
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.MARCH)) {
			makeViksGeburtstag(Calendar.MARCH, strOutDir);
		}

		if (trgOpt.bDoIt(Calendar.APRIL)) try {
			CalendarSheet sheet;
			sheet = new CalendarSheet(THIS_YEAR, Calendar.APRIL, mDates);
			sheet.prepareImage(5000);
			if (CalendarSheet.fWeight < 1) {
				sheet.drawImage(inDirCo+"20220319_101538.jpg",               .6, .6, 0,   0f,.203f, .235f, .241f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220319-WA0008.jpg",     .4, .5, 0,   0f,   0f, .235f, .200f);
				sheet.drawImage(inDirGP+"signal-2022-03-21-10-14-04-593.jpg",.4, .5, 0, .24f,   0f, .385f, .444f);
				sheet.drawImage(inDirCo+"20220319_151128.jpg",               .5, .4,90, .63f,  .0f, .385f, .444f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220320-WA0008.jpg",     .5, .5, 0, .0f , .67f, .395f, .33f);
				sheet.drawImage(inDirG+"20220319_172357.jpg",                .5, .5, 0, .40f, .67f, .600f, .33f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220320-WA0012.jpg",     .5, .5, 0,   0f, .447f,.275f, .22f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220320-WA0016.jpg",     .6, .5, 0, .28f ,.447f,.385f, .22f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220321-WA0000.jpg",     .5, .5, 0, .67f, .447f,.330f, .22f);
			} else {
				sheet.drawImage(inDirWhatsApp+"IMG-20220319-WA0008.jpg",     .4, .54,0,   0f,   0f, .157f, .200f);
				sheet.drawImage(inDirCo+"20220319_101538.jpg",               .6, .6, 0,   0f,.205f, .157f, .320f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220320-WA0006",         .6, .54,0,   0f,.53f , .157f, .135f);
				sheet.drawImage(inDirGP+"signal-2022-03-21-10-14-04-593.jpg",.35,.55,0, .16f,   0f, .247f, .665f);
				sheet.drawImage(inDirCo+"20220319_151128.jpg",               .5, .4,90, .41f,  .0f, .297f, .665f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220321-WA0000.jpg",     .5, .5, 0, .71f,   0f, .290f, .33f);
				sheet.drawImage(inDirG+"20220319_172357.jpg",                .5, .5, 0, .71f,.335f, .290f, .33f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220320-WA0012.jpg",     .5, .5, 0,   0f, .67f, .187f, .33f);
				sheet.drawImage(inDirWhatsApp+"IMG-20220320-WA0004.jpg",     .5, .5, 0, .19f, .67f, .517f, .33f);
				sheet.drawImage(inDirCo+"20220319_172513.jpg",               .5, .5,90, .71f, .67f, .290f, .33f);
			}
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.MAY)) {
			final String inDirVik2 = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2022_01_01 Viktoria in 2022\\";
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.MAY, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirCo+"20210720_161347.jpg");
			img.dRot = 90;
			img.setCenterPoint(.4f, .5f);
			sheet.addImage(img);
			img = new Draw1ImageI(inDirVik2+"20220331_163804.jpg");
			img.dRot = 90;
			sheet.addImage(img);
			sheet.setPreferedSize(CalendarSheet.fWeight > 1 ? 1 : 0);
			sheet.startMakingIt(strOutDir);
		}

		if (trgOpt.bDoIt(Calendar.JUNE)) try {
			CalendarSheet sheet;
			sheet = new CalendarSheet(THIS_YEAR, Calendar.JUNE, mDates);
			sheet.prepareImage(3750/0.9927248677248678-1);
			final float hSteg = 0.008f;
			final float wSteg = hSteg/CalendarSheet.fWeight;
			final float hCenterPic = .2f;
			final float wCenterPic = .2f;
			final float xCenterPic = (1f-wCenterPic)/2f;
			final float yCenterPic = (1f-hCenterPic)/2f;
			var img1 = new SourceImage(inDirWhatsAp0+"WhatsApp Bild 2023-06-04 um 13.29.31.jpg");
			img1.setSourceBounds(new Rectangle(270, 680, 540, 350));
			sheet.drawImage(img1.getImage(),                                          1f/2f, 1f/2f, 0, xCenterPic+wSteg     ,yCenterPic+hSteg     ,          wCenterPic-2*wSteg,         hCenterPic-2*hSteg);
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2023-06-04 um 13.15.18.jpg", 1f/2f, 4f/5f, 0, 0f                   ,                   0f, xCenterPic+wCenterPic-wSteg,yCenterPic                 );
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2023-06-04 um 13.15.49.jpg", 1f/2f, 1f/2f, 0, xCenterPic+wCenterPic,                   0f, 1f-xCenterPic-wCenterPic   ,yCenterPic+hCenterPic-hSteg);
			sheet.drawImage(inDirFb1+"351535115_1761041277631953_2772123894799380056_n.jpg",.5f,.5f,0, xCenterPic+wSteg     ,yCenterPic+hCenterPic,    1f-xCenterPic+wSteg     ,1f-yCenterPic-hCenterPic   );
			sheet.drawImage(inDirFb1+"351525974_776193984009985_4460773923173507186_n.jpg" ,.5f,.6f,0, 0f                   ,yCenterPic+hSteg     , xCenterPic                 ,1f-yCenterPic-hSteg        );
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();    
		}

		if (trgOpt.bDoIt(Calendar.JULY)) try {
			CalendarSheetCenterImage sheetCI;
			sheetCI = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JULY, mDates);
			Draw1ImageI img0 = new Draw1ImageI(inDirWhatsAp0+"WhatsApp Bild 2023-07-02 um 11.48.54.jpg");
			sheetCI.setImage1(img0);
			if (CalendarSheet.fWeight < 2 || 3 < CalendarSheet.fWeight) {
				Draw1ImageI img;
				img = new Draw1ImageI(inDirG+"20210205_101418.jpg");
				img.dRot = 0;
				sheetCI.addImage1(img);
			}
			if (.9f < CalendarSheet.fWeight && CalendarSheet.fWeight < 1.96f) {
				Draw1ImageI img;
				img = new Draw1ImageI(inDirG+"20210108_094950.jpg");
				img.dRot = 0;
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20210308_103506.jpg");
				img.dRot = 90;
				img.setCenterPoint(.4f, .5f);
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20210308_103522.jpg");
				img.dRot = 90;
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirCo+"20210720_161347.jpg");
				img.dRot = 90;
				img.setCenterPoint(.4f, .5f);
				sheetCI.addImage1(img);
				for (int iI = 1; iI <= 4; ++iI)
					if (iI != 3) {
						img = new Draw1ImageI(inDirV21+"WhatsApp Image 2021-03-22 at 12.48.41 ("+ iI +").jpeg");
						sheetCI.addImage2(img);
				}
				img = new Draw1ImageI(inDirWhatsAp+"WhatsApp Image 2022-07-30 at 19.24.49.jpeg");
				img.setCenterPoint(.5f, .3f);
				sheetCI.addImage2(img);
			}
			addManyImages(sheetCI, new Random(THIS_YEAR), .3f);
			sheetCI.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.AUGUST)) {
			CalendarSheetCenterImage sheetCI;
			sheetCI = new CalendarSheetCenterImage(THIS_YEAR, Calendar.AUGUST, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirWhatsAp0+"WhatsApp Bild 2023-07-15 um 19.43.25.jpg"); // 1600x900
			sheetCI.setImage1(img);
			if (CalendarSheet.fWeight < 2 || 3 < CalendarSheet.fWeight) {
				img = new Draw1ImageI(inDirG+"20221202_114315.jpg");
				img.dRot = 90;
				sheetCI.addImage1(img);
			}
			if (CalendarSheetCenterImage.fWeight > 1.9f) {
				img = new Draw1ImageI(inDirG+"20221203_110020.jpg");
				img.dRot = 180;
				sheetCI.addImage1(img);
			}
			if (.9f < CalendarSheet.fWeight && CalendarSheet.fWeight < 1.96f) {
				img = new Draw1ImageI(inDirG+"20221202_114310.jpg");
				img.dRot = 90;
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20221202_114333.jpg");
				img.dRot = 0;
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20221202_114335.jpg");
				img.dRot = -.02f;
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20221202_114337.jpg");
				img.dRot = -.05f;
				sheetCI.addImage1(img);
				if (CalendarSheetCenterImage.fWeight > 1.9f) {
					img = new Draw1ImageI(inDirG+"20221203_105817.jpg");
					img.dRot = 180;
					img.setCenterPoint(.5f, .4f);
					sheetCI.addImage1(img);
				}
				img = new Draw1ImageI(inDirG+"20221203_105817_001.jpg");
				img.dRot = 180;
				img.setCenterPoint(.5f, .43f);
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20221203_105859.jpg");
				img.dRot = 180;
				sheetCI.addImage1(img);
				img = new Draw1ImageI(inDirG+"20221203_105850.jpg");
				img.dRot = 90;
				sheetCI.addImage1(img);
				img = new Draw1ImageI("C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Pictures\\Whatsapp\\IMG-20220410-WA0003.jpg");
				sheetCI.addImage2(img);
				final String strImages21 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\";
				img = new Draw1ImageI(strImages21+"WhatsApp Bild 2022-11-25 um 17.58.34.jpg");
				img.setCenterPoint(.5f, .3f);
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages21+"WhatsApp Bild 2022-12-24 um 01.39.57_2.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(inDirV21+"WhatsApp Image 2021-08-21 at 20.13.53 (2).jpeg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(inDirV21+"WhatsApp Image 2021-08-07 at 11.54.06 (1).jpeg");
				img.setCenterPoint(.5f, .4f);
				sheetCI.addImage2(img);
				img = new Draw1ImageI(inDirV21+"WhatsApp Image 2021-07-31 at 16.42.48.jpeg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(inDirV21+"WhatsApp Image 2021-08-10 at 16.47.13 (2).jpeg");
				sheetCI.addImage2(img);
				final String strImages = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Pictures\\";
				img = new Draw1ImageI(strImages+"signal-2021-08-14-183040.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages+"signal-2021-08-14-201821.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages+"signal-2022-06-25-22-54-48-898.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages+"signal-2022-06-25-22-55-22-518.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages+"signal-2022-06-25-23-10-37-955.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages+"signal-2021-09-05-135702.jpg");
				sheetCI.addImage2(img);
				img = new Draw1ImageI(strImages+"signal-2021-09-05-185155.jpg");
				img.setCenterPoint(.5f, 6f/16f);
				sheetCI.addImage2(img);
			}
			sheetCI.startMakingIt(strOutDir);
		}
		
		if (trgOpt.bDoIt(Calendar.SEPTEMBER)) try {
			var sheetCI = new CalendarSheetCenterImage(THIS_YEAR,  Calendar.SEPTEMBER, mDates);
			Draw1ImageWithTxt img1;
			img1 = new Draw1ImageWithTxt("C:\\Users\\MiRoe\\OneDrive\\Adesso\\Handy\\Pictures\\Camera\\20220821_174416b.jpg",
					                     "Fotografin: Sophie Viktoria Köhler (3 Jahre)",
					                     new Color(224, 32, 128), Font.SERIF, Font.ITALIC, 200 );
			sheetCI.setImage1(img1);
			addManyImages(sheetCI, new Random(THIS_YEAR-2022), .5f);
			sheetCI.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.OCTOBER)) try {
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.OCTOBER, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirWhatsApp+"IMG-20220319-WA0016b.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirWhatsApp+"IMG-20220320-WA0017.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirWhatsApp+"IMG-20220320-WA0015.jpg");
			img.setCenterPoint(0.45f, 0.5f);
			sheet.addImage(img);
			img = new Draw1ImageI(inDirWhatsApp+"IMG-20220320-WA0016.jpg");
			img.setCenterPoint(.6f, .5f);
			sheet.addImage(img);
			Thread executer = sheet.startMakingIt(strOutDir);
			executer.join();
			if (THIS_YEAR == 2024) {
				//renameToTitle(strOutDir, "2410.jpg");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.NOVEMBER)) try {
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.NOVEMBER, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirWhatsAp0+"WhatsApp Bild 2023-08-30 um 17.28.37.jpg");
			sheet.addImage(img);
			img = new Draw1ImageI(inDirWhatsAp0+"WhatsApp Bild 2023-08-30 um 17.28.38.jpg");
			img.setCenterPoint(.43f, .5f);
			sheet.addImage(img);
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
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-24 um 22.08.38.jpg",.5 , .5, 0,.256, y2, .17 , h2);
			sheet.drawImage(inDirG +                           "20221224_180530.jpg",.45, .6, 0,.432, y2, .136, h2);
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-24 um 22.08.44.jpg",.55, .5, 0,.574, y2, .17 , h2);
			sheet.drawImage(inDirWhatsAp0+"WhatsApp Bild 2022-12-25 um 12.11.54_3.jpg",.5,.5, 0, .75 , y2,.25 , h2);
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
			img.dRot = 0;
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
	}

	void addAndres2023Images(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint) {
		final String inDirAndre2023 = "C:\\Users\\MiRoe\\Pictures\\2023 Famile Senior Köhler\\";
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
	}
}

