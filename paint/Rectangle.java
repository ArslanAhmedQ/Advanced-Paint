package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Create a rectangle
 * IS-A:        extends from Shape. 
 * HAS-A:       -length: integer, -width: integer 
 * RESPONDS-TO: +getWidth:Integer, +setWidth(int width):void,
 * 				+getLength:Integer, +setLength(int width):void,
 * 			    +draw(Graphics2d g2d):void
 */

public class Rectangle extends Shape {
	// width of the rectangle
	private int width;
	// length of the rectangle 
	private int length;
	

	/**
	 * Constructs a new Rectangle with the length, width, initial position, color and thickness  
	 * @param length: the length of the rectangle 
	 * @param width: the width of the rectangle
	 * @param position: the initial position of the rectangle
	 * @param color: the color of rectangle
	 * @param fill: it is boolean so it determines if rectangle is filled or not
	 * @param thickness: the thickness of sides of rectangle
	 */
	public Rectangle(int length, int width, Point position, Color color, boolean fill, int thickness){  // Constructor
		super(position, color, thickness);
		this.length = length;
		this.width = width;
		this.fill = fill;
	}
	
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	
	/**
	 * This void method draws the rectangle. 
	 * 
	 */
	public void draw(Graphics2D g2d){
		int x0 = this.getPosition().getX();
		int y0 = this.getPosition().getY();
		width = this.getWidth();
		length = this.getLength();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		// If fill is called, g2d.fillRect fills the rectangle
		// with chosen color. Otherwise, g2d.fillRect draws the rectangle without any fill 
		if(fill){
			g2d.fillRect(x0, y0, width, length);
		}
		else{
			g2d.drawRect(x0, y0, width, length);
		
			
		}
	}
	
	
}