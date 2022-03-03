/*
 */
package sacewicz.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sacew
 */
public class OutputGrid extends GridPane {
    
    private Label futureValue;
    private Label totEarned;
    
    private TextField futureValueF;
    private TextField totEarnedF;
    
    public OutputGrid(){
        
        setPadding(new Insets(10));
        setHgap(5);
        setVgap(5);
        
        futureValue = new Label("Future Investment Value:");
        add(futureValue, 2, 0);
        futureValueF = new TextField();
        add(futureValueF, 2, 1);
        futureValueF.setPrefColumnCount(12);
        futureValueF.setEditable(false);
        
        totEarned = new Label("Total Interest Earned:");
        add(totEarned, 2, 2);
        totEarnedF = new TextField();
        add(totEarnedF, 2, 3);
        totEarnedF.setPrefColumnCount(12);
        totEarnedF.setEditable(false);
    }
    
    public void setFutureValueField(String futureValTXT){
        futureValueF.setText(futureValTXT);
    }
    
    public void setTotEarnedField(String totEarnedTXT){
        totEarnedF.setText(totEarnedTXT);
    }
}
