package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * IS-A: Shape
 * HAS-A: -pf Point, -position Point, -color:Color ,-thickness:int, -isLAstSegment
 * RESPONDS-TO: +draw(), +drawFinal()
 * 
 * Polylgon is really a Polygon segment, consisting of one or more sides of the polygon, and the factory (which uses Strategy design
 * pattern) constructs each segment into a single polygon.
 * This was done so that the undo feature would remove one side of the polygon at a time, rather than the entire
 * polygon. 
 */
public class Polygon extends Shape{
	private ArrayList<Point> points = new ArrayList<Point>();
	private Point pf;
	private boolean isLastSegment;//if this polygon segment is the last segment of a completed polygon
	
	public Polygon(Point p0, Point pf, Color color, boolean fill, int thickness) {
		super(p0, color, thickness);
		this.pf = pf;
		this.fill = fill;
	}
	
	/**
	 * draws the next segment of the polygon. If the last point in the polygon is within a distance of 10 (vertical and
	 * horizontal) from he initial point, it calls drawFinal instead
	 * 
	 * @param g2d Graphics2D object
	 */
	public void draw(Graphics2D g2d){
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		if((Math.abs(pf.getX() - points.get(0).getX()) < 10) && (Math.abs(pf.getY() - points.get(0).getY()) < 10)){
				this.drawFinal(g2d);
		}
		else{
			g2d.drawLine(position.getX(), position.getY(), pf.getX(), pf.getY());
		}
		
		
	}
	/**
	 * Draws the completed polygon
	 * 
	 * Precondition: The final point in the polygon must be within a distance of 10 (vertically and horizontally)
	 * of the initial point in the polygon
	 * 
	 * @param g2d Graphics2D object
	 */
	public void drawFinal(Graphics2D g2d){
		
		//g2d.drawPolygon needs an int array of x coords, and an int array of y coords, so the code below constructs these
		//arrays from the Polygon's list of Points
		int[] xpoints = new int[this.points.size()];
		int[] ypoints = new int[this.points.size()];
		for (int i=0; i < this.points.size(); i++){
			xpoints[i] = (int)this.points.get(i).getX();
			ypoints[i] = (int)this.points.get(i).getY();
		}

		this.isLastSegment = true;//the polygon is complete, so this segment is the final one
		
		//fill or draw the polygon
		if(this.fill){
			g2d.fillPolygon(xpoints, ypoints, xpoints.length);
		}
		else{
			g2d.drawPolygon(xpoints, ypoints, xpoints.length);
		}
		
		
	}
	public ArrayList<Point> getPoints(){
		return this.points;
	}
	public void setPoints(ArrayList<Point> newpoints){
		this.points = newpoints;
	}

	public Point getLastPoint(){
		return this.pf;
	}
	
	public void addPoint(Point p){
		this.points.add(p);
	}
	public boolean getIsLastSegment(){
		return this.isLastSegment;
	}

	public void setLastPoint(Point point) {
		this.pf = point;
	}

}
