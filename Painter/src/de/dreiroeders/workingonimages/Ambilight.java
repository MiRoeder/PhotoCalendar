package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Ambilight {

	public BufferedImage mOutImage;
	public BufferedImage mMaskImage;
	public Color mColBack = null;

	public double sum_importances = 0d;
	
	public void drawAmbiLightH(int x0, int x9, int y0, int y9, int defStructSize) {
		try {
			int dSS = defStructSize*2;
			int dx = x0 < x9 ? 1 : -1;
			AmbiLightPixel cPx = new AmbiLightPixel();
			int nColBack = mColBack.getRGB();
			for (int iX = x0; iX*dx < x9*dx; iX += dx) {
				int maxDY = Math.min(defStructSize, Math.abs(x0-iX)+1);
				for (int iY = y0; iY < y9; ++iY) {
					cPx.reset();
					float impSum = 0f;
					for (int sy = -maxDY+(int)(Math.random()*maxDY/2); sy <= maxDY; sy+=((int)(Math.random()*maxDY/2)+1)) {
						int ys = iY+sy;
						if (y0 <= ys && ys < y9) {
							for (int sx = 1; sx < dSS; sx+=((int)(Math.random()*Math.min(defStructSize, sx))+1)) {
								float imp = getImpFactor(sx, sy, defStructSize);
								cPx.addPixel(iX-dx*sx, ys, imp);
								impSum += imp;
							}						
						}
					}
					cPx.addRGB(nColBack, Math.abs(impSum/((x9-iX)+2)));
					mOutImage.setRGB(iX, iY, cPx.getRGB());
				} /* end for (int iY = ...) */
				System.out.println(iX);
				if (System.in.available() > 0) {
					do {
						System.in.read();
					} while (System.in.available() > 0);
					MiRoesDraw.diagOut(this.mOutImage);
				}
			} /* end for (int iX = ...) */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} /* end drawAmbiLightH(int x0, int x9, int y0, int y9, int defStructSize) */

	/* cache: */
	private float[] impsFactors = null;
	
	private float getImpFactor(int sx, int sy, int defStructSize) {
		if (impsFactors == null || impsFactors.length != defStructSize*defStructSize*4) {
			impsFactors = new float[defStructSize*defStructSize*4];
		}
		int quad=sx*sx+sy*sy;
		if (quad < defStructSize*defStructSize*3) {
			float retVal = impsFactors[quad];
			if (Float.isNaN(retVal) || retVal == 0f) {
				double dist = Math.sqrt((double)quad) / (double)(defStructSize);
				double distR = (dist) * (Math.PI/2);
				retVal = (float)(Math.cos(distR) * (2d-dist) / (dist+1));
				impsFactors[quad] = retVal;
				sum_importances += retVal;
				System.out.println("sx = "+ sx + "; sy = "+ sy + " -> imp = "+ retVal +"; sum = "+ sum_importances);
			}
			return retVal;
		} else /* !(quad < defStructSize*defStructSize*9) */ {
			return 0f;
		}
	}
	
	private class AmbiLightPixel {
		float mC1;
		float mC2;
		float mC3;
		float mImp;
		
		public AmbiLightPixel() {
			reset();
		}
		
		public void reset() {
			mC1 = 0f;
			mC2 = 0f;
			mC3 = 0f;
			mImp= 0f;
		}

		public void addPixel(int iX, int iY, float imp) {
			try {
				int rgb = mOutImage.getRGB(iX, iY);
				addRGB(rgb, imp);
			} catch (ArrayIndexOutOfBoundsException ex) {
				/* does not matter */
			}
		}

		public void addRGB(int rgb, float imp) {
			int c11 = rgb & 0xFF;
			int c12 = (rgb >> 8) & 0xFF;
			int c13 = (rgb >> 16) & 0xFF;
			mC1 += c11*imp;
			mC2 += c12*imp;
			mC3 += c13*imp;
			mImp += imp;
		}
		
		public int getRGB() {
			try {
				int c1 = get1Col(mC1/mImp);
				int c2 = get1Col(mC2/mImp);
				int c3 = get1Col(mC3/mImp);
				return (c1 | (c2<<8) | (c3<<16));
			} catch (Exception ex) {
				ex.printStackTrace();
				return 0x808080;
			}
		}
		
		public /*static*/ int get1Col(float col) {
			return (int)Math.round(Math.max(0, Math.min(col, 255)));
		}
	} /* end class AmbiLightPixel */

}
