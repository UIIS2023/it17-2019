package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle implements Cloneable {

	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelectedShape(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setShapeColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}
	
	public void draw(Graphics g) {
		
		Graphics2D graphics = (Graphics2D) g; 
		
		Ellipse2D outer = new Ellipse2D.Double(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(), this.getRadius()*2, this.getRadius()*2); //jer je poluprecnik pa *2
		Ellipse2D inner = new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius() , this.getCenter().getY() - this.getInnerRadius(), this.innerRadius * 2, this.innerRadius * 2);
		
		Area area = new Area(outer);
		Area innerArea = new Area(inner);
		area.subtract(innerArea);
		
		graphics.setColor(getInnerColor());
		graphics.fill(area);
		graphics.setColor(getShapeColor());
		graphics.draw(area);
		
		if (isSelected()) {
			graphics.setColor(Color.BLUE);
			graphics.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics.drawRect(this.getCenter().getX() - this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics.drawRect(this.getCenter().getX() + this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			graphics.drawRect(this.getCenter().getX()- 3, this.getCenter().getY() - this.getRadius()- 3, 6, 6);
			graphics.drawRect(this.getCenter().getX()- 3,this.getCenter().getY()+ this.getRadius()- 3, 6, 6);
		}
		
		
	}
	
	@Override 
	public Donut clone() {
		Donut d = new Donut(); 
		
		d.setCenter(this.getCenter().clone());
		
		try {
			d.setRadius(this.getRadius());
		} catch (Exception e) {
			
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		
		d.setInnerRadius(this.getInnerRadius());
		d.setSelectedShape(this.isSelected());
		d.setShapeColor(this.getShapeColor());
		d.setInnerColor(this.getInnerColor());

		return d;
	}
	
	public int compareTo(Object object) {
		if (object instanceof Donut) {
			return (int) (this.area() - ((Donut) object).area());
		}
		return 0;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object object) {
		if (object instanceof Donut) {
			Donut donut = (Donut) object;
			if (this.getCenter().equals(donut.getCenter()) &&
					this.getRadius() == donut.getRadius() &&
					this.innerRadius == donut.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double distanceFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && distanceFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double distanceFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && distanceFromCenter > innerRadius;
	}
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return "Donut: " + super.toString() + " inner_radius: " + getInnerRadius();
	}
	
}
