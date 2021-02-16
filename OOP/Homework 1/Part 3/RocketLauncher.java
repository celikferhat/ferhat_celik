public class RocketLauncher extends  Components{
    /**
     * Same reaseons as Autorifle
     */
    Suit suit;          // Same as Autorifle

    /**
     * Constractor
     *
     * @param s It takes suit object as an argument because any suit can wear rocket launcher
     */
    public RocketLauncher(Suit s){
        this.suit = s;
    }

    @Override
    public String getDescription() {
        return suit.getDescription() + " , RocketLauncher";
    }

    @Override
    public double cost() {
        return 150 + suit.cost();
    }

    @Override
    public double weigth() {
        return 7.5 + suit.weigth();
    }
}
