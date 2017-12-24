package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the main view of the paint programs and it is responsible to gather 
 * all other view.This is the top level View+Controller, it contains other aspects of the View+Controller.
 *
 * IS-A:JFrame
 * HAS-A:-model:PaintModel ,-UndoneShapes:ArrayList<Shape>,-paintPanel:PaintPanel,
 * 		-shapeChooserPanel:ShapeChooserPanel, -colorChooserPanel:ColorChooserPanel
 * 		-colorChooserPanel2:ColorChooserPanel
 * RESPONDS-TO:+getUndoneShape():ArrayList<Shape> UndoneShapes,+actionPerformed(actionEvent e):void
 *             -createMenuBar():JMenuBar menuBar,+getPaintPanel():PaintPanel paintPanel
 *             +getShapeChooserPanel():ShapeChooserPanel shapeChooserPanel
 *             +getColorChooserPanel():ColorChooserPanel colorChooserPanel
 *             +getColorChooserPanel2():ColorChooserPanel2 colorChoosePanel2
 * 
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model;//model is an instance of the PaintMod and it will keep track of shapes
	
	private ArrayList<Shape> UndoneShapes=new ArrayList<Shape>();//keeps track of undone shapes when the undo is clicked
	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;//components that make up shape
	private ColorChooserPanel colorChooserPanel;//component that make primary colors and thickness

	private ColorChooserPanel2 colorChooserPanel2;//component that make more colors
	private SymmetryPanel symmetryPanel;//contains all options for selecting the type of drawing symmetry
	private JPanel westPanel;//will hold shapechooserpanel and symmetrypanel

	private JFrame jpanelframe;
	private Resolution resolution;
	
	/**
	 * constructs the component view
	 * @param model
	 */
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setJMenuBar(createMenuBar(this, this.paintPanel));
		
		Container c=this.getContentPane();
		
		
		this.westPanel = new JPanel(new GridLayout(2,1));
		
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		westPanel.add(this.shapeChooserPanel);
		
		this.symmetryPanel = new SymmetryPanel(this);
		westPanel.add(this.symmetryPanel);
		this.symmetryPanel.setVisible(false);//this panel will only be visible if the MirroredSquiggle shape is selected
		
		c.add(westPanel, BorderLayout.WEST);
		
		this.model=model;
		//adding the components to the view
		this.paintPanel = new PaintPanel(model, this, resolution);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		this.colorChooserPanel = new ColorChooserPanel(this, this.paintPanel);
		c.add(this.colorChooserPanel,BorderLayout.NORTH);
		
		
		
		this.setSize(895, 675);//default size needed so buttons don't look cramped
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	public ColorChooserPanel getColorChooserPanel() {
		return colorChooserPanel;
	}
	
	public ColorChooserPanel2 getColorChooserPanel2() {
		return colorChooserPanel2;
	}
	public SymmetryPanel getSymmetryPanel() {
		return symmetryPanel;
	}
	
	public Resolution getResolution() {
		return resolution;
	}
	
	public void setResolutionVisibility(boolean i){
		resolution.setVisible(i);
	}
	
	public boolean getCCPVisibility(){
		return colorChooserPanel2.isVisible();
	}
	public void setCCPVisibility(boolean i){
		colorChooserPanel2.setVisible(i);
	}
	
	/**
	 * creates a menu bar with tabs File and Edit
	 * @return menuBar
	 */
	private JMenuBar createMenuBar(View view, PaintPanel paintpanel) { // Don't worry about it now 
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		//adds the the tabFile
		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('E', KeyEvent.SHIFT_DOWN_MASK+KeyEvent.CTRL_DOWN_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Set Resolution");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	new Resolution(paintpanel, view);
	        }
	    });
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem=new JMenuItem("Undo");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.setIcon(new ImageIcon("Images/UndoIcon.png"));
	    menuItem.addActionListener(this);
		menu.add(menuItem);
		

		menuItem=new JMenuItem("Redo");
		//makes the shortcut
		menuItem.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		menuItem.setIcon(new ImageIcon("Images/RedoIcon.png"));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu=new JMenu("Help");
		//makes the shortcut
		menuItem=new JMenuItem("Information");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(menu);
		
		//clear button added to the menu bar so that it is easier to access
		JButton Clear;
		Clear = new JButton();
		Clear.setName("Clear");
		Clear.setOpaque(true);
		Clear.setBorderPainted(false);
		Clear.setContentAreaFilled(false);
		Clear.setIcon(new ImageIcon("Images/ClearIcon.png"));
		Clear.addActionListener(this);
		menuBar.add(Clear);
		

		//JButton j = new JButton("Set Resolution");
		//j.addActionListener(this);
		//menuBar.add(j);


		return menuBar;
	}
	/**
	 * controller aspect of view
	 * Add action to Undo, Redo, New and clear
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Undo"){
				//if shape list is not empty, it removes the last shape and 
				//adds it to undone shapes list
				if(model.getShapes().size()!=0){
					UndoneShapes.add(model.getShapes().get(model.getShapes().size()-1));
					model.removeShape(model.getShapes().size()-1);}}
		
		if(e.getActionCommand()=="Redo"){
				//it does the reversal of undo
				if(UndoneShapes.size()!=0){
					model.addShape(UndoneShapes.get(UndoneShapes.size()-1));
					UndoneShapes.remove(UndoneShapes.size()-1);
				}}
		if(e.getActionCommand()=="New"){
			//makes new paint
			Paint paint2=new Paint();
		}
		if(((JComponent) e.getSource()).getName()=="Clear"){
			    //clear everything inside shape list
				model.SetShape(new ArrayList<Shape>());
		}
		if(e.getActionCommand()=="Exit"){
		    // Exit the paint panel
			System.exit(0);
		}
		if(e.getActionCommand()=="Information"){
			//provides information for the use of this program
			//It does this application through a pop up.
			StringBuilder sb = new StringBuilder();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/subwayIcon2.png").
					getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
			List<String> lines = new ArrayList<String>();
			try {
				lines = Files.readAllLines(Paths.get("TextFiles/HelpText2.txt")); // Reads .txt file
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for (String s : lines)
			     sb.append(s+"\n"); // Converts list of strings, into string
			JOptionPane.showMessageDialog(this, sb, 
					"Instructions", JOptionPane.PLAIN_MESSAGE, imageIcon);
		}

	}
	public ArrayList<Shape> getUndoneShapes(){
		return UndoneShapes;
	}
	
}
