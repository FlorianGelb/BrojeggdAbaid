public class Slider extends javafx.scene.control.Slider
{
    public Slider(byte valMin, short valMax, short valDef, boolean tckLabels, boolean tckMarks, short tckUnit, short incrementStep )
    {
        createSlider(valMin, valMax, valDef, true,tckMarks,tckUnit,incrementStep);
    }
    public Slider createSlider (byte valMin, short valMax, short valDef, boolean tckLabels, boolean tckMarks, short tckUnit, short incrementStep )
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
