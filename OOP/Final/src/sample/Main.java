package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    AnimationTimer Loop;
    private boolean pause = false;
    private int second = 0;
    private Controller controller;
    Mediator mediator;
    Timer timer;
    List<Person> peopleList = new ArrayList<>();



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Epidemic Simulation");
        primaryStage.setScene(new Scene(root, 1400, 850));
        primaryStage.show();
        this.controller = fxmlLoader.getController();
        controller.drawCanvas();
        mediator = new Mediator(controller);
        controller.resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(pause){
                    mediator.resume();
                    Loop.start();
                    pause = false;
                }


            }
        });
        controller.pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!pause){
                    mediator.pause();
                    Loop.stop();
                    pause = true;
                }
            }
        });

        controller.add_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean M = controller.mask.isSelected();
                Integer S = (Integer) controller.speed.getValue();
                Integer D = (Integer) controller.social_distance.getValue();
                Integer C = (Integer) controller.social_factor.getValue();

                Integer amount = (Integer) controller.amount.getValue();

                for(int i = 0 ; i < amount ; i++){
                    Person person = new Person.PersonBuilder().M(M).S(S).D(D).C(C).Healty(true).build(mediator);
                    mediator.AddUser(person);
                    peopleList.add(person);
                }

            }
        });

        controller.reset_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediator.reset();
                peopleList = new ArrayList<>();
                Loop.stop();
                controller.resume.setDisable(true);
                controller.pause.setDisable(true);
                controller.add_button.setDisable(false);
                controller.start_button.setDisable(false);
            }
        });

        controller.start_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.add_button.setDisable(true);
                controller.start_button.setDisable(true);
                controller.resume.setDisable(false);
                controller.pause.setDisable(false);
                controller.reset_button.setDisable(false);
                Person person = new Person.PersonBuilder().M(false).S(2).D(1).C(2).Healty(false).build(mediator);
                mediator.AddUser(person);
                peopleList.add(person);
                mediator.setRZ();
                Loop.start();
                int w = 0;
                int wn = 0;
                for (Person p:peopleList ) {
                    if(p.getM() == 1.0)
                        wn++;
                    else
                        w++;
                }
                controller.update_maske(w,wn);
            }
        });

        start_timer();
    }






    private void start_timer(){
        timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    controller.setSecond_count(second);
                    second ++;
                });


            };
        };
        timer.schedule(tt, new Date(),1000); ;

        Loop = new AnimationTimer() {
            private long lastUpdate = 0 ;
            @Override
            public void handle(long l) {
                if (l - lastUpdate >= 40_000_000) {
                    controller.clear_canvas();
                    for (Person p:peopleList) {
                        p.Update();
                    }
                    lastUpdate = l ;
                    check_people();
                }

            }
        };

    }




    @Override
    public void stop() throws Exception {
        super.stop();
        timer.cancel();
    }
    private void check_people(){
        int ch = 0;
        int nch = 0;
        int cs = 0;
        int ncs = 0;
        int cd = 0;
        int ncd = 0;
        for (Person person : peopleList) {
            if (person.isHealty()){
                if(person.getM() == 0.2)
                    ch++;
                else
                    nch++;
            }
            else{
                if(!person.isDead()){
                    if(person.getM() == 0.2)
                        cs++;
                    else
                        ncs++;
                }
                else{
                    if(person.getM() == 0.2)
                        cd ++;
                    else
                        ncd++;
                }

            }

        }
        this.controller.setHealty_count(ch + nch);
        this.controller.setSick_count(cs + ncs);
        this.controller.setDead_count(cd + ncd);
        controller.update_pie_data(ch + nch,cs + ncs,cd + ncd);
        controller.update_third_chart(ch,nch,cs,ncs,cd,ncd);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
