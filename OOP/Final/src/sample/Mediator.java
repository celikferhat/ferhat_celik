package sample;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mediator implements SimulationMediator{
    /**
     * controller reference for getting Graphic Context
     */
    private Controller controller;
    /**
     * Shape width and height
     */
    private final int p_square = 5;
    /**
     * Mortality factor
     */
    private double R ;
    /**
     * Every person should die after dead time if s/he does not go to hospital
     */
    private int dead_time;
    /**
     * number of ventilators
     */
    private int B ;
    /**
     * Person number inside hospital
     */
    private int hc = 0;
    /**
     * Every shape represent a person
     */
    private List<Shape> shapeList = new ArrayList<>();
    /**
     * Hospital array
     */
    private final List<Shape> hospital  = new ArrayList<>();

    public Mediator(Controller controller){ this.controller = controller;  }

    /**
     * initialization of mortality factor and dead_time
     */
    public void setRZ(){
        R = controller.sf_slider.getValue();
        B = shapeList.size() / 100;
        dead_time =  (int)(100 * (1.0 - controller.df_slider.getValue() + 0.001 )   );
        dead_time = dead_time * 1000;

    }

    /**
     * Takes person as a parameter and creates new shape with this person
     * @param person Creates a shape and pass this person to shape
     */
    public void AddUser(Person person) {
        Shape shape = new Square(person);
        List<Integer> vxy = new_velocity();
        int rand_vx = vxy.get(0) * person.getS();
        int rand_vy = vxy.get(1) * person.getS();
        shape.setVelocity_x(rand_vx);
        shape.setVelocity_y(rand_vy);
        shape.setWidth(p_square);
        setRandCord(shape);
        shapeList.add(shape);
        shape.draw(controller.place.getGraphicsContext2D());
    }

    /**
     * Generates random x,y cords
     * @param shape generated cords set to this shape
     */
    private void setRandCord(Shape shape){
        int rand_x = (int)(Math.random() * (990 - p_square) - 10 ) + 20;
        int rand_y = (int)(Math.random() * (590 - p_square) - 10) + 20;
        shape.setCord_x(rand_x);
        shape.setCord_y(rand_y);
        for(int j = 0 ; j < shapeList.size() ; j++ ){
            if(distance(shape, shapeList.get(j) ) ){
                rand_x = (int)(Math.random() * 980) + 10;
                rand_y = (int)(Math.random() * 580) + 10;
                shape.setCord_x(rand_x);
                shape.setCord_y(rand_y);
                j = -1;
            }
        }

    }

    /**
     * Determines collision
     * @param s1 shape 1
     * @param s2 shape 2
     * @return if there is collision returns true , else returns false
     */
    private boolean distance(Shape s1 , Shape s2){
        int xdif = Math.abs(s1.getCord_x() - s2.getCord_x());
        int ydif = Math.abs(s1.getCord_y() - s2.getCord_y());
        return xdif < p_square && ydif < p_square;
    }

    /**
     * Resolves collisions,If 2 healty person or 2 sick person meets , they crash into each other and go in opposite directions
     * @param s1 shape 1
     * @param s2 shape 2
     * @return resolved shapes returns
     */
    private List<Shape> resolveCollision(Shape s1 , Shape s2){
/*
        if( Math.abs(s1.getCord_x() - s2.getCord_x()) > Math.abs(s1.getCord_y() - s2.getCord_y()) ){
            s1.setVelocity_x(-1 * s1.getVelocity_x());
            s2.setVelocity_x(-1 * s2.getVelocity_x());
        }else{
            s1.setVelocity_y(-1 * s1.getVelocity_y());
            s2.setVelocity_y(-1 * s2.getVelocity_y());
        }
*/



        List<Integer> vlst =  new_velocity();
        s1.setVelocity_x(vlst.get(0) * s1.getPerson().getS());
        s1.setVelocity_y(vlst.get(1) * s1.getPerson().getS());

        vlst =  new_velocity();
        s2.setVelocity_x(vlst.get(0) * s2.getPerson().getS());
        s2.setVelocity_y(vlst.get(1) * s2.getPerson().getS());


        double vector_x, vector_y;
        vector_x = s1.centerX() - s2.centerX();
        vector_y = s1.centerY() - s2.centerY();

        if(Math.abs(vector_y) > Math.abs(vector_x)){
            if (vector_y > 0)
                s1.setCord_y(s2.bottom());
            else
                s1.setCord_y(s2.getCord_y() - p_square);

        }
        else{
            if (vector_x > 0)
                s1.setCord_x(s2.right());
             else
                s1.setCord_x(s2.getCord_x() - p_square);
        }



        if(s1.bottom() >= 990){
            s1.setCord_y(990 - p_square);
        }
        if(s2.bottom() >= 990){
            s2.setCord_y(990 - p_square);
        }


        List<Shape> pl = new ArrayList<>();
        pl.add(s1);
        pl.add(s2);
        return pl;
    }

    /**
     *  Checks border collision and resolves collision
     * @param s shape
     * @return resolved shape
     */
    private Shape check_border_collision(Shape s){


        if(  (s.bottom() >= 595 || s.top() <= 5) || (s.left() <= 5 || s.right() >= 995)  ){
            if(s.bottom() >= 595){
                s.setCord_y(594 - p_square);
                s.setVelocity_y(-1 * Math.abs(s.getVelocity_y()));
            }
            if(s.top() <= 5){
                s.setCord_y(5);
                s.setVelocity_y(Math.abs(s.getVelocity_y()));
            }
            if(s.left() <= 5){
                s.setCord_x(5);
                s.setVelocity_x(Math.abs(s.getVelocity_x()));
            }
            if(s.right() >= 995){
                s.setCord_x(994 - p_square);
                s.setVelocity_x(-1 * Math.abs(s.getVelocity_x()));
            }
        }

        return s;
    }

    /**
     * Calculates new cords and checks times
     * @param s shape
     * @return if shape goes in hospital or die returns null , else returns calculated shape
     */
    private Shape calculate_next_step(Shape s){


        if(s.getStopWatch().isRunning()  && s.getStopWatch().getElapsedTime() >= dead_time && !inHospital(s)) {
            s.getPerson().setDead(true);
            shapeList.remove(s);
            return null;
        }
        else if(inHospital(s)){
             s = consume(s);
             if(s == null)
                return null;

        }
        else{
            s = produce(s);
            if(s == null){
                return null;
            }

        }
        if(s.getLastctime() == null){
            s.setCord_x(s.getVelocity_x() + s.getCord_x());
            s.setCord_y(s.getVelocity_y() + s.getCord_y());
        }else {
            if(System.currentTimeMillis() - s.getLastctime()  >= s.getCmax() * 1000.0){
                s.setCord_x(s.getVelocity_x() + s.getCord_x());
                s.setCord_y(s.getVelocity_y() + s.getCord_y());
                s.setLastctime(null);
            }
        }

        return s;
    }

    /**
     * Takes person and finds his shape and updates his shape
     * @param person person
     */
    public void Update(Person person){
        Shape shape = new Square(person);
        int index = shapeList.indexOf(shape);

        if(index == -1){
            if(inHospital(shape)){
                shape = calculate_next_step(shape);
                if(shape != null){
                    shape.draw(controller.place.getGraphicsContext2D());
                }
                return;
            }

            else
                return;
        }

        for(int j = 0 ; j < shapeList.size() ; j++){
            if(index == j)
                continue;
            if(distance(shapeList.get(index) , shapeList.get(j))){
                if(shapeList.get(index).getLastctime() == null &&  shapeList.get(j).getLastctime() == null){
                    if( (!shapeList.get(index).getPerson().isHealty() && shapeList.get(j).getPerson().isHealty()) || (shapeList.get(index).getPerson().isHealty() && !shapeList.get(j).getPerson().isHealty())) {
                        shapeList.get(index).setLastctime(System.currentTimeMillis());
                        shapeList.get(j).setLastctime(System.currentTimeMillis());

                        shapeList.get(index).setCmax(Math.max(shapeList.get(index).getPerson().getC(), shapeList.get(j).getPerson().getC()));
                        shapeList.get(j).setCmax(Math.max(shapeList.get(index).getPerson().getC(), shapeList.get(j).getPerson().getC()));


                        double M1 = shapeList.get(index).getPerson().getM();
                        double M2 = shapeList.get(j).getPerson().getM();
                        int C = Math.max(shapeList.get(index).getPerson().getC(), shapeList.get(j).getPerson().getC());
                        int D = Math.min(shapeList.get(index).getPerson().getD(), shapeList.get(j).getPerson().getD());
                        double P = R * (1+C/10.0) * M1 * M2 * (1-D/10.0);

                        if( Math.random() <= P ){
                            shapeList.get(index).getPerson().setHealty(false);
                            shapeList.get(j).getPerson().setHealty(false);

                            if(!shapeList.get(index).getStopWatch().isRunning())
                                shapeList.get(index).getStopWatch().start();
                            if(!shapeList.get(j).getStopWatch().isRunning())
                                shapeList.get(j).getStopWatch().start();

                        }


                    }
                    List<Shape> pl = resolveCollision(shapeList.get(index), shapeList.get(j));
                    shapeList.set(index, pl.get(0));
                    shapeList.set(j, pl.get(1));
                }
            }
        }
        Shape s = check_border_collision( shapeList.get(index));
        s = calculate_next_step(s);


        if(s != null){
            shapeList.set(index, s);
            shapeList.get(index).draw(controller.place.getGraphicsContext2D());
        }

    }

    /**
     * Calculates new velocity
     * @return velocity list (vx , vy)
     */
    private List<Integer> new_velocity(){
        int rand_vx = new Random().nextInt(1-(-1)+1)-1;
        int rand_vy = new Random().nextInt(1-(-1)+1)-1;
        while (rand_vx == 0 || rand_vy == 0){
            rand_vx = new Random().nextInt(1-(-1)+1)-1;
            rand_vy = new Random().nextInt(1-(-1)+1)-1;
        }
        List<Integer> list = new ArrayList<>();
        list.add(rand_vx);
        list.add(rand_vy);
        return list;
    }


    /**
     * If 25 seconds have passed since the person became sick and there is a place in the hospital, they are recorded in the hospital.
     * @param s shape
     * @return if enrolled in hospital returns null , else returns shape
     */
    private Shape produce(Shape s){
        if(B > hospital.size() && s.getStopWatch().isRunning()){
            if(s.getStopWatch().getElapsedTime() >= 25000){
                s.getStopWatch().reset();
                s.getStopWatch().start();
                hc ++;
                controller.hospitalized.setText(String.valueOf(hc));
                shapeList.remove(s);
                hospital.add(s);
                return null;
            }
        }
        return s;
    }

    /**
     * discharges recovered patients.
     * @param s shape
     * @return if person inside hospital and 10 seconds have passed , discharges this person
     */
    private Shape consume(Shape s){
        int index = hospital.indexOf(s);

        if(hospital.size() > 0 && index != -1 ){
            if(hospital.get(index).getStopWatch().getElapsedTime() >= 10000){
                hospital.remove(index);
                s.getStopWatch().reset();
                s.getPerson().setHealty(true);
                s.setLastctime(null);
                setRandCord(s);
                List<Integer> vl = new_velocity();
                s.setVelocity_x(vl.get(0) * s.getPerson().getS());
                s.setVelocity_y(vl.get(1)* s.getPerson().getS());
                s.setWidth(p_square);
                shapeList.add(s);

                return s;
            }

        }
        return null;
    }

    /**
     *
     * @param s gets shape
     * @return if person inside hospital returns true , else returns false
     */
    private boolean inHospital(Shape s){
        int index = hospital.indexOf(s);
        return index != -1;
    }

    /**
     * clear canvas
     */
    public void reset(){
        this.controller.clear_canvas();
        shapeList = new ArrayList<>();
    }

    /**
     * pause timers
     */
    public void pause(){
        for (Shape s:shapeList) {
            s.getStopWatch().pause();
        }
        for (Shape s:hospital) {
            s.getStopWatch().pause();
        }

    }

    /**
     * resume timers
     */
    public void resume(){
        for (Shape s:shapeList) {
            s.getStopWatch().resume();
        }
        for (Shape s:hospital) {
            s.getStopWatch().resume();
        }
    }

}
