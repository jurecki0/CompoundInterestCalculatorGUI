/*
 */
package sacewicz.gui;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author sacew
 */
public class MainBox extends VBox {

    private Button calculateBTN;
    private Button clearDataBTN;
    // create a alert
    String errContentTxt = "The value cannot be less than 0.";
    Alert error = new Alert(AlertType.ERROR, errContentTxt);

    public MainBox() {
        int size = this.getChildren().size();

        //MenuBar and MenuItems
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        this.getChildren().add(menuBar);
        MenuItem exit = new MenuItem("Exit");
        menuFile.getItems().add(exit);

        DataBox dataBox = new DataBox();

        this.getChildren().add(dataBox);
        
        HBox buttonsHBOX = new HBox();
        getChildren().add(buttonsHBOX);
        buttonsHBOX.setAlignment(Pos.CENTER_RIGHT);
        buttonsHBOX.setSpacing(10);
        buttonsHBOX.prefWidthProperty().bind(widthProperty().divide(size));

        clearDataBTN = new Button("Clear");
        buttonsHBOX.getChildren().add(clearDataBTN);
        clearDataBTN.setMinWidth(75);

        calculateBTN = new Button("Calculate");
        buttonsHBOX.getChildren().add(calculateBTN);
        calculateBTN.setMinWidth(75);

        //EVENT HANDLING
        calculateBTN.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                int compound = dataBox.getCompoundIntervalChoice();
                double interestRate = dataBox.getInterestRateField();
                double initialBal = dataBox.getInitialBalanceField();
                int years = 0;
                int months = 0;
                char selected = dataBox.getSelectedField();
                double amountReinv = dataBox.getAmountReinvested();
                int intervalChoice = dataBox.getIntervalsChoice();

                try {
                    years = dataBox.getYearsField();
                } catch (Exception NumberFormatException) {
                    error.setHeaderText("Years value erroneous");
                    error.show();
                }

                try {
                    months = dataBox.getMonthsField();
                } catch (Exception NumberFormatException) {
                    error.setHeaderText("Months value erroneous");
                    error.show();
                }

                if (initialBal == 0) {
                    error.setHeaderText("Initial Balance value erroneous");
                    error.show();
                } else if (interestRate == 0) {
                    error.setHeaderText("Interest Rate value erroneous");
                    error.show();
                } else if (years < 0) {
                    error.setHeaderText("Years value erroneous");
                    error.show();
                } else if (months < 0) {
                    error.setHeaderText("Months value erroneous");
                    error.show();
                } else {
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);

                    double futInvVal = OpCalc.investmentVal(initialBal,
                            interestRate, years, months, compound, selected, 
                            amountReinv, intervalChoice);
                    double totEarned = OpCalc.totalInterestEarned(futInvVal,
                            initialBal);

                    dataBox.setFutureValueField("$ " + df.format(futInvVal));
                    dataBox.setTotEarnedField("$ " + df.format(totEarned));
                }

            }
        });

        clearDataBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dataBox.setFutureValueField("");
                dataBox.setTotEarnedField("");
                dataBox.clearInputData();
            }
        });

        exit.setOnAction(e -> {
            Platform.exit();
        });
    }

}
