/*
 */
package sacewicz.gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author sacew
 */
public class InputGrid extends GridPane {

    private Label empty;
    private Label initial_balance;
    private Label interest_rate;
    private Label years;
    private Label months;
    private Label compound_interval;
    private Label reg_cont;
    private Label dOrW_amm;
    private Label adv_opt;

    private TextField initial_balanceF;
    private TextField interest_rateF;
    private TextField yearsF;
    private TextField monthsF;
    private TextField amountF;

    private ToggleGroup group;
    private RadioButton deposits = new RadioButton("Deposits ");
    private RadioButton withdrawals = new RadioButton("Withdrawals");

    private ChoiceBox<Intervals> compoundIntervalChoice;
    private ChoiceBox<Intervals> intervalChoice;

    public InputGrid() {

        setPadding(new Insets(10));
        setHgap(5);
        setVgap(5);

        empty = new Label("");
        add(empty, 0, 0, 5, 3);

        initial_balance = new Label("Initial balance:");
        add(initial_balance, 0, 0);
        initial_balanceF = new TextField();
        initial_balanceF.setPrefColumnCount(12);
        add(initial_balanceF, 0, 1);

        interest_rate = new Label("Interest rate:");
        add(interest_rate, 0, 2);
        interest_rateF = new TextField();
        interest_rateF.setPrefColumnCount(12);
        add(interest_rateF, 0, 3);

        HBox years_monthsHBOX = new HBox();
        add(years_monthsHBOX, 0, 4);
        years_monthsHBOX.setSpacing(10);
        VBox years_monthsVBOXleft = new VBox();
        VBox years_monthsVBOXright = new VBox();

        years_monthsHBOX.getChildren().add(years_monthsVBOXleft);
        years_monthsHBOX.getChildren().add(years_monthsVBOXright);

        years = new Label("Years:");
        years_monthsVBOXleft.getChildren().add(years);
        yearsF = new TextField();
        yearsF.setText("0");
        years_monthsVBOXleft.getChildren().add(yearsF);
        yearsF.setPrefColumnCount(6);

        months = new Label("Months:");
        years_monthsVBOXright.getChildren().add(months);
        monthsF = new TextField();
        monthsF.setText("0");
        years_monthsVBOXright.getChildren().add(monthsF);
        monthsF.setPrefColumnCount(6);

        compound_interval = new Label("Compound interval:");
        add(compound_interval, 0, 5);
        compoundIntervalChoice
                = new ChoiceBox(FXCollections.observableArrayList(
                        Intervals.Daily, Intervals.Weekly,
                        Intervals.Monthly, Intervals.Yearly)
                );
        add(compoundIntervalChoice, 0, 6);
        compoundIntervalChoice.setValue(Intervals.Monthly);

        adv_opt = new Label("Advanced options");
        add(adv_opt, 0, 8);
        adv_opt.setAlignment(Pos.CENTER_RIGHT);

        reg_cont = new Label("Regular contributions:");
        add(reg_cont, 0, 9);
        group = new ToggleGroup();
        HBox dOrWHbox = new HBox();
        dOrWHbox.getChildren().add(deposits);
        deposits.setToggleGroup(group);
        deposits.setSelected(true);
        dOrWHbox.getChildren().add(withdrawals);
        withdrawals.setToggleGroup(group);
        add(dOrWHbox, 0, 10);

        dOrW_amm = new Label("Deposit amount:");
        add(dOrW_amm, 0, 11);

        HBox dwHBOX = new HBox();
        add(dwHBOX, 0, 12);
        dwHBOX.setSpacing(10);
        amountF = new TextField();
        dwHBOX.getChildren().add(amountF);
        amountF.setPrefColumnCount(10);

        intervalChoice
                = new ChoiceBox(FXCollections.observableArrayList(
                        Intervals.Daily, Intervals.Weekly,
                        Intervals.Monthly, Intervals.Yearly)
                );
        dwHBOX.getChildren().add(intervalChoice);
        intervalChoice.setValue(Intervals.Monthly);

        //Event Handling
        EventHandler<KeyEvent> consumeEvent = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!event.getCharacter().matches("\\d{0,1}([\\.]\\d{0,1})?")) {
                    event.consume();
                }
            }
        };

        initial_balanceF.addEventFilter(KeyEvent.KEY_TYPED, consumeEvent);
        yearsF.addEventFilter(KeyEvent.KEY_TYPED, consumeEvent);
        monthsF.addEventFilter(KeyEvent.KEY_TYPED, consumeEvent);
        interest_rateF.addEventFilter(KeyEvent.KEY_TYPED, consumeEvent);
        amountF.addEventFilter(KeyEvent.KEY_TYPED, consumeEvent);
        
        EventHandler<ActionEvent> radioCheck = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (deposits.isSelected()) {
                    dOrW_amm.setText("Deposit amount:");
                }

                if (withdrawals.isSelected()) {
                    dOrW_amm.setText("Withdrawal amount:");
                }
            }
        };
        
        deposits.addEventFilter(ActionEvent.ACTION, radioCheck);
        withdrawals.addEventFilter(ActionEvent.ACTION, radioCheck);
    }

    public int getCompoundIntervalsChoice() {
        Intervals c = compoundIntervalChoice.getValue();
        int compound = 0;
        switch (c) {
            case Daily:
                compound = 365;
                break;
            case Weekly:
                compound = 52;
                break;
            case Monthly:
                compound = 12;
                break;
            case Yearly:
                compound = 1;
                break;
        }

        return compound;
    }

    public double getInitialBalanceField() {
        if (initial_balanceF.getText().isEmpty()) {
            return 0;
        }
        return Double.parseDouble(initial_balanceF.getText());
    }

    public double getInterestRateField() {
        if (interest_rateF.getText().isEmpty()) {
            return 0;
        }
        return Double.parseDouble(interest_rateF.getText());
    }

    public int getYearsField() {
        if (yearsF.getText().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(yearsF.getText());
    }

    public int getMonthsField() {
        if (monthsF.getText().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(monthsF.getText());
    }
    
    public char getSelectedField() {
        RadioButton rd = (RadioButton) group.getSelectedToggle();
        return rd.getText().charAt(0);
    }
    
    public double getAmountReinvested(){
        return Double.parseDouble(amountF.getText());
    }
    
    public int getIntervalsChoice() {
        Intervals c = intervalChoice.getValue();
        int compound = 0;
        switch (c) {
            case Daily:
                compound = 365;
                break;
            case Weekly:
                compound = 52;
                break;
            case Monthly:
                compound = 12;
                break;
            case Yearly:
                compound = 1;
                break;
        }

        return compound;
    }

    public void clearInputData() {
        initial_balanceF.setText("");
        interest_rateF.setText("");
        yearsF.setText("0");
        monthsF.setText("0");
        compoundIntervalChoice.setValue(Intervals.Monthly);
    }

}
