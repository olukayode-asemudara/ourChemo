package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    private UserRepository repository;
    private User user;

    @BeforeEach
    void setUp() {
        repository = new UserRepositoryImpl();
        user = new User(1,"Olukayode", "Asemudra", "kay@hotmail.com");
    }

    @Test
    void testThatICanSaveAUser() {
        repository.save(user);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void testThatICanSaveAUserWithDuplicateId() {
        repository.save(user);
        User duplicate = new User(1, "Kay", "Kayode", "ky@semicolon");
        IllegalStateException exception = assertThrows(
                IllegalStateException.class, ()-> repository.save(duplicate)
        );
        assertEquals(1, repository.findAll().size());
        assertEquals("User with id 1 already exists.", exception.getMessage());
    }

    @Test
    void testThatICanFindUserById() {
        repository.save(user);
        User foundUser = repository.findById(1);
        assertNotNull(foundUser);
        assertEquals(1, foundUser.getId());
        assertEquals("Olukayode Asemudra", foundUser.getFullName());
    }

    @Test
    void testThatICanDeleteUserById() {
        repository.save(user);
        repository.delete(1);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void testThatICanDeleteNonExistingUserById() {
        repository.save(user);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repository.delete(2)
        );
        assertEquals("User with id 2 does not exist.", exception.getMessage());
    }

    @Test
    void testThatICanDeleteUserFromEmptyRepository() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> repository.deleteAll()
        );
        assertEquals("Repository is already empty.", exception.getMessage());
    }

    @Test
    void testThatICanUpdateUserById() {
        repository.save(user);
        User updatedUser = new User(1, "Kay", "Kayode", "ky@gmail");
        repository.update(updatedUser);
        User foundUser = repository.findById(1);
        assertNotNull(foundUser);
        assertEquals("Kay", foundUser.getFirstName());
        assertEquals(1, foundUser.getId());
    }

    @Test
    void testDeleteAllUsers() {
        repository.save(user);
        User user1 = new User(2, "Olukayode", "Asemudra", "kay@hotmail.com");
        repository.save(user1);
        assertEquals(2, repository.findAll().size());
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }
}