/**
 * Green light on state
 */
public class green_light implements state {
    /**
     * for setting current state and takes seconds
     */
    traffic_lights lights;

    /**
     *  Constructor
     * @param lights for setting current state and takes seconds
     */
    public green_light(traffic_lights lights){
        this.lights = lights;
    }

    /**
     * Wait method implementation
     */
    @Override
    public void wait_X() {
        System.out.println("Waiting " + lights.getgSec() + " seconds");
    }
    /**
     *
     * Turn on green light
     * */
    @Override
    public void turn_on() {
        System.out.println("Green light opened");
    }

    /**
     * Turn off green light and set next state
     */
    @Override
    public void turn_off() {
        System.out.println("Green light closed");
        lights.setLampState(lights.getRed());
    }
}
