package com.raymondbl.essaychecker;
/** Essay Checker searches and points out input
 * text for first and second person personal 
 * pronouns and contractions. There is an option  
 * to replace contractions with their extended 
 * form, but it is still advised to check the results.
 * @author Raymond Liu
 * @version 1.05
 * @since 06/13/2014
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Loader extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Academic Style Checker (Contractions & Pronouns");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}