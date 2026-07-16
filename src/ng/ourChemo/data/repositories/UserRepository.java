package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;

import java.util.List;

public interface UserRepository {
    void save(User User);
    User findById(int id);
    List<User> findAll();
    void delete(int id);
    void update(User updatedUser);
    void deleteAll();
}