import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;


public class JAVA_UI extends  Application
{
    boolean tick = false;

    Pane root = new Pane();
    final BorderPane borderPane = new BorderPane();

    UserInterfaceElemente Volumen = new UserInterfaceElemente(0, 100, 1, true, true, 1, 1, 1, "1");
    UserInterfaceElemente Temperatur = new UserInterfaceElemente(0, 473, 294, true, true, 50, 1, 1, "294");
    UserInterfaceElemente Anzahl = new UserInterfaceElemente( 0,  10000,  1000, true, true,  2000,  10,1, "1000");
    UserInterfaceElemente Diagramm = new UserInterfaceElemente(0,0,0,false,false,0.1,0.0,0,"0");
    UserInterfaceElemente Diagramm2 = new UserInterfaceElemente(0,0,0,false,false,0.1,0.0,0,"0");

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
        stage.setMaximized(true);
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
        if (tick == false && Diagramm.returnButtonValue())
        {
            borderPane.setRight(createChartPane());
            tick = !tick;
            Diagramm.clicked = false;

        }
            else if (tick && Diagramm.returnButtonValue())
            { borderPane.setRight(null);
            tick = !tick;
            Diagramm.clicked = false;
            }
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
                Volumen.createTextFeld(),
                new Text("Temperatur [K]"),
                Temperatur.createTextFeld(),
                new Text("Anzahl der Teilchen"),
                Anzahl.createTextFeld(),
                Diagramm.createButton("Plot")
                );
        return vBox;
    }

    public Pane createChartPane()
    {
        final VBox vBox = new VBox(5);
        vBox.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
        vBox.getChildren().addAll(
        Diagramm.createLineChart(),
                Diagramm2.createLineChart()
        );
        return vBox;
    }
}
