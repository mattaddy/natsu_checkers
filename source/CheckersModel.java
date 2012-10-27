import java.util.*;
import java.io.IOException;
import java.awt.Color;

/**
 * The main model class for the game of checkers.
 *
 * @author Matt Addy <mxa5942@rit.edu>
 * @author Allan Liburd <abl2114@rit.edu>
 */
public class CheckersModel implements ViewListener {

    /**
     * The collection of ModelListener objects to send updates to.
     */
    ArrayList<ModelListener> modelListeners;

    /**
     * Construct a CheckersModel object.
     */
    public CheckersModel() {
        modelListeners = new ArrayList<ModelListener>();
    }

    /**
     * Add a ModelListener to this model object. All ModelListener objects
     * added will receieve updates from this model.
     *
     * @param modelListener The ModelListener object to add.
     */
    public synchronized void addModelListener(ModelListener modelListener) {
        modelListeners.add(modelListener);
    }

    public void join(ViewProxy viewProxy, String sessionName)
        throws IOException {}

}
