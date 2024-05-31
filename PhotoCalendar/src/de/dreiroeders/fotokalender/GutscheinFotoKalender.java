package de.dreiroeders.fotokalender;

import java.io.IOException;
import java.text.MessageFormat;

import de.dreiroeders.workingonimages.Draw1ImageA;
import de.dreiroeders.workingonimages.Draw1ImageAMirror;


public class GutscheinFotoKalender {

	public static final String SRC_DIR = "C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\results\\2019\\A1.7\\";
	public static final float  fWSrc = 8321f/5240f;
	
	public static final String TRG_PIC = SRC_DIR + "Gutschein.jpg";
	public static final String SRC_TOP_LEFT_BORDER = "res\\SANTA1.png";
	public static final int    WID_TOP_LEFT_BORDER = 448;
	public static final int    HEI_TOP_LEFT_BORDER = 635;
	
	Sheet1 mSheet;
	
	
	public GutscheinFotoKalender() {
		
	}

	public static void main(String[] args) {
		try {
			GutscheinFotoKalender mainObj = new GutscheinFotoKalender();
			mainObj.execute();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public void execute() throws IOException {
		mSheet = new Sheet1();
		mSheet.prepareImage(5000);
		try {
			paintPicsAndText();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		mSheet.writeJpg(TRG_PIC);
		System.out.println("===========================================================");
	}
	
	private void paintPicsAndText() {
		int nBorderHigh = mSheet.getUsuableWidth()*HEI_TOP_LEFT_BORDER/WID_TOP_LEFT_BORDER/2;
		Draw1ImageA img =              new Draw1ImageA(SRC_TOP_LEFT_BORDER, .5f, .5f, 0, 0,   0, .5f, nBorderHigh);
		mSheet.drawImageA(img);
		Draw1ImageAMirror img2 = new Draw1ImageAMirror(SRC_TOP_LEFT_BORDER, .5f, .5f, 0, 0.5, 0, .5f, nBorderHigh);
		img2.mFacX = -1f;
		mSheet.drawImageA(img2);
		mSheet.drawText("Gutschein", mSheet.getDefaultTextCol(), "Jokerman", 0, 0.2f, 0.15f, 0.6f, 0.15f);
		mSheet.drawText("für einen Kalender", mSheet.getDefaultTextCol(), "Jokerman", 0, 0.2f, 0.3f, 0.6f, 0.1f);
		int iTrgCalSW = mSheet.getUsuableWidth()/4; 
		int iTrgCalSH = (int)(iTrgCalSW/fWSrc);
		int y4 = mSheet.getUsuableHeight()-4*iTrgCalSH;
		img = new Draw1ImageA(
				SRC_DIR+"1812.jpg",
				0.5f, 0.5f, 0,
				1.5f*iTrgCalSW,
				y4,
				iTrgCalSW,
				iTrgCalSH );
		mSheet.drawImageA(img);

		int y8 = mSheet.getUsuableHeight()-iTrgCalSH;
		for (int iX = 0; iX < 4;++iX) {
			String strSrcImg = MessageFormat.format(SRC_DIR+"19{0,number,00}.jpg", iX+9);
			img = new Draw1ImageA(
					strSrcImg,
					0.5f, 0.5f, 0,
					iX*iTrgCalSW,
					y8,
					iTrgCalSW,
					iTrgCalSH );
			mSheet.drawImageA(img);
		}
		int y7 = y8-iTrgCalSH;
		for (int iX = 0; iX < 4;++iX) {
			String strSrcImg = MessageFormat.format(SRC_DIR+"19{0,number,00}.jpg", iX+5);
			img = new Draw1ImageA(
					strSrcImg,
					0.5f, 0.5f, 0,
					iX*iTrgCalSW,
					y7,
					iTrgCalSW,
					iTrgCalSH );
			mSheet.drawImageA(img);
		}
		int y6 = y7-iTrgCalSH;
		for (int iX = 0; iX < 4;++iX) {
			String strSrcImg = MessageFormat.format(SRC_DIR+"19{0,number,00}.jpg", iX+1);
			img = new Draw1ImageA(
					strSrcImg,
					0.5f, 0.5f, 0,
					iX*iTrgCalSW,
					y6,
					iTrgCalSW,
					iTrgCalSH );
			mSheet.drawImageA(img);
		}
	}
}
