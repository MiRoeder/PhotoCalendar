package de.dreiroeders.io;

import java.io.File;
import java.io.FileFilter;

public class MiRoeFileExtFilter implements FileFilter {

	String[] mExts = null;

	public MiRoeFileExtFilter(String strFileExt) {
		mExts = new String[1];
		mExts[0] = strFileExt;
	}

	public MiRoeFileExtFilter(String strFileExt1, String strFileExt2, String strFileExt3) {
		mExts = new String[3];
		mExts[0] = strFileExt1;
		mExts[1] = strFileExt2;
		mExts[2] = strFileExt3;
	}

	@Override
	public boolean accept(File fileName) {
		String strCurExt = MiRoeIoUtil.getFileExt(fileName);
		boolean bRetVal = false;
		if (mExts != null && strCurExt != null) {
			for (int iF = 0; iF < mExts.length && !bRetVal; ++iF) {
				bRetVal = strCurExt.equalsIgnoreCase(mExts[iF]);
			}
		}
		if (bRetVal) {
			System.out.println(fileName.getAbsolutePath());
		}
		return bRetVal;
	}

}
