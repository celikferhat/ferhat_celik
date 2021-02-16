public class AutoRifle extends  Components{
    /**
     * Autorifle is a component so there is a suit object inside for it can be attach
     */
    Suit suit;

    /**
     * Constractor
     *
     * @param s It takes suit object as an argument because any suit can wear autorifle
     */
    public AutoRifle(Suit s){
        this.suit = s;
    }

    /**
     *
     * @return Description of all worn parts
     */
    @Override
    public String getDescription() {
        return suit.getDescription() + " , AutoRifle";    }

    /**
     *
      * @return Autorifle cost + cost of other parts
     */
    @Override
    public double cost() {
        return 30 + suit.cost();
    }

    /**
     *
     * @return Autotifle weigth + weigth of other parts
     */
    @Override
    public double weigth() {
        return 1.5 + suit.weigth();
    }
}
