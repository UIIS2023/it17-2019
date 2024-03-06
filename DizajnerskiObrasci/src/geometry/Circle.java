package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape implements Cloneable {

	private Point center;
	private int radius;
	
	public Circle() {

	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelectedShape(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setShapeColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius	 + 1, this.center.getY() - radius + 1, radius*2 - 2, radius*2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getShapeColor());
		g.drawOval(this.center.getX() - radius	, this.center.getY() - radius, radius*2, radius*2); 
		this.fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}
		
	}
	
	@Override 
	public Circle clone() {
		
		Circle circle = new Circle();
		circle.setCenter(this.getCenter().clone());
		try {
			circle.setRadius(this.getRadius());
		} catch (Exception e) {
			
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		
		circle.setSelectedShape(this.isSelected());
		circle.setShapeColor(this.getShapeColor());
		circle.setInnerColor(this.getInnerColor());

		return circle;
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public boolean equals(Object object) {
		
		if (object instanceof Circle) {
			
			Circle circle = (Circle) object;
			
			if (this.center.equals(circle.getCenter()) &&
					this.radius == circle.getRadius()) {
				
				return true;
				
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		return center.distance(x, y) <= radius;
	}
	
	public boolean contains(Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		
	}

	public String toString() {
		return "Circle:" + " center: "+ getCenter().getX() + " " +
	getCenter().getY()+" radius: "+getRadius()+" color: "+getShapeColor().getRGB() +" inner_color: "+getInnerColor().getRGB();
	}

}
