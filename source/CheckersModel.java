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

            System.out.print("Player joined: ");

            Player player;

            if (modelListeners.size() == 0) {
                player = new Player(Color.BLACK);
                System.out.println("black.");
            } else {
                player = new Player(Color.RED);
                System.out.println("red.");
            }

            modelListeners.put(modelListener, player);

            if (currentTurn == null) {
                currentTurn = player;
                System.out.println(player.getColor().toString() + "'s turn.");
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
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public synchronized void selectPiece(ModelListener modelListener, int row,
        int column) {

        Player player = (Player) modelListeners.get(modelListener);
        selectedPiece = board.getPiece(row, column);

        if (currentTurn.equals(player)) {
            if (player.getColor().equals(selectedPiece.getColor())) {
                System.out.println("Selected piece: " + row + ", " + column);
            } else {
                System.out.println("You selected a piece that's not yours:");
                System.out.println("Piece color: " + selectedPiece.getColor());
                System.out.println("Your color : " + player.getColor());
            }
        } else {
            System.out.println("It's not your turn!");
        }

        try {
            Iterator it = modelListeners.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                ModelListener thisListener = (ModelListener) pairs.getKey();
                thisListener.pieceSelected(row, column);
            }
        } catch (IOException ex) {

        }
    }

    /**
     * Move a checker piece on the game board.
     *
     * @param row    The column of the piece to select.
     * @param column The row of the piece to select.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void movePiece(int row, int column) {
        // if (modelListeners.size() == 2 && selectedRow != -1 && selectedColumn != -1) {
        //     if ((row == selectedRow + 1 || row == selectedRow - 1) && (column == selectedColumn + 1 || column == selectedColumn - 1)) {
        //         System.out.println("Moving (" + selectedRow + ", " + selectedColumn + ") to (" + row + ", " + column + ").");

        //         try {
        //             if (modelListeners.get(0).equals(currentTurn)) {
        //                 currentTurn = modelListeners.get(1);
        //                 modelListeners.get(1).yourTurn();
        //             } else {
        //                 currentTurn = modelListeners.get(0);
        //                 modelListeners.get(0).yourTurn();
        //             }
        //         } catch (IOException ex) {

        //         }

        //     } else {
        //         System.out.println("Invalid move.");

        //     }
        // }
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
