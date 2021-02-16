import java.util.Observable;

/**
 * Observable
 * Determine traffic jam and informs traffic_lights
 */
public class HiTech extends Observable {
    /**
     * İf there is jam tf true else false
     */
    private boolean tf;
    private void contentChanged(){
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param flag jam
     */
    public void changeDetected(boolean flag){
        this.tf = flag;
        contentChanged();
    }

    /**
     *
     * @return returns traffic jam
     */
    public boolean getTrafficJam() {
        return tf;
    }
}
