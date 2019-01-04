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

    SliderTextElement Volumen = new SliderTextElement(0, 100, 1, true, true, 1, 1, 1, "1");
    SliderTextElement Temperatur = new SliderTextElement(0, 473, 294, true, true, 50, 1, 1, "294");
    SliderTextElement Anzahl = new SliderTextElement( 0,  10000,  1000, true, true,  2000,  10,1, "1000");


    Scene scene = new Scene(borderPane, 600, 400);

    SimulationTimer simualtionLoop;


    public void start(Stage stage)
    {
        borderPane.setTop(createTopPane());
        borderPane.setCenter(createCenterPane());
        borderPane.setBottom(createBottomPane());
        borderPane.setLeft(createSettingsPane());


        stage.setTitle("Gassimmulator V1.0.26122018.0");
        stage.setScene(scene);
        stage.show();

        startSimulation();
    }

    public void  startSimulation()
    {
        simualtionLoop = new SimulationTimer(this, 10);
        simualtionLoop.start();
    }

    public void updateSimulation()
    {

        if (Volumen.returnText() != Volumen.textBuffer || Volumen.returnSlider() != Volumen.sliderBuffer)
        {Volumen.update();}
        if (Temperatur.returnText() != Temperatur.textBuffer || Temperatur.returnSlider() != Temperatur.sliderBuffer)
        {Temperatur.update();}
        if (Anzahl.returnText() != Anzahl.textBuffer || Anzahl.returnSlider() != Anzahl.sliderBuffer)
        {Anzahl.update();}

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
                Volumen.createSlider(),
                Volumen.createTextFeld(),
                new Text("Temperatur [K]"),
                Temperatur.createSlider(),
                Temperatur.createTextFeld(),
                new Text("Anzahl der Teilchen"),
                Anzahl.createSlider(),
                Anzahl.createTextFeld(),
                new Button("Plot")
                );
        return vBox;
    }


}
