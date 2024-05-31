package de.dreiroeders.workingonimages;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class MiRoesImageObserver implements ImageObserver {

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		System.out.println("MiRoesImageObserver.imageUpdate(,,,,,) returns false");
		return false;
	}

}
