package de.dreiroeders.workingonimages;

public interface ISetPixImg {

	/**
	 * 
	 * @param x      x-coord
	 * @param y      y-coord
	 * @param rgb[3] array
	 *  In index 0 blue from -1 ... +1
	 * 			 1 green     -1 ... +1
	 * 			 2 red       -1 ... +1
	 * more details about the indeces in de.dreiroeders.workingonimages.EColorI
	 */
	void setPixel(int x, int y, float rgb[]);
	void setPixel(int x, int y, float alpha, float rgb[]);

	void setAlpha(int x, int y, float alpha);

}
