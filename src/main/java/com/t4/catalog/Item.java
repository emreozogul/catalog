package com.t4.catalog;

import javafx.scene.control.TreeItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Item  extends TreeItem<String> implements Serializable {



    //private String tag;

    private Set<String> tags;
    private String name;

    private ItemType folder;
    private ArrayList<Attribute> attributes;
    private ArrayList<String> attributesValues;


    public Item(String value, String name, Set<String> tags , ItemType folder) {
        super(value);
        // this.tag = tag;
        this.name = name;
        this.folder = folder;
        this.attributes = new ArrayList<>();
        this.attributesValues = new ArrayList<>();
        this.tags=tags;


    }

    public ItemType getFolder() {
        return folder;
    }

    public void setFolder(ItemType folder) {
        this.folder = folder;
    }

    /*  public String getTag() {
          return tag;
      }

      public void setTag(String tag) {
          this.tag = tag;
      }
  */
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
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
        return "Item{" + name +
                ", tag='"  + '\'' +
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

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAttributesValues() {
        return attributesValues;
    }
}

