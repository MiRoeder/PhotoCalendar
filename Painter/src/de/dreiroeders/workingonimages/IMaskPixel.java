package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;;

public interface IMaskPixel {

	public boolean DoItOnThisPixel(BufferedImage img, int x, int y);
	
}
