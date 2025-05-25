package sit707.ontrack.controller;

import sit707.ontrack.entity.Message;
import sit707.ontrack.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PutMapping(value = "/{messageId}/{message}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("messageId") String messageId, @PathVariable("message") String message ) {
        try{
            ResponseEntity<String> response = messageService.updateMessageContent(messageId, message);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message Service returned null response - Update failed");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - Update failed");
        }
    }

    @DeleteMapping("/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("messageId") String messageId) {
        try{
            ResponseEntity<String> response = messageService.deleteMessage(messageId);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message Service returned null response - Delete failed");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - Delete failed");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getMessageForAssignment(@RequestParam("userId") String userId, @RequestParam("receiverId") String receiverId,
                                                     @RequestParam("courseId")String courseId, @RequestParam("assignmentId")String assignmentId){
        try {
            List<Message> messagesList = messageService.findAllMessages(userId, receiverId, courseId, assignmentId);
            if (messagesList == null || messagesList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found - Null value returned from getMessageForAssignment method");
            }
            return ResponseEntity.status(HttpStatus.OK).body(messagesList);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - GetMessageForAssignment failed");
        }
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> search(@RequestParam("userId") String userId, @RequestParam("receiverId") String receiverId, @RequestParam("message") String message) {
        try {
            List<Message> messagesList = messageService.searchMessage(userId, receiverId, message);
            if (messagesList == null || messagesList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No messages found - Null value returned from searchMessage method");
            }
            return ResponseEntity.status(HttpStatus.OK).body(messagesList);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - searchMessage failed");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Message message ) {
        try{
            ResponseEntity<String> response = messageService.save(message);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Message Service returned null response - Save failed");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - Save failed");
        }
    }
}
