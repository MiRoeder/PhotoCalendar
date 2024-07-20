package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jama.Matrix;

public class MiRoesDraw {
	
	public static boolean bDoDiagOut = false;
	
	// see  docs/MiRoesDrawImage.png
	
	public static void drawImage(Draw1ImageI img) {
		if (img.iCenterPoint == null) {
			img.setCenterPoint(.5f, .5f);
		}
		int sWidth = img.getImage().getWidth();
		int sHeight= img.getImage().getHeight();
		/*diag:* / System.out.println(img.getImage().getType()); /*/
		/*diag:* / System.out.println(Integer.toHexString(img.getImage().getRGB(                   5, 5))); /**/
		/*diag:* / System.out.println(Integer.toHexString(img.getImage().getRGB(img.getImage().getWidth()-5, 5))); /**/
		Point[] sPoints = new Point[4];  // Edges of the source image
		sPoints[0] = new Point(      -img.iCenterPoint.x,        -img.iCenterPoint.y);
		sPoints[1] = new Point(sWidth-img.iCenterPoint.x,        -img.iCenterPoint.y);
		sPoints[2] = new Point(sWidth-img.iCenterPoint.x, sHeight-img.iCenterPoint.y);
		sPoints[3] = new Point(      -img.iCenterPoint.x, sHeight-img.iCenterPoint.y);
		double sin, cos;
		if (359.99 < img.dRot && img.dRot < 360.01) {
			img.turnToFit();
		}
		if (-10 < img.dRot && img.dRot < 10) {
			sin = Math.sin(img.dRot);
			cos = Math.cos(img.dRot);
		} else if (89.99 < img.dRot && img.dRot < 90.01) {
			sin = 1.0;
			cos = 0.0;
		} else if (-90.01 < img.dRot && img.dRot < -89.99) {
			sin = -1.0;
			cos = 0.0;
		} else if (179.99 < img.dRot && img.dRot < 180.01) {
			sin = 0.0;
			cos = -1.0;
		} else {
			sin = Math.sin(img.dRot*Math.PI/180);
			cos = Math.cos(img.dRot*Math.PI/180);
		}
		AffineTransform transform = new AffineTransform(cos, sin, -sin, cos, 0, 0);
		transform.transform(sPoints, 0, sPoints, 0, 4);
		switch (img.getFillType()) {
		case CutSource:
			drawImgIfNeededCutSource(img, sPoints, transform);
			break;
		case TransparentTarget:
			drawImgTransparentTarget(img, sPoints, transform);
			break;
		}
	}
	
	private static void drawImgIfNeededCutSource(Draw1ImageI img, Point[] sPoints, AffineTransform transform) {
		int sWidth = img.getImage().getWidth();
		int sHeight= img.getImage().getHeight();
		double quot = sWidth+sHeight;
		quot = getQuot2(sPoints[0], sPoints[1], img.iOutRect.width, img.iOutRect.height, quot);
		quot = getQuot2(sPoints[1], sPoints[2], img.iOutRect.width, img.iOutRect.height, quot);
		quot = getQuot2(sPoints[2], sPoints[3], img.iOutRect.width, img.iOutRect.height, quot);
		quot = getQuot2(sPoints[3], sPoints[0], img.iOutRect.width, img.iOutRect.height, quot);
		double fac = 0.5/quot;
		if (1d-1d/Math.max(img.iOutRect.width, img.iOutRect.height) < fac && fac < 1) {
			System.out.println("MiRoesDraw.drawImage(,,,,,) factor will be changed to 1.00000 "+ fac);
			fac = 1d;
		}
		transform.scale(fac, fac);
		Point center = new Point();
		transform.transform(img.iCenterPoint,  center);
		final int ExtraRand = 500;
		double transX = img.iOutRect.width /2 - center.getX() + ExtraRand;
		double transY = img.iOutRect.height/2 - center.getY() + ExtraRand;
		transform = new AffineTransform(transform.getScaleX(), transform.getShearY(), transform.getShearX(), transform.getScaleY(), transX, transY);
		BufferedImage image2 = new BufferedImage(img.iOutRect.width+2*ExtraRand, img.iOutRect.height+2*ExtraRand, img.getImageType());
		Graphics2D painter2 = image2.createGraphics();
		img.predrawOnIntermediate(image2, ExtraRand, ExtraRand, painter2, transform);
		double det = transform.getDeterminant();
		if (Math.abs(det) > 1E-6) {
			painter2.drawImage(img.getImage(), transform, null);
		}
		for (int iiC = 0; iiC < 8; ++iiC) {
			painter2.setColor(new Color((iiC & 2) *127, (iiC & 4) *63, (iiC & 1) * 255));
			painter2.drawRect(ExtraRand-iiC-1, ExtraRand-iiC-1, img.iOutRect.width+2*iiC, img.iOutRect.height+2*iiC);
		}
		img.drawOnIntermediate(image2, ExtraRand, ExtraRand, painter2, transform);
		painter2.dispose();
		Object dg = diagOut(image2);
		String strDg = dg instanceof File ? ((File)dg).getName() : String.valueOf(dg);
		System.out.println("MiRoesDraw.drawImage("+ img +"): factor = "+ fac +" "+ det +" > "+ strDg);
		Graphics2D painter = img.outputImage.createGraphics();
		boolean bRes = painter.drawImage(image2, img.iOutRect.x, img.iOutRect.y, img.iOutRect.x+img.iOutRect.width, img.iOutRect.y+img.iOutRect.height, ExtraRand, ExtraRand, ExtraRand+img.iOutRect.width-1, ExtraRand+img.iOutRect.height-1, null);
		assert(bRes);
		painter.dispose();
	}

