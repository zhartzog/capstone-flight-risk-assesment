package edu.unomaha.flightriskassessment.models;
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
private String mainCategory;
@Column
private String category;
@Column
private String categoryValue;
@Column
private int risk;
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


