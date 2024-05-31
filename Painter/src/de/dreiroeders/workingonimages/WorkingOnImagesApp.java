package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeIoUtil;


public class WorkingOnImagesApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File inFile, outFile, maskFile;
			maskFile= new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\res\\320px-Olympic_flag.svg.png");
			inFile  = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\320px-Olympic_flag.svg mask3.png");
			outFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\320px-Olympic_flag.svg mask4.png");
			BufferedImage maskImage = ImageIO.read(maskFile);
			BufferedImage mainImage = ImageIO.read(inFile);
			for (int sy = 0; sy < maskImage.getHeight(); ++sy) {
				for (int sx = 0; sx < maskImage.getWidth(); ++sx) {
					int maskRgb = maskImage.getRGB(sx, sy);
					if ((maskRgb & 0xF0F0F0) != 0xF0F0F0) {
						mainImage.setRGB(sx  ,sy-1,0);
						mainImage.setRGB(sx-1,sy,  0);
						mainImage.setRGB(sx,  sy,  0);
						mainImage.setRGB(sx+1,sy,  0);
						mainImage.setRGB(sx,  sy+1,0);
					}
				} /* end for (int sx...) */
				if (System.in.available() > 0) {
					do {
						System.in.read();
					} while (System.in.available() > 0);
					System.out.println(sy +" temp out: "+ MiRoesDraw.diagOut(mainImage));
				}
			} /* end for (int sy...) */

			ImageIO.write(mainImage, MiRoeIoUtil.getFileExt(outFile), outFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
