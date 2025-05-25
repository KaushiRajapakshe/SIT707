package sit707.ontrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
	@Id
	
	private String userId;
	private String email;
	private String username;
	private String status;

	public User( String userId,  String email,  String username,  String status) {
		this.userId= userId;
		this.email = email;
		this.username = username;
		this.status = status;
	}

	public User() {}

	@Override
	public String toString() {
		return "userValidation [userId=" + userId+ ", email=" + email + ", username=" + username + ", status=" + status
				+ "]";
	}
}
