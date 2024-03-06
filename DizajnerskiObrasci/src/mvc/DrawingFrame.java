package mvc;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.GridBagLayout;

public class DrawingFrame extends JFrame {
	
	private JPanel contentPane = new JPanel();
	private DrawingController controller;
	private DrawingView drawingView = new DrawingView();
	private DefaultListModel<String> defaulList = new DefaultListModel<String>(); 

	private JToggleButton tglBtnPoint = new JToggleButton("Point");
	private JToggleButton tglBtnLine = new JToggleButton("Line");
	private JToggleButton tglBtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglBtnCircle = new JToggleButton("Circle");
	private JToggleButton tglBtnDonut = new JToggleButton("Donut");
	private JToggleButton tglBtnHexagon = new JToggleButton("Hexagon");
	private JToggleButton tglBtnSelect = new JToggleButton("Select");

	private final JButton btnModify = new JButton("Modify");
	private final JButton btnDelete = new JButton("Delete");

	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnToBack = new JButton("To Back");
	private final JButton btnToFront = new JButton("To Front");
	private final JButton btnBringToFront = new JButton("Bring To Front");
	private final JButton btnBringToBack = new JButton("Bring To Back");
	private final JToggleButton tglBtnInnerColor = new JToggleButton("Inner color ");
	private final JToggleButton tglBtnOutlineColor = new JToggleButton("Outline color");
	private final JTextArea textArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(textArea);
	private JButton btnNext;

	public DrawingFrame() {

		drawingView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.mouseClicked(e);

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 800);
		setTitle("Tadic Kristina, IT17-2019");
		setResizable(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		drawingView.setBackground(Color.WHITE);
		getContentPane().add(drawingView, BorderLayout.CENTER);

		//NORTH
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(176, 224, 230));
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		getContentPane().add(drawingView, BorderLayout.CENTER);
		GroupLayout gl_view = new GroupLayout(drawingView);
		gl_view.setHorizontalGroup(gl_view.createParallelGroup(Alignment.LEADING).addGap(0, 587, Short.MAX_VALUE));
		gl_view.setVerticalGroup(gl_view.createParallelGroup(Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		drawingView.setLayout(gl_view);

		pnlNorth.add(tglBtnPoint);
		pnlNorth.add(tglBtnLine);
		pnlNorth.add(tglBtnCircle);
		pnlNorth.add(tglBtnDonut);
		pnlNorth.add(tglBtnRectangle);
		pnlNorth.add(tglBtnHexagon);

		ButtonGroup btnGroup = new ButtonGroup();

		btnGroup.add(tglBtnPoint);
		btnGroup.add(tglBtnLine);
		btnGroup.add(tglBtnCircle);
		btnGroup.add(tglBtnDonut);
		btnGroup.add(tglBtnRectangle);
		btnGroup.add(tglBtnHexagon);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setBackground(new Color(176, 224, 230));
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
		JPanel pnlButtons = new JPanel();
		pnlSouth.add(pnlButtons);
		
		pnlButtons.add(tglBtnSelect);
		pnlButtons.setBackground(new Color(176, 224, 230));
		
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});

		pnlButtons.add(btnDelete);
		btnGroup.add(btnDelete);
		
