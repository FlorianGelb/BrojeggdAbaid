import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;

/**
 * Übungsapplikation zur objektorientierten Programmierung mit JavaFX.
 * 
 * @version 2
 * @author Lorenz
 * 
 * Das kennst du schon:
 * - Variablen und elementare Datentypen
 * - Gültigkeitsbereiche von Variablen
 * - Arrays
 * - Listen
 * - Bäume/Graphen
 * - die OOP-Grundbegriffe Klasse, Objekt, Attribut, Methode, Vererbung und ihre 
 *   Realisierung in Java
 * 
 */
public class MyJfxApp extends Application {
	Group root = new Group();
	int Anzahl = 500;
	SimulationTimer simulationLoop;
	Ball[] b = new Ball[Anzahl];
	Block[] blocke = new Block[1];
	Random zufall = new Random();
	double rad = 10.0;
	final BorderPane borderPane = new BorderPane();
	Scene scene = new Scene(root, 600, 400);


	public void start(Stage stage) {


		for (int i = 0; i < b.length; i++) {
			b[i] = new Ball(zufall.nextInt(1000), zufall.nextInt(1000), rad, Color.RED);

			if (b[i].x >= scene.getWidth() || b[i].x <= 0)
			{
				b[i].x = scene.getHeight() / 2;
			}

			if (b[i].y >= scene.getHeight() || b[i].y <= 0)
			{
				b[i].y = scene.getHeight() / 2;
			}

			b[i].vx = zufall.nextDouble() * zufall.nextInt(10);
			b[i].vy = zufall.nextDouble() * zufall.nextInt(10);

			root.getChildren().add(b[i]);
		}
		for (int i = 0; i < blocke.length; i++)



		stage.setTitle("My JavaFX Application");
		stage.setScene(scene);
		stage.show();

		startSimulation();
	}

	private void startSimulation() {
		simulationLoop = new SimulationTimer(this, 1);
		simulationLoop.start();
	}


	/*
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}


	public void updateSimulation() {
		//updatePosition(myJfxApp);
		for (int i = 0; i < b.length; i++) {

			if ((scene.getWidth() - b[i].x <= b[i].radius && b[i].vx > 0) || (b[i].x <= b[i].radius && b[i].vx < 0 ))
			{
				b[i].vx = -b[i].vx;
			}

			if ((scene.getHeight() - b[i].y <= b[i].radius && b[i].vy > 0) || (b[i].y <= b[i].radius) && b[i].vy < 0)
			{
				b[i].vy = -b[i].vy;
			}

			for (int n = i + 1; n < b.length; n++)
			{

				if (b[n].collideWith(b[i]))
				{
					double d1 = b[n].vx;
					double d2 = b[n].vy;

					b[n].vx = b[i].vx;
					b[n].vy = b[i].vy;

					b[i].vx = d1;
					b[i].vy = d2;
					b[n].updatePosition();
					b[i].updatePosition();

				}

				//b[n].updatePosition();

			}
			b[i].updatePosition();


		}


	}

}