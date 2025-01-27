package de.dreiroeders.io;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.File;

public class MiRoeIoUtil {

	public static String getFileExt(File fileName) {
		if (fileName != null) {
			return getFileExt(fileName.getName());
		} else {
			return "";
		}
	}

	public static String getFileExt(String strFileName) {
		if (strFileName != null && strFileName.length() >= 2) {
			int iP = strFileName.lastIndexOf('.');
			if (0 <= iP && iP < strFileName.length()-1) {
				return strFileName.substring(iP+1);
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public static void logException(String msg, Throwable ex) {
		Logger.getGlobal().log(Level.WARNING, msg, ex);
	}
}
