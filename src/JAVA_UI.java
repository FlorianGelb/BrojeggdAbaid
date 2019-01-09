import com.sun.org.apache.xpath.internal.Arg;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;


public class JAVA_UI extends  Application
{
    boolean tick = false;
    static String[] Args = new String[4];
    Pane root = new Pane();
    final BorderPane borderPane = new BorderPane();

    UserInterfaceElemente Volumen = new UserInterfaceElemente();
    UserInterfaceElemente Temperatur = new UserInterfaceElemente();
    UserInterfaceElemente Anzahl = new UserInterfaceElemente();
    UserInterfaceElemente Diagramm = new UserInterfaceElemente();
    UserInterfaceElemente Diagramm2 = new UserInterfaceElemente();

    Scene scene = new Scene(borderPane, 600, 400);

    SimulationTimer simualtionLoop;


    public void start(Stage stage)
    {
        borderPane.setTop(createTopPane());
        borderPane.setCenter(createCenterPane());
        borderPane.setBottom(createBottomPane());
        borderPane.setLeft(createSettingsPane());


        stage.setTitle("Gassimmulator V1.1.0812019.0");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.getIcons().add(new Image("Main_Icon.png"));
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

            for (int  i = 0; i < 140; i++)
            {
                Diagramm.updateDiagramm(Math.random() * 100,Math.random() * 1000, "1");
                Diagramm2.updateDiagramm((i*2)*i, i, "1");

            }

        }
            else if (tick && Diagramm.returnButtonValue())
            { borderPane.setRight(null);
            tick = !tick;
            Diagramm.clicked = false;
            Diagramm.destroyDiagramm();
            Diagramm2.destroyDiagramm();
            }
    }

    public static void main(String[] args)
    {
        try {
            for (int i = 0; i < 4; i++) {
                String arg = args[0];
                Args = arg.split(";");
                launch(args);
            }
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e)
            {
                Args[0] = "1";
                Args[1] = "294";
                Args[2] = "1000";
                launch(args);
            }
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
                Volumen.createTextFeld(Args[0]),
                new Text("Temperatur [K]"),
                Temperatur.createTextFeld(Args[1]),
                new Text("Anzahl der Teilchen"),
                Anzahl.createTextFeld(Args[2]),
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
              //  Diagramm.createSlider(0,100, 0,true, true, 5,1,1),
                //new Text("Zoom Diagramm 1"),
                Diagramm2.createLineChart()
               // Diagramm2.createSlider(0,100, 0,true, true, 5,1,1),
                //new Text ("Zoom Diagramm 2")
        );
        return vBox;
    }
}
