import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Florian Braun
 * @version 1.0
 */

public class MyJfxApp extends Application {
	static final double BOLZTMANN_KONSTANTE = 1.3806504 * Math.pow(10, -23);
	static final int ZEIT_SCHRITT = 5;
	final BorderPane borderPane = new BorderPane();
	
	int anzahl = 10;
	int sec = 0;
	int druckCNT = 0;
	int anzahlT;
	
	double temperatur;
	double rad = 10.0;
	double masse = 1;
	double masseT;
	double speed = 0;
	double speedSample;
	double volumen = 1;
	double volumenT;
	double druckT = 0;
	double velocity = 3;
	double velocityT;
	double paneWidth;
	double paneHight;
	
	UserInterfaceElemente Temperatur = new UserInterfaceElemente();
	UserInterfaceElemente DruckT = new UserInterfaceElemente();
	UserInterfaceElemente Kraft = new UserInterfaceElemente();
	UserInterfaceElemente Druck = new UserInterfaceElemente();
	
	UserInterfaceElemente Masse = new UserInterfaceElemente();
	UserInterfaceElemente Volumen = new UserInterfaceElemente();
	UserInterfaceElemente Geschwindigkeit = new UserInterfaceElemente();
	UserInterfaceElemente Anzahl = new UserInterfaceElemente();
	
	UserInterfaceElemente ButtonChange = new UserInterfaceElemente();
	UserInterfaceElemente ButtonExit = new UserInterfaceElemente();
	
	UserInterfaceTimer uiLoop;
	SimulationTimer simulationLoop;
	Random zufall = new Random();
	Scene scene = new Scene(borderPane, 600, 400);
	ArrayList<Ball> b = new ArrayList<Ball>();
	
	/**
	 * @param args Der Konstruktor für die Hauptklasse "MyJfxApp" nimmt als Paramenter die Komandozeilenparameter
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 *
	 * @param stage die Methode "start" nimmt als Parameter eine Stage entgegen
	 *              die Stage "stage" ist in dieser Anwendung der sog. top level JavaFX Kontainer.
	 */
	public void start(Stage stage) {
		addBall(anzahl);
		stage.getIcons().add(new Image("Main_Icon.png"));
		stage.setTitle("Gassimmulator von Florian Braun");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.setFullScreen(false);
		stage.setFullScreenExitHint("");
		
		borderPane.setTop(createTopPane());
		borderPane.setRight(createChartPane());
		borderPane.setCenter(createCenterPane());
		borderPane.setLeft(createSettingsPane());
		
		stage.show();
		paneHight = borderPane.getCenter().getBoundsInLocal().getHeight() - 10;
		paneWidth = borderPane.getCenter().getBoundsInLocal().getWidth() - 10;
		
		startSimulation();
	}
	
	/**
	 * Die Methode "startSimulation" startet sowohl den Timer, der die Simulation updatet (simulationLoop), als auch den
	 * Timer, der die GUI updatet(uiLoop)
	 */
	private void startSimulation() {
		simulationLoop = new SimulationTimer(this, ZEIT_SCHRITT);
		uiLoop = new UserInterfaceTimer(this, 1000);
		timerStart();
		
	}
	
	public void timerStart() {
		uiLoop.start();
		simulationLoop.start();
	}
	
	public void timerStop() {
		uiLoop.stop();
		simulationLoop.stop();
	}
	
	public void updateSimulation() {
		collisionLogic();
	}
	
	public void addBall(int n) {
		if (n < 0) {
			for (int i = 0; i <= n * -1; i++) {
				b.remove(i);
			}
		}
		for (int i = 0; i <= n; i++) {
			b.add(new Ball(zufall.nextInt(100), zufall.nextInt(1000), rad, Color.RED));
		}
		updateVelocity(velocity);
	}
	
	public void updateVelocity(double newV) {
		for (Ball e : b) {
			e.vx = newV / Math.sqrt(2);
			e.vy = newV / Math.sqrt(2);
		}
	}
	
	private void refreshCenterPane() {
		borderPane.setCenter(null);
		borderPane.setCenter(createCenterPane());
	}
	
	public void collisionLogic() {
		for (Ball e : b) {
			
			if (((e.x + e.vx) <= e.radius && e.vx < 0) || ((e.x + e.vx) >= paneWidth) && e.vx > 0) {
				e.vx = -e.vx;
				druckCNT += 1;
			}
			if ((((e.y + e.vy) <= e.radius && e.vy < 0) || ((e.y + e.vx) >= paneHight) && e.vy > 0)) {
				e.vy = -e.vy;
				druckCNT += 1;
			}
			for (Ball n : b) {
				if (n.collideWith(e)) {
					double d1 = n.vx;
					double d2 = n.vy;
					
					n.vx = e.vx;
					n.vy = e.vy;
					
					e.vx = d1;
					e.vy = d2;
					
					e.updatePosition();
					n.updatePosition();
				}
			}
			e.updatePosition();
		}
	}
	
