package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements ng.ourChemo.data.reopsitories.UserRepository {
    private int count;
    private List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        count++;
        return user;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
        count--;
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

    @Override
    public User findById(long id) {
        for (User user : users) {
            return user;
        }
        return null;
    }
}