package de.dreiroeders.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class HttpFile {

	public static void HttpToFile(String httpSource, File target, int diagLevel) throws Exception {
		URL sourceU = new URL(httpSource);
		URLConnection sourceC = sourceU.openConnection();
		InputStream sourceS = sourceC.getInputStream();
		FileOutputStream targetS = new FileOutputStream(target);
		if (diagLevel > 2) {
			System.out.print(httpSource +" --> "+ target.getAbsolutePath()+" ");
		}
		copy(sourceS, targetS, diagLevel);
		targetS.close();
		sourceS.close();
		if (diagLevel > 2) {
			System.out.println(" ok");
		}
	}
	
	
	public static void copy(InputStream src, OutputStream trg, int diagLevel) throws Exception {
		byte[] buf = new byte[4096];
		int iRead = 0;
		int iRedL = -12345;
		do {
			iRead = src.read(buf);
			if (diagLevel > 8) {
				if (iRead == iRedL) {
					System.out.print("*");
				} else {
					System.out.print("\n"+iRead);
					iRedL = iRead;
				}
			}
			if (iRead > 0) {
				trg.write(buf, 0, iRead);
			}
		} while (iRead >= 0);
	}
}