	public void updateUI() {
		anzahlT = Integer.parseInt(Anzahl.returnText());
		masseT = Double.parseDouble(Masse.returnText());
		velocityT = Double.parseDouble(Geschwindigkeit.returnText());
		volumenT = Double.parseDouble(Volumen.returnText());
		
		if (ButtonExit.returnButtonValue()) {
			Window stage = scene.getWindow();
			stage.hide();
		}
		if (ButtonChange.returnButtonValue()) {
			if (anzahl != anzahlT) {
				timerStop();
				addBall(anzahlT - anzahl);
				anzahl = anzahlT;
				refreshCenterPane();
				timerStart();
				ButtonChange.clicked = false;
			}
			if (masse != masseT) {
				timerStop();
				masse = masseT;
				timerStart();
				ButtonChange.clicked = false;
			}
			if (velocity != velocityT) {
				timerStop();
				updateVelocity(velocityT);
				timerStart();
			}
			if (volumenT != volumen) {
				timerStop();
				volumen = volumenT;
				timerStart();
			}
			ButtonChange.clicked = false;
		}
		
		Temperatur.updateDiagramm(sec, calcTemp(), "Temperatur [K]");
		DruckT.updateDiagramm(sec, druckCNT * calcDruck(), "Druck(t) [N/m^2]");
		Kraft.updateDiagramm(sec, Math.sqrt(calcGeschwindigkeit()), "Kraft/Teilchen [N]");
		sec += 1;
		Druck.updateDiagramm(sec, calcDruckDurchschnitt(), "Druck Durchschnitt[*10^-15 N/m^2]");
		
	}
	
	public double calcDruckDurchschnitt() {
		druckT += calcDruck();
		double p = (druckT / sec) * Math.pow(10, 15);
		return p;
	}
	
	public double calcDruck() {
		double p = calcKraft() / 2 * (paneHight * paneHight + (volumen / paneHight * paneWidth) * (paneHight + paneWidth));
		druckCNT = 0;
		return p;
	}
	
	public double calcKraft() {
		double f = (Math.sqrt(calcGeschwindigkeit()) * (masse * Math.pow(10, -24)) / ZEIT_SCHRITT * Math.pow(10, -3));
		return f;
	}
	
	public double calcTemp() {
		temperatur = (calcGeschwindigkeit() * masse * Math.pow(10, -24) * 1 / (2 * BOLZTMANN_KONSTANTE));
		return temperatur;
	}
	
	public double calcGeschwindigkeit() {
		speedSample = speed;
		for (Ball e : b) {
			speed += (e.vx) * (e.vx) + (e.vy) * (e.vy);
			
		}
		speed = (speed) / b.size();
		return speed;
	}
	
	
	public Pane createTopPane() {
		final FlowPane TopPane = new FlowPane();
		
		return TopPane;
	}
	
	public Pane createCenterPane() {
		final Pane CenterPane = new Pane();
		CenterPane.getChildren().addAll(b);
		return CenterPane;
	}
	
	public Pane createSettingsPane() {
		final VBox vBox = new VBox(5);
		vBox.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
		vBox.getChildren().addAll(
				new Text("Volumen [m^3]"),
				Volumen.createTextFeld(String.valueOf(volumen)),
				new Text("Anzahl der Teilchen"),
				Anzahl.createTextFeld(String.valueOf(anzahl)),
				new Text("Masse [*10^-24Kg]"),
				Masse.createTextFeld(String.valueOf(masse)),
				new Text("Durchschnitt |v| [m/s]"),
				Geschwindigkeit.createTextFeld(String.valueOf(velocity)),
				ButtonChange.createButton("Werte ändern"),
				ButtonExit.createButton("Programm schließen")
		);
		return vBox;
	}
	
	public Pane createChartPane() {
		final HBox chartPane = new HBox();
		final VBox vbox = new VBox();
		final VBox vbox2 = new VBox();
		
		vbox.setStyle("-fx-border-color: black; -fx-boarder-with: 1pt");
		vbox.getChildren().addAll(
				Temperatur.createLineChart(),
				DruckT.createLineChart()
		);
		
		vbox2.getChildren().addAll(
				Kraft.createLineChart(),
				Druck.createLineChart()
		);
		
		chartPane.getChildren().add(vbox);
		chartPane.getChildren().add(vbox2);
		return chartPane;
	}
}