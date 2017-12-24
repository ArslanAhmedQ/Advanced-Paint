package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
/**
 * IS-A: N/A,NOTE: it works exactly like a class that implements ShapeManipulatorStrategy, only with a few extra parameters needed
 * HAS-A: None
 * RESPONDS-TO: +Construct
 */
public class PolyLineManipulator implements ShapeManipulatorStrategy  {

	/**
	 * 
	 * Constructs a Polyline (which is a Shape) instance and adds it to the model
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
		
			Polyline poly = new Polyline(new Point(x0,y0), new Point(xf,yf), color, thickness);//creates a default polyline to be modified later
			
			ArrayList<Shape> shapes = model.getShapes();//gets the list of shapes from the model
			//this is needed because polyline should only connect to the previous polyline if it is the most recent shape
			//ie. if a polyline is drawn, then a circle is drawn, the next polyline should not connect to the old one
			
			
			if (shapes.size() > 0 && shapes.get(model.getShapes().size()-1).getClass().isInstance(poly)){//if last shape drawn was Polyline
				
				Polyline prev_poly = (Polyline)model.getShapes().get(model.getShapes().size()-1);//gets previous shape if it is a Polyline
				
				if (dragOccurred){
					poly.setPosition(new Point(prev_poly.getPosition().getX(), prev_poly.getPosition().getY()));
					
				}//If the mouse is doing a drag, then the feedback should show from the point where the mouse was clicked
					//since this is where the polyline segment starts from
				
				
				else{	
					poly.setPosition(new Point(prev_poly.getLastPoint().getX(), prev_poly.getLastPoint().getY()));
					//on a new mouse press, the next segment of the polyline is being drawn, so the line needs to start from
					//the endpoint of the previous segment
				}
				
				if (SwingUtilities.isRightMouseButton(e)){
					poly.setPosition(new Point(x0,y0));
				}
						
			}
			if (dragOccurred){
				model.getShapes().remove(model.getShapes().size()-1);
			}
			model.addShape(poly);
		}

}
