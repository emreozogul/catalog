package com.t4.catalog;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;
import java.util.List;

public class ItemType extends TreeItem<String> {

    private String name;

    public ArrayList<Item> getItems() {
        return items;
    }

    private ArrayList<String> attributesName = new ArrayList<String>();


    private ArrayList<Item> items = new ArrayList<>();

    public ItemType(String value, String name) {
        super(value);
        this.name = name;
    }

    public ItemType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setValue(name );
        this.name = name;
    }

    public ArrayList<String> getAttributesName() {
        return attributesName;
    }

    public void setAttributesName(ArrayList<String> attributesName) {


        this.attributesName = attributesName;
    }
}
