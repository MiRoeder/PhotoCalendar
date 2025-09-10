package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeFileExtFilter;
import de.dreiroeders.io.MiRoeIoUtil;
import de.dreiroeders.workingonimages.BufferedImageSetPixImg_ABGR;
import de.dreiroeders.workingonimages.BuffrdImgSetPixelD;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.EFillType;
import de.dreiroeders.workingonimages.IHintsDrawImages;
import de.dreiroeders.workingonimages.MiRoesDraw;
import de.dreiroeders.workingonimages.SourceImage;


public class FotoKalender {

	public final int THIS_YEAR;
	public static final String LOCALE_MONTH = "";
	
	public FotoKalenderOpt m_trgOpt;
	
	protected PersonalDates mDates;
	
	public FotoKalender(int year) {
		THIS_YEAR = year < 100 ? 2000+year : year;
		mDates = new PersonalDates(THIS_YEAR);
	}

	public static final String inDirMsGal23 = "C:\\Users\\MiRoe\\Pictures\\SamsungGalS23\\DCIM\\Camera\\";

	public void makeViksGeburtstag(int nMonth, String strOutDir) {
		try {
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, this.mDates);
			final String inDir1 = "C:\\Users\\MiRoe\\Pictures\\2019 Sophie Viktoria\\1_Abend_der_Geburt\\";
			final String inDirV21 = "C:\\Users\\MiRoe\\Pictures\\2021 Vik\\";
			final String inDirConny = "C:\\Users\\MiRoe\\Pictures\\Constanzess\\Camera\\";
			final String inDirStep = "C:\\Users\\MiRoe\\Pictures\\Bilder von Stephan 2022\\2022_04_10 Kindergeburtstag\\";
			final String inDirWhatsApp= "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\WhatsApp\\Media\\WhatsApp Images\\";
			sheet.prepareImage(3867);
			Color colTxt = sheet.getDefaultTextCol();
			String strLine2;
			if (nMonth == Calendar.MARCH) {
				strLine2 = "Meldung rausschicken: Die Prinzessin vollendet ihr "+ (THIS_YEAR-2019) +". Lebensjahr.";
			} else {
				strLine2 = "Alle huldigen der Prinzessin";
			}
			float y3 = CalendarSheet.fWeight / 1.55f;
			float y5 = 0.44f*y3;
			float y7 = CalendarSheet.fWeight > 1 ? 1f : 0.6f;
			float h2 = y7-y5;
			if (h2 < .2f) {
				System.err.println("Problem in  makeViksGeburtstag(,) : h2 = "+ h2);
			} else {
				System.out.println("In  makeViksGeburtstag(,) : h2 = "+ h2);
			}
			sheet.drawText("Im Pressezentrum Berlin-Mahlsdorf:", colTxt, "Serif",     0,         0.000, 0.000*y3, 1    , 0.06 *y3 );
			sheet.drawText(strLine2,                             colTxt, "Serif",     0,         0.000, 0.062*y3, 1    , 0.05 *y3);	
			sheet.drawText("Die Menge wartet gespannt:",         colTxt, "Serif",     0,         0.000, 0.115*y3, 0.37 , 0.03 *y3);	
			sheet.drawImage(inDir1+"20190331_175443.jpg",                           0.55,0.5,180,0.000, 0.15 *y3, 0.37 , 0.280*y3);
			sheet.drawImage(inDir1+"20190331_182041.jpg",                           0.5, 0.54,0, 0.375, 0.115*y3, 0.31 , 0.315*y3);
			sheet.drawImage(inDir1+"WhatsApp Image 2019-03-31 at 20.09.13_heller.jpg",.5, .5, 0, 0.690, 0.115*y3, 0.31 , 0.315*y3);
			/* Berechne genaue Position von  Geburtstagstorte , damit nichts abgeschnitten wird. */
			SourceImage imgTorte6 = new SourceImage("C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik7\\Torte.png");
			int widTorte6 = imgTorte6.getWidth();
			int heiTorte6 = imgTorte6.getHeight();
			float facT6 = Math.min( sheet.getUsuableWidth() * 0.4f / widTorte6, sheet.getUsuableHeight() * h2 / heiTorte6 );
            int tWidT6 = (int)(widTorte6 * facT6);
			int tHeiT6 = (int)(heiTorte6 * facT6);
			sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\WhatsApp\\Vik7\\Vik7.jpg",.48,0.5, 0, 0.000f, y5,       0.35 , h2);
			int y6 = (int)(sheet.getUsuableHeight() * y5 + tHeiT6*.9f);
			sheet.drawImage(inDirMsGal23+"20250406_152442.jpg",                   .5, 0.5, 90, 0.650,  y5,     0.35, h2);
			float fH8 = h2*.4f;
			sheet.drawImage(inDirMsGal23+"20250406_152425.jpg",                   .4, 0.5, 90, 0.354,  1-fH8, 0.29, fH8);
			sheet.drawImage(imgTorte6,                                                        .5, 0.5, 0, 0.3f,   y5,     tWidT6  ,tHeiT6);
			makeNumberOnViksGeburtstagskuchen(sheet,                                          0.480f,y5+0.16f ,tWidT6/4, tHeiT6/4);
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end of makeViksGeburtstag(int nMonth, String strOutDir) */
	
	public void makeNumberOnViksGeburtstagskuchen(CalendarSheet sheet, float fX, float fY, int iW, int iH) {
		Graphics2D painter = null;
		try {
			BufferedImage sColsYears = new BufferedImage(2, 2, BufferedImage.TYPE_3BYTE_BGR);
			sColsYears.setRGB(0, 0, new Color(160, 192, 160).getRGB());
			sColsYears.setRGB(0, 1, new Color(160, 184,  80).getRGB());
			sColsYears.setRGB(1, 0, new Color( 64, 184, 160).getRGB());
			sColsYears.setRGB(1, 1, new Color( 64, 176,  80).getRGB());
			String strNumber = Integer.toString(THIS_YEAR-2019);
			BufferedImage tmpBuf1 = new BufferedImage(iW, iH, BufferedImage.TYPE_4BYTE_ABGR);
			painter = tmpBuf1.createGraphics();
			//OCR-A BT  i2434.png
			Font font = new Font("OCR-A BT", Font.BOLD, iH/2);
			MiRoesDraw.drawTextImg(strNumber, font, sColsYears, painter, iW/4, iH/2);
			MiRoesDraw.diagOut(tmpBuf1);
			sheet.drawImage(tmpBuf1, .5f, .5f, -.05, fX, fY, iW, iH);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (painter != null) {
			painter.dispose();
		}
	}
	
	
	public void makeWhiteXMas(String strOutDir) {
		try {
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, Calendar.DECEMBER, this.mDates);
			sheet.prepareImage(3494);
			sheet.drawText("Weiße Weihnachten?", sheet.getDefaultTextCol(), "Times New Roman", 0, 0, 0.00, 1, 0.04);
			sheet.drawText("23. Dezember 2012:", sheet.getDefaultTextCol(), "Times New Roman", 0, 0, 0.05, 1, 0.03);
			sheet.drawImage("http://www.3roeders.de/Dezember2012/2012-12-23.png",
					                                                                0.5, 0.5,  0, 0, 0.09, 1, 0.41 );
			sheet.drawText("24. Dezember 2012:", sheet.getDefaultTextCol(), "Times New Roman", 0, 0, 0.51, 1, 0.03);
			sheet.drawImage("http://www.3roeders.de/Dezember2012/2012-12-24%20ohne%20Giebel.png",
                                                                                    0.5, 0.55, 0, 0, 0.55, 1, 0.45 );
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	public void makeDontSleepInBed(int nMonth, String strOutDir) {
		try {
			final boolean bFullHight = true;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, this.mDates);
			final int width = 6000;
			sheet.prepareImage(width);
			int height = sheet.getTotalHeight()*19/20;
			float fx0 = 0.14f;
			int x0 = (int)(fx0*width+0.5f);
			
			float h1 = bFullHight ? 0.05f : 0.04f;
			Font font1 = new Font("Arial", Font.PLAIN, (int)(h1*height));
			int yL = (int)(2*h1*height);
			int xt0 = (width-x0-2459)/2+x0;
			int wi = sheet.drawText("Im Bett Schlafen ist uncool:", font1, Color.BLACK, xt0, yL);
			System.out.println("Text Breite: "+ x0 +" "+ (xt0-x0) +" "+ wi +" "+ (width-xt0-wi) );

			float h2 = 2*h1+0.01f;
			float hpic = bFullHight ? 0.4f : 0.38f;
			float hpic1 = hpic-0.03f;
			float hpic2 = hpic+0.03f;
			float fw10 = 0.19f;
			float fx2 = fx0 + fw10;
			float fw2 = (1-fx2)/2;
			sheet.drawImage("in\\Adriana\\0\\00_0005.jpg"   , fx0     , h2,fw10 , hpic1);
			sheet.drawImage("in\\Adriana\\0\\I034.jpg"	    , fx2     , h2, fw2 , hpic1);
			sheet.drawImage("F:\\Pictures\\2012-10-erste-Sony-Fotos\\100MSDCF\\DSC00031.jpg"   ,
											  100, 500, 0, 0, fx2+ fw2 ,h2, fw2 , hpic1 );
			
			fw2 = (1-fx0)/2;
			sheet.drawImage("in\\Adriana\\2\\France3_0003.jpg",            fx0     ,h2+hpic1, fw2 , hpic2);
			sheet.drawImage("F:\\Pictures\\2013 Kopenhagen\\P1100484.jpg", fx0+fw2 ,h2+hpic1, fw2 , hpic2);

			wi = sheet.drawText("Im Bett Schlafen ist uncool:", font1, Color.BLACK, xt0, yL);
			System.out.println("Text Breite: "+ x0 +" "+ (xt0-x0) +" "+ wi +" "+ (width-xt0-wi) );
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeGeniessenAutobahnVsZug(int nMonth, String strOutDir) {
		try {
			final String inDir1 = "C:\\Users\\MiRoe\\Pictures\\2024_Vik\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, mDates);
			sheet.prepareImage(3000);
			double hText = 0.07;
			double hTxt = hText-0.003;  // text height
			int iText2 = sheet.getUsuableHeight()*14/100;
			sheet.drawSerifText("Manche Leute genießen das Leben in vollen Zügen.",                  0,    0.00, 1d, hTxt     );
			sheet.drawSerifText("Andere lieber auf der Autobahn.",                                   0,   hText, 1d, hTxt     );
			Path2D trapezShape;
			trapezShape = new Path2D.Double();
			trapezShape.moveTo(sheet.getX1(), sheet.getY(0.05)+iText2);
			trapezShape.lineTo(sheet.getX(0.4), sheet.getY(0.05)+iText2);
			trapezShape.lineTo(sheet.getX(0.605), sheet.getY(1));
			trapezShape.lineTo(sheet.getX1(), sheet.getY(1));
			trapezShape.closePath();
			sheet.drawImage(inDirMsGal23+"20230908_155301.jpg",                         .5, .5, 0, trapezShape);
			trapezShape = new Path2D.Double();
			trapezShape.moveTo(sheet.getX(0.395), sheet.getY1()+iText2);
			trapezShape.lineTo(sheet.getX(1), sheet.getY1()+iText2);
			trapezShape.lineTo(sheet.getX(1), sheet.getY(.95));
			trapezShape.lineTo(sheet.getX(0.6), sheet.getY(.95));
			trapezShape.closePath();
			sheet.drawImage(inDir1+"WhatsApp Bild 2024-07-08 um 12.55.31_5bbc7308.jpg", .4, .5, 0, trapezShape);
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}  
	}

	public void makeSportIsMord(int nMonth, String strOutDir) {
		try {
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, this.mDates);
			final String inDir1 = "C:\\Users\\MiRoe\\Pictures\\2014-04 Prettin\\";
			final String inDir2 = "C:\\Users\\MiRoe\\Pictures\\ForCal2015\\";
			final String inDir3 = "C:\\Users\\MiRoe\\Pictures\\Von Michaels NOKIA Lumia 900\\Filmrolle\\";
			sheet.prepareImage(5000);
			sheet.drawImage(inDir1+"IMG_3729.jpg", 0.5, 0.5, 0,                      0.000, 0.052, 0.391, 0.443);
			sheet.drawImage(inDir1+"IMG_3715.jpg", 0.5, 0.5, 0,                      0.400, 0.057, 0.391, 0.438);
			sheet.drawImage(inDir2+"IMAG1916.jpg", 0.5, 0.5, 0,                      0.800, 0.052, 0.200, 0.443);
			sheet.drawImage(inDir1+"IMG_3723.jpg", 0.5, 0.5, 0,                      0.000, 0.505, 0.496, 0.495);
			sheet.drawImage(inDir2+"IMG_3764.jpg", 0.5, 0.5, 0,                      0.505, 0.505, 0.246, 0.495);
			sheet.drawImage(inDir3+"WP_000931.jpg",0.5, 0.5, 0,                      0.760, 0.505, 0.240, 0.245);
			sheet.drawImage(inDir3+"WP_000934.jpg",0.5, 0.6, 0,                      0.760, 0.760, 0.240, 0.240);
			sheet.drawText("Sport ist Mord:", sheet.getDefaultTextCol(), "Serif", 0, 0.000, 0.000, 1    , 0.05 );
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeHarzPlusHexe(int nMonth, String strOutDir) {
		try {
			String s0 = "C:\\Users\\MiRoe\\Pictures\\2012 Harz\\";
			int x0 = 700;
			int width = 2816/2;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, this.mDates);
			sheet.prepareImage(width*3+x0, new Color(224, 255, 224));
			int height = sheet.getUsuableHeight()/3;
			if (height > 2816) {
				height = 2816;
			}
			sheet.drawImage(s0+"P1100088.jpg", 0.5f, 0f, 0, 0,        x0,       0, width, height);
			sheet.drawImage(s0+"P1100089.JPG", 0.5f, 0f, 0, 0,  width+x0,       0, width, height);
			sheet.drawImage(s0+"P1100091.JPG", 0.5f, 0f, 0, 0,2*width+x0,       0, width, height);
			sheet.drawImage(s0+"P1100180.jpg", 0.5f, 0f, 0, 0,        x0,  height, width, height);
			sheet.drawImage(s0+"Adi auf Hexenbesen.JPG", Math.max(width/1728f, height/1376f),
													 0f, 0, 0,  width+x0,  height, width, height);
			sheet.drawImage(s0+"P1100196.JPG", 0.5f, 0f, 0, 0,2*width+x0,  height, width, height);
			sheet.drawImage(s0+"P1100275.jpg", 0.5f, 0f, 0, 0,        x0,2*height, width, height);
			sheet.drawImage(s0+"P1100226.JPG", 0.5f, 0f, 0, 0,  width+x0,2*height, width, height);
			sheet.drawImage(s0+"P1100301.JPG", 0.5f, 0f, 0, 0,2*width+x0,2*height, width, height);
			sheet.drawCalDates();
			sheet.writeJpg(strOutDir+ (nMonth+1) +".jpg");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public void makeOmaSagtZiehDichWarmAn(int month, String strOutDir) {
		try {
			Color bckCol = new Color(224, 224, 255);
			// WP_001307.jpg 2448 x 3262 pixels
			int picW = 2448;
			int picH = 3262;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(picW, picH, bckCol);
			int height = sheet.getUsuableHeight();
			System.out.println("'Oma sagt, warm anziehen' Gesamthöhe: " + height);
			int x0 = sheet.getX(0.0);
			int x1 = sheet.getX(0.5)-picW/2;
			int x05=x1 - (int)(800/1.777*sheet.fWeight); // 'warm' Breite:749 wenn fWight = 1.776
			int x2 = sheet.getX(0.5)+picW/2;
			int x3 = sheet.getX(1.0);
			int y0 = sheet.getY(0);
			
			sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\Von Michaels NOKIA Lumia 900\\Filmrolle\\WP_001307.jpg",
						0, 0, x1, y0, picW, picH );
			
			int y2 = 0;
			sheet.drawAmbiLightH(x1+1, x0, y0+y2, y0+y2+picH, picW/20);
			sheet.drawAmbiLightH(x2-1, x3, y0+y2, y0+y2+picH, picW/20);
			
			Color txtCol = new Color(32, 32, 128);
			int h1 = (int)(height/11f/1.777*sheet.fWeight);
			Font font1 = new Font("Arial", Font.PLAIN, h1);
			int yL = 2*h1+10;
			int wi = sheet.drawText("Oma", font1, txtCol, x05, yL);
			System.out.println("'Oma' Breite:"+ wi);
			yL += h1+10;
			wi = sheet.drawText("sagt:", font1, txtCol, x05, yL);
			System.out.println("'sagt' Breite:"+ wi);
			
			font1 = new Font("Serif", Font.PLAIN, h1);
			yL += h1+10+h1/2;
			wi = sheet.drawText("„Zieh", font1, txtCol, x05, yL);
			System.out.println("'Zieh Dich Breite:"+ wi);
			yL += h1+10;
			wi = sheet.drawText("Dich", font1, txtCol, x05, yL);
			System.out.println("Zieh Dich Breite:"+ wi);			
			yL += h1+10;
			wi = sheet.drawText("warm", font1, txtCol, x05, yL);
			System.out.println("'warm' Breite:"+ wi);
			yL += h1+10;
			wi = sheet.drawText("an!“", font1, txtCol, x05, yL);
			
			font1 = new Font("Arial", Font.PLAIN, h1);
			yL = 2*h1+10;
			wi = sheet.drawText(" Oma:", font1, txtCol, x2, yL);
			
			font1 = new Font("Serif", Font.PLAIN, h1);
			yL += h1+10+h1/2;
			wi = sheet.drawText(" „Ist der", font1, txtCol, x2, yL);
			yL += h1+10;
			wi = sheet.drawText(" Mantel", font1, txtCol, x2, yL);
			yL += h1+10;
			wi = sheet.drawText(" nicht", font1, txtCol, x2, yL);
			yL += h1+10;
			wi = sheet.drawText(" zu", font1, txtCol, x2, yL);
			yL += h1+10;
			wi = sheet.drawText(" dünn?“", font1, txtCol, x2, yL);

			sheet.drawCalDates();
			sheet.writeJpg(strOutDir+ (month<9?"0":"") + (month+1) +".jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void makeFamilyInZooWider(int month, String strOutDir) {
		try {
			Color bckCol = new Color(255, 224, 192);
			// 20210514_131018.jpg 3072 x 4032 pixels
			// 20210514_131018c.jpg 3015 x 2801 pixels
			int picW = 3015;
			int picH = 2801;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(picW, picH, bckCol);
			int height = sheet.getUsuableHeight();
			System.out.println("'Familie im Zoo' Gesamthöhe: " + height);
			int x0 = sheet.getX(0.0);
			int x1 = sheet.getX(0.5)-picW/2;
			int x2 = sheet.getX(0.5)+picW/2;
			int x3 = sheet.getX(1.0);
			int y0 = sheet.getY(0);
			
			sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\Constanzess\\Camera\\20210514_131018c.jpg",
						0, 0, x1, y0, picW, picH );
			
			int y2 = 0;
			sheet.drawAmbiLightH(x1+1, x0, y0+y2, y0+y2+picH, picW/20);
			sheet.drawAmbiLightH(x2-1, x3, y0+y2, y0+y2+picH, picW/20);
			
			sheet.drawCalDates();
			sheet.writeJpg(strOutDir+ (month<9?"0":"") + (month+1) +".jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public void makeAmbiLightH(int month, String strOutDir) {
		try {
			// C:\Users\MiRoe\Pictures\2020 Vik\Viktoria 2020\20200906_103647.jpg 1960 x 4032 pixels
			final int picW =  777;
			final int picH = 1600;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(picW+400, picH+400, sheet.getDefaultBackgroundCol());
			int height = sheet.getUsuableHeight();
			System.out.println("'AmbiLightH' Gesamtbreite: "+ sheet.getUsuableWidth() +" Gesamthöhe: " + height);
			int x0 = sheet.getX(0.0);
			int x1 = sheet.getX(0.5)-picW/2;
			int x2 = sheet.getX(0.5)+picW/2;
			int x3 = sheet.getX(1.0);
			int y0 = sheet.getY(0);
			
			sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\2019 Sophie Viktoria\\WhatsApp\\WhatsApp Image 2019-08-15 at 20.32.12(1).jpeg",
						0, 0, x1, y0, picW, picH );
			sheet.diag();
			
			int y2 = 0;
			sheet.drawAmbiLightH(x1+1, x0, y0+y2, y0+y2+picH, 10);
			sheet.drawAmbiLightH(x2-1, x3, y0+y2, y0+y2+picH, 10);
			
			sheet.drawCalDates();
			sheet.writeJpg(strOutDir+ (month<9?"0":"") + (month+1) +".jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void makeAmbiLightHD(int month, String strOutDir) {
		try {
			// C:\Users\MiRoe\Documents\E\Dev\Java\workspace\FotoKalender\tmp\2021\1.7\1.0\09d.jpg 
			// linke obere Ecke 1012, 2247
			// rechte obere Ecke 1012, 288
			// linke untere Ecke 6040, 2247
			// rechte untere Ecke 6040, 288
			final int picW = 1960;
			final int picH = 6040-1012+1;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(picW, picH, sheet.getDefaultBackgroundCol());
			int height = sheet.getUsuableHeight();
			System.out.println("'AmbiLightH' Gesamtbreite: "+ sheet.getUsuableWidth() +" Gesamthöhe: " + height);
			int x0 = sheet.getX(0.0);
			int x1 = sheet.getX(0.5)-picW/2;
			int x2 = sheet.getX(0.5)+picW/2;
			int x3 = sheet.getX(1.0);
			int y0 = sheet.getY(0);
			
			File inFile = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\2021\\1.7\\1.0\\09d.jpg");
			BufferedImage inImage = ImageIO.read(inFile);
			AffineTransform rotator = new AffineTransform(0, +1, -1, 0, x1+picW+288, y0-1012);
			sheet.drawImage(inImage, rotator);
			sheet.diag();
			
			int y2 = 0;
			sheet.drawAmbiLightH(x1+1, x0, y0+y2, y0+y2+picH, picW/400);
			sheet.drawAmbiLightH(x2-1, x3, y0+y2, y0+y2+picH, picW/400);
			
			sheet.drawCalDates();
			sheet.writeJpg(strOutDir+ (month<9?"0":"") + (month+1) +".jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void makeBerlinerKoennenAlles(int month, String strOutDir) {
		try {
			String s0 = "in\\";
			int x0 = 350;
			int width = 700;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(width*3+x0);
			int height = sheet.getUsuableHeight();
			
			int h1 = height/3;
			Font font1 = new Font("Arial", Font.PLAIN, h1);
			int yL = h1+10;
			int wi = sheet.drawText("Berliner", font1, Color.BLACK, x0+244, yL);
			System.out.println("Berliner Breite:"+ wi +" "+ (width*3-wi)/2);
			
			h1 = height/5;
			font1 = new Font("Serif", Font.PLAIN, h1);
			yL += h1+10;
			wi = sheet.drawText("können alles", font1, Color.BLACK, x0+346, yL);
			System.out.println("kön all Breite:"+ wi +" "+ (width*3-wi)/2);
			
			h1 = height/4;
			font1 = new Font("Serif", Font.PLAIN, h1);
			yL += h1+10;
			wi = sheet.drawText("außer", font1, Color.BLACK, x0+661, yL);
			System.out.println("außer Breite:"+ wi +" "+ (width*3-wi)/2);
			
			sheet.drawImage(s0+"Flughafen.jpg", 0, 0, x0, yL, 620, 310);
			sheet.drawImage(s0+"SBahn420_dpa.jpg", 1.5f, 0f, 0, 0, x0+width, yL, 627, 314);
			sheet.drawImage(s0+"1020_paderborn-allagui_01_656x369.jpg", 0, 0, x0+2*width, yL, 655, 369);

			sheet.drawCalDates();
			sheet.writeJpg(strOutDir+ (month+1) +".jpg");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public void make9Windows(int month, String strOutDir) {
		try {
			final String s0 = "http://www.3roeders.de/USA2015/";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			final int USABLE_WIDTH = 4960;	// maximale Kantenlänge bei posterxxl.de angeblich 8000 pixel
			sheet.prepareImage(USABLE_WIDTH, new Color(255, 224, 192));
			int height = sheet.getUsuableHeight();
			
			int h1 = height/21;
			Font font1 = new Font("Arial", Font.PLAIN, h1);
			int yL = h1;
			final String TITLE = "Windows 9, 10 oder 11";
			int wi = sheet.getTextWidth(TITLE, font1);
			System.out.println("Text Breite:"+ wi);
			int x0 = sheet.getX(0.5);
			sheet.drawText(TITLE, font1, Color.BLACK, x0-wi/2, yL);
			
			if (sheet.fWeight > 1.0) {
				sheet.drawImage(s0+"DSC04911.JPG", 0.5,  0.5, 0f, 0.00, 0.05, 0.31, 0.31);
				sheet.drawImage(s0+"DSC04838.JPG", 0.5,  0.3, 0f, 0.32, 0.05, 0.36, 0.30);
				sheet.drawImage(s0+"DSC04833.JPG", 0.5,  0.4, 0f, 0.69, 0.05, 0.31, 0.31);
				sheet.drawImage("https://upload.wikimedia.org/wikipedia/commons/b/b2/Delicate_Arch_Arches_NP.JPG",
												   0.5,  0.5, 0f, 0.00, 0.37, 0.31, 0.36);
				sheet.drawImage(s0+"DSC04889.JPG", 0.5,  0.5, 0f, 0.32, 0.36, 0.36, 0.37);
				sheet.drawImage(s0+"DSC04888.JPG", 0.5,  0.4, 0f, 0.69, 0.37, 0.31, 0.36);
				//sheet.drawImage(s0+"DSC04877.JPG", 0.5,  0.5, 0f, 0.00, 0.74, 0.31, 0.26);
				sheet.drawImage(s0+"DSC04840.JPG", 0.5,  0.5, 0f, 0.00, 0.74, 0.31, 0.26);
				sheet.drawImage(s0+"DSC04891.JPG", 0.5,  0.5, 0f, 0.32, 0.74, 0.36, 0.26);
				sheet.drawImage(s0+"DSC04882.JPG", 0.5,  0.5, 0f, 0.69, 0.74, 0.31, 0.26);
			} else {
				sheet.drawImage(s0+"DSC04809.JPG", 0.5,  0.5,0.05f,0.00,0.05, 0.495, 0.186);
				sheet.drawImage(s0+"DSC04911.JPG", 0.5,  0.5, 0f, 0.50, 0.05, 0.495, 0.186);
				sheet.drawImage(s0+"DSC04838.JPG", 0.5,  0.3, 0f, 0.00, 0.24, 0.495, 0.156);
				sheet.drawImage(s0+"DSC04833.JPG", 0.5,  0.4, 0f, 0.50, 0.24, 0.495, 0.156);
				sheet.drawImage("https://upload.wikimedia.org/wikipedia/commons/b/b2/Delicate_Arch_Arches_NP.JPG",
												   0.5,  0.45,0f, 0.00, 0.40, 0.495, 0.250);
				sheet.drawImage(s0+"DSC04889.JPG", 0.5,  0.5, 0f, 0.50, 0.40, 0.495, 0.250);
				sheet.drawImage(s0+"DSC04888.JPG", 0.45, 0.4, 0f, 0.00, 0.654,0.495, 0.156);
				sheet.drawImage(s0+"DSC04840.JPG", 0.5,  0.5, 0f, 0.50, 0.654,0.495, 0.156);
				sheet.drawImage(s0+"DSC04891.JPG", 0.5,  0.5, 0f, 0.00, 0.814,0.495, 0.186);
				sheet.drawImage(s0+"DSC04882.JPG", 0.5,  0.5, 0f, 0.50, 0.814,0.495, 0.186);				
			}

			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeHeartAntelopeCanyon(int month, String strOutDir) {
		try {
			final String inDir1 = "http://www.3roeders.de/USA2015/";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(4000, new Color(63, 0, 3));
			// DSC05435.jpg : 3264 Pixel x 4912 Pixel
			sheet.drawPartImage(inDir1+ "DSC05435.jpg", 0.5, 0.5, 4600, 3000,
					                                          1.10,  0.0  , 0.0,                         0.90, 1);
			
			sheet.drawImage(inDir1+ "DSC05396.JPG", 0.5, 0.5, 0.00,  0.0  , 0.00                       , 0.17, 0.20);
			sheet.drawImage(inDir1+ "DSC05389.JPG", 0.3, 0.5, 0.00,  0.0  , sheet.getflLastY() + 0.005f, 0.17, 0.13);
			sheet.drawImage(inDir1+ "DSC05397.JPG", 0.5, 0.5, 90,    0.0  , sheet.getflLastY() + 0.005f, 0.17, 0.27);
			sheet.drawImage(inDir1+ "DSC05398.JPG", 0.5, 0.5, 0.00,  0.0  , sheet.getflLastY() + 0.005f, 0.17, 0.19);
			float curY = sheet.getflLastY() + 0.01f;
			if (curY < 0.97) {
			    sheet.drawImage(inDir1+ "DSC05468_Calendar.png",
                 	                                0.5, 0.5, 0.00,  0.0,  curY,                         0.25, 1-curY);
			}
			sheet.drawImage(inDir1+ "DSC05408.JPG", 0.48,0.55,90,    0.77,  0.0,      0.23, 0.75);
			SourceImage inFile = new SourceImage(inDir1+ "DSC05460.JPG");
			BufferedImage inImage = inFile.getImage();
			BufferedImageSetPixImg_ABGR outImg = new BufferedImageSetPixImg_ABGR(inImage, 1f);
			int wD = inImage.getWidth()*3/4;
			int hD = inImage.getHeight()*19/30;
			for (int iX = 0; iX < wD; ++iX) {
				float fMY = (float)(wD-iX)/wD;
				for (int iY = 0; iY < fMY*hD; ++iY) {
					float alpha = 2 - fMY - (float)(hD-iY)/hD;
					if (alpha < 0.00001 || 0.99999 < alpha || 0.49999 < alpha && alpha < 0.50001) {
						System.out.println("x= "+ iX +", y="+ iY +", alpha="+ alpha);
					}
					alpha = 2*alpha-1;
					outImg.setAlpha(iX, iY, alpha);
				}
			}
			sheet.drawImage(outImg.getImage(0),     0.5, 0.4, 0.00,  0.67 , 0.75,      0.33, 0.25);
			sheet.getPainter(); // to restore sheet.mPainter
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end of makeHeartAntelopeCanyon(,) */
	
	public void makeHeartAntelopeCanyonH(int month, String strOutDir) {
		try {
			final String inDir1 = "C:\\Users\\MiRoe\\Pictures\\2015-Amerika\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(6818, new Color(26,5,4));
			sheet.drawImage(inDir1+ "DSC05436.jpg", 0.6, 0.5, 1.51,  0.00 , 0.00, 1.00, 0.91);
			sheet.drawImage(inDir1+ "DSC05396.jpg", 0.5, 0.5, 0.00,  0.0  , 0.89, 0.24, 0.11);
			sheet.drawImage(inDir1+ "DSC05397.jpg", 0.5, 0.5, 90,    0.56 , 0.87, 0.20, 0.13);
			sheet.drawImage(inDir1+ "DSC05398.jpg", 0.5, 0.5, 0.00,  0.77 , 0.87, 0.23, 0.13);
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end of makeHeartAntelopeCanyonH(,) */
	
	public void makeMeinBugDeinBug(int month, String strOutDir) {
		try {
			final String inDir1 = "C:\\Users\\MiRoe\\Pictures\\";
			File inFile = new File(inDir1+"MeinBug1.jpg");
			BufferedImage inImage1 = ImageIO.read(inFile);
			int srcWidth = inImage1.getWidth();
			float wi = 0.58f;
			float hi = 0.67f;
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage((int)(srcWidth/wi));
			BuffrdImgSetPixelD outImg1 = new BuffrdImgSetPixelD(inImage1);
			int overLapX = (int)((2*wi-1)/wi*srcWidth);
			int overLapY = (int)((2*hi-1)/hi*inImage1.getHeight());
			for (int iX = 0; iX < overLapX; ++iX) {
				for (int iY = 0; iY < overLapY; ++iY) {
					outImg1.setAlpha(srcWidth-iX-1, iY, (float)(iX+iY)/(overLapX+overLapY));
				}
			}
			sheet.drawImage(inDir1+"MeinBug2.jpg", 0.5f, 0.5f, 0.0, 1-wi, 0.00, wi, hi);
			sheet.drawImage(outImg1.getImage(),    0.5f, 0.5f, 0.0, 0.00, 1-hi, wi, hi);
			sheet.drawImage("C:\\Users\\MiRoe\\Pictures\\lumas.de\\showimg_mtr15_full.jpg", 0.5f, 0.6f, 0.0,wi+0.01, 0.74, 0.99-wi, 0.26);
			sheet.drawText("Mein Bug?", sheet.getDefaultTextCol(), "Serif", 0,            0.05, 0.05, 0.93-wi, 0.1);
			sheet.drawText("Dein Bug?", sheet.getDefaultTextCol(), "Serif", 0,            0.05, 0.17, 0.93-wi, 0.1);
			sheet.drawText("Bugs sind für uns alle da",
						               sheet.getDefaultTextCol(), "Serif", 0,             wi+0.01,hi, 0.98-wi, 0.07);
			sheet.drawText("Bild von lumas.de",
		                               sheet.getDefaultTextCol(), "SansSerif", Font.ITALIC,wi+0.1,0.98,0.9-wi, 0.02);

			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeAdrianaStephanKlettern(int month, String strOutDir) {
		try {
			final String inDir1 = "C:\\Users\\MiRoe\\Pictures\\WhatsApp\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, mDates);
			BufferedImageSetPixImg_ABGR imgSteph = new BufferedImageSetPixImg_ABGR(inDir1+"IMG-20170819-WA0005.jpg");
			final int src1W = 720;   // both pictures IMG-20170819-WA0005.jpg, IMG-20170818-WA0011.jpg and IMG-20170819-WA0005_Steph1.png
			final int src1H = 1280;  // both pictures IMG-20170819-WA0005.jpg, IMG-20170818-WA0011.jpg and IMG-20170819-WA0005_Steph1.png
			final int intersect1W = 150;
			final int src12W = 2*src1W-intersect1W;
			sheet.prepareImage(src12W, src1H, sheet.getDefaultBackgroundCol());
			float fac1 = (float)sheet.getUsuableWidth() / src12W;
			if (src1H * fac1 > sheet.getUsuableHeight()) {
				System.out.println("makeAdrianaStephanKlettern(,) passt noch nicht.");
				fac1 = (float)sheet.getUsuableHeight() / src1H;
			}
			System.out.println("makeAdrianaStephanKlettern(,) : fac1 =" + fac1);
			final int w2 = (int)(fac1 *(src1W-intersect1W));
			final int w3 = (int)(fac1 * src1W);
			final int w4 = (int)(fac1 * src12W);
			final int h2 = (int)(fac1 * src1H);
			final int nBildRand = Math.max((int)(fac1 * src1H/100 + .9), (int)(fac1 * src1W/50 + .9));
			int y2 = sheet.getUsuableHeight() - h2;
			if (y2 < 0) {
				System.err.println("FotoKalender.makeAdrianaStephanKlettern(,): y2 = "+ y2);
				++y2;
			}
			if (y2 > nBildRand*3) {
				sheet.drawImage(inDir1+"IMG-20170821-WA0006.jpg",   0.5, 0.5, 0,  0, 0, 1.0   , y2-nBildRand);
			} else {
				y2 = y2/2;
			}
			int x1 = sheet.getUsuableWidth()-w4;
			if (x1 < 0) {
				System.err.println("FotoKalender.makeAdrianaStephanKlettern(,): x1 = "+ x1);
				++x1;
			}
			if (x1 > nBildRand*3) {
				sheet.drawImage(inDir1+"IMG-20170818-WA0007a.jpg",   0.5, 0.5, 0,  0, 0, x1-nBildRand, 1.0);
			} else {
				x1 = x1/2;
			}
			int x8 = intersect1W-2;
			for (int xi = 0; xi < imgSteph.getWidth(); ++xi) {
				float alpha = Math.min(1f, (float)xi/x8);
				for (int yi = 0; yi < imgSteph.getHeight(); ++yi) {
					imgSteph.setAlpha(xi, yi, alpha);
				}
			}
					
			sheet.drawImage(inDir1+"IMG-20170818-WA0011.jpg",       0.5, 0.5, 0, x1,    y2, w3   , h2);
			sheet.drawImage(imgSteph.getImage(0),                   0.5, 0.5, 0, x1+w2, y2, w4-w2, h2);
			sheet.drawImage(inDir1+"IMG-20170819-WA0005_Steph1.png",0.5, 0.5, 0, x1+w2, y2, w4-w2, h2);
			
			
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end of makeAdrianaStephanKlettern(,) */

	public void makeAllWaysToBerlin(int month, int nOpt, String strOutDir) {
		try {
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(3235);
			int height = sheet.getUsuableHeight();
			int width  = sheet.getUsuableWidth();

			SourceImage sPgTrains  = new SourceImage("http://www.3roeders.de/MiHandy-2016-10/DSC_0037.png");
			SourceImage sPgAutobN  = new SourceImage("http://www.3roeders.de/MiNokiaLum900/WP_000402.png");
			SourceImage sPgAutoS1  = new SourceImage("http://www.3roeders.de/MiHandy-2016-10/DSC_0400.png");
			// https://de.wikipedia.org/wiki/Datei:Deutschland_aus_Berliner_Sicht.svg
			SourceImage sBerlinerSicht
				= new SourceImage("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Deutschland_aus_Berliner_Sicht.svg/725px-Deutschland_aus_Berliner_Sicht.svg.png");
			int iWBerlinerSicht = 0;
			int iHBerlinerSicht = 1;
			if (sBerlinerSicht.isOk()) {
				iWBerlinerSicht = sBerlinerSicht.getWidth();
				iHBerlinerSicht = sBerlinerSicht.getHeight();
			}
			int h1 = Math.min(height/12, width/14);
			Font font1 = new Font("Arial", Font.PLAIN, h1);
			int y1 = h1;
			final String str12 = "Berlin";
			final String str21 = "Alle Wege führen nach ";
			final String str22 = "Rom";
			int x0 = sheet.getX(0.0);
			int x9 = sheet.getX(1.0);
			
			int w12 = sheet.getTextWidth(str12, font1);
			int w21 = sheet.getTextWidth(str21, font1);
			int w22 = sheet.getTextWidth(str22, font1);
			int x1 = x0+(x9-x0-(w21+w22))/2;
			int x5 = x1+w21;
			int x4 = x5+(w22-w12)/2;
			int y2 = y1+h1;
			int x8 = Math.max(x4+w12, x5+w22);
			if (x8 > x9) {
				System.out.println("Text zu breit: "+ x0 +","+ x1 +","+ x5 +","+ x4 +","+ x8 +","+ x9);
				font1 = new Font("Arial", Font.PLAIN, h1*(x9-x0)/(x8-x0));
				/* und nochmal die ganze Rechnerei: */
				w12 = sheet.getTextWidth(str12, font1);
				w21 = sheet.getTextWidth(str21, font1);
				w22 = sheet.getTextWidth(str22, font1);
				x1 = x0+(x9-x0-(w21+w22))/2;
				x5 = x1+w21;
				x4 = x5+(w22-w12)/2;
				y2 = (int)(y1+h1*1.0+0.9);
				x8 = Math.max(x4+w12, x5+w22);
			}
			if (x8 > x9) {
				System.out.println("Text immer noch zu breit: "+ x0 +","+ x1 +","+ x5 +","+ x4 +","+ x8 +","+ x9);
			}
			if (x4+w12 > x9) {
				x4 = x9-w12;
			}
			System.out.println("Text Start Positionen: "+ x0 +","+ x1 +","+ x5 +","+ x4 +","+ x8 +","+ x9);
			int w01 = sheet.getTextWidth(" ", font1);
			sheet.drawText(str12, font1, Color.DARK_GRAY, x4, y1);
			sheet.drawText(str21, font1, Color.DARK_GRAY, x1, y2);
			sheet.drawText(str22, font1, Color.DARK_GRAY, x5, y2);

			float fY1 = 0.14f;
			float fH1 = 0.20f;
			float fY2 = fY1 + fH1 + 0.008f;
			float fH22 = 1f - fY2;
			float fH21 = fH22/2 - 0.004f;
			int iH22 = sheet.getY(1) - sheet.getY(1-fH22);
			int iW22 = iH22 * iWBerlinerSicht / iHBerlinerSicht;
			int iX22 = sheet.getUsuableWidth()-iW22;
			int iY22 = sheet.getUsuableHeight()-iH22;
			int iW21 = iX22-1-sheet.getUsuableWidth()/100;

			sheet.drawImage(sPgAutoS1,      0.5, 0.3 ,  0f , 0.00, fY1,  1.00, fH1);
			sheet.drawImage(sPgTrains, 		0.5, 0.5, -0.03, 0.00, fY2,  iW21, fH21);
			sheet.drawImage(sPgAutobN, 		0.5, 0.20, 0.05, 0.00,1-fH21,iW21, fH21);
			sheet.drawImage(sBerlinerSicht, 0.5, 0.5,  0  , iX22, iY22,  iW22, iH22);

			Graphics2D painter = sheet.getPainter();
			
			LineMetrics lm = font1.getLineMetrics(str21, painter.getFontRenderContext());
			int iShadow = (int)(lm.getStrikethroughThickness()/4f+0.9f);
			int iBetween = (int)(lm.getLeading()/2 + lm.getDescent());
			//painter.setColor(Color.WHITE);
			//painter.fillRect(x5, y2-h1/4, w22, h1/6);
			//painter.setColor(Color.BLACK);
			//painter.fillRect(x5+2*iShadow, y2-h1/4+2*iShadow, w22, h1/6);
			painter.setColor(Color.RED);
			int yT0 = y2-h1*4/9+iShadow;
			for (int yT = yT0; yT < yT0 + h1*4/12; yT += 6) {
				painter.fillRect(x5, yT, w22, 4);
			}
			painter.setColor(new Color(255, 64, 64));
			for (int iLine = -3; iLine < 3; ++iLine) {
				int xl1, xl2, yl1, yl2;
				xl1 = x4-w01*1/4+iLine;
				yl1 = y1-h1/3;
				xl2 = x5-w01/3+iLine;
				yl2 = y2-h1/3+iLine;
				painter.drawLine(xl1, yl1, xl2, yl2);
				xl1 = xl2;
				yl1 = yl2;
				xl2 = x5+iLine;
				yl2 = y1+iBetween+iLine;
				painter.drawLine(xl1, yl1, xl2, yl2);
				xl1 = xl2;
				yl1 = yl2;
				xl2 = x5+w22+iLine;
				painter.drawLine(xl1, yl1, xl2, yl2);
			}
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end of makeAllWaysToBerlin(int month, int nOpt, String strOutDir) */

	public void makeTramToDrogenbos(int month, String strOutDir) {
		try {
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			sheet.prepareImage(3414f);
			final Color colTxt = new Color(128,192,255);
			final Color colTxtS = new Color(160,244,255);

			SourceImage srcTram  = new SourceImage("http://www.3roeders.de/SamsungGalS23/20230727_114310.jpg");
			// Original size 3414 x 2685
			Rectangle rectDestinationIndicator = new Rectangle(1454, 1106, 160, 40);
			sheet.drawText("Warum so ein großer Aufwand?", colTxt, Font.SERIF, Font.PLAIN, 0, 0   , 1f, 0.06);
			int iX3 = sheet.getUsuableWidth()/50;
			int iW4 = sheet.getUsuableWidth()/100;
			int iW3 = sheet.getUsuableWidth()-2*iX3-iW4;
			int iW3a = sheet.drawText("In Brüssel fährt die Strassenbahn Linie Nr. 82 direkt zum Drogenbos",
														   colTxt, Font.SERIF, Font.PLAIN, iX3, 0.07, iW3, 0.06);
			int iX4 = iX3+(iW3-iW3a)/2+iW3a;
			sheet.drawText("s",	colTxtS, Font.SERIF, Font.PLAIN, iX4, 0.07, iW4, 0.06);

			sheet.drawImage(srcTram, .5f, .5f, 0,                                          0, 0.4,  1d, 0.6);
			srcTram.setSourceBounds(rectDestinationIndicator);
			int x0 = sheet.getX1();
			int y0 = sheet.getY1();
			int xDI = sheet.getX(rectDestinationIndicator.getX()/3414f);
			int wDI = sheet.getDX(rectDestinationIndicator.getWidth()/3414f);
			int yDI = sheet.getY(0.585);
			int hDI = sheet.getDY(0.05);
			int hBDI = sheet.getDY(0.14);
			int wBDI = (int)Math.round(hBDI*rectDestinationIndicator.getWidth()/rectDestinationIndicator.getHeight());
			int xBDI = xDI+(wDI-wBDI)/2;
			int yBDI = sheet.getY(0.14);
			sheet.drawImage(srcTram, .5f, .5f, 0, xBDI-x0, yBDI-y0, wBDI, hBDI);
			Graphics2D painter = sheet.getPainter();
			painter.setColor(new Color(128, 128, 255, 128));
			int wLines = hDI/3;
			for (int iWO = 0; iWO < wLines; ++iWO) {
				painter.drawOval(xDI-iWO/2, yDI-iWO/2, wDI+iWO, hDI+iWO);
			}
			wLines = hDI/5;
			for (int iWO = 0; iWO < wLines; ++iWO) {
				painter.drawLine(xDI+wDI/2 - wLines/2+iWO, yDI                , xBDI+wBDI/2 - wLines/2+iWO, yBDI+hBDI);
				painter.drawLine(xBDI+wBDI/2 - wLines/2+iWO-wDI, yBDI+hBDI+wDI, xBDI+wBDI/2 - wLines/2+iWO, yBDI+hBDI);
				painter.drawLine(xBDI+wBDI/2 - wLines/2+iWO+wDI, yBDI+hBDI+wDI, xBDI+wBDI/2 - wLines/2+iWO, yBDI+hBDI);
			}
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end of makeAllWaysToBerlin(int month, int nOpt, String strOutDir) */

	public void makeRiversHorseShoeAndMosel(int month, String strOutDir) {
		try {
			final String s0 = "C:\\Users\\MiRoe\\Pictures\\2015-Amerika\\";
			final String s1 = "C:\\Users\\MiRoe\\Pictures\\ForCal2016\\";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			Color backCol = sheet.getDefaultBackgroundCol();
			sheet.prepareImage(7000, backCol);
			
			sheet.drawImage(s0+"HorseShoe5.jpg", 0.5, 0.5, +4*Math.PI/180, 0, 0, 1, 1);
			float x1 = 0.81f;
			float y1 = 0.80f;
			float x2 = 0.78f;
			float y2 = 0.90f;
			sheet.drawImage(s1+"Mosel-U-Dunkle-Wolken.jpg",    0.5, 0.65,0f, x1, y1, 1-x1, y2-y1);

			Graphics2D painter = sheet.getPainter();
			painter.setColor(backCol);
			int x01 = sheet.getX(x1-0.0025);
			int y01 = sheet.getY(y1-0.0025);
			int x02 = sheet.getX(x1)+1;
			int y02 = sheet.getY(y1)+1;
			int w1 = sheet.getX(1);
			int h1 = sheet.getY(1);
			painter.fillRect(x01, y01, w1 -x01, y02-y01);
			painter.fillRect(x01, y01, x02-x01, h1 -y01);

			// Mosel-U-bei-Sonnenschein.jpg 659x241 pixel Ränder top 7, left 6, right 9, bottom 9
			sheet.drawImage(s1+"Mosel-U-bei-Sonnenschein.jpg", 0.5, 0.5, 0f, x2, y2, 1-x2, 1-y2);
			x01 = sheet.getX(x2-0.0025);
			y01 = sheet.getY(y2-0.0025);
			x02 = sheet.getX(x2)+1;
			y02 = sheet.getY(y2)+1;
			painter.fillRect(x01, y01, w1 -x01, y02-y01);
			painter.fillRect(x01, y01, x02-x01, h1 -y01);
			
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeRiversHorseShoe(int month, String strOutDir) {
		try {
			final String s0 = "http://www.3roeders.de/USA2015/";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, month, this.mDates);
			Color backCol = sheet.getDefaultBackgroundCol();
			sheet.prepareImage(6698, backCol);
						
			// HorseShoe5.png [7790x4346] but only 25/26 of the width is useful
			float f1High = 4346f/(7790*25/26/CalendarSheet.fWeight);
			float f1Widt = 1f;
			float f1X = 0f;
			if (f1High > 1f) {
				f1Widt = f1Widt/f1High;
				f1X = 0.5f-f1Widt/2f;
				f1High = 1f;
			}
			float y4 = Math.max((0.99f-f1High)/2, 0f);
			System.out.println("makeRiversHorseShoe(,) f1Widt= "+ f1Widt + ", f1High= "+ f1High);
			if (y4 > 0.1) {
				int f2H = (int)((y4-0.01) * sheet.getUsuableHeight());
				int f2W = Math.min(f2H * 4912 / 3264, sheet.getUsuableWidth()/2) - sheet.getUsuableWidth()/100-1;
				sheet.drawImage(s0+"DSC04922.JPG", 0.5, 0.5, 0, 0, 0, f2W, f2H);
			}
			sheet.drawImage(s0+"HorseShoe5.jpg", 0.5, 0.5, +4*Math.PI/180, f1X, y4, f1Widt, f1High);
			// DSC05144.jpg: [3264x4912], DSC05149.jpg + DSC05192.jpg [4912x3264]
			f1High = Math.max(0.20f, y4-0.01f) * sheet.getUsuableHeight();
			f1Widt = Math.min(f1High * 4912 / 3264, sheet.getUsuableWidth()/2) - sheet.getUsuableWidth()/100-1;
			final int w19 = (int)f1Widt+1;
			final int x19 = sheet.getUsuableWidth()-w19;
			final int h19 = (int)f1High;
			sheet.drawImage(s0+"DSC05192.JPG",   0.5, 0.5,              0, x19, 0.00, w19, h19);
			sheet.getPainter().setColor(sheet.getBackgroundCol());
			sheet.getPainter().fillRect(sheet.getX1()+x19-sheet.getDX(0.005), sheet.getY1()    , sheet.getDX(0.005), h19+sheet.getDY(0.008));
			sheet.getPainter().fillRect(sheet.getX1()+x19                   , sheet.getY1()+h19, x19               ,     sheet.getDY(0.008));
			
			f1High = 0.30f  * sheet.getUsuableHeight();
			f1Widt = f1High * 4912 / 3264;
			sheet.drawImage(s0+"DSC05149e.png",                  0.5, 0.5, 0, 1-f1Widt, 1f-f1High, f1Widt, f1High);
			sheet.drawImage(s0+"DSC05149%20mehr%20Konstrast%20m2.png",
					                                             0.5, 0.5, 0, 1-f1Widt, 1f-f1High, f1Widt, f1High);
			f1High = 0.30f  * sheet.getUsuableHeight();
			f1Widt = f1High * 3048 / 4269;
			sheet.drawImage(s0+"DSC05144f.png",                  0.5, 0.5, 0, 0,        1f-f1High, f1Widt, f1High);
			
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeFussballMitOlympiaStadion(int nMonth, String strOutDir) {
		try {
			final String s0 = "C:\\Users\\MiRoe\\Pictures\\Fussball\\OlympiaStadion2017-02.jpg";
			final String s125 = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\20170819_152823.jpg";
			final String sF = "C:\\Users\\MiRoe\\Pictures\\Cal2018\\Facebook\\FB_IMG_1487882614392.jpg";
			final String sB = "C:\\Users\\MiRoe\\Pictures\\Boatengs.jpg";
			CalendarSheet sheet = new CalendarSheet(THIS_YEAR, nMonth, this.mDates);
			sheet.prepareImage(4336, new Color(253,253,253));

			double h1 = 0.54f;
			sheet.drawImage(s0,   0.5, 0.5,    0, 0.000,  0.00,     1.00, h1);
			sheet.drawImage(s125, 0.5, 0.50,   0, 0.000,  h1,       0.36, 1.00-h1);
			sheet.drawImage(sB,   0.5, 0.55,   0, 0.370,  h1+.01,   0.26, 0.99-h1);
			sheet.drawImage(sF,   0.5, 0.48,   0, 0.640,  h1,       0.36, 1.00-h1);
			
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void makeDampflok(int nMonth, final String strOutDir) {
		int width;
		CalendarSheet sheet;
		int x0;
		try {
			// einzel bilder: 2048 x 1536
			// main picture: Dampflok_Try1.jpg 8104x1920
			// main picture: Dampflok_Try2.jpg 4051x960
			String s0 = "C:\\Users\\MiRoe\\Pictures\\2010-05\\";
			String sI = "C:\\Users\\MiRoe\\Pictures\\ForCal2011\\";
			x0 = 1300;
			width = 8104-x0;
			sheet = new CalendarSheet(THIS_YEAR, nMonth, mDates);
			sheet.prepareImage(width+x0);
			sheet.drawCheckLines();
			int w1 = width/9-5;
			double f1 = w1/2048.0;
			int h1 = (int)(1536*f1)+6;
			System.out.println("Höhe Dampflokeinzelbilder: "+ h1);
			sheet.drawImage(s0+"18052010916.jpg", f1,0, 0, 0,         x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010915.jpg", f1,0, 0, 0,         x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010914.jpg", f1,0, 0, 0,      w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010913.jpg", f1,0, 0, 0,      w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010912.jpg", f1,0, 0, 0,    2*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010911.jpg", f1,0, 0, 0,    2*w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010908.jpg", f1,0, 0, 0,    3*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010909.jpg", f1,0, 0, 0,    3*w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010906.jpg", f1,0, 0, 0,    4*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010907.jpg", f1,0, 0, 0,    4*w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010904.jpg", f1,0, 0, 0,    5*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010905.jpg", f1,0, 0, 0,    5*w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010902.jpg", f1,0, 0, 0,    6*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010903.jpg", f1,0, 0, 0,    6*w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010900.jpg", f1,0, 0, 0,    7*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010901.jpg", f1,0, 0, 0,    7*w1+x0,      h1, w1, h1);
			sheet.drawImage(s0+"18052010898.jpg", f1,0, 0, 0,    8*w1+x0,       0, w1, h1);
			sheet.drawImage(s0+"18052010899.jpg", f1,0, 0, 0,    8*w1+x0,      h1, w1, h1);
			
			sheet.drawImage(s0+"18052010919.jpg",2*f1,0, 0,0,         x0,    3*h1,2*w1,2*h1);
			sheet.drawImage(s0+"18052010917.jpg",2*f1,0, 0,0,  7*w1/3+x0,    3*h1,2*w1,2*h1);
			sheet.drawImage(s0+"18052010918.jpg",2*f1,0, 0,0, 14*w1/3+x0,    3*h1,2*w1,2*h1);
			sheet.drawImage(s0+"18052010894.jpg",2*f1,0, 0,0,    7*w1+x0,    3*h1,2*w1,2*h1);
			
			sheet.drawImage(sI+"Dampflok_Try1.JPG",  0, 0,      0, sheet.getTotalHeight()-1920-200, 8104, 1920);
			sheet.drawCalDates();
			sheet.writeInDir(strOutDir);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end makeDampflok(int nMonth, final String strOutDir) */

	void addManyPlaygroundImages(ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint) {
		Draw1ImageI img;
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/SamsungGalS23/20240520_175054.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/SamsungGalS23/20240520_174924.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/pixabay.com/castle-239083_1280.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/pixabay.com/castle-239086_1280.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/SamsungGalS23/20240520_180459.png");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/SamsungGalS23/20240520_175650.jpg");
			img.dRot = 90;
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/pixabay.com/slide-2686421_1280.jpg");
			sheet.addImage(img, hints);
		}
		if (randomGen == null || randomGen.nextFloat() < maxRandomToPaint) {
			img = new Draw1ImageI("https://www.3roeders.de/pixabay.com/playground-1051219_1280.jpg");
			sheet.addImage(img, hints);
		}
	}
	
	public void addManyImages(File directory, String sFileExt, ICalendarSheetAddImage sheet, IHintsDrawImages hints, Random randomGen, float maxRandomToPaint, float failFactor) {
		if (failFactor < 1f) {
			failFactor = 1f;
			if (randomGen != null) {
				System.err.println("FotoKalender.addManyImages: parameter failFactor will be ignored.");
			}
		}
		File[] inFiles = directory.listFiles(new MiRoeFileExtFilter(sFileExt));
		float curMxRnd2Paint = maxRandomToPaint;
		for (File f1 : inFiles) {
			if (randomGen == null || randomGen.nextFloat() < curMxRnd2Paint) {
				Draw1ImageI img = new Draw1ImageI(f1, EFillType.CutSource);
				sheet.addImage(img, hints);
				curMxRnd2Paint *= failFactor;
			} else {
				curMxRnd2Paint = maxRandomToPaint;
			}
		}
	}
	
	public static void renameToTitle(String strOutDir, String strOldName) {
		int nIStrExt = strOldName.lastIndexOf('.');
		String strExt = strOldName.substring(nIStrExt);
		File fi00 = new File(strOutDir, "0000"+strExt);
		File fil1 = new File(strOutDir, strOldName);
		boolean bDeleted = fi00.delete();
		boolean bRenamed = fil1.renameTo(fi00);
		if (bRenamed) {
			System.out.println(fil1.getAbsolutePath() +" prepared for title page by renaming to "+ fi00.getAbsolutePath());
		} else {
			System.out.println(fi00.getAbsolutePath() + (bDeleted ? " successfully" : " not") +"deleted.");
			System.out.println(fil1.getAbsolutePath() +" tried to prepare for title page by renaming to "+ fi00.getAbsolutePath());			
		}
	}
	
	
	public void addFamilyEvents() {
		PersonalDate hochzeit;
		mDates.addCalEvent(PersonalDate.createBirthday("* Michael", 15, 3, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Constanze", 11, 8, THIS_YEAR));
		hochzeit = PersonalDate.create1BitmapsBackground("Constanze", new File("res/Hochzeitsringe.png"), "Michael", 1f, 0f, 7, 5, THIS_YEAR);
		mDates.addCalEvent(hochzeit);
		mDates.addCalEvent(PersonalDate.createBirthday("* Adriana", 20, 12, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Stephan", 16, 2, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Viktoria", 31, 3, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("Einschulung Viktoria", 7, 9, 2024));
		hochzeit = PersonalDate.create1BitmapsBackground("Adriana", new File("res/Hochzeitsringe.png"), "Stephan", 1f, 0f, 16, 12, THIS_YEAR);
		mDates.addCalEvent(hochzeit);
		if (THIS_YEAR == 25) { //TODO
			var KabarettBesuch = new PersonalDate("{0,date,EE} ", 22, 2, 2025, (byte)0);
			KabarettBesuch.mBackgrounds = new ArrayList<IDaysBackground>(1);
			DaysBackgroundImage img = new DaysBackgroundImage(2, 0f, new File("C:\\Users\\MiRoe\\Pictures\\Juergen_von_der_Lippe.png"), .8f);
			KabarettBesuch.mBackgrounds.add(img);
			mDates.addCalEvent(KabarettBesuch);
		}
	}
	
	public void addStephansFamilyEvents() {
		mDates.addCalEvent(PersonalDate.createBirthday("* Marlis", 28, 1, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* André", 26, 4, THIS_YEAR));
		PersonalDate hochzeit;
		hochzeit = PersonalDate.create1BitmapsBackground("Marlis", new File("res/Hochzeitsringe.png"), "André", 1f, 0f, 2, 4, THIS_YEAR);
		mDates.addCalEvent(hochzeit);
	}
	
	public void addOlafsFamilyEvents() {
		mDates.addCalEvent(PersonalDate.createBirthday("* Olaf", 17, 1, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Angelika", 3, 6, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Fabian", 5, 3, THIS_YEAR));
	}
	
	public void addPrettinFamilyEvents() {
		mDates.addCalEvent(PersonalDate.createBirthday("* Elsbeth", 27, 3, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Franka", 28, 4, THIS_YEAR));
		mDates.addCalEvent(PersonalDate.createBirthday("* Klaus", 8, 10, THIS_YEAR));
	}
	
	public void addSpecialEvents() {
		// Fahrradsternfahrt vom ADFC jeweils am ersten Sonntag im Juni
		GregorianCalendar d1 =  new GregorianCalendar(THIS_YEAR, Calendar.JUNE, 1);
		while (d1.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY) {
			d1.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		PersonalDate ev1;
		// Tried: \uD83D\uDEB2 and \uD87D\uDEB2 as described in 
		// https://www.fileformat.info/info/unicode/char/1f6b2/fontsupport.htm and
		// https://groups.google.com/g/comp.lang.java.programmer/c/ej5Gx8zurpM?pli=1
		ev1 = new PersonalDate("ADFC Sternfahrt", d1, PersonalDate.PRIO_BIRTHDAY);
		mDates.addCalEvent(ev1);

		// Die Berlinerinnen und Berliner wählen am 20. September 2026 ihr Landesparlament. :
		if (THIS_YEAR == 2026) {
			d1 =  new GregorianCalendar(2026, Calendar.SEPTEMBER, 20);
			ev1 = new PersonalDate("Abgeordnetenhauswahl", d1, PersonalDate.PRIO_BIRTHDAY);
			mDates.addCalEvent(ev1);
		}
	}
	
	public void addGermanPublicHolidays() {
		mDates.addCalEvent(PersonalDate.createFixedHoliday("Neujahr", 1, 1, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createFixedHoliday("Tag der Arbeit", 1, 5, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createFixedHoliday("Tag der deutschen Einheit", 3, 10, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		// mDates.addCalEvent(new PersonalDate("Reformationstag", 31, 10, 2017, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createFixedHoliday("{0,date,EE} Heiligabend", 24, 12, THIS_YEAR, PersonalDate.HALFSUNDAY));
		mDates.addCalEvent(PersonalDate.createFixedHoliday("1. Weihnachtstag", 25, 12, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createFixedHoliday("2. Weihnachtstag", 26, 12, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createFixedHoliday("{0,date,EE} Silvester", 31, 12, THIS_YEAR, PersonalDate.HALFSUNDAY));
		/* the following holidays depend on the easter day : */
		GregorianCalendar easterSunday = EasterSunday.createEasterSunday(THIS_YEAR);
		mDates.addCalEvent(PersonalDate.createDate("Rosenmontag", easterSunday, -48, (byte)0));
		mDates.addCalEvent(PersonalDate.createDate("Aschermittwoch", easterSunday, -46, (byte)0));
		mDates.addCalEvent(PersonalDate.createDate("Karfeitag", easterSunday, -2, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		if (easterSunday.get(GregorianCalendar.DAY_OF_MONTH) != 31) {
			// wenn Ostersonntag auf den 31. März fällt, dann stehen dort 3 Termine: Viktorias Geburtstag, Beginn der Sommerzeit und Ostersonntag.
			// Das ist mir zuviel.
			mDates.addCalEvent(new PersonalDate("Ostersonntag", easterSunday, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		}
		mDates.addCalEvent(PersonalDate.createDate("Ostermontag", easterSunday, 1, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createDate("Christi Himmelfahrt", easterSunday, 39, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createDate("Pfingstsonntag", easterSunday, 49, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		mDates.addCalEvent(PersonalDate.createDate("Pfingstmontag", easterSunday, 50, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
	}

	public void addBerlinHolidays() {
		addGermanPublicHolidays();
		PersonalDate dateIntWomanDay = new PersonalDate("{0,date,EE} Internationaler Frauentag", 8, 3, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY);
		if (this.THIS_YEAR < 2021 && dateIntWomanDay.getDate().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			mDates.addCalEvent(PersonalDate.createFixedHoliday("{0,date,EE} Tag der Befreiung", 8, 5, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		} else {
			mDates.addCalEvent(dateIntWomanDay);
		}
		if (this.THIS_YEAR == 2025) {
			// https://www.berlin.de/aktuelles/8594788-958090-zusaetzlicher-feiertag-am-8-mai-soll-202.html
			// https://www.berlin-live.de/berlin/aktuelles/berlin-neuer-feiertag-senat-beschluss-z-e-id85589.html
			mDates.addCalEvent(PersonalDate.createFixedHoliday("{0,date,EE} Tag der Befreiung", 8, 5, THIS_YEAR, PersonalDate.PRIO_PUBLIC_GERMAN_HOLIDAY));
		}
	}
	
	public void addBavarianPublicHolidays() {
		addCalEventWhenNotFree(PersonalDate.createFixedHoliday("{0,date,EE} Heilige 3 Könige", 6, 1, THIS_YEAR, PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY));
		GregorianCalendar easterSunday = EasterSunday.createEasterSunday(THIS_YEAR);
		mDates.addCalEvent(PersonalDate.createDate("", easterSunday, -47, (byte)(PersonalDate.HALFSUNDAY|PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY))); // Fastnachtsdienstag
		addCalEventWhenNotFree(PersonalDate.createFixedHoliday("{0,date,EE} Maria Himmelfahrt", 15, 8, THIS_YEAR, PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY));
		addCalEventWhenNotFree(PersonalDate.createFixedHoliday("{0,date,EE} Allerheiligen", 1, 11, THIS_YEAR, PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY));
		GregorianCalendar fronleichnam = (GregorianCalendar)easterSunday.clone();
		fronleichnam.add(GregorianCalendar.DAY_OF_YEAR, 60);
		addCalEventWhenNotFree(new PersonalDate("{0,date,EE} Fronleichnam", fronleichnam, PersonalDate.PRIO_PUBLIC_BAVARIAN_HOLIDAY));
	}
	
	public void addAdvents() {
		GregorianCalendar advent = new GregorianCalendar(THIS_YEAR, Calendar.DECEMBER, 24);
		int tagDez24 = advent.get(GregorianCalendar.DAY_OF_WEEK);
		byte isSunday4Buy = tagDez24 == 1 ? (byte)0 : PersonalDate.SUNDAY4BUYING;
		advent.add(GregorianCalendar.DAY_OF_MONTH, 1-tagDez24);
		mDates.addLowPrioCalEvent(new PersonalDate("4. Advent", advent, (byte)(PersonalDate.ALL_HAVE_FREE|isSunday4Buy)));
		advent.add(GregorianCalendar.DAY_OF_MONTH, -7);
		isSunday4Buy = (byte)(isSunday4Buy ^ PersonalDate.SUNDAY4BUYING);
		mDates.addLowPrioCalEvent(new PersonalDate("3. Advent", advent, (byte)(PersonalDate.ALL_HAVE_FREE|isSunday4Buy)));
		advent.add(GregorianCalendar.DAY_OF_MONTH, -7);
		isSunday4Buy = (byte)(isSunday4Buy ^ PersonalDate.SUNDAY4BUYING);
		mDates.addLowPrioCalEvent(new PersonalDate("2. Advent", advent, (byte)(PersonalDate.ALL_HAVE_FREE|isSunday4Buy)));
		advent.add(GregorianCalendar.DAY_OF_MONTH, -7);
		isSunday4Buy = (byte)(isSunday4Buy ^ PersonalDate.SUNDAY4BUYING);
		mDates.addLowPrioCalEvent(new PersonalDate("1. Advent", advent, (byte)(PersonalDate.ALL_HAVE_FREE|isSunday4Buy)));
		advent.add(GregorianCalendar.DAY_OF_MONTH, -7);
		mDates.addLowPrioCalEvent(new PersonalDate("Totensonntag", advent, PersonalDate.ALL_HAVE_FREE));
		advent.add(GregorianCalendar.DAY_OF_MONTH, -7);
		mDates.addLowPrioCalEvent(new PersonalDate("Volkstrauertag", advent, PersonalDate.ALL_HAVE_FREE));
	}
	
	public void addMuttertag() {
		GregorianCalendar advent = new GregorianCalendar(THIS_YEAR, Calendar.MAY, 14);
		int tagMay14 = advent.get(GregorianCalendar.DAY_OF_WEEK);
		advent.add(GregorianCalendar.DAY_OF_MONTH, 1-tagMay14);
		mDates.addLowPrioCalEvent(new PersonalDate("Muttertag", advent, PersonalDate.ALL_HAVE_FREE));
	}
	
	public void addSundays4Buying() {
		if (this.THIS_YEAR == 2025) {
			GregorianCalendar sun4buy;
			sun4buy = new GregorianCalendar(2025, Calendar.JANUARY, 26);  //Anlass ist die Grüne Woche in Berlin
			mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			//sun4buy = new GregorianCalendar(2020, Calendar.FEBRUARY, 23);  //Berlinale 17. Februar 2019
			//mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			//sun4buy = new GregorianCalendar(2019, Calendar.MARCH, 10);  //Internationale TourismusBörse Berlin (ITB)
			//mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			//sun4buy = new GregorianCalendar(2020, Calendar.JUNE, 21);  // Fête de la musique
			//mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			sun4buy = new GregorianCalendar(2025, Calendar.SEPTEMBER, 7); // Anlass ist die Internationale Funkausstellung (IFA)
			mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			// 01.10.2017: Feier zum Tag der Deutschen Einheit, Eröffnungswochenende Berlin leuchtet – Lichterfest 2017 
			//sun4buy = new GregorianCalendar(2017, Calendar.OCTOBER, 1);
			//sun4buy.add(GregorianCalendar.DAY_OF_YEAR, 7*52);
			//mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			// 05.11.2017: Jazzfest Berlin 2017 
			//sun4buy = new GregorianCalendar(2017, Calendar.NOVEMBER, 5);
			//sun4buy.add(GregorianCalendar.DAY_OF_YEAR, 7*52);
			//mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
		}
		// Adventssonntage: Veranstaltung von zahlreichen Weihnachtsmärkten, bereits in addAdvents() gesetzt
		// 7. und 21. Dezember 2025
		if (this.THIS_YEAR > 2025) {
			// TODO: vorläufige Einschätzungen, denn am 10. September 2025:
			// Für 2026 gibt es in Berlin noch keine festen Termine für verkaufsoffene Sonntage, da diese oft kurzfristig anlassbezogen festgelegt werden.
			GregorianCalendar sun4buy;
			sun4buy = new GregorianCalendar(2025, Calendar.JANUARY, 26);  //Anlass ist die Grüne Woche in Berlin
			sun4buy.add(GregorianCalendar.WEEK_OF_YEAR, (int)((this.THIS_YEAR - 2025)*52.18f)); // wohl 52 Wochen später in 2026
			mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
			sun4buy = new GregorianCalendar(2025, Calendar.SEPTEMBER, 7); // Anlass ist die Internationale Funkausstellung (IFA)
			sun4buy.add(GregorianCalendar.WEEK_OF_YEAR, (int)((this.THIS_YEAR - 2025)*52.18f)); // wohl 52 Wochen später in 2026
			mDates.addCalEvent(new PersonalDate("", sun4buy, PersonalDate.SUNDAY4BUYING));
		}
	}
	

	public void addSoccerIntEvent() {
		PersonalDate game1;
		IDaysBackground img;
		DaysBackgroundText txt;

		if (THIS_YEAR == 2024) {
			DaysBackgroundHFlag gerFlag = new DaysBackgroundHFlag(1f, 0f, new Color[]{new Color(0,0,0), new Color(255,0,0), new Color(255,204,0)}, 5f/3f); // https://de.wikipedia.org/wiki/Flagge_Deutschlands#Farbton-Empfehlung_f%C3%BCr_die_Bundesflagge_ab_1996

			game1 = new PersonalDate("{0,date,EE} ", 14, 6, 2024, (byte)0);
			game1.mBackgrounds = new ArrayList<IDaysBackground>(3);
			game1.mBackgrounds.add(gerFlag);
			txt = new DaysBackgroundText(1f, 0f, game1, " : ");
			game1.mBackgrounds.add(txt);
			DaysBackground1 flag0 = new DaysBackgroundScottishFlag(1f, 0f);
			game1.mBackgrounds.add(flag0);
			mDates.addCalEvent(game1);

			game1 = new PersonalDate("{0,date,EE} ", 19, 6, 2024, (byte)0);
			game1.mBackgrounds = new ArrayList<IDaysBackground>(3);
			game1.mBackgrounds.add(gerFlag);
			txt = new DaysBackgroundText(1f, 0f, game1, " : ");
			game1.mBackgrounds.add(txt);
			flag0 = new DaysBackgroundHFlag(1f, 0f, new Color[]{new Color(206,41,57), Color.WHITE, new Color(71,112,80)}, 2); // https://commons.wikimedia.org/wiki/File:Flag_of_Hungary.svg  Dec-25-2023
			game1.mBackgrounds.add(flag0);
			mDates.addCalEvent(game1);

			game1 = new PersonalDate("{0,date,EE} ", 23, 6, 2024, (byte)0);
			game1.mBackgrounds = new ArrayList<IDaysBackground>(3);
			game1.mBackgrounds.add(gerFlag);
			txt = new DaysBackgroundText(1f, 0f, game1, " : ");
			game1.mBackgrounds.add(txt);
			flag0 = new DaysBackgroundSwissFlag(1f, 0f);
			game1.mBackgrounds.add(flag0);
			mDates.addCalEvent(game1);

		}

		if (THIS_YEAR == 2026) {
			game1 = new PersonalDate("{0,date,EE} ", 11, 6, 2026, (byte)0);
			game1.mBackgrounds = new ArrayList<IDaysBackground>(1);
			txt = new DaysBackgroundText(1f, 0f, game1, "Beginn: ");
			game1.mBackgrounds.add(txt);
			img = new DaysBackgroundImage(3f, 0f, new File("res/FIFA_2026.png"), .8f);
			game1.mBackgrounds.add(img);
			mDates.addCalEvent(game1);

			game1 = new PersonalDate("{0,date,EE} ", 19, 7, 2026, (byte)0);
			game1.mBackgrounds = new ArrayList<IDaysBackground>(1);
			txt = new DaysBackgroundText(1f, 0f, game1, "Finale: ");
			game1.mBackgrounds.add(txt);
			img = new DaysBackgroundImage(3f, 0f, new File("res/FIFA_World_Cup_Trophy.png"), .8f);
			game1.mBackgrounds.add(img);
			mDates.addCalEvent(game1);
		}
	}

	public void addOlympiade() {
		int nYear = THIS_YEAR; // no final to avoid warnings "Comparing identical expressions" or "Dead code"
		if (nYear == 2026) {
			// Die XXV. Olympischen Winterspiele sollen vom 6. bis zum 22. Februar 2026 in Mailand und Cortina d?Ampezzo stattfinden.[
			PersonalDate day1;
			IDaysBackground img;
			float fiDay = 6f;
			float fHigh = 1.8f;
			while (fiDay < 22.5f) {
				int iDay = (int)(fiDay+.4f);
				float dY = fiDay-iDay;
				fiDay += fHigh;
				img = new DaysBackgroundImage(fHigh*1.3f, dY, new File("res/320px-Olympic_flag.svg.png"), 0.5f);
				ArrayList<IDaysBackground> imgList = new ArrayList<IDaysBackground>(1);
				imgList.add(img);
				if (iDay <= 31) {
					day1 = new PersonalDate("", iDay,    2, 2026, (byte)0);
				} else {
					day1 = new PersonalDate("", iDay-31, 3, 2026, (byte)0);
				}
				day1.mBackgrounds = imgList;
				mDates.addCalEvent(day1);
			}
		}
	}

	public void addBerlinGrueneWoche() {
		// Die Grüne Woche findet vom 16. ? 25. Januar 2026 in den Hallen rund um den Funkturm statt.
		PersonalDate day1;
		IDaysBackground img;
		float fiDay = 16f-THIS_YEAR+2026;
		int endDate = 25-THIS_YEAR+2026;
		float fHigh = 1.8f;
		while (fiDay < endDate+.5f) {
			int iDay = (int)(fiDay+.4f);
			float dY = fiDay-iDay;
			fiDay += fHigh;
			img = new DaysBackgroundImage(fHigh*1.3f, dY, new File("res/GrueneWoche.png"), 0.5f);
			ArrayList<IDaysBackground> imgList = new ArrayList<IDaysBackground>(1);
			imgList.add(img);
			if (iDay <= 31) {
				day1 = new PersonalDate("", iDay,    1, THIS_YEAR, (byte)0);
			} else {
				day1 = new PersonalDate("", iDay-31, 2, THIS_YEAR, (byte)0);
			}
			day1.mBackgrounds = imgList;
			mDates.addCalEvent(day1);
		}
	}

	public void addVacations(boolean bBerlin, boolean bNiedersachsen) {
		int nYear = THIS_YEAR; // no final to avoid warnings "Comparing identical expressions" or "Dead code"
		GregorianCalendar easterSunday = null;

		if (bBerlin) {
			if (easterSunday == null) {
				easterSunday = EasterSunday.createEasterSunday(THIS_YEAR);
			}
			addVacations("{0,date,EE} Schulfrei",  3, 2, 2025     ,  8, 2, 2025); //Winterferien 2025
			addVacations("{0,date,EE} Schulfrei",  2, 2, 2026     ,  7, 2, 2026); //Winterferien 2026

			// Brückentage um Frauentag 8. März
			GregorianCalendar march7 = new GregorianCalendar(THIS_YEAR, GregorianCalendar.MARCH, 7);
			if (march7.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.MONDAY) {
				mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", march7, 0, PersonalDate.ADRIANA_HAS_FREE));
			}
			GregorianCalendar march9 = new GregorianCalendar(THIS_YEAR, GregorianCalendar.MARCH, 9);
			if (march9.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.FRIDAY) {
				mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", march9, 0, PersonalDate.ADRIANA_HAS_FREE));
			}

			// Osterferien usually a week before Easter and a week after Easter
			GregorianCalendar ferien1 = (GregorianCalendar)easterSunday.clone();
			GregorianCalendar ferien9 = (GregorianCalendar)easterSunday.clone();
			ferien1.add(GregorianCalendar.DAY_OF_MONTH, -7);
			if (nYear == 2017) {
				ferien9.add(GregorianCalendar.DAY_OF_MONTH, +2);
			} else {
				ferien9.add(GregorianCalendar.DAY_OF_MONTH, +7);
			}
			addVacations("{0,date,EE} Schulfrei", PersonalDate.ADRIANA_HAS_FREE, ferien1, ferien9);	//Osterferien
			mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", easterSunday, +40, PersonalDate.ADRIANA_HAS_FREE)); //Tag nach Himmelfahrt
			if (nYear != 2024) {
			    mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", easterSunday, +51, PersonalDate.ADRIANA_HAS_FREE)); // Dienstag nach Pfingsten
			}
			
			// bridge day before or after May 1st:
			GregorianCalendar april30 = new GregorianCalendar(THIS_YEAR, GregorianCalendar.APRIL, 30);
			if (april30.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.MONDAY) {
				mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", april30, 0, PersonalDate.ADRIANA_HAS_FREE));
			}
			GregorianCalendar may02 = new GregorianCalendar(THIS_YEAR, GregorianCalendar.MAY, 2);
			if (may02.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.FRIDAY) {
				mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", may02, 0, PersonalDate.ADRIANA_HAS_FREE));
			}
			
			addVacations("{0,date,EE} Schulfrei", 24, 7, 2025,  6, 9, 2025); 	// Sommerferien 2025
			addVacations("{0,date,EE} Schulfrei",  9, 7, 2026, 22, 8, 2026); 	// Sommerferien 2026
			addVacations("{0,date,EE} Schulfrei",  1, 7, 2027, 14, 8, 2027); 	// Sommerferien 2027
			addVacations("{0,date,EE} Schulfrei", 20,10, 2025,  1,11, 2025);	// Herbstferien 2025
			addVacations("{0,date,EE} Schulfrei", 19,10, 2026, 31,10, 2026);	// Herbstferien 2026
			addVacations("{0,date,EE} Schulfrei", 11,10, 2027, 23,10, 2027);	// Herbstferien 2027
			addVacations("{0,date,EE} Schulfrei", 22,12, 2025,  2, 1, 2026); 	// Weihnachtsferien 2025/26
			addVacations("{0,date,EE} Schulfrei", 23,12, 2026, 2,1, 2027); 	// Weihnachtsferien 2026/27
			GregorianCalendar oct2 = new GregorianCalendar(THIS_YEAR, GregorianCalendar.OCTOBER, 2);
			if (oct2.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.MONDAY) {
				mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", oct2, 0, PersonalDate.ADRIANA_HAS_FREE));
			}
			GregorianCalendar oct4 = new GregorianCalendar(THIS_YEAR, GregorianCalendar.OCTOBER, 4);
			if (oct4.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.FRIDAY) {
				mDates.addCalEvent(PersonalDate.createDate("{0,date,EE} Schulfrei", oct4, 0, PersonalDate.ADRIANA_HAS_FREE));
			}
		}
		
		if (bNiedersachsen) {
			Exception ex = new java.lang.IllegalArgumentException("Ferien in Niedersachsen nicht mehr implementiert!");
			ex.printStackTrace();
		}
	}
	
	public void addVacations(String strText,
							 int i1stDay,  int i1stMonth,  int i1stYear,
							 int iLastDay, int iLastMonth, int iLastYear ) {
		GregorianCalendar date = new GregorianCalendar(i1stYear, i1stMonth-1, i1stDay);
		GregorianCalendar lastDate = new GregorianCalendar(iLastYear, iLastMonth-1, iLastDay);
		addVacations(strText, PersonalDate.ADRIANA_HAS_FREE, date, lastDate);
	}
	
	public void addVacations(String strText, byte prio,
							 GregorianCalendar startDate, GregorianCalendar lastDate ) {
		while (!startDate.after(lastDate)) {
			int iFree = mDates.getPrio(startDate);
			if ((iFree & PersonalDate.ADRIANA_HAS_FREE) == 0) {
				mDates.addCalEvent(new PersonalDate(strText, startDate, prio));
			}
			startDate.add(GregorianCalendar.DAY_OF_MONTH, +1);
		}
	}

	public void addMunichWiesn(int maxFactor) {
		final String imgDirWiesn = "C:\\Users\\MiRoe\\Pictures\\Münchener Wiesn\\";
		GregorianCalendar oct1 = new GregorianCalendar(THIS_YEAR, Calendar.OCTOBER, 1);
		GregorianCalendar lastSunday = (GregorianCalendar)oct1.clone();
		while (lastSunday.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY) {
			lastSunday.add(GregorianCalendar.DAY_OF_MONTH, +1);
		}
		GregorianCalendar lastDay = (GregorianCalendar)lastSunday.clone();
		int lstIday = lastSunday.get(GregorianCalendar.DAY_OF_MONTH);
		if (lstIday < 3) {
			lastDay.add(GregorianCalendar.DAY_OF_MONTH, 3-lstIday);
		}
		GregorianCalendar firstDay = (GregorianCalendar)lastSunday.clone();
		firstDay.add(GregorianCalendar.DAY_OF_MONTH, -15);
		GregorianCalendar ccur = (GregorianCalendar)firstDay.clone();
		GregorianCalendar secondDay = (GregorianCalendar)firstDay.clone();
		secondDay.add(GregorianCalendar.DAY_OF_MONTH, +1);
		GregorianCalendar last2ndDay = (GregorianCalendar)lastDay.clone();
		last2ndDay.add(GregorianCalendar.DAY_OF_MONTH, -1);
		
		File icoFileEingang = new File(imgDirWiesn+"Haupteingang_Oktoberfest_201.png");
		if (mDates.getCalEvents(firstDay).size() < mDates.getCalEvents(secondDay).size()) {
			DaysBackgroundImage imgEingang = new DaysBackgroundImage(1, 0, icoFileEingang, 0.7f );
			PersonalDate startPD = new PersonalDate("{0,date,EE} ", firstDay, (byte)0);
			startPD.mBackgrounds = new ArrayList<IDaysBackground>(1);
			startPD.mBackgrounds.add(imgEingang);
			mDates.addCalEvent(startPD);			
			ccur.add(GregorianCalendar.DAY_OF_MONTH, +2);
		} else {
			DaysBackgroundImage imgEingang = new DaysBackgroundImage(3, 0, icoFileEingang, 0.6f );
			PersonalDate startPD = new PersonalDate("{0,date,EE} ", secondDay, (byte)0);
			startPD.mBackgrounds = new ArrayList<IDaysBackground>(1);
			startPD.mBackgrounds.add(imgEingang);
			mDates.addCalEvent(startPD);
			ccur.add(GregorianCalendar.DAY_OF_MONTH, +3);
		}
		if (mDates.getCalEvents(lastDay).size() < mDates.getCalEvents(last2ndDay).size()) {
			DaysBackgroundImage imgEingang = new DaysBackgroundImage(1, 0, icoFileEingang, 0.7f );
			PersonalDate startPD = new PersonalDate("{0,date,EE} ", lastDay, (byte)0);
			startPD.mBackgrounds = new ArrayList<IDaysBackground>(1);
			startPD.mBackgrounds.add(imgEingang);
			mDates.addCalEvent(startPD);			
		} else {
			DaysBackgroundImage imgEingang = new DaysBackgroundImage(3, 0, icoFileEingang, 0.6f );
			PersonalDate startPD = new PersonalDate("{0,date,EE} ", last2ndDay, (byte)0);
			startPD.mBackgrounds = new ArrayList<IDaysBackground>(1);
			startPD.mBackgrounds.add(imgEingang);
			mDates.addCalEvent(startPD);
		}
		addDaysBackgroundImage(ccur, lastDay, Math.min(maxFactor, 6), new File(imgDirWiesn+"Oktoberfest_2005_-_Riesenrad.png"));
		addDaysBackgroundImage(ccur, lastDay, Math.min(maxFactor, 4), new File(imgDirWiesn+"Hacker-Pschorr_Oktoberfest_Girl_Remix.png"));
		addDaysBackgroundImage(ccur, lastDay, Math.min(maxFactor, 4), new File(imgDirWiesn+"Muenchen-Oktoberfest-bjs2005-02.png"));
		addDaysBackgroundImage(ccur, lastDay, Math.min(maxFactor, 3), new File(imgDirWiesn+"Oktoberfest_2005_-_Riesenrad.png"));
		addDaysBackgroundImage(ccur, lastDay, Math.min(maxFactor, 2), new File(imgDirWiesn+"Hacker-Pschorr_Oktoberfest_Girl_Remix.png"));
	}
	
	private class ResultFindFreeSpace {
		GregorianCalendar firstDay;
		int nDays;
		
		public ResultFindFreeSpace() {
			firstDay = null;
			nDays = 0;
		}
	}

	private int addDaysBackgroundImage(GregorianCalendar beginDate, GregorianCalendar endDate, int maxDays, File backgroudIcoFile) {
		ResultFindFreeSpace insPoint = findFreeSpaceInOneMonth(beginDate, endDate, maxDays);
		if (insPoint.nDays < 1) {
			System.out.println("FotoKalender.addDaysBackgroundImage(,,,) : no free space found!");
		} else {
			DaysBackgroundImage img = new DaysBackgroundImage(insPoint.nDays, (insPoint.nDays & 1) != 0 ? -0.5f : 0f,
					backgroudIcoFile, insPoint.nDays < 3 ? 0.8f : 0.6f );
			GregorianCalendar cur = insPoint.firstDay;
			for (int iD = 0; iD < maxDays; ++iD) {
				PersonalDate pd = new PersonalDate("{0,date,EE} ", cur, (byte)0);
				if (iD >= maxDays/2 && img != null) {
					pd.mBackgrounds = new ArrayList<IDaysBackground>(1);
					pd.mBackgrounds.add(img);
					img = null;
				} else {
					pd.mBackgrounds = null;
				}
				mDates.addCalEvent(pd);
				cur = (GregorianCalendar)cur.clone();
				cur.add(GregorianCalendar.DAY_OF_MONTH, +1);
			}
		}
		return insPoint.nDays;
	}
	
	private ResultFindFreeSpace findFreeSpaceInOneMonth(GregorianCalendar beginDate, GregorianCalendar endDate, int maxDays) {
		int iMonth = beginDate.get(GregorianCalendar.MONTH);
		int eMonth = endDate.get(GregorianCalendar.MONTH);
		if (iMonth == eMonth) {
			return findFreeSpace(beginDate, endDate, maxDays);
		} else {
			ResultFindFreeSpace res1, res2;
			GregorianCalendar nextMonth = new GregorianCalendar(beginDate.get(GregorianCalendar.YEAR), iMonth+1, 1);
			GregorianCalendar endMonth = (GregorianCalendar)nextMonth.clone();
			endMonth.add(GregorianCalendar.DAY_OF_MONTH, -1);
			res1 = findFreeSpace(beginDate, endMonth, maxDays);
			if (res1.nDays >= maxDays) {
				return res1;
			} else {
				res2 = findFreeSpaceInOneMonth(nextMonth, endDate, maxDays);
				if (res1.nDays >= res2.nDays) {
					return res1;
				} else {
					return res2;
				}
			}
		}
	}

	private ResultFindFreeSpace findFreeSpace(GregorianCalendar beginDate, GregorianCalendar endDate, int maxDays) {
		System.out.println("Searching for "+ maxDays +" free days from "
				+ beginDate.get(GregorianCalendar.DAY_OF_MONTH) +". "
                + (beginDate.get(GregorianCalendar.MONTH)+1) +". .."
				+ endDate.get(GregorianCalendar.DAY_OF_MONTH) +". "
                + (endDate.get(GregorianCalendar.MONTH)+1) +"." );
		ResultFindFreeSpace result = new ResultFindFreeSpace();
		GregorianCalendar cur = (GregorianCalendar)beginDate.clone();
		while (!cur.after(endDate) && result.nDays < maxDays) {
			while (!cur.after(endDate) && !mDates.getCalEvents(cur).isEmpty()) {
				cur.add(GregorianCalendar.DAY_OF_MONTH, +1);
			}
			int iDays = 0;
			while (!cur.after(endDate) && mDates.getCalEvents(cur).isEmpty() && iDays <= maxDays) {
				cur.add(GregorianCalendar.DAY_OF_MONTH, +1);
				++iDays;
			}
			if (iDays > result.nDays) {
				result.firstDay = (GregorianCalendar)cur.clone();
				result.firstDay.add(GregorianCalendar.DAY_OF_MONTH, -iDays);
				result.nDays = iDays;
				System.out.println("find "+ iDays +" free, starting "
										+ result.firstDay.get(GregorianCalendar.DAY_OF_MONTH) +". "
						                + (result.firstDay.get(GregorianCalendar.MONTH)+1) +"." );
			}
		} /* while (!cur.after(endDate)) */		
		if (result.firstDay == null) {
			result.firstDay = (GregorianCalendar)beginDate.clone();
			result.nDays = 0;
		}
		System.out.println("find finally"+ result.nDays +" free, starting "
				+ result.firstDay.get(GregorianCalendar.DAY_OF_MONTH) +". "
                + (result.firstDay.get(GregorianCalendar.MONTH)+1) +"." );
		return result;
	}
	
	public void addCalEventWhenNotFree(PersonalDate calEvent) {
		int iFree = mDates.getPrio(calEvent.getDate());
		if ((iFree & PersonalDate.MICHAEL_HAS_FREE) != PersonalDate.MICHAEL_HAS_FREE) {
			mDates.addCalEvent(calEvent);
		}
	}
	
	public void addDaylightSavingTimeChanges () {
		addDaylightSavingTimeChanges(20, GregorianCalendar.MARCH, "Wingdings:\uF0B8\uF046\uF0B9");
		addDaylightSavingTimeChanges(20, GregorianCalendar.OCTOBER, "Wingdings:\uF0B9\uF046\uF0B8");
	}
	
	public void addDaylightSavingTimeChanges (int i1stDay, int iMonth, String strText) {
		GregorianCalendar date = new GregorianCalendar(THIS_YEAR, iMonth, i1stDay, 12, 0);
		TimeZone tz = date.getTimeZone();
		Date day1 = date.getTime();
		Date day2 = day1;
		int nMaxDays = 90;
		do {
			day1 = day2;
			day2 = new Date(day1.getTime()+PersonalDates.MILLIS_PER_DAY);
		} while (tz.inDaylightTime(day1) == tz.inDaylightTime(day2) && --nMaxDays >= 0);
		if (tz.inDaylightTime(day1) != tz.inDaylightTime(day2)) {
			date.setTime(day2);
			mDates.addCalEvent(new PersonalDate(strText, date, PersonalDate.PRIO_BIRTHDAY | PersonalDate.ALL_HAVE_FREE));
		}
	}
	
	public static void createEmptyTmpDir() {
		File tmpDir = new File("tmp");
		System.out.println("directory for temp files: "+ tmpDir.getAbsolutePath());
		tmpDir.mkdir();
		File[] files = tmpDir.listFiles();
		if (files == null) {
			System.err.println("missing directory for temp files: "+ tmpDir.getAbsolutePath());
		} else {
			for (File f1 : files) {
				boolean bDelSuc = f1.delete();
				if (!bDelSuc) {
					System.err.println("Could not delete "+ f1.getAbsolutePath());
				}
			}
		}
	}
	
	public static void waitForAllThreadsToFinish() {
		/* Waiting for all Threads to finish */
		Thread thisThread = Thread.currentThread();
		int nThreads = thisThread.getThreadGroup().activeCount();
		if (nThreads > 1) {
			System.out.println("Waiting for all Threads to finish.");
			Thread[] threads = new Thread[nThreads];
			int nThreads2 = thisThread.getThreadGroup().enumerate(threads);
			if (nThreads2 > nThreads) {
				System.err.println("Number of Threads unexpectedly changed: "+ nThreads +" < "+ nThreads2);
				nThreads2 = nThreads;
			}
			for (int iT = 0; iT < nThreads2; ++iT) {
				System.out.println("Waiting for Thread: "+threads[iT].getName());
				if (threads[iT] != thisThread) {
					try {
						threads[iT].join(30000);
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			int nThreads3 = thisThread.getThreadGroup().enumerate(threads);
			while (nThreads3 > 1) {
				System.err.println("Still "+ nThreads3 +" threads running! If you want to abort, press 'X'");
				for (int iT = 0; iT < nThreads3; ++iT) {
					if (threads[iT] != thisThread) {
						try {
							System.out.println("Waiting for Thread: "+threads[iT].getName());
							threads[iT].join(2000);
							if (System.in.available() > 0) {
								int inChar = System.in.read();
								System.out.println("Key No. "+ inChar +" pressed.");
								if (inChar == 'X' || inChar == 'x') {
									nThreads3 = -1;
								}
							}
						} catch(Exception ex) {
							MiRoeIoUtil.logException("", ex);
						}
					}
				}
				if (nThreads3 > 0) {
					nThreads3 = thisThread.getThreadGroup().enumerate(threads);
				}
			}
		}
	}
}
