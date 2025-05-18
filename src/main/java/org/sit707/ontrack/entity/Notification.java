package org.sit707.ontrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Notification {

	@Id
	@NonNull
	private String notificationId;
	@NonNull
	private String studentID;
	@NonNull
	private String studentName;
	@NonNull
	private String lecturerID;
	@NonNull
	private String lecturerName;
	@NonNull
	private String requestStatus;
	@NonNull
	private String message;
	@NonNull
	private String assignmentID;
	@NonNull
	private String assignmentName;
	@NonNull
	private String courseID;
	@NonNull
	private String courseName;
	
	public Notification(){}

	public Notification(String notificationId, String studentID, String studentName, String lecturerID, String lecturerName,
						String requestStatus, String message, String assignmentID, String assignmentName, String courseID, String courseName) {
		this.notificationId = notificationId;
		this.studentID = studentID;
		this.studentName = studentName;
		this.lecturerID = lecturerID;
		this.lecturerName = lecturerName;
		this.requestStatus = requestStatus;
		this.message = message;
		this.assignmentID = assignmentID;
		this.assignmentName = assignmentName;
		this.courseID = courseID;
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "Notification{" +
				"notificationId='" + notificationId + '\'' +
				", studentID='" + studentID + '\'' +
				", studentName='" + studentName + '\'' +
				", lecturerID='" + lecturerID + '\'' +
				", lecturerName='" + lecturerName + '\'' +
				", requestStatus='" + requestStatus + '\'' +
				", message='" + message + '\'' +
				", assignmentID='" + assignmentID + '\'' +
				", assignmentName='" + assignmentName + '\'' +
				", courseID='" + courseID + '\'' +
				", courseName='" + courseName + '\'' +
				'}';
	}
}
