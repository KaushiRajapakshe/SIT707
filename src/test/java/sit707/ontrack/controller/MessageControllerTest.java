package sit707.ontrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import sit707.ontrack.entity.Message;
import sit707.ontrack.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
class MessageControllerTest {
    @MockitoBean
    MessageService messageService;
    @Autowired
    MockMvc mockMvc;
    private Message message1;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        message1 = new Message(
                "Message-Kaushi01",
                "Kaushi01",
                "Kaushalya Rajapaksha",
                "STUDENT",
                false,
                "Hello World",
                Instant.now().minusSeconds(150),
                "SQT707-1.2C",
                "SQT - Unit Testing",
                "SQT-707",
                "Software Quality and Testing",
                "Shruthi01"
        );
    }
    @Test
    @DisplayName("MCT-01 - Update Rest API - Happy Path")
    void updateSuccessFull() throws Exception {
        String message = "Hello";
        String messageId = "Message-Kaushi01";
        String messageEncode = URLEncoder.encode(message, StandardCharsets.UTF_8);

        when(messageService.updateMessageContent(eq(messageId), eq("Hello")))
                .thenReturn(ResponseEntity.ok("Message updated successfully"));
        mockMvc.perform(put("/api/v1/message/" + messageId + "/" + messageEncode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Message updated successfully"));
        verify(messageService).updateMessageContent(messageId, message);
    }
    @Test
    @DisplayName("MCT-02 - Update Rest API - Internal Server Error")
    void updateInternalServerError() throws Exception {
        String rawMessage = "Hello";
        String messageEncode = URLEncoder.encode(rawMessage, StandardCharsets.UTF_8);
        String messageId = "Message-Kaushi01";
        when(messageService.updateMessageContent(eq(messageId), eq(rawMessage)))
                .thenThrow(new RuntimeException("Internal Service error - Update failed"));
        mockMvc.perform(put("/api/v1/message/" + messageId + "/" + messageEncode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Service error - Update failed"));
        verify(messageService).updateMessageContent(messageId, rawMessage);
    }
    @Test
    @DisplayName("MCT-03 - Update Rest API - PathVariable Null value pass")
    void updateNull() throws Exception {
        String rawMessage = "Hello";
        String messageEncode = URLEncoder.encode(rawMessage, StandardCharsets.UTF_8);
        String messageId = "Message-Kaushi01";
        when(messageService.updateMessageContent(eq(messageId), eq(messageEncode)))
                .thenReturn(null);
        mockMvc.perform(put("/api/v1/message/" + messageId + "/" + messageEncode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Message Service returned null response - Update failed"));
        verify(messageService).updateMessageContent(messageId, messageEncode);
    }
    @Test
    @DisplayName("MCT-04 - Delete Rest API - Happy Path")
    void deleteSuccessFull() throws Exception {
        String messageId = "Message-Kaushi01";
        when(messageService.deleteMessage(eq(messageId)))
                .thenReturn(ResponseEntity.ok("Message deleted successfully"));
        mockMvc.perform(delete("/api/v1/message/" + messageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Message deleted successfully"));
        verify(messageService).deleteMessage(messageId);
    }
    @Test
    @DisplayName("MCT-05 - Delete Rest API - Internal Server Error")
    void deleteInternalServerError() throws Exception {
        String messageId = "Message-Kaushi01";
        when(messageService.deleteMessage(eq(messageId)))
                .thenThrow(new RuntimeException("Internal Service error - Delete failed"));
        mockMvc.perform(delete("/api/v1/message/" + messageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Service error - Delete failed"));
        verify(messageService).deleteMessage(messageId);
    }
    @Test
    @DisplayName("MCT-06 - Delete Rest API - PathVariable Null value pass")
    void deleteNull() throws Exception {
        String messageId = "Message-Kaushi01";
        when(messageService.deleteMessage(eq(messageId)))
                .thenReturn(null);
        mockMvc.perform(delete("/api/v1/message/" + messageId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Message Service returned null response - Delete failed"));
        verify(messageService).deleteMessage(messageId);
    }
    @Test
    @DisplayName("MCT-07 - Get All Message For Assignment Rest API - Happy Path")
    void getMessageForAssignmentSuccessful() throws Exception {
        String userId = "Kaushi01";
        String assignmentId = "SQT707-1.2C";
        String receiverId = "Shruthi01";
        String courseId = "SQT-707";
        when(messageService.findAllMessages(userId, receiverId, courseId, assignmentId))
                .thenReturn(List.of(message1));
        mockMvc.perform(get("/api/v1/message")
                .param("userId", userId)
                .param("receiverId", receiverId)
                .param("courseId", courseId)
                .param("assignmentId", assignmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].messageId").value("Message-Kaushi01"))
                .andExpect(jsonPath("$[0].message").value("Hello World"));
    }
    @Test
    @DisplayName("MCT-08 - Get All Message For Assignment Rest API - Internal Server Error")
    void getMessageForAssignmentInternalServerError() throws Exception {
        String userId = "Kaushi01";
        String assignmentId = "SQT707-1.2C";
        String receiverId = "Shruthi01";
        String courseId = "SQT-707";
        when(messageService.findAllMessages(userId, receiverId, courseId, assignmentId)).thenReturn(List.of());
        mockMvc.perform(get("/api/v1/message")
                .param("userId", userId)
                .param("receiverId", receiverId)
                .param("courseId", courseId)
                .param("assignmentId", assignmentId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No messages found - Null value returned from getMessageForAssignment method"));
    }
    @Test
    @DisplayName("MCT-09 - Get All Message For Assignment Rest API - Bad Request")
    void getMessageForAssignmentBadRequest() throws Exception {
        String userId = "";
        String assignmentId = "SQT707-1.2C";
        String receiverId = " ";
        String courseId = "SQT-707";
        when(messageService.findAllMessages(userId, receiverId, courseId, assignmentId))
                .thenThrow(new IllegalArgumentException("Message review parameters can't be null value"));
        mockMvc.perform(get("/api/v1/message")
                .param("userId", userId)
                .param("receiverId", receiverId)
                .param("courseId", courseId)
                .param("assignmentId", assignmentId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Message review parameters can't be null value"));
    }
    @Test
    @DisplayName("MCT-10 - Get All Message For Assignment Rest API - Not Found")
    void getMessageForAssignmentNotFound() throws Exception {
        String userId = "Kaushi02";
        String assignmentId = "SQT707-1.2C";
        String receiverId = "Shruthi01";
        String courseId = "SQT-707";
        when(messageService.findAllMessages(userId, receiverId, courseId, assignmentId))
                .thenThrow(new IllegalArgumentException("No messages found - Null value returned from getMessageForAssignment method"));
        mockMvc.perform(get("/api/v1/message")
                        .param("userId", userId)
                        .param("receiverId", receiverId)
                        .param("courseId", courseId)
                        .param("assignmentId", assignmentId))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No messages found - Null value returned from getMessageForAssignment method"));
    }
    @Test
    @DisplayName("MCT-11 - Search Message Rest API - Happy Path")
    void searchMessageSuccessful() throws Exception {
        String userId = "Kaushi01";
        String receiverId = "Shruthi01";
        String message = "Hello World";

        when(messageService.searchMessage(userId, receiverId, message))
                .thenReturn(List.of(message1));
        mockMvc.perform(get("/api/v1/message/search")
                        .param("userId", userId)
                        .param("receiverId", receiverId)
                        .param("message", message))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].messageId").value("Message-Kaushi01"))
                .andExpect(jsonPath("$[0].message").value("Hello World"));
    }
    @Test
    @DisplayName("MCT-12 - Search Message Rest API - Internal Server Error")
    void searchMessageInternalServerError() throws Exception {
        String userId = "Kaushi01";
        String message = "Hello";
        String receiverId = "Shruthi01";
        when(messageService.searchMessage(userId, receiverId, message)).thenReturn(List.of());
        mockMvc.perform(get("/api/v1/message/search")
                        .param("userId", userId)
                        .param("receiverId", receiverId)
                        .param("message", message))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No messages found - Null value returned from searchMessage method"));
    }
    @Test
    @DisplayName("MCT-13 - Search Message Rest API - Bad Request")
    void searchMessageBadRequest() throws Exception {
        String userId = "";
        String message = "Hello";
        String receiverId = " ";
        when(messageService.searchMessage(userId, receiverId, message))
                .thenThrow(new IllegalArgumentException("Search parameters can't be null value"));
        mockMvc.perform(get("/api/v1/message/search")
                        .param("userId", userId)
                        .param("receiverId", receiverId)
                        .param("message", message))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Search parameters can't be null value"));
    }
    @Test
    @DisplayName("MCT-14 - Search Message Rest API - Not Found")
    void searchMessageNotFound() throws Exception {
        String userId = "Kaushi02";
        String message = "Hello";
        String receiverId = "Shruthi01";

        when(messageService.searchMessage(userId, receiverId, message))
                .thenThrow(new IllegalArgumentException("No messages found - Null value returned from searchMessage method"));
        mockMvc.perform(get("/api/v1/message/search")
                        .param("userId", userId)
                        .param("receiverId", receiverId)
                        .param("message", message))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No messages found - Null value returned from searchMessage method"));
    }
    @Test
    @DisplayName("MCT-15 - Save Rest API - Happy Path")
    void saveSuccessFull() throws Exception {
        when(messageService.save(any(Message.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Message save successfully"));
        mockMvc.perform(post("/api/v1/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectIntoJSON(message1))
                )
                .andExpect(status().isCreated())
                .andExpect(content().string("Message save successfully"));
        verify(messageService).save(any(Message.class));
    }
    @Test
    @DisplayName("MCT-16 - Save Rest API - Internal Server Error")
    void saveInternalServerError() throws Exception {
        when(messageService.save(any(Message.class)))
                .thenThrow(new RuntimeException("Internal Service error - Save failed"));

        mockMvc.perform(post("/api/v1/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectIntoJSON(message1)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Service error - Save failed"));

    }
    @Test
    @DisplayName("MCT-017 - Save Rest API - Request Body Null value pass")
    void saveNull() throws Exception {
        Message message3 = new Message();
        mockMvc.perform(post("/api/v1/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectIntoJSON(message3)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Message Service returned null response - Save failed"));
    }
    private static String convertObjectIntoJSON(final Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}