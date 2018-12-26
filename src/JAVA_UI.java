import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;


public class JAVA_UI extends  Application
{
    Pane root = new Pane();
    final BorderPane borderPane = new BorderPane();

    Scene scene = new Scene(borderPane, 600, 400);

    public void start(Stage stage)
    {
        borderPane.setTop(createTopPane());
        borderPane.setCenter(createCenterPane());
        borderPane.setBottom(createBottomPane());
        borderPane.setLeft(createSettingsPane());

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    public void updateSimulation()
    {
        System.out.println("1");
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
       return BottomPane;
    }

    public Pane createSettingsPane()
    {
        final VBox vBox = new VBox(5);
        vBox.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
        vBox.getChildren().addAll(
                new Text("Volumen [m^3]"),
                createSlider((byte)0, (short)100, (short)1, true, true, (short)20, (short)1),
                new Text("Temperatur [K]"),
                createSlider((byte)0, (short)473, (short)294, true, true, (short)50, (short)1)
                );
        return vBox;
    }

    public Slider createSlider(byte valMin, short valMax, short valDef, boolean tckLabels, boolean tckMarks, short tckUnit, short incrementStep )
    {
        Slider slider = new Slider();
        slider.setMin(valMin);
        slider.setMax(valMax);
        slider.setValue(valDef);
        slider.setShowTickLabels(tckLabels);
        slider.setShowTickMarks(tckMarks);
        slider.setMajorTickUnit(tckUnit);
        slider.setBlockIncrement(incrementStep);

        return  slider;
    }
}
