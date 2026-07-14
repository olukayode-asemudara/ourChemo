package ng.ourChemo.data.models;

import java.time.LocalDate;

public class Drug {
    private int price;
    private String name;
    private int id;
    private LocalDate expiryDate;
    private String brand;

    public Drug(int id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}