package de.dreiroeders.workingonimages;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

import de.dreiroeders.io.MiRoeFileExtFilter;


public class MiRoesImageScaler1 {

	public static void main(String[] args) {
		try {
			File inDir = new File("C:\\Users\\MiRoe\\Documents\\E\\Dev\\Java\\workspace\\FotoKalender\\tmp\\2021-10\\OlafAngelika\\1.96");
			File outDir = inDir;
			outDir.mkdir();
			File[] inFiles = inDir.listFiles(new MiRoeFileExtFilter("jpg"));
			for (File inFile1 : inFiles) {
				File outFile = new File(outDir, inFile1.getName());
				rescale(inFile1, outFile, "jpg", 1024, 1536, System.out);
			}
			inFiles = inDir.listFiles(new MiRoeFileExtFilter("png"));
			for (File inFile1 : inFiles) {
				File outFile = new File(outDir, inFile1.getName());
				rescale(inFile1, outFile, "png", 1024, 1536, System.out);
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static int rescale (File inFile, File outFile, String outFileFormat, int maxWidth, int maxHeight, PrintStream out) throws IOException {
		int quot;
		BufferedImage inImage = ImageIO.read(inFile);
		if (inImage == null) {
			if (out != null) {
				out.println(" no image!");
			}
			quot = 0;
		} else {
			quot = Math.max((inImage.getWidth()+maxWidth-1) / maxWidth, ((inImage.getHeight()+maxHeight-1) / maxHeight));
		}
		if (quot > 1) {
			int newW = (inImage.getWidth() )/quot;
			int newH = (inImage.getHeight())/quot;
			int imageType = inImage.getType();
			BufferedImage outImg = new BufferedImage(newW, newH, imageType);
			Graphics2D painter = outImg.createGraphics();
			AffineTransform scaler = AffineTransform.getScaleInstance(1d/quot, 1d/quot);
			painter.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			painter.drawImage(inImage, scaler, new MiRoesImageObserver());
			painter.dispose();
			ImageIO.write(outImg, outFileFormat, outFile);
			if (out != null) {
				out.append('"')
				   .append(inFile.getAbsolutePath())
				   .append("\" ["+ inImage.getWidth() +"x"+ inImage.getHeight() +"] --> \"")
				   .append(outFile.getAbsolutePath())
				   .println("\" ["+ newW +"x"+ newH +"]") ;
			}
		}
		return quot;
	}
}
