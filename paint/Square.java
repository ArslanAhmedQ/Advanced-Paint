
package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Create a Square
 * IS-A:        extends from Shape. 
 * HAS-A:       -width: integer 
 * RESPONDS-TO: +getWidth:Integer, +setWidth(int width):void,
 * 				+draw(Graphics2d g2d):void
 */

public class Square extends Shape {
	// width of the Square, no need of length since all the sides are equal to each other 
	private int width;
	
	 
	/**
	 * Constructs a new Square with the width, initial position, color and thickness  
	 * @param width: the width of the square of all sides
	 * @param position: the initial position of the square
	 * @param color: the color of square
	 * @param fill: it is boolean so it determines if square is filled or not
	 * @param thickness: the thickness of sides of square
	 */
	public Square(int width, Point position, Color color, boolean fill, int thickness){  
		super(position, color, thickness);
		this.width=width;
		this.fill = fill;
	}	
	
	public int getwidth() {
		return width;
	}

	public void setwidth(int width) {
		this.width = width;
	}
	
	/**
	 *This void method draws the square.  
	 * 
	 */
	public void draw(Graphics2D g2d){
		int x0 = this.getPosition().getX();
		int y0 = this.getPosition().getY();
		width = this.getwidth();
		g2d.setColor(this.color);
		g2d.setStroke(new BasicStroke(this.thickness));
		// If fill is called, g2d.fillRect fills the square
		// with chosen color. Otherwise, g2d.fillRect draws the square without any fill 
		if(fill) {
			g2d.fillRect(x0, y0, width, width);
		}
		else {
			g2d.drawRect(x0, y0,width, width);		
		}
	}
	
}
