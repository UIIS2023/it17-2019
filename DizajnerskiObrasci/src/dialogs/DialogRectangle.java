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
import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtRectangleX;
	private JTextField txtRectangleY;
	private JTextField txtWidth;
	private JTextField txtHeight;

	private boolean isOK;

	public Rectangle rectangle;

	private JButton btnRectangleOutLineColor;
	private JButton btnRectangleInnerColor;

	public static void main(String[] args) {
		try {
			DialogRectangle dialog = new DialogRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogRectangle() {

		setTitle("RECTANGLE");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);

		setBounds(100, 100, 300, 300);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230)); // Light Blue
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		{
			JLabel lblUpperLeftPoint = new JLabel("Upper Left Point:");
			GridBagConstraints gbc_lblULP = new GridBagConstraints();
			gbc_lblULP.insets = new Insets(0, 0, 5, 5);
			gbc_lblULP.gridx = 1;
			gbc_lblULP.gridy = 1;
			contentPanel.add(lblUpperLeftPoint, gbc_lblULP);

		}
		{
			JLabel lblUpperLeftPointX = new JLabel("Coordinate X:");
			GridBagConstraints gbc_lblNewLabelULPX = new GridBagConstraints();
			gbc_lblNewLabelULPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelULPX.gridx = 1;
			gbc_lblNewLabelULPX.gridy = 2;
			contentPanel.add(lblUpperLeftPointX, gbc_lblNewLabelULPX);

		}
		{
			txtRectangleX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 10);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 3;
			gbc_txtX.gridy = 2;
			contentPanel.add(txtRectangleX, gbc_txtX);
			txtRectangleX.setColumns(10);

		}
		{
			JLabel lblUpperLeftPointY = new JLabel("Coordinate Y:");
			GridBagConstraints gbc_lblULPY = new GridBagConstraints();
			gbc_lblULPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblULPY.gridx = 1;
			gbc_lblULPY.gridy = 3;
			contentPanel.add(lblUpperLeftPointY, gbc_lblULPY);
		}
		{
			txtRectangleY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 10);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 3;
			gbc_txtY.gridy = 3;
			contentPanel.add(txtRectangleY, gbc_txtY);
			txtRectangleY.setColumns(10);

		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 1;
			gbc_lblHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 10);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 3;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 1;
			gbc_lblWidth.gridy = 5;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 10);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 3;
			gbc_txtWidth.gridy = 5;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtHeight.setColumns(10);

		}

		btnRectangleInnerColor = new JButton("Inner Color");
		btnRectangleInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color innerColor = JColorChooser.showDialog(null, "Choose inner color!",
						btnRectangleInnerColor.getBackground());
				if (innerColor != null)
					if (innerColor.equals(Color.BLACK))
						btnRectangleInnerColor.setForeground(Color.WHITE);
					else if (innerColor.equals(Color.WHITE))
						btnRectangleInnerColor.setForeground(Color.BLACK);
				btnRectangleInnerColor.setBackground(innerColor);
			}
		});

		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.anchor = GridBagConstraints.SOUTH;
		gbc_btnInnerColor.insets = new Insets(0, 0, 10, 5);
		gbc_btnInnerColor.gridx = 1;
		gbc_btnInnerColor.gridy = 7;
		contentPanel.add(btnRectangleInnerColor, gbc_btnInnerColor);

		contentPanel.add(btnRectangleInnerColor, gbc_btnInnerColor);

		btnRectangleOutLineColor = new JButton("Outline Color");
		btnRectangleOutLineColor.setForeground(Color.WHITE);
		btnRectangleOutLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outLineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnRectangleOutLineColor.getBackground());
				if (outLineColor != null)
					if (outLineColor.equals(Color.BLACK))
						btnRectangleOutLineColor.setForeground(Color.WHITE);
					else if (outLineColor.equals(Color.WHITE))
						btnRectangleOutLineColor.setForeground(Color.BLACK);
				btnRectangleOutLineColor.setBackground(outLineColor);

			}
		});

		GridBagConstraints gbc_btnOutLineColor = new GridBagConstraints();
		gbc_btnOutLineColor.insets = new Insets(0, 0, 10, 5);
		gbc_btnOutLineColor.gridx = 3;
		gbc_btnOutLineColor.gridy = 7;
		contentPanel.add(btnRectangleOutLineColor, gbc_btnOutLineColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(100, 149, 237));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (txtRectangleX.getText().trim().isEmpty() || txtRectangleY.getText().trim().isEmpty()
									|| txtHeight.getText().trim().isEmpty() || txtWidth.getText().trim().isEmpty()) {
								setOK(false);
								JOptionPane.showMessageDialog(null, "All values are required!", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								if (Integer.parseInt(txtWidth.getText().toString()) <= 0
										|| Integer.parseInt(txtHeight.getText().toString()) <= 0
										|| Integer.parseInt(txtRectangleX.getText().toString()) < 0
										|| Integer.parseInt(txtRectangleY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater then 0!", "Error",
											JOptionPane.ERROR_MESSAGE);

								} else {
									rectangle = new Rectangle(
											new Point(Integer.parseInt(getTxtX().getText().toString()),
													Integer.parseInt(getTxtY().getText().toString())),
											Integer.parseInt(getTxtWidth().getText().toString()),
											Integer.parseInt(getTxtHeight().getText().toString()), false,
											btnRectangleOutLineColor.getBackground(),
											btnRectangleInnerColor.getBackground());

									setOK(true);
									setVisible(false);
								}
							}
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Enter numbers only!", "Error",
									JOptionPane.ERROR_MESSAGE);
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
		return txtRectangleX;
	}

	public void setTxtX(JTextField txtRectangleX) {
		this.txtRectangleX = txtRectangleX;
	}

	public JTextField getTxtY() {
		return txtRectangleY;
	}

	public void setTxtY(JTextField txtRectangleY) {
		this.txtRectangleY = txtRectangleY;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public JButton getBtnInnerColor() {
		return btnRectangleInnerColor;
	}

	public void setBtnInnerColor(JButton btnRectangleInnerColor) {
		this.btnRectangleInnerColor = btnRectangleInnerColor;
	}

	public JButton getBtnOutlineColor() {
		return btnRectangleOutLineColor;
	}

	public void setBtnOutlineColor(JButton btnRectangleOutLineColor) {
		this.btnRectangleOutLineColor = btnRectangleOutLineColor;
	}

}
