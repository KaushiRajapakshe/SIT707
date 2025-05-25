package sit707.ontrack.entity;

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
	private String assignmentId;
	private String assignmentName;
	private String courseId;
	private String courseName;
    private String deadlineDate;
	private String degreeType;
	private String semester;
	private String lecturerId;
	private String groupId;
	private String assignmentOrderNumber;

	public Course(){}

	public Course( String assignmentId,  String assignmentName,  String courseId,  String courseName,  String deadlineDate,  String degreeType,  String semester,  String lecturerId,  String groupId,  String assignmentOrderNumber) {
		this.assignmentId = assignmentId;
		this.assignmentName = assignmentName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.deadlineDate = deadlineDate;
		this.degreeType = degreeType;
		this.semester = semester;
		this.lecturerId = lecturerId;
		this.groupId = groupId;
		this.assignmentOrderNumber = assignmentOrderNumber;
	}

	@Override
	public String toString() {
		return "Course{" +
				"assignmentId='" + assignmentId + '\'' +
				", assignmentName='" + assignmentName + '\'' +
				", courseId='" + courseId + '\'' +
				", courseName='" + courseName + '\'' +
				", deadlineDate='" + deadlineDate + '\'' +
				", degreeType='" + degreeType + '\'' +
				", semester='" + semester + '\'' +
				", lecturerId='" + lecturerId + '\'' +
				", groupId='" + groupId + '\'' +
				", assignmentOrderNumber='" + assignmentOrderNumber + '\'' +
				'}';
	}
}
