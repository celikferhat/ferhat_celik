package Factory;

import Components.*;

public class EuFactory implements PhoneIngredientFactory{
    /**
     *
     * @return EU factory uses 4 cores
     */
    @Override
    public Cpu_Ram create_cpu_ram() {
        return new Cpu_Ram_4();
    }
    /**
     *
     * @return EU factory uses 24 bit display
     */
    @Override
    public Display create_display() {
        return new Display_24B();
    }
    /**
     *
     * @return EU factory uses lithium ion battery
     */
    @Override
    public Battery create_battery() {
        return new Battery_LI();
    }
    /**
     *
     * @return EU factory uses 64 gb storage
     */
    @Override
    public Storage create_storage() {
        return new Storage_64();
    }
    /**
     *
     * @return EU factory uses 3x zoom
     */
    @Override
    public Camera create_camera() {
        return new Camera_3x();
    }
    /**
     *
     * @return EU factory uses up to 1m
     */
    @Override
    public Case create_case() {
        return new Case_1() ;
    }
}
