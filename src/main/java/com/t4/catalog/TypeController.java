package com.t4.catalog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TypeController implements Initializable {

    private Stage stage;


    private MainController mainController ;



    @FXML private TextField nameTF;
    @FXML private TextField typeTF;

    @FXML private TableView<Attribute> tableView;
    @FXML private TableColumn<Attribute,String> nameColumn;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Attribute,String>( "attributeName"));
    }


    @FXML
    public void addChoice(){


        String name = typeTF.getText();

        for (int i = 0; i <mainController.getItemTypesName().size() ; i++) {
            if(mainController.getItemTypesName().get(i).equals(name)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Type already exists");
                alert.setContentText("Please enter a different name");
                alert.showAndWait();
                return;
            }

        }

        mainController.getItemTypesName().add(name);


        ItemType itemType = new ItemType(name,name);
        for (int i = 0; i <tableView.getItems().size() ; i++) {
            itemType.getAttributesName().add(tableView.getItems().get(i).getAttributeName());

        }


        mainController.getItemTypes().add(itemType);
        mainController.getChoiceBox().getItems().add(name);
        this.stage.close();





    }

    @FXML
    public void addAttribute(){
        String name = nameTF.getText();
        tableView.getItems().add(new Attribute(name,"null"));
    }






}
