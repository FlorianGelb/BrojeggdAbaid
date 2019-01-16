import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.awt.*;
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
	int anzahlT;
	
	double temperatur;
	double rad = 10.0;
	double masse = 1;
	double masseT;
	double volumen = 1;
	double volumenT;
	double druckT = 0;
	double druckCNT = 0;
	double velocity = 3;
	double velocityT;
	double paneWidth;
	double paneHight;

	
	boolean ballIsUpdated = false;
	
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
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Scene scene = new Scene(borderPane, 600, 400);
	ArrayList<Ball> b = new ArrayList<Ball>();
	
	/**
	 * @param args Die Methode "main()" nimmt als Paramenter die Komandozeilenparameter und startet das Programm.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * @param stage die Methode "start()" nimmt als Parameter eine Stage entgegen
	 *              die Stage "stage" ist in dieser Anwendung der sog. top level JavaFX Kontainer.
	 */
	public void start(Stage stage) {
		addBall(anzahl);
		stage.getIcons().add(new Image("Main_Icon.png"));
		stage.setTitle("Gassimmulator von Florian Braun");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		
		System.out.println(screenSize.getWidth());
		
		borderPane.setRight(createChartPane());
		borderPane.setCenter(createCenterPane());
		borderPane.setLeft(createSettingsPane());

		
		stage.show();
		
		paneHight = borderPane.getCenter().getBoundsInLocal().getHeight() - 10;
		paneWidth = borderPane.getCenter().getBoundsInLocal().getWidth() - 30;
		
		
		System.out.print(paneWidth);
		startSimulation();
	}
	
	/**
	 * Die Methode "startSimulation()" startet sowohl den Timer, der die Simulation updatet (simulationLoop), als auch den
	 * Timer, der die GUI updatet(uiLoop).
	 */
	private void startSimulation() {
		simulationLoop = new SimulationTimer(this, ZEIT_SCHRITT);
		uiLoop = new UserInterfaceTimer(this, 1000);
		timerStart();
		
	}
	
	/**
	 * Die Methode "timerStart()" startet den Timer f?r die GUI und den Timer f?r die Simulation.
	 */
	public void timerStart() {
		uiLoop.start();
		simulationLoop.start();
	}
	
	/**
	 * Die Methode "timerStop()" stopt den Timer f?r die GUI und den Timer f?r die Simulation .
	 */
	public void timerStop() {
		uiLoop.stop();
		simulationLoop.stop();
	}
	
	/**
	 * die Methode "updateSimulation()" wird alle 5ms von dem Timer "simulationLoop" aufgerufen und startet die Kollisionsabfrage
	 */
	public void updateSimulation() {
		
		collisionLogic();
	}
	
	/**
	 * Die Methode "addBall" f?gt n B?lle hinzu oder l?scht n B?lle
	 *
	 * @param n der Parameter n, des Typs Integer, legt fest, wieviele B?lle hinzugef?gt (falls n gr??er 0) oder  gel?scht (falls n kleiner 0) werden.
	 * ballIsUpdated Generiert eine Rückmeldung ob die Operation erfolgreich ausgeführt wurde, um Bugs zu vermeiden.
	 */
	public void addBall(int n) {
		if (n < 0) {
			for (int i = 0; i < n * -1; i++) {
				b.remove(0);
			}
			ballIsUpdated = true;
		}
		if( n > 0)
		{
			for (int i = 0; i < n; i++) {
				b.add(new Ball(zufall.nextDouble() * paneWidth , zufall.nextDouble() * paneHight, rad, Color.RED));
			}
			ballIsUpdated = true;
			updateVelocity(velocity);
		}
		
	}
	
	/**
	 * Die Methode "updateVelocity" ?ndert die Geschwindigkeitsvektoren so, dass der Betrag der Vektoren newV entspricht.
	 *
	 * @param newV Betrag des neuen Geschwindigkeitsvektor.
	 */
	public void updateVelocity(double newV) {
		for (Ball e : b) {
			e.vx = newV / Math.sqrt(2);
			e.vy = newV / Math.sqrt(2);
		}
	}
	
	/**
	 * Die Methode "refreshCenterPane()" aktuallisiert die centerPane, nachdem die Anzahl der B?lle ge?ndert wurde.
	 */
	private void refreshCenterPane() {
		borderPane.setCenter(null);
		borderPane.setCenter(createCenterPane());
	}
	
	/**
	 * Die Methode "collisionLogic()" ?berpr?ft, ob die B?lle mit anderen B?llen oder mit dem Rand kollidieren.
	 * Falls ein Ball mit dem Rand kollidiert, werden die Geschwindigkeitsvektoren negativiert.
	 * Falls ein Ball mit einen anderen Ball kollidiert, werden die Geschwindigkeitsvektoren beider B?lle ausgetauscht.
	 */
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
	
	/**
	 * Die Methode "updateUI()" wird einmal pro Sekunde von dem Timer uiLoop aufgerufen, um die Steuerelemente und Diagramme
	 * zu Aktuallisieren.
	 */
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
				ballIsUpdated = false;
				if (ballIsUpdated == false)
				{
					timerStop();
					addBall(anzahlT - anzahl);
					ballIsUpdated = true;
					anzahl = anzahlT;
					refreshCenterPane();
					timerStart();
					ButtonChange.clicked = false;
				}
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
		
		Temperatur.updateDiagramm(sec, calcTemp(0), "absolute Temperatur [K]");
		DruckT.updateDiagramm(sec,  calcDruck(), "Druck(t) [N/m^2]");
		Kraft.updateDiagramm(sec, calcKraft(), " *10^25 Kraft/Teilchen [N]");
		Druck.updateDiagramm(sec, calcDruckDurchschnitt(), "Druck Durchschnitt[*10^-15 N/m^2]");
		sec += 1;
		
	}
	
	/**
	 * Die Methode "calcDruckDurchschnitt()" errechnet den Durchschnitte aller gemessenen Werte f?r den Druck ?ber Zeit
	 *
	 * @return gibt den momentanten Durchschnitt des Drucks wieder. p = druck(t) / sec * 10^15
	 */
	public double calcDruckDurchschnitt() {
		druckT = druckT + calcDruck();
		double p = (druckT / (sec + 1)) * Math.pow(10, 15);
		return p;
	}
	
	/**
	 * Die Methode "calcDruck()" errechnet den Druck zum Zeitpunkt t.
	 *
	 * @return gibt den Druck zu Zeitpunkt t wieder. p(t) = Anzahl der Kollisionen im Zeitschritt * F / 2(H?he * Breite + (Volumen / H?he * Breite) * (H?he + Breite))
	 */
	public double calcDruck() {
		double p =  druckCNT * (calcKraft() * Math.pow(10,-25)) / (2*(volumen *(paneHight + paneWidth) / (paneHight * paneWidth) * (paneWidth + paneHight)));
		druckCNT = druckCNT / ZEIT_SCHRITT * Math.pow(10, -3);
		return p;
	}
	
	/**
	 * Die Methode "calcKraft" errechnet die Kraft, die ein einzelnes Teilchen auswirken kann.
	 *
	 * @return gibt den Betrag der Kraft wieder, die ein einzelnes Teilchen auswirken kann: F = |v| * (Masse / 5ms)
	 */
	public double calcKraft() {
		double f = (Math.sqrt(calcGeschwindigkeit(0)) * (masse * Math.pow(10, -24)) / ZEIT_SCHRITT * Math.pow(10, -3));
		return f * Math.pow(10, 25);
	}
	
	/**
	 * Die Methode "calcTemp()" errechnet die momentane Temperatur des Gases
	 *
	 * @return gibt die Temperatur wieder. T = |v|^2 * (Masse / 3Kb)
	 */
	public double calcTemp(int modus) {
		temperatur = (calcGeschwindigkeit(modus) * (masse * Math.pow(10, -24)) * 1 / (3 * BOLZTMANN_KONSTANTE));
		return temperatur;
	}
	
	/**
	 * Die Methode "calcGeschwindigkeit()" errechnet den durchschnittlichen betrag der Geschwindigkeitsvektoren vx und vy
	 *
	 * @return gibt |v|^2 wieder: |v|^2 = (vx[n]^2 + vy[n]^2) / Anzahl der Partikel; n hat kann die Werte [0;Anzahl der Partikel[ annehmen.
	 * @param modus Wenn modus = 0, Durchschnitsgeschwindigkeit wird zurückgegeben, wenn modus != 0, wird die Summe aller Beträge zurückgegeben
	 */
	public double calcGeschwindigkeit(int modus) {
		double speed = 0;
		for (Ball e : b) {
			speed += (e.vx) * (e.vx) + (e.vy) * (e.vy);
			
		}
		if (modus == 0)
		{
			speed = (speed) / b.size();
		}
		
		return speed;
	}
	
	/**
	 * Erstellt die centerPane, die als Gefäß für das Gas dient.
	 *
	 * @return gibt die centerPane wieder.
	 */
	public Pane createCenterPane() {
		final Pane CenterPane = new Pane();
		CenterPane.getChildren().addAll(b);
		return CenterPane;
	}
	
	/**
	 * Die Methode "createSettingsPane()" erstellt die Pane des Typs VBox, die and der linken Seite des Bildschirms den Eingabebereich einth?lt.
	 *
	 * @return gibt eine Pane wieder, die Textfelder f?r das Volumen, die Anzahl der Teilchen, die Masse und die
	 * Durchschnittliche Geschwindigkeit beinhaltet. Ebenfalls sind ein Knopf um die neuen Eingabewerte zu prozessieren und um das Fenster zu schlie?en enthalten.
	 */
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
	
	/**
	 * Die Methode "createChartPane()" erstellt eine HBox, die zwei Panes des Typs VBox beinhaltet. Diese Beinhalten die Diagramme.
	 *
	 * @return gibt die HBox "chartPane wieder.
	 */
	public Pane createChartPane() {
		final HBox chartPane = new HBox();
		final VBox vbox = new VBox();
		final VBox vbox2 = new VBox();

			chartPane.getChildren().removeAll();
			chartPane.setMaxWidth(screenSize.getWidth() / 2.57);
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