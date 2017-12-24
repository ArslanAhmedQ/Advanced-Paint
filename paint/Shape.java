package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Create a shape
 * IS-A:None
 * HAS-A:+position:Point ,+color:Color ,+thickness:int ,+fill:boolean 
 * RESPONDS-TO:+getPosition():Point, +setPosition(Point position):void
 * 			   +isFill():boolean, +setFill(boolean fill):void
 *             +getColor():String, +setColor(Color color):void
 *             +getThickness():String, +setThickness(int thickness):void
 *             +draw(Graphics2d g2d)
 */

public class Shape {
	//position keeps track of the initial position of the shape
	Point position;
	//color keeps track of the color of each instance of the shape
	Color color;
	//thickness keeps track of the thickness of the lines of the shape
	int thickness;
	//fill will check if the shape is filled. If not, it is going to
	// fill it with the attribute color
	boolean fill;
	
	/**
	 * Constructs a new shape using the initial position, color and thickness
	 * 
	 * @param position :the initial position of the shape
	 * @param color :the color of the shape 
	 * @param thickness :the thickness of the lines of the shape
	 */
	public Shape(Point position, Color color, int thickness){
		this.position=position;
		this.color=color;
		this.thickness=thickness;
		
	}
	
	
	
	/**
	 * returns the initial position of the shape
	 * @return position :which is the initial position of the shape
	 */
	public Point getPosition() {
		return position;
	}
	
	
	
	/**
	 * sets the initial position of the shape to a new position
	 * @param position :the new position
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	
	
	/**
	 * returns the color of the shape
	 * @return color :color of the shape
	 */
	public Color getColor() {
		return this.color;
	}
	
	
	
	/**
	 * sets the color of the shape to a new color
	 * @param color :new given color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	/**
	 * returns the thickness of the lines of the shape
	 * @return thickness: the thickness of the shape
	 */
	public int getThickness() {
		return thickness;
	}
	
	
	
	/**
	 * sets the thickness of the lines to a new thickness
	 * @param thickness :new given thickness
	 */
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	
	
	
	/**
	 * checks if the shape is filled
	 * @return fill :shape is filled(boolean)
	 */
	
	public boolean isFill() {
		return fill;
	}
	
	/**
	 * sets true if the shape is fill. false otherwise
	 * @param fill
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	/**
	 * draw the shape
	 * @param g2d
	 */
	public void draw(Graphics2D g2d){
		
	}
	
}
