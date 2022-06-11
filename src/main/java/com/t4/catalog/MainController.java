package com.t4.catalog;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class MainController implements Initializable {


    private Scene typeScene;
    private Stage typeStage;
    private Scene editScene;
    private Stage editStage;

    private TypeController typeController;
    private EditController editController;
    private Item item;
    private ArrayList<String> itemTypesName = new ArrayList<>();
    private ArrayList<ItemType> itemTypes = new ArrayList<>();

    private ArrayList<String> itemTagName = new ArrayList<>();

    @FXML
    private Button savetreebutton;

    @FXML
    private Menu help;

    @FXML
    private Button loadtree;

    private int count=0;
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private BorderPane mainBorder;
    @FXML
    public TreeView<String> treeView;
    @FXML
    private TableColumn<Attribute, String> attributeNameColumn;
    @FXML
    private TableColumn<Attribute, String> attributeValueColumn;
    @FXML
    private TableView<Attribute> tableView;
    @FXML
    private TextField addNameTF;
    @FXML
    private TextField addTagsTF;
    @FXML
    private TableColumn<Attribute, String> addNameColumn;
    @FXML
    private TableColumn<Attribute, String> addValueColumn;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TableView<Attribute> addTableView;
    @FXML
    ChoiceBox<String> choiceBoxTags;
    @FXML
    private ChoiceBox<String> attrChoiceBox;
    @FXML
    private TextField attrTF;

    private ArrayList<Item> items = new ArrayList<>();


    @FXML
    private TextField TextField;


    @FXML
    private TextField displayTagsTF;


    public ArrayList<ItemType> getItemTypes() {
        return itemTypes;
    }

    public ArrayList<String> getItemTypesName() {
        return itemTypesName;
    }


    public MainController() {
        this.typeController = new TypeController();
        this.editController = new EditController();
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

    public ChoiceBox<String> getChoiceBoxTags() {
        return choiceBoxTags;
    }

    public ArrayList<String> getItemTagName() {
        return itemTagName;
    }

    public void setEditScene(Scene editScene) {
        this.editScene = editScene;
    }

    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }

    private Set<String> tags;
    @FXML
    public void addTagButton() {
        String name = addTagsTF.getText();
        tags= new HashSet<>();
        tags.add(name);


       /*
        for (int i = 0; i < getItemTagName().size(); i++) {
            if (getItemTagName().get(i).equals(name)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tag already exists");
                alert.setContentText("Please enter a different tag name");
                alert.showAndWait();
                return;
            }
        }
        getItemTagName().add(name);

        for (int i = 0; i < tableView.getItems().size(); i++) {
            itemTagName.add(tableView.getItems().get(i).getAttributeName());
        }

        getChoiceBoxTags().getItems().add(name);
        */
    }


    public void setEditController(EditController editController) {
        this.editController = editController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        typeController.init(this);
        editController.init(this);

        ItemType root = new ItemType("root", "root");
        treeView.setRoot(root);
        treeView.setShowRoot(false);


        attributeNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        attributeValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));

        addNameColumn.setCellValueFactory(new PropertyValueFactory<>("attributeName"));
        addValueColumn.setCellValueFactory(new PropertyValueFactory<>("attributeValue"));


        choiceBox.setOnAction(event -> {
                    if (choiceBox.getItems().size() > 0) {
                        String selectedItem = choiceBox.getSelectionModel().getSelectedItem();
                        ItemType itemType = null;
                        addTableView.getItems().clear();
                        attrChoiceBox.getItems().clear();
                        for (int i = 0; i < itemTypes.size(); i++) {
                            if (itemTypes.get(i).getName().equals(selectedItem)) {
                                itemType = itemTypes.get(i);
                            }

                        }
                        if (itemType != null) {
                            for (int i = 0; i < itemType.getAttributesName().size(); i++) {
                                addTableView.getItems().add(new Attribute(itemType.getAttributesName().get(i), ""));
                                attrChoiceBox.getItems().add(itemType.getAttributesName().get(i));

                            }
                            addTableView.refresh();
                        }


                    }
                }
        );

        /*choiceBoxTags.setOnAction(event -> {

                    if (choiceBoxTags.getItems().size() > 0) {
                        String selectedTags = choiceBoxTags.getSelectionModel().getSelectedItem();
                        //itemTagName = null;

                        for (int i = 0; i < itemTagName.size(); i++) {
                            if (choiceBoxTags.getSelectionModel().getSelectedItem().equals(selectedTags)) {
                                selectedTags = choiceBoxTags.getSelectionModel().getSelectedItem();
                            }
                        }

                        displayTagsTF.setEditable(false);
                        displayTagsTF.setText(String.valueOf(itemTagName));

                    }
                }
        );
*/

        treeView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (treeView.getSelectionModel().getSelectedItem().getClass() == ItemType.class) {
                    return;
                }
                Item item = (Item) treeView.getSelectionModel().getSelectedItem();
                tableView.getItems().clear();
                for (int i = 0; i < item.getAttributes().size(); i++) {
                    tableView.getItems().add(item.getAttributes().get(i));
                }
                tableView.refresh();
            }
        });


    }


    @FXML
    public void saveButton() {

        String name = addNameTF.getText();
        // String tag = addTagsTF.getText();

        String typeName = choiceBox.getSelectionModel().getSelectedItem();
        ItemType itemType = null;
        for (ItemType type : itemTypes) {
            if (type.getName().equals(typeName)) {
                itemType = type;
            }
        }

        if (itemType == null) {
            return;
        }


        item = new Item(name, name, tags, itemType);

        items.add(item);


        for (int i = 0; i < addTableView.getItems().size(); i++) {
            Attribute attribute = addTableView.getItems().get(i);
            item.addAttribute(attribute);

        }

        ItemType root = (ItemType) treeView.getRoot();
        boolean isExist = false;
        for (int i = 0; i < root.getChildren().size(); i++) {
            ItemType treeItem = (ItemType) root.getChildren().get(i);


            if (treeItem.getValue().equals(itemType.getName())) {
                item.setFolder(treeItem);
                treeItem.getChildren().add(item);
                isExist = true;
            }

        }
        if (!isExist) {
            root.getChildren().add(item.getFolder());
            item.getFolder().getChildren().add(item);

        }

        addNameTF.clear();
        addTagsTF.clear();
        //displayTagsTF.clear();
        addTableView.getItems().clear();
        attrChoiceBox.getItems().clear();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Item has been added to the " + item.getFolder().getName());
        alert.showAndWait();


    }

    @FXML
    public void newTypeButton() {

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
    public void showAttribute() {
        for (int i = 0; i < addTableView.getItems().size(); i++) {
            Attribute attribute = addTableView.getItems().get(i);
            System.out.println("name :" + attribute.getAttributeName() + " value :" + attribute.getAttributeValue());
        }
    }

    @FXML
    void deleteButtonClicked() throws IOException {
        //Removes the selected Item
        TreeItem<String> t = treeView.getSelectionModel().getSelectedItem();

        deleteMethod(t);

        items.remove(t);

    }
    void deleteMethod(TreeItem<String> t){

        TreeItem<String> temp = t;

        temp.getParent().getChildren().remove(t);
    }

    @FXML
    public void addValueToAttribute() {
        if (attrChoiceBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select an attribute");
            alert.showAndWait();
            return;
        }
        if (attrTF.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Value is empty");
            alert.setContentText("Please enter value");
            alert.showAndWait();
            return;
        }
        String attr = attrChoiceBox.getSelectionModel().getSelectedItem();
        for (int i = 0; i < addTableView.getItems().size(); i++) {
            if (addTableView.getItems().get(i).getAttributeName().equals(attr)) {
                Attribute attribute = addTableView.getItems().get(i);
                attribute.setAttributeValue(attrTF.getText());
                addTableView.getItems().remove(i);
                addTableView.getItems().add(i, attribute);
                addTableView.refresh();
                attrTF.clear();
                return;

            }
        }

    }


    @FXML
    public void editButton() {
        if (treeView.getSelectionModel().getSelectedItem() == null || treeView.getSelectionModel().getSelectedItem().getClass() == ItemType.class) {
            return;
        }

        Item item = (Item) treeView.getSelectionModel().getSelectedItem();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit.fxml"));
            Parent root = loader.load();
            editScene = new Scene(root);
            editStage = new Stage();
            editStage.setScene(editScene);
            editController.setItem(item);
            editStage.show();
            editController = loader.getController();
            editController.setMainController(this);
            editController.setStage(typeStage);
            for (int i = 0; i < item.getAttributes().size(); i++) {
                editController.getTableView().getItems().add(item.getAttributes().get(i));
            }
            editController.getTableView().refresh();

            this.setEditController(editController);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static private void saveParentAndChildren(TreeItem<String> root, String parent) throws FileNotFoundException {


        System.out.println( root.getValue());
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(new File("tree_structure.txt"),true ))){
            writer.println(root.getValue() + "=" + parent);

            for(TreeItem<String> child: root.getChildren()){
                if(child.getChildren().isEmpty()){
                    writer.println(child.getValue() + "=" + root.getValue());
                } else {
                    saveParentAndChildren(child, root.getValue());
                }
            }
        }
        catch (FileNotFoundException ex) {

        }



    }

    static private TreeItem loadTree() {
        TreeItem root = null;
        HashMap<String, String> treeStructure = new HashMap();

        File file = new File("tree_structure.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String st;
            while((st=br.readLine()) != null){
                String[] splitLine = st.split("=");
                treeStructure.put(splitLine[0], splitLine[1]);
            }

            root = getChildrenNodes(treeStructure, "root").get(0);
            List<TreeItem> parentItems = getChildrenNodes(treeStructure, root.getValue().toString());

            if(parentItems.size() > 0)
            {
                root.getChildren().addAll(parentItems);
                for(TreeItem item : parentItems)
                {
                    item.getChildren().addAll(getChildrenNodes(treeStructure, item.getValue().toString()));
                }
            }
        }
        catch (IOException ex) {

        }

        return root;
    }

    static private List<TreeItem> getChildrenNodes(HashMap<String, String> hMap, String parent) {

        List<TreeItem> children = new ArrayList();

        for (Map.Entry<String, String> entry : hMap.entrySet())
        {

            if(entry.getValue().equals(parent))
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                children.add(new TreeItem(entry.getKey()));
            }
        }

        return children;
    }
    @FXML
    void savetreebuttonclicked (ActionEvent event) throws IOException {

           /* FileWriter w = new FileWriter("tree_structure.txt");
            Formatter f = new Formatter(w);

            for (Item item : items) {
                StringBuilder s = new StringBuilder(item.getName() + ", " + item.getFolder().getName() + ", ");

                for (int i = 0; i < item.getAttributesValues().size(); i++) {
                    s.append(item.getAttributesValues().get(i)).append(", ");
                }

                for (String tag : item.getTags()) {
                    s.append("#").append(tag).append(", ");
                }
                s = new StringBuilder(s.substring(0, s.length() - 2));
                f.format("%s%n", s);
            }
            f.close();
            w.close();*/
            savetreee(treeView.getRoot(),"root");

    }
    void savetreee(TreeItem<String> root, String parent){
        System.out.println("Current Parent :" + root.getValue());
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(new File("tree_structure.txt"),true /* append = true */))){
            writer.println(root.getValue() + "=" + parent);

            for(TreeItem<String> child: root.getChildren()){
                if(child.getChildren().isEmpty()){
                    writer.println(child.getValue() + "=" + root.getValue());
                } else {
                    saveParentAndChildren(child, root.getValue());
                }
            }
        }
        catch (FileNotFoundException ex) {

        }
    }


    @FXML
    TreeItem<String> loadtreeclicked (ActionEvent event){
            TreeItem root = null;
            HashMap<String, String> treeStructure = new HashMap();

            File file = new File("tree_structure.txt");
            try(BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String st;
                while((st=br.readLine()) != null){
                    String[] splitLine = st.split(",");
                    treeStructure.put(splitLine[0], splitLine[1]);
                }

                root = getChildrenNodes(treeStructure, "root").get(0);//This gets the root node
                List<TreeItem> parentItems = getChildrenNodes(treeStructure, root.getValue().toString());//get the root's children
                //if the root has children get every child's children if they have some.
                if(parentItems.size() > 0)
                {
                    root.getChildren().addAll(parentItems);
                    for(TreeItem item : parentItems)
                    {
                        item.getChildren().addAll(getChildrenNodes(treeStructure, item.getValue().toString()));
                    }
                }
            }
            catch (IOException ex) {

            }

            return root;
        }


    @FXML
    void searchButtonClicked(ActionEvent event) {
        count++;

        handleSearch(treeView.getRoot(),searchTextField.getText().trim());
    }

    @FXML
    void searchTextFieldClicked(ActionEvent event) {


    }
    protected int handleSearch(TreeItem<String> rootItem, String text) {
        int count=0;
        if(rootItem!=null&&rootItem.getValue().equals(text)){
            treeView.getSelectionModel().select(rootItem);
            return 1;
        }
        if(rootItem!=null&&!rootItem.getChildren().isEmpty()){
            for(TreeItem<String> treeItem: rootItem.getChildren()){
                count+=handleSearch(treeItem, text);
                if(this.count==count){
                    break;
                }
            }
        }
        return count;
    }

    @FXML
    void aboutclicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to Use");
        alert.setContentText(
                "Add Item: You can add items with using Add Item tab.\n" +
                        "Show Items: These items will automatically show in the display section.\n" +
                        "Show Attributes: By double clicking the items on the treeview, you can see these items attibutes.\n" +
                        "Edit Attribute: Select item, click edit to edit attributes by rewriting value at the column value." +
                        " The other buttons are not working.\n" +
                        "Search Item: You can search item by name. Write item name you want to search in the text field.\n" +
                        "Delete Item: Click the item and use delete button.\n" +
                        "Load Tree and Save Tree Buttons are not working.");

        alert.showAndWait();

    }
}
