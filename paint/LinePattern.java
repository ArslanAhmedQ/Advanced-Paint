package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * IS-A: Shape
 * HAS-A: -pf Point, -position Point, -color:Color ,-thickness:int
 * RESPONDS-TO: +draw()
 * 
 */
public class LinePattern extends Shape{

	private ArrayList<Point> points = new ArrayList<Point>();//list of points that makes up a linepattern
	private Point pf;//final point in the line pattern
	
	/**
	 * constructs a line pattern
	 * @param p0: initial point of line pattern
	 * @param pf: final point of line pattern
	 * @param color: color of line pattern
	 * @param thickness: thickness (stroke width) of the line pattern
	 */
	public LinePattern(Point p0, Point pf, Color color, int thickness) {
		super(p0, color, thickness);
		this.pf = pf;
	}
	
	/**
	 * Draws a pattern of lines that extend from the original point the mouse was clicked to the current mouse position
	 */
	public void draw(Graphics2D g2d){
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		
		int i =1;
		Point p1;
		Point p2;
		while(i<this.points.size() && this.points.size() > 0){
			p1 = this.points.get(i-1);
			p2 = this.points.get(i);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			++i;
		}
		
		
	}
	public ArrayList<Point> getPoints(){
		return this.points;
	}
	public Point getLastPoint(){
		return this.pf;
	}
	
	public void addPoint(Point p){
		this.points.add(p);
	}


}
