package ng.ourChemo.services;

public interface AuthServices {

    void registerUser(String username, String fullName,String password);

    void login(String username, String password);

    void logout();
}
