import java.io.IOException;

/**
 * Class ViewProxy provides a local representative for the remote client view
 * object. ViewProxy is responsible for sending and accepting encoded messages
 * to and from the associated remote client object.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class ViewProxy implements ModelListener {

    public void playerJoined() throws IOException {}

    public void boardChanged() throws IOException {}

    public void illegalMove() throws IOException {}

}
