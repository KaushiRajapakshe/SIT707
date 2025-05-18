package org.sit707.ontrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeadlineExtension {
	@Id
	@NonNull
	private String requestId;
	@NonNull
	private String studentID;
	@NonNull
	private String studentEmail;
	@NonNull
	private String studentName;
	@NonNull
	private String deadlineDate;
	@NonNull
	private String nextSubmissiondate;
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
	@NonNull
	private String lectureID;
	@NonNull
	private String lectureName;
	@NonNull
	private String lectureEmail;
	@NonNull
	private String requestStatus;

	public DeadlineExtension(){}

	public DeadlineExtension(String requestId, String studentID, String studentEmail, String studentName, String deadlineDate, String nextSubmissiondate, String message, String assignmentID, String assignmentName, String courseID, String courseName, String lectureID, String lectureName, String lectureEmail, String requestStatus) {
		this.requestId = requestId;
		this.studentID = studentID;
		this.studentEmail = studentEmail;
		this.studentName = studentName;
		this.deadlineDate = deadlineDate;
		this.nextSubmissiondate = nextSubmissiondate;
		this.message = message;
		this.assignmentID = assignmentID;
		this.assignmentName = assignmentName;
		this.courseID = courseID;
		this.courseName = courseName;
		this.lectureID = lectureID;
		this.lectureName = lectureName;
		this.lectureEmail = lectureEmail;
		this.requestStatus = requestStatus;
	}

	@Override
	public String toString() {
		return "DeadlineExtension{" +
				"requestId='" + requestId + '\'' +
				", studentID='" + studentID + '\'' +
				", studentEmail='" + studentEmail + '\'' +
				", studentName='" + studentName + '\'' +
				", deadlineDate='" + deadlineDate + '\'' +
				", nextSubmissiondate='" + nextSubmissiondate + '\'' +
				", message='" + message + '\'' +
				", assignmentID='" + assignmentID + '\'' +
				", assignmentName='" + assignmentName + '\'' +
				", courseID='" + courseID + '\'' +
				", courseName='" + courseName + '\'' +
				", lectureID='" + lectureID + '\'' +
				", lectureName='" + lectureName + '\'' +
				", lectureEmail='" + lectureEmail + '\'' +
				", requestStatus='" + requestStatus + '\'' +
				'}';
	}
}
