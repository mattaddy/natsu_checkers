import java.io.IOException;

/**
 * Class CheckersModelClone provides a local representative of the "real"
 * CheckersModel object that resides on the server.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class CheckersModelClone implements ModelListener {

    /**
     * The ModelListener object to send updates to.
     */
    private ModelListener modelListener;

    /**
     * Is it my turn to make a move?
     */
    private boolean myTurn;

    /**
     * The row of the currently selected piece.
     */
    private int selectedRow;

    /**
     * The column of the currently selected piece.
     */
    private int selectedColumn;

    /**
     * The board of checkers for this model object.
     */
    private CheckerBoard board;

    /**
     * Construct a CheckersModelClone object.
     */
    public CheckersModelClone() {
        board = new CheckerBoard();
        myTurn = false;
    }

    /**
     * Set the ModelListener object for this model clone.
     *
     * @param modelListener The ModelListener object.
     */
    public void setModelListener(ModelListener modelListener) {
        this.modelListener = modelListener;
    }

    /**
     * Get the CheckerBoard for this CheckersModel object.
     *
     * @return CheckerBoard The board for this model.
     */
    public CheckerBoard getBoard() {
        return board;
    }

    public void playerJoined() {}

    public void boardChanged() {}

    public void illegalMove() {}

    public void tooManyPlayers() throws IOException {
        modelListener.tooManyPlayers();
    }

    public void yourTurn() {
        myTurn = true;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void pieceSelected(int row, int column) {
        selectedRow = row;
        selectedColumn = column;

        System.out.println("Successfully selected the piece at " + row + ", " + column);
    }

}
