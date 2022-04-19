package com.t4.catalog;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {



    private Scene typeScene;
    private Stage typeStage;
    private TypeController typeController;
    private Item item;
    private ArrayList<String> itemTypesName= new ArrayList<>();
    private ArrayList<ItemType> itemTypes = new ArrayList<>();

    @FXML private BorderPane mainBorder ;
    @FXML private TreeView<String> treeView ;
    @FXML private TableColumn<Attribute,String> attributeNameColumn;
    @FXML private TableColumn<Attribute,String> attributeValueColumn;
    @FXML private TableView<Attribute> tableView ;
    @FXML private TextField addNameTF;
    @FXML private TextField addTagsTF;
    @FXML private TableColumn<Attribute,String> addNameColumn ;
    @FXML private TableColumn<Attribute,String> addValueColumn ;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private TextArea addDesc ;
    @FXML private TableView<Attribute> addTableView ;


    public ArrayList<ItemType> getItemTypes() {
        return itemTypes;
    }

    public ArrayList<String> getItemTypesName() {
        return itemTypesName;
    }

    public TreeView<String> getTreeView() {
        return treeView;
    }

    public MainController() {
        this.typeController = new TypeController();
    }




    public BorderPane getMainBorder() {
        return mainBorder;
    }

    public Scene getTypeScene() {
        return typeScene;
    }

    public void setTypeScene(Scene typeScene) {
        this.typeScene = typeScene;
    }

    public Stage getTypeStage() {
        return typeStage;
    }

    public void setTypeStage(Stage typeStage) {
        this.typeStage = typeStage;
    }


    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*
         * TODO: tree view double click
         */


        typeController.init(this);

        ItemType root = new ItemType("root", "root");
        treeView.setRoot(root);
        treeView.setShowRoot(false);




        attributeNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));

        addNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        addValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));


        choiceBox.setOnAction(event -> {
            if(choiceBox.getItems().size()>0) {
                String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
                ItemType itemType = null;
                addTableView.getItems().clear();
                for (int i = 0; i < itemTypes.size() ; i++) {
                    if(itemTypes.get(i).getName().equals(selectedItem)){
                        itemType = itemTypes.get(i);
                    }

                }
                if(itemType != null){
                    for (int i = 0; i < itemType.getAttributesName().size(); i++) {
                        addTableView.getItems().add(new Attribute(itemType.getAttributesName().get(i),null));

                    }
                    addTableView.refresh();
                }


            }
        }
        );


        addValueColumn.setOnEditCommit(event -> {
            new EventHandler<TableColumn.CellEditEvent<Attribute, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Attribute, String> t) {
                    tableView.getItems().get(t.getTablePosition().getRow()).setAttributeValue(t.getNewValue());
                }
            };
        }
        );






    }

    public void init(TypeController typeController){
        this.typeController= typeController;
    }


    @FXML
    public void saveButton(){


        // TODO: save button action not complete


        String name = addNameTF.getText();
        String tag = addTagsTF.getText();
        String descText =addDesc.getText();

        ItemType type = new ItemType(name,tag);

        // choose item type from choice box then match it with item type

        item = new Item(name,name,tag,descText,type);



        for(int i = 0; i < addTableView.getItems().size(); i++){
            Attribute attribute = addTableView.getItems().get(i);
            item.addAttribute(attribute);

        }
        ItemType root = (ItemType) treeView.getRoot();
        for (int i = 0; i <root.getChildren().size(); i++) {
            ItemType treeItem = (ItemType) root.getChildren().get(i);
            if(treeItem.getValue().equals(type.getName())){
                item.setFolder(treeItem);
            }


        }


    }




    @FXML
    public void newTypeButton(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("type.fxml"));
            Parent root = loader.load();
            typeScene = new Scene(root);
            typeStage = new Stage();
            typeStage.setScene(typeScene);
            typeStage.show();
            typeController = loader.getController();
            typeController.setMainController(this);
            typeController.setStage(typeStage);
            this.setTypeController(typeController);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTypeController(TypeController controller) {
        this.typeController = controller;
    }
}