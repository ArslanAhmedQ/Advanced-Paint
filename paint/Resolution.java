package ca.utoronto.utm.paint;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * Handles the JFrame used for setting the resolution of PaintPanel.
 * IS-A:JPanel
 * HAS-A:-JTextFields x, y:JTextField, -PaintPanel paintpanel-JPanel, -View view:JFrame, -JFrame frame:JFrame
 * RESPONDS-TO:
 *
 */
public class Resolution extends JPanel implements ActionListener{
	private View view; // Creates an instance of view
	private JFrame frame; // Creates an instance of JFrame
	private PaintPanel paintpanel; // Creates an instance of PaintPanel
	JTextField x, y; // Creates two JTextFields to input size
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a resolution frame
	 * @param paintpanel
	 * @param view
	 */
	public Resolution(PaintPanel paintpanel, View view){
		this.view=view;
		this.paintpanel=paintpanel;
		this.frame = new JFrame("Set Resolution"); // Initializes frame
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3, 4));
		x = new JTextField("720"); // Default width
		y = new JTextField("480"); // Default height
		JLabel w = new JLabel("Set Width:");
		JLabel h = new JLabel("Set Height:");
		JButton q = new JButton("Cancel");
		JButton s = new JButton("Start");
		q.addActionListener(this);
		s.addActionListener(this);
		this.add(w);
		this.add(x);
		this.add(h);
		this.add(y);
		this.add(s);
		this.add(q);
		frame.add(this);
		frame.setSize(new Dimension(200, 200)); // Sets size of frame
		frame.setLocationRelativeTo(null); // Sets frame location to middle of screen
		frame.setVisible(true); // Shows the frame
		this.view.getPaintPanel().setVisible(false); // Sets paintPanel to invisible to allow for changes 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Cancel"){
			frame.dispose();
			this.view.getPaintPanel().setVisible(true); //Closes frame and reinitiates paintPanel
		}
		else{
			int xbb = Integer.parseInt(this.x.getText()); // Gets the user input of width
			int ybb = Integer.parseInt(this.y.getText()); // Gets the user input of height
			this.view.getPaintPanel().setBound(xbb, ybb); // Asks PaintPanel to set resolution
			this.view.getPaintPanel().setVisible(true); 
			frame.dispose();
		}
	}

}
