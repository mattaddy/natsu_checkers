import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Class PlayerProxy provides a local representative for the remote client
 * player object. PlayerProxy is responsible for sending and accepting encoded
 * messages to and from the associated remote player object.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class PlayerProxy implements ModelListener {

    /**
     * The connection used when sending and receiving encoded messages to the
     * remote player object.
     */
    private Socket sock;

    /**
     * The connections output stream.
     */
    private PrintWriter out;

    /**
     * The connections input stream.
     */
    private Scanner in;

    /**
     * The ViewListener object to send updates to, as well as receive requests
     * from.
     */
    private ViewListener viewListener;

    /**
     * Construct a PlayerProxy object.
     *
     * @param sock The socket to use when communicating with the remote client.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public PlayerProxy(Socket sock) throws IOException {
        this.sock = sock;
        this.out = new PrintWriter(sock.getOutputStream());
        this.in = new Scanner(sock.getInputStream());
    }

    /**
     * Set the ViewListener object for this Proxy and start listening to
     * messages.
     *
     * @param viewListener The ViewListener for this proxy.
     */
    public void setViewListener(ViewListener viewListener) {
        if (this.viewListener == null) {
            this.viewListener = viewListener;
            Thread t = new Thread(new ClientMessage());
            t.start();
        } else {
            this.viewListener = viewListener;
        }
    }

    /**
     * Too many players are connected.
     *
     * @exception IOException Thrown if an I/O error occurs
     */
    public void tooManyPlayers() throws IOException {
        out.println("e");
        out.flush();
    }

    /**
     * Report that a piece has successfully been selected.
     *
     * @param row    The row of the selected piece.
     * @param column The column of the selected piece.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void pieceSelected(int row, int column) throws IOException {
        String message = "p " + row + " " + column;
        out.println(message);
        out.flush();
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
        String message = "m " + oldRow + " " + oldColumn + " " + newRow + " "
            + newColumn;
        out.println(message);
        out.flush();
    }

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
        int newColumn, CheckerPiece piece) throws IOException {
        String message = "j " + oldRow + " " + oldColumn + " " + newRow + " "
            + newColumn + " " + piece.getRow() + " " + piece.getColumn();
        System.out.println(message);
        out.println(message);
        out.flush();
    }

    /**
     * Class ClientMessage is responsible for receiving messages from the
     * remote client and sending messages to the ViewListener object.
     *
     * @author Matt Addy <mxa5942@rit.edu>
     * @author Allan Liburd <abl2114@rit.edu>
     */
    private class ClientMessage implements Runnable {

        public void run() {
            try {
                while (true) {
                    String message = in.next();

                    if (message.equals("j")) {
                        String sessionName = in.next();
                        viewListener.join(PlayerProxy.this, sessionName);
                    } else if (message.equals("s")) {
                        int row = in.nextInt();
                        int column = in.nextInt();
                        viewListener.selectPiece(PlayerProxy.this, row, column);
                    } else if (message.equals("m")) {
                        int row = in.nextInt();
                        int column = in.nextInt();
                        viewListener.movePiece(PlayerProxy.this, row, column);
                    }
                }
            } catch (IOException ex) {

            } catch (NoSuchElementException ex) {

            } finally {
                try {
                    out.close();
                    in.close();
                    sock.close();
                } catch(IOException ex) {

                }
            }
        }
    }

}
