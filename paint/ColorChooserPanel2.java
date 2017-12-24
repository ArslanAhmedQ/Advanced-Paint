package ca.utoronto.utm.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates a ShapeChooserPanel
 * IS-A:JPanel
 * HAS-A:-view:View
 * RESPONDS-TO:+actionPerformed():void
 * https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html
 * @author markospajic
 */

public class ColorChooserPanel2 extends JPanel implements ChangeListener{
	private PaintPanel paintpanel;
	
	private static final long serialVersionUID = 1L;
	JColorChooser colorchooser = new JColorChooser();
	
	private View view;
	/**
	 * Creates a colorchooser
	 * 
	 */
	public ColorChooserPanel2(PaintPanel paintpanel, View view) {
		
		this.view=view;
		this.paintpanel=paintpanel;
		JFrame frame = new JFrame("More Colors"); // Initializes frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(750, 300);
        colorchooser.setPreviewPanel(new JPanel());
        colorchooser.getSelectionModel().addChangeListener(this);
        frame.add(colorchooser);
        frame.setVisible(true);
    }
	
	/**
	 * Controller aspect of this
	 * Sets this.view's paintPanel mode to the specified userInput
	 * @param e component initiated
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		Color color = colorchooser.getColor();
		this.paintpanel.setColorMode(color);
		// color indicator button so changing the button color upon choosing color from Advanced color
		this.view.getColorChooserPanel().getColorIndicator().setBackground(view.getPaintPanel().getColor());  
	}
}
