package de.dreiroeders.fotokalender;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.*;

import de.dreiroeders.workingonimages.Draw1ImageA;
import de.dreiroeders.workingonimages.Draw1ImageI;


public class MakeOrigAndFake extends CalendarSheetAutoArrange {

	public Thread thisThread;
	
	public static MakeOrigAndFake startMaking(int nYear, int month, PersonalDates mDates, String strOutDir) {
		MakeOrigAndFake executer = new MakeOrigAndFake(nYear, month, mDates, strOutDir);
		String strName = "MakeOrigAndFake_"+ nYear +"-"+ (month+1);
		Thread thisThr = new Thread(executer, strName);
		thisThr.start();
		executer.thisThread = thisThr;
		return executer;
	}
	
	public MakeOrigAndFake(int nYear, int month, PersonalDates mDates, String strOutDir) {
		super(nYear, month, mDates);
		mStrOUT_DIR = strOutDir;
	}

	public void makePics() throws Exception {
		ArrayList<Draw1ImageI> pics = new ArrayList<Draw1ImageI>(2);
		Draw1ImageA imgO = new Draw1ImageA("C:\\Users\\MiRoe\\Pictures\\2015-Amerika\\DSC05044");
		Draw1ImageA imgF = new Draw1ImageA("C:\\Users\\MiRoe\\Pictures\\2015-Amerika\\DSC05044b");
		Draw1ImageA imgS = new Draw1ImageA("C:\\Users\\MiRoe\\Pictures\\2015-Amerika\\DSC04922");
		imgS.setCenterPoint(.5f, .3f);
		Draw1ImageA img3 = new Draw1ImageA("C:\\Users\\MiRoe\\Pictures\\2015-Amerika\\DSC04936");
		img3.setCenterPoint(.5f, .45f);
		pics.add(imgS);
		pics.add(img3);
		prepareImage(imgO.getWidth());
		int nTHOrig = getUsuableWidth()*imgO.getHeight()/imgO.getWidth();
		int nTHFake = getUsuableWidth()*imgF.getHeight()/imgF.getWidth();
		int nTHText = (int)(getUsuableHeight()*0.03f+.9);
		int nTHSteg = nTHText/6;
		int nTHTotl = nTHOrig + nTHFake + 2*(nTHText+3*nTHSteg+3);
		int nTYTxt1 = getUsuableHeight() - nTHTotl;
		if (nTYTxt1 < 0) {
			double fac = getUsuableHeight()/nTHTotl;
			nTHOrig = (int)(nTHOrig*fac);
			nTHFake = (int)(nTHFake*fac);
			nTHText = (int)(nTHText*fac);
			nTHSteg = (int)(nTHSteg*fac);
		}
		if (nTYTxt1 > 2*nTHText ) {
			Rectangle trgRect = new Rectangle(getX1(), getY1(), getUsuableWidth(), nTYTxt1-nTHSteg);
			autoArangeAndDraw(trgRect, pics, 2*nTHSteg+1);
		}
		drawText("Original:", getDefaultTextCol(), "Serif", Font.ITALIC,  0.00,  nTYTxt1+2*nTHSteg+1, 1.00, nTHText);
		imgO.aOutRect = new Rectangle2D.Float(0f, nTYTxt1+3*nTHSteg+1+nTHText, 1f, nTHOrig);
		drawImageA(imgO);
		drawText("und Fälschung:", getDefaultTextCol(), "Serif", Font.ITALIC,  0.00, nTYTxt1+5*nTHSteg+2+nTHText+nTHOrig, 1.00, nTHText);
		imgF.aOutRect = new Rectangle2D.Float(0f, nTYTxt1+6*nTHSteg+2+2*nTHText+nTHOrig, 1f, nTHFake);
		drawImageA(imgF);
		
	}
}
