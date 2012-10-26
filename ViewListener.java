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
 * ViewListerner interface
 * 
 * @author Allan Liburd - abl2114
 * @version 25-Oct-2012
 */
public interface ViewListener {

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
