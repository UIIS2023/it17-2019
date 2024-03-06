package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelectedShape(selected);
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setShapeColor(color);
	}

	@Override
	public int compareTo(Object object) {
		
		if (object instanceof Line) {
			return (int) (this.length() - ((Line) object).length());
		}
		return 0;
		
	}

	@Override
	public void moveBy(int byX, int byY) {
		
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
		
		
	}

	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getShapeColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());

		if (this.isSelected()) {
			g.setColor(Color.blue);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		}

	}

	@Override
	public Line clone() {
		
		Line line = new Line();

		line.setStartPoint(this.getStartPoint().clone());
		line.setEndPoint(this.getEndPoint().clone());
		line.setShapeColor(this.getShapeColor());

		return line;
	}

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	public boolean equals(Object object) {
		if (object instanceof Line) {
			Line temp = (Line) object;
			if (this.startPoint.equals(temp.getStartPoint()) && this.endPoint.equals(temp.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		
		if ((startPoint.distance(x, y) + endPoint.distance(x, y)) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		return "Line: "  + "start_point: " + getStartPoint().getX() +" "+  getStartPoint().getY() 
		+ " " + "end_point: " +
		+ getEndPoint().getX() + " " + getEndPoint().getY() +" color: " + getShapeColor().getRGB();
	}

}
