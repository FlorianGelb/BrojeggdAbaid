import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.text.*;
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
	boolean k = false;
	boolean m = false;
	Group root = new Group();
	Block o1 = null;
	Ball b1 = null;
	Ball b2 = null;
	int Anzahl = 500;
	SimulationTimer simulationLoop;
	Ball[] b3 = new Ball[Anzahl];
	Block[] blocke = new Block[1];
	Random zufall = new Random();
	double rad = 10.0;
	Text[] T = new Text[Anzahl];
	Text anzahl = new Text();
	final BorderPane borderPane = new BorderPane();
	Scene scene = new Scene(borderPane, 600, 400);
	public void start(Stage stage) {


		for (int i = 0; i < b3.length; i++) {
			b3[i] = new Ball(zufall.nextInt(1000), zufall.nextInt(1000), rad, Color.RED);
			b3[i].vx = zufall.nextDouble() * zufall.nextInt(10);
			b3[i].vy = zufall.nextDouble() * zufall.nextInt(10);
			T[i] = new Text(b3[i].x, b3[i].y, Integer.toString(b3[i].ballnummer));
			T[i].setFont(new Font(16));



			root.getChildren().add(b3[i]);
			root.getChildren().add(T[i]);
		}
		anzahl = new Text(20,20, "Anzahl: " + Ball.ballAnzahl);
		anzahl.setFont(new Font(20));
		root.getChildren().add(anzahl);
		for (int i = 0; i < blocke.length; i++)
		{
			blocke[i] = new Block(300, 300, 120, 120, Color.GREEN);
			root.getChildren().add(blocke[i]);
		}





		borderPane.setTop(createToolbarPane());
		borderPane.setCenter(createBallPane());
		borderPane.setLeft(createNavigationPane());

		stage.setTitle("My JavaFX Application");

		stage.setScene(scene);
		stage.show();

		startSimulation();
	}

	private void startSimulation() {
		simulationLoop = new SimulationTimer(this, 10);
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
		for (int i = 0; i < b3.length; i++) {

			if (b3[i].x + b3[i].radius < 0 || b3[i].x + b3[i].radius > scene.getWidth()) {
				b3[i].vx = -b3[i].vx;
			}

			if (b3[i].y + b3[i].radius < 0 || b3[i].y + b3[i].radius > scene.getHeight()) {
				b3[i].vy = -b3[i].vy;
			}

			for (int n = i + 1; n < b3.length; n++) {

				if (b3[n].collideWith(b3[i])) {
					double d1 = b3[n].vx;
					double d2 = b3[n].vy;

					b3[n].vx = b3[i].vx;
					b3[n].vy = b3[i].vy;

					b3[i].vx = d1;
					b3[i].vy = d2;
					b3[n].updatePosition();
					b3[i].updatePosition();

				}

				//b3[n].updatePosition();

			}
			T[i].setX(b3[i].x);
			T[i].setY(b3[i].y);
			b3[i].updatePosition();



			for (int n = 0; n < blocke.length; n++) {
				blocke[n].collideWith(b3[i]);
			}
		}


	}
	private Pane createToolbarPane()
	{
		final HBox hbox = new HBox(5);
		hbox.setStyle("-fx-border-color: red; -fx-boarder-with: 3pt");
		hbox.getChildren().addAll(new Text("TOP"), new Button("Hbox1"), new Button("Hbox2"));

		return hbox;
	}

	private Pane createNavigationPane() {
		final VBox vbox = new VBox(5);
		vbox.setStyle("-fx-border-color: #0021ff; -fx-border-width: 3pt;");
		vbox.getChildren().addAll(new Text("LEFT"), new Button("Vbox1"), new Button("Vbox2"));

		return vbox;
	}
	private Pane createBallPane()
	{
		FlowPane bPane = new FlowPane();
		bPane.getChildren().add(root);
		return bPane;
	}
}