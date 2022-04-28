package com.t4.catalog;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {



    private Scene typeScene;
    private Stage typeStage;
    private TypeController typeController;
    private Item item;
    private ArrayList<String> itemTypesName= new ArrayList<>();
    private ArrayList<ItemType> itemTypes = new ArrayList<>();

    private ArrayList<String> itemTagName=new ArrayList<>();

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
    @FXML private TableView<Attribute> addTableView ;
    @FXML ChoiceBox<String> choiceBoxTags;
    @FXML private ChoiceBox<String> attrChoiceBox;
    @FXML private TextField attrTF;



    @FXML
    private TextField TextField;


    @FXML private TextField displayTagsTF;




    public ArrayList<ItemType> getItemTypes() {
        return itemTypes;
    }

    public ArrayList<String> getItemTypesName() {
        return itemTypesName;
    }


    public MainController() {
        this.typeController = new TypeController();
    }

    public void setTypeScene(Scene typeScene) {
        this.typeScene = typeScene;
    }

    public void setTypeStage(Stage typeStage) {
        this.typeStage = typeStage;
    }


    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public ChoiceBox<String> getChoiceBoxTags(){return choiceBoxTags;}

    public ArrayList<String> getItemTagName(){return itemTagName;}


    @FXML
    public void addTagButton(){
        String name = addTagsTF.getText();
        for (int i = 0; i <getItemTagName().size() ; i++) {
            if(getItemTagName().get(i).equals(name)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tag already exists");
                alert.setContentText("Please enter a different tag name");
                alert.showAndWait();
                return;
            }
        }
        getItemTagName().add(name);

        for (int i = 0; i <tableView.getItems().size() ; i++) {
            itemTagName.add(tableView.getItems().get(i).getAttributeName());
        }

        getChoiceBoxTags().getItems().add(name);
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        typeController.init(this);

        ItemType root = new ItemType("root", "root");
        treeView.setRoot(root);
        treeView.setShowRoot(false);




        attributeNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));

        addNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        addValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));

        tableView.setEditable(true);
        attributeValueColumn.setEditable(true);

        attributeValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        attributeValueColumn.setOnEditCommit(event -> {
            new EventHandler<TableColumn.CellEditEvent<Attribute, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Attribute, String> t) {

                    Attribute  attribute = tableView.getSelectionModel().getSelectedItem();

                    Item i = (Item) treeView.getSelectionModel().getSelectedItem();
                    i.getAttributes().set(tableView.getSelectionModel().getSelectedIndex(),attribute);
                    tableView.refresh();


                }
            };


        });
        choiceBox.setOnAction(event -> {
            if(choiceBox.getItems().size()>0) {
                String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
                ItemType itemType = null;
                addTableView.getItems().clear();
                attrChoiceBox.getItems().clear();
                for (int i = 0; i < itemTypes.size() ; i++) {
                    if(itemTypes.get(i).getName().equals(selectedItem)){
                        itemType = itemTypes.get(i);
                    }

                }
                if(itemType != null){
                    for (int i = 0; i < itemType.getAttributesName().size(); i++) {
                        addTableView.getItems().add(new Attribute(itemType.getAttributesName().get(i),""));
                        attrChoiceBox.getItems().add(itemType.getAttributesName().get(i));

                    }
                    addTableView.refresh();
                }


            }
        }
        );

        choiceBoxTags.setOnAction(event -> {

                    if(choiceBoxTags.getItems().size()>0) {
                        String selectedTags = choiceBoxTags.getSelectionModel().getSelectedItem();
                        //itemTagName = null;

                        for (int i = 0; i < itemTagName.size() ; i++) {
                            if(choiceBoxTags.getSelectionModel().getSelectedItem().equals(selectedTags)){
                                selectedTags=choiceBoxTags.getSelectionModel().getSelectedItem();
                            }
                        }

                        displayTagsTF.setEditable(false);
                        displayTagsTF.setText(String.valueOf(itemTagName));

                    }
                }
        );





        //   deleteButton.setOnAction(actionEvent -> onDelete());

        treeView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                if(treeView.getSelectionModel().getSelectedItem().getClass() == ItemType.class){
                    return;
                }
                Item item = (Item) treeView.getSelectionModel().getSelectedItem();
                tableView.getItems().clear();
                for (int i = 0; i < item.getAttributes().size(); i++) {
                    tableView.getItems().add(item.getAttributes().get(i));
                }
                TextField.setText(item.getName());
                tableView.refresh();
        }});










    }

   /* public void onDelete(){
        TreeItem<String> selectedItem =treeView.getSelectionModel().getSelectedItem();
        if (selectedItem.isLeaf()) {

        } else {
     treeView.getRoot().getChildren().remove(selectedItem);
            }
        }
*/

    public void init(TypeController typeController){
        this.typeController= typeController;
    }


    @FXML
    public void saveButton(){



        String name = addNameTF.getText();
        String tag = addTagsTF.getText();

        String typeName = choiceBox.getSelectionModel().getSelectedItem();
        ItemType itemType = null;
        for (ItemType type : itemTypes) {
            if (type.getName().equals(typeName)) {
                itemType = type;
            }
        }

        if(itemType == null){
            return;
        }


        item = new Item(name,name,tag,itemType);


        for(int i = 0; i < addTableView.getItems().size(); i++){
            Attribute attribute = addTableView.getItems().get(i);
            item.addAttribute(attribute);

        }

        ItemType root = (ItemType) treeView.getRoot();
        boolean isExist = false;
        for (int i = 0; i <root.getChildren().size(); i++) {
            ItemType treeItem = (ItemType) root.getChildren().get(i);


            if(treeItem.getValue().equals(itemType.getName())){
                item.setFolder(treeItem);
                treeItem.getChildren().add(item);
                isExist = true;
            }

        }
        if(!isExist){
            root.getChildren().add(item.getFolder());
            item.getFolder().getChildren().add(item);

        }

        addNameTF.clear();
        addTagsTF.clear();
        displayTagsTF.clear();
        addTableView.getItems().clear();
        attrChoiceBox.getItems().clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Item has been added to the "+item.getFolder().getName());
        alert.showAndWait();




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

    @FXML
    public void showAttribute(){
        for (int i = 0; i <addTableView.getItems().size() ; i++) {
            Attribute attribute = addTableView.getItems().get(i);
            System.out.println("name :"+attribute.getAttributeName()+" value :"+attribute.getAttributeValue());
        }
    }
    @FXML
    void deleteButtonClicked() {
        //Removes the selected Item
        TreeItem<String> t =treeView.getSelectionModel().getSelectedItem();

        t.getParent().getChildren().remove(t);

    }
    @FXML
    void editButtonClicked() {
        ItemType selectedItem = (ItemType) treeView.getSelectionModel().getSelectedItem();

        Dialog<Void> dialog = new Dialog<>();
        DialogPane pane = new DialogPane();
        pane.getButtonTypes().addAll(ButtonType.OK);
        dialog.setDialogPane(pane);
        GridPane content = new GridPane();
        pane.setContent(content);
        content.add(new Label("name"), 0, 0);
        TextField name = new TextField();
        content.add(name, 1, 0);
        List<TextField> textFields = new ArrayList<>();


        dialog.setTitle("edit type");

        name.setText(selectedItem.getName());

        for (int i = 0; i < selectedItem.getAttributesName().size(); i++) {
            textFields.add(new TextField(selectedItem.getAttributesName().get(i)));
        }

        for (int i = 0; i < textFields.size(); i++) {
            content.add(new Label("field type " + (i + 1)), 0, i + 2);
            content.add(textFields.get(i), 1, i + 2);
        }
        dialog.showAndWait();

        selectedItem.setName(name.getText());
        ArrayList<String> fieldTypes = new ArrayList<>();

        for (TextField f : textFields) {
            fieldTypes.add(f.getText());
        }
        selectedItem.setAttributesName(fieldTypes);

    }
    @FXML
    void editItemNameButtonClicked() {
        int index = treeView.getSelectionModel().getSelectedIndex();

        TreeItem<String> t =treeView.getSelectionModel().getSelectedItem();

        Item i = (Item) t;

        String name = i.getName();

        //Take input from textfield for a new name value
        i.setName(TextField.getText());

        //Updates name of the Item
        treeView.getTreeItem(index).setValue(TextField.getText());
        treeView.refresh();

        tableView.refresh();
    }

    @FXML
    public void addValueToAttribute() {
        if(attrChoiceBox.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select an attribute");
            alert.showAndWait();
            return;
        }
        if(attrTF.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value is empty");
            alert.setContentText("Please enter value");
            alert.showAndWait();
            return;
        }
        String attr = attrChoiceBox.getSelectionModel().getSelectedItem();
        for (int i = 0; i < addTableView.getItems().size() ; i++) {
            if (addTableView.getItems().get(i).getAttributeName().equals(attr)){
                Attribute attribute = addTableView.getItems().get(i);
                attribute.setAttributeValue(attrTF.getText());
                addTableView.getItems().remove(i);
                addTableView.getItems().add(i,attribute);
                addTableView.refresh();
                attrTF.clear();
                return;

            }
        }

    }







}