package org.sit707.ontrack.service;


import org.sit707.ontrack.entity.Course;
import org.sit707.ontrack.entity.DeadlineExtension;
import org.sit707.ontrack.entity.Notification;
import org.sit707.ontrack.entity.User;
import org.sit707.ontrack.repository.CourseRepository;
import org.sit707.ontrack.repository.DeadlineExtensionRepository;
import org.sit707.ontrack.repository.NotificationRepository;
import org.sit707.ontrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeadlineExtensionService {

	DeadlineExtensionRepository deadlineExtensionRepository;
	UserRepository userRepository;
	CourseRepository courseRepository;
	NotificationRepository notificationRepository;

	public void sendDeadlineExtensionRequest(DeadlineExtension deadlineExtension){
		deadlineExtensionRepository.save(deadlineExtension);
	}
	
	public Optional<User> userValidation(String userID){
		if(userID != null && !userID.isEmpty())
			return userRepository.findById(userID);
		return Optional.empty();
	}

	public void approvedRequest(String id, String status){
		deadlineExtensionRepository.findById(id).ifPresent(exitDeadlineExtension -> {
			exitDeadlineExtension.setRequestStatus(status);
			deadlineExtensionRepository.save(exitDeadlineExtension);
		});
	}

	public void sendNotification(Notification notification){
		notificationRepository.save(notification);
	}

	public Optional<DeadlineExtension> reviewRequest(String requestId){
		if(requestId != null && !requestId.isEmpty())
			return deadlineExtensionRepository.findById(requestId);
		return Optional.empty();
	}

	public Optional<Course> assignmentValidation(String assignmentID){
		if(assignmentID != null && !assignmentID.isEmpty())
			return courseRepository.findById(assignmentID);
		return Optional.empty();
	}

}
