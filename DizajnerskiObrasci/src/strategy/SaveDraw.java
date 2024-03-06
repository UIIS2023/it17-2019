package strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import geometry.Shape;
import mvc.DrawingModel;

public class SaveDraw implements Save {

	private ArrayList<Shape> shapesList;

	public SaveDraw(ArrayList<Shape> shapesList) {
		this.shapesList = shapesList;
	}

	@Override
	public void saveData() {

		JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jFileChooser.setDialogTitle("Choose destination");
		if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

			File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());

			if (file.exists()) {

				JOptionPane.showMessageDialog(null, "Name must be unique!", "Error", JOptionPane.ERROR_MESSAGE);

			} else {

				FileOutputStream fileOutputStream;
				ObjectOutputStream objectOutputStream;
				try {
					fileOutputStream = new FileOutputStream(file + ".ser");
					objectOutputStream = new ObjectOutputStream(fileOutputStream);

					objectOutputStream.writeObject(shapesList);
					objectOutputStream.close();
					fileOutputStream.close();

					JOptionPane.showMessageDialog(null, "Successfully saved", "Saving complete",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
