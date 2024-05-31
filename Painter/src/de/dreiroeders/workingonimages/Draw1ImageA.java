package de.dreiroeders.workingonimages;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Draw1ImageA extends Draw1ImageI {

	public Point2D         aCenterPoint;
	public Rectangle2D     aOutRect;

	public Draw1ImageA(String srcFileName) {
		super(srcFileName);
	}

	public Draw1ImageA(BufferedImage image) {
		super(image);
	}

	public Draw1ImageA(
			String srcFileName,
			double centerPointX,
			double centerPointY,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
		) {
		super(srcFileName);
		aCenterPoint = new Point2D.Double(centerPointX, centerPointY);
		dRot = (float)rotator;
		aOutRect = new Rectangle2D.Double(tx0, ty0, tWidth, tHeight);
	}

	public Draw1ImageA(
			BufferedImage image,
			double centerPointX,
			double centerPointY,
			double rotator,
			double tx0, 
			double ty0,
			double tWidth,
			double tHeight
		) {
		super(image);
		aCenterPoint = new Point2D.Double(centerPointX, centerPointY);
		dRot = (float)rotator;
		aOutRect = new Rectangle2D.Double(tx0, ty0, tWidth, tHeight);
	}

	public void setCenterPoint() {
		if (iCenterPoint == null) {
			double centerPointX, centerPointY;
			if (aCenterPoint != null) {
				centerPointX = aCenterPoint.getX();
				centerPointY = aCenterPoint.getY();
			} else {
				centerPointX = 0.5;
				centerPointY = 0.5;
			}
			int iCPX;
			if (centerPointX < 1.0) {
				int imW = getWidth();
				iCPX = (int)(centerPointX*imW+0.5);
			} else {
				iCPX = (int)(centerPointX+0.5);
			}
			
			int iCPY;
			if (centerPointY < 1.0) {
				int imH = getHeight();
				iCPY = (int)(centerPointY*imH+0.5);
			} else {
				iCPY = (int)(centerPointY+0.5);
			}
			iCenterPoint = new Point(iCPX, iCPY);
		} /* end if (img.iCenterPoint == null) */
	}
	

}
