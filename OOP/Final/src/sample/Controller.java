package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.Arrays;


public class Controller  {
    @FXML AnchorPane  mainpane;
    @FXML  public Canvas place ;
    @FXML  private Label healty_count;
    @FXML  private Label sick_count;
    @FXML  public Button resume;
    @FXML  public Button pause;
    @FXML  public Label sf_value;
    @FXML  public Slider sf_slider;
    @FXML  public Label df_value;
    @FXML  public Slider df_slider;
    @FXML  public Label scnd;
    @FXML  public CheckBox mask;
    @FXML  public ComboBox<?> speed;
    @FXML  public ComboBox<?> social_distance;
    @FXML  public ComboBox<?> social_factor;
    @FXML  public Spinner<?> amount;
    @FXML  public Button add_button;
    @FXML  public Button start_button;
    @FXML  public Button reset_button;
    @FXML  public Label dead_count;
    @FXML  public Label hospitalized;
    @FXML  private PieChart pieChart;
    @FXML  private PieChart maske_oran;

    private ObservableList<PieChart.Data> pieData;
    private ObservableList<PieChart.Data> maskeData;
    private  XYChart.Series<String, Number> series1;
    private  XYChart.Series<String, Number> series2;

    public void drawCanvas(){
        GraphicsContext gc = place.getGraphicsContext2D();
        place.setWidth(1000);
        place.setHeight(600);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1000, 600);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        gc.strokeRect(0,0,1000,600);
    }


    public void clear_canvas(){
        GraphicsContext gc = place.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1000, 600);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeRect(0,0,1000,600);
    }

    public void setHealty_count(int healty_count) {
        this.healty_count.setText(String.valueOf(healty_count));
    }
    public void setSick_count(int sick_count) {
        this.sick_count.setText(String.valueOf(sick_count));
    }
    public void setDead_count(int dead) {
        this.dead_count.setText(String.valueOf(dead));
    }
    public void setSecond_count(int sec) {
        this.scnd.setText(String.valueOf(sec));
    }
    public void initialize() {

        sf_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sf_value.setText(String.format("%.1f", newValue.doubleValue()));
        });
        df_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            df_value.setText(String.format("%.1f", newValue.doubleValue()));
        });


         pieData= FXCollections.observableArrayList(
                                        new PieChart.Data("Healty",1),
                                        new PieChart.Data("Sick",1),
                                        new PieChart.Data("Dead",1)
        );

        pieChart.setData(pieData);
        applyCustomColorSequence(
                pieData,
                "#008000",
                "#ea8824",
                "#ff0000"

        );
        pieChart.setLegendVisible(false);
        maskeData = FXCollections.observableArrayList(
                new PieChart.Data("Wear",1),
                new PieChart.Data("Not Wear",1)

        );
        maske_oran.setData(maskeData);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
                ("Healty", "Sick","Dead")));
        xAxis.setLabel("category");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Population");

        StackedBarChart<String, Number> mcomp =  new StackedBarChart<String,Number>(xAxis, yAxis);

        series1 = new XYChart.Series<>();
        series1.setName("Wear Mask");
        series1.getData().add(new XYChart.Data<>("Healty", 1));
        series1.getData().add(new XYChart.Data<>("Sick", 1));
        series1.getData().add(new XYChart.Data<>("Dead", 1));
        series2 = new XYChart.Series<>();
        series2.setName("Not Wear Mask");
        series2.getData().add(new XYChart.Data<>("Healty", 1));
        series2.getData().add(new XYChart.Data<>("Sick", 1));
        series2.getData().add(new XYChart.Data<>("Dead", 1));
        mcomp.getData().addAll(series1,series2);

        mcomp.setLayoutX(475.0);
        mcomp.setLayoutY(615.0);
        mcomp.setPrefHeight(220.0);
        mcomp.setPrefWidth(300.0);
        mcomp.setStyle("-fx-background-color: white");
        mainpane.getChildren().add(mcomp);



    }
    private void applyCustomColorSequence(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

    public void update_pie_data(int healty , int sick , int dead){
        for(javafx.scene.chart.PieChart.Data d : pieData)
        {
            switch (d.getName()) {
                case "Healty":
                    d.setPieValue(healty);
                    break;
                case "Sick":
                    d.setPieValue(sick);
                    break;
                case "Dead":
                    d.setPieValue(dead);
                    break;
            }

        }

    }
    public void update_maske(int wear,int not_wear){
        for(javafx.scene.chart.PieChart.Data d : maskeData)
        {
            switch (d.getName()) {
                case "Wear":
                    d.setPieValue(wear);
                    break;
                case "Not Wear":
                    d.setPieValue(not_wear);
                    break;

            }

        }
    }
    public void update_third_chart(int mh,int nmh,int ms,int nms,int md,int nmd){
        series1.getData().get(0).setYValue(mh);
        series1.getData().get(1).setYValue(ms);
        series1.getData().get(2).setYValue(md);
        series2.getData().get(0).setYValue(nmh);
        series2.getData().get(1).setYValue(nms);
        series2.getData().get(2).setYValue(nmd);


    }


}
