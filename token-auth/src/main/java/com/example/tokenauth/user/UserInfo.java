package com.example.tokenauth.user;

import javax.persistence.*;

//@Entity
public class UserInfo {
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

//	@OneToOne(mappedBy = "userInfo")
	private User user;

	private String name;

	private long dni;

	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
