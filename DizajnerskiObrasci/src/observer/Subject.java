package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Subject {
	
	private int size;
	private PropertyChangeSupport propertyChangeSupport; 
	
	
	public Subject() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		
		propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
		
		propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		propertyChangeSupport.firePropertyChange("Size", this.size, size);
		this.size = size;
	}

	public PropertyChangeSupport getProperyChangeSupport() {
		return propertyChangeSupport;
	}

	public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
		this.propertyChangeSupport = propertyChangeSupport;
	}

	
	
}
