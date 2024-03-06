package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean newSelected) {
		this(x, y);
		setSelectedShape(newSelected);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x, y, selected);
		setShapeColor(color);
	}

	@Override
	public int compareTo(Object object) {
		if (object instanceof Point) {
			Point p = new Point(0, 0);
			return (int) (this.distance(p.getX(), p.getY()) - ((Point) object).distance(p.getX(), p.getY()));
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x = this.x + byX;
		this.y += byY;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getShapeColor());
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.x - 3, this.y - 3, 6, 6);
		}
		
	}
	
	@Override
	public Point clone() {
		Point point = new Point();
		
		point.setX(this.getX());
		point.setY(this.getY());
		point.setShapeColor(this.getShapeColor());

		return point;
	}
	
	public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx*dx + dy * dy);
		return d;
	}
	
	public boolean equals(Object object) {
		
		if (object instanceof Point) {
			Point point = (Point) object;
			if (this.x == point.getX() && this.y == point.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}
	
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "Point: " + getX()+" "+getY()+" "+ "color: " + getShapeColor().getRGB();
	}



}
