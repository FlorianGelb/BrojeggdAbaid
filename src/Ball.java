import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * @author Lorenz
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
	
	public Ball(double bx, double by, double radius, Color color) {
		super(bx, by, radius);
		x = bx;
		y = by;
		c = color;
		
		setFill(color);
		
		ballnummer = ballAnzahl;
		ballAnzahl++;
		
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getVx()
	{
		return vx;
	}
	
	public double getVy()
	{
		return vy;
	}
	
	public void setColor(Color farbe) {
		c = farbe;
		setFill(farbe);
	}
	
	public void updatePosition() {
		x = x + vx;
		y = y + vy;
		
		relocate(x, y);
	}
	
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