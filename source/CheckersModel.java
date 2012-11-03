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
    private static final int MAX_PLAYERS = 2;

    /**
     * The collection of ModelListener objects to send updates to.
     */
    private HashMap<ModelListener, Player> modelListeners;

    /**
     * The player who's allowed to make changes to the board.
     */
    private Player currentTurn;

    /**
     * The currently selected piece, if there is one.
     */
    private CheckerPiece selectedPiece;

    /**
     * The board of checkers for this model object.
     */
    private CheckerBoard board;

    /**
     * Construct a CheckersModel object.
     */
    public CheckersModel() {
        modelListeners = new HashMap<ModelListener, Player>(MAX_PLAYERS);
        this.board = new CheckerBoard();
    }

    /**
     * Add a ModelListener to this model object. All ModelListener objects
     * added will receieve updates from this model.
     *
     * @param modelListener The ModelListener object to add.
     */
    public synchronized void addModelListener(ModelListener modelListener) {
        if (modelListeners.size() < MAX_PLAYERS) {

            Player player;

            if (modelListeners.size() == 0) {
                player = new Player(Color.BLACK);
            } else {
                player = new Player(Color.RED);
            }

            modelListeners.put(modelListener, player);

            if (currentTurn == null) {
                currentTurn = player;
            }
        }
    }

    /**
     * Join a game session by specifying the games unique identifier.
     *
     * @param playerProxy The PlayerProxy object.
     * @param sessionName The name of the session to join.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void join(PlayerProxy playerProxy, String sessionName)
        throws IOException {}

    /**
     * Select a checker piece on the game board.
     *
     * @param modelListener The ModelListener object making the request.
     * @param row           The column of the piece to select.
     * @param column        The row of the piece to select.
     */
    public synchronized void selectPiece(ModelListener modelListener, int row,
        int column) {

        Player player = (Player) modelListeners.get(modelListener);
        selectedPiece = board.getPiece(row, column);

        if (currentTurn.equals(player) && selectedPiece != null) {
            if (player.getColor().equals(selectedPiece.getColor())) {
                System.out.println("Selected piece (" + row + ", " + column + ")");
                try {
                    Iterator it = modelListeners.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry) it.next();
                        ModelListener thisListener = (ModelListener) pairs.getKey();
                        thisListener.pieceSelected(row, column);
                    }
                } catch (IOException ex) {

                }
            } else {
                System.out.println("You selected a piece that's not yours.");
                selectedPiece = null;
            }
        } else {
            System.out.println("It's not your turn!");
            selectedPiece = null;
        }
    }

    /**
     * Move a checker piece on the game board.
     *
     * @param modelListener The ModelListener object making the request.
     * @param row           The column of the piece to move.
     * @param column        The row of the piece to move.
     */
    public synchronized void movePiece(ModelListener modelListener, int row,
        int column) {
        CheckerPiece piece = board.getPiece(row, column);
        Player player = (Player) modelListeners.get(modelListener);

        // Make sure we have a selected piece and that desired location to
        // move the piece to is empty.
        if (currentTurn.equals(player) && selectedPiece != null && piece == null
            && modelListeners.size() == 2) {

            int thisRow = selectedPiece.getRow();
            int thisColumn = selectedPiece.getColumn();

            // Check if the player is trying to jump
            CheckerPiece pieceJumped = board.jumpPiece(selectedPiece, row, column);

            if (pieceJumped != null) {
                selectedPiece.move(row, column);

                try {
                    Iterator it = modelListeners.entrySet().iterator();
                    boolean playerSwitched = false;
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry) it.next();
                        Player thisPlayer = (Player) pairs.getValue();
                        ModelListener thisListener = (ModelListener) pairs.getKey();

                        thisListener.pieceJumped(thisRow, thisColumn, row,
                            column, pieceJumped);

                        if (!currentTurn.equals(thisPlayer) && !playerSwitched) {
                            System.out.println("Now it's " + thisPlayer + "'s turn.");
                            currentTurn = thisPlayer;
                            playerSwitched = true;
                        }
                    }
                } catch (IOException ex) {

                }

            } else if (board.movePiece(selectedPiece, row, column)) {
                selectedPiece.move(row, column);

                try {
                    Iterator it = modelListeners.entrySet().iterator();
                    boolean playerSwitched = false;
                    while (it.hasNext()) {
                        Map.Entry pairs = (Map.Entry) it.next();
                        Player thisPlayer = (Player) pairs.getValue();
                        ModelListener thisListener = (ModelListener) pairs.getKey();
                        thisListener.pieceMoved(thisRow, thisColumn, row, column);

                        if (!currentTurn.equals(thisPlayer) && !playerSwitched) {
                            System.out.println("Now it's " + thisPlayer + "'s turn.");
                            currentTurn = thisPlayer;
                            playerSwitched = true;
                        }
                    }
                } catch (IOException ex) {

                }
            } else {
                System.out.println("Invalid move.");
            }
        }
    }

    /**
     * Check if there is room for another ModelListener in this model.
     *
     * @return boolean True if there is room, false otherwise.
     */
    public synchronized boolean spaceAvailable() {
        return modelListeners.size() < MAX_PLAYERS;
    }

    /**
     * Perform any necessary cleanup when the application closes.
     *
     * @exception IOException Thrown if any errors occur during communication
     *                        with the service.
     */
    public synchronized void close() {}
}
