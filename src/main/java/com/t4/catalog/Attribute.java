package com.t4.catalog;

import javafx.beans.property.SimpleStringProperty;

public class Attribute {

    private SimpleStringProperty attributeName;
    private SimpleStringProperty attributeValue;

    public Attribute(String attributeName, String attributeValue) {
        this.attributeName = new SimpleStringProperty(attributeName);
        this.attributeValue = new SimpleStringProperty(attributeValue);
    }

    public String getAttributeName() {
        return attributeName.get();
    }

    public SimpleStringProperty attributeNameProperty() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName.set(attributeName);
    }

    public String getAttributeValue() {
        return attributeValue.get();
    }

    public SimpleStringProperty attributeValueProperty() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue.set(attributeValue);
    }




}
