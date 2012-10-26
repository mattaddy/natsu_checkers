//******************************************************************************
//
// File:    ModelListener.java
// Package: ---
// Unit:    Interface ViewListener
//
//******************************************************************************

import java.io.IOException;

/**
 * Interface ModelListener specifies the interface for an object that is
 * triggered by events from the model object in the Checkers Game
 * 
 * @author Allan Liburd - abl2114
 * @version 25-Oct-2012
 */
public interface ModelListener {

	/**
	 * Reports that a player has joined the game session
	 * 
	 * @throws IOException
	 *             - if an I/O error occurs, throws this exception
	 */
	public void playerJoined() throws IOException;

	/**
	 * Reports that the Checkers game board changed.
	 * 
	 * @throws IOException
	 *             - if an I/O error occurs, throws this exception
	 */
	public void boardChanged() throws IOException;

	/**
	 * Reports that a wrong move was made
	 * 
	 * @throws IOException
	 *             - if an I/O error occurs, throws this exception
	 */
	public void illegalMove() throws IOException;
}
