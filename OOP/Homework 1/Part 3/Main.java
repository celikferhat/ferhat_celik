
public class Main {

    public static void main(String [] args){
        Suit suit = new Tor();
        suit = new Flamethrower(suit);
        suit = new AutoRifle(suit);
        suit = new RocketLauncher(suit);

        System.out.println(suit.getDescription() + "\n Cost : " + suit.cost() + "k TL  Weigth : " + suit.weigth() + " kg");

    }

}
