﻿import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
public class MyJfxApp extends Application
{
	Group root = new Group();
	int Anzahl = 10000;
	SimulationTimer simulationLoop;
	Ball[] b = new Ball[Anzahl];
	Block[] blocke = new Block[1];
	Random zufall = new Random();
	double rad = 5.0;

	boolean tick = false;
	int anzahl = 500;
	Random zufall = new Random();
	static String[] Args = new String[4];
	double rad = 10.0;
	double speedSample;
	int sec = 0;
	double Speed = 0;

	final BorderPane borderPane = new BorderPane();

	UserInterfaceElemente Volumen = new UserInterfaceElemente();
	UserInterfaceElemente Temperatur = new UserInterfaceElemente();
	UserInterfaceElemente Anzahl = new UserInterfaceElemente();
	UserInterfaceElemente Diagramm = new UserInterfaceElemente();
	UserInterfaceElemente Diagramm2 = new UserInterfaceElemente();

	Scene scene = new Scene(borderPane, 600, 400);
	SimulationTimer simulationLoop;
	UserInterfaceTimer uiLoop;

	Ball[] b = new Ball[anzahl];
	Block raum= new Block(0,0,scene.getWidth(),scene.getHeight(),Color.TRANSPARENT);

	public void start(Stage stage)
	{
		for (int i = 0; i < b.length; i++)
		{
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

			speedSample = b[i].vx * b[i].vx + b[i].vy * b[i].vy;

			//root.getChildren().add(b[i]);
		}

		borderPane.setTop(createTopPane());
		borderPane.setRight(createChartPane());
		borderPane.setCenter(createCenterPane());
		borderPane.setBottom(createBottomPane());
		borderPane.setLeft(createSettingsPane());
		stage.setMaximized(true);
		stage.getIcons().add(new Image("Main_Icon.png"));
		stage.setScene(scene);
		stage.show();
		raum.resize(scene.getWidth()-670, scene.getHeight()-16);
		startSimulation();
	}

	private void startSimulation()
	{
		simulationLoop = new SimulationTimer(this, 1);
		uiLoop = new UserInterfaceTimer(this, 250);
		simulationLoop.start();
		uiLoop.start();

	}

	public static void main(String[] args)
	{
		try {
			for (int i = 0; i < 4; i++) {
				String arg = args[0];
				Args = arg.split(";");
				launch(args);
			}
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e)
		{
			Args[0] = "1";
			Args[1] = "294";
			Args[2] = "1000";
			launch(args);
		}
	}

	public void updateSimulation()
	{

		//updateUI();
		collisionLogic();
	}
	public void updateUI()
	{
			if (sec%2 == 0) {
				Diagramm.updateDiagramm(sec, getSpeed(), "Speed");
			}
			sec += 1;


	}


	public void collisionLogic()
{
	for (int i = 0; i < b.length; i++)
	{
		if ((raum.returnWidth() - b[i].x <= b[i].radius && b[i].vx > 0) || (b[i].x <= b[i].radius && b[i].vx < 0 ))
		{
			b[i].vx = -b[i].vx;
		}

		if ((raum.returnHeight() - b[i].y <= b[i].radius && b[i].vy > 0) || (b[i].y <= b[i].radius) && b[i].vy < 0)
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
		}
		b[i].updatePosition();
		}
	}


	public Pane createTopPane()
	{
		final FlowPane TopPane = new FlowPane();

		return TopPane;
	}

	public Pane createCenterPane()
	{
		final Pane CenterPane = new Pane();
		CenterPane.getChildren().add(raum);
		CenterPane.getChildren().addAll(b);
		return CenterPane;
	}

	public Pane createBottomPane()
	{
		final FlowPane BottomPane = new FlowPane();
		BottomPane.getChildren().add(new Text("Gassimmulator von Florian Braun"));
		return BottomPane;
	}

	public Pane createSettingsPane()
	{
		final VBox vBox = new VBox(5);
		vBox.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
		vBox.getChildren().addAll(
				new Text("Volumen [m^3]"),
				Volumen.createTextFeld(Args[0]),
				new Text("Temperatur [K]"),
				Temperatur.createTextFeld(Args[1]),
				new Text("Anzahl der Teilchen"),
				Anzahl.createTextFeld(Args[2])
				//Diagramm2.createLineChart(),
				//Diagramm.createLineChart()
				);
		return vBox;
	}

	public Pane createChartPane()
	{
		final GridPane gridPane = new GridPane();
		gridPane.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
		gridPane.getChildren().addAll(
				Diagramm.createLineChart()
				//  Diagramm.createSlider(0,100, 0,true, true, 5,1,1),
				//new Text("Zoom Diagramm 1"),
				//Diagramm2.createLineChart()
				//Diagramm2.createSlider(0,100, 0,true, true, 5,1,1),
				//new Text ("Zoom Diagramm 2")
		);
		gridPane.setVgrow(Diagramm.createLineChart(), Priority.NEVER);
		return gridPane;
	}

	public double getSpeed()
	{
		speedSample = Speed;
		for (int i = 0; i<b.length; i++)
		{
			Speed += b[i].vx * b[i].vx + b[i].vy * b[i].vy;

		}
		Speed = Math.sqrt(Speed) / b.length;


		return  Speed;
	}
}