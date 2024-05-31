package de.dreiroeders.diag;

import java.util.*;
import java.io.*;

public class DiagFewRepeat {

	private HashMap<String, Integer> mMsgs = new HashMap<String, Integer>();
	private PrintStream mOut;
	
	public DiagFewRepeat(PrintStream out) {
		this.mOut = out;
	}

    public void OutIf11(String strMainMsg, Object[] paras) {
		Integer nAnz = mMsgs.get(strMainMsg);
		if (nAnz == null) {
			nAnz = Integer.valueOf(1);
		} else {
			nAnz = Integer.valueOf(nAnz.intValue()+1);
		}
		int n1s = Integer.bitCount(nAnz.intValue());
		if (n1s <= 2) {
			StringBuilder strDiag = new StringBuilder(strMainMsg.length() + paras.length*8 + 10);
			strDiag.append("[").append(nAnz.intValue()).append("] ").append(strMainMsg);
			for (Object para : paras) {
				strDiag.append(" ").append(String.valueOf(para));
			}
			mOut.println(strDiag.toString());
		}
		mMsgs.put(strMainMsg, nAnz);
	}
}
