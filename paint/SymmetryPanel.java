package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel that contains all buttons related to the selection of the drawing symmetry
 * 
 * IS-A JPanel
 * HAS-A: -View, -PaintPanel
 * RESPONDS-TO +actionPerformed(MouseEvent e)
 *
 */
public class SymmetryPanel extends JPanel implements ActionListener{
	private View view;
	
	/**
	 * constructs a new SymmetryPanel
	 * @param view
	 */
	public SymmetryPanel(View view) {
		this.view = view;//to talk to other view components
		
		ButtonGroup group = new ButtonGroup(); // Collects all the buttons into a group
		String[] buttonLabels = {"Vertical", "Horizontal", "Quadrant", "Mandala"};

		this.setLayout(new GridLayout(5, 1));
		
		//creating JTextArea to display title of the panel, and adding it to the panel
		JTextArea title = new JTextArea("        Symmetry\n          Options");
		title.setFont(new Font("Arial", Font.HANGING_BASELINE, 16));
		title.setBackground(new Color(211, 213, 218));
		title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(210, 210, 230), Color.darkGray));
		title.setOpaque(true);
		this.add(title);

		
		//Creating and adding buttons
		for (String label : buttonLabels) {
			JToggleButton button = new JToggleButton();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/"+label+"Icon.png").
					getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)); // Retrieves and scales icons, no icons at the moment
			button.setIcon(imageIcon); // Adds icon to button (once icons are added)
			button.setName(label); // Identifier for the button
			button.setText(label);
			group.add(button);
			this.add(button);
			button.addActionListener(this);
			if (label == "Vertical"){
				button.setSelected(true);//Vertical is the default symmetry, so the button should be selected when the program starts
			}
		}
		
	}

	
	/**
	 * controller component of SymmetryPanel
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		this.view.getPaintPanel().setSymmerty(((JComponent) e.getSource()).getName());//set paintpanel's symmetry to whatever the name of the
																			//button clicked is	
	}
	
}
