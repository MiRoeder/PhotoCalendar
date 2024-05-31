package de.dreiroeders.workingonimages;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class Draw1ImageI extends SourceImage {

	public Point         iCenterPoint;
	public float         dRot;
	public BufferedImage outputImage;
	public Rectangle     iOutRect;
	private EFillType    mFillType;
	
	public Draw1ImageI(BufferedImage image) {
		super(image);
		mFillType = EFillType.CutSource;
	}

	public Draw1ImageI(String srcFileName) {
		super(srcFileName);
		mFillType = EFillType.CutSource;
	}

	public Draw1ImageI(File srcFile, EFillType fillType) {
		super(srcFile);
		mFillType = fillType;
	}

	public int getImageType() {
		return getImage().getType();
	}
	
	public EFillType getFillType() {
		return mFillType;
	}

	public void setFillType(EFillType fillType) {
		this.mFillType = fillType;
	}

	public float getRotGrad() {
		if (-10 < dRot && dRot < 10) {
			return dRot * 180f / (float)Math.PI;
		} else {
			return dRot;
		}
	}
	
	public float getPicRelTrgWidth() {
		float fGrad = getRotGrad();
		if (-135 < fGrad && fGrad < -45 || 45 < fGrad && fGrad < 135) {
			return (float)getHeight() / (float)getWidth();
		} else {
			return (float)getWidth() / (float)getHeight();
		}
	}
	
	public void predrawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand, Graphics2D painter, AffineTransform transform) {
	}

	public void drawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand, Graphics2D painter, AffineTransform transform) {
	}

	public void setCenterPoint(float xC, float yC) {
		int xM = (int)(getWidth()* xC +.5f);
		int yM = (int)(getHeight()*yC +0.5f);
		iCenterPoint = new Point(xM, yM);
	}
	
	public void setCenterPoint(int xM, int yM) {
		iCenterPoint = new Point(xM, yM);
	}
	
	public void turnToFit() {
		boolean bSrcLandscape = this.getWidth()    > this.getHeight();
		boolean bTrgLandscape = iOutRect.getWidth()> iOutRect.getHeight();
		if (bSrcLandscape == bTrgLandscape) {
			dRot = 0;
		} else {
			dRot = 90;
		}
	}
	
	@Override
	public String toString() {
		return toString(0, 0);
	}
	

	public String toString(int dx0, int dy0) {
		StringBuilder res = new StringBuilder(80);
		res.append(super.toString());
		if (iCenterPoint == null) {
			res.append(",cx,cy,");
		} else {
			res.append(',');
			res.append(iCenterPoint.x);
			res.append(',');
			res.append(iCenterPoint.y);
			res.append(',');
		}
		res.append(dRot);
		if (iOutRect == null) {
			res.append(",x0,y0,width,height");
		} else {
			res.append(',');
			res.append(iOutRect.x + dx0);
			res.append(',');
			res.append(iOutRect.y + dy0);
			res.append(',');
			res.append(iOutRect.width);
			res.append(',');
			res.append(iOutRect.height);
		}
		if (outputImage == null) {
			res.append(" /* null */");
		} else {
			res.append("/* [");
			res.append(outputImage.getWidth());
			res.append("x");
			res.append(outputImage.getHeight());
			res.append("] */");
		}

		return res.toString();
	}

}
