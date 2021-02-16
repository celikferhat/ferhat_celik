package Factory;

import Components.*;

/**
 * ModelandStores.Phone production factory
 */
public interface PhoneIngredientFactory {
public Cpu_Ram create_cpu_ram();
public Display create_display();
public Battery create_battery();
public Storage create_storage();
public Camera create_camera();
public Case create_case();
}
