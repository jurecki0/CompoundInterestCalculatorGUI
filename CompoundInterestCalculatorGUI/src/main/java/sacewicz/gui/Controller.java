package sacewicz.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author sacew
 */
public class Controller extends Application {

    public static void execute(String[] args) {
        Controller.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //LAYOUT
        primaryStage.setTitle("Compound Interest Calculator");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(245);
        primaryStage.setMinWidth(400);
        
        VBox mainBox = new MainBox();
        mainBox.setSpacing(10);
        Scene scene = new Scene(mainBox);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
