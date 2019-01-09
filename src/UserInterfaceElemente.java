import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.text.NumberFormat;
import javafx.scene.control.Button;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class UserInterfaceElemente
{
    Slider slider = new Slider();
    Number newVal;
    TextField textFeld;
    Button button = new Button();

    String textBuffer;
    double sliderBuffer;

    boolean clicked = false;
    NumberAxis xAchse = new NumberAxis();
    NumberAxis yAchse = new NumberAxis();
    final LineChart<Number, Number> Diagramm = new LineChart<Number, Number>(xAchse, yAchse);

    public UserInterfaceElemente()
    {




    }
    public LineChart createLineChart()
    {
        return Diagramm;
    }

    public void updateDiagramm(double x, double y)
    {
        XYChart.Series update = new XYChart.Series();
        update.getData().add(new XYChart.Data(x,y));
    }

    public Button createButton(String text)
    {
        button.setText(text);
        return button;
    }

    public boolean returnButtonValue()
    {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            clicked = true;
            }
        });
        return clicked;
    }

    public void updateTextfeld (String newString){textFeld.setText(newString);}

    public TextField createTextFeld(String text) {
        textFeld = new TextField(text);
        return textFeld;
    }

    public void update () {
        if (this.sliderBuffer != (slider.getValue()) || this.textBuffer.equals(textFeld.getText()) == false ){
            try {
                textFeld.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
                this.sliderBuffer = slider.getValue();
                this.textBuffer = textFeld.getText();
            } catch (Throwable e) {
                textFeld.setText(this.textBuffer);
                slider.setValue(Double.parseDouble(this.textBuffer));
            }
        }
    }

    public int getCursor()
    {
        String[] list = textFeld.getText().split("");
        for (int i = 0; i< list.length; i++)
        {
            if(list[i].equals(","))
            {
                return i;
            }
        }
        return list.length;
    }
    public String returnText(){return textFeld.getText();}
    public double returnSlider(){return slider.getValue();}
    public Slider createSlider(int valMin, int valMax, int valDef, boolean tckLabels, boolean tckMarks, double tckUnit, double incrementStep, int tckCount) {

//        textBuffer = textFeld.getText();
  //      sliderBuffer = slider.getValue();

        slider.setMin(valMin);
        slider.setMax(valMax);
        slider.setValue(valDef);
        slider.setShowTickLabels(tckLabels);
        slider.setShowTickMarks(tckMarks);
        slider.setMajorTickUnit(tckUnit);
        slider.setBlockIncrement(incrementStep);
        slider.setMinorTickCount(tckCount);
        slider.setSnapToTicks(true);
        newVal = valDef;

        return slider;}
}

