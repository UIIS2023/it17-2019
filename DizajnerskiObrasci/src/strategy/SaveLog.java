package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import mvc.DrawingFrame;

public class SaveLog implements Save {

	DrawingFrame drawingFrame;

	public SaveLog(DrawingFrame drawingFrame) {

		this.drawingFrame = drawingFrame;

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
				try {
					FileWriter fileWriter = new FileWriter(file + ".txt");
					fileWriter.write(drawingFrame.getTextArea().getText());
					fileWriter.close();
					
					JOptionPane.showMessageDialog(null, "Successfully saved!", "Saving completed!",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Something went wrong!", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

}}
