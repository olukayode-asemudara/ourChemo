package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void delete(int id) {
        User user = findById(id);
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public void update(User updatedUser) {
        for (int index = 0; index < users.size(); index++) {
            if (users.get(index).getId() == updatedUser.getId()) {
                users.set(index, updatedUser);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        users.clear();
    }
}