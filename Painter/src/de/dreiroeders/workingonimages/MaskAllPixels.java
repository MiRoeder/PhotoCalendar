package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;

public class MaskAllPixels implements IMaskPixel {

	@Override
	public boolean DoItOnThisPixel(BufferedImage img, int x, int y) {
		return   img != null
			  && 0 <= x && x <  img.getWidth()
			  && 0 <= y && y < img.getHeight() ;
	}

}
