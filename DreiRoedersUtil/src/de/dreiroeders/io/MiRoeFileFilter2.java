package de.dreiroeders.io;

import java.io.File;

public class MiRoeFileFilter2 extends MiRoeFileExtFilter {

	private String m_strNameBeg;
	
	public MiRoeFileFilter2(String strFileNameBegin, String strFileExt) {
		super(strFileExt);
		m_strNameBeg = strFileNameBegin;
	}

	@Override
	public boolean accept(File fileName) {
		String strCurExt = MiRoeIoUtil.getFileExt(fileName);
		String fnNam = fileName.getName();
		boolean bRetVal = m_strNameBeg == null
				       || (   fnNam.length() >= m_strNameBeg.length()
				           && fnNam.substring(0, m_strNameBeg.length()).equalsIgnoreCase(m_strNameBeg) );
		if (bRetVal && mExts != null && strCurExt != null) {
			bRetVal = false;
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
