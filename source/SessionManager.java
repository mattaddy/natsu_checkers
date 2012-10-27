import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * Class SessionManager is responsible for maintaining a collection of model
 * objects for each session that has been created. Each session consists of a
 * single CheckersModel object which is composed of 1 or 2 ViewListener
 * objects.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class SessionManager implements ViewListener {

    /**
     * The collection of model objects, mapped by the session name.
     */
    HashMap<String, CheckersModel> sessions;

    /**
     * Construct a SessionManager object.
     */
    public SessionManager() {
        sessions = new HashMap<String, CheckersModel>();
    }

    /**
     * Join a game session by specifying the games unique identifier.
     *
     * @param viewProxy   The ViewProxy object.
     * @param sessionName The name of the session to join.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public synchronized void join(ViewProxy viewProxy, String sessionName)
        throws IOException {

    }

    public void movePiece(int currLoc, int nextLoc, Color c)
        throws IOException {}

}
