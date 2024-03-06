
package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
	private PropertyChangeSupport propertyChangeSupport;

	public DrawingModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void addListener(PropertyChangeListener propertyChangeListener) {
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}

	public void removeListener(PropertyChangeListener propertyChangeListener) {

		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}

	public void add(Shape s) {
		shapes.add(s);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(s));

	}

	public void remove(Shape shape) {
		propertyChangeSupport.firePropertyChange("Index", getShapes().size() - 1, getShapes().indexOf(shape));
		shapes.remove(shape);
	}

	public void addSelected(Shape shape) {
		propertyChangeSupport.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size() + 1);
		selectedShapes.add(shape);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size(), getShapes().indexOf(shape));

	}

	public void removeSelected(Shape shape) {
		propertyChangeSupport.firePropertyChange("Size", this.selectedShapes.size(), this.selectedShapes.size() - 1);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size() - 1, getShapes().indexOf(shape));
		selectedShapes.remove(shape);
	}

	public void addOnIndex(int index, Shape shape) {

		shapes.add(index, shape);
		propertyChangeSupport.firePropertyChange("Index", getShapes().size(), index);
	}

	public Shape get(int index) {
		return shapes.get(index);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}

	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;

	}

}
