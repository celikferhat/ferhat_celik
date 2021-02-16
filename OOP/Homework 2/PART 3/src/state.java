/**
 * State interface
 */
public interface state {
    /**
     * Wait seconds for turn off the light
     */
    public void wait_X();

    /**
     * Turn on light
     */
    public void turn_on();
    /**
     * Turn off light
     */
    public void turn_off();
}
