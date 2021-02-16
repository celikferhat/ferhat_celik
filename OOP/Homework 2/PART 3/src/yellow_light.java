/**
 * Yellow light on state
 */
public class yellow_light implements state{
    /**
     * for setting current state and takes seconds
     */
    traffic_lights lights;
    /**
     *  Constructor
     * @param lights for setting current state and takes seconds
     */
    public yellow_light(traffic_lights lights){
        this.lights = lights;
    }
    /**
     * Wait method implementation
     */
    @Override
    public void wait_X() {
        System.out.println("Waiting " + lights.getySec() + " seconds");
    }
    /**
     *
     * Turn on yellow light
     * */
    @Override
    public void turn_on() {
        System.out.println("Yellow light opened");
    }
    /**
     *
     * Turn off yellow light and set next state
     * */
    @Override
    public void turn_off() {
        System.out.println("Yellow light closed");
        lights.setLampState(lights.getGreen());
    }
}
