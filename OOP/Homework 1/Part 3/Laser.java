public class Laser extends  Components{
    /**
     * Same reaseons as Autorifle
     */
    Suit suit;

    /**
     * Constractor
     *
     * @param s It takes suit object as an argument because any suit can wear Laser
     */
    public Laser(Suit s){
        this.suit = s;
    }

    @Override
    public String getDescription() {
        return suit.getDescription() + " , Laser";
    }

    @Override
    public double cost() {
        return 200 + suit.cost();
    }

    @Override
    public double weigth() {
        return 5.5 + suit.weigth();
    }
}
