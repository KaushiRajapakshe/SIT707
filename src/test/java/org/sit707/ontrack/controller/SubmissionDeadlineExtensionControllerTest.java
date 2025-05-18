package org.sit707.ontrack.controller;

import org.sit707.ontrack.entity.Course;
import org.sit707.ontrack.entity.DeadlineExtension;
import org.sit707.ontrack.entity.Notification;
import org.sit707.ontrack.entity.User;
import org.sit707.ontrack.service.DeadlineExtensionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SubmissionDeadlineExtensionControllerTest {

    @InjectMocks
    private SubmissionDeadlineExtensionController submissionDeadlineExtensionController;

    @Mock
    private DeadlineExtensionService deadlineExtensionService;

    private Notification createNotificationDataSet;
    private DeadlineExtension createDeadlineExtensionDataSet;
    private Course createCourseDataSet;
    private User createUserDataSetForStudent;
    private User createUserDataSetForLecture;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createNotificationDataSet = new Notification(
                "NOTIF_SQT001",
                "AUS001",
                "Kamani Perice",
                "HenriAlbert86",
                "Dr. Henri",
                "Approved",
                "Request approved",
                "SQT2025",
                "SQT 12.1",
                "SQT2025_12",
                "SQT 2025 12.1"
        );
        createDeadlineExtensionDataSet = new DeadlineExtension(
                "SQT1005",
                "AUS001",
                "kamani@deakin.aus",
                "Kamani Perice",
                "2025/02/15",
                "2025/02/22",
                "I have fever",
                "SQT2025",
                "SQT 12.1",
                "SQT2025_12",
                "SQT 2025 12.1",
                "HenriAlbert86",
                "Dr. Henri",
                "Henri@deakin.aus",
                "Pending"
        );
        createCourseDataSet =  new Course(
                "SQT2025",
                "SQT 12.1",
                "SQT2025_12",
                "SQT 2025 12.1",
                "2025/02/15",
                "MSC",
                "SECOND 2025",
                "HenriAlbert86",
                "MSC2025A"
        );
        createUserDataSetForStudent = new User(
                "AUS001",
                "kamani@deakin.aus",
                "Kamani Perice",
                "STUDENT"
        );
        createUserDataSetForLecture = new User(
                "HenriAlbert86",
                "Henri@deakin.aus",
                "Dr. Henri",
                "DR_LECTURE"
        );
    }

    @Test
    void sendDeadlineExtensionRequestSuccess() {
        doNothing().when(deadlineExtensionService).sendDeadlineExtensionRequest(createDeadlineExtensionDataSet);
        ResponseEntity<String> response = submissionDeadlineExtensionController.sendDeadlineExtensionRequest(createDeadlineExtensionDataSet);
        verify(deadlineExtensionService, times(1)).sendDeadlineExtensionRequest(createDeadlineExtensionDataSet);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("successful", response.getBody());
    }

    @Test
    void approvedRequestSuccess() {
        doNothing().when(deadlineExtensionService).approvedRequest("SQT1005","APPROVED");
        ResponseEntity<String> response = submissionDeadlineExtensionController.approvedRequest("SQT1005","APPROVED");
        verify(deadlineExtensionService, times(1)).approvedRequest("SQT1005","APPROVED");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("successful", response.getBody());
    }

    @Test
    void sendNotificationSuccess() {
        doNothing().when(deadlineExtensionService).sendNotification(createNotificationDataSet);
        ResponseEntity<String> response = submissionDeadlineExtensionController.sendNotification(createNotificationDataSet);
        verify(deadlineExtensionService, times(1)).sendNotification(createNotificationDataSet);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("successful", response.getBody());
    }

    @Test
    void reviewRequestSuccess() {
        Optional<DeadlineExtension> createDeadlineExtensionDataSet1 = Optional.of(createDeadlineExtensionDataSet);
        when(deadlineExtensionService.reviewRequest("SQT1005")).thenReturn(createDeadlineExtensionDataSet1);
        Optional<DeadlineExtension> result = submissionDeadlineExtensionController.reviewRequest("SQT1005");
        assertTrue(result.isPresent());
        assertEquals(createDeadlineExtensionDataSet, result.get());
        verify(deadlineExtensionService, times(1)).reviewRequest("SQT1005");
    }

    @Test
    void userValidationSuccessStudent() {
        Optional<User> userOptional = Optional.of(createUserDataSetForStudent);
        when(deadlineExtensionService.userValidation("AUS001")).thenReturn(userOptional);
        Optional<User> result = submissionDeadlineExtensionController.userValidation("AUS001");
        assertTrue(result.isPresent());
        assertEquals(createUserDataSetForStudent, result.get());
        verify(deadlineExtensionService, times(1)).userValidation("AUS001");
    }

    @Test
    void userValidationSuccessLecture() {
        Optional<User> userOptional = Optional.of(createUserDataSetForStudent);
        when(deadlineExtensionService.userValidation("HenriAlbert86")).thenReturn(userOptional);
        Optional<User> result = submissionDeadlineExtensionController.userValidation("HenriAlbert86");
        assertTrue(result.isPresent());
        assertEquals(createUserDataSetForStudent, result.get());
        verify(deadlineExtensionService, times(1)).userValidation("HenriAlbert86");
    }

    @Test
    void assignmentValidationSuccess() {
       Optional<Course> courseOptional = Optional.of(createCourseDataSet);
       when(deadlineExtensionService.assignmentValidation("SQT2025")).thenReturn(courseOptional);
       Optional<Course> result = submissionDeadlineExtensionController.assignmentValidation("SQT2025");
       assertTrue(result.isPresent());
       assertEquals(createCourseDataSet, result.get());
    }

    @Test
    void sendDeadlineExtensionRequestFailure() {
        doThrow(new RuntimeException("SQL query error"))
                .when(deadlineExtensionService)
                .sendDeadlineExtensionRequest(any(DeadlineExtension.class));
        ResponseEntity<String> response = submissionDeadlineExtensionController
                .sendDeadlineExtensionRequest(createDeadlineExtensionDataSet);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("SQL query error", response.getBody());
    }

    @Test
    void approvedRequestFailure() {
        doThrow(new RuntimeException("SQL query error")).when(deadlineExtensionService).approvedRequest("SQT1005","APPROVED");
        ResponseEntity<String> response =submissionDeadlineExtensionController.approvedRequest("SQT1005","APPROVED");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("SQL query error", response.getBody());
    }

    @Test
    void sendNotificationFailure() {
        doThrow(new RuntimeException("SQL query error")).when(deadlineExtensionService).sendNotification(createNotificationDataSet);
        ResponseEntity<String> response = submissionDeadlineExtensionController.sendNotification(createNotificationDataSet);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("SQL query error", response.getBody());
    }

    @Test
    void reviewRequestFailure() {

        String requestId = "REQ002";
        when(deadlineExtensionService.reviewRequest(requestId)).thenThrow(new RuntimeException("Invalid request ID"));
        Optional<DeadlineExtension> result = submissionDeadlineExtensionController.reviewRequest(requestId);
        assertFalse(result.isPresent());
        verify(deadlineExtensionService, times(1)).reviewRequest(requestId);
    }

    @Test
    void userValidationFailure() {
        String userId = "HenriAlbert86";
        when(deadlineExtensionService.userValidation(userId)).thenThrow(new RuntimeException("Invalid user ID"));
        Optional<User> result = submissionDeadlineExtensionController.userValidation(userId);
        assertFalse(result.isPresent());
        verify(deadlineExtensionService, times(1)).userValidation(userId);
    }

    @Test
    void assignmentValidationFailure() {
        String assigmentID = "SQT2025";
        when(deadlineExtensionService.assignmentValidation(assigmentID)).thenThrow(new RuntimeException("Invalid assignment ID"));
        Optional<Course> result = submissionDeadlineExtensionController.assignmentValidation(assigmentID);
        assertFalse(result.isPresent());
        verify(deadlineExtensionService, times(1)).assignmentValidation(assigmentID);
    }

}