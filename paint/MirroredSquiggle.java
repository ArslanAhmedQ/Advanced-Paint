package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * creates a new MirroredSquiggle
 *	IS-A:Shape
 *	HAS-A: -ArrayList<Point> points, -String symmetry, -int xb, -int yb;
 *	RESPONDS-TO: +getPoints():void,+setPoints(ArrayList<Point> points):void
 *				 +draw(Graphics2D g2d)
 */
public class MirroredSquiggle extends Shape {
	private ArrayList<Point> points=new ArrayList<Point>();//list of Points that make up a MirroredSquiggle
	
	private String symmetry;//the type of symmetry to apply when drawing; "Vertical", "Horizontal", "Quadrant", or "Mandala"
	private int xb;//the width of the PaintPanel that is going to be drawn on
	private int yb;//the height of the PaintPanel that is going to be drawn on
	
	/**
	 * Constructs a new MirroredSquiggle
	 * @param type 	 : the type of the shape (MirroredSquiggle)
	 * @param points :list of needed points to draw a miroredsquiggle
	 * @param color
	 * @param thickness
	 */
	public MirroredSquiggle(String type,ArrayList<Point> points, Color color, int thickness, String symmetry, int xb, int yb) {
		super(new Point(0,0), color, thickness); // position doesn't matter for squiggle
		this.points=points;
		this.symmetry=symmetry;
		this.xb=xb;
		this.yb=yb;

	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	/**
	 * draws a MirroredSquiggle using g2d.
	 * 
	 * The way it is drawn depends on this.symmerty.
	 * 
	 * If this.symmetry == "Horizontal", it draws the squiggle as well as its vertical reflection in the horizontal
	 * line at half the height of the canvas
	 * If this.symmetry == "Vertical", it draws the squiggle as well as its horizontal reflection in the vertical
	 * line at half the width of the canvas
	 * If this.symmetry == "Quadrant", it draws the squiggle as well as its horizontal reflection in the vertical
	 * line at half the width of the canvas, its vertical reflection in the horizontal line at half the height 
	 * of the canvas, and another copy of itself after being reflected in both those lines
	 * If this.symmetry == "Mandala", it draws the squiggle as well as 5 copies, each rotated 60 degrees
	 * counter clockwise from the previous one
	 */
	public void draw(Graphics2D g2d){
		
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
				
		if (this.symmetry=="Vertical"){
			this.drawVSymmetric(g2d);
		}
		else if (this.symmetry=="Horizontal"){
			this.drawHSymmetric(g2d);
		}
		else if (this.symmetry=="Quadrant"){
			this.drawBSymmetric(g2d);
		}
		else if (this.symmetry=="Mandala"){
			this.drawMandala(g2d);
		}
		else{
			//draw squiggle regularly
			ArrayList<Point> points = this.getPoints();
			//goes through a list of points and draw a line between each pair of points
			for(int i=0;i<points.size()-1; i++){
				Point p1=points.get(i);
				Point p2=points.get(i+1);
				g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		
		}
	}
	/**
	 * draws a squiggle, as well as 5 copies of it, each rotated 60 degrees (about the center of the canvas)
	 * counter clockwise from the previous one, using g2d
	 * @param g2d
	 */
	private void drawMandala(Graphics2D g2d) {
		Point center = new Point((int)this.xb/2, (int)this.yb/2);
		//makes a point with the coordinates of the center of the PaintPanel that g2d is in (technically not true, 
		//it only does this since the rest of the code in other classes only uses the g2d from the same PaintPanel
		//instance that xb and yb are from)
		
		//in the code below, AffineTransform is used so that only one object gets rotated, and not the following objects
		//ie. getTransform resets the rotation of the Graphics2D object (g2d) to what it was before it was rotated
		//this idea (to use AffineTransform in that way) was taken from:
		//    http://stackoverflow.com/questions/14124593/how-to-rotate-graphics-in-java
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			//draws initial line
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			
			AffineTransform old = g2d.getTransform();//store state of g2d
			
			//rotates by 60 degrees and draws the same line again
			g2d.rotate(Math.PI/3, center.getX(), center.getY());			
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			
			//rotates by another 60 degrees and draws the same line again
			g2d.rotate(Math.PI/3, center.getX(), center.getY());
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			
			//and again 3 more times
			g2d.rotate(Math.PI/3, center.getX(), center.getY());
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			g2d.rotate(Math.PI/3, center.getX(), center.getY());
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			g2d.rotate(Math.PI/3, center.getX(), center.getY());
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			
			g2d.setTransform(old);//reverts the state of g2d so that shapes drawn afterwards
									//aren't rotated
		}
		
	}
	/**
	 * draws a squiggle, as well as its horizontal reflection in the vertical
	 * line at half the width of the canvas
	 * @param g2d
	 */
	private void drawVSymmetric(Graphics2D g2d) {
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());//regular line
			g2d.drawLine(this.xb-p1.getX(), p1.getY(), this.xb-p2.getX(), p2.getY());//horizontally reflected line
		}
	}
	/**
	 * draws a squiggle, as well as its vertical reflection in the horizontal line at half the height 
	 * of the canvas
	 * @param g2d
	 */
	private void drawHSymmetric(Graphics2D g2d) {
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());//regular line
			g2d.drawLine(p1.getX(), this.yb-p1.getY(), p2.getX(), this.yb-p2.getY());//vertically reflected line
		}
	}
	
	/**
	 * draws a squiggle, as well as its horizontal reflection in the vertical
	 * line at half the width of the canvas, its vertical reflection in the horizontal line at half the height 
	 * of the canvas, and another copy of itself after being reflected in both those lines
	 * @param g2d
	 */
	private void drawBSymmetric(Graphics2D g2d) {
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());//regular line
			
			g2d.drawLine(this.xb-p1.getX(), p1.getY(), this.xb-p2.getX(), p2.getY());//horizontally reflected line
			g2d.drawLine(p1.getX(), this.yb-p1.getY(), p2.getX(), this.yb-p2.getY());//vertically reflected line
			g2d.drawLine(this.xb-p1.getX(), this.yb-p1.getY(), this.xb-p2.getX(), this.yb-p2.getY());//dually reflected line
		}
	}
	
	
	
}
