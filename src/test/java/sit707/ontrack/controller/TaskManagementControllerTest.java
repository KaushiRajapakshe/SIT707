package sit707.ontrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import sit707.ontrack.entity.Course;
import sit707.ontrack.service.CourseService;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskManagementController.class)
class TaskManagementControllerTest {

    @MockitoBean
    CourseService courseService;
    @Autowired
    MockMvc mockMvc;
    private Course course;
    private Course course2;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        course = new Course(
                "SQT707-1.2C",
                "SQT - Unit Testing",
                "SQT-707",
                "Software Quality and Testing",
                "2024-06-03",
                "MSC",
                "First",
                "Shruthi01",
                "First2023",
                "2"
        );
        course2 = new Course(
                "SQT707-1.3C",
                "SQT - Unit Testing",
                "SQT-708",
                "Software Quality and Testing",
                "2024-07-03",
                "MSC",
                "Second",
                "Shruthi01",
                "First2024",
                "4"
        );
    }
    @Test
    @DisplayName("TCT-01 - Update Rest API - Happy Path")
    void updateSuccessFull() throws Exception {
        String assignmentId = "SQT707-1.2C";
        String deadlineDate = "2024-01-18";
        when(courseService.updateCourseAssignmentDueDate(assignmentId, deadlineDate))
                .thenReturn(ResponseEntity.ok("Assignment update - successfully"));
        mockMvc.perform(put("/api/v1/task/"+assignmentId+"/"+deadlineDate))
                .andExpect(status().isOk())
                .andExpect(content().string("Assignment update - successfully"));
    }
    @Test
    @DisplayName("TCT-02 - Update Rest API - Not Found")
    void updateNotFound() throws Exception {
        String assignmentId = "SQT707-1.2B";
        String deadlineDate = "2023-07-23";
        when(courseService.updateCourseAssignmentDueDate(assignmentId, deadlineDate))
                .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment update - Not found"));
        mockMvc.perform(put("/api/v1/task/"+assignmentId+"/"+deadlineDate))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Assignment update - Not found"));
    }
    @Test
    @DisplayName("TCT-03 - Update Rest API - PathVariable Null value pass")
    void updateNull() throws Exception {
        String assignmentId = " ";
        String deadlineDate = " ";
        when(courseService.updateCourseAssignmentDueDate(assignmentId, deadlineDate))
                .thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Assignment update - Due date is Null, Empty, Invalidate, Incorrect"));
        mockMvc.perform(put("/api/v1/task/"+assignmentId+"/"+deadlineDate))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Assignment update - Due date is Null, Empty, Invalidate, Incorrect"));
    }
    @Test
    @DisplayName("TCT-04 - Update Rest API - Internal Server Error")
    void updateInternalServerError() throws Exception {
        String assignmentId = "SQT707-1.2C";
        String deadlineDate = "2023-07-23";
        when(courseService.updateCourseAssignmentDueDate(assignmentId, deadlineDate))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment update - Internal Server Error"));
        mockMvc.perform(put("/api/v1/task/"+assignmentId+"/"+deadlineDate))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Assignment update - Internal Server Error"));
    }
    @Test
    @DisplayName("TCT-05 - Delete Rest API - Happy Path")
    void deleteSuccessFull() throws Exception {
        String assignmentId = "SQT707-1.2C";
        when(courseService.deleteCourseAssignment(eq(assignmentId)))
                .thenReturn(ResponseEntity.ok("Course deleted successfully"));
        mockMvc.perform(delete("/api/v1/task/" + assignmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Course deleted successfully"));
        verify(courseService).deleteCourseAssignment(assignmentId);
    }
    @Test
    @DisplayName("TCT-06 - Delete Rest API - Internal Server Error")
    void deleteInternalServerError() throws Exception {
        String assignmentId = "SQT707-1.2C";
        when(courseService.deleteCourseAssignment(eq(assignmentId)))
                .thenThrow(new RuntimeException("Internal Service error - Delete failed"));
        mockMvc.perform(delete("/api/v1/task/" + assignmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Service error - Delete failed"));
        verify(courseService).deleteCourseAssignment(assignmentId);
    }
    @Test
    @DisplayName("TCT-07 - Delete Rest API - PathVariable Null value pass")
    void deleteNull() throws Exception {
        String assignmentId = "SQT707-1.2C";
        when(courseService.deleteCourseAssignment(eq(assignmentId)))
                .thenReturn(null);
        mockMvc.perform(delete("/api/v1/task/" + assignmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Course Service returned null response - Delete failed"));
        verify(courseService).deleteCourseAssignment(assignmentId);
    }

    @Test
    @DisplayName("TCT-08 - GetMessageForAssignment Rest API - Happy Path")
    void getMessageForAssignmentSuccessFull() throws Exception {
        String assignmentId = "SQT707-1.2C";
        when(courseService.findAssignment(assignmentId)).thenReturn(Optional.of(course));
        mockMvc.perform(get("/api/v1/task/" + assignmentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    @DisplayName("MCT-09 - GetMessageForAssignment Rest API - Not Found")
    void getMessageForAssignmentNotFound() throws Exception {
        String assignmentId = "SQT708-1.2B";
        when(courseService.findAssignment(assignmentId)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/task/" + assignmentId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("GetMessageForAssignment - Not Found"));
    }
    @Test
    @DisplayName("TCT-10 - GetMessageForAssignment Rest API - PathVariable Null value pass")
    void getMessageForAssignmentNull() throws Exception {
        when(courseService.findAssignment(null)).thenThrow(new IllegalArgumentException("Assignment ID parameters can't be null value"));
        mockMvc.perform(get("/api/v1/task/"))
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("TCT-11 - GetMessageForAssignment Rest API - Internal Server Error")
    void getMessageForAssignmentInternalServerError() throws Exception {
        String assignmentId = "SQT708-1.2B";
        when(courseService.findAssignment(assignmentId)).thenThrow(new RuntimeException("GetMessageForAssignment - Internal Service Error"));
        mockMvc.perform(get("/api/v1/task/" + assignmentId))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("GetMessageForAssignment - Internal Service Error"));
    }
    @Test
    @DisplayName("TCT-12 - Save Rest API - Happy Path")
    void saveSuccessFull() throws Exception {
        when(courseService.save(any(Course.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Assignment save successful"));
        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectIntoJSON(course))
                )
                .andExpect(status().isCreated())
                .andExpect(content().string("Assignment save successful"));
        verify(courseService).save(any(Course.class));
    }
    @Test
    @DisplayName("TCT-13 - Save Rest API - Internal Server Error")
    void saveInternalServerError() throws Exception {
        when(courseService.save(any(Course.class)))
                .thenThrow(new RuntimeException("Internal Service error - failed to Save course"));
        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectIntoJSON(course)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Service error - failed to Save course"));

    }
    @Test
    @DisplayName("TCT-14 - Save Rest API - Request Body Null value pass")
    void saveNull() throws Exception {
        Course course1 = new Course();
        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectIntoJSON(course1)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Course Service returned null response - Save failed"));
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