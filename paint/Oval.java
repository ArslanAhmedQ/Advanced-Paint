package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;
/**
 * creates a new oval
 * IS-A:Shape
 * HAS-A:- width, radius
 * RESPONDS-TO:+getCentre():Point position,+setCentre(Point centre):void
 * 			   +getHeight():int height, +setHeight(int height):void
 * 			   +getWidth():int width, +setWidth(int width):void
 * 			   +draw(Graphics2d g2d):void
 */

public class Oval extends Shape{
	
	// oval has two components width and height
	private int width;
	private int height;
	
	
	/**
	 * constructs a new circle 
	 * @param centre: center of the oval
	 * @param width: integer, width of the oval
	 * @param height: integer, height of the oval
	 * @param color: color object, color of the oval shape
	 * @param fill: it is boolean, if the oval is filled or not
	 * @param thickness: integer, decides the thickness of the oval
	 */
	public Oval(Point centre, int width, int height, Color color, boolean fill, int thickness){
		super(centre, color, thickness);
		this.width = width;
		this.height = height;
		
		this.fill = fill;
	}
	
	
	public Point getCentre() {
		return position;
	}
	
	public void setCentre(Point centre) {
		this.position = centre;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	/**
	 * draws a oval
	 * @param Graphics2D g2d: uses the graphics2d to draw
	 */
	public void draw(Graphics2D g2d){
		//stores the initial x and initial y 
		int x0=this.position.getX();
		int y0=this.position.getY();
		
		int width=this.width;
		int height = this.height;
		
		//Let's tell graphics to apply those attributes of the oval
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		if(fill){
			g2d.fillOval(x0, y0, width, height);
		}
		else{
			g2d.drawOval(x0, y0, width, height);
		}
	}

}
