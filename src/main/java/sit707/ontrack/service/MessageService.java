package sit707.ontrack.service;

import sit707.ontrack.entity.Message;
import sit707.ontrack.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
public class MessageService {

    MessageRepository messageRepository;

    public ResponseEntity<String> updateMessageContent(String messageId, String message) {
            if(message == null || message.isEmpty()){
                return ResponseEntity.badRequest().body("Message is null");
            }
            Optional<Message> oldMessage = findMessage(messageId);
            if(oldMessage.isEmpty()){
                return ResponseEntity.badRequest().body("Message not found");
            }
            if( messageTimeValidation(messageId)) {
                Message oldMessageObject = oldMessage.get();
                oldMessageObject.setMessage(message);
                oldMessageObject.setMessageTime(Instant.now());
                try{
                    messageRepository.save(oldMessageObject);
                    return ResponseEntity.ok().body("Message updated successfully");
                }catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                }
            }else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Message can't be update after 3 minutes");
            }
    }

    public ResponseEntity<String> deleteMessage(String messageId) {
        if(messageId == null || messageId.isEmpty()){
            return ResponseEntity.badRequest().body("Message is null");
        }
        Optional<Message> oldMessage = findMessage(messageId);
        if(oldMessage.isEmpty()){
            return ResponseEntity.badRequest().body("Message not found");
        }
        if(!messageTimeValidation(messageId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Message can't be delete after 3 minutes");
        }
        try{
            messageRepository.deleteById(messageId);
            return ResponseEntity.ok().body("Message deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public Optional<Message> findMessage(String messageId) {
        if(messageId == null || messageId.isEmpty()) {
            throw new IllegalArgumentException("Message ID parameters can't be null value");
        }
        return messageRepository.findById(messageId);
    }

    public ResponseEntity<String> messageIdValidation(String messageId) {
        if(messageId == null|| messageId.isEmpty()) {
            return ResponseEntity.badRequest().body("Message Id not given");
        }
        ResponseEntity<String> findMessageId =  findMessage(messageId)
                .map(message -> ResponseEntity.ok(message.getMessageId()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found"));
        if(!findMessageId.getBody().equals(messageId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
        }
        return findMessageId;
    }

    public boolean messageTimeValidation(String messageId) {
        Instant findMessageTime =  messageDate(messageId);
        return Duration.between(findMessageTime, Instant.now()).toMinutes() <= 3;
    }

    public Instant messageDate(String messageId){
        Optional<Message> messageOptional = findMessage(messageId);
        return messageOptional.map(Message::getMessageTime).orElse(null);
    }

    public List<Message> findAllMessages(String userId, String receiverId,
                                         String courseId, String assignmentId) {
        if(courseId == null || courseId.isEmpty() || userId == null || userId.isEmpty() || receiverId == null || receiverId.isEmpty() || assignmentId == null || assignmentId.isEmpty()) {
            throw new IllegalArgumentException("Message review parameters can't be null value");
        }
        return messageRepository.findUserMessage(userId, receiverId, courseId, assignmentId);
    }
    public List<Message> searchMessage(String userId, String receiverId, String message){
        if(message == null || message.isEmpty() || userId == null || userId.isEmpty() || receiverId == null || receiverId.isEmpty()){
            throw new IllegalArgumentException("Search parameters can't be null value");
        }
        return messageRepository.searchMessage(message, userId, receiverId);
    }

    public ResponseEntity<String> save(Message message){
        if(message == null) {
            return ResponseEntity.badRequest().body("Message not given");
        }
        try{
            messageRepository.save(message);
            return ResponseEntity.status(HttpStatus.CREATED).body("Message save successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
