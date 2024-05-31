package de.dreiroeders.workingonimages;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class SourceImage {
	
	private Rectangle m_srcRect;

	public static String[] splitDirNames(String pathName) {
		if (pathName == null) {
			return new String[]{"."};
		} else {
			return pathName.split("[\\\\/]");
		}
	}
	
	private String m_srcFileName;
	private BufferedImage m_image;
	
	public static synchronized BufferedImage ImageIO_read(File inFile) throws IOException {
		return ImageIO.read(inFile);
	}
	
	public SourceImage(File srcFile) {
		m_srcFileName = srcFile.getAbsolutePath();
	}

	public SourceImage(String srcFileName) {
		m_srcFileName = srcFileName;
	}

	public SourceImage(BufferedImage image) {
		m_image = image;
	}

	public void setSourceBounds(Rectangle rect) {
		m_srcRect = rect;
		m_image = null;
	}
	
	public BufferedImage getImage() {
		if (m_image == null && m_srcFileName != null) {
			setImageFromSrcFileName();
		}
		return m_image;
	}
	
	public int getWidth() {
		return getImage().getWidth();
	}
	
	public int getHeight() {
		return getImage().getHeight();
	}
	
	private class SrcDir {
		public File mDir;
		public boolean m_bExists;
		
		public SrcDir(String strDirName) {
			mDir = new File(strDirName);
			m_bExists = mDir.exists();
			System.out.println("Directory "+ mDir.getAbsolutePath()
			       + (m_bExists ? "" : " does not") +" exist"+ (m_bExists ? "s" : "")+"." );
		}
	} /* end class SrcDir */
	
	private static ArrayList<SrcDir> sSrcDirs;
	
	private void initSrcDirs() {
		sSrcDirs = new ArrayList<SrcDir>(2);
		sSrcDirs.add(new SrcDir("Bilder"));
		sSrcDirs.add(new SrcDir("C:\\Users\\MiRoe\\Pictures"));
	}
	
	private void setImageFromSrcFileName() {
		if (sSrcDirs == null) {
			initSrcDirs();
		}
		File inFile = new File(m_srcFileName);
		if (!inFile.exists()) {
			String names[] = splitDirNames(m_srcFileName);
			for (int iP = 0; iP < names.length && !inFile.exists(); ++iP) {
				for (int iS = 0; iS < sSrcDirs.size() && !inFile.exists(); ++iS) {
					if (sSrcDirs.get(iS).m_bExists) {
						File dir = sSrcDirs.get(iS).mDir;
						for (int iD = iP; iD < names.length-1 && dir.exists(); ++iD) {
							dir = new File(dir, names[iD]);
						}
						if (dir.exists()) {
							inFile = new File(dir, names[names.length-1]);
							if (!inFile.exists()) {
								inFile = new File(dir, names[names.length-1]+".png");
							}
							if (!inFile.exists()) {
								inFile = new File(dir, names[names.length-1]+".jpg");
							}
						}
					}
				}
			}
		} /* end if (!inFile.exists()) */
		if (inFile.exists()) {
			System.out.println("\""+ m_srcFileName +"\" mapped to \""+ inFile.getAbsolutePath() +"\"");
			try {
			    m_image = ImageIO_read(inFile);
			    if (m_srcRect != null) {
			    	m_image = m_image.getSubimage(m_srcRect.x, m_srcRect.y, m_srcRect.width, m_srcRect.height);
			    }
			} catch (Exception ex) {
				ex.printStackTrace();
				if (m_srcRect != null) {
					m_image = new BufferedImage(m_srcRect.width, m_srcRect.height, BufferedImage.TYPE_3BYTE_BGR);						
				} else {
					m_image = new BufferedImage(1000, 700, BufferedImage.TYPE_3BYTE_BGR);
				}
				Graphics2D painter = m_image.createGraphics();
				painter.setColor(Color.LIGHT_GRAY);
				painter.fillRect(0, 0, m_image.getWidth(), m_image.getHeight());
				String strEx = ex.getMessage();
				if (strEx == null || strEx.length() < 5) {
					strEx = ex.toString();
				}
				int h2 = m_image.getHeight()/2;
				MiRoesDraw.drawText(strEx, Color.RED, "Arial", 0, painter, 0, 0, m_image.getWidth(), h2);
				MiRoesDraw.drawText(m_srcFileName, Color.RED, "Arial", 0, painter, 0, h2, m_image.getWidth(), h2);
				painter.dispose();
			}
		} else {
			writeFilePaths(m_srcFileName);
		} /* end if (!inFile.exists()) */
	}

	private void writeFilePaths(String inFile) {
		if (m_image == null) {
			if (m_srcRect != null) {
				m_image = new BufferedImage(m_srcRect.width, m_srcRect.height, BufferedImage.TYPE_3BYTE_BGR);						
			} else {
				m_image = new BufferedImage(1000, 700, BufferedImage.TYPE_3BYTE_BGR);
			}
		}
		Graphics2D painter = m_image.createGraphics();
		painter.setColor(Color.LIGHT_GRAY);
		painter.fillRect(0, 0, m_image.getWidth(), m_image.getHeight());
		String[] pathes = splitDirNames(inFile);
		int tHeight = m_image.getHeight()/pathes.length;
		for (int iY = 0; iY < pathes.length; ++iY) {
			StringBuilder pat1 = new StringBuilder();
			for (int iS = iY; iS < pathes.length; ++iS) {
				if (iS > iY) {
					pat1.append('\\');
				}
				pat1.append(pathes[iS]);
			}
			int y1 = m_image.getHeight()*iY/pathes.length;
			Color col = new Color((iY&4)*32, (iY&2)*64, (iY&1)*128);
			MiRoesDraw.drawText(pat1.toString(), col, "times", 0, painter, 0, y1, m_image.getWidth(), tHeight);
		}
		painter.dispose();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		if (m_srcFileName == null) {
			res.append("null");
		} else {
			res.append("\"");
			res.append(m_srcFileName);
			res.append("\" /* ");
		}
		if (m_image == null) {
			res.append("null */");
		} else {
			res.append("[");
			res.append(getWidth());
			res.append("x");
			res.append(getHeight());
			res.append("] */");
		}
		return res.toString();
	}
	
	
}
