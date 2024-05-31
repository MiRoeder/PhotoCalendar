package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeIoUtil;

public class UnSharp {

	public static void main(String[] args) {
		try {
			File inFile, outFile;
			inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\320px-Olympic_flag.svg mask2.png");
			outFile =new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\320px-Olympic_flag.svg mask3.png");
			BufferedImage inImage = ImageIO.read(inFile);
			BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
					   BufferedImage.TYPE_3BYTE_BGR );
			unSharp(inImage, new MaskAllPixels(), 20, outImage);
			ImageIO.write(outImage, MiRoeIoUtil.getFileExt(outFile), outFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void unSharp(BufferedImage inImage, IMaskPixel mask, int maxDist, BufferedImage outImage) throws Exception {
		unSharp(inImage, 0, 0, inImage.getWidth(), inImage.getHeight(), mask, maxDist, outImage);
	}
	
	public static void unSharp(BufferedImage inImage, int x0, int y0, int width, int height, IMaskPixel mask, int maxDist, BufferedImage outImage) throws Exception {
		final int maxDistQuad = maxDist*maxDist;
		for (int sy = y0; sy < y0+height; ++sy) {
			for (int sx = x0; sx < x0+width; ++sx) {
				double[] col = {0d, 0d, 0d};
				double quot = 0d;
				for (int ty = Math.max(sy-maxDist, 0); ty < y0+height && ty <= sy+maxDist; ++ty) {
					for (int tx = Math.max(sx-maxDist, 0); tx < x0+width && tx <= sx+maxDist; ++tx) {
						int distQ = (sy-ty)*(sy-ty)+(sx-tx)*(sx-tx);
						if (distQ <= maxDistQuad && mask.DoItOnThisPixel(outImage, tx, ty)) {
							double quot1 = 1d/(distQ+1);
							int rgb1 = inImage.getRGB(tx, ty);
							for (int iC = 0; iC < 3; ++iC) {
								col[iC] += MiRoesDraw.getCi(rgb1, iC)*quot1;
							}
							quot += quot1;
							if (System.in.available() > 0) {
								do {
									System.in.read();
								} while (System.in.available() > 0);
								System.out.println(sx +","+sy +" -> "+tx+","+ty+" temp out: "+ MiRoesDraw.diagOut(outImage));
							}
						}
					} /* end for (int tx...) */
				} /* end for (int ty...) */
				try {
					int[] nCols = new int[3];
					for (int iC = 0; iC < 3; ++iC) {
						double c1t = col[iC]/quot;
						if (c1t < 0.0 || 255 < c1t) {
							System.out.println(sx +","+sy +" -> "+ c1t);
						}
						nCols[iC] = (int)(c1t+0.5);
					}
					int rgb2 = MiRoesDraw.makeRGB(nCols);
					outImage.setRGB(sx, sy, rgb2);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} /* end for (int sx...) */
			if (System.in.available() > 0) {
				do {
					System.in.read();
				} while (System.in.available() > 0);
				System.out.println(sy +" temp out: "+ MiRoesDraw.diagOut(outImage));
			}
		} /* end for (int sy...) */
	}

}
