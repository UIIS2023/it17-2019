package command.UpdateShapes;

import command.Command;
import geometry.Circle;

public class ModifyCircleCmd implements Command {

	private Circle oldValue;
	private Circle newValue;
	private Circle originalValue;

	public ModifyCircleCmd(Circle oldValue, Circle newValue) {

		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		originalValue = oldValue.clone();

		oldValue.getCenter().setX(newValue.getCenter().getX());
		oldValue.getCenter().setY(newValue.getCenter().getY());

		try {
			oldValue.setRadius(newValue.getRadius());
		} catch (Exception e) {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}

		oldValue.setSelectedShape(newValue.isSelected());
		oldValue.setShapeColor(newValue.getShapeColor());
		oldValue.setInnerColor(newValue.getInnerColor());

	}

	@Override
	public void unexecute() {

		oldValue.getCenter().setX(originalValue.getCenter().getX());
		oldValue.getCenter().setY(originalValue.getCenter().getY());

		try {
			oldValue.setRadius(originalValue.getRadius());
		} catch (Exception e) {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}

		oldValue.setSelectedShape(originalValue.isSelected());
		oldValue.setShapeColor(originalValue.getShapeColor());
		oldValue.setInnerColor(originalValue.getInnerColor());

	}

}
