package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Cloneable, Serializable {

	private boolean selectedShape;
	private Color color = Color.black;
	
	public abstract boolean contains(int x, int y);
	public abstract void draw(Graphics g);
	protected abstract Shape clone();

	public boolean isSelected() {
		return selectedShape;
	}

	public void setSelectedShape(boolean selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Color getShapeColor() {
		return color;
	}

	public void setShapeColor(Color color) {
		this.color = color;
	}
	
	
	
}