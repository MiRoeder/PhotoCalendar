package de.dreiroeders.fotokalender;

import java.awt.Color;


public class FotoBlatt1 extends FotoKalender1 {

	public FotoBlatt1(int year) {
		super(year);
	}

	public static void main(String[] args) {
		CalendarSheet.sBackBackgroundCol = Color.LIGHT_GRAY;
		CalendarSheet.fWeight = 1.715f;
		String strOutDir = "results/flickr.com/";
		FotoBlatt1 mainObj = new FotoBlatt1(0);
		mainObj.makeRiversHorseShoe(-1, strOutDir);
	}

}
