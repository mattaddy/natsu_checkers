/**
 * 
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * CheckersGUI - the GUI for the checkers game
 * 
 * @author Allan Liburd - abl2114
 */
public class CheckersGUI {

	private JFrame frame;

	private JButton[] board;
	private final Color BLACK = Color.BLACK;
	private final Color RED = Color.RED;
	private final int size = 64;

	private JButton quitButton;

	/**
	 * The CheckersGUI constructor
	 */
	public CheckersGUI() {

		frame = new JFrame("Nastu: Checkers App");
		Container pane = frame.getContentPane();
		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		pane.add(mainPanel);
		mainPanel.setLayout(flow);

		// Create an 8 x 8 board
		board = new JButton[size];
		GridLayout buttonGrid = new GridLayout(8, 8);
		buttonPanel.setLayout(buttonGrid);

		for (int x = 0; x < board.length; x++) {
			board[x] = new JButton("");
			Color temp = determineColor(x);
			board[x].setBackground(temp);
			if (temp == RED) {
				board[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						illegalMoveError();
					}
				});
			} else {
				// could ask the model for the color, or create a board class
				// that's shared between model and view
				board[x].setIcon(new ImageIcon("checkersKCblack.png"));
				board[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						// smthg
					}
				});
			}
			buttonPanel.add(board[x]);
		}

		mainPanel.add(buttonPanel);

		// Close the program
		frame.addWindowListener(new WindowAdapter() {
			public void closeWindow(WindowEvent we) {
				System.exit(0);
			}
		});

		// Display the UI
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Determines the background color to be used
	 * 
	 * @param c
	 * @return
	 */
	private Color determineColor(int c) {
		int temp = c % 16;
		if (temp > 7)
			temp += 1;
		if (temp % 2 == 0)
			return RED;
		else
			return BLACK;
	}

	/**
	 * Message Dialog for the illegal/
	 */
	private void illegalMoveError() {
		JOptionPane.showMessageDialog(frame, "Illegal Move",
				"Illegal move. Try again", JOptionPane.ERROR_MESSAGE);
	}
}
