import javafx.animation.AnimationTimer;

/**
 * @author Florian Braun
 * Die Klasse UserInterfaceTimer basiet auf der Klasse SimulationTimer von Uwe Lorenz
 */

public class UserInterfaceTimer extends AnimationTimer
{
    MyJfxApp jfxApp = null;
    int msec;
    long lastUpdate = 0;
    
    /**
     * Der Konstruktor der Klasse UserInterfaceTimer initialisiert msec und jfxApp mit den Parametern.
     * @param app Nimmt die Klasse entgegen, auf die der Timer zugreifen soll.
     * @param millisekunden gibt an, wie oft der Timer updaten soll.
     */
    public UserInterfaceTimer(MyJfxApp app, int millisekunden)
    {
        msec = millisekunden;
        jfxApp = app;
    }

    @Override
    public void handle(long now)
    {
        if ((now-lastUpdate)> (msec * 1000000))
        {
            jfxApp.updateUI();
            lastUpdate = now;
        }
    }
}