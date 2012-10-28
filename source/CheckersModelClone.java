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
     * Construct a CheckersModelClone object.
     */
    public CheckersModelClone() {}

    /**
     * Set the ModelListener object for this model clone.
     *
     * @param modelListener The ModelListener object.
     */
    public void setModelListener(ModelListener modelListener) {
        this.modelListener = modelListener;
    }

    public void playerJoined() {}

    public void boardChanged() {}

    public void illegalMove() {}

    public void tooManyPlayers() throws IOException {
        modelListener.tooManyPlayers();
    }

}
