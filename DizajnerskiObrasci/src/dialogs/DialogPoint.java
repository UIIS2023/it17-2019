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

import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoordinateX;
	private JTextField txtCoordinateY;

	private Point p;

	private boolean isOK;

	private JButton btnPointColor;

	public static void main(String[] args) {
		try {
			DialogPoint dialog = new DialogPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogPoint() {

		setTitle("POINT");
		setModal(true);
		setResizable(false);
		setBackground(Color.WHITE);
		setLocationRelativeTo(null);

		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230)); // Light Blue
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		{
			JLabel lblX = new JLabel("X coordinate:");
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
			JLabel lblY = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
			gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel1.gridx = 1;
			gbc_lblNewLabel1.gridy = 2;
			contentPanel.add(lblY, gbc_lblNewLabel1);
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

		btnPointColor = new JButton("COLOR");

		btnPointColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outlineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnPointColor.getBackground());
				if (outlineColor != null)
					if (outlineColor.equals(Color.BLACK))
						btnPointColor.setForeground(Color.WHITE);
					else if (outlineColor.equals(Color.WHITE))
						btnPointColor.setForeground(Color.BLACK);
				btnPointColor.setBackground(outlineColor);
			}

		});
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor.gridx = 1;
		gbc_btnColor.gridy = 5;
		contentPanel.add(btnPointColor, gbc_btnColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(100, 149, 237));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtCoordinateX.getText().trim().isEmpty() || txtCoordinateY.getText().trim().isEmpty()) {
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtCoordinateX.getText().toString()) < 0
										|| Integer.parseInt(txtCoordinateY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									p = new Point(Integer.parseInt(txtCoordinateX.getText().toString()),
											Integer.parseInt(txtCoordinateY.getText().toString()), false,
											btnPointColor.getBackground());
									setOK(true);
									setVisible(false);
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

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public JButton getBtnColor() {
		return btnPointColor;
	}

	public void setBtnColor(JButton btnPointColor) {
		this.btnPointColor = btnPointColor;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	/*public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}*/

}
