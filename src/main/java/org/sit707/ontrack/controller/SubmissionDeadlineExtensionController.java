package org.sit707.ontrack.controller;

import org.sit707.ontrack.entity.Course;
import org.sit707.ontrack.entity.DeadlineExtension;
import org.sit707.ontrack.entity.Notification;
import org.sit707.ontrack.entity.User;
import org.sit707.ontrack.service.DeadlineExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/extension")
public class SubmissionDeadlineExtensionController {

    @Autowired
    DeadlineExtensionService deadlineExtensionService;

    @PostMapping(value = "/request",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> sendDeadlineExtensionRequest(@RequestBody DeadlineExtension deadlineExtension) {
        try{
            deadlineExtensionService.sendDeadlineExtensionRequest(deadlineExtension);
            return ResponseEntity.status(HttpStatus.CREATED).body("successful");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}/{status}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> approvedRequest(@PathVariable("id") String id, @PathVariable("status") String status ) {
        try{
            deadlineExtensionService.approvedRequest(id, status);
            return ResponseEntity.status(HttpStatus.CREATED).body("successful");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/notification",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> sendNotification(@RequestBody Notification notification) {
        try{
            deadlineExtensionService.sendNotification(notification);
            return ResponseEntity.status(HttpStatus.CREATED).body("successful");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/review/{id}")
    public Optional<DeadlineExtension> reviewRequest(@PathVariable("id") String id){
        try{
            return deadlineExtensionService.reviewRequest(id);
        }catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return  Optional.empty();
        }
    }

    @GetMapping("/user/{id}")
    public Optional<User> userValidation(@PathVariable("id") String id){
        try{
            return deadlineExtensionService.userValidation(id);
        }catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/course/{id}")
    public Optional<Course> assignmentValidation(@PathVariable("id") String id){
        try{
            return deadlineExtensionService.assignmentValidation(id);
        }catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping
    public void helloWord(){
        System.out.println("Hello World");
    }
}
