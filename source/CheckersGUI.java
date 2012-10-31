/**
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

/**
 * CheckersGUI - the GUI for the checkers game
 *
 * @author Allan Liburd <abl2114@rit.edu>
 * @author Matt Addy <mxa5942@rit.edu>
 */
public class CheckersGUI implements ModelListener {

	/**
	 * The ViewListener object to send messages to.
	 */
	private ViewListener viewListener;

	private JFrame frame;

	private JButton[][] boardButtons;
	private final Color BLACK = Color.BLACK;
	private final Color RED = Color.RED;

	private JButton quitButton;

	/**
	 * The CheckersGUI constructor
	 */
	public CheckersGUI(CheckerBoard board) {

		frame = new JFrame("Nastu: Checkers App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container pane = frame.getContentPane();
		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		pane.add(mainPanel);
		mainPanel.setLayout(flow);

		// Create an 8 x 8 board
		boardButtons = new JButton[CheckerBoard.ROWS][CheckerBoard.COLUMNS];
		GridLayout buttonGrid = new GridLayout(CheckerBoard.ROWS,
			CheckerBoard.COLUMNS);
		buttonPanel.setLayout(buttonGrid);

		for (int row = 0; row < CheckerBoard.ROWS; row++) {
		    for (int column = 0; column < CheckerBoard.COLUMNS; column++) {
		    	boardButtons[row][column] = new JButton("");
    	        CheckerPiece piece = board.getPiece(row, column);

    	        boardButtons[row][column].setOpaque(true);
    	        boardButtons[row][column].setForeground(Color.WHITE);
	        	boardButtons[row][column].setBorder(
	        		BorderFactory.createLineBorder(Color.WHITE, 1));
	        	boardButtons[row][column].setPreferredSize(new Dimension(60, 60));

    	        if (row % 2 == 0 && column % 2 == 0 || (row % 2 == 1 && column % 2 == 1)) {
    	        	boardButtons[row][column].setBackground(RED);
    	        } else {
    	        	boardButtons[row][column].setBackground(BLACK);
    	        }

    	        if (piece != null) {
	    	        CheckerColor color = piece.getColor();

	    	        if (color == CheckerColor.RED) {
						boardButtons[row][column].setIcon(new ImageIcon(
							"checkersKCred.png"));
	    	        } else if (color == CheckerColor.BLACK) {
	    	        	boardButtons[row][column].setIcon(new ImageIcon(
	    	        		"checkersKCblack.png"));
	    	        }
	    	    }

	    	    final int currentRow = row;
	    	    final int currentColumn = column;

	    	    boardButtons[row][column].addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    		try {
		    	    		viewListener.selectPiece(currentRow, currentColumn);
		    	    	} catch (IOException ex) {
		    	    		showIOError();
		    	    	}
	    	    	}
	    	    });

    	        buttonPanel.add(boardButtons[row][column]);
		    }
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

	public void setViewListener(ViewListener viewListener) {
		this.viewListener = viewListener;
	}

	public void playerJoined() {}

	public void boardChanged() {}

	public void illegalMove() {}

	public void tooManyPlayers() {
		JOptionPane.showMessageDialog(frame, "Too many players",
			"There are too many players in the specified session. Please "
			+ "create a new session or try joining another one.",
			JOptionPane.ERROR_MESSAGE);
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


	/**
	 * Print an I/O error message to standard error and terminate.
	 */
	private void showIOError() {
	    try {
	        System.err.println("Error communicating with the server. Exiting.");
	        viewListener.close();
	    } catch (IOException ex) {

	    } finally {
	        System.exit(0);
	    }
	}
}
