package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *  creates a new Dotted Squiggle
 *	IS-A:Shape
 *	HAS-A: -ArrayList<Point> points;
 *	RESPONDS-TO: +getPoints():void,+setPoints(ArrayList<Point> points):void 
 *				 +draw(Graphics2D g2d): void
 */
public class DottedSquiggle extends Shape {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	/**
	 * Constructs a new DottedSquiggle
	 * @param type 	 : DottedSquiggle
	 * @param points :list of needed points to draw DottedSquiggle
	 * @param color  : color of the DottedSquiggle
	 * @param thickness: Thickness of DottedSquiggle
	 */
	public DottedSquiggle(String type,ArrayList<Point> points, Color color, int thickness) {
		super(new Point(0,0), color, thickness); 
		this.points=points;

	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	/**
	 * draws a dotted Squiggle
	 */
	public void draw(Graphics2D g2d){
		
		
		ArrayList<Point> points = this.getPoints();
		//goes through a list of points and draw a dotted line between each pair of points
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i);
			g2d.setColor(this.color);
			g2d.setStroke(new BasicStroke(this.thickness));
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());

		
		}
	}
}
