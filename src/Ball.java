import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * @version 1
 * @author Lorenz
 */
public class Ball extends Circle implements HarterKoerper {
    static int ballAnzahl = 0;
    int ballnummer = 0;
	double radius = 4;
	double x = 0;
	double y = 0;
	double vx = 0.4;
	double vy = 0.2;
	double ax = 1;
	double ay = 9.81;
	double m = 1;
	Color c = Color.BLUE;

	/**
	 * Konstruktor der Ballklasse.
	 *
	 * @param bx     x-Koordinate des Startpunktes.
	 * @param by     y-Koordinate des Startpunktes.
	 * @param radius Radius des Balls.
	 * @param col    Farbe des Balls.
	 */
	public Ball(double bx, double by, double radius, Color col) {
		super(bx, by, radius);
		x = bx;
		y = by;
		c = col;
		setFill(col);
		ballnummer = ballAnzahl;
		ballAnzahl++;

	}

	/**
	 * Setzt die Farbe des Balls.
	 *
	 * @param farbe
	 */
	public void setColor(Color farbe) {
		c = farbe;
		setFill(farbe);
	}

	/**
	 * Aktualisiert die Ballposition entsprechend der Geschwindigkeitsattribute.
	 */
	public void updatePosition() {

		x = x + vx;
		y = y + vy;

		relocate(x, y);
	}

	/**
	 * Diese Methode wird vom Simulationstimer immer wieder aufgerufen.
	 *
	 *
	 */
	public boolean collideWith(HarterKoerper h) {
		if (h.getClass() == Ball.class) {
			Ball b = (Ball) h;
			if (((this.x - b.x) * (this.x - b.x) + (this.y - b.y) * (this.y - b.y)) < (this.radius + b.radius) * (this.radius + b.radius)) {
				return true;
			}
			return false;
		}
		return false;
	}

}