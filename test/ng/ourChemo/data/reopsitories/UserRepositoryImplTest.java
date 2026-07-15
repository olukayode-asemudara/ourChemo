package ng.ourChemo.data.reopsitories;

import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRepositoryImplTest {

    private UserRepository repository;
    private User user;

    @BeforeEach
    public void setUp(){
        repository = new UserRepositoryImpl();
        user = new User();
    }

    @Test
    public void testEmptyRepository(){
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testSaveUser() {
        repository.save(user);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testDeleteUser() {
        user.setId(1);
        repository.save(user);
        repository.delete(1);
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testClearRepository(){
        repository.save(user);
        User user2 = new User();
        user2.setId(1);
        repository.save(user2);
        assertEquals(2, repository.findAll().size());
        repository.deleteAll();
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void testTwoUsersSameID(){
        repository.save(user);
        User user2 = new User();
        assertThrows(IllegalStateException.class, ()-> repository.save(user2));
    }

    @Test
    public void testFindUserByID(){
        repository.save(user);
        repository.findById(0);
        assertEquals(0, user.getId());
    }

    @Test
    public void testFIndUserByIdNotExist(){
        repository.save(user);
        assertThrows(IllegalArgumentException.class, ()-> repository.findById(1));
    }
}
