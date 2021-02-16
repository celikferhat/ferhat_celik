package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Shape interface
 */
public interface Shape {

    public StopWatch getStopWatch();
    public int getId();
    public void setId(int id);
    public int getVelocity_x();
    public int getVelocity_y();
    public void setVelocity_x(int velocity_x);
    public void setVelocity_y(int velocity_y);
    public void setWidth(int width);
    public int getCord_x();
    public int getCord_y();
    public void setCord_x(int x);
    public void setCord_y(int y);
    public Long getLastctime();
    public void setLastctime(Long lastctime);
    public int getCmax();
    public void setCmax(int cmax);
    public double centerX();
    public double centerY();
    public int bottom();
    public int left();
    public int right();
    public int top();

    public void draw(GraphicsContext g);
    public Person getPerson();

}
