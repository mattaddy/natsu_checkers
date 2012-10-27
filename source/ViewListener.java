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

}
