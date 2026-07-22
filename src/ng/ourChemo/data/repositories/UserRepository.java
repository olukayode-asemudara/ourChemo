package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User findById(int id);

    User findByUsername(String username);

    List<User> findAll();

    void delete(int id);

    void update(User updatedUser);

    void deleteAll();
}