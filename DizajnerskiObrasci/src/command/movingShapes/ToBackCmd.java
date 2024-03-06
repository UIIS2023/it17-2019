package command.movingShapes;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {

	private Shape shape;
	private DrawingModel drawingModel;
	private int index;

	public ToBackCmd(Shape shape, DrawingModel drawingModel) {
		this.shape = shape;
		this.drawingModel = drawingModel;
		this.index = drawingModel.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if (isValidIndex()) {
			drawingModel.getShapes().remove(index);
			drawingModel.addOnIndex(index - 1, shape);
		}
	}

	@Override
	public void unexecute() {
		if (isValidIndex()) {
			drawingModel.getShapes().remove(index - 1);
			drawingModel.addOnIndex(index, shape);
		}
	}

	private boolean isValidIndex() {
		return index >= 1 && index < drawingModel.getShapes().size();
	}
}
