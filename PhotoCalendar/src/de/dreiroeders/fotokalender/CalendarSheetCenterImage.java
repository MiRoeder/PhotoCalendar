package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import de.dreiroeders.workingonimages.Draw1ImageI;
import de.dreiroeders.workingonimages.EFillType;
import de.dreiroeders.workingonimages.IHintsDrawImages;


public class CalendarSheetCenterImage 
	extends CalendarSheetAutoArrange 
	implements ICalendarSheetAddImage {

	public Color mBackgroundCol;
	
	private Draw1ImageI centerImage;
	private ArrayList<Draw1ImageI>[] mPics;
	
	public CalendarSheetCenterImage(int nYear, int month, PersonalDates mDates) {
		super(nYear, month, mDates);
		mPics = new ArrayList[2];
		mPics[0] = new ArrayList<Draw1ImageI>();
		mPics[1] = new ArrayList<Draw1ImageI>();
		mBackgroundCol = null;
	}

	public void setImage1(Draw1ImageI img) {
		centerImage = img;
	}
	
	public void addImage1(Draw1ImageI img) {
		mPics[0].add(img);
	}
	
	public void addImage2(Draw1ImageI img) {
		mPics[1].add(img);
	}

	public void addImage(Draw1ImageI img, IHintsDrawImages hints) {
		int jI;
		if (mPics[0].size() > mPics[1].size()) {
			jI = 1;
		} else {
			jI = 0;
		}
		mPics[jI].add(img);
	}

	public void addImages(File inDir, FileFilter fileFilter) {
		File[] inFiles = inDir.listFiles(fileFilter);
		if (inFiles != null) {
			int jI;
			if (mPics[0].size() > mPics[1].size()) {
				jI = 2;
			} else {
				jI = 1;
			}
			for (File inFile : inFiles) {
				var img = new Draw1ImageI(inFile, EFillType.TransparentTarget);
				if (jI == 1) {
					addImage1(img);
					jI = 2;
				} else {
					addImage2(img);
					jI = 1;
				}
			}
		}
	}
	
	public void makePics() throws IOException {
		setPicsCenterPoints();
		int srcWid1;
		int srcHei1;
		float fRot = Math.abs(centerImage.dRot);
		if (fRot < 10) {
			fRot *= 180/Math.PI;
		}
		if (fRot < 30) {
			srcWid1 = centerImage.getWidth();
			srcHei1 = centerImage.getHeight();
		} else
		if (fRot < 60) {
			srcWid1 = centerImage.getWidth();
			srcHei1 = srcWid1;
		} else
		if (fRot < 120) {
			srcWid1 = centerImage.getHeight();
			srcHei1 = centerImage.getWidth();
		} else
		if (fRot < 150) {
			srcWid1 = centerImage.getWidth();
			srcHei1 = srcWid1;
		} else {
			srcWid1 = centerImage.getWidth();
			srcHei1 = centerImage.getHeight();
		}
		if (mBackgroundCol == null) {
			mBackgroundCol = getDefaultBackgroundCol();
		}
		prepareImage(srcWid1, srcHei1, mBackgroundCol);
		float fac = Math.min((float)getUsuableWidth()/srcWid1, (float)getUsuableHeight()/srcHei1);
		int iSteg = (Math.max(getUsuableWidth(), getUsuableHeight()) + 195) / 200;
		int iStegIntern;
		if (mPics[0].size() >= 1 && mPics[0].get(0).getFillType() == EFillType.TransparentTarget) {
			iStegIntern = 0;
		} else {
			iStegIntern = iSteg;
		}
		int iTCW = (int)(srcWid1*fac+.5f);
		int iTCH = (int)(srcHei1*fac+.5f);
		int iTCX1 = (getUsuableWidth() -iTCW)/2;
		int iTCY1 = (getUsuableHeight()-iTCH)/2;
		System.out.println("CalendarSheetCenterImage fac="+ fac +" X0="+ iTCX1 +" Y0="+ iTCY1);
		if (iTCX1 < 3*iSteg) {
			iTCX1 = 0;
		}
		if (iTCY1 < 3*iSteg) {
			iTCY1 = 0;
		}
		centerImage.iOutRect = new Rectangle(iTCX1+getX1(), iTCY1+getY1(), iTCW, iTCH);
		drawImage(centerImage);
		ArrayList<Rectangle> sndRects = new ArrayList<Rectangle>(2);
		if (iTCX1 >= 3*iSteg) {
			Rectangle rect = new Rectangle(getX1(), getY1(), iTCX1-iSteg, getUsuableHeight());
			sndRects.add(rect);
		}
		if (iTCY1 >= 3*iSteg) {
			Rectangle rect = new Rectangle(getX1(), getY1(), getUsuableWidth(), iTCY1-iSteg);
			sndRects.add(rect);
		}
		int iX8 = getUsuableWidth()-iTCX1-iTCW;
		if (iX8 >= 3*iSteg) {
			Rectangle rect = new Rectangle(getX1()+iTCX1+iTCW+iSteg, getY1(), iX8-iSteg, getUsuableHeight());
			sndRects.add(rect);
		}
		int iY8 = getUsuableHeight()-iTCY1-iTCH;
		if (iY8 >= 3*iSteg) {
			Rectangle rect = new Rectangle(getX1(), getY1()+iTCY1+iTCH+iSteg, getUsuableWidth(), iY8-iSteg);
			sndRects.add(rect);
		}
		System.out.println("CalendarSheetCenterImage: Rahmenbilder verteilt auf "+ sndRects.size() + " Rahmen.");
		if (sndRects.size() == 1) {
			mPics[0].addAll(mPics[1]);
		}
		int iR = 0;
		Iterator<Rectangle> iter = sndRects.iterator();
		while (iter.hasNext()) {
			Rectangle rect = iter.next();
			autoArangeAndDraw(rect, mPics[iR++], iStegIntern);
		}
	}

	public void setPicsCenterPoints() {
		if (centerImage.iCenterPoint == null) {
			centerImage.setCenterPoint(0.5f, 0.5f);
		}
		setPicsCenterPoints(mPics[0]);
		setPicsCenterPoints(mPics[1]);
	}
	
}
