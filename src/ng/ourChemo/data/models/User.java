package ng.ourChemo.data.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public User(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public  void setLastName(String lastName) {this.lastName = lastName;}

    public String getFirstName() {return firstName;}
    public String getLastName(){return lastName;}
}