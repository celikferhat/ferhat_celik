package Factory;

import Components.*;

/**
 * Turkey phone production factory
 */
public class TurkeyFactory implements PhoneIngredientFactory{
    /**
     *
     * @return Turkey factory uses 8 cores
     */
    @Override
    public Cpu_Ram create_cpu_ram() {
        return new Cpu_Ram_8();
    }

    /**
     *
     * @return Turkey factory uses 32 bit display
     */
    @Override
    public Display create_display() {
        return new Display_32B();
    }
    /**
     *
     * @return Turkey factory uses Lithium Boron battery
     */
    @Override
    public Battery create_battery() {
        return new Battery_LB();
    }
    /**
     *
     * @return Turkey factory uses 128 gb storage
     */
    @Override
    public Storage create_storage() {
        return new Storage_128();
    }
    /**
     *
     * @return Turkey factory uses 4x zoom
     */
    @Override
    public Camera create_camera() {
        return new Camera_4x();
    }
    /**
     *
     * @return Turkey factory uses up to 2m
     */
    @Override
    public Case create_case() {
        return new Case_2();
    }
}
