package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/**
 * class Factory
 * 
 * constructs Shapes
 * IS-A:None
 * HAS-A:None 
 * RESPONDS-TO:+getPosition():Point, +setPosition(Point position):void
 * 			   +getColor():String, +set
 */
public class Factory {
	/**
	 * 	Construct and return an instance of a subclass of Shape (Circle, Square, Rectangle, Squiggle, or Polyline)
	 * @param type the type of shape to be constructed
	 * @param x0 int; the x coordinate of the Point where the mouse was pressed. Must be positive
	 * @param y0 int; the y coordinate of the Point where the mouse was pressed. Must be positive
	 * @param xf int; the x coordinate of the Point where the mouse was dragged to. Must be positive
	 * @param yf int; the y coordinate of the Point where the mouse was dragged to. Must be positive
	 * @param points ArrayList<Point>; an ArrayList of all the Points that were drawn (only used to create Squiggle shapes)
	 * @param color Color; the color the shape being constructed should be
	 * @param fill boolean; true if the shape being constructed should be filled, false if it should be just the outline
	 * @param thickness int; the thickness of the shape (thickness of the border for closed shapes
	 * @param model PaintModel; The model containing all the shapes that have been drawn
	 * @param dragOccurred Boolean; true if the factory is called from a mouseDragged method, false otherwise
	 * @param e MouseEvent; used to determine which mouse button was clicked. Right click starts a new Polyline.
	 */
	ShapeManipulatorStrategy strategy;
	public void construct(String type, int x0, int y0, int xf, int yf,ArrayList<Point> points, Color color, boolean fill, 
			int thickness, PaintModel model, boolean dragOccurred, MouseEvent e, String symmetry, int xb, int yb){
		if (type == "Circle"){
			strategy=new CircleManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
			
		}else if(type=="Squiggle"){
			strategy=new SquiggleManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		} 
		else if(type=="MirroredSquiggle"){
			MirroredSquiggleManipulator sq =new MirroredSquiggleManipulator();//needs some exra params for symmetry to work, so doesn't implement the Strategy
			sq.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e, symmetry, xb, yb);
		} 
		else if(type=="Rectangle") {
			strategy=new RectangleManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		}
	
		else if(type=="Square") {
			strategy=new SquareManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		}
		
		else if (type=="Polyline"){
			strategy=new PolyLineManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		}
		else if (type=="LinePattern"){
			strategy=new LinePatternManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
			
		}
		else if (type=="DottedSquiggle"){
			strategy=new DottedSquiggleManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
			
		}
		else if (type=="Oval"){
			strategy=new OvalManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		}
		else if (type=="Text"){
			strategy=new TextManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		}
		else if (type=="Polygon"){
			strategy=new PolygonManipulator();
			strategy.Construct(type, x0, y0, xf, yf, points, color, fill, thickness, model, dragOccurred, e);
		}
	}
}