	private static double getQuot2(Point p1, Point p2, int tW, int tH, double retVal0) {
		double retVal;
		retVal = getQuot(p1, p2, tW+1,  tH+1, retVal0);
		retVal = getQuot(p1, p2, tW+1, -tH-1, retVal);
		return retVal;
	}
	
	private static double getQuot(Point p1, Point p2, int tW, int tH, double retVal0) {
		double retVal = retVal0;
		try {
			double[][] lMat = new double[2][];
			double[]   rMat = new double[2];
			lMat[0] = new double[2];
			lMat[1] = new double[2];
			lMat[0][0] = p2.getX() - p1.getX();
			lMat[0][1] = tW;
			rMat[0]    = p1.getX();
			lMat[1][0] = p2.getY() - p1.getY();
			lMat[1][1] = tH;
			rMat[1]    = p1.getY();
			Matrix mat1 = new Matrix(lMat);
			Matrix mat2 = new Matrix(rMat, 2);
			Matrix ans = mat1.solve(mat2);
			double retVal2 = Math.abs(ans.get(1, 0));
			retVal = Math.min(retVal, retVal2);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return retVal;
	}

	private static void drawImgTransparentTarget(Draw1ImageI img, Point[] sPoints, AffineTransform transform1) {
		System.out.println("drawImgTransparentTarget(\n"+ img +",\n"+ sPoints +",\n"+ transform1 +")...");
		double x0 = 0;
		double x9 = x0;
		double y0 = 0;
		double y9 = y0;
		for (int iP = 0; iP < 4; ++iP) {
			double x5 = sPoints[iP].getX();
			x0 = Math.min(x0, x5);
			x9 = Math.max(x9, x5);
			double y5 = sPoints[iP].getY();
			y0 = Math.min(y0, y5);
			y9 = Math.max(y9, y5);
		}
		double wid = x9-x0;
		double hei = y9-y0;
		if (wid >= 1 && hei >= 1) {
			double fac = Math.min(img.iOutRect.width/(wid+1), img.iOutRect.height/(hei+1));
			AffineTransform transform2 = new AffineTransform(transform1.getScaleX()*fac, transform1.getShearY()*fac,
					                                         transform1.getShearX()*fac, transform1.getScaleY()*fac,
					                                         img.iOutRect.x+img.iOutRect.width/2 -img.iCenterPoint.x*fac,
					                                         img.iOutRect.y+img.iOutRect.height/2-img.iCenterPoint.y*fac );
			System.out.println(transform2);
			int sWidth = img.getWidth();
			int sHeight= img.getHeight();
			Point[] srcEdges = new Point[4];
			srcEdges[0] = new Point(     0,      0);
			srcEdges[1] = new Point(sWidth,      0);
			srcEdges[2] = new Point(sWidth, sHeight);
			srcEdges[3] = new Point(     0, sHeight);
			transform2.transform(srcEdges, 0, srcEdges, 0, 4);
			int dx = 0;
			int dy = 0;
			for (int iP = 0; iP < 4; ++iP) {
				int x5 = srcEdges[iP].x;
				if (x5+dx < img.iOutRect.x) {
					dx = img.iOutRect.x-x5;
					System.out.println("drawImgTransparentTarget(..) move image right "+ dx +" to fit edge "+ iP);
				}
				if (x5+dx > img.iOutRect.x+img.iOutRect.width) {
					dx = img.iOutRect.x+img.iOutRect.width-x5;
					System.out.println("drawImgTransparentTarget(..) move image left "+ (-dx) +" to fit edge "+ iP);
				}
				int y5 = srcEdges[iP].y;
				if (y5+dy < img.iOutRect.y) {
					dy = img.iOutRect.y-y5;
					System.out.println("drawImgTransparentTarget(..) move image down "+ dy +" to fit edge "+ iP);
				}
				if (y5+dy > img.iOutRect.y+img.iOutRect.height) {
					dy = img.iOutRect.y+img.iOutRect.height-y5;
					System.out.println("drawImgTransparentTarget(..) move image up "+ (-dy) +" to fit edge "+ iP);
				}
			}
			if (dx != 0 || dy != 0) {
				transform2 = new AffineTransform(transform2.getScaleX(), transform2.getShearY(),
                        						 transform2.getShearX(), transform2.getScaleY(),
                        						 transform2.getTranslateX()+dx,
                        						 transform2.getTranslateY()+dy );
			}
			System.out.println(transform2.toString());

			Graphics2D painter = img.outputImage.createGraphics();
			img.predrawOnIntermediate(img.outputImage, 0, 0, painter, transform1);
			img.drawOnIntermediate(img.outputImage, 0, 0, painter, transform1);
			double det = transform1.getDeterminant();
			if (Math.abs(det) > 1E-6) {
				System.out.println("MiRoesDraw.drawImgTransparentTarget("+ img +",): factor = "+ fac +" "+ det +" > ["+ img.iOutRect +"]");
				boolean bRes = painter.drawImage(img.getImage(), transform2, null);
				assert(bRes);
				diagOut(img.outputImage);
			} else {
				System.out.println("drawImgTransparentTarget("+ img.toString() + ",) too small determinant = "+ det);
			}
			painter.dispose();
		} else {
			System.out.println("drawImgTransparentTarget("+ img.toString() + " too small: ["+ wid +"x"+ hei +"])");
		}
		System.out.println("drawImgTransparentTarget(,,);");
	}
	
	public static void drawPartImage(
			String strInFileName,
			double centerPointX,
			double centerPointY,
			double minWidth,
			double minHeight,
			double rotator,
			BufferedImage outputImage,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
			) throws IOException {
		SourceImage inFile = new SourceImage(strInFileName);
		BufferedImage inImage = inFile.getImage();
		int iCPX;
		if (centerPointX < 1.0) {
			int imW = inImage.getWidth();
			iCPX = (int)(centerPointX*imW+0.5);
		} else {
			iCPX = (int)(centerPointX+0.5);
		}
		int iCPY;
		if (centerPointY < 1.0) {
			int imH = inImage.getHeight();
			iCPY = (int)(centerPointY*imH+0.5);
		} else {
			iCPY = (int)(centerPointY+0.5);
		}
		int iLeX;
		if (minWidth <= 1.0001) {
			int imW = inImage.getWidth();
			iLeX = (int)(minWidth*imW+0.5);
		} else {
			iLeX = (int)(minWidth+0.5);
		}
		int iLeY;
		if (minHeight <= 1.0001) {
			int imH = inImage.getHeight();
			iLeY = (int)(minHeight*imH+0.5);
		} else {
			iLeY = (int)(minHeight+0.5);
		}
		int omW = outputImage.getWidth();
		int omH = outputImage.getHeight();
		int itW;
		if (tWidth < 1.5) {
			itW = (int)(tWidth*omW+0.5);
		} else {
			itW = (int)(tWidth+0.5);
		}
		int itX0;
		if (tx0 < 1.0) {
			itX0 = (int)(tx0*omW+0.5);
		} else {
			itX0 = (int)(tx0+0.5);
		}
		int itH;
		if (tHeight < 1.5) {
			itH = (int)(tHeight*omH+0.5);
		} else {
			itH = (int)(tHeight+0.5);
		}
		int itY0;
		if (ty0 < 1.0) {
			itY0 = (int)(ty0*omH+0.5);
		} else {
			itY0 = (int)(ty0+0.5);
		}
		drawPartImage(inImage, iCPX, iCPY, iLeX, iLeY, rotator, outputImage, itX0, itY0, itW, itH);
	}

	public static void drawPartImage(
			BufferedImage inImage,
			int centerPointX,
			int centerPointY,
			int minWidth,
			int minHeight,
			double rotator,
			BufferedImage outputImage,
			int tx0, 
			int ty0,
			int tWidth,
			int tHeight
			) throws IOException {
		double sin, cos;
		if (89.99 < rotator && rotator < 90.01) {
			sin = 1.0;
			cos = 0.0;
		} else if (-90.01 < rotator && rotator < -89.99) {
			sin = -1.0;
			cos = 0.0;
		} else {
			sin = Math.sin(rotator);
			cos = Math.cos(rotator);
		}
		double fac = Math.min((double)tWidth/minWidth, (double)tHeight/minHeight);
		final int ExtraRand = 1000;
		AffineTransform transform = new AffineTransform(cos*fac, sin*fac, -sin*fac, cos*fac, 0, 0);
		Point center = new Point(centerPointX, centerPointY);
		transform.transform(center, center);
		double transX = tWidth /2 - center.getX() + ExtraRand;
		double transY = tHeight/2 - center.getY() + ExtraRand;
		transform = new AffineTransform(transform.getScaleX(), transform.getShearY(), transform.getShearX(), transform.getScaleY(), transX, transY);
		BufferedImage image2 = new BufferedImage(tWidth+2*ExtraRand, tHeight+2*ExtraRand, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D painter2 = image2.createGraphics();
		painter2.setBackground(new Color(0x80,0x80,0x80, 0));
		painter2.clearRect(0, 0, tWidth+2*ExtraRand, tHeight+2*ExtraRand);
		painter2.drawImage(inImage, transform, null);
		for (int iiC = 0; iiC < 8; ++iiC) {
			painter2.setColor(new Color((iiC & 2) *127, (iiC & 4) *63, (iiC & 1) * 255));
			painter2.drawRect(ExtraRand-iiC-1, ExtraRand-iiC-1, tWidth+2*iiC, tHeight+2*iiC);
		}
		painter2.dispose();
		diagOut(image2);
		Graphics2D painter = outputImage.createGraphics();
		boolean bRes = painter.drawImage(image2, tx0, ty0, tx0+tWidth, ty0+tHeight, ExtraRand, ExtraRand, ExtraRand+tWidth-1, ExtraRand+tHeight-1, null);
		assert(bRes);
		painter.dispose();
	}


	public static int drawText(String strText, Font font, Object color, Graphics2D output, int x0, int y0) {
		int retVal;
		if (color == null || color instanceof Color) {
			retVal = drawTextCol(strText, font, (Color)color, output, x0, y0);
		} else
		if (color instanceof BufferedImage) {
			retVal = drawTextImg(strText, font, (BufferedImage)color, output, x0, y0);
		} else {
			System.err.println("Error:");
			System.err.println("de.dreiroeders.workingonimages.MiRoesDraw.drawText(String, Font, wrong Object, BufferedImage, int, int)");
			System.err.println("got unexpected color object:)" + color.getClass());
			System.err.println(color.toString());
			retVal = 0;
		}
		return retVal;
	}
	
	public static int drawTextImg(String strText, Font font, BufferedImage color, Graphics2D painter, int x0, int y0) {
		int wid = 0;
		Graphics2D pai = null;
		try {
			painter.setFont(font);
			Rectangle2D shape = font.getStringBounds(strText, painter.getFontRenderContext());
			wid = (int)Math.ceil(shape.getWidth());
			int hei = (int)Math.ceil(shape.getHeight());
			FontRenderContext frc = painter.getFontRenderContext();
			LineMetrics lm = font.getLineMetrics(strText, frc);
			int ascent = (int)Math.ceil(lm.getAscent());
			BufferedImage text = new BufferedImage(wid, hei, BufferedImage.TYPE_BYTE_BINARY);
			pai = text.createGraphics();
			pai.setFont(font);
			pai.setColor(Color.WHITE);
			pai.fillRect(0, 0, wid, hei);
			pai.setColor(Color.BLACK);
			pai.drawString(strText, 0, ascent);
			pai.dispose();
			diagOut(text);
			
			AffineTransform xform = new AffineTransform(shape.getWidth()/(float)color.getWidth(), 0, 0, shape.getHeight()/color.getHeight(), 0, 0);
			//diag: System.out.println("drawTextImg(\""+ strText +"\",...) scale : "+ xform.getScaleX() + " x "+ xform.getScaleY());
			BufferedImage cols = new BufferedImage(wid, hei, BufferedImage.TYPE_3BYTE_BGR);
			pai = cols.createGraphics();
			pai.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			pai.drawRenderedImage(color, xform);
			pai.dispose();
			diagOut(cols);
			
			int h1 = (int)(lm.getStrikethroughThickness()/4f+0.9f);

			int col1;
			col1 = color.getRGB(0, 0);
			Color col2;
			col2 = new Color(255-(255-getC3(col1))/2, 255-(255-getC2(col1))/2, 255-(255-getC1(col1))/2);
			painter.setColor(col2);
			painter.drawString(strText, x0+0, y0+0);
			col1 = color.getRGB(color.getWidth()-1, color.getHeight()-1);
			col2 = new Color(getC3(col1)/2, getC2(col1)/2, getC1(col1)/2);
			painter.setColor(col2);
			painter.drawString(strText, x0+2*h1, y0+2*h1);
			for (int iy = 0; iy < hei; ++iy) {
				for (int ix = 0; ix < wid; ++ix) {
					int iBP = text.getRGB(ix, iy);
					if (iBP != -1) {
						int nRGB = cols.getRGB(ix, iy);
						painter.setColor(new Color(nRGB));
						int y = y0+1+iy - ascent;
						painter.drawLine(x0+h1+ix, y, x0+h1+ix, y);
					}					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (pai != null) {
			pai.dispose();
		}
		return wid+2;
	}
	
	public static int drawTextCol(String strText, Font font, Color color, Graphics2D painter, int x0, int y0) {
		int retVal = 0;
		painter.setFont(font);
		LineMetrics lm = font.getLineMetrics(strText, painter.getFontRenderContext());
		int h1 = (int)(lm.getStrikethroughThickness()/2f+0.9f);
		retVal = drawTextCol(strText, font, h1, color, painter, x0, y0);
		return retVal;
	}
	
	public static int drawTextCol(String strText, Font font, int iShadow, Color color, Graphics2D painter, int x0, int y0) {
		int retVal = 0;
		painter.setFont(font);
		Rectangle2D shape = font.getStringBounds(strText, painter.getFontRenderContext());
		retVal = (int)Math.ceil(shape.getWidth());
		if (iShadow != 0) {
			Color col2;
			col2 = new Color(255-(255-color.getRed())/2, 255-(255-color.getGreen())/2, 255-(255-color.getBlue())/2);
			painter.setColor(col2);
			for (int iShad = 0; iShad < iShadow; ++iShad) {
				painter.drawString(strText, x0+iShad, y0+iShad);
			}
			col2 = new Color(color.getRed()/2, color.getGreen()/2, color.getBlue()/2);
			painter.setColor(col2);
			for (int iShad = 0; iShad < iShadow; ++iShad) {
				painter.drawString(strText, x0+2*iShadow-iShad, y0+2*iShadow-iShad);
			}
		}
		if (color != null) {
			painter.setColor(color);
			painter.drawString(strText, x0+iShadow, y0+iShadow);
		}
		return retVal+2*iShadow;
	}
	
	public static int drawText (
			String strText,
			Color colorText,
			String strFontName,
			int iFontStyle,
			Graphics2D output,
			int tx0, 
			int ty0,
			int tWidth,
			int tHeight
			) {
		int h1 = tHeight;
		Font font1 = new Font(strFontName, iFontStyle, h1);
		output.setFont(font1);
		Rectangle2D shape = font1.getStringBounds(strText, output.getFontRenderContext());
		LineMetrics lm = font1.getLineMetrics(strText, output.getFontRenderContext());
		System.out.println("Try1: h1="+ h1 +" LineMetrics: Ascent="+ lm.getAscent() +" Descent="+ lm.getDescent() +" Leading="+ lm.getLeading() +" Rect.height="+ shape.getHeight());
		float facW, facH;
		int hei = (int)Math.ceil(shape.getHeight());
		if (hei > tHeight) {
			facH = (float)tHeight/hei;
			System.out.println("MiRoesDraw.drawText(\""+ strText +"\",...) Text too high: "+ hei +" versus "+ tHeight);
		} else {
			facH = 1f;
		}
		int wid = (int)Math.ceil(shape.getWidth());
		if (wid >= tWidth) {
			facW = (float)tWidth/wid;
			System.out.println("MiRoesDraw.drawText(\""+ strText +"\",...) Text too width: "+ wid +" versus "+ tWidth);
		} else {
			facW = 1f;
		}
		if (facH < 0.999999f || facW < 0.999999f) {
			h1 = (int)(Math.min(facH, facW)*h1);
			font1 = new Font(strFontName, iFontStyle, h1);
		}
		output.setFont(font1);
		/*LineMetrics*/ lm = font1.getLineMetrics(strText, output.getFontRenderContext());
		shape = font1.getStringBounds(strText, output.getFontRenderContext());
		System.out.println("Try2: h1="+ h1 +" LineMetrics: Ascent="+ lm.getAscent() +" Descent="+ lm.getDescent() +" Leading="+ lm.getLeading() +" Rect.height="+ shape.getHeight());
		wid = (int)Math.ceil(shape.getWidth());
		hei = (int)Math.ceil(shape.getHeight());
		int w2 = (tWidth-wid)/2;
		int h2 = (tHeight-hei)/2;
		if (w2 < 0) {
			System.out.println("MiRoesDraw.drawText(\""+ strText +"\",...) Text still too width: "+ w2);
			w2 = 0;
		}
		if (h2 < 0) {
			System.out.println("MiRoesDraw.drawText(\""+ strText +"\",...) Text still too height: "+ h2);
			h2 = 0;
		}
		return drawTextCol(strText, font1, colorText, output, tx0+w2, ty0+(int)Math.ceil(lm.getAscent())+h2);
	}

	public static MiRoesFont getFont (
			String strText,
			String strFontName,
			int iFontStyle,
			Graphics2D output,
			int tWidth,
			int tHeight
			) {
		int h1 = tHeight;
		Font font1 = new Font(strFontName, iFontStyle, h1);
		output.setFont(font1);
		Rectangle2D shape = font1.getStringBounds(strText, output.getFontRenderContext());
		int wid = (int)Math.ceil(shape.getWidth());
		if (wid >= tWidth) {
			h1 = (int)((float)tWidth/wid*h1);
			font1 = new Font(strFontName, iFontStyle, h1);
		}
		output.setFont(font1);
		shape = font1.getStringBounds(strText, output.getFontRenderContext());
		wid = (int)Math.ceil(shape.getWidth());
		int w2 = (tWidth-wid);
		int h2 = (tHeight-h1);
		if (w2 < 0) {
			System.out.println("MiRoesDraw.getFont(\"+ strText +\",...) Text immer noch zu breit: "+ w2);
		}
		if (h2 < 0) {
			System.out.println("MiRoesDraw.getFong(\"+ strText +\",...) Text immer noch zu hoch: "+ h2);
		}
		return new MiRoesFont(font1, wid, h1);
	}

	public static void drawMasked(BufferedImage inImage, BufferedImage maskImage, float maskFac, BufferedImage targetImage, int targetX0, int targetY0) throws Exception {
		int we = inImage.getWidth();
		int he = inImage.getHeight();
		int xi0 = Math.max(0, -targetX0);
		int xi9 = Math.min(we, targetImage.getWidth()-targetX0);
		int yi0 = Math.max(0, -targetY0);
		int yi9 = Math.min(he, targetImage.getHeight()-targetY0);
		for (int yi = yi0; yi < yi9; ++yi) {
			for (int xi = xi0; xi < xi9; ++xi) {
				int nMask = maskImage.getRGB(xi, yi);
				if ((nMask & 0xFFFFFF) != 0xFFFFFF) {
					int nIn = inImage.getRGB(xi, yi);
					float fAlfaIn = (float)((nIn >> 24)&0xFF) / 255f * maskFac;
					if (fAlfaIn < 0 || 1 < fAlfaIn) {
						System.err.println("Gleich schiesse ich eine Assertion !");
					}
					int nTar0 = targetImage.getRGB(xi+targetX0, yi+targetY0);
					double fM1;
					fM1 = (255-getC1(nMask))/255f*fAlfaIn;
					assert(0 <= fM1 && fM1 <= 1);
					int nT1 = (int)Math.round(getC1(nIn) * fM1 + getC1(nTar0) * (1-fM1));
					fM1 = (255-getC2(nMask))/255f*fAlfaIn;
					assert(0 <= fM1 && fM1 <= 1);
					int nT2 = (int)Math.round(getC2(nIn) * fM1 + getC2(nTar0) * (1-fM1));
					fM1 = (255-getC3(nMask))/255f*fAlfaIn;
					assert(0 <= fM1 && fM1 <= 1);
					int nT3 = (int)Math.round(getC3(nIn) * fM1 + getC3(nTar0) * (1-fM1));
					int rgb = ((nT3 << 16) | (nT2 << 8) | nT1 );
					targetImage.setRGB(xi+targetX0, yi+targetY0, rgb);
				}
			}
		}
	}
	
	public static int getC1(int rgb) {
		return rgb & 0xFF;
	}

	public static int getC2(int rgb) {
		return (rgb >> 8) & 0xFF;
	}

	public static int getC3(int rgb) {
		return (rgb >> 16) & 0xFF;
	}

	public static int getCi(int rgb, int iCol) {
		switch (iCol) {
		case 0: return getC1(rgb);
		case 1: return getC2(rgb);
		case 2: return getC3(rgb);
		default:
			String strErrMsg = "Wrong color index: "+ iCol;
			throw new IllegalArgumentException(strErrMsg);
		}
	}

	public static int[] getCs(int rgb, int[] nCols) {
		int[] result = nCols;
		if (result == null) {
			result = new int[3];
		}
		for (int iCol = 0; iCol < 3; ++iCol) {
			result[iCol] = getCi(rgb, iCol);
		}
		return result;
	}

	public static int getRed(int srcPixel) {
		return MiRoesDraw.getC3(srcPixel);
	}
	
	public static int getGreen(int srcPixel) {
		return MiRoesDraw.getC2(srcPixel);
	}
	
	public static int getBlue(int srcPixel) {
		return MiRoesDraw.getC1(srcPixel);
	}
	
	public static int makeRGB(int[] nCols) {
		assert(nCols.length == 3);
		assert(0 <= nCols[0] && nCols[0] <= 255);
		assert(0 <= nCols[1] && nCols[1] <= 255);
		assert(0 <= nCols[2] && nCols[2] <= 255);
		return (nCols[2] << 16) | (nCols[1] << 8) | nCols[0];
	}

	public static void fillImage(BufferedImage outputImage, Color backColor) {
		Graphics2D painter = outputImage.createGraphics();
		painter.fillRect(0, 0, outputImage.getWidth(), outputImage.getHeight());
		painter.dispose();
	}
	
	public static void merge (
		int width, int high,
		IGetPixImg imgMask,
		IGetPixImg imsPlus,
		IGetPixImg imgMinus,
		ISetPixImg imgResult ) {
		float fsMsk[] = new float[3];
		float fsPlu[] = new float[3];
		float fsMin[] = new float[3];
		for (int iY = 0; iY < high; ++iY) {
			for (int iX = 0; iX < width; ++iX) {
				float fMskAlpha = 0f;
				imgMask .getPixel(iX, iY, fsMsk);
				imsPlus .getPixel(iX, iY, fsPlu);
				imgMinus.getPixel(iX, iY, fsMin);
				for (int iC = 0; iC < 3; ++iC) {
					fsPlu[iC] = ((1f+fsMsk[iC]) * fsPlu[iC] + (1f-fsMsk[iC]) * fsMin[iC]) /2f;
					fMskAlpha += fsMsk[iC];
				}
				float fResAlpha = ((3+fMskAlpha) * imsPlus.getAlpha(iX, iY) + (3-fMskAlpha) * imgMinus.getAlpha(iX, iY)) / 6f;
				imgResult.setPixel(iX, iY, fResAlpha, fsPlu);
			} /* for (int iX ... ) */			
		} /* for (int iY ... ) */
	}
	
	public static void flatAndHigherContrast(
		int width, int high,
		IGetPixImg imgIn,
		ISetPixImg imgRes ) {
		final float dark[] = new float[]{-1f,-1f,-1f};
		final float brgh[] = new float[]{+1f,+1f,+1f};
		for (int iY = 0; iY < high; ++iY) {
			for (int iX = 0; iX < width; ++iX) {
				float fBright = 0f;
				for (int idY = -1; idY <= 1; ++idY) {
					for (int idX = -1; idX <= 1; ++idX) {
						float fac = 1/(0.4f+Math.abs(idX)+Math.abs(idY));
						fBright += imgIn.getBright(iX+idX, iY+idY, 0.35f, 0.35f, 0.3f) * fac;
					} /* for (int iX ... ) */			
				} /* for (int iY ... ) */
				imgRes.setPixel(iX, iY, fBright < 0 ? dark : brgh);
			} /* for (int iX ... ) */			
		} /* for (int iY ... ) */
	} /* end of flatAndHigherContrast(,) */
	
	public static String diagSize(RenderedImage img) {
		try {
			if (img != null) {
				return "[" + img.getWidth() +"x"+ img.getHeight() +"]";
			} else {
				return "-0-";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ex.toString();
		}
	}
	
	public static int siDiag = 1000;
		
	public static Object diagOutOnKey(RenderedImage img) {
		try {
			if (System.in.available() > 0) {
				do {
					System.in.read();
				} while (System.in.available() > 0);
				return diagOut(img);
			} else {
				return "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ex.toString();
		}	
	}
	
	public static Object diagOut(RenderedImage img) {
		if (bDoDiagOut) {
			try {
				File outFile = new File("tmp/i"+(++siDiag)+".png");
				ImageIO.write(img, "png", outFile);
				if (img instanceof BufferedImage) {
					BufferedImage bimg = (BufferedImage)img;
					int iType = bimg.getType();
					if (iType == BufferedImage.TYPE_4BYTE_ABGR) {
						int wid = bimg.getWidth();
						int hei = bimg.getHeight();
						BufferedImage imgAlpha = new BufferedImage(wid, hei, BufferedImage.TYPE_BYTE_GRAY);
						for (int iY = 0; iY < hei; ++iY) {
							for (int iX = 0; iX < wid; ++iX) {
								int iRgb = bimg.getRGB(iX, iY);
								iRgb = (0xFF - (iRgb >> 24)) & 0xFF;
								iRgb = iRgb | (iRgb << 8) | (iRgb << 16);
								imgAlpha.setRGB(iX, iY, iRgb);
							}
						}
						diagOut(imgAlpha);
					}
				}
				return outFile;
			} catch (Exception ex) {
				ex.printStackTrace();
				return ex.toString();
			}	
		} else {
			return " _ ";
		}
	}
	
	public static void showSinglePixels(
			int width, int high,
			int nAura,
			IGetPixImg imgIn,
			ISetPixImg imgRes ) {
		float res[] = new float[3];
		for (int iY = 0; iY < high; ++iY) {
			for (int iX = 0; iX < width; ++iX) {
				float fBright = imgIn.getBright(iX, iY, 0.35f, 0.35f, 0.3f);
				for (int iCol = 0; iCol < 3; ++iCol) {
					res[iCol] = fBright;
				}
				for (int idY = -nAura; idY <= nAura; ++idY) {
					for (int idX = -nAura; idX <= nAura; ++idX) {
						fBright = imgIn.getBright(iX+idX, iY+idY, 0.35f, 0.35f, 0.3f);
						res[0] = Math.max(res[0], fBright);
						res[2] = Math.min(res[2], fBright);
					} /* for (int iX ... ) */			
				} /* for (int iY ... ) */
				imgRes.setPixel(iX, iY, res);
			} /* for (int iX ... ) */			
		} /* for (int iY ... ) */
	}

	public static void showSinglePixels(
			BuffrdImgGetPixelD imgIn,
			BuffrdImgSetPixelD imgDark,
			BuffrdImgSetPixelD imgBright,
			int recomTrgW, int recomTrgH ) {
		showSinglePixelsInitOut(imgIn, imgDark,  recomTrgW, recomTrgH);
		showSinglePixelsInitOut(imgIn, imgBright,recomTrgW, recomTrgH);
		int trgwWdth;
		int trgHigh;
		if (imgDark != null) {
			if (imgBright != null) {
				trgwWdth = Math.min(imgDark.getWidth(), imgBright.getWidth());
				trgHigh = Math.min(imgDark.getHeight(), imgBright.getHeight());
			} else {
				trgwWdth = imgDark.getWidth();
				trgHigh = imgDark.getHeight();
			}
		} else {
			if (imgBright != null) {
				trgwWdth = imgBright.getWidth();
				trgHigh = imgBright.getHeight();
			} else {
				trgwWdth = 0;
				trgHigh = 0;
			}
		}
		showSinglePixels(imgIn.getWidth(), imgIn.getHeight(), imgIn, trgwWdth, trgHigh, imgDark, imgBright);
	}

	public static void showSinglePixels(
			int srcwWdth, int srcHigh,
			IGetPixImg imgIn,
			int trgwWdth, int trgHigh,
			ISetPixImg imgDark,
			ISetPixImg imgBright) {
		float[] inP = new float[3];
		float[] oDk = new float[3];
		float[] oBr = new float[3];
		float dX = (float)srcwWdth/trgwWdth;
		float dY = (float)srcHigh/trgHigh;
		int nX2 = (int)Math.ceil(dX/2f);
		int nY2 = (int)Math.ceil(dY/2f);
		for (int iTY = 0; iTY < trgHigh; ++iTY) {
			int sY0 = (int)((iTY+0.5f)*dY+0.5f);
			for (int iTX = 0; iTX < trgwWdth; ++iTX) {
				for (int iC = 0; iC < 3; ++iC) {
					oDk[iC] = +1f;
					oBr[iC] = -1f;
				}
				int sX0 = (int)((iTX+0.5f)*dX+0.5f);
				for (int iSY = -nY2; iSY <= nY2; ++iSY) {
					for (int iSX = -nX2; iSX <= nX2; ++iSX) {
						imgIn.getPixel(sX0+iSX, sY0+iSY, inP);
						for (int iC = 0; iC < 3; ++iC) {
							oDk[iC] = Math.min(oDk[iC], inP[iC]);
							oBr[iC] = Math.max(oBr[iC], inP[iC]);
						}
					}
				}
				imgDark.setPixel(iTX, iTY, oDk);
				imgBright.setPixel(iTX, iTY, oBr);
			} /* for (int iTX ... ) */			
		} /* for (int iTY ... ) */
	}

	private static void showSinglePixelsInitOut(BuffrdImgGetPixelD imgIn, BuffrdImgSetPixelD imgOut, int recomTrgW, int recomTrgH) {
		if (imgOut != null) {
			if (imgOut.getWidth() < 1 || imgOut.getHeight() < 1) {
				int nDiff = Math.max((imgIn.getWidth()+recomTrgW-1) / recomTrgW, (imgIn.getHeight()+recomTrgH-1) / recomTrgH);
				if (nDiff > 0) {
					int width = (imgIn.getWidth()+nDiff-1)/nDiff;
					int height = (imgIn.getHeight()+nDiff-1)/nDiff;
					BufferedImage img = imgIn.getImage();
					int imageType = img != null ? img.getType() : BufferedImage.TYPE_3BYTE_BGR;
					imgOut.init(width, height, imageType);
				}
			}
		}
	}
			
			
}
