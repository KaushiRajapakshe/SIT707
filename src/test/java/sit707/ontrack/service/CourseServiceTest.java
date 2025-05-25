package sit707.ontrack.service;

import sit707.ontrack.entity.Course;
import sit707.ontrack.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Spy
    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
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
    @DisplayName("CST-01 - Update Assignment Due Date Null And Empty Assignment ID")
    public void updateCourseAssignmentDueDateNullAndEmpty_assignmentId() {
        String assignmentIdNull = null;
        String assignmentIdEmpty = "";
        String dueDate = "2025-02-28";
        ResponseEntity<String> responseNull = courseService.updateCourseAssignmentDueDate(assignmentIdNull, dueDate);
        assertEquals(HttpStatus.BAD_REQUEST, responseNull.getStatusCode());
        assertEquals("Due date is empty", responseNull.getBody());
        ResponseEntity<String> responseEmpty = courseService.updateCourseAssignmentDueDate(assignmentIdEmpty, dueDate);
        assertEquals(HttpStatus.BAD_REQUEST, responseEmpty.getStatusCode());
        assertEquals("Due date is empty", responseEmpty.getBody());
    }
    @Test
    @DisplayName("CST-02 - Update Assignment Due Date Null And Empty Due Date")
    public void updateCourseAssignmentDueDateNullAndEmpty_dueDate() {
        String dueDateNull = null;
        String dueDateEmpty = "";
        String assignmentId = "SQT707-1.2C";
        ResponseEntity<String> responseNull = courseService.updateCourseAssignmentDueDate(assignmentId, dueDateNull);
        assertEquals(HttpStatus.BAD_REQUEST, responseNull.getStatusCode());
        assertEquals("Due date is empty", responseNull.getBody());
        ResponseEntity<String> responseEmpty = courseService.updateCourseAssignmentDueDate(assignmentId, dueDateEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, responseEmpty.getStatusCode());
        assertEquals("Due date is empty", responseEmpty.getBody());
    }
    @Test
    @DisplayName("CST-03 - Update Assignment Due Date Successful")
    public void updateCourseAssignmentDueDateSuccessful(){
        String assignmentId = "SQT707-1.2C";
        String dueDateNew = "2025-03-13";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ResponseEntity<String> response = courseService.updateCourseAssignmentDueDate(assignmentId, dueDateNew);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Assignment updated successfully", response.getBody());
        assertEquals(dueDateNew, course.getDeadlineDate());
    }
    @Test
    @DisplayName("CST-04 - Update Assignment Due Date Not Found")
    public void updateCourseAssignmentDueDateNotFound(){
        String assignmentId = "SQT707-1.2C";
        String dueDate = "2025-03-13";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = courseService.updateCourseAssignmentDueDate(assignmentId, dueDate);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Assignment Not Found", response.getBody());
    }
    @Test
    @DisplayName("CST-05 - Update Assignment Due Date Internal Server Error")
    public void updateCourseAssignmentDueDateInternalServerError(){
        String assignmentId = "SQT707-1.2C";
        String dueDate = "2025-03-13";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.of(course));
        doThrow(new RuntimeException("Creat Query Error - Course Table")).when(courseRepository).save(any(Course.class));
        ResponseEntity<String> response = courseService.updateCourseAssignmentDueDate(assignmentId, dueDate);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Assignment update unsuccessfully", response.getBody());
    }
    @Test
    @DisplayName("CST-06 - Delete Course Assignment Successful")
    public void deleteCourseAssignmentSuccessful(){
        String assignmentId = "SQT707-1.2C";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).deleteById(assignmentId);
        ResponseEntity<String> response = courseService.deleteCourseAssignment(assignmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Course deleted successfully", response.getBody());
        verify(courseRepository).findById(assignmentId);
        verify(courseRepository).deleteById(assignmentId);
    }
    @Test
    @DisplayName("CST-07 - Delete Course Assignment Null Parameter")
    public void deleteCourseAssignmentNullParameter(){
        String assignmentId = null;
        ResponseEntity<String> response = courseService.deleteCourseAssignment(assignmentId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Course delete unsuccessfully", response.getBody());
    }
    @Test
    @DisplayName("CST-08 - Delete Course Assignment function DB Simulation")
    public void deleteCourseAssignmentErrorDB(){
        String assignmentId = "SQT707-1.2C";
        doReturn(ResponseEntity.ok(assignmentId)).when(courseService).findAssignmentIdValidation(assignmentId);
        doThrow(new RuntimeException("Delete Query Error - Course Table")).when(courseRepository).deleteById(assignmentId);
        ResponseEntity<String> response = courseService.deleteCourseAssignment(assignmentId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Delete Query Error - Course Table", response.getBody());
    }
    @Test
    @DisplayName("CST-10 - Find Course Assignment Successful")
    public void findCourseAssignmentSuccessful(){
        String courseId = "SQT-707";
        List<Course> courseList = List.of(course, course2);
        when(courseRepository.findAssignment(courseId)).thenReturn(courseList);
        List<Course> result = courseService.findCourseAssignment(courseId);
        assertEquals(courseList.size(), result.size());
        verify(courseRepository).findAssignment(courseId);
    }
    @Test
    @DisplayName("CST-11 - Find Course Assignment Null and Empty Parameters")
    public void findCourseAssignmentNullAndEmptyAssignmentId(){
        String courseIdEmpty = "";
        String courseIdNull = null;
        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> {courseService.findCourseAssignment(courseIdNull);});
        assertEquals("Message ID parameters can't be null value", exceptionEmpty.getMessage());
        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> {courseService.findCourseAssignment(courseIdEmpty);});
        assertEquals("Message ID parameters can't be null value", exceptionNull.getMessage());
    }
    @Test
    @DisplayName("CST-12 - Find All Assignment Successful")
    public void findAllAssignmentSuccessful(){
        String assignmentId = "SQT707-1.2C";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.of(course));
        Optional<Course> course1 = courseService.findAssignment(assignmentId);
        assertTrue(course1.isPresent());
        assertEquals(assignmentId, course1.get().getAssignmentId());
        assertEquals("2024-06-03", course1.get().getDeadlineDate());
        assertEquals("SQT - Unit Testing", course1.get().getAssignmentName());
        assertEquals("Software Quality and Testing", course1.get().getCourseName());
        verify(courseRepository).findById(assignmentId);
    }
    @Test
    @DisplayName("CST-13 - Find Course Assignment Null and Empty Parameters")
    public void findAllAssignmentNullAndEmptyAssignmentId(){
        String courseIdEmpty = "";
        String courseIdNull = null;
        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> {courseService.findCourseAssignment(courseIdNull);});
        assertEquals("Message ID parameters can't be null value", exceptionEmpty.getMessage());
        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> {courseService.findCourseAssignment(courseIdEmpty);});
        assertEquals("Message ID parameters can't be null value", exceptionNull.getMessage());
    }
    @Test
    @DisplayName("CST-14 - Assignment ID Validation Successful")
    public void assignmentIdValidationSuccessful(){
        String assignmentId = "SQT707-1.2C";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.of(course));
        ResponseEntity<String> response = courseService.findAssignmentIdValidation(assignmentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assignmentId, response.getBody());
    }
    @Test
    @DisplayName("CST-15 - Assignment ID Validation Null and Empty Parameters")
    public void assignmentIdValidationNullParameters(){
        String assignmentIdNull = null;
        String assignmentIdEmpty = "";
        ResponseEntity<String> responseNull = courseService.findAssignmentIdValidation(assignmentIdNull);
        assertEquals(HttpStatus.BAD_REQUEST, responseNull.getStatusCode());
        assertEquals("Assignment ID not given", responseNull.getBody());
        ResponseEntity<String> responseEmpty = courseService.findAssignmentIdValidation(assignmentIdEmpty);
        assertEquals(HttpStatus.BAD_REQUEST, responseEmpty.getStatusCode());
        assertEquals("Assignment ID not given", responseEmpty.getBody());
    }
    @Test
    @DisplayName("CST-16 - Assignment ID Not Found")
    public void assignmentIdValidationNotFound(){
        String assignmentId = "SQT707-1.2C";
        when(courseRepository.findById(assignmentId)).thenReturn(Optional.empty());
        ResponseEntity<String> assignmentIdValidation = courseService.findAssignmentIdValidation(assignmentId);
        assertEquals(HttpStatus.NOT_FOUND, assignmentIdValidation.getStatusCode());
        assertEquals("Assignment Not Found", assignmentIdValidation.getBody());
    }
    @Test
    @DisplayName("CST-17 - Save Course Successful")
    public void saveAssignmentSuccessful(){
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        ResponseEntity<String> response1 = courseService.save(course);
        ResponseEntity<String> response2 = courseService.save(course2);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals("Assignment save successful", response1.getBody());
        verify(courseRepository).save(course);
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals("Assignment save successful", response2.getBody());
        verify(courseRepository).save(course2);
    }
    @Test
    @DisplayName("CST-18 - Save Course Null Object")
    public void saveAssignmentNullObject(){
        Course course3 = null;
        ResponseEntity<String> response = courseService.save(course3);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Assignment not given", response.getBody());
        verify(courseRepository, never()).save(any());
    }
    @Test
    @DisplayName("CST-19 - Save Course Assignment function DB Simulation")
    public void saveAssignmentErrorDB(){
        when(courseRepository.save(any())).thenThrow(new RuntimeException("Creat Query Error - Course Table"));
        Course course3 = new Course();
        ResponseEntity<String> response = courseService.save(course3);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Creat Query Error - Course Table", response.getBody());
    }
}

