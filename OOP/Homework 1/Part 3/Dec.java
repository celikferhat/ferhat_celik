public class Dec extends Suit {
    /**
     * Dec class extend from suit
     */
    public Dec(){
        description = "Dec Suit";
    }

    /**
     *
     * @return Dec suit Cost
     */
    @Override
    public double cost() {
        return 500;
    }

    /**
     *
     * @return Dec suit Weigth
     */
    @Override
    public double weigth() {
        return 25;
    }
}
