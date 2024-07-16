package de.dreiroeders.net;

import java.io.File;


public class NetGet {

	public NetGet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HttpFile.HttpToFile(" http://www.w3.org/TR/html4/loose.dtd", new File("C:\\Users\\MiRoe\\Documents\\E\\docs\\tmp\\loose.dtd"), 10);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
