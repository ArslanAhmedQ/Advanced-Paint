package ca.utoronto.utm.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * Creates a ShapeChooserPanel
 * IS-A:JPanel
 * HAS-A:-view:View
 * RESPONDS-TO:+actionPerformed():void
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	
	/**
	 * Constructs a ShapeChooserPanel
	 * @param view
	 */
	public ShapeChooserPanel(View view) {	
		this.view=view; // To talk to the components
		ButtonGroup group = new ButtonGroup(); // Collects all the buttons into a group
		String[] buttonLabels = { "Circle", "Oval", "Square", "Rectangle", "Squiggle", "Polyline", "LinePattern", 
				"DottedSquiggle", "Text", "Polygon", "MirroredSquiggle" };

		this.setLayout(new GridLayout(buttonLabels.length/2+1, 1));
		for (String label : buttonLabels) {
			JToggleButton button = new JToggleButton();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/"+label+"Icon.png").
					getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)); // Retrieves and scales icons
			button.setIcon(imageIcon); // Adds icon to button		
			button.setName(label); // Identifier for the button
			group.add(button);
			this.add(button);
			button.addActionListener(this);
			if (label == "Circle"){
				button.setSelected(true);//Circle is the default shape, so the button should be selected when the program starts
			}
		}
	}
	
	/**
	 * Controller aspect of this
	 * Sets this.view's paintPanel mode to the specified userInput
	 * @param e component initiated
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(((JComponent) e.getSource()).getName());
		
		//make the SymmertyPanel visible only if the MirroredSquiggle shape is selected
		if (((JComponent)e.getSource()).getName() == "MirroredSquiggle"){
			this.view.getSymmetryPanel().setVisible(true);
		}
		else{
			this.view.getSymmetryPanel().setVisible(false);
		}
	}
}


