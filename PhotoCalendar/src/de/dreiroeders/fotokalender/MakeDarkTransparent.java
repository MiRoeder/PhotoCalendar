package de.dreiroeders.fotokalender;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import de.dreiroeders.workingonimages.BufferedImageSetPixImg_ABGR;
import de.dreiroeders.workingonimages.Draw1ImageA;
import de.dreiroeders.workingonimages.MiRoesDraw;

public class MakeDarkTransparent extends Draw1ImageA {

	public static void mk1PixFeuerwerk(BufferedImageSetPixImg_ABGR outImgC, int iX, int iY, float fak0) {
		float fak = Math.max(0f, Math.min(fak0, 1f));
		float alpha = outImgC.getAlpha(iX, iY);
		float bright = outImgC.getBright(iX, iY, 0.25f, 0.25f, 0.2f) + 0.3f;
		bright = Math.max(bright, 0f);
		alpha *= (fak + (1f-fak) * bright);
		outImgC.setAlpha(iX, iY, alpha);
	}

	public MakeDarkTransparent(BufferedImage image, double centerPointX, double centerPointY, double rotator,
			double tx0, double ty0, double tWidth, double tHeight) {
		super(image, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
	}

	public MakeDarkTransparent(
			String srcFileName,
			double centerPointX,
			double centerPointY,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
		) {
		super(srcFileName);
		aCenterPoint = new Point2D.Double(centerPointX, centerPointY);
		dRot = (float)rotator;
		aOutRect = new Rectangle2D.Double(tx0, ty0, tWidth, tHeight);
	}

	public MakeDarkTransparent(
			String srcFileName,
			float centerPointX,
			float centerPointY,
			float rotator,
			float tx0, 
			float ty0,
			float tWidth,
			float tHeight
		) {
		super(srcFileName);
		aCenterPoint = new Point2D.Float(centerPointX, centerPointY);
		dRot = rotator;
		aOutRect = new Rectangle2D.Float(tx0, ty0, tWidth, tHeight);
	}

	@Override
	public int getImageType() {
		return BufferedImage.TYPE_4BYTE_ABGR;
	}

	@Override
	public void drawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand, Graphics2D painter,
			AffineTransform transform) {
		MiRoesDraw.diagOut(imgIntermediate);
		BufferedImageSetPixImg_ABGR setPixImg = new BufferedImageSetPixImg_ABGR(imgIntermediate, 1f);
		int iWid = imgIntermediate.getWidth() - 2*iXExtraRand;
		int iHei = imgIntermediate.getHeight()- 2*iYExtraRand;
		float fWid = (float)iWid / iHei;
		float xCent = imgIntermediate.getWidth() /2f;
		float yCent = imgIntermediate.getHeight()/2f;
		float mxWd = (xCent-iXExtraRand);
		float mxHg = (yCent-iYExtraRand)*fWid;
		float mxDf = mxWd*mxWd+mxHg*mxHg;
		for (int iY = iYExtraRand; iY < iHei+iYExtraRand; ++iY) {
			float yQDif = (iY-yCent)*fWid;
			yQDif = yQDif*yQDif;
			for (int iX = iXExtraRand; iX < iWid+iXExtraRand; ++iX) {
				float xQDif = (iX-xCent);
				xQDif = xQDif*xQDif;
				float fac = Math.max(0, 1-(xQDif+yQDif)/mxDf);
				mk1PixFeuerwerk(setPixImg, iX, iY, fac);
			}
		}
	}

}
