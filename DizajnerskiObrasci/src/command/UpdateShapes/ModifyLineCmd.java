package command.UpdateShapes;

import command.Command;
import geometry.Line;

public class ModifyLineCmd implements Command {

	private Line oldValue;
	private Line newValue;
	private Line originalValue;

	public ModifyLineCmd(Line oldValue, Line newValue) {

		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		originalValue = oldValue.clone();

		oldValue.getStartPoint().setX(newValue.getStartPoint().getX());
		oldValue.getStartPoint().setY(newValue.getStartPoint().getY());
		oldValue.getEndPoint().setX(newValue.getEndPoint().getX());
		oldValue.getEndPoint().setY(newValue.getEndPoint().getY());
		oldValue.setShapeColor(newValue.getShapeColor());
	}

	@Override
	public void unexecute() {

		oldValue.getStartPoint().setX(originalValue.getStartPoint().getX());
		oldValue.getStartPoint().setY(originalValue.getStartPoint().getY());
		oldValue.getEndPoint().setX(originalValue.getEndPoint().getX());
		oldValue.getEndPoint().setY(originalValue.getEndPoint().getY());
		oldValue.setShapeColor(originalValue.getShapeColor());
	}

}
