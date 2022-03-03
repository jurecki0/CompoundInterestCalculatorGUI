/*
 */
package sacewicz.gui;

import javafx.scene.layout.HBox;

/**
 *
 * @author sacew
 */
public class DataBox extends HBox {
    
    private InputGrid inputGrid;
    private OutputGrid outputGrid;
    
    public DataBox(){
        inputGrid = new InputGrid();
        outputGrid = new OutputGrid();
        this.getChildren().addAll(inputGrid, outputGrid);
    }
    
    public double getInitialBalanceField(){
        return inputGrid.getInitialBalanceField();
    }
    
    public double getInterestRateField(){
        return inputGrid.getInterestRateField();
    }
    
    public int getYearsField(){
        return inputGrid.getYearsField();
    }
    
    public int getMonthsField(){
        return inputGrid.getMonthsField();
    }
    
    public int getCompoundIntervalChoice(){
        return inputGrid.getCompoundIntervalsChoice();
    }
    
    public void setFutureValueField(String futValTXT){
        outputGrid.setFutureValueField(futValTXT);
    }

    public void setTotEarnedField(String totEarnedTXT){
        outputGrid.setTotEarnedField(totEarnedTXT);
    }
    
    public char getSelectedField(){
        return inputGrid.getSelectedField();
    }
    
    public double getAmountReinvested(){
        return inputGrid.getAmountReinvested();
    }
    
    public int getIntervalsChoice(){
        return inputGrid.getIntervalsChoice();
    }
    
    public void clearInputData(){
        inputGrid.clearInputData();
    }
}
