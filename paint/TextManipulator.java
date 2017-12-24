package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * IS-A: N/A,NOTE: it works exactly like a class that implements ShapeManipulatorStrategy, only with a few extra parameters needed
 * HAS-A: None
 * RESPONDS-TO: +Construct
 */
public class TextManipulator implements ShapeManipulatorStrategy,ActionListener{
	
	private String userInput;//saves user input
	private JFrame UserInput=new JFrame("User Input");//responsible for the GUI of the prompt
	private JTextField input= new JTextField(20);//provides area for user input
	private JButton Ok=new JButton("Accept");//making sure of the user's choice
	private Point initialPoint;//the point where string should be printed
	private Color color;//the color of the string
	private int thickness;//the size of the string
	private PaintModel model;//add the string to the list
	
	/**
	 * 
	 * Constructs a circle (which is a Shape) instance and adds it to the model
	 * 
	 * @param type: the type of Shape to be built
	 * @param x0: the x coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param y0: the y coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param xf: the x coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param yf: the y coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param points: a list of Point objects that will be used to construct the MirroredSquiggle instance
	 * @param color: a Color object indicating what color the shape should be
	 * @param fill: a boolean marking whether or not the shape should be filled
	 * @param thickness: an integer indicating the thickness that the shape's 
	 * @param model: the PaintModel instance that the shape is to be added to
	 * @param dragOccurred: a boolean marking whether or not the mouse event that caused this shape to be constructed was a drag
	 * @param e: a MouseEvent instance (contains information like whether a left or right click was used)
	 * @param symmetry: the type of symmetry to apply when drawing; "Vertical", "Horizontal", "Quadrant", or "Mandala"
	 * @param xb: the width of the PaintPanel instance that is going to be drawn on
	 * @param yb: the height of the PaintPanel instance that is going to be drawn on
	 */
	public void Construct(String type, int x0, int y0, int xf, int yf, ArrayList<Point> points, Color color,
			boolean fill, int thickness, PaintModel model, boolean dragOccurred, MouseEvent e) {
		initialPoint=new Point(x0,y0);
		this.color=color;
		this.thickness=thickness;
		this.model=model;
		
		if(!dragOccurred){
			//it asks the user for the input using a popup window
			JLabel info=new JLabel("Enter the Text:");
			UserInput.setLayout(new FlowLayout());
			UserInput.add(info);
			UserInput.getContentPane().add(input);
			UserInput.getContentPane().add(Ok);
			Ok.addActionListener(this);
			UserInput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			UserInput.pack();
			UserInput.setVisible(true);}
	}
	
	public void actionPerformed(ActionEvent e) {
		userInput=input.getText();
		UserInput.dispose();
		model.addShape(new Text(initialPoint, color, thickness, userInput));
	}

}
