package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import de.dreiroeders.workingonimages.DefaultPicsPosesI;
import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.IHintsDrawImages;


public class CalendarSheetAutoArrange1 extends CalendarSheet implements Runnable, ICalendarSheetAddImage {

	public int mPreferedWidth;
	public int mPreferedHeight;
	public Color mBackgroundCol;
	
	private DefaultPicsPosesI mCalcPos;
	private String mStrOUT_DIR;
	private ArrayList<Draw1ImageI> mPics;
	
	public CalendarSheetAutoArrange1(int nYear, int month, PersonalDates mDates) {
		super(nYear, month, mDates);
		mPics = new ArrayList<Draw1ImageI>();
		mCalcPos = new DefaultPicsPosesI();
		mBackgroundCol = null;
	}

	public void addImage(Draw1ImageI img) {
		mPics.add(img);
	}
	
	public void addImage(Draw1ImageI img, IHintsDrawImages hints) {
		if (img.isOk()) {
			mPics.add(img);
		}
	}

	public void setPicRelWidth(float picRelWidth) {
		mCalcPos.mPicRelWidth = picRelWidth;
	}
	
	public Thread startMakingIt(String strOutDir) throws IOException {
		mStrOUT_DIR = strOutDir;
		String strName = "CalendarSheetAutoArrange"+THIS_YEAR +"-"+ nMonth;
		Thread thisThr = new Thread(this, strName);
		thisThr.start();
		//run();
		return thisThr;
	}
	
	public void makingIt(String strOutDir) throws IOException {
		mStrOUT_DIR = strOutDir;
		run();
	}
	

	@Override
	public void run() {
		System.out.println("Begin of creating CalendarSheetAutoArrange1 for "+ THIS_YEAR +"-"+ (nMonth+1));
		try {
			makeIt();
		} catch (Throwable ex) {
			System.err.println("\nProblem when creating CalendarSheetAutoArrange1 for "+ THIS_YEAR +"-"+ (nMonth+1));
			ex.printStackTrace();
		}
	}

	public void makeIt() throws IOException {
		setPicsCenterPoints();
		if (!mPics.isEmpty() && mPreferedWidth < 15 && mPreferedHeight < 15) try {
			setPreferedSize(0);
		} catch (Throwable ex) {
			System.err.println("\nProblem when creating CalendarSheetAutoArrange1 for "+ THIS_YEAR +"-"+ (nMonth+1));
			ex.printStackTrace();
		}
		if (mBackgroundCol == null) {
			mBackgroundCol = getDefaultBackgroundCol();
		}
		prepareImage(mPreferedWidth, mPreferedHeight, mBackgroundCol);
		if (mCalcPos.mPicRelWidth < 0.001) {
			mCalcPos.mPicRelWidth = 1f;
		}
		if (!mPics.isEmpty()) try {
			makeIt2();
		} catch (Throwable ex) {
			System.err.println("\nProblem when creating CalendarSheetAutoArrange1 for "+ THIS_YEAR +"-"+ (nMonth+1));
			ex.printStackTrace();
		}
		this.drawCalDates();
		writeInDir(mStrOUT_DIR);
	}

	public void setPicsCenterPoints() {
		Iterator<Draw1ImageI> iter = mPics.iterator();
		while (iter.hasNext()) {
			Draw1ImageI dr1Img = iter.next();
			if (dr1Img.iCenterPoint == null) {
				dr1Img.iCenterPoint = new Point(dr1Img.getWidth()/2, dr1Img.getHeight()/2);
			}
		}
	}
	
	public void setPreferedSize(int nIImg) {
		Draw1ImageI img1 = mPics.get(nIImg);
		int srcWid1;
		int srcHei1;
		float fRot = Math.abs(img1.dRot);
		if (fRot < 10) {
			fRot *= 180/Math.PI;
		}
		if (fRot < 30) {
			srcWid1 = img1.getWidth();
			srcHei1 = img1.getHeight();
		} else
		if (fRot < 60) {
			srcWid1 = img1.getWidth();
			srcHei1 = srcWid1;
		} else
		if (fRot < 120) {
			srcWid1 = img1.getHeight();
			srcHei1 = img1.getWidth();
		} else
		if (fRot < 150) {
			srcWid1 = img1.getWidth();
			srcHei1 = srcWid1;
		} else {
			srcWid1 = img1.getWidth();
			srcHei1 = img1.getHeight();
		}
		mCalcPos.mfImgStegW = srcWid1/100+1;
		mCalcPos.mfImgStegH = srcHei1/100+1;
		if (mPics.size() < 4) {
			mPreferedWidth  = srcWid1;
			mPreferedHeight = srcHei1;
		} else {
			mPreferedWidth  = 2*srcWid1+(int)mCalcPos.mfImgStegW;
			mPreferedHeight = 2*srcHei1+(int)mCalcPos.mfImgStegH;
		}
		if (mCalcPos.mPicRelWidth < 0.001f) {
			mCalcPos.mPicRelWidth = (float)srcWid1/srcHei1;
		}
	}
	
	private void makeIt2() throws IOException {
		mCalcPos.mPics = this.mPics;
		mCalcPos.mMainPaintReg = new Rectangle(getX(0), getY(0), getUsuableWidth(), getUsuableHeight());
		mCalcPos.calcPicPoses();
		mCalcPos.diagOut(-getX(0), -getY(0));
		Iterator<Draw1ImageI> iter = mPics.iterator();
		while (iter.hasNext()) {
			Draw1ImageI dr1Img = iter.next();
			drawImage(dr1Img);
		}
	}
	
	
}
