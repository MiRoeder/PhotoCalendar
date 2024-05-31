package de.dreiroeders.workingonimages;

public enum EColorI {
	
	Red(2),
	Green(1),
	Blue(0);
	
	private int mIndex;
	
	private EColorI(int index) {
		this.mIndex = index;
	}
	
	public int getIndex() {
		return mIndex;
	}
}
