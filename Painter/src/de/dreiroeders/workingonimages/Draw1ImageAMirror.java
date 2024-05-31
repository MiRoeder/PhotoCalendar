package de.dreiroeders.workingonimages;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Draw1ImageAMirror extends Draw1ImageA {

	public float mFacX;
	public float mFacY;
	
	public Draw1ImageAMirror(String srcFileName) {
		super(srcFileName);
		init();
	}

	public Draw1ImageAMirror(BufferedImage image) {
		super(image);
		init();
	}

	public Draw1ImageAMirror(String srcFileName, double centerPointX, double centerPointY, double rotator, double tx0,
			double ty0, double tWidth, double tHeight) {
		super(srcFileName, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		init();
	}

	public Draw1ImageAMirror(BufferedImage image, double centerPointX, double centerPointY, double rotator, double tx0,
			double ty0, double tWidth, double tHeight) {
		super(image, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		init();
	}

	private void init() {
		mFacX = 1f;
		mFacY = 1f;
	}

	@Override
	public void predrawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand,
			Graphics2D painter, AffineTransform transform) {
		int trgW = imgIntermediate.getWidth() - 2*iXExtraRand;
		int trgH = imgIntermediate.getHeight()- 2*iYExtraRand;
		double m02 = transform.getTranslateX() + (1-mFacX)/2 *trgW;
		double m12 = transform.getTranslateY() + (1-mFacY)/2 *trgH;
		transform.setTransform(transform.getScaleX()*mFacX, transform.getShearY()*mFacY, transform.getShearX()*mFacX, transform.getScaleY()*mFacY, m02, m12);
	}
}
