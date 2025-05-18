package org.sit707.ontrack.entity;

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
	@NonNull
	private String userID;
	@NonNull
	private String email;
	@NonNull
	private String username;
	@NonNull
	private String status;
	
	public User() {}
	public User(String userID, String email, String username, String status) {
		super();
		this.userID = userID;
		this.email = email;
		this.username = username;
		this.status = status;
	}
	@Override
	public String toString() {
		return "userValidation [userID=" + userID + ", email=" + email + ", username=" + username + ", status=" + status
				+ "]";
	}
}
