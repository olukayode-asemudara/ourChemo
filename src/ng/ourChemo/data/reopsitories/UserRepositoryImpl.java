package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private List<User> Users = new ArrayList<>();


    @Override
    public void save(User User) {
        if (User == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        for (User user : Users) {
            if (user.getId() == User.getId()) {
                throw new IllegalStateException(
                        "User with id " + User.getId() + " already exists."
                );
            }
        }
        Users.add(User);
    }

    @Override
    public User findById(int id) {
        for (User User : Users) {
            if (User.getId() == id) {
                return User;
            }
        }
        throw new IllegalArgumentException(
                "User with id " + id + " does not exist.");
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(Users);
    }

    @Override
    public void delete(int id) {
        User User = findById(id);
        Users.remove(User);
    }

    @Override
    public void update(User updatedUser) {
        if (updatedUser == null) {
            throw new IllegalArgumentException("Updated User cannot be null.");
        }
        User existingUser = findById(updatedUser.getId());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
    }

    @Override
    public void deleteAll() {
        if (Users.isEmpty()) {
            throw new IllegalStateException("Repository is already empty.");
        }

        Users.clear();
    }
}