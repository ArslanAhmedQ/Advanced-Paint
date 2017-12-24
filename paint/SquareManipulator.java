package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *  creates a new Square
 *	IS-A: ShapeManipulatorStrategy
 *	HAS-A: None
 *	RESPONDS-TO: +getPoints():void, +Construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points,
 *                                  Color color, boolean fill, int thickness, PaintModel model, boolean dragOccurred, MouseEvent e): void
 */
public class SquareManipulator implements ShapeManipulatorStrategy{
	/**
	 * it constructs the rectangle, while still dragging is in progress it would delete the previous rectangle until the click has been released
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
	public void Construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points, Color color, boolean fill, int thickness, PaintModel model, boolean dragOccurred, MouseEvent e){
		int width = Math.abs(xf-x0);
		int length = Math.abs(yf-y0);
		int sidelength = Math.min(length,  width);
		//the side length of square will be the smallest out of the vertical mouse drag distance (height)
		//and the horizontal drag distance (width)
		
		int x,y;
		
		if (sidelength==width){//if horizontal drag distance is less than the vertical drag distance
			
			
			//Like rectangle, square must be drawn using the top left corner of the square
			//since  the size of the square only depends on width (x direction), the x coord
			//of this point is the smallest (furthest left) of x0 and xf
			x = Math.min(x0, xf);
			
			
			//however, depending on how the user drags the mouse, the height of the square will not depend
			//on y0 (it only depends on the width). Therefore, there are two cases for finding the y coord
			// of the top left point:
			// 1) if y0 is smaller (higher up) than yf, then y0 is the top coordinate.
			// 2) if yf is smaller (higher up) than y0, then the y coordinate of the top left point is
			//    y0 minus the height of the square. Since the height is the same as the width, this is given
			//	  by y = y0 - |xf-x0|
			if(y0<yf){
				y=y0;
			}
			else{
				y = Math.min(y0, y0 - Math.abs(xf-x0));
			}	
		}
		else{//if vertical drag distance is less than the horizontal drag distance
			
			//everything done below is the reverse of what was done in the previous 
			//case (all instances of xf and x0 were swapped with yf and y0, and vice versa))
			y = Math.min(y0, yf);
			if(x0<xf){
				x=x0;
			}
			else{
				x = Math.min(x0, x0 - Math.abs(yf-y0));
			}	
		}

		if (dragOccurred){
			model.getShapes().remove(model.getShapes().size()-1);
		}
		model.addShape(new Square(sidelength, new Point(x,y), color, fill, thickness));
	}
}
