package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import javax.imageio.ImageIO;

public class MachDurchsichtig {
	
	static final char DIGIT = '1';
	static final String STR_SRC_FILENAME = "C:/Users/MiRoe/Pictures/Feuerwerke/Digit_"+ DIGIT +".png";
	static final String STR_TRG_FMT_FILENAME = "C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\Digit"+ DIGIT +"_{0,number}_{0,number}.png";
	
	
	public static void main(String[] args) {
		try {
			SourceImage srcImg = new SourceImage(STR_SRC_FILENAME);
			for (float minToShow = 0.1f; minToShow < 1f; minToShow += 0.2f) {
				machDurch(srcImg, 0.1f, 1f, 1f, minToShow);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private BufferedImageSetPixImg_ABGR mImage;
	private float mRedFac, mGreenFac, mBlueFac;
	private float mMinToShow; // jeder Punkt heller als diese Grenze bleibt sichtbar
	private float mMaxToHide; // jeder Punkt dunkler als diese Grenze wird total durchsichtig
	
	static void machDurch(SourceImage src, float redFac, float greenFac, float blueFac, float minToShow) throws IOException {
		MachDurchsichtig excuter = new MachDurchsichtig();
		excuter.mImage = new BufferedImageSetPixImg_ABGR(src);
		excuter.mRedFac   = redFac;
		excuter.mGreenFac = greenFac;
		excuter.mBlueFac  = blueFac;
		excuter.mMinToShow= minToShow;
		excuter.mMaxToHide= Math.min(minToShow - 0.2f, 1f);
		excuter.exec();
	}
	
	private static final float[] dummyCol = new float[] {0.5f, 0.1f, 0.6f};
	
	private void exec() throws IOException {
		for (int ym = 0; ym < mImage.getHeight(); ++ym) {
			for (int xm = 1; xm < mImage.getWidth(); ++xm) {
				float fAlpha =  mImage.getAlpha(xm, ym);
				if (fAlpha > 0.001f) {
					float fBright = mImage.getBright(xm, ym, mRedFac, mGreenFac, mBlueFac);
					if (fBright < mMinToShow) {
						float newAlpha;
						if (fBright < mMaxToHide) {
							newAlpha = 0f;
							mImage.setPixel(xm, ym, 0f, dummyCol);
						} else {
							newAlpha = (fBright - mMaxToHide) / (mMinToShow - mMaxToHide);
						}
						mImage.setAlpha(xm, ym, newAlpha);
					}
				}
			}
		}
		String strOutFileName = MessageFormat.format(STR_TRG_FMT_FILENAME, 
											         Integer.valueOf((int)(mMinToShow*49+50.5f)), 
											         Integer.valueOf((int)(mRedFac*49+50.5f)) );
		mImage.write(strOutFileName);
		
		BufferedImage img2 = new BufferedImage(mImage.getWidth(), mImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D painter = img2.createGraphics();
		painter.setColor(Color.BLACK);
		painter.fillRect(0, 0, mImage.getWidth(), mImage.getHeight());
		painter.drawImage(mImage.getImage(0), 0, 0, new MiRoesImageObserver());
		painter.dispose();
		File out1 = new File(strOutFileName+"_BB.png");
		ImageIO.write(img2, "png", out1);
	}

}
