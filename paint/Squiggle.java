package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * creates a new Squiggle
 *	IS-A:Shape
 *	HAS-A: -ArrayList<Point> points;
 *	RESPONDS-TO: +getPoints():void,+setPoints(ArrayList<Point> points):void
 *				 +draw(Graphics2D g2d)
 */
public class Squiggle extends Shape {
	private ArrayList<Point> points=new ArrayList<Point>();//list of Points that make up a squiggle
	
	/**
	 * Constructs a new squiggle
	 * @param type 	 :squiggle
	 * @param points :list of needed points to draw squiggle
	 * @param color
	 * @param thickness
	 */
	public Squiggle(String type,ArrayList<Point> points, Color color, int thickness) {
		super(new Point(0,0), color, thickness); // position doesn't matter for squiggle
		this.points=points;

	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	/**
	 * draws a Squiggle
	 */
	public void draw(Graphics2D g2d){
		
		
		ArrayList<Point> points = this.getPoints();
		//goes through a list of points and draw a line between each pair of points
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.setColor(this.color);
			g2d.setStroke(new BasicStroke(this.thickness));
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());

		
		}
	}
}
