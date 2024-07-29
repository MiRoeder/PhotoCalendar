package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Draw1ImageWithTxt extends Draw1ImageI {

	public String m_sBottomText;
	public Color m_colorText;
	public String m_strFontName;
	public int m_iFontStyle;
	public int m_height;
	
	public Draw1ImageWithTxt(String srcFileName, String sBottomText, Color colorText, String strFontName, int iFontStyle, int height) {
		super(srcFileName);
		this.m_sBottomText = sBottomText;
		this.m_colorText = colorText;
		this.m_strFontName = strFontName;
		this.m_iFontStyle = iFontStyle;
		this.m_height = height;
	}

	public Draw1ImageWithTxt(SourceImage src, String sBottomText, Color colorText, String strFontName, int iFontStyle, int height) {
		super(src.getImage());
		this.m_sBottomText = sBottomText;
		this.m_colorText = colorText;
		this.m_strFontName = strFontName;
		this.m_iFontStyle = iFontStyle;
		this.m_height = height;
	}

	@Override
	public void drawOnIntermediate(BufferedImage imgIntermediate, int iXExtraRand, int iYExtraRand, Graphics2D painter,
			AffineTransform transform) {
		super.drawOnIntermediate(imgIntermediate, iXExtraRand, iYExtraRand, painter, transform);
		MiRoesDraw.diagOut(imgIntermediate);
		int iWid = imgIntermediate.getWidth() - 2*iXExtraRand;
		int iHei = imgIntermediate.getHeight()- 2*iYExtraRand;
		System.out.println("Draw1ImageT.drawOnIntermediate : "+ iWid +"-"+ iXExtraRand +", "+ iHei +"-"+ iYExtraRand);
		MiRoesDraw.drawText(m_sBottomText, m_colorText, m_strFontName, m_iFontStyle, painter, iXExtraRand, iHei-m_height+iYExtraRand, iWid, m_height);
		MiRoesDraw.diagOut(imgIntermediate);
	}

}
