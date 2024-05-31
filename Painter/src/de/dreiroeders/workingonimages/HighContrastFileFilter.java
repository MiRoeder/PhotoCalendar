package de.dreiroeders.workingonimages;

import java.io.File;

import de.dreiroeders.io.MiRoeFileExtFilter;

public class HighContrastFileFilter extends MiRoeFileExtFilter {

	public HighContrastFileFilter() {
		super("jpg");
	}

	@Override
	public boolean accept(File fileName) {
		if (super.accept(fileName)) {
			String str1 = fileName.getName();
			return !str1.endsWith(MiRoesDraw1.STR_EXT_RES1) && !str1.endsWith(MiRoesDraw1.STR_EXT_RES2);
		} else {
			return false;
		}
	}

}
