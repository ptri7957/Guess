import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Phillip Trinh
 *
 */
public class RandomGuesser extends JFrame {

	JTextField field;
	JButton button;
	JTextArea area;
	int r;
	int count = 0;

	public RandomGuesser() {

		// Set screen size
		this.setSize(400, 200);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();

		int x = (dim.width / 2) - (this.getWidth() / 2);
		int y = (dim.height / 2) - (this.getHeight() / 2);

		// Set location of frame on screen
		this.setLocation(x, y);

		// Close application upon exit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set title of the application
		this.setTitle("Random Guesser");

		// Create new panel. Wraps contents to
		// the frame
		JPanel panel = new JPanel();

		// Random number generator
		Random rand = new Random();

		// Assign r with random int
		r = rand.nextInt((10 - 0) + 1);

		// Create new button
		button = new JButton("Submit");

		// Create action listener
		ActionListen action = new ActionListen();

		// Add action listener to button
		button.addActionListener(action);

		// Add listener to button
		panel.add(button);

		// Create new
		field = new JTextField("Enter a number.", 15);

		// Add field to panel
		panel.add(field);

		// Create new text area
		area = new JTextArea(7, 25);

		area.setLineWrap(true);

		area.setWrapStyleWord(true);

		// Create scroll bars
		JScrollPane scroll = new JScrollPane(area,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Add scroll bars to panel
		panel.add(scroll);

		// Add panel to frame
		this.add(panel);

		this.setVisible(true);
	}

	private class ActionListen implements ActionListener {

		// Check if string is numeric
		public boolean isNumeric(String s) {
			NumberFormat f = NumberFormat.getInstance();
			ParsePosition pos = new ParsePosition(0);
			f.parse(s, pos);
			return s.length() == pos.getIndex();
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == button) {
				
				// Check if user entered a number
				if (!isNumeric(field.getText())) {
					if (field.getText().equals("")
							|| field.getText().equals("Enter a number.")) {
						area.append("Please enter a number. \n");
					}else{
						area.append("Not a number \n");
					}
				} else { 
					
					// If user guesses correctly, congratulate the user
					if ((Integer.parseInt(field.getText())) == r) {
						area.append("Congratulations! That was the correct answer! \n");
					
					// Check if input is in bounds
					} else if ((Integer.parseInt(field.getText())) < 0 ||
							(Integer.parseInt(field.getText())) > 10) {
						area.append("Number is out of bounds. \n");
						
					// Notify user that they are incorrect
					} else {
						count++;
						if ((Integer.parseInt(field.getText())) != r && count < 10) {
							area.append("Incorrect. \n");
						} else if ((Integer.parseInt(field.getText())) != r && 
								(count > 10)) {
							area.append("Pathetic. \n");
						}
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		new RandomGuesser();
	}
}
