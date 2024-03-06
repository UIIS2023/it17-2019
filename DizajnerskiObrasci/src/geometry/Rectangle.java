package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape {

	private Point upperLeftPoint;
	private int height;
	private int width;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int height, int width) {
		this.upperLeftPoint = upperLeftPoint;
		this.height = height;
		this.width = width;
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected) {
		this(upperLeftPoint, height, width);
		setSelectedShape(selected);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color) {
		this(upperLeftPoint, height, width, selected);
		setShapeColor(color);
	}

	public Rectangle(Point upperLeftPoint, int height, int width, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, height, width, selected, color);
		setInnerColor(innerColor);
	}

	@Override
	public int compareTo(Object object) {
		if (object instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) object).area());
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getShapeColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);

		this.fill(g);

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
			g.drawRect(getUpperLeftPoint().getX() + getWidth() - 3, getUpperLeftPoint().getY() + getHeight() - 3, 6, 6);
		}

	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.getUpperLeftPoint().getY() + 1, width - 1, height - 1);
	}

	@Override
	public Rectangle clone() {

		Rectangle rectangle = new Rectangle();

		rectangle.setUpperLeftPoint(this.upperLeftPoint.clone());
		rectangle.setHeight(this.getHeight());
		rectangle.setWidth(this.getWidth());
		rectangle.setSelectedShape(this.isSelected());
		rectangle.setShapeColor(this.getShapeColor());
		rectangle.setInnerColor(this.getInnerColor());

		return rectangle;
	}

	public double area() {
		return height * width;
	}

	public boolean equals(Object object) {

		if (object instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) object;
			if (this.upperLeftPoint.equals(rectangle.getUpperLeftPoint()) && this.width == rectangle.getWidth()
					&& this.height == rectangle.getHeight()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {

		if (this.getUpperLeftPoint().getX() <= x && this.getUpperLeftPoint().getY() <= y
				&& x <= this.getUpperLeftPoint().getX() + width && y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(Point point) {

		if (this.getUpperLeftPoint().getX() <= point.getX() && this.getUpperLeftPoint().getY() <= point.getY()
				&& point.getX() <= this.getUpperLeftPoint().getX() + width
				&& point.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String toString() {
		return "Rectangle: Upper_left_point " + getUpperLeftPoint().getX() + " " + getUpperLeftPoint().getY()
				+ " height: " + getHeight() + " width: " + getWidth() + " inner_color: " + getInnerColor().getRGB()
				+ " outer_color: " + getShapeColor().getRGB();
	}
}
