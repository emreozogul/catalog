package com.t4.catalog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScene.fxml"));
        Scene scene = new Scene(loader.load());


        FXMLLoader typeLoader = new FXMLLoader(getClass().getResource("type.fxml"));
        Parent root = typeLoader.load();
        Scene typeScene = new Scene(root);
        Stage typeStage = new Stage();
        typeStage.setScene(typeScene);



        MainController controller = loader.getController();
        TypeController typeController = typeLoader.getController();
        controller.setTypeController(typeController);
        typeController.setMainController(controller);
        controller.setTypeScene(typeScene);
        controller.setTypeStage(typeStage);




        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}