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
        selectedRow = -1;
        selectedColumn = -1;
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

    public void tooManyPlayers() throws IOException {
        modelListener.tooManyPlayers();
    }

    public void pieceSelected(int row, int column) {
        selectedRow = row;
        selectedColumn = column;
    }

    /**
     * Report that a piece has been moved.
     *
     * @param oldRow    The row the piece is being moved from.
     * @param oldColumn The column the piece is being moved from.
     * @param newRow    The row the piece is moving to.
     * @param newColumn The column the piece is moving to.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void pieceMoved(int oldRow, int oldColumn, int newRow,
        int newColumn) throws IOException {
        CheckerPiece piece = board.getPiece(oldRow, oldColumn);
        board.movePiece(piece, newRow, newColumn);
        modelListener.pieceMoved(oldRow, oldColumn, newRow,
            newColumn);
    }

}
