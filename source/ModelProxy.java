import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Class ModelProxy provides the connection to the "real" model object that
 * resides on the server. This class receives updates from the view and sends
 * the relevant messages over the network to the model on the server.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class ModelProxy implements ViewListener {

    /**
     * The connection used when sending and receiving encoded requests to and
     * from the server program.
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
     * The ModelListener object to send updates to as we receive them from the
     * server program.
     */
    private ModelListener modelListener;

    /**
     * Construct a ModelProxy object.
     *
     * @param sock The socket to use when communicating with the server.
     */
    public ModelProxy(Socket sock) throws IOException {
        this.sock = sock;
        this.out = new PrintWriter(sock.getOutputStream());
        this.in = new Scanner(sock.getInputStream());
    }

    /**
     * Set the ModelListener object for this Proxy and start listening for
     * messages from the service.
     *
     * @param modelListener The ModelListener to send updates to.
     */
    public void setModelListener(ModelListener modelListener) {
        this.modelListener = modelListener;
        Thread t = new Thread(new ServerMessage());
        t.start();
    }

    /**
     * Join a game session by specifying the games unique identifier.
     *
     * @param PlayerProxy The PlayerProxy object.
     * @param sessionName The name of the session to join.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void join(PlayerProxy playerProxy, String sessionName)
        throws IOException {
        String message = "j " + sessionName;
        out.println(message);
        out.flush();
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
    public void selectPiece(ModelListener modelListener, int row, int column) throws IOException {
        String message = "s " + row + " " + column;
        out.println(message);
        out.flush();
    }

    /**
     * Move a checker piece on the game board.
     *
     * @param modelListener The ModelListener object making the request.
     * @param row           The column of the piece to move.
     * @param column        The row of the piece to move.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void movePiece(ModelListener modelListener, int row, int column) throws IOException {
        String message = "m " + row + " " + column;
        out.println(message);
        out.flush();
    }

    /**
     * Close the socket connection on this proxy object.
     *
     * @param modelListener The ModelListener closing the connection.
     *
     * @exception IOException Thrown if any errors occur during communication
     *                        with the service.
     */
    public void close(ModelListener modelListener) throws IOException {
        out.close();
        in.close();
        sock.close();
    }

    /**
     * Class ServerMessage is responsible for receiving messages from the
     * server and sending appropriate updates to the ModelListener object.
     *
     * @author Matt Addy <mxa5942@rit.edu>
     * @author Allan Liburd <abl2114@rit.edu>
     */
    private class ServerMessage implements Runnable {

        public void run() {
            try {
                while (true) {
                    String message = in.next();

                    if (message.equals("e")) {
                        modelListener.tooManyPlayers();
                    } else if (message.equals("p")) {
                        int row = in.nextInt();
                        int column = in.nextInt();
                        modelListener.pieceSelected(row, column);
                    } else if (message.equals("m")) {
                        int oldRow = in.nextInt();
                        int oldColumn = in.nextInt();
                        int newRow = in.nextInt();
                        int newColumn = in.nextInt();
                        modelListener.pieceMoved(oldRow, oldColumn, newRow,
                            newColumn);
                    } else if (message.equals("j")) {
                        int oldRow = in.nextInt();
                        int oldColumn = in.nextInt();
                        int newRow = in.nextInt();
                        int newColumn = in.nextInt();
                        int pieceRemovedRow = in.nextInt();
                        int pieceRemovedColumn = in.nextInt();
                        CheckerPiece piece = new CheckerPiece(pieceRemovedRow,
                            pieceRemovedColumn);
                        modelListener.pieceJumped(oldRow, oldColumn,
                            newRow, newColumn, piece);
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
