package ng.ourChemo.data.models;

public class Drug {
    private String name;
    private long id;
    private String brand;

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}