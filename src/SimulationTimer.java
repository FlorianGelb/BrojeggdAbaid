import javafx.animation.AnimationTimer;

public class SimulationTimer extends AnimationTimer
{
    JAVA_UI jfxApp = null;
    int msec;
    long lastUpdate = 0;

    public SimulationTimer( JAVA_UI app, int millisekunden)
    {
        msec = millisekunden;
        jfxApp = app;
    }

    @Override
    public void handle(long now)
    {
        if ((now-lastUpdate)> (msec * 1000000))
        {
            jfxApp.updateSimulation();
            lastUpdate = now;
        }
    }
}