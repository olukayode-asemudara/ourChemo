package ng.ourChemo.services;

import ng.ourChemo.data.models.User;

import java.util.List;

public interface AuthServices {

    void registerUser(String username, String fullName, String password);

    void login(String username, String password);

    void logout(String username);

    boolean isLoggedIn();

    List<User> getAllUsers();
}