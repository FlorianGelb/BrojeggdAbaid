import javafx.scene.control.Slider;

public class CreateSlider extends Slider
{
    Slider slider = new Slider();

    public CreateSlider(byte valMin, short valMax, short valDef, boolean tckLabels, boolean tckMarks, short tckUnit, short incrementStep )
    {

        slider.setMin(valMin);
        slider.setMax(valMax);
        slider.setValue(valDef);
        slider.setShowTickLabels(tckLabels);
        slider.setShowTickMarks(tckMarks);
        slider.setMajorTickUnit(tckUnit);
        slider.setBlockIncrement(incrementStep);

    }

    public Slider createSlider ()
    {
        return  slider;
    }

    public double returnValue()
    {
        return slider.getValue();
    }


}
