import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.text.NumberFormat;

import javafx.scene.control.Button;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class UserInterfaceElemente {
	TextField textFeld;
	Button button = new Button();
	
	
	boolean clicked = false;
	
	NumberAxis xAchse = new NumberAxis();
	NumberAxis yAchse = new NumberAxis();
	final LineChart<Number, Number> Diagramm = new LineChart<Number, Number>(xAchse, yAchse);
	XYChart.Series<Number, Number> update = new XYChart.Series<Number, Number>();
	
	public UserInterfaceElemente() {
	
	}
	
	public LineChart createLineChart() {
		Diagramm.setAnimated(true);
		return Diagramm;
	}
	
	public void updateDiagramm(double x, double y, String Name) {
		this.update.getData().add(new XYChart.Data<Number, Number>(x, y));
		this.update.setName(Name);
		
		if (this.Diagramm.getData().contains(update) == false) {
			this.Diagramm.getData().add(update);
			if (x != 0)
				this.update.getData().clear();
		}
	}
	
	public Button createButton(String text) {
		button.setText(text);
		return button;
	}
	
	public boolean returnButtonValue() {
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clicked = true;
			}
		});
		return clicked;
	}
	
	public TextField createTextFeld(String text) {
		textFeld = new TextField(text);
		return textFeld;
	}
	
	
	public String returnText() {
		return textFeld.getText();
	}
	
	
}

