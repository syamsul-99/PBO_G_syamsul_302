package com.praktikum.barang;

public class Item {
    private String itemName;
    private String description;
    private String location;
    private String status; // "Reported" atau "Claimed"

    public Item(String itemName, String description, String location, String reported) {
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.status = "Reported";
    }

    public String getItemName() { return itemName; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