		btnModify.setEnabled(false);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.modify();

			}
		});
		pnlButtons.add(btnModify);
		btnGroup.add(btnModify);
		
		pnlButtons.add(btnUndo);
		btnGroup.add(btnUndo);

		//UNDO i REDO
		btnUndo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/undo.png")));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		pnlButtons.add(btnUndo);
		btnGroup.add(btnUndo);
		
		btnRedo.setIcon(new ImageIcon(DrawingFrame.class.getResource("/icons/redo.png")));
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});	
		pnlButtons.add(btnRedo);
		btnGroup.add(btnRedo);

		//WEST
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(new Color(176, 224, 230));
		contentPane.add(pnlWest, BorderLayout.WEST);
		
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[] { 99, 0 };
		gbl_pnlWest.rowHeights = new int[] { 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 33,
				0 };
		gbl_pnlWest.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlWest.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlWest.setLayout(gbl_pnlWest);
		
		btnToFront.setEnabled(false);
		
		GridBagConstraints gbc_btnTF = new GridBagConstraints();
		gbc_btnTF.fill = GridBagConstraints.BOTH;
		gbc_btnTF.insets = new Insets(0, 0, 5, 0);
		gbc_btnTF.gridx = 0;
		gbc_btnTF.gridy = 4;
		pnlWest.add(btnToFront, gbc_btnTF);
		btnGroup.add(btnToFront);

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		btnGroup.add(btnToBack);

		btnToBack.setEnabled(false);
		
		GridBagConstraints gbc_btnTB = new GridBagConstraints();
		gbc_btnTB.fill = GridBagConstraints.BOTH;
		gbc_btnTB.insets = new Insets(0, 0, 5, 0);
		gbc_btnTB.gridx = 0;
		gbc_btnTB.gridy = 6;
		pnlWest.add(btnToBack, gbc_btnTB);

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		btnGroup.add(btnBringToFront);
		
		btnBringToFront.setEnabled(false);
		
		GridBagConstraints gbc_btnBTF = new GridBagConstraints();
		gbc_btnBTF.fill = GridBagConstraints.BOTH;
		gbc_btnBTF.insets = new Insets(0, 0, 5, 0);
		gbc_btnBTF.gridx = 0;
		gbc_btnBTF.gridy = 8;
		pnlWest.add(btnBringToFront, gbc_btnBTF);

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		btnGroup.add(btnBringToBack);
		
		btnBringToBack.setEnabled(false);

		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		GridBagConstraints gbc_btnBTB = new GridBagConstraints();
		gbc_btnBTB.fill = GridBagConstraints.BOTH;
		gbc_btnBTB.insets = new Insets(0, 0, 5, 0);
		gbc_btnBTB.gridx = 0;
		gbc_btnBTB.gridy = 10;
		pnlWest.add(btnBringToBack, gbc_btnBTB);
		
		btnBringToFront.setToolTipText("Place the selected shape on top");
		btnToFront.setToolTipText("Move the selected shape closer to the top");
		btnBringToBack.setToolTipText("Place the selected shape on the bottom");
		btnToBack.setToolTipText("Move the selected shape closer to the bottom");

		
		JPanel pnlTextArea = new JPanel();
		pnlTextArea.setBackground(new Color(176, 224, 230));
		pnlSouth.add(pnlTextArea);

		textArea.setTabSize(30);
		textArea.setColumns(50);
		textArea.setRows(8);
		textArea.setSize(500, 80);
		textArea.setEditable(false);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlTextArea.add(scrollPane, gbc_scrollPane);
		
		//EAST
		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(new Color(176, 224, 230));
		pnlEast.setForeground(SystemColor.inactiveCaption);
		contentPane.add(pnlEast, BorderLayout.EAST);
		
		GridBagLayout gbl_pnlEast = new GridBagLayout();
		gbl_pnlEast.columnWidths = new int[] { 99, 0 };
		gbl_pnlEast.rowHeights = new int[] { 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 33,
				0 };
		gbl_pnlEast.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlEast.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlEast.setLayout(gbl_pnlEast);

		
		tglBtnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.activeInnerColor();
				getTglbtnInnerColor().setSelected(false); //prikazivanje da taster nije vise pritisnut
			}
		});
		
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.BOTH;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnInnerColor.gridx = 0;
		gbc_btnInnerColor.gridy = 4;
		pnlEast.add(tglBtnInnerColor, gbc_btnInnerColor);
		
		tglBtnOutlineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.activeOutlineColor();
				getTglBtnOutlineColor().setSelected(false);

			}
		});
		
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.fill = GridBagConstraints.BOTH;
		gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnOutlineColor.gridx = 0;
		gbc_btnOutlineColor.gridy = 6;
		pnlEast.add(tglBtnOutlineColor, gbc_btnOutlineColor);
		
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(true);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 8;
		pnlEast.add(btnSave, gbc_btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object[] options = { "Save log", "Save drawing" };
				int option = JOptionPane.showOptionDialog(null, "How do you want to save this?", "Save", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				controller.save(option);
			}
		});

		JButton btnOpenLog = new JButton("Open Log");
		btnOpenLog.setEnabled(true);
		GridBagConstraints gbc_btnOpenLog = new GridBagConstraints();
		gbc_btnOpenLog.fill = GridBagConstraints.BOTH;
		gbc_btnOpenLog.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenLog.gridx = 0;
		gbc_btnOpenLog.gridy = 10;
		pnlEast.add(btnOpenLog, gbc_btnOpenLog);

		btnOpenLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.OpenLog();
			}
		});
		
		JButton btnOpenDrawing = new JButton("Open Draw");
		btnOpenDrawing.setEnabled(true);
		GridBagConstraints gbc_btnOpenDrawing = new GridBagConstraints();
		gbc_btnOpenDrawing.fill = GridBagConstraints.BOTH;
		gbc_btnOpenDrawing.gridx = 0;
		gbc_btnOpenDrawing.gridy = 12;
		pnlEast.add(btnOpenDrawing, gbc_btnOpenDrawing);

		btnOpenDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.OpenDrawing();
			}
		});

		btnNext = new JButton("Next");
		btnNext.setVisible(false);
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.fill = GridBagConstraints.BOTH;
		btnNext.setEnabled(false);
		gbc_btnNext.insets = new Insets(0, 0, 5, 0);
		gbc_btnNext.gridx = 0;
		gbc_btnNext.gridy = 14;
		pnlEast.add(btnNext, gbc_btnNext);

	}
	
	public DrawingView getView() {
		return drawingView;
	}

	public void setView(DrawingView drawingView) {
		this.drawingView = drawingView;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	public DefaultListModel<String> getDefaultList() {
		return defaulList;
	}

	public void setDefaultList(DefaultListModel<String> defaulList) {
		this.defaulList = defaulList;
	}

	public JToggleButton getTglBtnPoint() {
		return tglBtnPoint;
	}

	public void setTglBtnPoint(JToggleButton tglBtnPoint) {
		this.tglBtnPoint = tglBtnPoint;
	}

	public JToggleButton getTglBtnLine() {
		return tglBtnLine;
	}

	public void setTglBtnLine(JToggleButton tglBtnLine) {
		this.tglBtnLine = tglBtnLine;
	}
	
	public JToggleButton getTglBtnCircle() {
		return tglBtnCircle;
	}

	public void setTglBtnCircle(JToggleButton tglbtnCircle) {
		this.tglBtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglBtnDonut() {
		return tglBtnDonut;
	}

	public void setTglBtnDonut(JToggleButton tglbtnDonut) {
		this.tglBtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglBtnRectangle() {
		return tglBtnRectangle;
	}

	public void setTglBtnRectangle(JToggleButton tglBtnRectangle) {
		this.tglBtnRectangle = tglBtnRectangle;
	}

	public JToggleButton getTglBtnHexagon() {
		return tglBtnHexagon;
	}

	public void setTglBtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglBtnHexagon = tglbtnHexagon;
	}
	
	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}

	public void setTglBtnSelect(JToggleButton tglbtnSelect) {
		this.tglBtnSelect = tglbtnSelect;
	}
	
	public JToggleButton getTglbtnInnerColor() {
		return tglBtnInnerColor;
	}

	public JToggleButton getTglBtnOutlineColor() {
		return tglBtnOutlineColor;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}
	
	public JButton getBtnToFront() {
		return btnToFront;
	}
	
	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
}
