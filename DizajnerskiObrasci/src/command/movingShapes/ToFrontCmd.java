package command.movingShapes;

import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	private DrawingModel drawingModel;
    private Shape shape;
    private int index;

    public ToFrontCmd(Shape shape, DrawingModel drawingModel) {
        this.shape = shape;
        this.drawingModel = drawingModel;
        this.index = drawingModel.getShapes().indexOf(shape);
    }

    @Override
    public void execute() {
        try {
            if (isValidIndex()) {
            	drawingModel.getShapes().remove(index);
            	drawingModel.addOnIndex(index + 1, shape);
            } else {
                System.out.println("Operacija ToFront nije izvršena.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error happened!");
            e.printStackTrace();
        }
    }

    @Override
    public void unexecute() {
        try {
            if (isValidIndex()) {
            	drawingModel.getShapes().remove(index + 1);
            	drawingModel.addOnIndex(index, shape);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error happend!");
            e.printStackTrace();
        }
    }

    private boolean isValidIndex() {
        return index >= 0 && index < drawingModel.getShapes().size() - 1;
    }
}
