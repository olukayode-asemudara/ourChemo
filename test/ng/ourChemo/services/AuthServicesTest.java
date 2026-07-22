package ng.ourChemo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServicesTest {

    private AuthServices authService;

    @BeforeEach
    public void setUp() {
        authService = new AuthServicesImpl();
    }

    @Test
    public void testRegisterUser() {
        authService.registerUser(
                "kay",
                "Asemudara Olukayode",
                "1234"
        );
        assertEquals(1, authService.getAllUsers().size());
    }

    @Test
    public void testRegisterTwoUsers() {
        authService.registerUser(
                "kay",
                "Asemudara Olukayode",
                "1234"
        );
        authService.registerUser(
                "john",
                "John Doe",
                "5678"
        );
        assertEquals(2, authService.getAllUsers().size());
    }

    @Test
    public void testRegisterDuplicateUsername() {
        authService.registerUser(
                "kay",
                "Asemudara",
                "1234"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> authService.registerUser(
                        "kay",
                        "Another User",
                        "1111"
                )
        );
    }

    @Test
    public void testSuccessfulLogin() {
        authService.registerUser(
                "kay",
                "Asemudara",
                "1234"
        );
        authService.login(
                "kay",
                "1234"
        );
        assertTrue(authService.isLoggedIn());
    }

    @Test
    public void testLoginWithWrongPassword() {
        authService.registerUser(
                "kay",
                "Asemudara",
                "1234"
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> authService.login(
                        "kay",
                        "9999"
                )
        );
    }

    @Test
    public void testLoginWithUnknownUsername() {
        assertThrows(
                IllegalArgumentException.class,
                () -> authService.login(
                        "unknown",
                        "1234"
                )
        );
    }

    @Test
    public void testLogout() {
        authService.registerUser(
                "kay",
                "Asemudara",
                "1234"
        );
        authService.login(
                "kay",
                "1234"
        );
        authService.logout();
        assertFalse(authService.isLoggedIn());
    }
}