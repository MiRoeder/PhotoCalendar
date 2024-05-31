package de.dreiroeders.workingonimages;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;


public class DefaultPicsPosesI extends CalcDefaultPicPos {

	public static void calcPicPoses(Rectangle trgRect, List<Draw1ImageI> pics) {
		DefaultPicsPosesI executer = new DefaultPicsPosesI();
		executer.mMainPaintReg = trgRect;
		executer.mPics = pics;
		executer.calcPicPoses();
	}

	public List<Draw1ImageI> mPics;

	public DefaultPicsPosesI() {
	}

	public void calcPicPoses() {
		mNoPics = mPics.size();
		if (mPicRelWidth < 1E-6) {
			Draw1ImageI img1 = mPics.get(0);
			mPicRelWidth = img1.getPicRelTrgWidth();
		}
		setIter0();
		Iterator<Draw1ImageI> iter = mPics.iterator();
		while (hasNextLine() && iter.hasNext()) {
			nextLine();
			while (hasNextCol() && iter.hasNext()) {
				Draw1ImageI dr1Img = iter.next();
				Rectangle curRect = new Rectangle();
				nextPic(curRect);
				if (dr1Img.iOutRect == null) {
					dr1Img.iOutRect = curRect;
				}
			}
		}
		//System.out.println("DefaultPicsPosesI.calcPicPoses " + iter.hasNext() +" "+ hasNextLine() +" "+ hasNextCol());
	}
	
	public void diagOut(int dx, int dy) {
		Iterator<Draw1ImageI> iter = mPics.iterator();
		while (iter.hasNext()) {
			Draw1ImageI dr1Img = iter.next();
			String str1Img = "sheet.drawImage(" + dr1Img.toString(dx, dy) + ");";
			str1Img.replace("\\", "\\\\");
			System.out.println(str1Img);
		}
	}

}
