import java.net.*;
import java.io.*;

/**
 * The main server program for Checkers.
 *
 * Usage: java CheckersServer <host> <port>
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class CheckersServer {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java CheckersServer <host> <port>");
            System.exit(1);
        }

        try {
            String host = args[0];
            int port = Integer.parseInt(args[1]);

            ServerSocket serverSock = new ServerSocket();
            serverSock.bind(new InetSocketAddress(host, port));

            SessionManager manager = new SessionManager();

            while (true) {
                Socket sock = serverSock.accept();
                ViewProxy viewProxy = new ViewProxy(sock);

                viewProxy.setViewListener(manager);
            }

        } catch (NumberFormatException ex) {
           System.err.println("Invalid port number");

        } catch (IOException ex) {
            System.err.println("Could not setup connection on the specified port");
        }
    }
}
