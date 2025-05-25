package sit707.ontrack.service;

import sit707.ontrack.entity.Message;
import sit707.ontrack.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {
    @Mock
    private MessageRepository messageRepository;
    @Spy
    private MessageService messageServiceSpy;
    @Spy
    @InjectMocks
    private MessageService messageService;
    private Message message1;
    private Message message2;
    private final Message message3 = null;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        message1 = new Message(
                "Message-Kaushi01",
                "Kaushi01",
                "Kaushalya Rajapaksha",
                "STUDENT",
                false,
                "Happy Cording",
                Instant.now().minusSeconds(150),
                "SQT707-1.2C",
                "SQT - Unit Testing",
                "SQT-707",
                "Software Quality and Testing",
                "Shruthi01"
        );
        message2 = new Message(
                "Message-Shruthi01",
                "Shruthi01",
                "Shruthi Narayanan",
                "LECTURE",
                true,
                "Happy Cording",
                Instant.parse("2026-11-11T01:06:45.368Z"),
                "SQT707-1.2C",
                "SQT - Unit Testing",
                "SQT-707",
                "Software Quality and Testing",
                "Kaushi01"
        );
    }
    @Test
    @DisplayName("MST-01 - Update Message Successful")
    public void updateMessageSuccessful(){
        String messageId = "Message-Kaushi01";
        String newMessageText = "Hello Kaushi";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        when(messageRepository.save(any(Message.class))).thenReturn(message1);
        doReturn(true).when(messageService).messageTimeValidation(messageId);
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, newMessageText);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Message updated successfully", response.getBody());
        verify(messageRepository).save(any(Message.class));
    }
    @Test
    @DisplayName("MST-02 - Update Throw exception - DB simulation")
    public void updateMessageThrowsException(){
        String messageId = "Message-Kaushi01";
        String newMessageText = "Hello Kaushi";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        doReturn(true).when(messageService).messageTimeValidation(messageId);
        when(messageRepository.save(any(Message.class))).thenThrow(new RuntimeException("Database error - update message failed"));
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, newMessageText);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Database error - update message failed", response.getBody());
    }
    @Test
    @DisplayName("MST-03 - Update Message Null Parameters")
    public void updateMessageNullValue(){
        String messageId = "Message-Kaushi01";
        String messageNew = null;
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, messageNew);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message is null", response.getBody());
    }
    @Test
    @DisplayName("MST-04 - Update Message Empty Parameters")
    public void updateMessageEmptyValue(){
        String messageId = "Message-Kaushi01";
        String messageNew = "";
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, messageNew);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message is null", response.getBody());
    }
    @Test
    @DisplayName("MST-05 - Update Message Not Found")
    public void updateMessageNotFound(){
        String messageId = "Message-Kaushi02";
        String messageText = "Hello Kaushi";
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, messageText);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message not found", response.getBody());
    }
    @Test
    @DisplayName("MST-06 - Update Message Expired ")
    public void updateTimeExpiredMessage() {
        String messageId = "Message-Kaushi01";
        String messageText = "Hello Kaushi";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        doReturn(false).when(messageService).messageTimeValidation(messageId);
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, messageText);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Message can't be update after 3 minutes", response.getBody());
    }
    @Test
    @DisplayName("MST-07 - Update Message function DB Simulation")
    public void updateMessageErrorDB(){
        String messageId = "Message-Kaushi01";
        String messageNew = "This DB Message table query error";
        message1.setMessageTime(Instant.now().minusSeconds(150)); // Valid time
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        when(messageRepository.save(any(Message.class)))
                .thenThrow(new RuntimeException("Update Query Error - Message Table"));
        ResponseEntity<String> response = messageService.updateMessageContent(messageId, messageNew);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Update Query Error - Message Table", response.getBody());
    }

    @Test
    @DisplayName("MST-08 - Delete Message Successful")
    public void deleteMessageSuccessful(){
        String messageId = "Message-Kaushi01";
        MessageService messageServiceSpy = spy(new MessageService());
        ReflectionTestUtils.setField(messageServiceSpy, "messageRepository", messageRepository);
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        doReturn(true).when(messageServiceSpy).messageTimeValidation(messageId);
        ResponseEntity<String> response = messageServiceSpy.deleteMessage(messageId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Message deleted successfully", response.getBody());
        verify(messageRepository).deleteById(messageId);
    }
    @Test
    @DisplayName("MST-09 - Delete Message Null Parameters")
    public void deleteMessageNull(){
        String messageId = null;
        ResponseEntity<String> response = messageService.deleteMessage(messageId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message is null", response.getBody());
    }
    @Test
    @DisplayName("MST-10 - Delete Message Empty Parameters")
    public void deleteMessageEmpty(){
        String messageId = "";
        ResponseEntity<String> response = messageService.deleteMessage(messageId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message is null", response.getBody());
    }
    @Test
    @DisplayName("MST-11 - Delete Message Not Found")
    public void deleteMessageNotFound(){
        String messageId = "Message-Kaushi02";
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = messageService.deleteMessage(messageId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message not found", response.getBody());
    }
    @Test
    @DisplayName("MST-12 - Delete Message - > 3 minutes - Delete Forbidden")
    public void deleteTimeExpiredMessage(){
        String messageId = "Message-Kaushi01";
        MessageService messageServiceSpy = spy(new MessageService());
        ReflectionTestUtils.setField(messageServiceSpy, "messageRepository", messageRepository);
        message1.setMessageTime(Instant.now().minus(Duration.ofMinutes(10)));
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        doReturn(false).when(messageServiceSpy).messageTimeValidation(messageId);
        ResponseEntity<String> response = messageServiceSpy.deleteMessage(messageId);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Message can't be delete after 3 minutes", response.getBody());
    }
    @Test
    @DisplayName("MST-13 - Delete Message function DB Simulation")
    public void deleteMessageThrowException(){
        String messageId = "Message-Kaushi01";
        MessageService messageServiceSpy = spy(new MessageService());
        ReflectionTestUtils.setField(messageServiceSpy, "messageRepository", messageRepository);
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        doReturn(true).when(messageServiceSpy).messageTimeValidation(messageId);
        doThrow(new RuntimeException("Delete Query Error - Message Table")).when(messageRepository).deleteById(messageId);
        ResponseEntity<String> response = messageServiceSpy.deleteMessage(messageId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Delete Query Error - Message Table", response.getBody());
    }
    @Test
    @DisplayName("MST-14 - Search Message Successful")
    public void searchMessageSuccessful(){
        String userId = "Kaushi01";
        String receiverId = "Shruthi01";
        String message = "Happy Cording";
        List<Message> messageList = List.of(message1);
        when(messageRepository.searchMessage(message, userId, receiverId)).thenReturn(messageList);
        List<Message> messageListResult = messageService.searchMessage(userId, receiverId, message);
        assertNotNull(messageListResult);
        assertEquals(1, messageListResult.size());
        assertEquals("Message-Kaushi01", messageListResult.get(0).getMessageId());
        assertEquals("Happy Cording", messageListResult.get(0).getMessage());
        assertEquals(userId, messageListResult.get(0).getUserId());
        assertEquals(receiverId, messageListResult.get(0).getReceiverId());
        verify(messageRepository).searchMessage(message, userId, receiverId);
    }
    @Test
    @DisplayName("MST-15 - Search Message Null Parameters")
    public void searchMessageNullObject(){
        String userId = null;
        String receiverId = "Shruthi01";
        String message = "Happy Cording";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.searchMessage(message, userId, receiverId);
        });
        assertEquals("Search parameters can't be null value", exception.getMessage());
        verify(messageRepository, never()).searchMessage(anyString(), anyString(), anyString());
    }
    @Test
    @DisplayName("MST-16 - Find All Message Successful")
    public void findAllMessageSuccessful(){
        String userId = "Kaushi01";
        String receiverId = "Shruthi01";
        String courseId = "Happy Cording";
        String assignmentId = "Happy Cording";
        List<Message> messageList = List.of(message1);
        when(messageRepository.findUserMessage(userId, receiverId, courseId, assignmentId)).thenReturn(messageList);
        List<Message> messageListResult = messageService.findAllMessages(userId, receiverId, courseId, assignmentId);
        assertNotNull(messageListResult);
        assertEquals(1, messageListResult.size());
        assertEquals("Message-Kaushi01", messageListResult.get(0).getMessageId());
        assertEquals("Happy Cording", messageListResult.get(0).getMessage());
        assertEquals(userId, messageListResult.get(0).getUserId());
        assertEquals(receiverId, messageListResult.get(0).getReceiverId());
        verify(messageRepository).findUserMessage(userId, receiverId, courseId, assignmentId);
    }
    @Test
    @DisplayName("MST-17 - Find All Message Null Parameters")
    public void findAllMessageNullObject(){
        String userId = null;
        String receiverId = "Shruthi01";
        String courseId = "Happy Cording";
        String assignmentId = "Happy Cording";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.findAllMessages(userId, receiverId, courseId, assignmentId);
        });
        assertEquals("Message review parameters can't be null value", exception.getMessage());
        verify(messageRepository, never()).findUserMessage(anyString(), anyString(), anyString(), anyString());
    }
    @Test
    @DisplayName("MST-18 - Find Message Successful")
    public void findMessageSuccessful(){
        String messageId = "Message-Kaushi01";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        Optional<Message> message = messageService.findMessage(messageId);
        assertTrue(message.isPresent());
        assertEquals("Message-Kaushi01", message.get().getMessageId());
        assertEquals("Happy Cording", message.get().getMessage());
        assertEquals(messageId, message.get().getMessageId());
        assertEquals(messageId, message.get().getMessageId());
        verify(messageRepository).findById(messageId);
    }
    @Test
    @DisplayName("MST-19 - Find Message Null Parameters")
    public void findMessageNullObject(){
        String messageId = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            messageService.findMessage(messageId);
        });
        assertEquals("Message ID parameters can't be null value", exception.getMessage());
        verify(messageRepository, never()).findById(anyString());
    }
    @Test
    @DisplayName("MST-20 - Message ID Validation Successful")
    public void messageIdValidationSuccessful(){
        String messageId = "Message-Kaushi01";
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message1));
        ResponseEntity<String> response = messageService.messageIdValidation(messageId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageId, response.getBody());
    }
    @Test
    @DisplayName("MST-21 - Message ID Validation Null Parameters")
    public void messageIdValidationNullObject(){
        String messageIdNull = null;
        String messageIdEmpty = "";
        ResponseEntity<String> responseNull = messageService.messageIdValidation(messageIdNull);
        assertEquals(HttpStatus.BAD_REQUEST, responseNull.getStatusCode());
        assertEquals("Message Id not given", responseNull.getBody());
        ResponseEntity<String> responseEmpty = messageService.messageIdValidation(messageIdEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, responseEmpty.getStatusCode());
        assertEquals("Message Id not given", responseEmpty.getBody());
    }
    @Test
    @DisplayName("MST-22 - Message ID Validation Not Found")
    public void messageIdValidationNotFound(){
        String messageId = "Message-Kaushi02";
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());
        ResponseEntity<String> messageIdValidation = messageService.messageIdValidation(messageId);
        assertEquals(HttpStatus.NOT_FOUND, messageIdValidation.getStatusCode());
        assertEquals("Message not found", messageIdValidation.getBody());
    }
    @Test
    @DisplayName("MST-23 - Message Edit time Validation Successful <= 3")
    public void messageTimeValidationWithThreeAndLess(){
        String messageId = "Message-Kaushi01";
        Instant recentTimeLess = Instant.now().minus(Duration.ofMinutes(2));
        doReturn(recentTimeLess).when(messageServiceSpy).messageDate(messageId);
        boolean resultLess = messageServiceSpy.messageTimeValidation(messageId);
        assertTrue(resultLess);
        Instant recentTimeEqually = Instant.now().minus(Duration.ofMinutes(3));
        doReturn(recentTimeEqually).when(messageServiceSpy).messageDate(messageId);
        boolean resultEqually = messageServiceSpy.messageTimeValidation(messageId);
        assertTrue(resultEqually);
    }
    @Test
    @DisplayName("MST-24 - Message Edit time Validation Unsuccessful > 3")
    public void messageTimeValidationGraterThanThree(){
        String messageId = "Message-Kaushi01";
        Instant recentTime = Instant.now().minus(Duration.ofMinutes(5));
        doReturn(recentTime).when(messageServiceSpy).messageDate(messageId);
        boolean result = messageServiceSpy.messageTimeValidation(messageId);
        assertFalse(result);
        Instant recentTime1 = Instant.now().minus(Duration.ofMinutes(4));
        doReturn(recentTime1).when(messageServiceSpy).messageDate(messageId);
        boolean result2 = messageServiceSpy.messageTimeValidation(messageId);
        assertFalse(result2);
    }
    @Test
    @DisplayName("MST-25 - Find Message Date and time Successful")
    public void messageExitDate(){
        String messageId = "Message-Kaushi01";
        Instant recentTime = Instant.now();
        message1.setMessageTime(recentTime);
        doReturn(Optional.of(message1)).when(messageServiceSpy).findMessage(messageId);
        Instant messageDate = messageServiceSpy.messageDate(messageId);
        assertNotNull(messageDate);
        assertEquals(recentTime, messageDate);
    }
    @Test
    @DisplayName("MST-26 - Find Message Date and time - Non Exit Message")
    public void messageNonExitDate(){
        String messageId = "Message-Kaushi01";
        doReturn(Optional.empty()).when(messageServiceSpy).findMessage(messageId);
        Instant messageDate = messageServiceSpy.messageDate(messageId);
        assertNull(messageDate);
    }
    @Test
    @DisplayName("MST-27 - Save Message Successful")
    public void saveMessageSuccessful(){
        when(messageRepository.save(any(Message.class))).thenReturn(message1);
        ResponseEntity<String> response1 = messageService.save(message1);
        ResponseEntity<String> response2 = messageService.save(message2);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals("Message save successfully", response1.getBody());
        verify(messageRepository).save(message1);
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals("Message save successfully", response2.getBody());
        verify(messageRepository).save(message2);
    }
    @Test
    @DisplayName("MST-28 - Save Message Null Object")
    public void saveMessageNullObject(){
        ResponseEntity<String> response = messageService.save(message3);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message not given", response.getBody());
        verify(messageRepository, never()).save(any());
    }
    @Test
    @DisplayName("MST-29 - Save Message function DB Simulation")
    public void saveMessageErrorDB(){
        when(messageRepository.save(any())).thenThrow(new RuntimeException("Creat Query Error - Message Table"));
        Message message = new Message();
        ResponseEntity<String> response = messageService.save(message);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Creat Query Error - Message Table", response.getBody());
    }
}