package project3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Project3 extends Application {
	
	TextField D= new TextField();
	TextField GC= new TextField();
	TextField GM= new TextField();
	TextField Num= new TextField();
	TextField HC= new TextField();
	TextField FC= new TextField();
	TextField A= new TextField();
	TextField TOTAL= new TextField();
	
	private String Distance;
	private String Price;
	private String Mil;
	
	ComboBox<String> distance= new ComboBox<>();
	ComboBox<String> price= new ComboBox<>();
	ComboBox<String> mil= new ComboBox<>();
	

	Stage TripCalCulator;
	GridPane pane= new GridPane();
	Pane pane1= new Pane();
	
	Font bold =(Font.font ("Arial", FontWeight.BOLD, 12));
	Scene scene= new Scene(pane, 500, 350 );

	
	
	@Override
	public void start(Stage primarystage) {
		
		TripCalCulator= primarystage;
		Button calc= new Button("Calculate");
		
		D.setAlignment(Pos.CENTER);
		GC.setAlignment(Pos.CENTER);
		GM.setAlignment(Pos.CENTER);
		Num.setAlignment(Pos.CENTER);
		HC.setAlignment(Pos.CENTER);
		FC.setAlignment(Pos.CENTER);
		A.setAlignment(Pos.CENTER);
		TOTAL.setAlignment(Pos.CENTER);
		
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setPadding(new Insets(10,10,10,10));
		pane.setHgap(5);
		pane.setVgap(5);
		
		distance.getItems().addAll("Kilometers", "Miles", "No Vehicle");
		distance.setValue("Miles");
		distance.setPrefWidth(100);
		
		price.getItems().addAll("$/Liter", "$/Gallon" );
		price.setValue("$/Gallons");
		price.setPrefWidth(100);
		
		mil.getItems().addAll("KM/liter", "ML/gallon" );
		mil.setValue("ML/gallon");
		mil.setPrefWidth(100);
		
		pane.add(new Label ("Distance: "), 0, 0); 
		pane.add(D, 1, 0 );
		pane.add(distance, 2, 0);
		
		pane.add(new Label("Gasoline Cost: "),0, 1);
		pane.add(GC, 1, 1);
		pane.add(price, 2, 1);
		
		
		pane.add(new Label("Gas Mileage: "), 0, 2);
		pane.add(GM, 1, 2);
		pane.add(mil, 2, 2);
		
		pane.add(new Label("Number of Days: "), 0, 3);
		pane.add(Num, 1, 3);
		
		pane.add(new Label ("Hotel Cost: "), 0, 4);
		pane.add(HC, 1, 4);
		
		pane.add(new Label ("Food Cost: "), 0, 5);
		pane.add(FC,1,5);
		
		pane.add(new Label ("Attractions: "), 0, 6);
		pane.add(A,1,6);
		
		calc.setPrefWidth(150);
		pane.add(calc, 1, 7);
		
		pane.add(new Label ("Total Trip Cost: "), 0, 8);
		pane.add(TOTAL,1,8);
		TOTAL.setEditable(false);
		
		
		
		distance.setOnAction(e -> {
			String Option= distance.valueProperty().get();
			if(Option == "No Vehicle") 
			{
				
				GC.setStyle("-fx-text-fill: blue");
				GC.setFont(bold);
				GC.setText("0000");
				GC.setEditable(false);
				
				D.setStyle("-fx-text-fill: blue");
				D.setFont(bold);
				D.setText("0000");
				D.setEditable(false);
				
				GM.setStyle("-fx-text-fill: blue");
				GM.setFont(bold);
				GM.setText("0000");
				GM.setEditable(false);	
					
					
				price.setVisible(false);
				mil.setVisible(false);
			} else {
				
				GC.setVisible(true);
				GC.setFont(Font.font(12));
				GC.setStyle("-f-text-fill: black");
				GC.setEditable(true);
				
				D.setVisible(true);
				D.setFont(Font.font(12));
				D.setStyle("-f-text-fill: black");
				D.setEditable(true);
				
				GM.setVisible(true);
				GM.setFont(Font.font(12));
				GM.setStyle("-fx-text-fill: black");
				GM.setEditable(true);
				
				price.setVisible(true);
				mil.setVisible(true);
			}
		});

		calc.setOnAction( e -> {
			total();});
		
		
		pane.setAlignment(Pos.CENTER);
		
		TripCalCulator.setTitle("TRIP CALCULATOR");
		TripCalCulator.setScene(scene);
		TripCalCulator.show();
		
		}

	private void total() throws RuntimeException {
		 
		Distance= distance.valueProperty().get();
		Price= price.valueProperty().get();
		Mil= mil.valueProperty().get();
		
		
		try {	
		TripCost getTotal= new TripCost(D, GC, GM, Num, HC, FC, A);
		
		String Result;
		
		if( Distance == "Kilometers") {
			
		getTotal.KM();
		
		} else if(Distance == "No Vehicle") {
			getTotal.NV();
		}
		if(Price == "$/Liter") {
			
		getTotal.DL();
			
		}
		if(Mil == "KM/liter") {
			getTotal.MG();
		}
		
		Result= getTotal.total();

	
		TOTAL.setText(Result);
		}catch(Exception e) {
		
		//Button settings to go back to the first Scene.
		Button exit= new Button("Go Back");
		VBox box= new VBox();
		box.setPadding(new Insets(10,10,15,10));
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(exit);
		
		exit.setOnAction(a -> TripCalCulator.setScene(scene));
		
		GridPane center= new GridPane();
		
		center.setPadding(new Insets(10,10,10,10));
		BorderPane border= new BorderPane();
		
		border.setCenter(center);
		border.setBottom(box);
		
		Label read=new Label("You can ONLY insert numbers");
		read.setFont(bold);
		read.setStyle("-fx-text-fill: red");
		
		
		Label read2= new Label("  Add a 0 to the empty places.");
		read2.setFont(bold);
		read2.setStyle("-fx-text-fill: red");
		
		
		
		center.add(read, 0, 0);
		center.add(read2, 0, 1);
		center.setAlignment(Pos.CENTER);
		//create new page-screen
		Scene scene2= new Scene(border, 250, 150);
		//Set the tittle and scene to our Stage/Window... and show.
		TripCalCulator.setTitle("IMPORTANT");
		TripCalCulator.setScene(scene2);
		TripCalCulator.show();
		
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
