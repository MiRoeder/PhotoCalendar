package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.Draw1ImageWithTxt;
import de.dreiroeders.workingonimages.SourceImage;

public class PhotoCalendar extends FotoKalender {

	public static void main(String[] args) {
		try {
			Date curTime = new Date();
			DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
			System.out.println("Procedure "+ PhotoCalendar.class.getName() +".main(args) started creating FotoKalender: "+ dateFormatter.format(curTime) +" "+ curTime.getTime() + " ms.");
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
		PhotoCalendar mainObj = new PhotoCalendar(trgOpt.m_nYear);
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

		//TODO all of these constants have to be replaced by your own paths to your pictures.
		final String inDirV21= "C:/Users/MiRoe/Pictures/2021 Vik/";
		final String inDirG = "C:/Users/MiRoe/Pictures/GalaxyS8/Camera/";
		final String inDirGP = "C:/Users/MiRoe/Pictures/GalaxyS8/Pictures/";
		final String inDirCo = "C:/Users/MiRoe/Pictures/Constanzess/Camera/";
		final String inDirWhatsApp= "C:/Users/MiRoe/Pictures/GalaxyS8/WhatsApp/Media/WhatsApp Images/";
		final String inDirWhatsAp0 = "C:/Users/MiRoe/Pictures/WhatsApp/";
		final String inDirRBB = "C:/Users/MiRoe/Videos/rbb Fernsehen Weihnachtssingen an der Alten Försterei/";

		m_trgOpt = trgOpt;
		
		addDates();
		
		/* TODO Replace the file paths by links to your own pictures :*/
		if (trgOpt.bDoIt(-1)) try { /* Titelbild */
			CalendarSheetCenterImage sheet = new CalendarSheetCenterImage(THIS_YEAR, Calendar.JANUARY, mDates);
			Draw1ImageI img;
			img = new Draw1ImageI(inDirRBB+"vlcsnap-2023-07-06-13h04m41s722_PS.jpg");
			sheet.setImage1(img);
			//TODO addAndres2023Images(sheet, null, null, 1);
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
			makeAllWaysToBerlin(Calendar.FEBRUARY, 10, strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.MARCH)) {
			try {
				final String inDir1 = "http://www.3roeders.de/OmasHaus/";
				CalendarSheet sheet = new CalendarSheet(THIS_YEAR, Calendar.MARCH, mDates);
				sheet.prepareImage(3000);
				double wSteg = 0.005; // interspace width
				double hSteg = wSteg*CalendarSheet.fWeight; // interspace height
				double hText = 0.1;
				double hTxt = hText-hSteg;  // text height
				double h1 = 0.4; // Height of the images in the first row
				double wImg2 = (1d-wSteg)/2; // Width of the image in the first row
				sheet.drawSerifText("Berliner Häuser",             0    ,  0.00,  1d  ,  hTxt);
				sheet.drawImage(inDir1+"IMG_0216.JPG", .5,.5, 0,   0    , hText, wImg2, h1);
				sheet.drawImage(inDir1+"IMG_0222.JPG", .5,.5, 0, 1-wImg2, hText, wImg2, h1);
				double y2 = hText+h1+hSteg; // Y-Coord of the images in the second row
				double wC2 = .2;			// Width of the image in the center of the second row 
				double w2 = (1-wC2)/2;		// Width of the other images in the second row
				sheet.drawImage(inDir1+"IMG_0231.JPG", .5,.5, 0,        0, y2,  w2         , 1-y2);
				sheet.drawImage(inDir1+"IMG_0246.JPG", .5,.5, 0, w2+wSteg, y2,  wC2-2*wSteg, 1-y2);
				sheet.drawImage(inDir1+"IMG_0202.JPG", .5,.5, 0,     1-w2, y2,  w2         , 1-y2);
				sheet.drawCalDates();
				sheet.writeInDir(strOutDir);
			} catch (Exception ex) {
				ex.printStackTrace();
			}  
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
			final String inDir = "Examples.res/";
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.MAY, mDates);
			Draw1ImageI img;
			if (CalendarSheet.fWeight > 1) {
				img = new Draw1ImageI(inDir+"EingangGülPark.jpg");
				sheet.addImage(img);
			}
			img = new Draw1ImageI(inDir+"BarcelonaKirchePanaroma2.jpg");
			sheet.addImage(img);
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
			var img1 = new SourceImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.29.31.jpg");
			img1.setSourceBounds(new Rectangle(270, 680, 540, 350));
			sheet.drawImage(img1.getImage(),                                          1f/2f, 1f/2f, 0, xCenterPic+wSteg     ,yCenterPic+hSteg     ,          wCenterPic-2*wSteg,         hCenterPic-2*hSteg);
			sheet.drawImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.15.18.jpg", 1f/2f, 4f/5f, 0, 0f                   ,                   0f, xCenterPic+wCenterPic-wSteg,yCenterPic                 );
			sheet.drawImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.15.49.jpg", 1f/2f, 1f/2f, 0, xCenterPic+wCenterPic,                   0f, 1f-xCenterPic-wCenterPic   ,yCenterPic+hCenterPic-hSteg);
			sheet.drawImage("Examples.res/351535115_1761041277631953_2772123894799380056_n.jpg",.5f,.5f,0, xCenterPic+wSteg     ,yCenterPic+hCenterPic,    1f-xCenterPic+wSteg     ,1f-yCenterPic-hCenterPic   );
			sheet.drawImage("Examples.res/351525974_776193984009985_4460773923173507186_n.jpg" ,.5f,.6f,0, 0f                   ,yCenterPic+hSteg     , xCenterPic                 ,1f-yCenterPic-hSteg        );
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();    
		}

		if (trgOpt.bDoIt(Calendar.JULY)) try {
			makeRiversHorseShoe(Calendar.JULY, strOutDir);
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
				img = new Draw1ImageI("C:/Users/MiRoe/Pictures/GalaxyS8/Pictures/Whatsapp/IMG-20220410-WA0003.jpg");
				sheetCI.addImage2(img);
				final String strImages21 = "C:/Users/MiRoe/Pictures/WhatsApp/";
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
				final String strImages = "C:/Users/MiRoe/Pictures/GalaxyS8/Pictures/";
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
			img1 = new Draw1ImageWithTxt("Examples.res/20220821_174416b.jpg",
					                     "Fotografin: Sophie Viktoria Köhler (3 Jahre)",
					                     new Color(224, 32, 128), Font.SERIF, Font.ITALIC, 200 );
			sheetCI.setImage1(img1);
			addManyPlaygroundImages(sheetCI, null, null, 1f);
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
			sheet.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.NOVEMBER)) try {
			MakeSheetShadesOfGrey.startMaking(500, THIS_YEAR, Calendar.NOVEMBER, mDates, strOutDir);
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
	
	public PhotoCalendar(int year) {
		super(year);
	}

	public void addDates() {
		/* TODO put your own events and dates here: Most of these methods are defined in base class FotoKalender :*/
		addFamilyEvents();
		addStephansFamilyEvents();
		//addOlafsFamilyEvents();
		addBerlinHolidays();
		//addBavarianPublicHolidays();
		addVacations(m_trgOpt.m_bBerlinVacations, false);
		addDaylightSavingTimeChanges();
		addAdvents();
		addSundays4Buying();
		if (m_trgOpt.m_bBundesliga) {
			MyFussballBundesliga.addMyFussballBundesliga(this);
		}
		if (m_trgOpt.m_bBundesliga2) {
			MyFussballBundesliga2.addMyFussballBundesliga2(this);
		}
		addSoccerIntEvent();
		addOlympiade();
		addSpecialEvents();
		addMuttertag();
		//addMunichWiesn(2);
	}
}
