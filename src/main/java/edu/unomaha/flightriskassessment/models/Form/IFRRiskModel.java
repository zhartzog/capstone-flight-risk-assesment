package edu.unomaha.flightriskassessment.models.Form;
import java.util.Locale.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//mark class as an Entity
@Entity
//defining class name as Table Name
@Table
public class IFRRiskModel
{
//Defining id as primary key
@Id
@Column
private String mainCategory; //e.g. IFR Local or IFR Cross Country Departure
@Column
private String category; //e.g. Ceiling (Day)
@Column
private String categoryValue; //e.g. 1000+, Precision, 4+ SM
@Column
private int risk; // e.g. 0-12 Okay, 13-14 Caution, 15+ No Go
public String getMainCategory()
{
    return mainCategory;
}
public void setMainCategory(String mainCategory)
{
    this.mainCategory = mainCategory;
}
public String getCategory()
{
    return category;
}
public void setCategory(String category)
{
    this.category = category;
}
public String getCategoryValue()
{
    return categoryValue;
}
public void setLow(String categoryValue)
{
    this.categoryValue = categoryValue;
}
public void setHigh(String high)
{
    this.high = high;
}
public int getRisk()
{
    return risk;
}
}


