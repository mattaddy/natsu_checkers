import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Class ViewProxy provides a local representative for the remote client view
 * object. ViewProxy is responsible for sending and accepting encoded messages
 * to and from the associated remote client object.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class ViewProxy implements ModelListener {

    /**
     * The connection used when sending and receiving encoded messages to the
     * remote client (view) object.
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
     * Construct a ViewProxy object.
     *
     * @param sock The socket to use when communicating with the remote client.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public ViewProxy(Socket sock) throws IOException {
        this.sock = sock;
        this.out = new PrintWriter(sock.getOutputStream());
        this.in = new Scanner(sock.getInputStream());
    }

    /**
     * Set the ViewListener object for this Proxy and start listening to
     * messages.
     */
    public void setViewListener(ViewListener viewListener) {
        this.viewListener = viewListener;
        Thread t = new Thread(new ClientMessage());
        t.start();
    }

    public void playerJoined() throws IOException {}

    /**
     * Report that the current state of the CheckersBoard has changed.
     *
     * @exception IOException Thrown if an I/O error occurs.
     */
    public void boardChanged() throws IOException {}

    public void illegalMove() throws IOException {}

    public void tooManyPlayers() throws IOException {
        out.println("e");
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
                        viewListener.join(ViewProxy.this, sessionName);
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
