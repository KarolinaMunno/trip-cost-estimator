//Description: this class creates text fields for user input, uses combo boxes 
//to provide user with a choice, creates user interface, adds components to 
//the grid, creates and places a scene, obtains and parses user input,
//calculate the total trip cost and checks for invalid input.

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TripCostEstimator extends Application {
    
    //Create text fields for user input.
    //Create combo boxes to provide user with a choice.
    private TextField tfDistance = new TextField();
    private ComboBox<String> cbMiles = new ComboBox<>();
    private TextField tfGasolineCost = new TextField();
    private ComboBox<String> cbDollarsGal = new ComboBox<>();
    private TextField tfGasMileage = new TextField();
    private ComboBox<String> cbMilesGallon = new ComboBox<>();
    private TextField tfNumberOfDays = new TextField();
    private TextField tfHotelCost = new TextField();
    private TextField tfFoodCost = new TextField();
    private TextField tfAttractions = new TextField();
    private TextField tfTotalTripCost = new TextField ("$0.00");
    private Button btCalculate = new Button("Calculate");

    @Override
    public void start(Stage primaryStage) {
        //Create user interface.
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
        //Add components to the grid.
        gridPane.add(new Label("Distance:"), 0, 0);
        gridPane.add(tfDistance, 1, 0);
        cbMiles.getItems().addAll("miles", "kilometers");
        cbMiles.setValue("miles");
        gridPane.add(cbMiles, 2, 0);

        gridPane.add(new Label("Gasoline Cost:"), 0, 1);
        gridPane.add(tfGasolineCost, 1, 1);
        cbDollarsGal.getItems().addAll("dollars/gal", "dollars/liter");
        cbDollarsGal.setValue("dollars/gal");
        gridPane.add(cbDollarsGal, 2, 1);

        gridPane.add(new Label("Gas Mileage:"), 0, 2);
        gridPane.add(tfGasMileage, 1, 2);
        cbMilesGallon.getItems().addAll("miles/gallon", "kilometers/liter");
        cbMilesGallon.setValue("miles/gallon");
        gridPane.add(cbMilesGallon, 2, 2);

        gridPane.add(new Label("Number Of Days:"), 0, 3);
        gridPane.add(tfNumberOfDays, 1, 3);
        gridPane.add(new Label("Hotel Cost:"), 0, 4);
        gridPane.add(tfHotelCost, 1, 4);
        gridPane.add(new Label("Food Cost:"), 0, 5);
        gridPane.add(tfFoodCost, 1, 5);
        gridPane.add(new Label("Attractions:"), 0, 6);
        gridPane.add(tfAttractions, 1, 6);
       
        gridPane.add(btCalculate, 1, 7);
        GridPane.setHalignment(btCalculate, HPos.CENTER);
        gridPane.add(new Label("Total Trip Cost:"), 0, 9);
        tfTotalTripCost.setEditable(false);
        gridPane.add(tfTotalTripCost, 1, 9);


        //Set alignment.
        gridPane.setAlignment(Pos.CENTER);
        tfDistance.setAlignment(Pos.BOTTOM_RIGHT);
        tfGasolineCost.setAlignment(Pos.BOTTOM_RIGHT);
        tfGasMileage.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumberOfDays.setAlignment(Pos.BOTTOM_RIGHT);
        tfHotelCost.setAlignment(Pos.BOTTOM_RIGHT);
        tfFoodCost.setAlignment(Pos.BOTTOM_RIGHT);
        tfAttractions.setAlignment(Pos.BOTTOM_RIGHT);
        tfTotalTripCost.setAlignment(Pos.BOTTOM_RIGHT);
        
        //Align the calculate button with text fields.
        btCalculate.setStyle("-fx-min-width: 150px;");
        btCalculate.setOnAction(e -> calculateTripCost());

        //Create and place a scene.
        Scene scene = new Scene(gridPane, 425, 350);
        primaryStage.setTitle("Trip Cost Estimator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateTripCost() {
        try {
            //Obtain and parse user input.
            double distance = Double.parseDouble(tfDistance.getText());
            double gasolineCost = Double.parseDouble(tfGasolineCost.getText());
            double gasMileage = Double.parseDouble(tfGasMileage.getText());
            int numberOfDays = Integer.parseInt(tfNumberOfDays.getText());
            double hotelCost = Double.parseDouble(tfHotelCost.getText());
            double foodCost = Double.parseDouble(tfFoodCost.getText());
            double attractions = Double.parseDouble(tfAttractions.getText());

            //Convert value to chosen user option as needed.
            if (cbMiles.getValue().equals("kilometers")) {
                distance *= 0.621371;
            }

            if (cbDollarsGal.getValue().equals("dollars/liter")) {
                gasolineCost *= 0.264172;
            }

            if (cbMilesGallon.getValue().equals("kilometers/liter")) {
                gasMileage *= 2.3521458;
            }

            //Calculate the total trip cost.
            double totalTripCost = (distance / gasMileage) * gasolineCost
                    + (hotelCost + foodCost) * numberOfDays + attractions;

           
            tfTotalTripCost.setText(String.format("$%.2f", totalTripCost));

          //Check for invalid input.
        } catch (NumberFormatException e) {
            tfTotalTripCost.setText("Error: Invalid input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

