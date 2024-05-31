package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;


public class FindLine extends FindLine1 implements IMaskPixel {
 
	public static int MIN_LINE_LEN = 20;
	
	public FindLine(IMaskPixel maxBeLinePixel, BufferedImage maskImage) {
		this.mMaxBeLinePixel = maxBeLinePixel;
		this.mOutImage = maskImage;
		this.mX9 = maskImage.getWidth();
		this.mY9 = maskImage.getHeight();
	}
	
	@Override
	public boolean DoItOnThisPixel(BufferedImage img, int x, int y) {
		mInImage = img;
		mD1x = 1;
		mD1y = 1;
		boolean bResult;
		bResult = isLinePixel(x, y, MIN_LINE_LEN);
		if (!bResult) {
			mD1y = -1;
			bResult = isLinePixel(x, y, MIN_LINE_LEN);
		}
		if (!bResult) {
			mD1x = -1;
			bResult = isLinePixel(x, y, MIN_LINE_LEN);
		}
		if (!bResult) {
			mD1y = 1;
			bResult = isLinePixel(x, y, MIN_LINE_LEN);
		}
		if (!bResult) {
			mD1x = 1;
			mD1y = 0;
			bResult = isLinePixel(x, y, MIN_LINE_LEN);
		}
		if (!bResult) {
			mD1x = 0;
			mD1y = 1;
			bResult = isLinePixel(x, y, MIN_LINE_LEN);
		}
		return bResult;
	}

}
