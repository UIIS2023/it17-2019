package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class SelectedShapesListObserver implements PropertyChangeListener {

	private DrawingFrame frame;
	private int index;
	private int size;
	private int sizeOfShapes;

	public SelectedShapesListObserver(DrawingFrame frame) {
		this.frame = frame;
	}

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		
		if (propertyChangeEvent.getPropertyName().equals("Size")) {
			
			this.size = (int) propertyChangeEvent.getNewValue();

		}
		if (propertyChangeEvent.getPropertyName().equals("Index")) {
			
			this.index = (int) propertyChangeEvent.getNewValue();
			this.sizeOfShapes = (int) propertyChangeEvent.getOldValue();

		}

		enableButtons();

	}

	public void enableButtons() {

		
		if (size == 0) {
			
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(false);

		} else if (size == 1) {

			frame.getBtnModify().setEnabled(true);
			frame.getBtnDelete().setEnabled(true);

			if (index > 0) {	
				
				frame.getBtnBringToBack().setEnabled(true);
				frame.getBtnToBack().setEnabled(true);
			} else {
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
			}

			if (index < sizeOfShapes - 1) {
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnToFront().setEnabled(true);
			} else {
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
			}

		} else if (size > 1) {
			
			frame.getBtnModify().setEnabled(false);
			frame.getBtnDelete().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);

		}

	}

}
