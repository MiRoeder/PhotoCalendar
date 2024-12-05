package de.dreiroeders.workingonimages;

import java.awt.geom.Rectangle2D;

public class CalcDefaultPicPos {

	public Rectangle2D mMainPaintReg;
	
	public float mPicRelWidth;
	
	public int mNoPics;
	
	public int mNoLines;
		
	public int mfImgStegW = 1;
    public int mfImgStegH = 1;

    public float mfHeightOf1Line;
    public float mfWidthOf1Pic;
    
    public int mIPic;
    public int mIRow;
    public int mICol;
    public int mNCols;
    
    public float mfDNCols;
    
	public CalcDefaultPicPos() {
		mPicRelWidth = 0f;
		mfDNCols = 0.49f;
	}

	public void setStegWH(int fImgStegW) {
		mfImgStegW = fImgStegW;
		float fac;
		if (mMainPaintReg != null) {
			fac = (float)mMainPaintReg.getHeight() / (float)mMainPaintReg.getWidth();
		} else {
			fac = 1f;
		}
		mfImgStegH = (int)(fImgStegW * fac + .5f);
	}
	
	public void setIter0() {
		calcNoLines();
		mIPic = 0;
		mIRow = -1;
		mICol = 0;
		calcNoCols();
	}
	
	public void calcNoLines() {
		if (mPicRelWidth < 0.001f) {
			mPicRelWidth = 1f;
		}
		float relW = mPicRelWidth * (float)mMainPaintReg.getHeight() / (float)mMainPaintReg.getWidth();
		float fNoLindes = Math.min(mNoPics, (float)Math.sqrt(mNoPics*relW));
		float fNoCols = mNoPics/fNoLindes;
		mNoLines = Math.max(1, (int)(fNoLindes+0.999f-fNoCols+((int)fNoCols)));
		System.out.println("Recommended number of lines for "+ mNoPics +" pics. Prefered target width per pic: "+ mPicRelWidth
				          +" target size ["+ mMainPaintReg.getWidth() +"x"+ mMainPaintReg.getHeight() +"] ["
				          + fNoLindes +"x"+ fNoCols +"] --> "+ mNoLines );
		calcHeightOf1Line();
	}
	
	public void calcHeightOf1Line() {
		if (mMainPaintReg != null) {
			double totalHigh = mMainPaintReg.getHeight();
			int nLines = Math.max(1, mNoLines);
			double dHeightOf1Line = (totalHigh - (nLines-1) * mfImgStegH) / nLines;
			mfHeightOf1Line = (float)dHeightOf1Line;
		} else {
			mfHeightOf1Line = 0f;
		}
	}
	
	public void calcNoCols() {
		mNCols = mNoPics - mIPic;
		int nRestLines = mNoLines - mIRow;
		if (nRestLines > 1) {
			mNCols = (int)((float)mNCols / nRestLines + mfDNCols);
		}
		calcWidthOf1Line();
	}
	
	public void calcWidthOf1Line() {
		if (mMainPaintReg != null) {
			double totalWidth = mMainPaintReg.getWidth();
			int nCols = Math.max(1, mNCols);
			double dWidthOf1Line = (totalWidth - (nCols-1) * mfImgStegW) / nCols;
			mfWidthOf1Pic = (float)dWidthOf1Line;
		} else {
			mfWidthOf1Pic = 0f;
		}
	}
	
	public boolean hasNextCol() {
		return mICol < mNCols;
	}
	
	public boolean hasNextLine() {
		return mIRow+1 < mNoLines;
	}

	public void nextLine() {
		mICol = 0;
		++mIRow;
		calcNoCols();
	}

	public void nextPic(/*out:*/ Rectangle2D targetRect) {
		if (targetRect != null) {
			targetRect.setRect(
					mMainPaintReg.getX() + (mfWidthOf1Pic  +mfImgStegW)*mICol,
					mMainPaintReg.getY() + (mfHeightOf1Line+mfImgStegH)*mIRow,
					mfWidthOf1Pic,
					mfHeightOf1Line );
		
		}
		++mIPic;
		if (hasNextCol()) {
			++mICol;
		} else {
			nextLine();
		}
	}
	

}
