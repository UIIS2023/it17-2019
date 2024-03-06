 package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {

		DrawingModel drawingModel = new DrawingModel();
		DrawingFrame drawingFrame = new DrawingFrame();
		drawingFrame.getView().setModel(drawingModel);
		DrawingController controller = new DrawingController(drawingModel, drawingFrame);
		drawingFrame.setController(controller);

		drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawingFrame.setVisible(true);

	}

}