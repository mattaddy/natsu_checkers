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
     * How many ModelListener objects can be listening to this model at a time.
     */
    private static final int MAX_LISTENERS = 2;

    /**
     * The collection of ModelListener objects to send updates to.
     */
    private ArrayList<ModelListener> modelListeners;

    /**
     * Construct a CheckersModel object.
     */
    public CheckersModel() {
        modelListeners = new ArrayList<ModelListener>(MAX_LISTENERS);
    }

    /**
     * Add a ModelListener to this model object. All ModelListener objects
     * added will receieve updates from this model.
     *
     * @param modelListener The ModelListener object to add.
     *
     * @return boolean True if the ModelListener was added, false otherwise.
     */
    public synchronized boolean addModelListener(ModelListener modelListener) {
        boolean listenerAdded = false;

        if (modelListeners.size() < MAX_LISTENERS) {
            modelListeners.add(modelListener);
            listenerAdded = true;
        }

        return listenerAdded;
    }

    public void join(ViewProxy viewProxy, String sessionName)
        throws IOException {}

}
