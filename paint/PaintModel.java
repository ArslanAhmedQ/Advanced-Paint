package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;
/**
 * Create a Paint Model
 * IS-A:        Observable 
 * HAS-A:       -Shape: ArrayList 
 * RESPONDS-TO: +addShape(Shape s):void, +removeShape(int i):void,
 * 				+SetShape(ArrayList<Shpae> shapes):void, +ArrayList<Shape> getShapes:ArrayList
 */
public class PaintModel extends Observable {
	private ArrayList<Shape> shapes=new ArrayList<Shape>();//make list
	
	/**
	 * Adds the shape to ArrayList shapes
	 * @param s: Shape s is a object
	 */
	public void addShape(Shape s){
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * This void method removes the shape from the ArrayList shapes
	 * @param i: i is an integer, removes the shape based on the index of the shape in ArrayList shapes 
	 */
	public void removeShape(int i){
		if(shapes.size()!=0){  // if the list is empty it doesn't do anything 
			this.shapes.remove(i);
			this.setChanged();
			this.notifyObservers();}
	}
	/**
	 * Set the newly given ArrayList of shape to paintModel's ArrayList of shapes 
	 * @param shapes: is an ArrayList containing the shapes 
	 */
	public void SetShape(ArrayList<Shape> shapes){
		this.shapes=shapes;
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
}
