package de.dreiroeders.diag;

public class Diag2 {

	private static DiagFewRepeat sDoer = null;
	
	public static void OutIf11(String strMainMsg, Object... paras) {
		if (sDoer == null) {
			sDoer = new DiagFewRepeat(System.out);
		}
		sDoer.OutIf11(strMainMsg, paras);
	}
}
