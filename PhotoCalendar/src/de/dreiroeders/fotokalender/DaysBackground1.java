package de.dreiroeders.fotokalender;

public abstract class DaysBackground1 implements IDaysBackground {

	public  float			  mHighBackgroudIcos = 1f;
	public  float			  mDeltaY = 0f;
	
	
	public DaysBackground1(float highBackgroudIcos, float deltaY) {
		super();
		this.mHighBackgroudIcos = highBackgroudIcos;
		this.mDeltaY = deltaY;
	}

}
