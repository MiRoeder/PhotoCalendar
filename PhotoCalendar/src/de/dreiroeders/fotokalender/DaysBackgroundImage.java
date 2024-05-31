package de.dreiroeders.fotokalender;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import de.dreiroeders.workingonimages.MiRoesDraw;

public class DaysBackgroundImage extends DaysBackground1 {

	private  File  mBackgroundIco = null;
	private  float mAlpha;

	public DaysBackgroundImage(float highBackgroudIcos, float deltaY,
			File backgroundIco, float alpha) {
		super(highBackgroudIcos, deltaY);
		this.mBackgroundIco = backgroundIco;
		this.mAlpha = alpha;
	}

	public int draw(CalendarSheet sheet, GregorianCalendar cal, int iX, int iY) {
		int ix9 = iX;
		int y0 = -123456;
		Graphics2D backP = null;
		File maskFile = null;
		BufferedImage backImage = null;
		BufferedImage maskImage = null;
		try {
			sheet.disposePainter();
			String strFileName = mBackgroundIco.getName();
			int iExt = strFileName.lastIndexOf('.');
			if (iExt > 0) {
				maskFile = new File(mBackgroundIco.getParentFile(), strFileName.substring(0, iExt) +" mask.png");
				if (!maskFile.exists()) {
					maskFile = new File(mBackgroundIco.getParentFile(), strFileName.substring(0, iExt) +" mask"+ strFileName.substring(iExt));
				}
			}
			y0 = sheet.getYLine(iY + mDeltaY - mHighBackgroudIcos/2/sheet.getLinesPerDay() + .4f);
			BufferedImage inImage;
			inImage = ImageIO.read(mBackgroundIco);
			int srcH = inImage.getHeight();
			int srcW = inImage.getWidth();
			double wQuoti = ((double)srcW)/srcH;
			double exQuoti = wQuoti > 1.0 ? Math.sqrt(wQuoti) : 1.0;
			int desH = (int)(mHighBackgroudIcos * sheet.getLineHeight() / exQuoti);
			int desW = (int)(wQuoti * desH);
			y0 += ((int)(mHighBackgroudIcos * sheet.getLineHeight()) - desH)/2;
			ix9 = iX+desW;
			backImage = new BufferedImage(desW, desH, BufferedImage.TYPE_4BYTE_ABGR);
			backP = backImage.createGraphics();
			boolean bRes = backP.drawImage(inImage, 0, 0, desW, desH, 0, 0, srcW, srcH, null);
			backP.dispose();
			backP = null;
			maskImage = new BufferedImage(desW, desH, BufferedImage.TYPE_3BYTE_BGR);
			backP = maskImage.createGraphics();
			if (maskFile != null && maskFile.exists()) {
				BufferedImage maskInImage = ImageIO.read(maskFile);
				bRes = backP.drawImage(maskInImage, 0, 0, desW, desH, 0, 0, srcW, srcH, null);
				assert(bRes);
			} else {
				backP.setColor(Color.BLACK);
				backP.fillRect(0, 0, desW, desH);
			}
			backP.dispose();
			backP = null;
			MiRoesDraw.diagOut(backImage);
			MiRoesDraw.diagOut(maskImage);
			sheet.drawMasked(backImage, maskImage, mAlpha, iX, y0);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Problems: "+ MiRoesDraw.diagSize(backImage) +" \""+ String.valueOf(maskFile) +"\":"+ MiRoesDraw.diagSize(maskImage) +" "+ iX +" "+ y0);
		}
		if (backP != null) {
			backP.dispose();
			backP = null;
		}
		sheet.getPainter(); /* to mOutImage.createGraphics(); */
		return ix9;
	}

}
