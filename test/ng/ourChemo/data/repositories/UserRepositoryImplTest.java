package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplTest {
    private UserRepository repository;
    private User user;

    @BeforeEach
    public void setUp() {
        repository = new UserRepositoryImpl();
        user = new User();
    }

    @Test
    public void testEmptyRepository() {
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testSaveUser() {
        repository.save(user);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testFindUserByID() {
        user.setId(1);
        repository.save(user);
        User foundUser = repository.findById(1);
        assertNotNull(foundUser);
        assertEquals(1, foundUser.getId());
    }

    @Test
    public void testFindUserByInvalidID() {
        User foundUser = repository.findById(100);
        assertNull(foundUser);
    }

    @Test
    public void testSaveMultipleUsers() {
        user.setId(1);
        User user2 = new User();
        user2.setId(2);
        repository.save(user);
        repository.save(user2);
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void testDeleteUser() {
        user.setId(1);
        repository.save(user);
        repository.delete(1);
        assertEquals(0, repository.findAll().size());
        assertNull(repository.findById(1));
    }

    @Test
    public void testDeleteNonExistingUser() {
        user.setId(1);
        repository.save(user);
        repository.delete(10);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testUpdateUser() {
        user.setId(1);
        repository.save(user);
        User updatedUser = new User();
        updatedUser.setId(1);
        repository.update(updatedUser);
        assertEquals(1, repository.findById(1).getId());
    }

    @Test
    public void testUpdateNonExistingUser() {
        User updatedUser = new User();
        updatedUser.setId(10);
        repository.update(updatedUser);
        assertNull(repository.findById(10));
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testClearRepository() {
        repository.save(user);
        User user2 = new User();
        user2.setId(1);
        repository.save(user2);
        assertEquals(2, repository.findAll().size());
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testDeleteAllOnEmptyRepository() {
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testFindAllReturnsCopy() {
        repository.save(user);
        repository.findAll().clear();
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testDuplicateUserIds() {
        user.setId(1);
        User user2 = new User();
        user2.setId(1);
        repository.save(user);
        repository.save(user2);
        assertEquals(2, repository.findAll().size());
    }
}