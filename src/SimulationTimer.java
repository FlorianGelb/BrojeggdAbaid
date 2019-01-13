import javafx.animation.AnimationTimer;

/**
 * @author Uwe Lorenz
 */
public class SimulationTimer extends AnimationTimer
{
    MyJfxApp jfxApp = null;
    double msec;
    long lastUpdate = 0;
    /**
     * Der Konstruktor der Klasse UserInterfaceTimer initialisiert msec und jfxApp mit den Parametern.
     * @param app Nimmt die Klasse entgegen, auf die der Timer zugreifen soll.
     * @param millisekunden gibt an, wie oft der Timer updaten soll.
     */
    public SimulationTimer( MyJfxApp app, int millisekunden)
    {
        msec = millisekunden;
        jfxApp = app;
    }

    @Override
    public void handle(long now)
    {
        if ((now-lastUpdate)> (msec * 1000))
        {
            jfxApp.updateSimulation();
            lastUpdate = now;
        }
    }
}