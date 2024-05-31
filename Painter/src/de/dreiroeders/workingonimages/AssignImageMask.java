package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;



public class AssignImageMask {

	public AssignImageMask() {
	}

	public static void main(String[] args) {
		try {
			final String strPicName = "1344x1357";
			final String strPicExt = ".jpg";
			final String strMaskName = "1344x1357.mask";
			final String strMaskExt = ".png";
			final String strPicOut = "WA_1344x1357";
			final String strPath = "C:\\Users\\MiRoe\\Pictures\\2019 Sophie Viktoria\\WhatsApp\\WhatsApp Image 2019-10-28 at 17.58.29 (2)\\";
			BufferedImageSetPixImg_ABGR imgResult = new BufferedImageSetPixImg_ABGR(new SourceImage(strPath + strPicName + strPicExt));
			File inFile = new File(strPath + strMaskName + strMaskExt);
			BufferedImage maskImg = ImageIO.read(inFile);
			
			/* Special treatment for this picture: */
			for (int ym = 334; ym < 840; ++ym) {
				float fAlph = (float)(ym-333)/(840-333);
				for (int xm = 0; xm < maskImg.getWidth(); ++xm) {
					int maskPixel = maskImg.getRGB(xm, ym);
					if ((maskPixel&0xFF) > 0x80) {
						imgResult.setAlpha(xm, ym, fAlph);
					}
				}
			}

			for (int ym = 0; ym < 334; ++ym) {
				for (int xm = 0; xm < maskImg.getWidth(); ++xm) {
					int maskPixel = maskImg.getRGB(xm, ym);
					if ((maskPixel&0xFF) > 0x80) {
						imgResult.setAlpha(xm, ym, 0);
					}
				}
			}

			imgResult.write(strPath + strPicOut + ".png");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
