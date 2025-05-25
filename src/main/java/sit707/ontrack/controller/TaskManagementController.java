package sit707.ontrack.controller;

import sit707.ontrack.entity.Course;
import sit707.ontrack.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/task")
public class TaskManagementController {

    @Autowired
    private CourseService courseService;

    @DeleteMapping(value = "/{assignmentId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("assignmentId") String assignmentId ) {
        try{
            ResponseEntity<String> response = courseService.deleteCourseAssignment(assignmentId);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course Service returned null response - Delete failed");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - Delete failed");
        }
    }

    @PutMapping("/{assignmentId}/{deadlineDate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable("assignmentId") String assignmentId, @PathVariable("deadlineDate") String deadlineDate) {
        try{
            ResponseEntity<String> response = courseService.updateCourseAssignmentDueDate(assignmentId, deadlineDate);
            HttpStatusCode status =  response.getStatusCode();

            if (status == HttpStatus.OK) {
                return ResponseEntity.ok("Assignment update - successfully");
            } else if (status == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assignment update - Not found");
            } else if (status == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.badRequest().body("Assignment update - Due date is Null, Empty, Invalidate, Incorrect");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment update - Internal Server Error");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment update - Internal Server Error");
        }
    }

    @GetMapping("/{assignmentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getMessageForAssignment(@PathVariable("assignmentId")String assignmentId){
        try {
            Optional<Course> result = courseService.findAssignment(assignmentId);
            if (result.isPresent()) {
                return ResponseEntity.ok(result.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("GetMessageForAssignment - Not Found");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("GetMessageForAssignment - Assignment ID is null or empty");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("GetMessageForAssignment - Internal Service Error");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Course course ) {
        try{
            ResponseEntity<String> response = courseService.save(course);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course Service returned null response - Save failed");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Service error - failed to Save course");
        }
    }
}
