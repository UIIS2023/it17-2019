package command.UpdateShapes;

import java.util.ArrayList;
import command.Command;
import geometry.Shape;
import mvc.DrawingModel;

public class DeleteShapeCmd implements Command {

    private ArrayList<Shape> shapes;
    private DrawingModel drawingModel;
    private ArrayList<Shape> currentShapesList = new ArrayList<Shape>();

    public DeleteShapeCmd(ArrayList<Shape> shapes, DrawingModel drawingModel) {
        this.shapes = new ArrayList<Shape>(shapes);
        this.drawingModel = drawingModel;
    }

    @Override
    public void execute() {
        try {
            currentShapesList.addAll(drawingModel.getShapes());
            for (Shape shape : shapes) {
            	drawingModel.remove(shape);
            	drawingModel.removeSelected(shape);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void unexecute() {
        try {
        	drawingModel.getShapes().clear();
        	drawingModel.getShapes().addAll(currentShapesList);
            for (Shape shape : shapes) {
                shape.setSelectedShape(true);
                drawingModel.addSelected(shape);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
    }
}