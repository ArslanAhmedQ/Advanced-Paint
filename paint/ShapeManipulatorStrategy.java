package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 * IS-A: Interface
 * HAS-A: None
 * RESPONDS-TO: +Construct
 */
public interface ShapeManipulatorStrategy {
	/**
	 * 
	 * Abstract method to be implemented
	 * 
	 * @param type: the type of Shape to be built
	 * @param x0: the x coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param y0: the y coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param xf: the x coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param yf: the y coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param points: (only use to draw line related shapes) a list of Point objects that will be used to 
	 * 					construct the Shape instance
	 * @param color: a Color object indicating what color the shape should be
	 * @param fill: a boolean marking whether or not the shape should be filled
	 * @param thickness: an integer indicating the thickness that the shape's 
	 * @param model: the PaintModel instance that the shape is to be added to
	 * @param dragOccurred: a boolean marking whether or not the mouse event that caused this shape to be constructed was a drag
	 * @param e: a MouseEvent instance (contains information like whether a left or right click was used)
	 *
	 */
	public void Construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points, Color color, boolean fill, 
			int thickness, PaintModel model, boolean dragOccurred, MouseEvent e);
}	