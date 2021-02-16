package ModelandStores;

import Factory.PhoneIngredientFactory;

public class MaximumEffort extends Phone {
    /**
     * Factory for taking components
     */
    PhoneIngredientFactory phoneIngredientFactory;
    public  MaximumEffort(PhoneIngredientFactory phoneIngredientFactory){
        this.phoneIngredientFactory = phoneIngredientFactory;
    }

    /**
     * Prepare the phone according to model
     */
    @Override
    void prepare() {
        setName("Maximum Efford");
        System.out.println("Preparing " + getName());
        display = phoneIngredientFactory.create_display();
        display.setSize("5.5 inches");
        battery = phoneIngredientFactory.create_battery();
        battery.setPower("27h,3600mAh");
        cpu_ram = phoneIngredientFactory.create_cpu_ram();
        cpu_ram.setFeatures("2.8GHz,8GB");
        storage = phoneIngredientFactory.create_storage();
        storage.setSize("MicroSD support, 64GB");
        camera = phoneIngredientFactory.create_camera();
        camera.setFeatures("12Mp front, 8Mp rear");
        aCase = phoneIngredientFactory.create_case();
        aCase.setFeatures("151x73x7.7 mm / dustproof,/ waterproof,/aluminum");
    }
}
