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
public class PolygonManipulator implements ShapeManipulatorStrategy{
	/**
	 * 
	 * Constructs a Polygon (which is a Shape) instance and adds it to the model
	 * 
	 * @param type: the type of Shape to be built
	 * @param x0: the x coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param y0: the y coordinate of the initial position of a mouse drag (or of the location of a mouse press)
	 * @param xf: the x coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param yf: the y coordinate of the final position of a mouse drag (or of the location of a mouse release)
	 * @param points: a list of Point objects that will be used to construct the Polygon instance
	 * @param color: a Color object indicating what color the shape should be
	 * @param fill: a boolean marking whether or not the shape should be filled
	 * @param thickness: an integer indicating the thickness that the shape's 
	 * @param model: the PaintModel instance that the shape is to be added to
	 * @param dragOccurred: a boolean marking whether or not the mouse event that caused this shape to be constructed was a drag
	 * @param e: a MouseEvent instance (contains information like whether a left or right click was used)
	 *
	 */
	public void Construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points, Color color, boolean fill, int thickness, PaintModel model, boolean dragOccurred, MouseEvent e) {
		Polygon poly = new Polygon(new Point(x0,y0), new Point(xf,yf), color, fill, thickness);//creates a default polyline to be modified later
		
		ArrayList<Shape> shapes = model.getShapes();//gets the list of shapes from the model
		//this is needed because polygon segment should only connect to the previous polygon segment if it is the most recent shape
		//ie. if a polygon is drawn, then a circle is drawn, the next polygon should not connect to the old one
		
		
		if (shapes.size() > 0 && shapes.get(model.getShapes().size()-1).getClass().isInstance(poly)){//if last shape drawn was Polygon
			
			Polygon prev_poly = (Polygon)model.getShapes().get(model.getShapes().size()-1);//gets previous shape if it is a Polygon
			
			points = prev_poly.getPoints();
			poly.setPoints(prev_poly.getPoints());
			if (dragOccurred){
				poly.setPosition(new Point(prev_poly.getPosition().getX(), prev_poly.getPosition().getY()));
			}//If the mouse is doing a drag, then the feedback should show from the point where the mouse was clicked
				//since this is where the polygon segment starts from
			
			else{
				poly.setPosition(new Point(prev_poly.getLastPoint().getX(), prev_poly.getLastPoint().getY()));
				//on a new mouse press, the next segment of the polygon is being drawn, so the line needs to start from
				//the endpoint of the previous segment
				if(prev_poly.getIsLastSegment()){
					poly.setPoints(new ArrayList<Point>());
					poly.setPosition(new Point(x0, y0));
				}
			}
			if (SwingUtilities.isRightMouseButton(e)){
				//on rightclick, close previous polygon by adding its first point to the end of its points list
				// and begin a new polygon
				if(!dragOccurred){
					
					if (!prev_poly.getIsLastSegment()){
						prev_poly.addPoint(prev_poly.getLastPoint());
					}
					prev_poly.setLastPoint(prev_poly.getPoints().get(0));
					model.addShape(prev_poly);
					poly.setPoints(new ArrayList<Point>());//on right press (not drag), start new line (reset list of points)
				}
				
				poly.setPosition(new Point(x0,y0));
			}
		}
		

		
		poly.addPoint(poly.getPosition());
		
		
		if (dragOccurred){
			model.getShapes().remove(model.getShapes().size()-1);//remove previous shape for feedback purposes
		}
		
		
		
		
		
		model.addShape(poly);
	}

}
