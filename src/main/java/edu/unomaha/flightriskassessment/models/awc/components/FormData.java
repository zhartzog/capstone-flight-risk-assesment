package edu.unomaha.flightriskassessment.models.awc.components;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// This is a spring class that represents a form data object in the database

@Entity
public class FormData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	private String studentFirstName;
	public String getStudentFirstName() { return studentFirstName; }
	public void setStudentFirstName(String studentFirstName) { this.studentFirstName = studentFirstName; }

	private String studentLastName;
	public String getStudentLastName() { return studentLastName; }
	public void setStudentLastName(String studentLastName) { this.studentLastName = studentLastName; }

	private String studentId;
	public String getStudentId() { return studentId; }
	public void setStudentId(String studentId) { this.studentId = studentId; }

	private String airSigmetRawString;
	public String getAirSigmetRawString() { return airSigmetRawString; }
	public void setAirSigmetRawString(String airSigmetRawString) { this.airSigmetRawString = airSigmetRawString; }

	private String metarRawString;
	public String getMetarRawString() { return metarRawString; }
	public void setMetarRawString(String metarRawString) { this.metarRawString = metarRawString; }

	private String tafRawString;
	public String getTafRawString() { return tafRawString; }
	public void setTafRawString(String tafRawString) { this.tafRawString = tafRawString; }

	private String tafForecastRawString;
	public String getTafForecastRawString() { return tafForecastRawString; }
	public void setTafForecastRawString(String tafForecastRawString) { this.tafForecastRawString = tafForecastRawString; }
}
