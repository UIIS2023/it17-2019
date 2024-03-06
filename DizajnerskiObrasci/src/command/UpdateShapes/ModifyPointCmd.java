package command.UpdateShapes;

import command.Command;
import geometry.Point;

public class ModifyPointCmd implements Command {

	private Point oldValue;
	private Point newValue;
	private Point originalValue;

	public ModifyPointCmd(Point oldValue, Point newValue) {
		this.oldValue = oldValue;
		this.newValue = newValue;

	}

	@Override
	public void execute() {

		originalValue = oldValue.clone();

		oldValue.setX(newValue.getX());
		oldValue.setY(newValue.getY());
		oldValue.setShapeColor(newValue.getShapeColor());
	}

	@Override
	public void unexecute() {
		oldValue.setX(originalValue.getX());
		oldValue.setY(originalValue.getY());
		oldValue.setShapeColor(originalValue.getShapeColor());

	}

}
