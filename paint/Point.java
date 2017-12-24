package ca.utoronto.utm.paint;

/**
 * Creates a Point
 * 
 * IS-A:         None 
 * HAS-A:       -x:integer, -y:integer, -isLastPoint:boolean 
 * RESPONDS-TO: +isLastPoint:boolean, +setisLastPoint(boolean isLastPoint):void,
 * 				+getX:Integer, +setX(int X):void,
 * 			    +getY:Integer, +setY(int Y):void,
 */
public class Point {
	private int x, y;
	
	/**
	 * Creates the point with x coordinate and y coordinate
	 * @param x: the x coordinate in the plane/canvas
	 * @param y: the y coordinate in the plane/canvas
	 */
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	
		
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
