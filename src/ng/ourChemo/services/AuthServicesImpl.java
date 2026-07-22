package ng.ourChemo.services;

import ng.ourChemo.data.models.User;
import ng.ourChemo.data.repositories.UserRepository;
import ng.ourChemo.data.repositories.UserRepositoryImpl;

import java.util.List;

public class AuthServicesImpl implements AuthServices {

    private UserRepository userRepository = new UserRepositoryImpl();

    private User loggedInUser;

    @Override
    public void registerUser(String username, String fullName, String password) {
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                throw new IllegalArgumentException("Username already exists.");
            }
        }
        User user = new User();
        user.setUsername(username);
        user.setFullName(fullName);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User does not exist.");
        }
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password.");
        }
        loggedInUser = user;
    }

    @Override
    public void logout(String username) {
        if (loggedInUser != null &&
                loggedInUser.getUsername().equalsIgnoreCase(username)) {
            loggedInUser = null;
        }
    }

    @Override
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}