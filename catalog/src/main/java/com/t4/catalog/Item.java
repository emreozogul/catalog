package com.t4.catalog;

import javafx.scene.control.TreeItem;

import java.util.ArrayList;

public class Item  extends TreeItem<String> {



    private String tag;
    private final String name;
    private String description;
    private ItemType folder;
    private ArrayList<Attribute> attributes;

    public Item(String value, String name, String tag , String description, ItemType folder) {
        super(value);
        this.tag = tag;
        this.name = name;
        this.description = description;
        this.folder = folder;
    }

    public ItemType getFolder() {
        return folder;
    }

    public void setFolder(ItemType folder) {
        this.folder = folder;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }



    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
    }

    public void removeAttribute(int index) {
        this.attributes.remove(index);
    }

    @Override
    public String toString() {
        return "Item{" +
                ", tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                ", folder=" + folder +
                ", attributes=" + attributes +
                '}';
    }


    public void setAttributes(Attribute attribute) {
        this.attributes.add(attribute);
    }


    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

}

