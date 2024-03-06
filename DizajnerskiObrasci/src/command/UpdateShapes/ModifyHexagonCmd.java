package command.UpdateShapes;

import adapter.HexagonAdapter;
import command.Command;

public class ModifyHexagonCmd implements Command {

	private HexagonAdapter oldValue;
	private HexagonAdapter newValue;
	private HexagonAdapter originalValue = new HexagonAdapter();

	public ModifyHexagonCmd(HexagonAdapter oldValue, HexagonAdapter newValue) {

		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public void execute() {
		originalValue = (HexagonAdapter) oldValue.clone();

		oldValue.getHexagon().setX(newValue.getHexagon().getX());
		oldValue.getHexagon().setY(newValue.getHexagon().getY());

		try {
			oldValue.getHexagon().setR(newValue.getHexagon().getR());
		} catch (Exception e) {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		oldValue.getHexagon().setSelected(newValue.getHexagon().isSelected());
		oldValue.getHexagon().setBorderColor(newValue.getHexagon().getBorderColor());
		oldValue.getHexagon().setAreaColor(newValue.getHexagon().getAreaColor());

	}

	@Override
	public void unexecute() {
		oldValue.getHexagon().setX(originalValue.getHexagon().getX());
		oldValue.getHexagon().setY(originalValue.getHexagon().getY());
		try {
			oldValue.getHexagon().setR(originalValue.getHexagon().getR());
		} catch (Exception e) {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		oldValue.getHexagon().setSelected(originalValue.getHexagon().isSelected());
		oldValue.getHexagon().setBorderColor(originalValue.getHexagon().getBorderColor());
		oldValue.getHexagon().setAreaColor(originalValue.getHexagon().getAreaColor());

	}

}
