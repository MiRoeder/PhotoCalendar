package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import de.dreiroeders.diag.Diag2;



public class MiRoesDraw1 {

	public static void main(String[] args) {
		try {
			showContrasts();
			//setContrastMaskAmerika2015DSC05149();
			//merge5149MaskPngs();
			//flatAndHigherContrast5149();
			//showSinglePixels5149();
			//mergeBlackAndWhite5149();
			//identifyHighContrast();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void showContrasts() throws IOException {
		final String strName = "DSC05149 mehr Konstrast m";
		final String strExt = ".png";
		File inFile = new File("E:\\Bilder\\2015-Amerika\\5149.tmp\\"+ strName + strExt);
		BuffrdImgGetPixelD biGP1 = new BuffrdImgGetPixelD(inFile);
		BuffrdImgSetPixelD biSPD = new BuffrdImgSetPixelD();
		BuffrdImgSetPixelD biSPB = new BuffrdImgSetPixelD();
		MiRoesDraw.showSinglePixels(biGP1, biSPD, biSPB, biGP1.getWidth(), biGP1.getHeight());
		biSPD.write(new File("E:\\Bilder\\2015-Amerika\\5149.tmp\\"+ strName +" Darks" + strExt));
		biSPB.write(new File("E:\\Bilder\\2015-Amerika\\5149.tmp\\"+ strName +" Brigh" + strExt));
		BufferedImageSetPixImg_ABGR biDif = new BufferedImageSetPixImg_ABGR(
				biGP1.getWidth(), biGP1.getHeight(), biSPB, +1f, biSPD, -1f );
		File filResult = new File("E:\\Bilder\\2015-Amerika\\5149.tmp\\"+ strName +" Diff1" + strExt);
		ImageIO.write(biDif.getImage(0), "png", filResult);
		System.out.println(filResult.getAbsolutePath());
		filResult = new File("E:\\Bilder\\2015-Amerika\\5149.tmp\\"+ strName +" Diff2" + strExt);
		ImageIO.write(biDif.getImage(1), "png", filResult);
	}
	
	public static void ignoreSomeInAmerika2015DSC05149() throws Exception {
		File filOrg = new File("E:\\Bilder\\2015-Amerika\\DSC05149.JPG");
		File filManuTransparent = new File("E:\\Bilder\\2015-Amerika\\DSC05149a.png");
		File filResult = new File("E:\\Bilder\\2015-Amerika\\DSC05149b.png");
		int nTotWidth =	3264;	
		int nTotHeight = 4912;
		int nLeftTopX =	278;
		int nLeftTopY =	662;
		int nLeftBotX =	249;
		int nLeftBotY =	4156;
		int nRightTopX = 3047;
		int nRightTopY = 721;
		int nRightBotX = 2886;
		int nRightBotY = 4911;

		BufferedImage inImageOrg = ImageIO.read(filOrg);
		BufferedImage image = ImageIO.read(filManuTransparent);
		int iTyp = image.getType();
		if (iTyp != BufferedImage.TYPE_4BYTE_ABGR) {
			System.err.println("Enexpected image type: "+ iTyp);
		} else {
			System.out.println("Expected image type: "+ iTyp +" ["+ inImageOrg.getWidth() +"x"+ inImageOrg.getHeight() +"] -> ["
															 + image.getWidth() +"x"+ image.getHeight() +"]");
		}

		for (int ty = nRightTopY; ty < nTotHeight; ++ty) {
			float fL = (float)(ty-nLeftTopY)/(nLeftBotY-nLeftTopY);
			int nBL = (int)(nLeftTopX+fL*(nLeftBotX-nLeftTopX));
			float fR = (float)(ty-nRightTopY)/(nRightBotY-nRightTopY);
			int nBR = (int)(nRightTopX+fR*(nRightBotX-nRightTopX));
			System.out.println(ty +": "+ nBL +", "+ nBR);
		    for (int tx = 0; tx < nTotWidth; ++tx) {
		    	boolean bMakTrans = tx > nBR || tx < nBL && ty < nLeftBotY;
		    	boolean bCopyOrg = !bMakTrans && (ty < nLeftBotY || tx > (nTotWidth>>1));
		    	if (bMakTrans) {
		    		image.setRGB(tx, ty, 0x00808080);
		    	} else 
		    	if (bCopyOrg) {
		    		int sx = ty;
		    		int sy = nTotWidth-1-tx;
		    		int nRGB = inImageOrg.getRGB(sx, sy) | 0xFF000000;
		    		image.setRGB(tx, ty, nRGB);
		    	}
		    } /* end for (int tx...) */
		} /* end for (int ty...) */
		ImageIO.write(image, "png", filResult);
	}

	public static void setContrastMaskAmerika2015DSC05149() throws Exception {
		int iColLimits[][] = new int[2][];
		iColLimits[0] = new int[]{ 96, 120, 160};
		iColLimits[1] = new int[]{ 96, 120, 160};
		float fColLimits[][] = new float[2][3];
		File filDir = new File("E:\\Bilder\\2015-Amerika\\5149.tmp");
		StringBuilder strResultFileName = new StringBuilder(filDir.getAbsolutePath()+"\\DSC05149M_");
		for (int iY = 0; iY < 2; ++iY) {
			for (int iCol = 0; iCol < 3; ++iCol) {
				fColLimits[iY][iCol] = (iColLimits[iY][iCol]-127.5f)/127.5f;
				String strCol = Integer.toHexString(iColLimits[iY][iCol]);
				if (strCol.length() < 2) {
					strResultFileName.append("0");
				}
				if (strCol.length() > 2) {
					strResultFileName.append("_");
				}
				strResultFileName.append(strCol);
			}
		}
		strResultFileName.append(".png");
		File filOrg = new File(filDir, "DSC05149 mehr Konstrast.png");
		File filResult = new File(strResultFileName.toString());

		BufferedImage inImageOrg = ImageIO.read(filOrg);
		int nTotWidth =	inImageOrg.getWidth();	
		int nTotHeight = inImageOrg.getHeight();
		BufferedImageGetPixImg inImgOrg = new BufferedImageGetPixImg(inImageOrg);
		BufferedImage image = new BufferedImage(nTotWidth, nTotHeight, BufferedImage.TYPE_4BYTE_ABGR);
		BuffrdImgSetPixelD imagePx = new BuffrdImgSetPixelD(image);

		float px[] = new float[3];
		for (int ty = 0; ty < nTotHeight; ++ty) {
			float fy = (float)ty/nTotHeight;
		    for (int tx = 0; tx < nTotWidth; ++tx) {
	    		px = inImgOrg.getPixel(tx, ty, px);
	    		for (int iC = 0; iC < 3; ++iC) {
	    			px[iC] = px[iC] >= (fColLimits[0][iC]*(1-fy) + fColLimits[1][iC]*fy) ? +1f :-1f;
	    		}
	    		imagePx.setPixel(tx, ty, px);
		    } /* end for (int tx...) */
		} /* end for (int ty...) */
		ImageIO.write(image, "png", filResult);
		System.out.println(filResult.getAbsolutePath());
	}

	public static void machSomeThingTransparent() throws Exception {
		//   inFile = new File("E:\\Bilder\\2015-Amerika\\DSC05468.jpg");
		File inFile = new File("C:\\Users\\MiRoe\\OneDrive\\Bilder\\DSC05468_Cal.jpg");
		File outFile = new File("E:\\Bilder\\2015-Amerika\\DSC05468_Calendar.png");
		final int SRC_WIDTH = 4912;
		final int SRC_HEIGH = 3264;
		final int IGN_HEIGH = 820;
		BufferedImage outImage;
		boolean phase1 = true;
		if (phase1) {
			BufferedImage inImage = ImageIO.read(inFile);
			outImage = new BufferedImage(SRC_WIDTH, SRC_HEIGH-IGN_HEIGH, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D painter2 = outImage.createGraphics();
			AffineTransform transf = new AffineTransform(-1, 0, 0, +1, SRC_WIDTH, -IGN_HEIGH);
			painter2.drawImage(inImage, transf, new MiRoesImageObserver());
			painter2.dispose();
		} else {
			outImage = ImageIO.read(outFile);
		}
		BuffrdImgSetPixelD outImg = new BuffrdImgSetPixelD(outImage);
		float newColor[] = new float[]{-1, +1, +1};
		int prevMY = 4;
		for (int iX = 2280; iX < SRC_WIDTH; ++iX) {
			int iY;
			boolean cont = true;
			float prevB = 0f;
			for (iY = 0; iY < 4; ++iY) {
				prevB += outImg.getBright(iX, iY, .5f, .4f, .1f);
			}
			prevB = prevB / 4;
			for (iY = 4; cont && iY < prevMY+4 && iY < SRC_HEIGH-IGN_HEIGH; ++iY) {
				float curB = outImg.getBright(iX, iY, .5f, .4f, .1f);
				prevB = (3*prevB + curB)/4;
				cont = iY < prevMY+1 && curB < -.5f && prevB < -.8f
				    || iY < prevMY+4 && curB < -.7f && prevB < -.9f
				    || outImg.getAlpha(iX, iY) < 0.05f ;
				if (cont) {
					outImg.setPixel(iX, iY-4, 0f, newColor);
				}
			}
			if (iY <= prevMY-3 || prevMY+3 <= iY) {
				float[] cols = outImg.getPixel(iX, iY, null);
				System.out.println("Steil DSC05468.jpg : x="+ iX +", y="+ iY +" cols="+ cols[2] +","+ cols[1] +","+ cols[0]);
			}
			prevMY = iY;
		}
		ImageIO.write(outImage, "png", outFile);
	}
	

	public static void comparePics(String strInfile1, String strInfile2, String strDiffFiles, String strDarkDiffFile, String strBrightDiffFile) {
		try {
			File inFile1 = new File(strInfile1);
			File inFile2 = new File(strInfile2);
			BufferedImage inImage1 = ImageIO.read(inFile1);
			BufferedImage inImage2 = ImageIO.read(inFile2);
			BufferedImageGetPixImg inImgG1 = new BufferedImageGetPixImg(inImage1);
			BufferedImageGetPixImg inImgG2 = new BufferedImageGetPixImg(inImage2);
			final int wi = Math.min(inImage1.getWidth() , inImage2.getWidth());
			final int hi = Math.min(inImage1.getHeight(), inImage2.getHeight());
			BufferedImage outImage1 = new BufferedImage(wi, hi, BufferedImage.TYPE_4BYTE_ABGR);
			BufferedImageSetPixImg_ABGR outImg = new BufferedImageSetPixImg_ABGR(outImage1, 2f);
			float[] col1 = new float[3];
			float[] col2 = new float[3];
			float[] colO = new float[3];
			for (int y = 0; y < hi; ++y) {
				for (int x = 0; x < wi; ++x) {
					col1 = inImgG1.getPixel(x, y, col1);
					col2 = inImgG2.getPixel(x, y, col2);
					for (int iC = 0; iC < 3; ++iC) {
						colO[iC] = col1[iC] - col2[iC];
					}
					outImg.setPixel(x, y, colO);
				}
			}
			outImg.write(strDiffFiles);
			MiRoesDraw.diagOut(outImage1);
			MiRoesDraw.diagOut(outImg.getImage(1));
			BuffrdImgGetPixelD biGP1 = new BuffrdImgGetPixelD(outImage1);
			BuffrdImgSetPixelD biSPD = new BuffrdImgSetPixelD();
			BuffrdImgSetPixelD biSPB = new BuffrdImgSetPixelD();
			MiRoesDraw.showSinglePixels(biGP1, biSPD, biSPB, biGP1.getWidth(), biGP1.getHeight());
			biSPD.write(new File(strDarkDiffFile));
			biSPB.write(new File(strBrightDiffFile));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void Olympic_flag_mask1() {
		try {
			File inFile = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\res\\320px-Olympic_flag.svg.png");
			final int width = 320;
			final int height = 213;
			final int leftMargin = 46;
			final int topMargin = 53;
			final int rightMargin = width - 273 - 1; /* == leftMargin */
			final int bottomMargin = height - 1 - 155;
			System.out.println("[ "+ leftMargin +" "+ topMargin +" "+ rightMargin +" "+ bottomMargin +"]");
			BufferedImage inImage = ImageIO.read(inFile);
			BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(),
													   BufferedImage.TYPE_BYTE_GRAY );
			Graphics2D mPainter = outImage.createGraphics();
			mPainter.setColor(Color.WHITE);
			mPainter.fillRect(0, 0, inImage.getWidth(), inImage.getHeight());
			
			int nMargin = Math.min(Math.min(leftMargin, topMargin), Math.min(rightMargin, bottomMargin));
			int wP = width-leftMargin-rightMargin+nMargin+nMargin;
			int hP = height-topMargin-bottomMargin+nMargin+nMargin;
			int dCol = 127/(nMargin+1);
			int nCol1 = 255;
			for (int nM1 = 0; nM1 <= nMargin; ++nM1) {
				assert(0 <= nCol1 && nCol1 <= 0xFF);
				mPainter.setColor(new Color(nCol1, nCol1, nCol1));
				if ((nM1 & 15) != 16) {
					mPainter.drawRoundRect(leftMargin-nMargin+nM1, topMargin-nMargin+nM1, wP-nM1-nM1, hP-nM1-nM1, 30, 30);
					MiRoesDraw.diagOut(outImage);
				}
				nCol1 -= dCol;
			}
			// int nM1 = nMargin;
			// mPainter.setColor(Color.GRAY);
			// mPainter.fillRect(leftMargin-nMargin+nM1, topMargin-nMargin+nM1, wP-nM1-nM1, hP-nM1-nM1);
			mPainter.dispose();
			mPainter = null;
			
			File outFile = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\320px-Olympic_flag.svg mask1.png");
			String writerNames[] = ImageIO.getWriterFormatNames();
			for (String writerName : writerNames) {
				System.out.println(writerName);
			}
			ImageIO.write(outImage, "png", outFile);
			System.out.println(outFile.getAbsolutePath());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void merge5149MaskPngs() throws Exception {
		File filDir = new File("E:\\Bilder\\2015-Amerika\\5149.tmp");
		File filIn1 = new File(filDir, "DSC05149M_686868383038.png");
		MiRoesImageObserver obs = new MiRoesImageObserver();
		BufferedImage imgIn = ImageIO.read(filIn1);
		BufferedImage image = new BufferedImage(imgIn.getWidth(), imgIn.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
		Graphics2D painter = image.createGraphics();
		try {
			painter.setColor(Color.WHITE);
			painter.fillRect(0, 0, imgIn.getWidth(), imgIn.getHeight());
			painter.drawImage(imgIn,  537,   68, 2776, 272,  537,   68, 2776,  272, obs);
			File filIn2 = new File(filDir, "DSC05149M_989090341834.png");
			BufferedImage imgIn2 = ImageIO.read(filIn2);
			painter.drawImage(imgIn2, 600,  320, 2280, 640,  600,  320, 2280,  640, obs);
			File filIn3 = new File(filDir, "DSC05149M_a0a0a0341834.png");
			BufferedImage imgIn3 = ImageIO.read(filIn3);
			painter.drawImage(imgIn3, 610,  690, 2650, 850,  610,  690, 2650,  850, obs);
			painter.drawImage(imgIn2, 990,  920, 2600, 1170, 990,  920, 2600, 1170, obs);
			File filIn4 = new File(filDir, "DSC05149M_888080341834.png");
			BufferedImage imgIn4 = ImageIO.read(filIn4);
			painter.drawImage(imgIn4, 970, 1280, 2650, 1520, 970, 1280, 2650, 1520, obs);
			File filIn5 = new File(filDir, "DSC05149M_807070341834.png");
			BufferedImage imgIn5 = ImageIO.read(filIn5);
			painter.drawImage(imgIn5, 970, 1660, 2650, 1830, 970, 1660, 2650, 1830, obs);
			painter.drawImage(imgIn4, 970, 1940, 2200, 2250, 970, 1940, 2200, 2250, obs);
			File filIn6 = new File(filDir, "DSC05149M_46404a463c48.png");
			BufferedImage imgIn6 = ImageIO.read(filIn6);
			painter.drawImage(imgIn6, 600, 2350, 1520, 2900, 600, 2350, 1520, 2900, obs);
			painter.drawImage(imgIn6, 999, 3270, 2105, 3350, 999, 3270, 2105, 3350, obs);
			File filIn7 = new File(filDir, "DSC05149M_704438704438.png");
			BufferedImage imgIn7 = ImageIO.read(filIn7);
			painter.drawImage(imgIn7,1105, 2854, 1200, 2900,1105, 2854, 1200, 2900, obs);
			
			painter.setColor(Color.CYAN);
			painter.drawLine( 550,  91, 548, 233);
			painter.drawLine( 550,  91,2771, 137);
			painter.drawLine(2771, 137,2767, 270);
			painter.drawLine( 548, 233,2767, 270);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		painter.dispose();
		ImageIO.write(image, "png", new File(filDir, "DSC05149M_02.png"));
	}

	public static void flatAndHigherContrast5149() {
		File filDir = new File("E:\\Bilder\\2015-Amerika\\5149.tmp");
		File filIn1 = new File(filDir, "DSC05149M1.png");
		BuffrdImgGetPixelD biGP1 = new BuffrdImgGetPixelD(filIn1);
		BuffrdImgSetPixelD biSP1 = new BuffrdImgSetPixelD(biGP1.getWidth(), biGP1.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		MiRoesDraw.flatAndHigherContrast(biGP1.getWidth(), biGP1.getHeight(), biGP1, biSP1);
		biSP1.write(new File(filDir, "DSC05149M1_BW.png"));
	}
	
	public static void showSinglePixels5149() {
		File filDir = new File("E:\\Bilder\\2015-Amerika\\5149.tmp");
		File filIn1 = new File(filDir, "DSC05149M1_BW.png");
		BuffrdImgGetPixelD biGP1 = new BuffrdImgGetPixelD(filIn1);
		BuffrdImgSetPixelD biSP1 = new BuffrdImgSetPixelD(biGP1.getWidth(), biGP1.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
		MiRoesDraw.showSinglePixels(biGP1.getWidth(), biGP1.getHeight(), 6, biGP1, biSP1);
		biSP1.write(new File(filDir, "DSC05149M1_BW_SinglePixels6.png"));
	}
	
	public static void mergeBlackAndWhite5149() {
		File filDir = new File("E:\\Bilder\\2015-Amerika\\5149.tmp");
		BuffrdImgGetPixelD biGPW = new BuffrdImgGetPixelD(new File(filDir, "DSC05149e_white3.png"));
		BuffrdImgGetPixelD biGPB = new BuffrdImgGetPixelD(new File(filDir, "DSC05149e_dark.png"));
		BuffrdImgGetPixelD biGPM = new BuffrdImgGetPixelD(new File(filDir, "DSC05149M1_BW.png"));
		BuffrdImgSetPixelD biSPR = new BuffrdImgSetPixelD(biGPM.getWidth(), biGPM.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		MiRoesDraw.merge(biGPM.getWidth(), biGPM.getHeight(), biGPM, biGPW, biGPB, biSPR);
		biSPR.write(new File(filDir, "DSC05149f.png"));
	}

	public static void identifyHighContrast() {
		File filDir;
		filDir = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\results\\2017\\SepAll");
		identifyHighContrast(filDir);
		filDir = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\results\\2018\\SepAll");
		identifyHighContrast(filDir);
	}
	
	public static final String STR_EXT_RES1 = ".ignMed.jpg";
	public static final String STR_EXT_RES2 = ".ignMd2.jpg";
	
	public static void identifyHighContrast(File filDir) {
		File[] inFiles = filDir.listFiles(new HighContrastFileFilter());
		if (inFiles == null || inFiles.length < 1) {
			inFiles = new File[1];
			inFiles[0] = filDir;
		}
		for (int iF = 0; iF < inFiles.length; ++iF) {
			System.out.println(inFiles[iF].getName());
			BuffrdImgGetPixelD biGP1 = new BuffrdImgGetPixelD(inFiles[iF]);
			BuffrdImgSetPixelD biSPD = new BuffrdImgSetPixelD();
			BuffrdImgSetPixelD biSPB = new BuffrdImgSetPixelD();
			MiRoesDraw.showSinglePixels(biGP1, biSPD, biSPB, 800, 600);
			MiRoesDraw.diagOut(biSPD.getImage());
			MiRoesDraw.diagOut(biSPB.getImage());
			BufferedImageSetPixImg_ABGR biDif = new BufferedImageSetPixImg_ABGR(
					biSPD.getWidth(), biSPD.getHeight(), biSPB, +1f, biSPD, -1f );
			MiRoesDraw.diagOut(biDif.getImage(0));
			MiRoesDraw.diagOut(biDif.getImage(1));
		}
	}
	
	public static void identifyHighContrast(
			int width, int high,
			IGetPixImg imgIn,
			ISetPixImg imgRes ) {
		float fsPix[] = new float[3];
		for (int iY = 0; iY < high; ++iY) {
			for (int iX = 0; iX < width; ++iX) {
				imgIn.getPixel(iX, iY, fsPix);
				for (int iCol = 0; iCol < 3; ++iCol) {
					if (-0.9f < fsPix[iCol] && fsPix[iCol] < 0.9f) {
						fsPix[iCol] = 0f;
					} else {
						String strRGB;
						if (imgIn instanceof BuffrdImgGetPixelD) {
							int iRGB = ((BuffrdImgGetPixelD)imgIn).getImage().getRGB(iX, iY);
							strRGB = Integer.toHexString(iRGB);
						} else {
							strRGB = "";
						}
						Diag2.OutIf11("Strong dark or brigth", iX, iY, strRGB);
					}
				}
				imgRes.setPixel(iX, iY, fsPix);
			} /* for (int iX ... ) */			
		} /* for (int iY ... ) */
	} /* end of identifyHighContrast(,,,) */
}
