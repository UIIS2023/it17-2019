package mvc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import adapter.HexagonAdapter;
import command.Command;
import command.UpdateShapes.AddShapeCmd;
import command.UpdateShapes.DeleteShapeCmd;
import command.UpdateShapes.ModifyCircleCmd;
import command.UpdateShapes.ModifyDonutCmd;
import command.UpdateShapes.ModifyHexagonCmd;
import command.UpdateShapes.ModifyLineCmd;
import command.UpdateShapes.ModifyPointCmd;
import command.UpdateShapes.ModifyRectangleCmd;
import dialogs.DialogCircle;
import dialogs.DialogDonut;
import dialogs.DialogHexagon;
import dialogs.DialogLine;
import dialogs.DialogPoint;
import dialogs.DialogRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import observer.*;
import strategy.Save;
import strategy.SaveLog;
import strategy.SaveDraw;
import strategy.SaveManager;

public class DrawingController {
	private DrawingModel drawingModel;
	private DrawingFrame frame;
	private Color innerColor;
	private Color outlineColor;

	private Shape selectedShape;
	private Point startPoint;

	private Stack<Command> activityStack = new Stack<Command>();
	private Stack<Command> undoStack = new Stack<Command>();
	private SelectedShapesListObserver observer;

	public DrawingController(DrawingModel drawingModel, DrawingFrame frame) {
		this.frame = frame;
		this.drawingModel = drawingModel;
		observer = new SelectedShapesListObserver(frame);
		drawingModel.addListener(observer);
	}

