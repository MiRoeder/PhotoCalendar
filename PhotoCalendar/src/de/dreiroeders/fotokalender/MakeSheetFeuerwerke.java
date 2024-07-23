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
			SourceImage imgFabianVerbrenntPapier = new SourceImage("http://www.3roeders.de/Signal/signal-2021-09-05-185912.jpg");
			int nCols = imgFabianVerbrenntPapier.isOk() ? 1 : 0;
			// TGIF 2022 - week51
			SourceImage imgXMasMenPutInGarbage = new SourceImage("https://blogger.googleusercontent.com/img/a/AVvXsEi1SSLp0KrgQCKk67f9ns9r_D9Gfnn2BC6mSHMX8ZO9U-9QmboN6AIOJSFcQhRRnfll9XSHYuy_mDxzQM8LM9KBr3NAE__K8W4UdT3J9tVbzQ-pQgxfIIzbmGz3vSinLaN-caSFJcGK0Q5Jlz_kx1itQW_B5cm28JzsgV9jVBxGKHs_LZvvG3_cl_js=s420");
			if (imgXMasMenPutInGarbage.isOk()) {
				++nCols;
			}
			// TGIF 2024 - week22
			SourceImage imgDeepLearning = new SourceImage("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEicO7UjPnP5DsTmTDGX8jssmsgdHCszdX38onClGHmyDMTfXLgPUJYMoTX3csGGc7vpUandtsfpRy1ClxUlbJo1GJOm0zPftYOvwMX7gwoKEqd0PrwUiQxEgdpJEUHpjWQBWpKmkqmpD9q1TvrD3AGK0gMfjWrZeBjFu6EuV4d7KW6gLvpKZd12SSnwY1I/s772/pc11_deeplearning.jpg");
			if (imgDeepLearning.isOk()) {
				++nCols;
			}
			// TGIF 2024 - week12
			SourceImage imgHaeufigerErnaehrungsfehler = new SourceImage("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhODJSzhkIBp671XKkQcstsGmpSHuaDMVdLkUI0NnL1brjEpltGFJ52D2b-eOgrCZLaW7bTaaTgl2GDwsqfecwT0ZsI8ASe3Y1w368ebVcjV0Jf_W_YDhyozVePzhq_qqE6RXJJrOQs2Jw62u18s325KzZc9CJMIMFq6XajBKCvo7SXLJbT1tt2TaLkIhk/s320/essen01_diaet02.jpg");
			if (imgHaeufigerErnaehrungsfehler.isOk()) {
				++nCols;
			}
			// TGIF 2024 - week18
			SourceImage imgPerfekteFrau = new SourceImage("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEijQFeICxcFvmPQ9l9J32oWMKrLA4DE1bQvbgPGbmbsu9VtCts4qtHMb5vJR2Cheb_7JmZ5GxTrxYBK5SjoIPDnWQ5scJivaboJWyUlyCuzC1shYQ4AWMHkucj5ivBuL3YjNRaxpgIF4Z0vBxYvhVHu4IWYJMkID-ZQVPf9nhCaXQz-GEtw0dItNb_5iRo/s640/arbeit09_kellnerin.jpg");
			if (imgPerfekteFrau.isOk()) {
				++nCols;
			}

			final float wSteg = CalendarSheet.fWeight < 1 ? .005f : .003f;
			final float wCol1 = (1 - (nCols-1)*wSteg) / nCols;
			final Color colTxt = new Color(255,224,192);
			mSheet.drawText("Achtung! Bitte im neuen Jahr "+ SHOW_YEAR +" beachten:", colTxt, Font.SANS_SERIF, Font.PLAIN, .1f, 0.75f, .8f, 0.048);
			float xC = 0f;
			if (imgFabianVerbrenntPapier.isOk()) {
				float wText = wCol1/3f;
				float wPic = wCol1-wText;
				mSheet.drawImage(imgFabianVerbrenntPapier          , 0.5, 0.5, 0,  xC+wText, 0.800, wPic , 0.200f);
				mSheet.drawText("Alte Akten", colTxt, Font.SERIF,      Font.PLAIN, xC,       0.850, wText, 0.025f);
				mSheet.drawText("vernichten", colTxt, Font.SERIF,      Font.PLAIN, xC,       0.885, wText, 0.025f);
				mSheet.drawText("  gemäß",    colTxt, Font.SERIF,      Font.PLAIN, xC,       0.920, wText, 0.025f);
				mSheet.drawText("DSGVO:",     colTxt, Font.SERIF,      Font.PLAIN, xC,       0.955, wText, 0.025f);
				xC = wCol1 + wSteg;
			}
			if (imgXMasMenPutInGarbage.isOk()) {
				float wPicSrc = imgXMasMenPutInGarbage.getWidth()/imgXMasMenPutInGarbage.getHeight()*mSheet.getUsuableHeight()*.200f;	
				float wText = wCol1/3f;
				float wPicTrg = (wCol1-wText)*mSheet.getUsuableWidth();
				float wPic = Math.min(wPicSrc, wPicTrg);
				mSheet.drawImage(imgXMasMenPutInGarbage           , 0.5, 0.5, 0,  xC+wText, 0.800, wPic , 0.200f);
				mSheet.drawText(" Alten ",   colTxt, Font.SERIF,      Font.PLAIN, xC,       0.850, wText, 0.025f);
				mSheet.drawText(" Kram",     colTxt, Font.SERIF,      Font.PLAIN, xC,       0.885, wText, 0.025f);
				mSheet.drawText("weg-",      colTxt, Font.SERIF,      Font.PLAIN, xC,       0.920, wText, 0.025f);
				mSheet.drawText("schmeißen", colTxt, Font.SERIF,      Font.PLAIN, xC,       0.955, wText, 0.025f);
				xC += wCol1 + wSteg;
			}
			if (imgHaeufigerErnaehrungsfehler.isOk()) {
				float wPicSrc = imgHaeufigerErnaehrungsfehler.getWidth()/imgHaeufigerErnaehrungsfehler.getHeight()*mSheet.getUsuableHeight()*.200f;	
				float wText = wCol1/3f;
				float wPicTrg = (wCol1-wText)*mSheet.getUsuableWidth();
				float wPic = Math.min(wPicSrc, wPicTrg);
				mSheet.drawImage(imgHaeufigerErnaehrungsfehler    , 0.5, 0.5, 0,  xC+wText, 0.800, wPic , 0.200f);
				mSheet.drawText(" Sich ",    colTxt, Font.SERIF,      Font.PLAIN, xC,       0.860, wText, 0.025f);
				mSheet.drawText("gesund",    colTxt, Font.SERIF,      Font.PLAIN, xC,       0.895, wText, 0.025f);
				mSheet.drawText("ernähren:", colTxt, Font.SERIF,      Font.PLAIN, xC,       0.930, wText, 0.025f);
				xC += wCol1 + wSteg;
			}
			if (imgDeepLearning.isOk()) {
				float wPicSrc = imgDeepLearning.getWidth()/imgDeepLearning.getHeight()*mSheet.getUsuableHeight()*.200f;	
				float wText = wCol1/3f;
				float wPicTrg = (wCol1-wText)*mSheet.getUsuableWidth();
				float wPic = Math.min(wPicSrc, wPicTrg);
				mSheet.drawImage(imgDeepLearning                        , 0.5, 0.5, 0,  xC+wText, 0.800, wPic , 0.200f);
				mSheet.drawText("Aktuellen",       colTxt, Font.SERIF,      Font.PLAIN, xC,       0.850, wText, 0.025f);
				mSheet.drawText("Stand der",       colTxt, Font.SERIF,      Font.PLAIN, xC,       0.885, wText, 0.025f);
				mSheet.drawText("Technik",         colTxt, Font.SERIF,      Font.PLAIN, xC,       0.920, wText, 0.025f);
				mSheet.drawText("verwenden",       colTxt, Font.SERIF,      Font.PLAIN, xC,       0.955, wText, 0.025f);
				xC += wCol1 + wSteg;
			}
			if (imgPerfekteFrau.isOk()) {
				float wPicSrc = imgPerfekteFrau.getWidth()/imgPerfekteFrau.getHeight()*mSheet.getUsuableHeight()*.200f;	
				float wText = wCol1/3f;
				float wPicTrg = (wCol1-wText)*mSheet.getUsuableWidth();
				float wPic = Math.min(wPicSrc, wPicTrg);
				mSheet.drawImage(imgPerfekteFrau                      , 0.5, 0.5, 0,  xC+wText, 0.800, wPic , 0.200f);
				mSheet.drawText("Die perfekte",  colTxt, Font.SERIF,      Font.PLAIN, xC,       0.850, wText, 0.025f);
				mSheet.drawText("Frau ist",      colTxt, Font.SERIF,      Font.PLAIN, xC,       0.885, wText, 0.025f);
				mSheet.drawText("selten. Warte", colTxt, Font.SERIF,      Font.PLAIN, xC,       0.920, wText, 0.025f);
				mSheet.drawText("nicht auf sie", colTxt, Font.SERIF,      Font.PLAIN, xC,       0.955, wText, 0.025f);
				xC += wCol1 + wSteg;
			}
		}
		mSheet.drawCalDates();
		mSheet.writeExJpg(StrOUT_DIR);
	} /* end of makeFeuerwerkBerlin() */
	
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
