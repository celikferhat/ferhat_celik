package Components;

/**
 * Battery Component
 */
public abstract class Battery {
    String spec;
    String power;

    /**
     * Returns battery type
     * @return Battery type
     */
    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     *
     * @return Battery power
     */
    public String getPower() {
        return power;
    }

    public void setPower(String size) {
        this.power = size;
    }
}
