import java.io.*;
import java.net.*;

/**
 * The main client program for checkers.
 *
 * Usage: java CheckersClient <host> <port> <session>
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class CheckersClient {

	public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java CheckersClient <host> <port> <session>");
            System.exit(1);
        }

        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            String sessionName = args[2];

            Socket sock = new Socket();
            sock.connect(new InetSocketAddress(host, port), 5000);

            CheckersModelClone modelClone = new CheckersModelClone();
            CheckersGUI ui = new CheckersGUI();
            ModelProxy modelProxy = new ModelProxy(sock);

            ui.setViewListener(modelProxy);
            modelProxy.setModelListener(modelClone);
            modelClone.setModelListener(ui);

            modelProxy.join(null, sessionName);

        } catch (NumberFormatException ex) {
            System.err.println("Invalid port number");

        } catch (SocketTimeoutException ex) {
            System.err.println("Could not connect to the specified host and port (timeout after 5 seconds)");

        } catch (IOException ex) {
            System.err.println("Could not connect to the specified host and port");
        }

	}

}
