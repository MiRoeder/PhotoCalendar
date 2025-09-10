package de.dreiroeders.fotokalender;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import de.dreiroeders.workingonimages.BufferedImageSetPixImg_ABGR;
import de.dreiroeders.workingonimages.MiRoesDraw;

public class MakeDarkTransparentRoundRect extends MakeDarkTransparent {

	public int mSizeRound;
	
	public MakeDarkTransparentRoundRect(BufferedImage image, double centerPointX, double centerPointY, double rotator,
			double tx0, double ty0, double tWidth, double tHeight) {
		super(image, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		mSizeRound = 500;
	}

	public MakeDarkTransparentRoundRect(BufferedImage image, double centerPointX, double centerPointY, double rotator,
										double tx0, double ty0, double tWidth, double tHeight, int sizeRound) {
		super(image, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		mSizeRound = sizeRound;
	}

	public MakeDarkTransparentRoundRect(String srcFileName, double centerPointX, double centerPointY, double rotator,
			double tx0, double ty0, double tWidth, double tHeight) {
		super(srcFileName, centerPointX, centerPointY, rotator, tx0, ty0, tWidth, tHeight);
		mSizeRound = 500;
	}

	@Override
	public void drawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand, Graphics2D painter,
			AffineTransform transform) {
		MiRoesDraw.diagOut(imgIntermediate);
		BufferedImageSetPixImg_ABGR setPixImg = new BufferedImageSetPixImg_ABGR(imgIntermediate, 1f);
		int iWid = imgIntermediate.getWidth() - 2*iXExtraRand;
		int iHei = imgIntermediate.getHeight()- 2*iYExtraRand;
		System.out.println("MakeDarkTransparentRoundRect.drawOnIntermediate : "+ iWid +"-"+ iXExtraRand +", "+ iHei +"-"+ iYExtraRand);
		for (int iY = iYExtraRand; iY < iHei+iYExtraRand; ++iY) {
			int qYDif = 0;
			int dY9 = iHei-1-iY+iYExtraRand;
			if (iY < mSizeRound+iYExtraRand) {
				int nDif = mSizeRound+iYExtraRand-iY;
				qYDif = nDif*nDif;
			}
			if (dY9 < mSizeRound) {
				int nDif = mSizeRound-dY9;
				qYDif += nDif*nDif;
			}
			for (int iX = iXExtraRand; iX < iWid+iXExtraRand; ++iX) {
				int qDif = qYDif;
				int dX9 = iWid-1-iX+iXExtraRand;
				if (iX < mSizeRound+iXExtraRand) {
					int nDif = mSizeRound+iXExtraRand-iX;
					qDif += nDif*nDif;
				}
				if (dX9 < mSizeRound) {
					int nDif = mSizeRound-dX9;
					qDif += nDif*nDif;
				}
				float fac = Math.max(0, 1-(float)qDif/mSizeRound/mSizeRound);
				mk1PixFeuerwerk(setPixImg, iX, iY, fac);
				/* Diag: if (Integer.bitCount(iX+iY) <= 2) {
					System.out.println("MakeDarkTransparentRoundRect.drawOnIntermediate : "+ iX +","+ iY +": "+ qDif +", "+ fac);
				} end of Diag. */
			}
		}
	}

}
