import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SliderTextElement
{
    Slider slider = new Slider();
    Number newVal;
    TextField textFeld;

    String textBuffer;
    Double sliderBuffer;

    public SliderTextElement (int valMin, int valMax, int valDef, boolean tckLabels, boolean tckMarks, int tckUnit, int incrementStep, int tckCount, String text)
    {
        slider.setMin(valMin);
        slider.setMax(valMax);
        slider.setValue(valDef);
        slider.setShowTickLabels(tckLabels);
        slider.setShowTickMarks(tckMarks);
        slider.setMajorTickUnit(tckUnit);
        slider.setBlockIncrement(incrementStep);
        slider.setMinorTickCount(tckCount);
        newVal = valDef;

        textFeld = new TextField(text);

        textBuffer = textFeld.getText();
        sliderBuffer = slider.getValue();

    }

    public Slider createSlider (){return slider;}

    public TextField createTextFeld() {return textFeld;}

    public void update ()
    {
        if (this.sliderBuffer != slider.getValue())
        {
            try
            {
                textFeld.setText("" + slider.getValue());
                this.sliderBuffer = slider.getValue();
            }
            catch (java.lang.NumberFormatException e)
            {
                textFeld.setText(this.textBuffer);
            }

        }
        if (this.textBuffer.equals(textFeld.getText()) != true)
        {
            try
            {
                slider.setValue(Double.parseDouble(textFeld.getText()));
            }
            catch(java.lang.NumberFormatException e2)
            {
                slider.setValue(this.sliderBuffer);
            }
        }
    }

}

