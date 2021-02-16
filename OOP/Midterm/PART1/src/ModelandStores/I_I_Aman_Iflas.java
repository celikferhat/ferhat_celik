package ModelandStores;

import Factory.PhoneIngredientFactory;

public class I_I_Aman_Iflas extends Phone {
    /**
     * Factory for taking components
     */
    PhoneIngredientFactory phoneIngredientFactory;
    public  I_I_Aman_Iflas(PhoneIngredientFactory phoneIngredientFactory){
        this.phoneIngredientFactory = phoneIngredientFactory;
    }
    /**
     * Prepare the phone according to model
     */
    @Override
    void prepare() {
        setName("I_I_Aman_Iflas");
        System.out.println("Preparing " + getName());
        display = phoneIngredientFactory.create_display();
        display.setSize("4.5 inches");
        battery = phoneIngredientFactory.create_battery();
        battery.setPower("16h,2000mAh");
        cpu_ram = phoneIngredientFactory.create_cpu_ram();
        cpu_ram.setFeatures("2.2GHz,4GB");
        storage = phoneIngredientFactory.create_storage();
        storage.setSize("MicroSD support, 16GB");
        camera = phoneIngredientFactory.create_camera();
        camera.setFeatures("8Mp front, 5Mp rear");
        aCase = phoneIngredientFactory.create_case();
        aCase.setFeatures("143x69x7.3 mm / waterproof / plastic");
    }
}
