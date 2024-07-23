package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.util.Calendar;

import de.dreiroeders.workingonimages.BufferedImageSetPixImg_ABGR;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.EFillType;
import de.dreiroeders.workingonimages.*;

public class MakeSheetFeuerwerke extends Thread {

	public static MakeSheetFeuerwerke startMaking(int nYear, int month, PersonalDates mDates, String strOutDir) {
		MakeSheetFeuerwerke executer = new MakeSheetFeuerwerke(nYear, month, mDates, strOutDir);
		executer.start();
		return executer;
	}
	
	public static MakeSheetFeuerwerke startMaking(int nYear, int month, PersonalDates mDates, String strOutDir, int nOpt) {
		MakeSheetFeuerwerke executer = new MakeSheetFeuerwerke(nYear, month, mDates, strOutDir, nOpt);
		executer.start();
		return executer;
	}
	
	private final int THIS_YEAR;
	private final int SHOW_YEAR;
	private final int nMonth;
	private final PersonalDates CAL_Dates;
	private final String StrOUT_DIR;
	private final int m_nOpt;
	private CalendarSheet mSheet;
	
	public MakeSheetFeuerwerke(int nYear, int month, PersonalDates mDates, String strOutDir) {
		THIS_YEAR = nYear;
		nMonth    = month;
		SHOW_YEAR = month >= Calendar.DECEMBER ? nYear+1 : nYear;
		CAL_Dates = mDates;
		StrOUT_DIR= strOutDir;
		m_nOpt = 0;
		setName("MakeSheetFeuerwerke_"+nYear+"-"+month);
	}

	public MakeSheetFeuerwerke(int nYear, int month, PersonalDates mDates, String strOutDir, int nOpt) {
		THIS_YEAR = nYear;
		nMonth    = month;
		SHOW_YEAR = month >= Calendar.DECEMBER ? nYear+1 : nYear;
		CAL_Dates = mDates;
		StrOUT_DIR= strOutDir;
		m_nOpt = nOpt;
		setName("MakeSheetFeuerwerke_"+nYear+"-"+month);
	}

	@Override
	public void run() {
		System.out.println("Begin of MakeSheetFeuerwerke_ for "+ THIS_YEAR +"-"+ (nMonth+1));
		try {
			makeFeuerwerkBerlin();
			System.out.println("End of MakeSheetFeuerwerke_ for "+ THIS_YEAR +"-"+ (nMonth+1));
		} catch (Throwable ex) {
			System.err.println("\nProblem when MakingSheetFeuerwerke for "+ THIS_YEAR +"-"+ (nMonth+1));
			ex.printStackTrace();
		}
	}

