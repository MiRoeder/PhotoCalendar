package de.dreiroeders.workingonimages;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Draw1ImageT extends Draw1ImageA {

	public double mtdLeft;
	public double mtdTop;
	public double mtdRight;
	public double mtdBottom;
	
	public Draw1ImageT(String srcFileName) {
		super(srcFileName);
	}

	public Draw1ImageT(BufferedImage image) {
		super(image);
	}

	public Draw1ImageT(String srcFileName, 
					   double tx0, double ty0, double tWidth, double tHeight, // whole target rectangle including transparent parts of the source image
					   double tdLeft, double tdTop, double tdRight, double tdBottom ) // partly transparent parts of the image, coordinates relativ to the border of the target rectangle
	{
		super(srcFileName, .5f, .5f, 0, tx0, ty0, tWidth, tHeight);
		mtdLeft = tdLeft;
		mtdTop  = tdTop;
		mtdRight= tdRight;
		mtdBottom=tdBottom;
	}

	public Draw1ImageT(BufferedImage image,
	                   double tx0, double ty0, double tWidth, double tHeight, // whole target rectangle including transparent parts of the source image
	                   double tdLeft, double tdTop, double tdRight, double tdBottom ) // 
	{
		super(image, .5f, .5f, 0, tx0, ty0, tWidth, tHeight);
		mtdLeft = tdLeft;
		mtdTop  = tdTop;
		mtdRight= tdRight;
		mtdBottom=tdBottom;
	}

	@Override
	public int getImageType() {
		if (mtdLeft > 1E-6 || mtdTop > 1E-6 || mtdRight > 1E-6 || mtdBottom > 1E-6) {
			return BufferedImage.TYPE_4BYTE_ABGR;
		} else {
			return BufferedImage.TYPE_3BYTE_BGR;			
		}
	}

	@Override
	public void drawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand, Graphics2D painter,
			AffineTransform transform) {
		MiRoesDraw.diagOut(imgIntermediate);
		BufferedImageSetPixImg_ABGR setPixImg = new BufferedImageSetPixImg_ABGR(imgIntermediate, 1f);
		int iWid = imgIntermediate.getWidth() - 2*iXExtraRand;
		int iHei = imgIntermediate.getHeight()- 2*iYExtraRand;
		System.out.println("Draw1ImageT.drawOnIntermediate : "+ iWid +"-"+ iXExtraRand +", "+ iHei +"-"+ iYExtraRand);
		if (mtdLeft > 1E-6) {
			int itdLeft = mtdLeft < 1 ? (int)(mtdLeft*iWid+.5f) : (int)(mtdLeft+.5f);
			for (int iX = 0; iX < itdLeft; ++iX) {
				float fak = (float)iX / itdLeft;
				for (int iY = 0; iY < iHei; ++iY) {
					mk1Pix(setPixImg, iX+iXExtraRand, iY+iYExtraRand, fak);
				}
			}
		}
		if (mtdTop > 1E-6) {
			int itdTop = mtdTop < 1 ? (int)(mtdTop*iHei+.5f) : (int)(mtdTop+.5f);
			for (int iY = 0; iY < itdTop; ++iY) {
				float fak = (float)iY / itdTop;
				for (int iX = 0; iX < iWid; ++iX) {
					mk1Pix(setPixImg, iX+iXExtraRand, iY+iYExtraRand, fak);
				}
			}
		}
		if (mtdRight > 1E-6) {
			int itdRight = mtdRight< 1 ? (int)(mtdRight*iWid+.5f) : (int)(mtdRight+.5f);
			for (int iX = iWid-1; iX >= iWid-itdRight; --iX) {
				float fak = (float)(iWid-1-iX) / itdRight;
				for (int iY = 0; iY < iHei; ++iY) {
					mk1Pix(setPixImg, iX+iXExtraRand, iY+iYExtraRand, fak);
				}
			}
		}
		if (mtdBottom > 1E-6) {
			int itdBot = mtdBottom< 1 ? (int)(mtdBottom*iWid+.5f) : (int)(mtdBottom+.5f);
			for (int iY = iHei-1; iY >= iHei-itdBot; --iY) {
				float fak = (float)(iHei-1-iY) / itdBot;
				for (int iX = 0; iX < iWid; ++iX) {
					mk1Pix(setPixImg, iX+iXExtraRand, iY+iYExtraRand, fak);
				}
			}
		}
	}
	
	public static void mk1Pix(BufferedImageSetPixImg_ABGR outImgC, int iX, int iY, float fak) {
		float alpha = outImgC.getAlpha(iX, iY);
		alpha *= fak;
		outImgC.setAlpha(iX, iY, alpha);
	}



}
