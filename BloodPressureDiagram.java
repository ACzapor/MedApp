package application;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BloodPressureDiagram {

Stage stage;
	
	public BloodPressureDiagram(Patient patient) {
		
		List<BloodPressure> array = patient.getBloodPressureTests();
		
		stage = new Stage();
		
		stage.setTitle("Blood Pressure tests results");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        xAxis.setLabel("Date");
        yAxis.setLabel("Result");
        
		final LineChart lineChart = new LineChart(xAxis, yAxis);
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Systol");
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Diastol");
        
        for (int i = 0; i < array.size(); i++) {
        	series1.getData().add(new XYChart.Data(array.get(i).getDate(), array.get(i).getSystolic()));
        	series2.getData().add(new XYChart.Data(array.get(i).getDate(), array.get(i).getDiastolic()));
        }
        
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().addAll(series1, series2);
       
        stage.setScene(scene);
        stage.show();
        
	}
}