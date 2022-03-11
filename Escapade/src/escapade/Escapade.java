/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escapade;

import com.sun.javaws.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author KattaX
 */
public class Escapade extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            
            StackPane root = new StackPane();
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/FXMLNvVehicule.fxml")));
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/FXMLNvLocation.fxml")));
            Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("GUI/firstdashboard.fxml")));
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("view/FXMLNvClient.fxml")));
            
            primaryStage.setTitle("Bechir Ben Youssef");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
