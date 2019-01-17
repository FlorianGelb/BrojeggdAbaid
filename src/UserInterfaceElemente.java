import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class UserInterfaceElemente {
	TextField textFeld;
	Button button = new Button();
	
	boolean clicked = false;
	
	NumberAxis xAchse = new NumberAxis();
	NumberAxis yAchse = new NumberAxis();
	final LineChart<Number, Number> Diagramm = new LineChart<Number, Number>(xAchse, yAchse);
	XYChart.Series<Number, Number> update = new XYChart.Series<Number, Number>();
	
	/**
	 * Übergibt ein Diagramm
	 * @return Diagramm
	 */
	public LineChart createLineChart() {
		Diagramm.setAnimated(true);
		return Diagramm;
	}
	
	/**
	 * Ermöglicht es die Werte des Diagramms zu aktuallisieren
	 * @param x neue X-Koordinate
	 * @param y neue Y-Koordinate
	 * @param Name Name des Graphens
	 */
	public void updateDiagramm(double x, double y,  String Name) {
		this.update.getData().add(new XYChart.Data<Number, Number>(x, y));
		this.update.setName(Name);
		
		if (this.Diagramm.getData().contains(update) == false) {
			this.Diagramm.getData().add(update);
			if (x != 0)
				this.update.getData().clear();
		}
	}
	
	/**
	 * Erstellt einen Knopf
	 * @param text Text des Knopfes
	 * @return Übergibt einen Knopf
	 */
	public Button createButton(String text) {
		button.setText(text);
		return button;
	}
	
	/**
	 * Fragt ab, ob der Knopf gedrückt wurde
	 * @return true, wenn der Knopf gedrückt wurde
	 */
	public boolean returnButtonValue() {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clicked = true;
			}
		});
		return clicked;
	}
	
	/**
	 * Erstellt ein Textfeld
	 * @param text Text, der standartmäßig angezeigt wird
	 * @return übergibt ein Textfeld
	 */
	public TextField createTextFeld(String text) {
		textFeld = new TextField(text);
		return textFeld;
	}
	
	/**
	 * Fragt den Text ab, der in dem Textfeld steht
	 * @return Übergibt den Text
	 */
	public String returnText() {
		return textFeld.getText();
	}
	
	
}

