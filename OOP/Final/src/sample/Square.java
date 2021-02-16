package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.sql.Timestamp;

public class Square implements Shape{
    private int id;
    private int cord_x;
    private int cord_y;
    private int width;
    private int velocity_x = 0;
    private int velocity_y = 0;
    private Long lastctime = null;
    private Long firstetime = null;
    private int Cmax;
    private Person person;
    private StopWatch stopWatch;

    /**
     * This method overrided because of that shape object equals if id's equal
     * @param obj shape object
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {

        return obj instanceof Shape && id == ((Square) obj).id;
    }

    /**
     *
     * @return shape id same as person id , every shape has person object and their ids same
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id set id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return encounter start time
     */
    public Long getLastctime() {
        return lastctime;
    }

    /**
     *
     * @return cmax
     */
    public int getCmax() {
        return Cmax;
    }

    /**
     *
     * @param cmax cmax
     */
    public void setCmax(int cmax) {
        Cmax = cmax;
    }

    /**
     *
     * @param lastctime encounter start time
     */
    public void setLastctime(Long lastctime) {
        this.lastctime = lastctime;
    }

    /**
     *
     * @param person person object for linking shape and person
     */
    public Square(Person person){
        this.person = person;
        this.id = person.getId();
        this.stopWatch = new StopWatch();
    }

    /**
     *
     * @return timer object
     */
    public StopWatch getStopWatch(){return  stopWatch;}

    /**
     *
     * @return returns linked person
     */
    public Person getPerson() {
        return person;
    }

    /**
     *
     * @param g draw square
     */
    public void draw(GraphicsContext g){
        if(person.isHealty())
            g.setFill(Color.GREEN);
        else
            g.setFill(Color.RED);

        g.fillRect(cord_x, cord_y, width, width);
    }

    /**
     *
     * @param width width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @return x cord
     */
    public int getCord_x() {
        return cord_x;
    }

    /**
     *
     * @param cord_x x cord
     */
    public void setCord_x(int cord_x) {
        this.cord_x = cord_x;
    }

    /**
     *
     * @return y cord
     */
    public int getCord_y() {
        return cord_y;
    }

    /**
     *
     * @param cord_y y cord
     */
    public void setCord_y(int cord_y) {
        this.cord_y = cord_y;
    }

    /**
     *
     * @return vx
     */
    public int getVelocity_x() {
        return velocity_x;
    }

    /**
     *
     * @return vy
     */
    public int getVelocity_y() {
        return velocity_y;
    }

    /**
     *
     * @param velocity_x vx
     */
    public void setVelocity_x(int velocity_x) {
        this.velocity_x = velocity_x;
    }

    /**
     *
     * @param velocity_y vy
     */
    public void setVelocity_y(int velocity_y) { this.velocity_y = velocity_y; }

    /**
     *
     * @return center x cord of square
     */
    public double centerX() { return cord_x + this.width * 0.5; };

    /**
     *
     * @return center y cord of square
     */
    public double centerY() { return cord_y + this.width * 0.5; };


    public int bottom() { return cord_y + this.width; };
    public int left() { return cord_x; };
    public int right() { return cord_x + this.width; };
    public int top() { return cord_y; };
}
