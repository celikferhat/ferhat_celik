package sample;


import javafx.scene.paint.Color;
public class Person {
    private static int largestID = 0;
    private final int id = largestID++;
    private double M; // Mask
    private int S; // Speed
    private int D; // Distance
    private int C; // Collision time
    private boolean healty = true;
    private boolean isDead = false;
    private Mediator mediator;
    private PersonBuilder builder;
    /**
     * Registers mediator
     * @param mediator takes mediator as parameter
     */
    public Person(PersonBuilder builder, Mediator mediator){
        this.builder = builder;
        this.mediator = mediator;
        this.M = builder.M;
        this.S = builder.S;
        this.D = builder.D;
        this.C = builder.C;
        this.healty = builder.healty;
    }

    /**
     *
     * @return Healt status
     */
    public boolean isHealty() {
        return healty;
    }

    /**
     *
     * @param healty Sets healt status
     */
    public void setHealty(boolean healty) {
        this.healty = healty;
    }

    /**
     *
     * @return Person is dead or alive
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     *
     * @param dead kill person
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     *
     * @return Person ID
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return Mask
     */
    public double getM() {
        return M;
    }

    /**
     *
     * @param m Mask
     */
    public void setM(double m) {
        M = m;
    }

    /**
     *
     * @return Speed
     */
    public int getS() {
        return S;
    }

    /**
     *
     * @param s Speed
     */
    public void setS(int s) {
        S = s;
    }

    /**
     *
     * @return Distance
     */
    public int getD() {
        return D;
    }

    /**
     *
     * @param d Distance
     */
    public void setD(int d) {
        D = d;
    }

    /**
     *
     * @return Social Factor
     */
    public int getC() {
        return C;
    }

    /**
     *
     * @param c Social Factor
     */
    public void setC(int c) {
        C = c;
    }

    /**
     * Update person status on canvas
     */
    public void Update(){
        mediator.Update(this);
    }


    /**
     * Builder pattern
     */
    public static class PersonBuilder{
        private double M = 1.0; // Mask
        private int S = 1; // Speed
        private int D = 1; // Distance
        private int C = 1; // Collision time
        private boolean healty = true;
        public PersonBuilder(){ }

        public PersonBuilder M(boolean M){
            if(M) this.M =0.2;
            else this.M = 1.0;

            return this;
        }
        public PersonBuilder S(int S){
            this.S = S;
            return this;
        }
        public PersonBuilder D(int D){
            this.D = D;
            return this;
        }
        public PersonBuilder C(int C){
            this.C = C;
            return this;
        }
        public PersonBuilder Healty(boolean h){
            this.healty = h;
            return this;
        }
        public Person build(Mediator mediator){
            return new Person(this,mediator);
        }


    }


}
