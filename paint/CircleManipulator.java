package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * IS-A: N/A, but implements ShapeManipulatorStrategy
 * HAS-A: None
 * RESPONDS-TO: +Construct
 */
public class CircleManipulator implements ShapeManipulatorStrategy{
	/**
	 * 
	 * Constructs a Circle (which is a Shape) instance and adds it to the model
	 * 
	 * @param type: the type of Shape to be built
	 * @param x0: the x coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param y0: the y coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param xf: the x coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param yf: the y coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param points: (only used for line related shapes)
	 * @param color: a Color object indicating what color the shape should be
	 * @param fill: a boolean marking whether or not the shape should be filled
	 * @param thickness: an integer indicating the thickness that the shape's 
	 * @param model: the PaintModel instance that the shape is to be added to
	 * @param dragOccurred: a boolean marking whether or not the mouse event that caused this shape to be constructed was a drag
	 * @param e: a MouseEvent instance (contains information like whether a left or right click was used)
	 */
	public void Construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points, Color color, boolean fill, int thickness, PaintModel model, boolean dragOccurred, MouseEvent e){
		int radius = (int)Math.sqrt((xf-x0)*(xf-x0) + (yf-y0)*(yf-y0))*2;
		// the way g2d.draw oval works, it uses the beginning and end of a mouse drag as the diameter
		//of the circle. Since we want that to be the radius.
		
		if (dragOccurred){
			model.getShapes().remove(model.getShapes().size()-1);
		}
		model.addShape(new Circle(new Point(x0-radius/2, y0-radius/2),radius, color, fill, thickness));
		//x0 and y0 are the center coordinates of the circle. Due to the way g2d draws the 
		//circle, this point needs to be shifted to the left by radius/2 and up by radius/2
	}
}
