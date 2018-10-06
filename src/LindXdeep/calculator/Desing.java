package LindXdeep.calculator;

import java.awt.Color;

public class Desing {
	
	Color colorBack = null;
	Color colorFocus = null;
	Color colorClick = null;
	
	private Desing() {
		colorBack = new Color(33, 33, 33);
		colorFocus = new Color(65, 65, 65);
		colorClick = new Color(95, 95, 95);
	}
	
	public static Desing inut() {
		return new Desing();
	}
	public Color getColorBack() {
		return colorBack;
	}
	public Color getColorFocus() {
		return colorFocus;
	}
	public Color getColorClick() {
		return colorClick;
	}
}
