package sit707.ontrack.service;

import sit707.ontrack.entity.Course;
import sit707.ontrack.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@Service
public class CourseService {
    CourseRepository courseRepository;

    public ResponseEntity<String> updateCourseAssignmentDueDate(String assignmentId, String deadlineDate) {
       if(deadlineDate == null  || deadlineDate.isEmpty() ||assignmentId == null || assignmentId.isEmpty()){
            return badRequest().body("Due date is empty");
        }
        try {
            Optional<Course> course = findAssignment(assignmentId);
            if(course.isPresent()){
                Course updatedCourse = course.get();
                updatedCourse.setDeadlineDate(deadlineDate);
                courseRepository.save(updatedCourse);
                return ResponseEntity.ok().body("Assignment updated successfully");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment Not Found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment update unsuccessfully");
        }
    }

    public ResponseEntity<String> deleteCourseAssignment(String assignmentId) {
        ResponseEntity<String> assignmentIdValidation = findAssignmentIdValidation(assignmentId);
        if(assignmentIdValidation.getBody().equals(assignmentId)) {
            try{
                courseRepository.deleteById(assignmentId);
                return ResponseEntity.ok().body("Course deleted successfully");
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }else {
            return ResponseEntity.badRequest().body("Course delete unsuccessfully");
        }
    }

    public List<Course> findCourseAssignment(String courseId) {
        if(courseId == null || courseId.isEmpty()) {
            throw new IllegalArgumentException("Message ID parameters can't be null value");
        }
        return courseRepository.findAssignment(courseId);
    }

    public Optional<Course> findAssignment(String assignmentId) {
        if(assignmentId == null || assignmentId.isEmpty()) {
            throw new IllegalArgumentException("Assignment ID parameters can't be null value");
        }
        return courseRepository.findById(assignmentId);
    }

    public ResponseEntity<String> findAssignmentIdValidation(String assignmentId) {
        if(assignmentId == null || assignmentId.isEmpty()){
            return ResponseEntity.badRequest().body("Assignment ID not given");
        }
        ResponseEntity<String> findAssignmentId =  findAssignment(assignmentId)
                .map(course -> ResponseEntity.ok(course.getAssignmentId()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment Not Found"));
        if(!findAssignmentId.getBody().equals(assignmentId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment Not Found");
        }
        return findAssignmentId;
    }

    public ResponseEntity<String> save(Course course){
        if(course == null){
            return ResponseEntity.badRequest().body("Assignment not given");
        }
        try{
            courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Assignment save successful");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
