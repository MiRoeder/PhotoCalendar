package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Random;

import de.dreiroeders.io.MiRoeIoUtil;
import de.dreiroeders.workingonimages.BufferedImageSetPixImg_ABGR;
import de.dreiroeders.workingonimages.MiRoesDraw;
import de.dreiroeders.workingonimages.SourceImage;

public class MakeSheetFeuerwerke extends Thread {

	public static MakeSheetFeuerwerke startMaking(int nYear, int month, PersonalDates mDates, String strOutDir, int nOpt) {
		MakeSheetFeuerwerke executor = new MakeSheetFeuerwerke(nYear, month, mDates, strOutDir, nOpt);
		executor.start();
		return executor;
	}


	private static class Feuerwerk1 {
		public String strFilename;
		public float  fac;

		public Feuerwerk1(String strFilename, float fac) {
			this.strFilename = strFilename;
			this.fac = fac;
		}
	} /* end of class Feuerwerk1 */

	private static final Feuerwerk1 [] picsFirework = {
			new Feuerwerk1("20200101_000328_10a.jpg", .6f),
			new Feuerwerk1("20200101_000328_10b.jpg", .6f),
			new Feuerwerk1("20200101_000328_11a.jpg", .6f),
			new Feuerwerk1("20200101_000328_11b.jpg", .6f),
			new Feuerwerk1("20200101_000328_12a.jpg", .6f),
			new Feuerwerk1("20200101_000328_12b.png", .6f),
			new Feuerwerk1("20200101_000328_14a.jpg", .6f),
			new Feuerwerk1("20200101_000328_16a.jpg", .6f),
			new Feuerwerk1("20200101_000328_16b.jpg", .5f),
			new Feuerwerk1("20200101_000328_18a.jpg", .6f),
			new Feuerwerk1("20200101_000328_18b.jpg", .6f),
			new Feuerwerk1("20200101_000328_18c.jpg", .5f),
			new Feuerwerk1("20200101_000328_19a.jpg", .6f),
			new Feuerwerk1("20200101_000328_25a.jpg", .6f),
			new Feuerwerk1("20200101_000328_26a.jpg", .6f),
			new Feuerwerk1("20200101_000328_28a.jpg", .6f),
			new Feuerwerk1("20200101_000328_30a.jpg", .6f),
			new Feuerwerk1("20200101_000328_34a.jpg", .6f),
			new Feuerwerk1("20200101_000328_37a.jpg", .6f),
			new Feuerwerk1("20200101_000328_38a.jpg", .6f),
			new Feuerwerk1("20200101_000328_38b.jpg", .6f),
			new Feuerwerk1("20200101_000328_39a.jpg", .6f),
			new Feuerwerk1("20200101_000328_39b.jpg", .6f),
			new Feuerwerk1("20200101_000328_3a.png", .6f),
			new Feuerwerk1("20200101_000328_43.jpg", .6f),
			new Feuerwerk1("20200101_000328_43a.jpg", .5f),
			new Feuerwerk1("20200101_000328_45a.jpg", .6f),
			new Feuerwerk1("20200101_000328_46a.jpg", .6f),
			new Feuerwerk1("20200101_000328_49a.jpg", .6f),
			new Feuerwerk1("20200101_000328_54a.jpg", .6f),
			new Feuerwerk1("20200101_000328_55a.jpg", .1f),
			new Feuerwerk1("20200101_000328_56a.jpg", .6f),
			new Feuerwerk1("20200101_000328_56b.jpg", .6f),
			new Feuerwerk1("20200101_000328_57a.jpg", .6f),
			new Feuerwerk1("20200101_000328_58a.jpg", .6f),
			new Feuerwerk1("20200101_000328_5a.jpg", .6f),
			new Feuerwerk1("20200101_000328_64a.jpg", .6f),
			new Feuerwerk1("20200101_000328_66a.jpg", .6f),
			new Feuerwerk1("20200101_000328_66b.jpg", .6f),
			new Feuerwerk1("20200101_000328_68a.jpg", .6f),
			new Feuerwerk1("20200101_000328_71a.jpg", .6f),
			new Feuerwerk1("20200101_000328_72a.jpg", .6f),
			new Feuerwerk1("20200101_000328_86a.jpg", .6f),
			new Feuerwerk1("20200101_000328_86b.jpg", .6f),
			new Feuerwerk1("20200101_000328_86c.jpg", .6f),
			new Feuerwerk1("20200101_000328_86d.jpg", .6f),
			new Feuerwerk1("20200101_000927_1a.jpg", .6f),
			new Feuerwerk1("20200101_000927_1b.jpg", .6f),
			new Feuerwerk1("20200101_000927_2a.jpg", .2f),
			new Feuerwerk1("20200101_000927_3a.jpg", .3f),
			new Feuerwerk1("20200101_000927_4a.jpg", .3f),
			new Feuerwerk1("20200101_000927_4b.jpg", .6f),
			new Feuerwerk1("20200101_000927_4c.jpg", .6f),
			new Feuerwerk1("20200101_000927_5a.jpg", .6f),
			new Feuerwerk1("20200101_000927_5b.jpg", .6f),
			new Feuerwerk1("20200101_000927_6a.jpg", .6f),
			new Feuerwerk1("20200101_000927_6b.jpg", .6f),
			new Feuerwerk1("20200101_001558_1.jpg", .6f),
			new Feuerwerk1("20200101_001558_2.jpg", .6f),
			new Feuerwerk1("20200101_001826_1.jpg", .6f),
			new Feuerwerk1("20200101_002007_10a.jpg", .6f),
			new Feuerwerk1("20200101_002007_1a.jpg", .6f),
			new Feuerwerk1("20200101_002007_3a.jpg", .6f),
			new Feuerwerk1("20200101_002007_4a.jpg", .6f),
			new Feuerwerk1("20200101_002007_5a.jpg", .6f),
			new Feuerwerk1("20200101_002007_6a.jpg", .6f),
			new Feuerwerk1("20200101_002007_7a.jpg", .6f),
			new Feuerwerk1("20200101_002336_1_01.jpg", .6f),
			new Feuerwerk1("20200101_002336_1_02a.jpg", .3f),
			new Feuerwerk1("20200101_000328_72b.jpg", .6f),
			new Feuerwerk1("20200101_000328_29a.jpg", .6f),
			new Feuerwerk1("20200101_002007_1_01a.jpg", .6f),
			new Feuerwerk1("20200101_002007_2a.jpg", .6f),
			new Feuerwerk1("20200101_002007_8a.png", .6f),
			new Feuerwerk1("20200101_002007_9a.jpg", .6f),
	};


