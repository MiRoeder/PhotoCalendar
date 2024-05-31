package de.dreiroeders.workingonimages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InspectPic1 {

	public static void main(String[] args) {
		try {
			File inFile;
			inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif1111.png");
			inspectPic(inFile, 8, 8, 4412, 3248);
			inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif2222.png");
			inspectPic(inFile, 8, 8, 4412, 3248);
			inFile = new File("C:\\Users\\Michael\\Documents\\E\\Dev\\Java\\workspace\\WorkingOnImages\\tryRemoveMirrorImages.data\\Dif6666.png");
			inspectPic(inFile, 8, 8, 4412, 3248);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}


	public static void inspectPic (
			File inFileImage
			) throws IOException {
		System.out.println(inFileImage.getAbsolutePath() +" inspected: ");
		BufferedImage inImage = ImageIO.read(inFileImage);
		inspectPic(inImage, 0, 0, 30000, 30000);
	}

	public static void inspectPic (
			File inFileImage,
			int x0, int y0,	int width, int height
			) throws IOException {
		System.out.println(inFileImage.getAbsolutePath() +" inspected: ");
		BufferedImage inImage = ImageIO.read(inFileImage);
		inspectPic(inImage, x0, y0, width, height);
	}

	public static void inspectPic (
			BufferedImage inImage,
			int x0, int y0,	int width, int height
			) {
		int x01 = Math.max(0, x0);
		int y01 = Math.max(0, y0);
		int x9 = Math.min(inImage.getWidth() , width +x01);
		int y9 = Math.min(inImage.getHeight(), height+y01);
		int minCols[] = new int[3];
		int maxCols[] = new int[3];
		int minColX[] = new int[3];
		int maxColX[] = new int[3];
		int minColY[] = new int[3];
		int maxColY[] = new int[3];
		for (int iCol = 0; iCol < 3; ++iCol) {
			minCols[iCol] = 255;
			maxCols[iCol] = 0;
		}
		for (int ym = y01; ym < y9; ++ym) {
			for (int xm = x01; xm < x9; ++xm) {
				int inPx = inImage.getRGB(xm, ym);
				int[] inCols = MiRoesDraw.getCs(inPx, null);
				for (int iCol = 0; iCol < 3; ++iCol) {
					if (inCols[iCol] < minCols[iCol]) {
						minColX[iCol] = xm;
						minColY[iCol] = ym;
						minCols[iCol] = inCols[iCol];
					}
					if (inCols[iCol] > maxCols[iCol]) {
						maxColX[iCol] = xm;
						maxColY[iCol] = ym;
						maxCols[iCol] = inCols[iCol];
					}
				}
			}
		}
		System.out.println("x0 ="+ x01 +" y0="+ y01 +" width="+ (x9-x01) +" height="+ (y9-y01));
		for (int iCol = 0; iCol < 3; ++iCol) {
			System.out.println("Color"+ iCol +" min: "+ minCols[iCol] +" at "+ minColX[iCol] +","+ minColY[iCol]);
			System.out.println("Color"+ iCol +" max: "+ maxCols[iCol] +" at "+ maxColX[iCol] +","+ maxColY[iCol]);
		}
	}
}
