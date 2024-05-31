package de.dreiroeders.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;


public class MrBufferedReader extends LineNumberReader {

	public static MrBufferedReader createInstance(String strFileName) throws FileNotFoundException {
		FileInputStream fileInStr = null;
		InputStreamReader inStrRead = null;
		fileInStr = new FileInputStream(strFileName);
		inStrRead = new InputStreamReader(fileInStr);
		MrBufferedReader bufReader = new MrBufferedReader(fileInStr, inStrRead);
		return bufReader;
	}

	public static MrBufferedReader createInstance(File fileName) throws FileNotFoundException {
		FileInputStream fileInStr = null;
		InputStreamReader inStrRead = null;
		fileInStr = new FileInputStream(fileName);
		inStrRead = new InputStreamReader(fileInStr);
		MrBufferedReader bufReader = new MrBufferedReader(fileInStr, inStrRead);
		return bufReader;
	}

	private FileInputStream mFileInStr = null;
	private InputStreamReader mInStrRead = null;

	public MrBufferedReader(FileInputStream fileInStr, InputStreamReader inStrRead) {
		super(inStrRead);
		this.mFileInStr = fileInStr;
		this.mInStrRead = inStrRead;
	}

	public boolean isOpened() {
		return mInStrRead != null;
	}
	
	@Override
	public void close () {
		if (mInStrRead != null) {
			try {
				super.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				mInStrRead.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			mInStrRead = null;
		}
		if (mFileInStr != null) {
			try {
				mFileInStr.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			mFileInStr = null;
		}
	}
}
