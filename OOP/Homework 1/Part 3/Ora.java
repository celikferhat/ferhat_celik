public class Ora  extends Suit{
    /**
     * Ora class extend from suit
     */
    public Ora(){
        description = "Ora Suit";
    }

    /**
     *
     * @return Ora suit cost
     */
    @Override
    public double cost() {
        return 1500;
    }

    /**
     *
     * @return Ora suit Weigth
     */
    @Override
    public double weigth() {
        return 30;
    }
}