	private final int THIS_YEAR;
	private final int SHOW_YEAR;
	private final int nMonth;
	private final PersonalDates CAL_Dates;
	private final String StrOUT_DIR;
	private final int m_nOpt;
	private CalendarSheet mSheet;
	private int mNewYearDigitsCenterSpace;
	
	public MakeSheetFeuerwerke(int nYear, int month, PersonalDates mDates, String strOutDir, int nOpt) {
		THIS_YEAR = nYear;
		nMonth    = month;
		SHOW_YEAR = month >= Calendar.DECEMBER ? nYear+1 : nYear;
		CAL_Dates = mDates;
		StrOUT_DIR= strOutDir;
		m_nOpt = nOpt;
		setName("MakeSheetFeuerwerke_"+nYear+"-"+month);
		mNewYearDigitsCenterSpace = 0;
	}

	@Override
	public void run() {
		System.out.println("Begin of MakeSheetFeuerwerke_ for "+ THIS_YEAR +"-"+ (nMonth+1));
		try {
			makeFeuerwerkBerlin();
			System.out.println("End of MakeSheetFeuerwerke_ for "+ THIS_YEAR +"-"+ (nMonth+1));
		} catch (Throwable ex) {
			MiRoeIoUtil.logException("Problem when MakingSheetFeuerwerke for "+ THIS_YEAR +"-"+ (nMonth+1), ex);
		}
	}

