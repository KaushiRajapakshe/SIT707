package sit707.ontrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

import java.time.Instant;

@Entity
@Setter
@Getter
public class Message {

	@Id
	
	private String messageId;
	
	private String userId;
	
	private String userName;
	
	private String userStatus;
	
	private Boolean requestStatus;
	
	private String message;
	
	private Instant messageTime;
	
	private String assignmentId;
	
	private String assignmentName;
	
	private String courseId;
	
	private String courseName;
	
	private String receiverId;

	public Message(){}

	public Message( String messageId,  String userId,  String userName,  String userStatus,  Boolean requestStatus,  String message,  Instant messageTime,  String assignmentId,  String assignmentName,  String courseId,  String courseName,  String receiverId) {
		this.messageId = messageId;
		this.userId = userId;
		this.userName = userName;
		this.userStatus = userStatus;
		this.requestStatus = requestStatus;
		this.message = message;
		this.messageTime = messageTime;
		this.assignmentId = assignmentId;
		this.assignmentName = assignmentName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.receiverId = receiverId;
	}

	@Override
	public String toString() {
		return "Message{" +
				"messageId='" + messageId + '\'' +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", userStatus='" + userStatus + '\'' +
				", requestStatus='" + requestStatus + '\'' +
				", message='" + message + '\'' +
				", messageTime='" + messageTime + '\'' +
				", assignmentId='" + assignmentId + '\'' +
				", assignmentName='" + assignmentName + '\'' +
				", courseId='" + courseId + '\'' +
				", courseName='" + courseName + '\'' +
				", receiverId='" + receiverId + '\'' +
				'}';
	}
}
