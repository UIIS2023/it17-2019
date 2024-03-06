
package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Donut;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	private Hexagon hexagon = new Hexagon(0,0,0);

	public HexagonAdapter() {

	}

	public HexagonAdapter(Point point) {
		hexagon.setX(point.getX());
		hexagon.setY(point.getY());
	}

	public HexagonAdapter(Point point, int radius) {
		hexagon.setX(point.getX());
		hexagon.setY(point.getY());
		hexagon.setR(radius);

	}

	public HexagonAdapter(Point point, int radius, boolean selected, Color outlineColor, Color innerColor) {
		this.hexagon = new Hexagon(point.getX(), point.getY(), radius);
		this.hexagon.setBorderColor(outlineColor);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setSelected(selected);
	}

	public HexagonAdapter(Point point, int radius, Color outlineColor, Color innerColor) {

		this.hexagon = new Hexagon(point.getX(), point.getY(), radius);
		this.hexagon.setBorderColor(outlineColor);
		this.hexagon.setAreaColor(innerColor);

	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(byX);
		this.hexagon.setY(byY);
	}
	
	
	public HexagonAdapter clone () {
		HexagonAdapter hexagon = new HexagonAdapter(); 
		
		hexagon.hexagon.setX(this.hexagon.getX());
		hexagon.hexagon.setY(this.hexagon.getY());

				try {
					hexagon.hexagon.setR(this.hexagon.getR());
				} catch (Exception e) {
					throw new NumberFormatException("Radius has to be a value greater then 0!");
				}
				
				hexagon.hexagon.setSelected(this.hexagon.isSelected());
				hexagon.hexagon.setBorderColor(this.hexagon.getBorderColor());
				hexagon.hexagon.setAreaColor(this.hexagon.getAreaColor());

		return hexagon;
	}
	

	@Override
	public int compareTo(Object object) {
		if (object instanceof Hexagon) {
			return (this.hexagon.getR() - ((Hexagon) object).getR());
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.hexagon.getX() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() - this.hexagon.getR() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX() + this.hexagon.getR() - 3, this.hexagon.getY() - 3, 6, 6);
			g.drawRect(this.hexagon.getX()- 3, this.hexagon.getY() - this.hexagon.getR()- 3, 6, 6);
			g.drawRect(this.hexagon.getX()- 3,this.hexagon.getY()+ this.hexagon.getR()- 3, 6, 6);
		}
		
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	

	public String toString() {
		return "Hexagon: center: " + getHexagon().getX() + " " + getHexagon().getY() + " radius: " + getHexagon().getR()
				+ " area_color: " + getHexagon().getAreaColor().getRGB() + " line_color: "
				+ getHexagon().getBorderColor().getRGB();
	}


}
