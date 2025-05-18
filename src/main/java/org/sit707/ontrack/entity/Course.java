package org.sit707.ontrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Course {

    @Id
	@NonNull
	private String assignmentID;
	@NonNull
	private String assignmentName;
	@NonNull
	private String courseID;
	@NonNull
	private String courseName;
	@NonNull
    private String deadlineDate;
	@NonNull
	private String degreeType;
	@NonNull
	private String semester;
	@NonNull
	private String lecturerID;
	@NonNull
	private String groupID;

	public Course(){}

	public Course(String assignmentID, String assignmentName, String courseID, String courseName, String deadlineDate, String degreeType, String semester, String lecturerID, String groupID) {
		this.assignmentID = assignmentID;
		this.assignmentName = assignmentName;
		this.courseID = courseID;
		this.courseName = courseName;
		this.deadlineDate = deadlineDate;
		this.degreeType = degreeType;
		this.semester = semester;
		this.lecturerID = lecturerID;
		this.groupID = groupID;
	}

	@Override
	public String toString() {
		return "course{" +
				"assignmentID='" + assignmentID + '\'' +
				", assignmentName='" + assignmentName + '\'' +
				", courseID='" + courseID + '\'' +
				", courseName='" + courseName + '\'' +
				", deadlineDate='" + deadlineDate + '\'' +
				", degreeType='" + degreeType + '\'' +
				", semester='" + semester + '\'' +
				", lecturerID='" + lecturerID + '\'' +
				", groupID='" + groupID + '\'' +
				'}';
	}
}
