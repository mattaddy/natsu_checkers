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
     * @param PlayerProxy The PlayerProxy object.
     * @param sessionName The name of the session to join.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public synchronized void join(PlayerProxy playerProxy, String sessionName)
        throws IOException {

        CheckersModel model = sessions.get(sessionName);

        if (model == null) {
            model = new CheckersModel();
            sessions.put(sessionName, model);
        }

        if (model.spaceAvailable()) {
            playerProxy.setViewListener(model);
            model.addModelListener(playerProxy);
        } else {
            playerProxy.tooManyPlayers();
        }
    }

    /**
     * Select a checker piece on the game board.
     *
     * @param modelListener The ModelListener object making the request.
     * @param row           The column of the piece to select.
     * @param column        The row of the piece to select.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void selectPiece(ModelListener modelListener, int row, int column) {}

    /**
     * Move a checker piece on the game board.
     *
     * @param row    The column of the piece to select.
     * @param column The row of the piece to select.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void movePiece(int row, int column) { }

    /**
     * Perform any necessary cleanup when the application closes.
     *
     * @exception IOException Thrown if any errors occur during communication
     *                        with the service.
     */
    public synchronized void close() {}

}
