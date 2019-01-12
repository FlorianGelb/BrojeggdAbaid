import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
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
	static String[] Args = new String[4];
	final BorderPane borderPane = new BorderPane();
	Group root = new Group();
	SimulationTimer simulationLoop;
	Block[] blocke = new Block[1];
	double temperatur;
	boolean tick = false;
	int anzahl = 10;
	Random zufall = new Random();
	double rad = 5.0;
	double speedSample;
	int sec = 0;
	double Speed = 0;
	int druckCNT = 0;
	double druck;
    double paneWidth;
    double paneHight;

	UserInterfaceElemente Temperatur = new UserInterfaceElemente();
	UserInterfaceElemente Druck = new UserInterfaceElemente();

    UserInterfaceElemente ButtonChange = new UserInterfaceElemente();
    UserInterfaceElemente ButtonExit = new UserInterfaceElemente();

	UserInterfaceElemente Masse = new UserInterfaceElemente();
	UserInterfaceElemente Volumen = new UserInterfaceElemente();
	UserInterfaceElemente Geschwindigkeit = new UserInterfaceElemente();
	UserInterfaceElemente Anzahl = new UserInterfaceElemente();

	Scene scene = new Scene(borderPane, 600, 400);
	UserInterfaceTimer uiLoop;

	ArrayList<Ball> b = new ArrayList<Ball>();
	Block raum= new Block(0,0,0,0,Color.BLACK);

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

    public void addBall(int n)
    {
        if (n < 0)
        {
            for (int i = 0; i <= n * -1 ; i++)
            {
                b.remove(i);
            }
        }
        for (int i = 0; i<=n; i++)
        {
            b.add(new Ball(zufall.nextInt(100), zufall.nextInt(1000), rad, Color.RED));
        }
        for (Ball e: b)
        {

            e.vx = zufall.nextDouble() * zufall.nextDouble()*zufall.nextInt(10);
            e.vy = zufall.nextDouble() * zufall.nextDouble()*zufall.nextInt(10);
        }
    }

	public void start(Stage stage)
	{
	    addBall(anzahl);

        stage.getIcons().add(new Image("Main_Icon.png"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");
		borderPane.setTop(createTopPane());
		borderPane.setRight(createChartPane());
		borderPane.setCenter(createCenterPane());
		//borderPane.setBottom(createBottomPane());
		borderPane.setLeft(createSettingsPane());


		stage.show();
		paneHight = borderPane.getCenter().getBoundsInLocal().getHeight() - 10;
		paneWidth = borderPane.getCenter().getBoundsInLocal().getWidth() - 10;

		startSimulation();
	}

    private  void refreshCenterPane()
    {
        borderPane.setCenter(null);
        borderPane.setCenter(createCenterPane());
    }

	private void startSimulation()
	{
		simulationLoop = new SimulationTimer(this, 5);
		uiLoop = new UserInterfaceTimer(this, 1000);
		simulationLoop.start();
		uiLoop.start();

	}

	public void updateSimulation()
	{

	    collisionLogic();
	}
	public void updateUI()
	{
			if (ButtonExit.returnButtonValue())
            {
                Window stage = scene.getWindow();
                stage.hide();
            }
			if (ButtonChange.returnButtonValue())
            {
                addBall(Integer.parseInt(Anzahl.returnText())-anzahl);
                refreshCenterPane();
                anzahl = Integer.parseInt(Anzahl.returnText());
                ButtonChange.clicked = false;
            }
	        Temperatur.updateDiagramm(sec, calcTemp(), "Temperatur [K]");
			Druck.updateDiagramm(sec, getSpeed(), "Druck");
			sec += 1;
	}
	public double calcDruck() {
		double p = (2/3 * anzahl / (1 * Math.pow(0.686,3)*(6.6 * Math.pow(10,-2))*getSpeed()));
		return  p;
	}
	public double calcTemp()
	{
		temperatur = (getSpeed()*6.6 * Math.pow(10,-24) * (2.7613008 * Math.pow(10, 23.0)));
		return temperatur;
	}

	public void collisionLogic()
{
	for (Ball e: b)
	{

	    if(((e.x + e.vx)  <= e.radius && e.vx <0)|| ((e.x + e.vx) >= paneWidth) && e.vx >0)
        {e.vx = - e.vx;}
	    if ((((e.y + e.vy)  <= e.radius && e.vy <0)|| ((e.y + e.vx) >= paneHight) && e.vy >0))
        {
            e.vy = - e.vy;
        }
		for (Ball n: b)
		{
			if (n.collideWith(e))
			{
				double d1 = n.vx;
				double d2 = n.vy;

				n.vx = e.vx;
				n.vy = e.vy;

				n.vx = d1;
				n.vy = d2;
				n.updatePosition();
				n.updatePosition();
			}
		}
		e.updatePosition();
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
		CenterPane.getChildren().addAll(b);
		return CenterPane;
	}

	public Pane createBottomPane()
	{
		final FlowPane BottomPane = new FlowPane();
        BottomPane.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
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
				Anzahl.createTextFeld(Args[2]),
				new Text("Masse [Kg]"),
				Masse.createTextFeld("1"),
				new Text("Durchschnitt |v| [m/s]"),
				Geschwindigkeit.createTextFeld("3"),
                ButtonChange.createButton("Werte ändern"),
                ButtonExit.createButton("Programm schließen")
				);
		return vBox;
	}
	public Pane createChartPane()
	{
	    final VBox gridPane = new VBox();
		gridPane.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
		gridPane.getChildren().addAll(
				Temperatur.createLineChart(),
				Druck.createLineChart()
		);
		return gridPane;
	}

	public double getSpeed()
	{
		speedSample = Speed;
		for (Ball e: b)
		{
			Speed += (e.vx) * (e.vx ) + (e.vy) * (e.vy);

		}
		Speed = (Speed)/b.size();
		return  Speed;
	}
}