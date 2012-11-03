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
	 * Too many players are connected.
	 *
	 * @exception IOException Thrown if an I/O error occurs.
	 */
	public void tooManyPlayers() throws IOException;

	/**
	 * Report that a piece has successfully been selected.
	 *
	 * @param row    The row of the selected piece.
	 * @param column The column of the selected piece.
	 *
	 * @exception IOException Thrown if an I/O error occurs.
	 */
	public void pieceSelected(int row, int column) throws IOException;

	/**
	 * Report that a piece has been moved.
	 *
	 * @param oldRow    The row the piece is being moved from.
	 * @param oldColumn	The column the piece is being moved from.
	 * @param newRow    The row the piece is moving to.
	 * @param newColumn The column the piece is moving to.
	 *
	 * @exception IOException Thrown if an I/O error occurs.
	 */
	public void pieceMoved(int oldRow, int oldColumn, int newRow,
		int newColumn) throws IOException;

	/**
	 * Report that a piece has been jumped on the board.
	 *
	 * @param oldRow    The row the piece is being moved from.
	 * @param oldColumn The column the piece is being moved from.
	 * @param newRow    The row the piece is moving to.
	 * @param newColumn The column the piece is moving to.
	 * @param piece     The piece that was jumped.
	 *
	 * @exception IOException Thrown if an I/O error occurs.
	 */
	public void pieceJumped(int oldRow, int oldColumn, int newRow,
		int newColumn, CheckerPiece piece) throws IOException;

	/**
	 * Report that a piece has been kinged.
	 *
	 * @param piece The piece that has been kinged.
	 *
	 * @excetion IOException Thrown if an I/O error occurs.
	 */
	public void pieceKinged(CheckerPiece piece) throws IOException;

	/**
	 * Report that the game is over.
	 *
	 * @param player The player who won the game.
	 *
	 * @exception IOException Thrown if an I/O error occurs.
	 */
	public void gameOver(Player winner) throws IOException;

}
