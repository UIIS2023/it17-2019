package command.movingShapes;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {

	private Shape shape;
	private DrawingModel drawingModel;
	private int index;

	public BringToFrontCmd(Shape shape, DrawingModel drawingModel) {
		this.shape = shape;
		this.drawingModel = drawingModel;
		this.index = drawingModel.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		try {
			drawingModel.remove(shape);
			drawingModel.add(shape);
		} catch (Exception e) {
			System.out.println("An unexpected error occurred in BringToFrontCmd execution: " + e.getMessage());
		}
	}

	@Override
	public void unexecute() {
		try {
			if (drawingModel.getShapes().size() > 0) {
				drawingModel.getShapes().remove(drawingModel.getShapes().size() - 1);
				drawingModel.addOnIndex(index, shape);
			} else {
				System.out.println("Invalid state for BringToFrontCmd unexecution: DrawingModel is empty.");
			}
		} catch (Exception e) {
			System.out.println("An unexpected error occurred in BringToFrontCmd unexecution: " + e.getMessage());
		}
	}
}
