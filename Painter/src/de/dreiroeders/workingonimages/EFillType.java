package de.dreiroeders.workingonimages;

public enum EFillType {
	// The source image will be scaled to cover the target rectangle.
	// But what happens, when the proportion of the soucre and target rectangle does not fit?
	// Or when the source image is skewed?
	// Or when the main object in the source image is not in the center?
	// then ...
	
	CutSource, // The proportion of the source image will be kept.
	           // The source image will be scaled to cover the whole target rectangle.
			   // If it does not fit exactly, the margin of the source image will be cut.
	
	TransparentTarget, // The proportion of the source image will be kept.
	                   // The source image will be scaled to fit totally into the target rectangle.
	                   // If it does not fit exactly, some parts of target rectangle will be transparent.
}
