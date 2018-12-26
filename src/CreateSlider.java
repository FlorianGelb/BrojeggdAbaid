import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class CreateSlider extends Slider
{
    Slider slider = new Slider();
    Number newVal;

    public CreateSlider(int valMin, int valMax, int valDef, boolean tckLabels, boolean tckMarks, int tckUnit, int incrementStep, int tckCount)
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

    }

    public Slider createSlider ()
    {
        return  slider;
    }

    public double returnValue() {return (int)slider.getValue();}

    public Number listener() {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                newVal = newValue;
            }
        });
        return newVal;
    }
}
