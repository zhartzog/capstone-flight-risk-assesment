package edu.unomaha.flightriskassessment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

// This is a spring class that represents a professor entity in the database

@Entity
@Getter
@Setter
public class Professor {
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Integer id;
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    private String firstName;
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    private String lastName;
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    private String userName;
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    private String password;
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

