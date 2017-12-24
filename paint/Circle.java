package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;
/**
 * Creates a new circle
 * IS-A:Shape
 * HAS-A:-radius:int
 * RESPONDS-TO:+getCentre():Point position,+setCentre(Point centre):void
 * 			   +getRadius():int Radius, +setRadius(int radius):void
 * 			   +draw(Graphics2d g2d):void
 * Creates an oval using g2d, however the behavior is different.
 * The initial point that is pressed will act as the center of the circle.
 * Dragging out will increase the radius.
 */

public class Circle extends Shape{
	//keeps track of the radius of the circle 
	private int radius;
	
	
	/**
	 * Constructs a new circle 
	 * @param centre The center point of the circle
	 * @param radius The radius of the circle
	 * @param color The color attributed to the circle
	 * @param fill The fill attributed to the circle
	 * @param thickness The fill attributed to the line of circle, when fill is not true
	 */
	public Circle(Point centre, int radius, Color color, boolean fill, int thickness){
		super(centre, color, thickness);
		this.radius = radius;
		this.fill = fill;
	}
	
	
	public Point getCentre() {
		return position;
	}
	
	public void setCentre(Point centre) {
		this.position = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	/**
	 * draws a circle
	 * @param Graphics2D g2d: uses the graphics2d to draw
	 */
	public void draw(Graphics2D g2d){
		//stores the initial x and initial y 
		int x0=this.position.getX();
		int y0=this.position.getY();
		int radius=this.radius;
		//Let's tell graphics to apply those attributes of the circle 
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		if(fill){
			g2d.fillOval(x0, y0, radius, radius);
		}
		else{
			g2d.drawOval(x0, y0, radius, radius);
		}
	}

}
