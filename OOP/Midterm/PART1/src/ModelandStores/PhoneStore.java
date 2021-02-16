package ModelandStores;

public abstract class PhoneStore {
    /**
     *
     * @param model Taking phone model as parameter
     * @return Returns An incomplete phone
     */
    protected abstract Phone createPhone(String model);


    /**
     *  Makes a new phone and returns
     * @param model Takes model name
     * @return  Returns phone
     */
    public Phone orderPhone(String model){
        Phone phone = createPhone(model);
        phone.attach_cpu_ram();
        phone.attach_display();
        phone.attach_battery();
        phone.attach_storage();
        phone.attach_camera();
        phone.attach_case();

        return phone;
    }
}
