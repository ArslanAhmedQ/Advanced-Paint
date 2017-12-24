package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * IS-A: N/A,NOTE: it works exactly like a class that implements ShapeManipulatorStrategy, only with a few extra parameters needed
 * HAS-A: None
 * RESPONDS-TO: +Construct
 */
public class MirroredSquiggleManipulator{//doesn't implement ShapeManipulatorStrategy because it needs a few extra parameters in order to 
									//implement the symmetrical drawing mode
	/**
	 * 
	 * Constructs a MirroredSquiggle (which is a Shape) instance and adds it to the model
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
	public void Construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points, Color color, boolean fill, 
			int thickness, PaintModel model, boolean dragOccurred, MouseEvent e, String symmetry, int xb, int yb){
		if (dragOccurred){
			model.getShapes().remove(model.getShapes().size()-1);//remove the previous shape if a drag occurred in order
																	//for user to see feedback on the shape being drawn
																	//without having multiple shapes
		}
		
		//creates a MirroredSquiggle object and adds to model
		model.addShape(new MirroredSquiggle("MirroredSquiggle",points, color, thickness, symmetry, xb, yb));
	}
}
