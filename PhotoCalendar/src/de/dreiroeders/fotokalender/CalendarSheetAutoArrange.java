package de.dreiroeders.fotokalender;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import de.dreiroeders.workingonimages.DefaultPicsPosesI;
import de.dreiroeders.workingonimages.Draw1ImageA;
import de.dreiroeders.workingonimages.Draw1ImageI;


public class CalendarSheetAutoArrange extends CalendarSheet implements Runnable {

	protected String mStrOUT_DIR;

	public CalendarSheetAutoArrange(int nYear, int month, PersonalDates mDates) {
		super(nYear, month, mDates);
	}

	public Thread startMakingIt(String strOutDir) throws IOException {
		mStrOUT_DIR = strOutDir;
		String strName = getClass().getName() +"_"+ THIS_YEAR +"-"+ nMonth;
		Thread thisThr = new Thread(this, strName);
		thisThr.start();
		return thisThr;
	}

	public void makingIt(String strOutDir) throws IOException {
		mStrOUT_DIR = strOutDir;
		run();
	}

	@Override
	public void run() {
		System.out.println("Begin of creating CalendarSheetAutoArrange for "+ THIS_YEAR +"-"+ (nMonth+1));
		try {
			makeIt();
		} catch (Throwable ex) {
			System.err.println("\nProblem when creating CalendarSheetAutoArrange for "+ THIS_YEAR +"-"+ (nMonth+1));
			ex.printStackTrace();
		}
	}

	public void makeIt() throws IOException {
		try {
			makePics();
		} catch (Throwable ex) {
			System.err.println("\nProblem when creating CalendarSheetAutoArrange for "+ THIS_YEAR +"-"+ (nMonth+1));
			ex.printStackTrace();
		}
		drawCalDates();
		writeExJpg(mStrOUT_DIR);
	}

	public void makePics() throws Exception {
		throw new UnsupportedOperationException("Missing pictures in CalendarSheetAutoArrange for "+ THIS_YEAR +"-"+ (nMonth+1));
	}


	public void autoArangeAndDraw(Rectangle trgRect, List<Draw1ImageI> pics, int iSteg) {
		if (pics != null && pics.size() > 0 && trgRect != null && trgRect.getWidth() > 3 && trgRect.getHeight() > 3) {
			setPicsCenterPoints(pics);
			DefaultPicsPosesI calcPos = new DefaultPicsPosesI();
			calcPos.mMainPaintReg = trgRect;
			calcPos.mPics = pics;
			calcPos.mfImgStegW = iSteg;
			calcPos.mfImgStegH = iSteg;
			calcPos.calcPicPoses();
			calcPos.diagOut(-getX(0), -getY(0));
			Iterator<Draw1ImageI> iter = pics.iterator();
			while (iter.hasNext()) {
				Draw1ImageI dr1Img = iter.next();
				drawImage(dr1Img);
			}
		}
	}
	
	public void setPicsCenterPoints(List<Draw1ImageI> pics) {
		Iterator<Draw1ImageI> iter = pics.iterator();
		while (iter.hasNext()) {
			Draw1ImageI dr1Img = iter.next();
			if (dr1Img.iCenterPoint == null && dr1Img instanceof Draw1ImageA) {
				((Draw1ImageA)dr1Img).setCenterPoint();
			}
			if (dr1Img.iCenterPoint == null) {
				dr1Img.iCenterPoint = new Point(dr1Img.getWidth()/2, dr1Img.getHeight()/2);
			}
		}
	}
		
}
