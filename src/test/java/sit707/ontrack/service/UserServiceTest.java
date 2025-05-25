package sit707.ontrack.service;

import sit707.ontrack.entity.User;
import sit707.ontrack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Spy
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private User user1;
    private User user2;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        user1 = new User(
                "Shruthi01",
                "shruthi.narayanan@deakin.aus",
                "Shruthi Narayanan",
                "LECTURE"
        );
        user2 = new User(
                "Kaushi01",
                "kaushi.rajapaksha@deakin.aus",
                "Kaushalya Rajapaksha",
                "STUDENT"
        );
    }

    @Test
    @DisplayName("UST-01 - Find User Successful")
    void findUserSuccessful() {
        String userId = "Kaushi01";
        when(userRepository.findById(userId)).thenReturn(Optional.of(user2));
        Optional<User> result = userService.findUser(userId);
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
    }
    @Test
    @DisplayName("UST-02 - Find User - User ID Parameters Empty And Null - Throw Exception")
    public void findUserThrowExceptionEmptyAndNull() {
        String userIdNull = null;
        String userIdEmpty = "";
        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> {
            userService.findUser(userIdEmpty);
        });
        assertEquals("User ID parameters can't be null value", exceptionEmpty.getMessage());
        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> {
            userService.findUser(userIdNull);
        });
        assertEquals("User ID parameters can't be null value", exceptionNull.getMessage());
    }
    @Test
    @DisplayName("UST-03 - Find Status Successful")
    public void userStatusSuccessful() {
        String userId = "Kaushi01";
        when(userRepository.findById(userId)).thenReturn(Optional.of(user2));
        ResponseEntity<String> response = userService.userStatus(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("STUDENT", response.getBody());
    }
    @Test
    @DisplayName("UST-04 - Find User Status - User ID Parameters Empty And Null - Throw Exception")
    public void userStatusThrowExceptionEmptyAndNull() {
        String userIdNull = null;
        String userIdEmpty = "";
        ResponseEntity<String> responseNull = userService.userStatus(userIdNull);
        assertEquals(HttpStatus.BAD_REQUEST, responseNull.getStatusCode());
        assertEquals("User ID not given", responseNull.getBody());
        ResponseEntity<String> responseEmpty = userService.userStatus(userIdEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, responseEmpty.getStatusCode());
        assertEquals("User ID not given", responseEmpty.getBody());
    }
    @Test
    @DisplayName("UST-05 - Find User Status - Not Found")
    public void userStatusNotFound() {
        String userId = "Kaushi02";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = userService.userStatus(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User Not Found", response.getBody());
    }
    @Test
    @DisplayName("UST-06 - User Validation Successful")
    public void userValidationSuccessful() {
        String userId = "Kaushi01";
        when(userRepository.findById(userId)).thenReturn(Optional.of(user2));
        ResponseEntity<String> response = userService.userValidation(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userId, response.getBody());
    }
    @Test
    @DisplayName("UST-07 - User Validation Successful - User ID Parameters Empty And Null")
    public void userValidationNullAndEmpty(){
        String userIdNull = null;
        String userIdEmpty = "";
        ResponseEntity<String> responseNull = userService.userValidation(userIdNull);
        assertEquals(HttpStatus.BAD_REQUEST, responseNull.getStatusCode());
        assertEquals("User ID not given", responseNull.getBody());
        ResponseEntity<String> responseEmpty = userService.userValidation(userIdEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, responseEmpty.getStatusCode());
        assertEquals("User ID not given", responseEmpty.getBody());
    }
    @Test
    @DisplayName("UST-08 - Find User Validation - Not Found")
    public void userValidationNotFound(){
        String userId = "Kaushi02";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        ResponseEntity<String> messageIdValidation = userService.userValidation(userId);
        assertEquals(HttpStatus.NOT_FOUND, messageIdValidation.getStatusCode());
        assertEquals("User Not Found", messageIdValidation.getBody());
    }
    @Test
    @DisplayName("UST-09 - Save User Successful")
    public void saveUserSuccessful(){
        when(userRepository.save(any(User.class))).thenReturn(user1);
        ResponseEntity<String> response1 = userService.save(user1);
        ResponseEntity<String> response2 = userService.save(user2);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals("User save successfully", response1.getBody());
        verify(userRepository).save(user1);
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals("User save successfully", response2.getBody());
        verify(userRepository).save(user2);
    }
    @Test
    @DisplayName("UST-10 - Save User Null Object")
    public void saveUserNullObject(){
        User user3 = null;
        ResponseEntity<String> response = userService.save(user3);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not given", response.getBody());
        verify(userRepository, never()).save(any());
    }
    @Test
    @DisplayName("UST-11 - Save User function DB Simulation")
    public void saveUserErrorDB(){
        when(userRepository.save(any())).thenThrow(new RuntimeException("Creat Query Error - User Table"));
        User User = new User();
        ResponseEntity<String> response = userService.save(User);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Creat Query Error - User Table", response.getBody());
    }
}