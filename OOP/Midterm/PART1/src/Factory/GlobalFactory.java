package Factory;

import Components.*;

public class GlobalFactory implements PhoneIngredientFactory{
    /**
     *
     * @return Global factory uses 2 cores
     */
    @Override
    public Cpu_Ram create_cpu_ram() {
        return new Cpu_Ram_2();
    }
    /**
     *
     * @return Global factory uses 24 bit display
     */
    @Override
    public Display create_display() {
        return new Display_24B();
    }
    /**
     *
     * @return Global factory uses lithium cobalt battery
     */
    @Override
    public Battery create_battery() {
        return new Battery_LC();
    }
    /**
     *
     * @return Global factory uses 32 gb storage
     */
    @Override
    public Storage create_storage() {
        return new Storage_32();
    }
    /**
     *
     * @return Global factory uses 2x zoom
     */
    @Override
    public Camera create_camera() {
        return new Camera_2x();
    }
    /**
     *
     * @return Global factory uses up to 50 cm
     */
    @Override
    public Case create_case() {
        return new Case_05();
    }
}
