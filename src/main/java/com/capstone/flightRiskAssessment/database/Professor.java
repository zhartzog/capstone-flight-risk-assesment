package com.capstone.flightRiskAssessment.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professor {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	
	private String password;
	
	public Integer getId() { return id; }
	
	public String getFirstName() { return firstName; }
	
	public String getLastName() { return lastName; }
	
	public String getUserName() { return userName; }
	
	public String getPassword() { return password; }
	
	public void setId(Integer id) { this.id = id; }
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public void setUserName(String userName) { this.userName = userName; }
	
	public void setPassword(String password) { this.password = password; }
}
