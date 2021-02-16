public class Flamethrower extends  Components{
    /**
     * Same reaseons as Autorifle
     */
    Suit suit;
    /**
     * Constractor
     *
     * @param s It takes suit object as an argument because any suit can wear Flamethrower
     */
    public Flamethrower(Suit s){
        this.suit = s;
    }

    @Override
    public String getDescription() {
        return suit.getDescription() + " , Flamethrower";
    }

    @Override
    public double cost() {
        return 50 + suit.cost();
    }

    @Override
    public double weigth() {
        return 2 + suit.weigth();
    }
}
