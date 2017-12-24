package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a ColorChooserPanel
 * IS-A:JPanel
 * HAS-A:-view:View
 * RESPONDS-TO:+actionPerformed():void
 */
class ColorChooserPanel extends JPanel implements ActionListener, ChangeListener {
private View view; // So we can talk to our parent or other components of the view
private PaintPanel paintpanel;
private ColorChooserPanel2 colorChooserPanel2;

private JLabel colorIndicator = new JLabel("");  // new color indicator button




	public JLabel getColorIndicator() {
		return this.colorIndicator;
	}
	
	public ColorChooserPanel(View view, PaintPanel paintpanel) {	 
		this.view=view;
		this.paintpanel=paintpanel;
		ButtonGroup group = new ButtonGroup();
		
		
		this.setLayout(new GridLayout(1,5));
		
		//adding SubwayPaint Logo
		JLabel logo = new JLabel("");
		ImageIcon Imageicon = new ImageIcon(new ImageIcon("Images/subwayIcon2.png").
				getImage().getScaledInstance(125, 100, Image.SCALE_DEFAULT));
		logo.setIcon(Imageicon);
		this.add(logo);
		
		// Panel for Color Indicator
		JPanel panel1 = new JPanel(new GridLayout(1,1));  
		this.add(panel1);
		// Panel for colors
		JPanel panel2 = new JPanel(new GridLayout(2,4));  
		this.add(panel2);
		// Panel for fill and morecolors  
		JPanel panel3 = new JPanel(new GridLayout(2,1)); 
		this.add(panel3);
		// Panel for thickness slider
		JPanel panel4 = new JPanel(new GridLayout(2,1)); 
		this.add(panel4);
		
		
		Color[] buttonLabels = {new Color(0, 0, 0), new Color(255, 0, 0), 
				new Color(255,255,40), new Color(0, 0, 255), new Color(255, 255, 255), new Color(255,135,40),
				new Color(0, 255, 0), new Color(180, 0, 255)}; // Some base colors
		String[] buttonLabels2 = {"black", "red", "yellow", "blue", "white", "orange", "green", "purple"};
		
		
		// making color indicator
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(210,210,230), Color.darkGray);
		colorIndicator.setBorder(border);
			
		colorIndicator.setOpaque(true);
		panel1.add(this.colorIndicator);
	    
		for (int i=0; i<buttonLabels.length; i++) {
			Color label = buttonLabels[i];
			String label2 = buttonLabels2[i];
			JToggleButton button = new JToggleButton();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/"+label2+"Icon.png").
					getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)); // Retrieves and scales icons
			button.setIcon(imageIcon); // Adds icon to button
			button.setName(label2); // Identifier for the button 
			button.setForeground(label);
			button.setOpaque(true);
			group.add(button);
			panel2.add(button);
			button.addActionListener(this);
			if (label == buttonLabels[0]){
				button.setSelected(true);//Black is the default color, so the button should be selected when the program starts
			}
		}
		JButton aselector = new JButton("More Colors");
		aselector.setIcon(new ImageIcon(new ImageIcon("Images/colorWheeIcon.png").
					getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		this.view=view;
	    aselector.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        	//view.setCCPVisibility(!view.getCCPVisibility());
	        	new ColorChooserPanel2(paintpanel, view);
	        }
	    });
	    panel3.add(aselector);  
	    
		//Making fill button
		JToggleButton fillButton = new JToggleButton();
	    ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("Images/FilledCircleIcon.png").
				getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)); // Retrieves and scales icons
		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("Images/CircleIcon.png").
				getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)); // Retrieves and scales icons
		fillButton.addActionListener(this);
		fillButton.setText("Fill");
		fillButton.setIcon(imageIcon1);
		fillButton.setDisabledIcon(imageIcon1); // Setting change of icon to represent fill button
		fillButton.setPressedIcon(imageIcon2);
		fillButton.setSelectedIcon(imageIcon2);
		fillButton.setDisabledSelectedIcon(imageIcon1);
		panel3.add(fillButton);

		//Making thickness slider
		JLabel label = new JLabel("Thickness:", JLabel.CENTER);
		panel4.add(label);
		
		JSlider thickness = new JSlider(JSlider.HORIZONTAL, 1, 10, 1); // Creates slider
		thickness.setMajorTickSpacing(9); // Sets max position for slider
		thickness.setMinorTickSpacing(1); // Sets minimum position for slider
		thickness.setPaintTicks(true);
		thickness.setPaintLabels(true);
		thickness.addChangeListener(this);
		panel4.add(thickness);		
	}
	
	/**
	 * Controller aspect of this
	 * Sets this.view's paintPanel mode to the specified userInput
	 * @param e component initiated
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Fill"){
			this.view.getPaintPanel().setFill(!this.view.getPaintPanel().getFill());
		}
		// ActionListener for basic color
		else if((((JComponent) e.getSource()).getForeground()) != null){
			this.view.getPaintPanel().setColorMode((((JComponent) e.getSource()).getForeground()));
			// color indicator button so changing the button color upon choosing color from basic color
			this.view.getColorChooserPanel().getColorIndicator().setBackground(view.getPaintPanel().getColor());  
		}
	}

	/**
	 * Controller for thickness slider
	 * Sets this.view's paintPanel mode to the specified userInput
	 * @param e component initiated
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider thickness = (JSlider) e.getSource();
        if (!thickness.getValueIsAdjusting()) {
          int thick = thickness.getValue();
          this.view.getPaintPanel().setThickness(thick);
        }
		
	}

	
}