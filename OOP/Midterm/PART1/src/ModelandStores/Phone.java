package ModelandStores;

import Components.*;

/**
 * ModelandStores.Phone CLass
 */
public abstract class Phone {
    String name;
    Display display;
    Battery battery;
    Cpu_Ram cpu_ram;
    Storage storage;
    Camera camera;
    Case aCase;

    /**
     * Selects components by model
     */
    abstract void prepare();

    /**
     * Set model name
     * @param name Model name
     */
    void setName(String name){
        this.name = name;
    }

    /**
     *
     * @return Return model name
     */
    String getName(){
        return name;
    }

    /**
     * attach the display on the phone
     */
    void attach_display(){ System.out.println("Display attached :" +  display.getSize()  +" "+ display.getSpec() );    }
    /**
     * attach the battery on the phone
     */
    void attach_battery(){
        System.out.println("Battery attached :" +   battery.getPower()+ " " +battery.getSpec());
    }
    /**
     * attach the storage on the phone
     */
    void attach_storage(){
        System.out.println("Storage attached :" +storage.getSpec()+" "+ storage.getSize()  );
    }
    /**
     * attach the camera on the phone
     */
    void attach_camera(){
        System.out.println("Camera attached :" +  camera.getFeatures()+" " + camera.getSpec()  );
    }
    /**
     * attach the cpu and ram on the phone
     */
    void attach_cpu_ram(){
        System.out.println("Cpu_ram attached :" +   cpu_ram.getFeatures()+ " "+cpu_ram.getSpec());
    }
    /**
     * attach the case on the phone
     */
    void attach_case(){
        System.out.println("Case attached :" +  aCase.getFeatures()+" "+ aCase.getSpec() );
    }

}
