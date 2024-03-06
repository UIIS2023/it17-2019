package command.UpdateShapes;

import command.Command;
import geometry.Rectangle;

public class ModifyRectangleCmd implements Command {

	private Rectangle oldValue;
	private Rectangle newValue;
	private Rectangle originalValue;

	public ModifyRectangleCmd(Rectangle oldValue, Rectangle newValue) {

		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		originalValue = oldValue.clone();

		oldValue.getUpperLeftPoint().setX(newValue.getUpperLeftPoint().getX());
		oldValue.getUpperLeftPoint().setY(newValue.getUpperLeftPoint().getY());
		oldValue.setHeight(newValue.getHeight());
		oldValue.setWidth(newValue.getWidth());
		oldValue.setSelectedShape(newValue.isSelected());
		oldValue.setShapeColor(newValue.getShapeColor());
		oldValue.setInnerColor(newValue.getInnerColor());

	}

	@Override
	public void unexecute() {
		oldValue.getUpperLeftPoint().setX(originalValue.getUpperLeftPoint().getX());
		oldValue.getUpperLeftPoint().setY(originalValue.getUpperLeftPoint().getY());
		oldValue.setHeight(originalValue.getHeight());
		oldValue.setWidth(originalValue.getWidth());
		oldValue.setSelectedShape(originalValue.isSelected());
		oldValue.setShapeColor(originalValue.getShapeColor());
		oldValue.setInnerColor(originalValue.getInnerColor());

	}

}
