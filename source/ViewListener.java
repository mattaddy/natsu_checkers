//******************************************************************************
//
// File:    ViewListener.java
// Package: ---
// Unit:    Interface ViewListener
//
//******************************************************************************

import java.awt.Color;
import java.io.IOException;

/**
 * ViewListerner interface, responsible for accepting messages from the view
 * object.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public interface ViewListener {

	/**
	 * Join a game session by specifying the games unique identifier.
	 *
	 * @param viewProxy   The ViewProxy object.
	 * @param sessionName The name of the session to join.
	 *
	 * @exception IOException Thrown if an I/O error occurs.
	 */
	public void join(ViewProxy viewProxy, String sessionName)
		throws IOException;

	/**
	 * Reports that a piece is trying to move
	 *
	 * @param currLoc
	 *            - the current location of the piece
	 * @param nextLoc
	 *            - the location to move the piece to
	 * @param c
	 *            - the color of the game piece
	 * @throws IOException
	 */
	public void movePiece(int currLoc, int nextLoc, Color c) throws IOException;

}
