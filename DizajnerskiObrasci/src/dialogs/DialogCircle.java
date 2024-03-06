package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DialogCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtRadius;

	private Circle circle;

	private boolean isOK;

	private JButton btnCircleInnerColor;
	private JButton btnCircleOutLineColor;

	public static void main(String[] args) {
		try {
			DialogCircle dialog = new DialogCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setModal(true);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public DialogCircle() {

		setTitle("CIRCLE");
		setResizable(false);
		setModal(true);
		setBackground(Color.WHITE);

		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		contentPanel.setBackground(new Color(176, 224, 230)); // Light Blue

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		{
			JLabel lblX = new JLabel("Enter Coordinate X:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 1;
			gbc_lblX.gridy = 1;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtCoordinateX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 10);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 3;
			gbc_txtX.gridy = 1;
			contentPanel.add(txtCoordinateX, gbc_txtX);
			txtCoordinateX.setColumns(10); //prikaz do 10 znakova horizontalno
		}
		{
			JLabel lblY = new JLabel("Enter Coordinate Y:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 1;
			gbc_lblY.gridy = 2;
			contentPanel.add(lblY, gbc_lblY);

		}
		{
			txtCoordinateY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 10);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 3;
			gbc_txtY.gridy = 2;
			contentPanel.add(txtCoordinateY, gbc_txtY);
			txtCoordinateY.setColumns(10);

		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblNewLabelRadius = new GridBagConstraints();
			gbc_lblNewLabelRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelRadius.gridx = 1;
			gbc_lblNewLabelRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblNewLabelRadius);

		}
		{
			txtRadius = new JTextField();
			txtRadius.setText("");
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 10);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 3;
			gbc_txtRadius.gridy = 5;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);

		}

		btnCircleInnerColor = new JButton("Inner Color");
		btnCircleInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color",
						btnCircleInnerColor.getBackground());
				if (innerColor != null)
					if (innerColor.equals(Color.BLACK))
						btnCircleInnerColor.setForeground(Color.WHITE);
					else if (innerColor.equals(Color.WHITE))
						btnCircleInnerColor.setForeground(Color.BLACK);
				btnCircleInnerColor.setBackground(innerColor);
			}
		});
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 10, 5);
		gbc_btnInnerColor.gridx = 1;
		gbc_btnInnerColor.gridy = 7;
		contentPanel.add(btnCircleInnerColor, gbc_btnInnerColor);

		btnCircleOutLineColor = new JButton("Outline Color");
		btnCircleOutLineColor.setForeground(Color.WHITE);
		btnCircleOutLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outLineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnCircleOutLineColor.getBackground());
				if (outLineColor != null)
					if (outLineColor.equals(Color.BLACK))
						btnCircleOutLineColor.setForeground(Color.WHITE);
					else if (outLineColor.equals(Color.WHITE))
						btnCircleOutLineColor.setForeground(Color.BLACK);
				btnCircleOutLineColor.setBackground(outLineColor);
			}
		});

		GridBagConstraints gbc_btnOutLineColor = new GridBagConstraints();
		gbc_btnOutLineColor.insets = new Insets(0, 0, 10, 5);
		gbc_btnOutLineColor.gridx = 3;
		gbc_btnOutLineColor.gridy = 7;
		contentPanel.add(btnCircleOutLineColor, gbc_btnOutLineColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(100, 149, 237)); // Cornflower Blue
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtCoordinateX.getText().trim().isEmpty() || txtCoordinateY.getText().trim().isEmpty()
								|| txtRadius.getText().trim().isEmpty()) {
							setOK(false);
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtCoordinateX.getText().toString()) < 0
										|| Integer.parseInt(txtCoordinateY.getText().toString()) < 0
										|| Integer.parseInt(txtRadius.getText().toString()) <= 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									circle = new Circle(
											new Point(Integer.parseInt(txtCoordinateX.getText().toString()),
													Integer.parseInt(txtCoordinateY.getText().toString())),
											Integer.parseInt(txtRadius.getText().toString()), false,
											btnCircleOutLineColor.getBackground(), btnCircleInnerColor.getBackground());
									setOK(true);
									setVisible(false);
								}
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Enter numbers only", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnOK.setBackground(new Color(176, 224, 230)); // Light Blue
				btnOK.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setBackground(new Color(250, 128, 114));
				btnCancel.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 11));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}

	public JTextField getTxtX() {
		return txtCoordinateX;
	}

	public void setTxtX(JTextField txtCoordinateX) {
		this.txtCoordinateX = txtCoordinateX;
	}

	public JTextField getTxtY() {
		return txtCoordinateY;
	}

	public void setTxtY(JTextField txtCoordinateY) {
		this.txtCoordinateY = txtCoordinateY;
	}

	public JTextField getTxtR() {
		return txtRadius;
	}

	public void setTxtR(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public JButton getBtnInnerColor() {
		return btnCircleInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnCircleInnerColor = btnInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnCircleOutLineColor;
	}

	public void setBtnOutlineColor(JButton btnOutlineColor) {
		this.btnCircleOutLineColor = btnOutlineColor;
	}

}
