package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {

	private DrawingModel drawingModel = new DrawingModel();
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		Iterator<Shape> it = drawingModel.getShapes().iterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}

	}

	public DrawingModel getModel() {
		return drawingModel;
	}

	public void setModel(DrawingModel drawingModel) {
		this.drawingModel = drawingModel;
	}

}
