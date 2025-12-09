package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import de.dreiroeders.io.MiRoeIoUtil;
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
		final String inDirRBB = "C:/Users/MiRoe/Videos/rbb Fernsehen Weihnachtssingen an der Alten F�rsterei/";

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
			makeAllWaysToBerlin(Calendar.FEBRUARY, 0, strOutDir);
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
				sheet.drawSerifText("Berliner H\u00E4user",                            0    ,  0.00,  1d  ,  hTxt);
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
			makeTramToDrogenbos(Calendar.APRIL, strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.MAY)) {
			final String inDir = "Examples.res/";
			CalendarSheetAutoArrange1 sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.MAY, mDates);
			Draw1ImageI img;
			if (CalendarSheet.fWeight > 1) {
				img = new Draw1ImageI(inDir+"EingangG\u00FClPark.jpg");
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
			sheet.drawImage(img1.getImage(),                                              1f/2f, 1f/2f, 0, xCenterPic+wSteg     ,yCenterPic+hSteg           ,        wCenterPic-2*wSteg ,hCenterPic-2*hSteg         );
			sheet.drawImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.15.18.jpg",      1f/2f, 4f/5f, 0, 0                    , 0f                        ,xCenterPic+wCenterPic-wSteg,yCenterPic                 );
			sheet.drawImage("Examples.res/WhatsApp Bild 2023-06-04 um 13.15.49.jpg",      1f/2f, 1f/2f, 0, xCenterPic+wCenterPic, 0f                        ,1f-xCenterPic-wCenterPic   ,yCenterPic+hCenterPic-hSteg);
			sheet.drawImage("Examples.res/351535115_1761041277631953_2772123894799380056_n.jpg",.5f,.5f,0, xCenterPic+wSteg     ,yCenterPic+hCenterPic      ,    1f-xCenterPic+wSteg    ,1f-yCenterPic-hCenterPic   );
			sheet.drawImage("https://www.3roeders.de/facebook/FB_IMG_1729759398884.jpg"        ,.5f,.6f,0, 0f                   ,yCenterPic+hSteg           , xCenterPic                ,1f-yCenterPic-hSteg        );
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
			makeHeartAntelopeCanyon(Calendar.AUGUST, strOutDir);
		}
		
		if (trgOpt.bDoIt(Calendar.SEPTEMBER)) try {
			var sheetCI = new CalendarSheetCenterImage(THIS_YEAR, Calendar.SEPTEMBER, mDates);
			Draw1ImageWithTxt img1;
			img1 = new Draw1ImageWithTxt("Examples.res/20220821_174416b.jpg",
					                     "Fotografin: Sophie Viktoria K\u00F6hler (3 Jahre)",
					                     new Color(224, 32, 128), Font.SERIF, Font.ITALIC, 200 );
			sheetCI.setImage1(img1);
			addManyPlaygroundImages(sheetCI, null, null, 1f);
			sheetCI.startMakingIt(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.OCTOBER)) try {
			make9Windows(Calendar.OCTOBER, strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (trgOpt.bDoIt(Calendar.NOVEMBER)) try {
            CalendarSheetCenterImage sheetCI = null;
            Draw1ImageI img = null;
            if (THIS_YEAR % 5 != 0) {
                img = new Draw1ImageI("https://www.3roeders.de/SamsungGalS23/20240604_155404a.jpg");
                if (img.isOk()) {
                    sheetCI = new CalendarSheetCenterImage(THIS_YEAR, Calendar.NOVEMBER, mDates);
                    sheetCI.setImage1(img);
                    sheetCI.addImage(new Draw1ImageI("https://gdkfiles.visitdenmark.com/files/382/164757_Nyhavn_Jacob-Schjrring-og-Simon-Lau.jpg?width=987"), null);
                    sheetCI.addImage(new Draw1ImageI("https://www.visitdenmark.de/sites/visitdenmark.com/files/styles/map_list/public/2019-03/Tivoli_Photo_Martin%20Heiberg_Copenhagen%20Media%20Center.jpg?h=170ba4b8&itok=7qOXyhyl"), null);
                    sheetCI.addImage(new Draw1ImageI("https://www.visitdenmark.de/sites/visitdenmark.com/files/styles/map_list/public/2019-03/Amalienborg-palace-copenhagen.jpg?h=4aa1a1ac&itok=rGM074dw"), null);
                    sheetCI.addImage(new Draw1ImageI("https://www.visitdenmark.de/daenemark/reiseplanung/canal-tours-grand-tour-copenhagen-gdk410731"), null);
                    sheetCI.addImage(new Draw1ImageI("https://www.daenemark.citysam.de/fotos-daenemark-i/kopenhagen-3.jpg"), null);
                    sheetCI.addImage(new Draw1ImageI("https://www.daenemark.citysam.de/fotos-daenemark-i/kopenhagen-4.jpg"), null);
                    sheetCI.addImage(new Draw1ImageI("https://www.shutterstock.com/de/image-photo/copenhagen-old-town-panorama-nyhavn-harbor-1900231666?trackingId=17d8bdc6-2cb6-4a0e-85b4-1c7b9f716260"), null);
                    sheetCI.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2024/02/26/12/15/photo-8597811_1280.jpg"), null);
                    sheetCI.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2013/03/15/20/06/copenhagen-94100_1280.jpg"), null);
                    sheetCI.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2020/04/25/11/56/metro-5090499_1280.jpg"), null);
                }
            }
            if (sheetCI != null && sheetCI.getNumberOfImage() >= 4) {
                sheetCI.makingIt(strOutDir);
                trgOpt.m_bDoIt[Calendar.NOVEMBER] = false; // because already created.
            }
		} catch (Exception ex) {
            MiRoeIoUtil.logException("Problem in calendar sheet NOVEMBER", ex);
		}

        if (trgOpt.bDoIt(Calendar.NOVEMBER)) try {
            MakeSheetShadesOfGrey.startMaking(500, THIS_YEAR, Calendar.NOVEMBER, mDates, strOutDir);
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem in calendar sheet NOVEMBER", ex);
        }

		if (trgOpt.bDoIt(Calendar.DECEMBER)) try {
            var sheet = new CalendarSheetAutoArrange1(THIS_YEAR, Calendar.DECEMBER, mDates);
            sheet.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2019/12/19/10/56/lights-4705882_1280.jpg"), null);
            sheet.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2020/06/15/05/44/cologne-5300430_1280.jpg"), null);
            sheet.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2014/12/10/15/25/weihnachts-zauber-563165_1280.jpg"), null);
            sheet.addImage(new Draw1ImageI("https://cdn.pixabay.com/photo/2014/12/13/15/55/christmas-566749_1280.jpg"), null);
            sheet.addImage(new Draw1ImageI("https://www.berlin.de/binaries/asset/image_assets/8580141/source/1701160202/1000x500/"), null);
            sheet.addImage(new Draw1ImageI("https://www.berlin.de/binaries/asset/image_assets/7880868/source/1704803197/1000x500/"), null);
            sheet.addImage(new Draw1ImageI("https://www.berlin.de/binaries/asset/image_assets/3696437/source/1417433746/1000x500/"), null);
            sheet.addImage(new Draw1ImageI("https://i.pinimg.com/736x/6b/97/78/6b9778d46e52d17f134a79c5e06703d3.jpg"), null);
            sheet.addImage(new Draw1ImageI("https://i.pinimg.com/originals/7e/a4/16/7ea4164d3b6907424cda0490c87c6804.jpg"), null);
            if (sheet.getNumberOfImage() >= 4) {
                sheet.makingIt(strOutDir);
                trgOpt.m_bDoIt[Calendar.DECEMBER] = false; // because already created.
            }
		} catch (Exception ex) {
            MiRoeIoUtil.logException("Problem in calendar sheet DECEMBER "+THIS_YEAR, ex);
		}

        if (trgOpt.bDoIt(Calendar.DECEMBER)) try {
            makeWhiteXMas(strOutDir);
        } catch (Exception ex) {
            MiRoeIoUtil.logException("Problem in calendar sheet DECEMBER "+THIS_YEAR, ex);
        }

        System.out.println("Kalender "+ THIS_YEAR +" creating / created in "+ strOutDir);
	} /* end of makeFamilyCal(int year) */
	
	public PhotoCalendar(int year) {
		super(year);
	}

	public void addDates() {
		/* TODO put your own events and dates here: Most of these methods are defined in base class FotoKalender :*/
		//addFamilyEvents();
		//addStephansFamilyEvents();
		//addOlafsFamilyEvents();
        addBerlinGrueneWoche();
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