	public void makeFeuerwerkBerlin() throws Exception {
		final String picRuedi2 = "http://www.3roeders.de/Feuerwerke/Ruedi2023_2.jpg";
		final String picRuedi5 = "http://www.3roeders.de/Feuerwerke/Ruedi2023_5.jpg";
		final String picRuedi6 = "http://www.3roeders.de/Feuerwerke/Ruedi2023_6.jpg";
		final String picRuediKlo = "http://www.3roeders.de/2014-00-Neujahr/DSC01809.JPG";
		float h3 = .8f;
		final float fY8=h3+0.165f;
		mSheet = new CalendarSheet(THIS_YEAR, nMonth, CAL_Dates);
		mSheet.prepareImage(5000, Color.BLACK);
		BufferedImageSetPixImg_ABGR img1;
		float rotC;
		SourceImage srcMainImg = new SourceImage("https://upload.wikimedia.org/wikipedia/commons/4/49/New_Year_Berlin.jpg");
		if (srcMainImg.isOk()) {
			mSheet.drawImage(srcMainImg , 0.5, 0.5, 0.0 , 0.000, 0.000, 1f, 1f);
		} else {
			srcMainImg = new SourceImage("https://upload.wikimedia.org/wikipedia/commons/4/49/New_Year_Berlin.jpg");
			//TODO
		}
		img1 = mkPicFeuerwerk(picRuedi2, 0.5f);
		mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.2,   0 , 0.000, 0.000, 0.350, 0.35));
		img1 = mkPicFeuerwerk(picRuedi5, 0.4f);
		mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.3,   0 , 0.650, 0.300, 0.350, 0.35));
		img1 = mkPicFeuerwerk(picRuedi6, 0.4f);
		mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.3,   0 , 0.700, 0.400, 0.300, 0.35));
		img1 = mkPicFeuerwerk(picRuediKlo, 0.8f);
		rotC = (float)(2456-2528) / (2829-4267) * 0.8f;
		mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    ,  .5,  .5,-rotC, 0.800, fY8-.25f,0.200,0.25f));
		writeYear();
		if (m_nOpt > 0) {
			final int nCols = CalendarSheet.fWeight < 1 ? 3 : 6;
			final float wSteg = CalendarSheet.fWeight < 1 ? .005f : .003f;
			final float wCol1 = (1 - (nCols-1)*wSteg) / nCols;
			final float wText = wCol1/3f;
			final float wPic = wCol1-wText;
			final Color colTxt = new Color(255,224,192);
			mSheet.drawText("Achtung! Bitte im neuen Jahr "+ SHOW_YEAR +" beachten:", colTxt, Font.SANS_SERIF, Font.PLAIN, .1f, 0.8f, .8f, 0.048);
			final String strFabianVerbrenntPapier = "http://www.3roeders.de/Signal/signal-2021-09-05-185912.jpg";
			float wText01 = 0.01f;
			mSheet.drawText("Alte Akten",               colTxt, Font.SERIF,      Font.PLAIN, wText01, 0.850, wText-wText01, 0.025f);
			mSheet.drawText("vernichten",               colTxt, Font.SERIF,      Font.PLAIN, wText01, 0.885, wText-wText01, 0.025f);
			mSheet.drawText("  gemäß",                  colTxt, Font.SERIF,      Font.PLAIN, wText01, 0.920, wText-wText01, 0.025f);
			mSheet.drawText("DSGVO:",                   colTxt, Font.SERIF,      Font.PLAIN, wText01, 0.955, wText-wText01, 0.025f);
			mSheet.drawImage(strFabianVerbrenntPapier                      , 0.5, 0.5, 0,  wText, 0.850, wPic , 0.150f);
			float xC = wCol1 + wSteg;
			String strAufFrauenHoer;
			float iWidAFH, iHeiAFH;
			if (THIS_YEAR <= 2024) {
				// Charles Schlecht Angezogen
				strAufFrauenHoer = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\FB_IMG_1663422808486b.jpg"; // 892x914 pixel
				iWidAFH = 892; iHeiAFH = 914;
			} else
			if ((THIS_YEAR & 1) == 0 /* gerade Jahre */) {
				// Löwin faucht Löwe an.
				strAufFrauenHoer = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\FB_IMG_1664953678923.jpg"; // 640x1386 pixel, benutzt volle Breite, aber  nur Höhe von 380 bis 990
				iWidAFH = 640; iHeiAFH = 1386-2*380;
			} else
			if ((THIS_YEAR & 3) == 1 /* nach Schaltjahren */) {
				// Israelin mit Maschinenpistole hält Mann an der Hand
				strAufFrauenHoer = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\FB_IMG_1628713071363.jpg"; // 572x960
				iWidAFH = 572; iHeiAFH = 960;
			} else {
				// Frau sagt, Mann sei nicht von ihr dominiert.
				strAufFrauenHoer = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\40392902_1943019619097605_5584471711262179328_n.jpg"; // 720x638 pixel inklusive Rand
				iWidAFH = 720; iHeiAFH = 638;
			}
			float fWidAFH = iWidAFH/iHeiAFH/mSheet.getUsuableWidth()*mSheet.getUsuableHeight()*.150f;		
			float fWiTAFH = wCol1 - fWidAFH;
			if (fWiTAFH > .05f) {
				mSheet.drawText("    Den",          colTxt, Font.SERIF,      Font.PLAIN, xC, 0.860,fWiTAFH, 0.025f);
				mSheet.drawText(" Frauen",          colTxt, Font.SERIF,      Font.PLAIN, xC, 0.895,fWiTAFH, 0.025f);
				mSheet.drawText("zuhören:",         colTxt, Font.SERIF,      Font.PLAIN, xC, 0.930,fWiTAFH, 0.025f);
			}
			mSheet.drawImage(strAufFrauenHoer                           , 0.5, 0.5, 0,    xC+wCol1-fWidAFH,
					                                                                          0.850,fWidAFH, 0.150f);
			xC += wCol1 + wSteg;
			final String strRegenbogenZumToilettenhaus = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\FB_IMG_1663919192758.jpg";
			mSheet.drawText("Nicht alles",           colTxt, Font.SERIF,      Font.PLAIN, xC, 0.850, wText, 0.025f);
			mSheet.drawText("glauben, was",          colTxt, Font.SERIF,      Font.PLAIN, xC, 0.885, wText, 0.025f);
			mSheet.drawText("im Internet",           colTxt, Font.SERIF,      Font.PLAIN, xC, 0.920, wText, 0.025f);
			mSheet.drawText("  steht:",              colTxt, Font.SERIF,      Font.PLAIN, xC, 0.955, wText, 0.025f);
			mSheet.drawImage(strRegenbogenZumToilettenhaus         , 0.5, 0.5, 0,   xC+wText, 0.850, wPic , 0.150f);
			if (nCols >= 6) {
				final String strSchokoEiVsRiegel = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\FB_IMG_1614413609587.jpg";
				iWidAFH = 706f; iHeiAFH = 960f;
				xC += wCol1 + wSteg;
				fWidAFH = iWidAFH/iHeiAFH/mSheet.getUsuableWidth()*mSheet.getUsuableHeight()*.150f;		
				fWiTAFH = wCol1 - fWidAFH;
				if (fWiTAFH > .05f) {
					mSheet.drawText(" Sich",         colTxt, Font.SERIF,      Font.PLAIN, xC, 0.860,fWiTAFH, 0.025f);
					mSheet.drawText(" gesund",       colTxt, Font.SERIF,      Font.PLAIN, xC, 0.895,fWiTAFH, 0.025f);
					mSheet.drawText("ernähren:",     colTxt, Font.SERIF,      Font.PLAIN, xC, 0.930,fWiTAFH, 0.025f);
				}
				mSheet.drawImage(strSchokoEiVsRiegel                    , 0.5, 0.5, 0,    xC+wCol1-fWidAFH,
                                                                                              0.850,fWidAFH, 0.150f);
			}
			if (nCols >= 4) {
				final String strKatzekauftStreu = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\FB_IMG_1603107441186.jpg";
				iWidAFH = 746f; iHeiAFH = 960f;
				xC += wCol1 + wSteg;
				fWidAFH = iWidAFH/iHeiAFH/mSheet.getUsuableWidth()*mSheet.getUsuableHeight()*.150f;		
				fWiTAFH = wCol1 - fWidAFH;
				if (fWiTAFH > .05f) {
					mSheet.drawText("Rechtzeitig",   colTxt, Font.SERIF,      Font.PLAIN, xC, 0.860,fWiTAFH, 0.025f);
					mSheet.drawText("Vorsorge",      colTxt, Font.SERIF,      Font.PLAIN, xC, 0.895,fWiTAFH, 0.025f);
					mSheet.drawText("treffen:",      colTxt, Font.SERIF,      Font.PLAIN, xC, 0.930,fWiTAFH, 0.025f);
				}
				mSheet.drawImage(strKatzekauftStreu                     , 0.5, 0.5, 0,    xC+wCol1-fWidAFH,
                                                                                              0.850,fWidAFH, 0.150f);
			}
			if (nCols >= 5) {
				final String strFrauSehrWarmAngezogen = "C:\\Users\\MiRoe\\Pictures\\GalaxyS8\\Facebook\\43698285_1921852824578075_7129875109858246656_n.jpg";
				iWidAFH = 795f; iHeiAFH = 960f;
				xC += wCol1 + wSteg;
				fWidAFH = iWidAFH/iHeiAFH/mSheet.getUsuableWidth()*mSheet.getUsuableHeight()*.150f;		
				fWiTAFH = wCol1 - fWidAFH;
				if (fWiTAFH > .05f) {
					mSheet.drawText("Auf kalte",     colTxt, Font.SERIF,      Font.PLAIN, xC, 0.860,fWiTAFH, 0.025f);
					mSheet.drawText("Temperaturen",  colTxt, Font.SERIF,      Font.PLAIN, xC, 0.895,fWiTAFH, 0.025f);
					mSheet.drawText("vorbereiten:",  colTxt, Font.SERIF,      Font.PLAIN, xC, 0.930,fWiTAFH, 0.025f);
				}
				mSheet.drawImage(strFrauSehrWarmAngezogen               , 0.5, 0.5, 0,    xC+wCol1-fWidAFH,
                                                                                              0.850,fWidAFH, 0.150f);
			}
		}
		mSheet.drawCalDates();
		mSheet.writeExJpg(StrOUT_DIR);
	}
	
	public void writeYear() {
		String strYear = Integer.toString(SHOW_YEAR);
		int nLenX = strYear.length();
		float fLenX2 = (float)(nLenX-1)/2f;
		int wi = mSheet.getUsuableWidth() / (nLenX+1);
		int wi_overlap = wi/5;
		int dY1 = mSheet.getUsuableHeight() / Math.max(nLenX, 10);
		int x0 = wi / 2 - wi_overlap;
		Rectangle outRect = new Rectangle();
		outRect.width = wi + 2*wi_overlap;
		outRect.height = mSheet.getUsuableHeight() * 2/5;
		for (int iD = 0; iD < nLenX; ++iD) {
			outRect.x = mSheet.getX1() + x0 + iD*wi;
			outRect.y = mSheet.getY1() + (int)(Math.max(Math.abs(fLenX2-iD)-.5f, 0f) * dY1);
			char c1 = strYear.charAt(iD);
			File filCh = new File("res\\Digit"+c1+"_"+iD+".png");
			if (!filCh.exists()) {
				filCh = new File("res\\Digit"+c1+".png");
			}
			Draw1ImageI imi = new Draw1ImageI(filCh, EFillType.TransparentTarget);
			imi.setCenterPoint(.5f, .9f);
			imi.dRot = (iD-fLenX2) / nLenX * 0.5f;
			imi.iOutRect = outRect;
			mSheet.drawImage(imi);
		}
	}
	
	public BufferedImageSetPixImg_ABGR mkPicFeuerwerk(String strInImage, float fak) {
		try {
			BufferedImageSetPixImg_ABGR image = new BufferedImageSetPixImg_ABGR(strInImage);
			return mkPicFeuerwerk(image, fak);
		} catch (Exception ex) {
			System.err.println();
			System.err.println("Problem with picture \""+ strInImage +"\":");
			ex.printStackTrace();
			return new BufferedImageSetPixImg_ABGR(1, 1, 1f);
		}
	}
	
	public BufferedImageSetPixImg_ABGR mkPicFeuerwerk(BufferedImageSetPixImg_ABGR inImage, float fak) {
		int srcWidth = inImage.getWidth();
		int srcHeight = inImage.getHeight();
		for (int iY = 0; iY < srcHeight; ++iY) {
			for (int iX = 0; iX < srcWidth; ++iX) {
				MakeDarkTransparent.mk1PixFeuerwerk(inImage, iX, iY, fak);
			}
		}
		return inImage;
	}
	
}
