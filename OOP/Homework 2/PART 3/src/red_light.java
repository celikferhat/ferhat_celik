/**
 * Red light on state
 */
public class red_light implements state{
    /**
     * for setting current state and takes seconds
     */
    traffic_lights lights;
    /**
     *  Constructor
     * @param lights for setting current state and takes seconds
     */
    public red_light(traffic_lights lights){
        this.lights = lights;
    }

    /**
     * Wait method implementation
     */
    @Override
    public void wait_X() {
    System.out.println("Waiting " + lights.getrSec() + " seconds");
    }
    /**
     *
     * Turn on red light
     * */
    @Override
    public void turn_on() {
    System.out.println("Red light opened");
    }
    /**
     *
     * Turn off red light and set next state
     * */
    @Override
    public void turn_off() {
        System.out.println("Red light closed");
        lights.setLampState(lights.getYellow());
    }
}
