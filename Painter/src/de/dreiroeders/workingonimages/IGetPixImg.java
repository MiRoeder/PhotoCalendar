package de.dreiroeders.workingonimages;

public interface IGetPixImg {

	/**
	 * 
	 * @param x      x-coord
	 * @param y      y-coord
	 * @param result[3] array for the results.
	 * @return the array param result[3]
	 *  In index 0 blue from -1 ... +1
	 * 			 1 green     -1 ... +1
	 * 			 2 red       -1 ... +1
	 * more details about the indeces in de.dreiroeders.workingonimages.EColorI
	 */
	public float[] getPixel(int x, int y, float result[]);

	public float getAlpha(int x, int y);
	
	public float getBright(int x1, int y1, float facRed, float facGreen, float facBlue);

}
