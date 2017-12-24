package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * creates a Text 
 * IS-A:Shape
 * HAS-A:-userInput:String,-TextAttribute:Font 
 * RESPONDS-TO: +Text(Point position, Color color, int thickness, String userInput):void,
 * +draw(Graphics2D g2d):void, +getUserInput():String, +setUserInput(String userInput):void
 */

public class Text extends Shape{
	private String userInput; //stores the user's input
	private Font TextAttribute;//text attributes
	
	/**
	 * constructs a Text
	 * @param position the position of the text
	 * @param color the color of the text
	 * @param thickness the size of the text
	 * @param userInput 
	 */
	public Text(Point position, Color color, int thickness, String userInput) {
		super(position, color, thickness);
		this.fill=fill;
		this.userInput=userInput;
	}
	
	/**
	 * draws a Text
	 * @param Graphics2D g2d: uses the graphics2d to draw
	 */
	public void draw(Graphics2D g2d){
		//stores the initial x and initial y 
		int x0=this.position.getX();
		int y0=this.position.getY();
		//Let's tell graphics to apply those attributes of the circle 
		g2d.setColor(this.color);
		TextAttribute = new Font("Comic Sans MS", Font.BOLD, thickness*15);
		g2d.setFont(TextAttribute);
		g2d.drawString(userInput, x0, y0);
		
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}


}
