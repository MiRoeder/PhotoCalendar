package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FindLine1 {

	public BufferedImage mInImage = null;
	public int mX0 = 0;
	public int mY0 = 0;
	public int mX9 = 0;
	public int mY9 = 0;
	public int mD1x = 0;
	public int mD1y = 0;
	public IMaskPixel mMaxBeLinePixel = null;
	public IMaskPixel mIsAlreadyLinePixel = new IsAlreadyLinePixel();
	public int mOutRgbIsLine = Color.BLACK.getRGB();
	public BufferedImage mOutImage = null;
	
	public boolean isLinePixel(int x, int y, int minLength) {
		if (mIsAlreadyLinePixel.DoItOnThisPixel(mOutImage, x, y)) {
			return true;
		} else {
			return isLinePixel(x, y, false, minLength);
		}
	}
	
	public boolean isLinePixel(int x, int y, boolean mayUse2ndDir, int minLength) {
		if (x >= mX0 && y >= mY0 && x < mX9 && y < mY9) {
			if (mMaxBeLinePixel.DoItOnThisPixel(mInImage, x, y)) {
				if (minLength <= 1) {
					return true;
				} else {
					boolean bRetVal = false;
					if (mD1x != 0) {
						bRetVal = isLinePixel(x+mD1x, y, true, minLength-1);
					}
					if (mD1y != 0 && !bRetVal) {
						bRetVal = isLinePixel(x, y+mD1y, true, minLength-1);
					}
					if (mayUse2ndDir && mD1x == 0 && !bRetVal) {
						bRetVal = isLinePixel(x, y-1, false, minLength-1) || isLinePixel(x, y+1, false, minLength-1);
					}
					if (mayUse2ndDir && mD1y == 0 && !bRetVal) {
						bRetVal = isLinePixel(x-1, y, false, minLength-1) || isLinePixel(x+1, y, false, minLength-1);
					}
					return bRetVal;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	} /* end isLinePixel(int x, int y, boolean mayUse2ndDir, int minLength) */

	public class IsAlreadyLinePixel implements IMaskPixel {

		@Override
		public boolean DoItOnThisPixel(BufferedImage img, int x, int y) {
			int rgb = img.getRGB(x, y);
			int nBr = 0;
			for (int iCol = 0; iCol < 3; ++iCol) {
				int nCol = MiRoesDraw.getCi(rgb, iCol);
				nBr += nCol < 127 ? 1 : 0;
			}
			return nBr >= 1;
		}
	} /* end class IsAlreadyLinePixel */

}
