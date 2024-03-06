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

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private boolean isOK;
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	
	public Line line;
	private Color color = Color.BLACK;
	private JButton btnLineOutLineColor;
	
	public static void main(String[] args) {
		try {
			DialogLine dialog = new DialogLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogLine() {
		
		setModal(true);
		setTitle("LINE");
		setBackground(Color.WHITE);
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
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		
		{
			JLabel lblStartPoint = new JLabel("Start Point: ");
			GridBagConstraints gbc_lblSP = new GridBagConstraints();
			gbc_lblSP.insets = new Insets(0, 0, 5, 5);
			gbc_lblSP.gridx = 1;
			gbc_lblSP.gridy = 0;
			contentPanel.add(lblStartPoint, gbc_lblSP);
			
		}
		{
			JLabel lblStartPointX = new JLabel("Enter Coordinate X");
			GridBagConstraints gbc_lblSPX = new GridBagConstraints();
			gbc_lblSPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblSPX.gridx = 1;
			gbc_lblSPX.gridy = 1;
			contentPanel.add(lblStartPointX, gbc_lblSPX);
			
		}
		{
			txtStartPointX = new JTextField();
			GridBagConstraints gbc_txtStartPointX = new GridBagConstraints();
			gbc_txtStartPointX.insets = new Insets(0, 0, 5, 10);
			gbc_txtStartPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartPointX.gridx = 3;
			gbc_txtStartPointX.gridy = 1;
			contentPanel.add(txtStartPointX, gbc_txtStartPointX);
			txtStartPointX.setColumns(10);
			
		}
		{
			JLabel lblStartPointY = new JLabel("Enter Coordinate Y");
			GridBagConstraints gbc_lblSPY = new GridBagConstraints();
			gbc_lblSPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblSPY.gridx = 1;
			gbc_lblSPY.gridy = 2;
			contentPanel.add(lblStartPointY, gbc_lblSPY);
			
		}
		{
			txtStartPointY = new JTextField();
			GridBagConstraints gbc_txtStartPointY = new GridBagConstraints();
			gbc_txtStartPointY.insets = new Insets(0, 0, 5, 10);
			gbc_txtStartPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartPointY.gridx = 3;
			gbc_txtStartPointY.gridy = 2;
			contentPanel.add(txtStartPointY, gbc_txtStartPointY);
			txtStartPointY.setColumns(10);
			
		}
		{
			JLabel lblEndPoint = new JLabel("End Point: ");
			GridBagConstraints gbc_lblNewLabelEP = new GridBagConstraints();
			gbc_lblNewLabelEP.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabelEP.gridx = 1;
			gbc_lblNewLabelEP.gridy = 3;
			contentPanel.add(lblEndPoint, gbc_lblNewLabelEP);
			
		}
		{
			JLabel lblEndPointX = new JLabel("Enter Coordinate X");
			GridBagConstraints gbc_lblEPX = new GridBagConstraints();
			gbc_lblEPX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEPX.gridx = 1;
			gbc_lblEPX.gridy = 5;
			contentPanel.add(lblEndPointX, gbc_lblEPX);
			
		}
		{
			txtEndPointX = new JTextField();
			GridBagConstraints gbc_txtEndPointX = new GridBagConstraints();
			gbc_txtEndPointX.insets = new Insets(0, 0, 5, 10);
			gbc_txtEndPointX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndPointX.gridx = 3;
			gbc_txtEndPointX.gridy = 5;
			contentPanel.add(txtEndPointX, gbc_txtEndPointX);
			txtEndPointX.setColumns(10);
			
		}
		{
			JLabel lblEndPointY = new JLabel("Enter Coordinate Y");
			GridBagConstraints gbc_lblEPY = new GridBagConstraints();
			gbc_lblEPY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEPY.gridx = 1;
			gbc_lblEPY.gridy = 6;
			contentPanel.add(lblEndPointY, gbc_lblEPY);
		}
		{
			txtEndPointY = new JTextField();
			GridBagConstraints gbc_txtEndPointY = new GridBagConstraints();
			gbc_txtEndPointY.insets = new Insets(0, 0, 5, 10);
			gbc_txtEndPointY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndPointY.gridx = 3;
			gbc_txtEndPointY.gridy = 6;
			contentPanel.add(txtEndPointY, gbc_txtEndPointY);
			txtEndPointY.setColumns(10);
		}

		btnLineOutLineColor = new JButton("OUTLINE COLOR");
		btnLineOutLineColor.setForeground(Color.WHITE);
		btnLineOutLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color outLineColor = JColorChooser.showDialog(null, "Choose outline color",
						btnLineOutLineColor.getBackground());
				if (outLineColor != null)
					if (outLineColor.equals(Color.BLACK))
						btnLineOutLineColor.setForeground(Color.WHITE);
					else if (outLineColor.equals(Color.WHITE))
						btnLineOutLineColor.setForeground(Color.BLACK);
				btnLineOutLineColor.setBackground(outLineColor);

			}
		});
		GridBagConstraints gbc_btnOutLineColor = new GridBagConstraints();
		gbc_btnOutLineColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnOutLineColor.gridx = 1;
		gbc_btnOutLineColor.gridy = 8;
		contentPanel.add(btnLineOutLineColor, gbc_btnOutLineColor);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(100, 149, 237));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("OK");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (txtStartPointX.getText().trim().isEmpty() || txtStartPointY.getText().trim().isEmpty()
								|| txtEndPointX.getText().trim().isEmpty() || txtEndPointY.getText().trim().isEmpty()) {
							setOK(false);
							JOptionPane.showMessageDialog(null, "All fields are required!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								if (Integer.parseInt(txtStartPointX.getText().toString()) < 0
										|| Integer.parseInt(txtStartPointY.getText().toString()) < 0
										|| Integer.parseInt(txtEndPointX.getText().toString()) < 0
										|| Integer.parseInt(txtEndPointY.getText().toString()) < 0) {
									JOptionPane.showMessageDialog(null, "Insert values greater than 0!", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								} else {
									line = new Line(
											new Point(Integer.parseInt(txtStartPointX.getText().toString()),
													Integer.parseInt(txtStartPointY.getText().toString())),
											new Point(Integer.parseInt(txtEndPointX.getText().toString()),
													Integer.parseInt(txtEndPointY.getText().toString())),
											false, btnLineOutLineColor.getBackground());
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

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public JTextField getTxtSPX() {
		return txtStartPointX;
	}

	public void setTxtSPX(JTextField txtStartPointX) {
		this.txtStartPointX = txtStartPointX;
	}

	public JTextField getTxtSPY() {
		return txtStartPointY;
	}

	public void setTxtSPY(JTextField txtStartPointY) {
		this.txtStartPointY = txtStartPointY;
	}

	public JTextField getTxtEPX() {
		return txtEndPointX;
	}

	public void setTxtEPX(JTextField txtEndPointX) {
		this.txtEndPointX = txtEndPointX;
	}

	public JTextField getTxtEPY() {
		return txtEndPointY;
	}

	public void setTxtEPY(JTextField txtEndPointY) {
		this.txtEndPointY = txtEndPointY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public JButton getBtnOutlineColor() {
		return btnLineOutLineColor;
	}

	public void setBtnOutlineColor(JButton btnLineOutLineColor) {
		this.btnLineOutLineColor = btnLineOutLineColor;
	}

}