	@SuppressWarnings({"ReassignedVariable", "ConstantConditions"})
	public void makeFeuerwerkBerlin() throws Exception {
		mSheet = new CalendarSheet(THIS_YEAR, nMonth, CAL_Dates);
		mSheet.mBackBackgroundCol = Color.BLACK;
		SourceImage srcMainImg = null;
		mSheet.prepareImage(5000, Color.BLACK);
		if ((m_nOpt & 2) == 2) {
			srcMainImg = new SourceImage("C:\\Users\\MiRoe\\Pictures\\Berlin\\38962551524_0ee9419415_o.jpg");
		}
		boolean bAddMoreFirework = true;
		if (srcMainImg == null || !srcMainImg.isOk()) {
			srcMainImg = new SourceImage("https://upload.wikimedia.org/wikipedia/commons/4/49/New_Year_Berlin.jpg");
			bAddMoreFirework = !srcMainImg.isOk();
		}

		if (!srcMainImg.isOk()) {
			srcMainImg = new SourceImage("https://media.cntraveller.de/photos/67b461ca5030272ccf64ee16/16:9/w_2240,c_limit/Feenstaub%20und%20Pyrotechnik-PR-global-3.jpg");
			mNewYearDigitsCenterSpace = mSheet.getUsuableWidth()/12;
			bAddMoreFirework = !srcMainImg.isOk();
		}
		boolean bHintsForNewYear = (m_nOpt & 1) != 0;
		if (srcMainImg.isOk()) {
			BufferedImage mainImg = srcMainImg.getImage();
			for (int ix = 0; ix < srcMainImg.getWidth(); ++ix) {
				for (int iy = 0; iy < srcMainImg.getHeight(); ++iy) {
					int dwPix = mainImg.getRGB(ix, iy);
					dwPix = (((dwPix>>16)&0xFF)*3/4<<16) | (((dwPix>>8)&0xFF)*3/4<<8) | (((dwPix)&0xFF)*3/4);
					mainImg.setRGB(ix, iy, dwPix);
				}				
			}
			mSheet.drawImage(mainImg , 0.5, 0.5, 0.0 , 0.000, 0.000, 1f, bHintsForNewYear ? .799f : 1f);
		} else {
			bAddMoreFirework = true;
		}
		if (bAddMoreFirework) {
			final String picRuedi2 = "http://www.3roeders.de/Feuerwerke/Ruedi2023_2.jpg";
			final String picRuedi5 = "http://www.3roeders.de/Feuerwerke/Ruedi2023_5.jpg";
			final String picRuedi6 = "http://www.3roeders.de/Feuerwerke/Ruedi2023_6.jpg";
			final String picRuediKlo = "http://www.3roeders.de/2014-00-Neujahr/DSC01809.JPG";
			BufferedImageSetPixImg_ABGR img1;
			img1 = mkPicFeuerwerk(picRuedi2, 0.5f);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.2,   0 , 0.000, 0.000, 0.250, 0.25));
			img1 = mkPicFeuerwerk(picRuedi5, 0.4f);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.3,   0 , 0.300, 0.250, 0.250, 0.25));
			img1 = mkPicFeuerwerk(picRuedi6, 0.4f);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.3,   0 , 0.200, 0.200, 0.200, 0.25));
			img1 = mkPicFeuerwerk(picRuediKlo, 0.8f);
			float rotC = (float)(2456-2528) / (2829-4267) * 0.8f;
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    ,  .5,  .5,-rotC, 0.920, 0.350, 0.080, 0.25f));
			img1 = getPicFeuerwerk(26);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.400, 0.100, 0.080, 0.12));
			img1 = getPicFeuerwerk(8);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.050, 0.000, 0.100, 0.14));
			img1 = getPicFeuerwerk(51);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.650, 0.200, 0.100, 0.11));
			img1 = getPicFeuerwerk(51);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.300, 0.200, 0.100, 0.11));
			img1 = getPicFeuerwerk(1);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.050, 0.200, 0.150, 0.18));
			img1 = getPicFeuerwerk(11);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.400, 0.200, 0.058, 0.08));
			img1 = getPicFeuerwerk(31);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.650, 0.100, 0.100, 0.2));
			img1 = getPicFeuerwerk(50);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.850, 0.100, 0.150, 0.2));
			img1 = getPicFeuerwerk(63);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.900, 0.000, 0.100, 0.2));
			img1 = getPicFeuerwerk(74);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.280, 0.050, 0.150, 0.095));
			img1 = getPicFeuerwerk(52);
			mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , 0.580, 0.100, 0.200, 0.3));
		} /* end if (bAddMoreFirework) */
		if ((m_nOpt & 4) == 0) {
			writeYear(1.2f, 1.2f, 1.4f, 10);
		}
		if (bHintsForNewYear) {
			SourceImage imgFabianVerbrenntPapier = new SourceImage("http://www.3roeders.de/Signal/signal-2021-09-05-185912.jpg");
			int nCols = imgFabianVerbrenntPapier.isOk() ? 1 : 0;
			// TGIF 2022 - week51
			SourceImage imgXMasMenPutInGarbage = new SourceImage("https://blogger.googleusercontent.com/img/a/AVvXsEi1SSLp0KrgQCKk67f9ns9r_D9Gfnn2BC6mSHMX8ZO9U-9QmboN6AIOJSFcQhRRnfll9XSHYuy_mDxzQM8LM9KBr3NAE__K8W4UdT3J9tVbzQ-pQgxfIIzbmGz3vSinLaN-caSFJcGK0Q5Jlz_kx1itQW_B5cm28JzsgV9jVBxGKHs_LZvvG3_cl_js=s420");
			if (imgXMasMenPutInGarbage.isOk()) {
				++nCols;
			}
			// https://debeste.de/bilder/neue-jahr/4
			SourceImage imgImportantSpouseAtEndOfYear = new SourceImage("https://debeste.de/upload/662fe51acca66d5f28545507aac5726b4778.jpg");
			if (imgImportantSpouseAtEndOfYear.isOk()) {
				++nCols;
			}
			if (nCols < 5) {
				++nCols; // Space for text: Ich brauche keine neuen Vorsätze ...
			}
			boolean bWriteToDoList;
            bWriteToDoList = nCols < 5;
            if (bWriteToDoList) {
				++nCols; // Space for ToDo Liste ...
			}
			final float wSteg = CalendarSheet.fWeight < 1 ? .005f : .003f;
			final float wCol1 = (1 - (nCols-1)*wSteg) / nCols;
			final Color colTxt = new Color(192,224,255);
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
				xC += wCol1 + wSteg;
			}
			if (imgXMasMenPutInGarbage.isOk()) {
				float wPicSrc = 0.200f*imgXMasMenPutInGarbage.getWidth()/imgXMasMenPutInGarbage.getHeight()*mSheet.getUsuableHeight();	
				float wText = wCol1/3f;
				float wPicTrg = (wCol1)*mSheet.getUsuableWidth();
				float wPic = Math.min(wPicSrc, wPicTrg);
				Color coltx1 = new Color(255, 192, 16);
				mSheet.drawImage(imgXMasMenPutInGarbage           , .6, 0.5, 0,  xC, 0.800, wPic , 0.200f);
				mSheet.drawText(" Alten ",   coltx1, Font.SERIF,      Font.PLAIN, xC,       0.830, wText, 0.025f);
				mSheet.drawText(" Kram",     coltx1, Font.SERIF,      Font.PLAIN, xC,       0.865, wText, 0.025f);
				mSheet.drawText("weg-",      coltx1, Font.SERIF,      Font.PLAIN, xC,       0.900, wText, 0.025f);
				mSheet.drawText("schmeißen", coltx1, Font.SERIF,      Font.PLAIN, xC,       0.935, wText, 0.025f);
				xC += wCol1 + wSteg;
			}
			if (bWriteToDoList) {
				mSheet.drawText("To-Do-Liste:",                        colTxt, Font.SERIF,      Font.PLAIN, xC, 0.810, wCol1, 0.030f);
				mSheet.drawText("\u2611 To-Do-Liste erstellen       ", colTxt, Font.MONOSPACED, Font.PLAIN, xC, 0.860, wCol1, 0.020f);
				mSheet.drawText("\u2611 1. Punkt abhaken            ", colTxt, Font.MONOSPACED, Font.PLAIN, xC, 0.890, wCol1, 0.020f);
				mSheet.drawText("\u2611 mich freuen über Erledigung ", colTxt, Font.MONOSPACED, Font.PLAIN, xC, 0.920, wCol1, 0.020f);
				mSheet.drawText("\u2610 nach getaner Arbeit: Pause  ", colTxt, Font.MONOSPACED, Font.PLAIN, xC, 0.950, wCol1, 0.020f);
				xC += wCol1 + wSteg;
			}
			if (imgImportantSpouseAtEndOfYear.isOk()) {
				float wPicSrc = 0.200f*imgImportantSpouseAtEndOfYear.getWidth()/imgImportantSpouseAtEndOfYear.getHeight()*mSheet.getUsuableHeight();
				float wPicTrg = wCol1*mSheet.getUsuableWidth();
				float wPic = Math.min(wPicSrc, wPicTrg);
				mSheet.drawImage(imgImportantSpouseAtEndOfYear, 0.5, 0.5, 0,  xC, 0.800, wPic , 0.200f);
				xC += wCol1 + wSteg;
			}
			float wText  = 1-xC;
			if (wText > 4*wSteg) {
				mSheet.drawText("Ich brauche keine neuen", colTxt, Font.SERIF, Font.PLAIN, xC, 0.830, wText, 0.025f);
				mSheet.drawText("Gute Vorsätze",           colTxt, Font.SERIF, Font.PLAIN, xC, 0.865, wText, 0.025f);
				mSheet.drawText("Die Alten sind noch",     colTxt, Font.SERIF, Font.PLAIN, xC, 0.920, wText, 0.025f);
				mSheet.drawText("praktisch unangetastet",  colTxt, Font.SERIF, Font.PLAIN, xC, 0.955, wText, 0.025f);
			}
		} /* end if (bHintsForNewYear) */
		mSheet.drawCalDates();
		mSheet.writeExJpg(StrOUT_DIR);
	} /* end of makeFeuerwerkBerlin() */

	@SuppressWarnings("ReassignedVariable")
	public void writeYear(float facWiderBorderDigits, float facHigherCenterDigits, float facOverlap, int nRepeatFireworks) {
		String strYear = Integer.toString(SHOW_YEAR);
		final int iFH = 11;
		BufferedImage imgText = new BufferedImage(iFH*4, iFH*2, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D painter1 = imgText.createGraphics();
		painter1.setColor(Color.WHITE);
		painter1.fillRect(0, 0, iFH*4, iFH*2);
		Font font1 = new Font(Font.SANS_SERIF, Font.PLAIN, iFH);
		painter1.setFont(font1);
		painter1.setColor(Color.BLACK);
		painter1.drawString(strYear, 1, iFH);
		painter1.dispose();
		MiRoesDraw.diagOut(imgText);
		int iTY = -1;
		int iTH = 0;
		int iY = 0;
		int iTX0 = iFH*5;
		int iTX9 = 0;
		do {
			boolean bFoundTxt;
			for (int iX = 0; iX < iFH*4; ++iX) {
				int pix = imgText.getRGB(iX, iY);
				bFoundTxt = (pix & 0xFFFFFF) < 0x101010;
				// On diagnosis: System.out.println("pix= "+ pix +" "+ (pix&0xFF0000) + (bFoundTxt ? " Text" : " Background"));
				if (bFoundTxt) {
					iTX0 = Math.min(iTX0, iX);
					iTX9 = Math.max(iTX9, iX);
					if (iTY < 0) {
						iTY = iY;
					}
					iTH = iY+1-iTY;
				}
			}
			++iY;
		} while (iY < iFH*2);
		int iTW = iTX9+1-iTX0;
		if (iTW > 0 && iTH > 0) {
			if (MiRoesDraw.bDoDiagOut) {
				Graphics2D painterD = imgText.createGraphics();
				painterD.setColor(Color.BLACK);
				for (int iD = 1; iD < 8; iD += 2) {
					painterD.drawRect(iTX0-iD, iTY-iD, iTW-1+2*iD, iTH-1+2*iD);
				}
				painterD.dispose();
				MiRoesDraw.diagOut(imgText);
			}
			final int iTotW = mSheet.getUsuableWidth()*4/5-mNewYearDigitsCenterSpace;
			final int iTotH = mSheet.getUsuableHeight()*2/5;
			final int iWPixs1FW = (int)( iTotW / ( Math.max(facWiderBorderDigits, 1f) * iTW));
			final int iHPixs1FW = (int)( iTotH / (Math.max(facHigherCenterDigits, 1f) * iTH));
			final int iWPixs1FWo = (int)(iWPixs1FW * facOverlap + .5f);
			final int iHPixs1FWo = (int)(iHPixs1FW * facOverlap + .5f);
			Random randomGen = new Random(THIS_YEAR * 12L + nMonth);
			int iTTX0 = (mSheet.getUsuableWidth()-iTotW)/2;
			int iTTY0 = 0;
			if (facOverlap > 1f) {
				iTTX0 -= iWPixs1FWo-iWPixs1FW;
				iTTY0 += iHPixs1FWo-iHPixs1FW;
			}
			final int iRoundRec = (int)(Math.min(iWPixs1FW, iHPixs1FW) * Math.max(facOverlap, 1f));
			iTTX0 += (int)((facWiderBorderDigits-1f)*iTotW/2f+.5f);
			for (int iSY = 0; iSY < iTH; ++iSY) {
				float facY = 1f - (float)iSY / (float) iTH;
				float facWiderDigit = facY * (facWiderBorderDigits-1f);
				int iTTX0y = iTTX0 - (int)(facWiderDigit*iTotW/2f+.5f);
				facWiderDigit += 1f;
				for (int iSX = 0; iSX < iTW; ++iSX) {
					int pix = imgText.getRGB(iSX+iTX0, iSY+iTY);
					boolean bFoundTxt = (pix & 0xFFFFFF)  < 0x101010;
					if (bFoundTxt) {
						float iFromCenterX = Math.abs(iTW/2-iSX);
						float facHigherDigit = (1f - iFromCenterX/(iTW/2f)) * (facHigherCenterDigits-1f) +1f;
						//On Diagnosis: System.out.println("iSX = "+ iSX +", iSY = "+ iSY +", iFromCenterX = "+ iFromCenterX +", facHigherDigit = " + facHigherDigit);
						int tx = (int)(iSX * iWPixs1FW * facWiderDigit +.5f) + iTTX0y + (iSX < iTW/2 ? -mNewYearDigitsCenterSpace : mNewYearDigitsCenterSpace);
						int ty = (int)(iHPixs1FW * (iSY * facHigherDigit + (facHigherCenterDigits-facHigherDigit) * iTH)) + iTTY0;
						BufferedImageSetPixImg_ABGR img1;
						for (int iF = 0; iF < nRepeatFireworks; ++iF) {
							int rF = nRepeatFireworks > 1 ? randomGen.nextInt(69) : randomGen.nextInt(5) +70;
							img1 = getPicFeuerwerk(rF);
							mSheet.drawImageA(new MakeDarkTransparentRoundRect(img1.getImage(0)    , 0.5, 0.5,   0 , tx, ty, iWPixs1FWo*facWiderDigit, iHPixs1FWo*facHigherDigit, iRoundRec));
						}
					}
				}
			}
		} else {
			System.err.println("Problem in MakeSheetFeuerwerke.writeYear: iTW = "+ iTW +", iTH = "+ iTH);
		} /* end if (iTW > 0 && iTH > 0) */
	}

	private BufferedImageSetPixImg_ABGR [] cacheFeuerwerke = null;

	@SuppressWarnings("ReassignedVariable")
	public BufferedImageSetPixImg_ABGR getPicFeuerwerk(int nr) {
		final String sD1 = "https://www.3roeders.de/Feuerwerke/2020_FrankfurtM/";
		if (cacheFeuerwerke == null) {
			cacheFeuerwerke = new BufferedImageSetPixImg_ABGR[picsFirework.length];
		}
		if (0 <= nr && nr < picsFirework.length) {
			BufferedImageSetPixImg_ABGR result = cacheFeuerwerke[nr];
			if (result == null) {
				result = mkPicFeuerwerk(sD1+picsFirework[nr].strFilename, picsFirework[nr].fac);
				cacheFeuerwerke[nr] = result;
			}
			return result;
		} else {
			System.err.println("MakeSheetFeuerwerke.getPicFeuerwerk("+ nr +") tries to access unknown firework picture.");
			return null;
		}
	}

	public BufferedImageSetPixImg_ABGR mkPicFeuerwerk(String strInImage, float fak) {
		try {
			BufferedImageSetPixImg_ABGR image = new BufferedImageSetPixImg_ABGR(strInImage);
			return mkPicFeuerwerk(image, fak);
		} catch (Exception ex) {
			MiRoeIoUtil.logException("Problem with picture \""+ strInImage +"\":", ex);
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
