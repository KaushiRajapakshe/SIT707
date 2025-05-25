package sit707.ontrack.service;

import sit707.ontrack.entity.User;
import sit707.ontrack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class UserService {

    UserRepository userRepository;

    public Optional<User> findUser(String userId){
        if(userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID parameters can't be null value");
        }
        return userRepository.findById(userId);
    }

    public ResponseEntity<String> userStatus(String userId){
        if(userId == null || userId.isEmpty()) return ResponseEntity.badRequest().body("User ID not given");
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(user.getStatus()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found"));
    }

    public ResponseEntity<String> userValidation(String userId){

        if(userId == null || userId.isEmpty()) return ResponseEntity.badRequest().body("User ID not given");
        ResponseEntity<String> findUserId=  findUser(userId)
                .map(user -> ResponseEntity.ok(user.getUserId()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found"));

        if(!findUserId.getBody().equals(userId)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");

        return findUserId;
    }

    public ResponseEntity<String> save(User user){
        if(user == null) return ResponseEntity.badRequest().body("User not given");
        try{
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User save successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
