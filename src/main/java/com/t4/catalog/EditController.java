package com.t4.catalog;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    private  MainController mainController ;
    private Stage stage;

    private Item item ;

    @FXML private TextField nameTF ;
    @FXML private ChoiceBox<String> typeCB ;
    @FXML private ChoiceBox<String> attributeCB ;
    @FXML private ChoiceBox<String> tagCB ;
    @FXML private TextField attributeTF ;
    @FXML private TextArea tagTextArea;
    @FXML private TableColumn<Attribute,String> nameColumn ;
    @FXML private TableColumn<Attribute,String> valueColumn ;
    @FXML private TableView<Attribute> tableView ;

    public TableView<Attribute> getTableView() {
        return tableView;
    }

    @FXML
    private Button CancelButton;

    @FXML
    private Button saveButton;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void init(MainController mainController)
    {
        setMainController(mainController);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @FXML
    void CancelButtonClicked(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {


        tableView.refresh();

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));

        tableView.setEditable(true);
        valueColumn.setCellFactory(
                TextFieldTableCell.forTableColumn());
        valueColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Attribute, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAttributeValue(t.getNewValue())
        );
    }
}
