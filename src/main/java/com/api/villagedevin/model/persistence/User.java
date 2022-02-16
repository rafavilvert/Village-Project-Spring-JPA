package com.api.villagedevin.model.persistence;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.villagedevin.model.transport.UserDTO;

@Entity
@Table(name = "`user`")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String[] roles;

	public User() {
		
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, String password, String[] roles) {
		this(email, password);
		this.roles = roles;
	}

	public User(String email, String password) {
		this(email);
		this.password = password;
	}

	public User(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	public User(UserDTO userdto) {
		this.email = userdto.getEmail();
		this.password = userdto.getPassword();
		this.roles = userdto.getRoles();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

}
