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

	public Ball(double bx, double by, double radius, Color col)
	{
		super(bx, by, radius);
		x = bx;
		y = by;
		c = col;
		setFill(col);
		ballnummer = ballAnzahl;
		ballAnzahl++;

	}
	public void setColor(Color farbe)
	{
		c = farbe;
		setFill(farbe);
	}
	public void updatePosition()
	{
		x = x + vx;
		y = y + vy;

		relocate(x, y);
	}
	public boolean collideWith(HarterKoerper h)
	{
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