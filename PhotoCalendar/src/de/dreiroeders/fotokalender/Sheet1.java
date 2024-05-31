package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Sheet1 extends CalendarSheet {

	public Sheet1() {
		super(0, -1, null);
		fWeight = (float)Math.sqrt(2f);
	}

	@Override
	public void prepareImage(int usuableWidth0, int usuableHeight0, Color colBack) {
		int w2 = Math.max((int)(usuableWidth0), (int)(usuableHeight0*fWeight))+1;
		int h2 = (int)(w2*fWeight)+1;
		mX0 = w2/20;
		mY0 = h2/20;
		mX1 = 0;
		mX9 = mX0;
		mY9 = mY0;
		mX5 = w2;
		mY5 = h2;
		mOutImage = new BufferedImage(mX0+mX1+mX5+mX9, mY0+mY5+mY9, BufferedImage.TYPE_3BYTE_BGR);
		mPainter = mOutImage.createGraphics();
		mPainter.setColor(colBack);
		mColBack = colBack;
		mPainter.fillRect(0, 0, mX0+mX1+mX5+mX9, mY0+mY5+mY9);
	}

	@Override
	public Color getDefaultBackgroundCol() {
		return Color.WHITE;
	}

	
}
