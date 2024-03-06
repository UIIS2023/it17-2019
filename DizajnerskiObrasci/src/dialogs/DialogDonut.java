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

import geometry.Donut;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DialogDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;

	public boolean isOK;
	public Donut donut;

	public JButton btnDonutInnerColor;
	public JButton btnDonutOutLineColor;

	public static void main(String[] args) {
		try {
			DialogDonut dialog = new DialogDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogDonut() {

		setBounds(100, 100, 450, 300);
		setTitle("DONUT");
		setResizable(false);
		setModal(true);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230)); // Light Blue
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
			txtCoordinateX.setColumns(10);
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
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setText("");
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 10);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 3;
			gbc_txtRadius.gridy = 3;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 1;
			gbc_lblInnerRadius.gridy = 4;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 10);
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 3;
			gbc_txtInnerRadius.gridy = 4;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}

		btnDonutInnerColor = new JButton("Inner Color");
		btnDonutInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color",
						btnDonutInnerColor.getBackground());
				if (innerColor != null)
					if (innerColor.equals(Color.BLACK))
						btnDonutInnerColor.setForeground(Color.WHITE);
					else if (innerColor.equals(Color.WHITE))
						btnDonutInnerColor.setForeground(Color.BLACK);
				btnDonutInnerColor.setBackground(innerColor);

			}
		});
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.insets = new Insets(0, 0, 10, 5);
		gbc_btnInnerColor.gridx = 1;
		gbc_btnInnerColor.gridy = 5;
		contentPanel.add(btnDonutInnerColor, gbc_btnInnerColor);

		btnDonutOutLineColor = new JButton("OutLine Color");
		btnDonutOutLineColor.setForeground(Color.WHITE);
		btnDonutOutLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outLineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnDonutOutLineColor.getBackground());
				if (outLineColor != null)
					if (outLineColor.equals(Color.BLACK))
						btnDonutOutLineColor.setForeground(Color.WHITE);
					else if (outLineColor.equals(Color.WHITE))
						btnDonutOutLineColor.setForeground(Color.BLACK);

				btnDonutOutLineColor.setBackground(outLineColor);

			}
		});

		GridBagConstraints gbc_btnOutLineColor = new GridBagConstraints();
		gbc_btnOutLineColor.insets = new Insets(0, 0, 10, 5);
		gbc_btnOutLineColor.gridx = 3;
		gbc_btnOutLineColor.gridy = 5;
		contentPanel.add(btnDonutOutLineColor, gbc_btnOutLineColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(100, 149, 237));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtCoordinateX.getText().trim().isEmpty() || txtCoordinateY.getText().trim().isEmpty()
								|| txtRadius.getText().isEmpty() || txtInnerRadius.getText().isEmpty()) {
							setOK(false);
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtCoordinateX.getText().toString()) < 0
										|| Integer.parseInt(txtCoordinateY.getText().toString()) < 0
										|| Integer.parseInt(txtRadius.getText().toString()) < 0
										|| Integer.parseInt(txtInnerRadius.getText().toString()) <= 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									if (Integer.parseInt(txtInnerRadius.getText().toString()) < Integer
											.parseInt(txtRadius.getText().toString())) {
										donut = new Donut(
												new Point(Integer.parseInt(txtCoordinateX.getText().toString()),
														Integer.parseInt(txtCoordinateY.getText().toString())),
												Integer.parseInt(txtRadius.getText().toString()),
												Integer.parseInt(txtInnerRadius.getText().toString()), false,
												btnDonutOutLineColor.getBackground(),
												btnDonutInnerColor.getBackground());
										setOK(true);
										setVisible(false);
									} else {
										JOptionPane.showMessageDialog(null,
												"Please insert inner radius less than outer radius!", "ERROR",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(null, "Enter numbers only!", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnOK.setBackground(new Color(152, 251, 152));
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

	public JTextField getTxtInnerR() {
		return txtInnerRadius;
	}

	public void setTxtInnerR(JTextField txtInnerRadius) {
		this.txtInnerRadius = txtInnerRadius;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

	public JButton getBtnInnerColor() {
		return btnDonutInnerColor;
	}

	public void setBtnInnerColor(JButton btnDonutInnerColor) {
		this.btnDonutInnerColor = btnDonutInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnDonutOutLineColor;
	}

	public void setBtnOutlineColor(JButton btnDonutOutLineColor) {
		this.btnDonutOutLineColor = btnDonutOutLineColor;
	}

}
