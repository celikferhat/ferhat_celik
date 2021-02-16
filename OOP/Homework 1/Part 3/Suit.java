public abstract class Suit {
    /**
     * Description for suit names
     */
    String description;

    /**
     *
     * @return Getter method for description
     */
    public String getDescription(){
        return description;
    }

    /**
     *
     * @return Suit cost
     */
    public abstract double cost ();

    /**
     *
     * @return Suit weigth
     */
    public abstract double weigth();

}
