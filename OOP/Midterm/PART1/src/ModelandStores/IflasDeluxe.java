package ModelandStores;

import Factory.PhoneIngredientFactory;

public class IflasDeluxe extends Phone {
    /**
     * Factory for taking components
     */
    PhoneIngredientFactory phoneIngredientFactory;
    public  IflasDeluxe(PhoneIngredientFactory phoneIngredientFactory){
        this.phoneIngredientFactory = phoneIngredientFactory;
    }
    /**
     * Prepare the phone according to model
     */
    @Override
    void prepare() {
        setName("IflasDeluxe");
        System.out.println("Preparing " + getName());
        display = phoneIngredientFactory.create_display();
        display.setSize("5.3 inches");
        battery = phoneIngredientFactory.create_battery();
        battery.setPower("20h,2800mAh");
        cpu_ram = phoneIngredientFactory.create_cpu_ram();
        cpu_ram.setFeatures("2.2GHz,6GB");
        storage = phoneIngredientFactory.create_storage();
        storage.setSize("MicroSD support, 32GB");
        camera = phoneIngredientFactory.create_camera();
        camera.setFeatures("12Mp front, 5Mp rear");
        aCase = phoneIngredientFactory.create_case();
        aCase.setFeatures("149x73x7.7 mm / waterproof / aluminum");
    }
}
