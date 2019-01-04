import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import java.text.NumberFormat;

public class SliderTextElement
{
    Slider slider = new Slider();
    Number newVal;
    TextField textFeld;

    String textBuffer;
    double sliderBuffer;

    int a;

    public SliderTextElement (int valMin, int valMax, int valDef, boolean tckLabels, boolean tckMarks, double tckUnit, double incrementStep, int tckCount, String text)
    {
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

        textFeld = new TextField(text);

        textBuffer = textFeld.getText();
        sliderBuffer = slider.getValue();
        a = valMax;
    }

    public Slider createSlider (){return slider;}

    public TextField createTextFeld() {return textFeld;}

    public void update () {
        if (this.sliderBuffer != (slider.getValue()) || this.textBuffer.equals(textFeld.getText()) == false ){
            try {
                textFeld.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
                //textFeld.positionCaret(this.getCursor());
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
}

