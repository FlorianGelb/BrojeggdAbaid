import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;



public class JAVA_UI extends  Application
{
    Pane root = new Pane();
    final BorderPane borderPane = new BorderPane();

    CreateSlider sliderVolumen = new CreateSlider((byte)0, (short)100, (short)1, true, true, (short)20, (short)1);
    CreateSlider sliderTemperatur = new CreateSlider((byte)0, (short)473, (short)294, true, true, (short)50, (short)1);
    CreateSlider sliderAnzahl = new CreateSlider((byte) 0, (short) 1000, (short) 100, true, true, (short) 100, (short) 10);

    CreateTextField textFieldVolumen = new CreateTextField("" + sliderVolumen.returnValue());
    CreateTextField textFieldTemperatur = new CreateTextField("" + sliderTemperatur.returnValue());
    CreateTextField textFieldAnzahl = new CreateTextField("" + sliderAnzahl.returnValue());

    Scene scene = new Scene(borderPane, 600, 400);



    public void start(Stage stage)
    {
        borderPane.setTop(createTopPane());
        borderPane.setCenter(createCenterPane());
        borderPane.setBottom(createBottomPane());
        borderPane.setLeft(createSettingsPane());

        stage.setTitle("Gassimmulator V1.0.26122018.0");
        stage.setScene(scene);
        stage.show();
    }

    public void  startSimulation()
    {
        SimulationTimer simualtionLoop = new SimulationTimer(this, 10);
        simualtionLoop.start();
    }

    public void updateSimulation()
    {

    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public Pane createTopPane()
    {
        final FlowPane TopPane = new FlowPane();

        return TopPane;
    }

    public Pane createCenterPane()
    {
        FlowPane CenterPane = new FlowPane();
        CenterPane.getChildren().add(root); // root will contain simulation stuff
        return CenterPane;
    }

    public Pane createBottomPane()
    {
       final FlowPane BottomPane = new FlowPane();
       BottomPane.getChildren().add(new Text ("Gassimmulator von Florian Braun"));
       return BottomPane;
    }

    public Pane createSettingsPane()
    {
        final VBox vBox = new VBox(5);
        vBox.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
        vBox.getChildren().addAll(
                new Text("Volumen [m^3]"),
                sliderVolumen.createSlider(),
                textFieldVolumen.createTextFeld(),
                new Text("Temperatur [K]"),
                sliderTemperatur.createSlider(),
                textFieldTemperatur.createTextFeld(),
                sliderAnzahl.createSlider(),
                textFieldAnzahl.createTextFeld(),
                new Button("Plot")
                );
        return vBox;
    }


}
