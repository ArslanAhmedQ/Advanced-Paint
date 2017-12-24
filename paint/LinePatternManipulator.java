package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
/**
 * IS-A: N/A, but implements ShapeManipulatorStrategy
 * HAS-A: None
 * RESPONDS-TO: +Construct
 */
public class LinePatternManipulator implements ShapeManipulatorStrategy{
	/**
	 * 
	 * Constructs a LinePattern (which is a Shape) instance and adds it to the model
	 * 
	 * NOTE: this was a bug from polyline that we decided to implement as a feature.
	 * 
	 * @param type: the type of Shape to be built
	 * @param x0: the x coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param y0: the y coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param xf: the x coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param yf: the y coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param points: a list of Point objects that will be used to construct the LinePattern instance
	 * @param color: a Color object indicating what color the shape should be
	 * @param fill: a boolean marking whether or not the shape should be filled
	 * @param thickness: an integer indicating the thickness that the shape's 
	 * @param model: the PaintModel instance that the shape is to be added to
	 * @param dragOccurred: a boolean marking whether or not the mouse event that caused this shape to be constructed was a drag
	 * @param e: a MouseEvent instance (contains information like whether a left or right click was used)
	 *
	 */
	public void Construct(String type, int x0, int y0, int xf, int yf, ArrayList<Point> points, Color color,
			boolean fill, int thickness, PaintModel model, boolean dragOccurred, MouseEvent e) {
		
		LinePattern line = new LinePattern(new Point(x0,y0), new Point(xf,yf), color, thickness);//creates a default LinePattern to be modified later
		
		ArrayList<Shape> shapes = model.getShapes();//gets the list of shapes from the model
		//this is needed because polyline should only connect to the previous polyline if it is the most recent shape
		//ie. if a polyline is drawn, then a circle is drawn, the next polyline should not connect to the old one
		
		
		if (shapes.size() > 0 && shapes.get(model.getShapes().size()-1).getClass().isInstance(line)){//if last shape drawn was Polyline
			
			LinePattern prev_line = (LinePattern)model.getShapes().get(model.getShapes().size()-1);//gets previous shape if it is a Polyline
			
			points = prev_line.getPoints();
			if (dragOccurred == true){
				line.setPosition(new Point(prev_line.getPosition().getX(), prev_line.getPosition().getY()));					
			}
			
			else{
				line.setPosition(new Point(prev_line.getLastPoint().getX(), prev_line.getLastPoint().getY()));
				//on a new mouse press, the next segment of the linepattern is being drawn, so the line needs to start from
				//the endpoint of the previous segment
			}
			
			if (SwingUtilities.isRightMouseButton(e)){
				if(!dragOccurred){
					points = new ArrayList<Point>();//on right press (not drag), start new line pattern
				}
				line.setPosition(new Point(x0,y0));
			}//set starting position of linepattern to location of mouse 
			//(instead of end of previous linepattern) on right press		
		}
		
		for(Point i: points){
			line.addPoint(i);
		}//add all points to current linepattern
		
		line.addPoint(line.getPosition());
		line.addPoint(line.getLastPoint());
		
		if (dragOccurred){
			model.getShapes().remove(model.getShapes().size()-1);
		}//remove previous line pattern and add the new one
		//this way, undo removes the entire line pattern instead of just the last line

		model.addShape(line);
	}


}
