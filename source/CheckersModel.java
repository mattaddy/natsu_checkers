import java.util.*;
import java.io.IOException;
import java.awt.Color;

/**
 * The main model class for the game of checkers.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class CheckersModel implements ViewListener {

    /**
     * How many ModelListener objects can be listening to this model at a time.
     */
    private static final int MAX_LISTENERS = 2;

    /**
     * The collection of ModelListener objects to send updates to.
     */
    private ArrayList<ModelListener> modelListeners;

    /**
     * The ModelListener object who can make changes to the board right now.
     * Eseentially, this is the current players turn.
     */
    private ModelListener currentTurn;

    /**
     * The board of checkers for this model object.
     */
    private CheckerBoard board;

    /**
     * Construct a CheckersModel object.
     */
    public CheckersModel() {
        modelListeners = new ArrayList<ModelListener>(MAX_LISTENERS);
    }

    /**
     * Add a ModelListener to this model object. All ModelListener objects
     * added will receieve updates from this model.
     *
     * @param modelListener The ModelListener object to add.
     */
    public synchronized void addModelListener(ModelListener modelListener) {
        if (modelListeners.size() < MAX_LISTENERS) {
            modelListeners.add(modelListener);

            if (currentTurn == null) {
                currentTurn = modelListener;

                try {
                    modelListener.yourTurn();
                } catch (IOException ex) {

                }
            }
        }
    }

    /**
     * Join a game session by specifying the games unique identifier.
     *
     * @param viewProxy   The ViewProxy object.
     * @param sessionName The name of the session to join.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void join(ViewProxy viewProxy, String sessionName)
        throws IOException {}

    /**
     * Select a checker piece on the game board.
     *
     * @param row    The column of the piece to select.
     * @param column The row of the piece to select.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public synchronized void selectPiece(int row, int column) {
        System.out.println("Selected piece: " + row + ", " + column);
    }

    /**
     * Check if there is room for another ModelListener in this model.
     *
     * @return boolean True if there is room, false otherwise.
     */
    public synchronized boolean spaceAvailable() {
        return modelListeners.size() < MAX_LISTENERS;
    }

    /**
     * Initialize the game board.
     */
    public synchronized void initializeBoard() {
        this.board = new CheckerBoard();
    }

    /**
     * Perform any necessary cleanup when the application closes.
     *
     * @exception IOException Thrown if any errors occur during communication
     *                        with the service.
     */
    public synchronized void close() {}
}