	public void mouseClicked(MouseEvent mouse) {

		Shape newShape = null;
		Point click = new Point(mouse.getX(), mouse.getY());

		if (frame.getTglBtnSelect().isSelected()) {
			selectedShape = null;
			Iterator<Shape> i = drawingModel.getShapes().iterator();

			while (i.hasNext()) {
				Shape shape = i.next();
				if (shape.contains(click.getX(), click.getY()))

					selectedShape = shape;

			}

			if (selectedShape != null) {

				if (!selectedShape.isSelected()) {

					selectedShape.setSelectedShape(true);
					drawingModel.addSelected(selectedShape);
					frame.getTextArea().append("Select: " + selectedShape.toString() + "\n");

				} else {

					selectedShape.setSelectedShape(false);
					drawingModel.removeSelected(selectedShape);
					frame.getTextArea().append("Deselect: " + selectedShape.toString() + "\n");

				}
			}

		} else if (frame.getTglBtnPoint().isSelected()) {

			newShape = new Point(click.getX(), click.getY(), false, Color.black);

		} else if (frame.getTglBtnLine().isSelected()) {

			if (startPoint == null)
				startPoint = click;
			else {
				newShape = new Line(startPoint, new Point(mouse.getX(), mouse.getY(), false, Color.black));
				startPoint = null;
			}

		} else if (frame.getTglBtnCircle().isSelected()) {

			DialogCircle dialog = new DialogCircle();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);

			if (outlineColor != null)
				dialog.getBtnOutlineColor().setBackground(outlineColor);
			dialog.setVisible(true);

			if (dialog.isOK()) {
				newShape = dialog.getCircle();
			}

		} else if (frame.getTglBtnDonut().isSelected()) {
			DialogDonut dialog = new DialogDonut();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);
			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outlineColor != null)
				dialog.getBtnOutlineColor().setBackground(outlineColor);
			dialog.setVisible(true);

			if (dialog.isOK()) {

				newShape = dialog.getDonut();
			}
		} else if (frame.getTglBtnRectangle().isSelected()) {

			DialogRectangle dialog = new DialogRectangle();
			dialog.setModal(true);
			dialog.getTxtX().setText("" + Integer.toString(mouse.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(mouse.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);

			if (outlineColor != null)
				dialog.getBtnOutlineColor().setBackground(outlineColor);
			dialog.setVisible(true);

			if (dialog.isOK()) {

				try {
					newShape = dialog.getRectangle();
				} catch (Exception exc) {

					JOptionPane.showMessageDialog(frame, "Wrong data type", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (frame.getTglBtnHexagon().isSelected()) {

			DialogHexagon dialog = new DialogHexagon();

			dialog.getTxtX().setText("" + Integer.toString(click.getX()));
			dialog.getTxtX().setEditable(false);
			dialog.getTxtY().setText("" + Integer.toString(click.getY()));
			dialog.getTxtY().setEditable(false);

			if (innerColor != null)
				dialog.getBtnInnerColor().setBackground(innerColor);
			if (outlineColor != null)
				dialog.getBtnOutlineColor().setBackground(outlineColor);
			dialog.setVisible(true);
			if (dialog.isOK()) {
				newShape = dialog.getHexagon();
			}

		}

		if (newShape != null) {

			frame.getTextArea().append("Add: " + (newShape.toString() + "\n"));

			Command command = new AddShapeCmd((Shape) newShape, drawingModel);
			command.execute();
			activityStack.push(command);

		}

		if (!getActivityStack().isEmpty()) {
			frame.getBtnUndo().setEnabled(true);
		}

		clearRedo();
		frame.getView().repaint();

	}

	// Clear redo
	public void clearRedo() {
		getUndoStack().clear();
		frame.getBtnRedo().setEnabled(false);
	}

	public void modify() {
		if (drawingModel.getSelectedShapes().get(0) != null) {
			modifyShape();
		} else {
			JOptionPane.showMessageDialog(null, "Please, select what you want to modify!", "Error",
					JOptionPane.ERROR_MESSAGE);
			frame.getTglBtnSelect().setSelected(true);
		}

		frame.getTglBtnSelect().setSelected(true);
	}

	private void modifyShape() {
		Shape selectedShape = drawingModel.getSelectedShapes().get(0);
		Command command;

		if (selectedShape != null) {
			if (selectedShape instanceof Point) {
				modifyPoint((Point) selectedShape);
			} else if (selectedShape instanceof HexagonAdapter) {
				modifyHexagon((HexagonAdapter) selectedShape);
			} else if (selectedShape instanceof Donut) {
				modifyDonut((Donut) selectedShape);
			} else if (selectedShape instanceof Circle && !(selectedShape instanceof Donut)) {
				modifyCircle((Circle) selectedShape);
			} else if (selectedShape instanceof Line) {
				modifyLine((Line) selectedShape);
			} else if (selectedShape instanceof Rectangle) {
				modifyRectangle((Rectangle) selectedShape);
			}
		}

		clearRedo();
		drawingModel.getSelectedShapes().get(0).setSelectedShape(true);
		frame.getView().repaint();
	}

	private void modifyPoint(Point point) {
		DialogPoint dialog = new DialogPoint();

		dialog.getTxtX().setText("" + Integer.toString(point.getX()));
		dialog.getTxtY().setText("" + Integer.toString(point.getY()));
		dialog.getBtnColor().setBackground(point.getShapeColor());
		dialog.setVisible(true);

		// setCommonFields(point, dialog);
		if (dialog.isOK()) {
			frame.getTextArea().append("Modify: " + ((Point) dialog.getP()).toString() + "\n");
			ModifyPointCmd command = new ModifyPointCmd(point, dialog.getP());
			command.execute();
			activityStack.push(command);
		}
	}

	private void modifyHexagon(HexagonAdapter hexagon) {

		DialogHexagon dialog = new DialogHexagon();

		dialog.getTxtX().setText("" + Integer.toString(hexagon.getHexagon().getX()));
		dialog.getTxtY().setText("" + Integer.toString(hexagon.getHexagon().getY()));
		dialog.getTxtR().setText("" + Integer.toString(hexagon.getHexagon().getR()));
		dialog.getBtnInnerColor().setBackground(hexagon.getHexagon().getAreaColor());
		dialog.getBtnOutlineColor().setBackground(hexagon.getHexagon().getBorderColor());

		dialog.setVisible(true);

		if (dialog.isOK()) {
			frame.getTextArea().append("Modify: " + ((HexagonAdapter) dialog.getHexagon()).toString() + "\n");
			ModifyHexagonCmd command = new command.UpdateShapes.ModifyHexagonCmd(hexagon, dialog.getHexagon());
			command.execute();
			activityStack.push(command);
		}
	}

	private void modifyLine(Line line) {
		DialogLine dialog = new DialogLine();

		dialog.getTxtSPX().setText(String.valueOf(line.getStartPoint().getX()));
		dialog.getTxtSPY().setText(String.valueOf(line.getStartPoint().getY()));
		dialog.getTxtEPX().setText(String.valueOf(line.getEndPoint().getX()));
		dialog.getTxtEPY().setText(String.valueOf(line.getEndPoint().getY()));
		dialog.setVisible(true);

		if (dialog.isOK()) {
			frame.getTextArea().append("Modify: " + ((Line) dialog.getLine()).toString() + "\n");
			ModifyLineCmd command = new command.UpdateShapes.ModifyLineCmd(line, dialog.getLine());
			command.execute();
			activityStack.push(command);
		}
	}

	private void modifyCircle(Circle circle) {
		DialogCircle dialog = new DialogCircle();

		dialog.getTxtX().setText(String.valueOf(circle.getCenter().getX()));
		dialog.getTxtY().setText(String.valueOf(circle.getCenter().getY()));
		dialog.getTxtR().setText(String.valueOf(circle.getRadius()));
		dialog.getBtnInnerColor().setBackground(circle.getInnerColor());
		dialog.getBtnOutlineColor().setBackground(circle.getShapeColor());
		dialog.setVisible(true);

		if (dialog.isOK()) {
			frame.getTextArea().append("Modify: " + ((Circle) dialog.getCircle()).toString() + "\n");
			ModifyCircleCmd command = new ModifyCircleCmd(circle, dialog.getCircle());
			command.execute();
			activityStack.push(command);
		}
	}

	private void modifyDonut(Donut donut) {
		DialogDonut dialog = new DialogDonut();

		dialog.getTxtX().setText(String.valueOf(donut.getCenter().getX()));
		dialog.getTxtY().setText(String.valueOf(donut.getCenter().getY()));
		dialog.getTxtR().setText(String.valueOf(donut.getRadius()));
		dialog.getTxtInnerR().setText(String.valueOf(donut.getInnerRadius()));
		dialog.getBtnInnerColor().setBackground(donut.getInnerColor());
		dialog.getBtnOutlineColor().setBackground(donut.getShapeColor());
		dialog.setVisible(true);

		if (dialog.isOK()) {
			frame.getTextArea().append("Modify: " + ((Donut) dialog.getDonut()).toString() + "\n");
			ModifyDonutCmd command = new command.UpdateShapes.ModifyDonutCmd(donut, dialog.getDonut());
			command.execute();
			activityStack.push(command);
		}
	}

	private void modifyRectangle(Rectangle rectangle) {
		DialogRectangle dialog = new DialogRectangle();

		dialog.getTxtX().setText(String.valueOf(rectangle.getUpperLeftPoint().getX()));
		dialog.getTxtY().setText(String.valueOf(rectangle.getUpperLeftPoint().getY()));
		dialog.getTxtHeight().setText(String.valueOf(rectangle.getHeight()));
		dialog.getTxtWidth().setText(String.valueOf(rectangle.getWidth()));
		dialog.getBtnInnerColor().setBackground(rectangle.getInnerColor());
		dialog.getBtnOutlineColor().setBackground(rectangle.getShapeColor());
		dialog.setVisible(true);

		if (dialog.isOK()) {
			frame.getTextArea().append("Modify: " + ((Rectangle) dialog.getRectangle()).toString() + "\n");
			ModifyRectangleCmd command = new command.UpdateShapes.ModifyRectangleCmd(rectangle, dialog.getRectangle());
			command.execute();
			activityStack.push(command);
		}
	}

	// Delete

	public void delete() {

		try {
			if (drawingModel.getSelectedShapes() != null) {
				int option = JOptionPane.showConfirmDialog(null, "Please confirm deletion", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					frame.getTextArea().append("Delete: " + drawingModel.getSelectedShapes().toString() + "\n");

					Command command = new DeleteShapeCmd(drawingModel.getSelectedShapes(), drawingModel);
					command.execute();
					activityStack.push(command);
				}
			} else {
				JOptionPane.showMessageDialog(null, "You haven't selected any shape!", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

			this.setSelectedShape(null);
			clearRedo();
			frame.getView().repaint();
			frame.getTglBtnSelect().setSelected(false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occurred while deleting shape.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Redo
	public void redo() {
		if (undoStack.isEmpty()) {
			return; // Ništa za redo
		}

		Command command = undoStack.pop();
		command.execute();
		activityStack.push(command);

		updateUndoRedoButtons();

		frame.getTextArea().append("REDO \n");
		frame.getView().repaint();
	}

	// Undo
	public void undo() {
		if (activityStack.isEmpty()) {
			return; // Ništa za undo
		}

		Command command = activityStack.pop();
		command.unexecute();
		undoStack.push(command);

		updateUndoRedoButtons();

		frame.getTextArea().append("Undo \n");
		frame.getView().repaint();
	}

	private void updateUndoRedoButtons() {
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnRedo().setEnabled(!undoStack.isEmpty());
	}

	// To Back
	public void toBack() {

		List<Shape> selectedShapes = drawingModel.getSelectedShapes();

		if (selectedShapes.isEmpty()) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);

		Command cmd = new command.movingShapes.ToBackCmd(selectedShape, drawingModel);
		cmd.execute();

		activityStack.add(cmd);
		clearRedo();

		frame.getTextArea().append("ToBack: " + selectedShape.toString() + "\n");
		frame.getView().repaint();
	}

	// To Front
	public void toFront() {
		List<Shape> selectedShapes = drawingModel.getSelectedShapes();

		if (selectedShapes.isEmpty()) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);

		Command cmd = new command.movingShapes.ToFrontCmd(selectedShape, drawingModel);
		cmd.execute();

		activityStack.add(cmd);
		clearRedo();

		frame.getTextArea().append("ToFront: " + selectedShape.toString() + "\n");
		frame.getView().repaint();
	}

	// Bring To Front
	public void bringToFront() {
		List<Shape> selectedShapes = drawingModel.getSelectedShapes();

		if (selectedShapes.isEmpty()) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);

		Command cmd = new command.movingShapes.BringToFrontCmd(selectedShape, drawingModel);
		cmd.execute();

		activityStack.add(cmd);
		clearRedo();

		frame.getTextArea().append("BringToFront: " + selectedShape.toString() + "\n");
		frame.getView().repaint();
	}

	// Bring to Back
	public void bringToBack() {
		List<Shape> selectedShapes = drawingModel.getSelectedShapes();

		if (selectedShapes.isEmpty()) {
			return;
		}

		Shape selectedShape = selectedShapes.get(0);

		Command cmd = new command.movingShapes.BringToBackCmd(selectedShape, drawingModel);
		cmd.execute();

		activityStack.add(cmd);
		clearRedo();

		frame.getTextArea().append("BringToBack: " + selectedShape.toString() + "\n");
		frame.getView().repaint();
	}

	public void activeInnerColor() {

		innerColor = JColorChooser.showDialog(null, "Choose inner color", frame.getTglbtnInnerColor().getBackground());
		if (innerColor != null)
			frame.getTglbtnInnerColor().setBackground(innerColor);

	}

	public void activeOutlineColor() {
		outlineColor = JColorChooser.showDialog(null, "Choose outline color",
				frame.getTglBtnOutlineColor().getBackground());
		if (outlineColor != null)
			frame.getTglBtnOutlineColor().setBackground(outlineColor);
	}

	//Save
	public void save(int option) {

		if (option == JOptionPane.YES_OPTION) {
			SaveManager saveManager = new SaveManager(new SaveLog(frame));
			saveManager.saveData();
		} else if (option == JOptionPane.NO_OPTION) {
			SaveManager drawingManager = new SaveManager(new SaveDraw(drawingModel.getShapes()));
			drawingManager.saveData();
		}

	}

	//Open Drawing
	public void OpenDrawing() {

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dlg = jFileChooser.showOpenDialog(frame);
		File file = jFileChooser.getSelectedFile();

		if (dlg == JFileChooser.APPROVE_OPTION) {

			clearAll();
			frame.getTextArea().setText(" ");

			try {

				FileInputStream fileInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				ArrayList<Shape> list = new ArrayList<Shape>();

				list = (ArrayList<Shape>) objectInputStream.readObject();
				drawingModel.getShapes().addAll(list);

				fileInputStream.close();
				objectInputStream.close();

			} catch (ClassNotFoundException e) {

				JOptionPane.showMessageDialog(null, "File not found!", "Message", JOptionPane.INFORMATION_MESSAGE);

			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "something went wrong!", "Message",
						JOptionPane.INFORMATION_MESSAGE);

			}
			frame.getBtnUndo().setEnabled(false);
			frame.getView().repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	//Open Log
	public void OpenLog() {

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int dialog = jFileChooser.showOpenDialog(frame);
		File file = jFileChooser.getSelectedFile();

		if (dialog == JFileChooser.APPROVE_OPTION) {

			clearAll();
			frame.getTextArea().setText(" ");

			try {

				Scanner fileScanner = new Scanner(file); 
				frame.getTextArea().append("Start loading commands - click next \n");
				frame.getBtnNext().setVisible(true);
				frame.getBtnNext().setEnabled(true);

				frame.getBtnNext().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Scanner scanner = fileScanner;
						try {

							if (scanner.hasNextLine()) {

								String ourLine = scanner.nextLine();
								readNextLine(ourLine);

							} else {

								frame.getBtnNext().setEnabled(false);
								JOptionPane.showMessageDialog(null, "There is no more shapes!", "Message",
										JOptionPane.INFORMATION_MESSAGE);
								fileScanner.close();

							}

						} catch (Exception exc) {

							exc.printStackTrace();
						}

					}

				});

			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "Something went wrong!", "Message",
						JOptionPane.INFORMATION_MESSAGE);

			}

		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		frame.getView().repaint();
	}

	public void readNextLine(String nextLine) {

		String[] s = nextLine.split(" ");
		Command cmd;

		if (s[0].equals("Add:")) {

			Shape addShape = returnShape(nextLine);

			if (addShape != null) {
				frame.getTextArea().append("Add: " + addShape.toString() + "\n");
				cmd = new AddShapeCmd((Shape) addShape, drawingModel);
				cmd.execute();
				activityStack.push(cmd);
			}

			if (!getActivityStack().isEmpty()) {
				frame.getBtnUndo().setEnabled(true);
			}

			clearRedo();
			frame.getView().repaint();

		} else if (s[0].equals("Delete:")) {

			frame.getTextArea().append("Delete: " + drawingModel.getSelectedShapes().toString() + "\n");

			cmd = new DeleteShapeCmd(drawingModel.getSelectedShapes(), drawingModel);
			cmd.execute();
			activityStack.push(cmd);

			this.setSelectedShape(null);
			clearRedo();
			frame.getView().repaint();
			frame.getTglBtnSelect().setSelected(false);

		} else if (s[0].equals("Modify:")) {

			Shape shape = returnShape(nextLine);
			Shape oldShape = drawingModel.getSelectedShapes().get(0);

			if (s[1].equals("Point:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new ModifyPointCmd((Point) oldShape, (Point) shape);
				cmd.execute();
				activityStack.push(cmd);

			} else if (s[1].equals("Line:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateShapes.ModifyLineCmd((Line) oldShape, (Line) shape);
				cmd.execute();
				activityStack.push(cmd);

			} else if (s[1].equals("Circle:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateShapes.ModifyCircleCmd((Circle) oldShape, (Circle) shape);
				cmd.execute();
				activityStack.push(cmd);

			} else if (s[1].equals("Donut:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateShapes.ModifyDonutCmd((Donut) oldShape, (Donut) shape);
				cmd.execute();
				activityStack.push(cmd);

			} else if (s[1].equals("Rectangle:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateShapes.ModifyRectangleCmd((Rectangle) oldShape, (Rectangle) shape);
				cmd.execute();
				activityStack.push(cmd);

			} else if (s[1].equals("Hexagon:")) {

				frame.getTextArea().append("Modify: " + shape.toString() + "\n");
				cmd = new command.UpdateShapes.ModifyHexagonCmd((HexagonAdapter) oldShape, (HexagonAdapter) shape);
				cmd.execute();
				activityStack.push(cmd);

			}

			clearRedo();
			oldShape.setSelectedShape(true);
			frame.getView().repaint();

		} else if (s[0].equals("Select:")) {

			Shape shape = returnShape(nextLine);

			for (int i = 0; i < drawingModel.getShapes().size(); i++) {

				if (drawingModel.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter h1 = (HexagonAdapter) drawingModel.getShapes().get(i);
					HexagonAdapter h2 = (HexagonAdapter) shape;

					if (h1.getHexagon().getX() == h2.getHexagon().getX()
							&& h1.getHexagon().getY() == h2.getHexagon().getY()
							&& h1.getHexagon().getR() == h2.getHexagon().getR()
							&& h1.getHexagon().getAreaColor().equals(h2.getHexagon().getAreaColor())
							&& h1.getHexagon().getBorderColor().equals(h2.getHexagon().getBorderColor())) {
						shape = drawingModel.getShapes().get(i);
					}
				}

				else if ((drawingModel.getShapes().get(i)).equals(shape)) {
					shape = drawingModel.getShapes().get(i);

				}
			}

			shape.setSelectedShape(true);
			drawingModel.addSelected(shape);
			frame.getTextArea().append("Select: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();

		} else if (s[0].equals("Deselect:")) {

			Shape shape = returnShape(nextLine);

			for (int i = 0; i < drawingModel.getShapes().size(); i++) {

				if (drawingModel.getShapes().get(i) instanceof HexagonAdapter && shape instanceof HexagonAdapter) {
					HexagonAdapter h1 = (HexagonAdapter) drawingModel.getShapes().get(i);
					HexagonAdapter h2 = (HexagonAdapter) shape;

					if (h1.getHexagon().getX() == h2.getHexagon().getX()
							&& h1.getHexagon().getY() == h2.getHexagon().getY()
							&& h1.getHexagon().getR() == h2.getHexagon().getR()
							&& h1.getHexagon().getAreaColor().equals(h2.getHexagon().getAreaColor())
							&& h1.getHexagon().getBorderColor().equals(h2.getHexagon().getBorderColor())) {
						shape = drawingModel.getShapes().get(i);
					}

				}

				if (shape.equals(drawingModel.getShapes().get(i)))
					shape = drawingModel.getShapes().get(i);
			}

			shape.setSelectedShape(false);
			drawingModel.removeSelected(shape);
			frame.getTextArea().append("Deselect: " + shape.toString() + "\n");
			clearRedo();
			frame.getView().repaint();

		} else if (s[0].equals("Undo")) {
			undo();
		} else if (s[0].equals("REDO")) {
			redo();
		} else if (s[0].equals("ToBack:")) {
			toBack();
		} else if (s[0].equals("ToFront:")) {
			toFront();
		} else if (s[0].equals("BringToBack:")) {
			bringToBack();
		} else if (s[0].equals("BringToFront:")) {
			bringToFront();
		}

	}

	public Shape returnShape(String nl) {

		Shape shape = null;

		String[] s = nl.split(" ");

		if (s[1].equals("Point:")) {
			Point p = new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false, Color.decode(s[5]));
			shape = p;

		} else if (s[1].equals("Line:")) {

			Line l = new Line(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])),
					new Point(Integer.parseInt(s[6]), Integer.parseInt(s[7])), false, Color.decode(s[9]));
			shape = l;

		} else if (s[1].equals("Circle:")) {

			Circle c = new Circle(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])), Integer.parseInt(s[6]),
					false, Color.decode(s[8]), Color.decode(s[10]));
			shape = c;

		} else if (s[1].equals("Donut:")) {

			Donut dn = new Donut(new Point(Integer.parseInt(s[4]), Integer.parseInt(s[5])), Integer.parseInt(s[7]),
					Integer.parseInt(s[13]), false, Color.decode(s[9]), Color.decode(s[11]));
			shape = dn;

		} else if (s[1].equals("Rectangle:")) {

			Rectangle r = new Rectangle(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])),
					Integer.parseInt(s[6]), Integer.parseInt(s[8]), false, Color.decode(s[12]), Color.decode(s[10]));
			shape = r;

		} else if (s[1].equals("Hexagon:")) {

			HexagonAdapter ha = new HexagonAdapter(new Point(Integer.parseInt(s[3]), Integer.parseInt(s[4])),
					Integer.parseInt(s[6]), false, Color.decode(s[10]), Color.decode(s[8]));
			shape = ha;
		}

		return shape;
	}

	public void clearAll() {

		drawingModel.getSelectedShapes().clear();
		drawingModel.getShapes().clear();
		frame.getTextArea().setText(" ");
		undoStack.clear();
		activityStack.clear();

	}

	public DrawingModel getModel() {
		return drawingModel;
	}

	public void setModel(DrawingModel drawingModel) {
		this.drawingModel = drawingModel;
	}

	public DrawingFrame getFrame() {
		return frame;
	}

	public void setFrame(DrawingFrame frame) {
		this.frame = frame;
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Stack<Command> getActivityStack() {
		return activityStack;
	}

	public void setActivityStack(Stack<Command> activityStack) {
		this.activityStack = activityStack;
	}

	public Stack<Command> getUndoStack() {
		return undoStack;
	}

	public void setUndoStack(Stack<Command> undoStack) {
		this.undoStack = undoStack;
	}

}
