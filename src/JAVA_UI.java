import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;


public class JAVA_UI extends  Application
{
    Pane root = new Pane();
    final BorderPane borderPane = new BorderPane();

    CreateSlider sliderVolumen = new CreateSlider((byte)0, (short)100, (short)1, true, true, (short)20, (short)1);
    CreateSlider sliderTemperatur = new CreateSlider((byte)0, (short)473, (short)294, true, true, (short)50, (short)1);

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
                sliderVolumen.createSlider(),
                new TextField(),
                new Text("Temperatur [K]"),
                sliderTemperatur.createSlider(),
                new TextField()
                );

        return vBox;
    }


}
