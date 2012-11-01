import java.io.IOException;

/**
 * Interface ModelListener specifies the interface for an object that is
 * triggered by events from the model object in the Checkers Game
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public interface ModelListener {

	/**
	 * Reports that a player has joined the game session
	 *
	 * @exception IOException Thrown if an I/O error occurs
	 */
	public void playerJoined() throws IOException;

	/**
	 * Reports that the Checkers game board changed.
	 *
	 * @exception IOException Thrown if an I/O error occurs
	 */
	public void boardChanged() throws IOException;

	/**
	 * Reports that a wrong move was made
	 *
	 * @exception IOException Thrown if an I/O error occurs
	 */
	public void illegalMove() throws IOException;

	/**
	 * Too many players are connected.
	 *
	 * @exception IOException Thrown if an I/O error occurs
	 */
	public void tooManyPlayers() throws IOException;

	/**
	 * Tell the model that it's his or her turn.
	 *
	 * @exception IOException Thrown if an I/O error occurs
	 */
	public void yourTurn() throws IOException;

	/**
	 * Check whether it's my turn to move.
	 *
	 * @return boolean True if it's my turn, false otherwise.
	 */
	public boolean isMyTurn();

	/**
	 * A piece has successfully been selected.
	 *
	 * @param row    The row of the selected piece.
	 * @param column The column of the selected piece.
	 *
	 * @exception IOException Thrown if an I/O error occurs
	 */
	public void pieceSelected(int row, int column) throws IOException;

	/**
	 * Determine whether there is a piece currently selected on the board.
	 *
	 * @return boolean True if there is a piece selected, false otherwise.
	 */
	public boolean isPieceSelected();
}
