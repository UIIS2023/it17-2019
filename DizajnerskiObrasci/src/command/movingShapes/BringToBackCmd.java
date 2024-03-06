package command.movingShapes;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

	private Shape shape;
	private DrawingModel drawingModel;
	private int index;

	public BringToBackCmd(Shape shape, DrawingModel drawingModel) {
		this.index = drawingModel.getShapes().indexOf(shape);
		this.shape = shape;
		this.drawingModel = drawingModel;
	}

	@Override
	public void execute() {
		
		try {
			if (isValidIndexForBringToBack()) {
				drawingModel.getShapes().remove(index);
				drawingModel.addOnIndex(0, shape);
			} else {
				System.out.println("Invalid index for BringToBackCmd execution.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException in BringToBackCmd execution: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred in BringToBackCmd execution: " + e.getMessage());
		}
	}

	@Override
	public void unexecute() {
		try {
			if (isValidIndexForBringToBackUndo()) {
				drawingModel.getShapes().remove(0);
				drawingModel.addOnIndex(index, shape);
			} else {
				System.out.println("Invalid index for BringToBackCmd unexecution.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException in BringToBackCmd unexecution: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred in BringToBackCmd unexecution: " + e.getMessage());
		}
	}

	private boolean isValidIndexForBringToBack() {
		return index >= 0 && index < drawingModel.getShapes().size();
	}

	private boolean isValidIndexForBringToBackUndo() {
		return index >= 0 && index < drawingModel.getShapes().size() - 1;
	}
}
