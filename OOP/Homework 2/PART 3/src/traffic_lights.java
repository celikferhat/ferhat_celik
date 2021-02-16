import java.util.Observable;
import java.util.Observer;

/**
 * Observer traffic lights class
 */
public class traffic_lights implements Observer {
    Observable observable;
    public void setObservable(Observable ob){
        this.observable = ob;
        ob.addObserver(this);
    }

    /**
     *  if there is traffic jam green ligth waits 90 sec else 60 sec
     * @param o Hitech
     * @param arg for push
     */
    @Override
    public void update(Observable o, Object arg) {
        HiTech tech = (HiTech) o;
        if(tech.getTrafficJam())
            setgSec(90);
        else
            setgSec(60);

    }

    /**
     * Three light state
     */
    private state red;
    private state yellow;
    private state green;
    /**
     * For holding current state
     */
    private state current_state ;
    /**
     * Green light second
     */
    private int gsec;
    private final int rsec = 15;
    private final int ysec = 3;

    /**
     *
     * @return red state
     */
    public state getRed() {
        return red;
    }
    /**
     *
     * @return yellow state
     */
    public state getYellow() {
        return yellow;
    }
    /**
     *
     * @return green state
     */
    public state getGreen() {
        return green;
    }

    /**
     * Constructor
     */
    public traffic_lights(){
        gsec = 60;
        red    = new red_light(this);
        yellow = new yellow_light(this);
        green  = new green_light(this);
        setLampState(red);
    }

    /**
     * Setstate method
     * @param lampState takes state and changes current state
     */
    public void setLampState(state lampState) {
        this.current_state = lampState;
    }

    /**
     * For change green ligth second
     * @param num new second
     */
    public void setgSec(int num){
        gsec = num;
    }
    public int getgSec(){
        return gsec;
    }
    public int getrSec(){
        return rsec;
    }
    public int getySec(){
        return ysec;
    }

    /**
     * turns the light on, wait and turn off respectively
     */
    public void run_lights(){
        current_state.turn_on();
        current_state.wait_X();
        current_state.turn_off();
    }


}
