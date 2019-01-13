import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * @author Uwe Lorenz und Florian Braun
 * @version 1
 */
public class Ball extends Circle implements HarterKoerper {
	static int ballAnzahl = 0;
	int ballnummer = 0;
	double radius;
	double x;
	double y;
	double vx = 0.4;
	double vy = 0.2;
	Color c = Color.BLUE;
	
	/**
	 * Der Kunstruktor ruft den Konstruktor der Superklasse Circle auf und Übergibt die Parameter bx, by und radius.
	 * @param bx X-Koordinate relativ zur centerPane
	 * @param by Y-Koordinate relativ zur centerPane
	 * @param radius Radius des Teilchens
	 * @param color Farbe des Teilchens
	 */
	public Ball(double bx, double by, double radius, Color color) {
		super(bx, by, radius);
		x = bx;
		y = by;
		c = color;
		
		setFill(color);
		
		ballnummer = ballAnzahl;
		ballAnzahl++;
	}
	
	/**
	 * getter für die momentane X-Koordinate
	 * @return momentane X-Koordinate
	 */
	public double getX()
	{
		return x;
	}
	/**
	 * getter für die momentane Y-Koordinate
	 * @return momentane Y-Koordinate
	 */
	public double getY()
	{
		return y;
	}
	/**
	 * getter für den momentanen Geschwindigkeitsvektor in X-Richtung
	 * @return momentaner Geschwindigkeitsvektor in X-Richtung
	 */
	public double getVx()
	{
		return vx;
	}
	/**
	 * getter für den momentanen Geschwindigkeitsvektor in Y-Richtung
	 * @return momentaner Geschwindigkeitsvektor in Y-Richtung
	 */
	public double getVy()
	{
		return vy;
	}
	
	/**
	 * Setter für Farbe des Teilchens
	 * @param farbe Farbwert für die neue Farbe
	 */
	public void setColor(Color farbe) {
		c = farbe;
		setFill(farbe);
	}
	
	/**
	 * Aktualisiert die momentane Position.
	 */
	public void updatePosition() {
		x = x + vx;
		y = y + vy;
		
		relocate(x, y);
	}
	
	/**
	 * Überprüft ob das Teilchen mit einem anderen Objekt, welches über das Interface HartenKörper verfügt, kollidiert ist.
	 * @param h ist das andere Objekt, welches auf eine Kollision überprüft wird
	 * @return true, falls es eine Kollision gab, false, wenn es keine Kollision gab.
	 * Der Autor dieser Methode ist Uwe Lorenz.
	 */
	public boolean collideWith(HarterKoerper h) {
		if (h.getClass() == Ball.class) {
			Ball b = (Ball) h;
			double x_ball = b.getX() + b.getVx();
			double y_ball = b.getY() + b.getVy();
			
			if (((this.x - x_ball) * (this.x - x_ball) +
					(this.y - y_ball) * (this.y - y_ball)) <= (this.radius + b.getRadius()) * (this.radius + b.getRadius())) {
				return true;
			}
		}
		return false;
	}
}