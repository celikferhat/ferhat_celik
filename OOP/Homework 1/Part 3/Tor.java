public class Tor extends Suit{
    /**
     * Tor class extend from suit
     */
    public Tor(){
        description = "Tor Suit";
    }

    /**
     *
     * @return Tor suit cost
     */
    @Override
    public double cost() {
        return 5000;
    }

    /**
     *
     * @return Tor suit Weigth
     */
    @Override
    public double weigth() {
        return 50;
    }
}
