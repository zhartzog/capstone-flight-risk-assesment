package edu.unomaha.flightriskassessment.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//mark class as an Entity
@Entity
//defining class name as Table Name
@Table
public class adminThresholds
{
//Defining id as primary key
@Id
@Column
private int adminThresholdId;
@Column
private String groupType;
@Column
private String name;
@Column
private String low;
@Column
private String med;
@Column
private String high;
@Column
private String category;
public int getAdminThresholdId()
{
    return adminThresholdId;
}
public void setAdminThresholdId(int adminThresholdId)
{
    this.adminThresholdId = adminThresholdId;
}
public String getGroup()
{
    return groupType;
}
public void setGroup(String groupType)
{
    this.groupType = groupType;
}
public String getName()
{
    return name;
}
public void setName(String name)
{
    this.name = name;
}
public String getLow()
{
    return low;
}
public void setLow(String low)
{
    this.low = low;
}
public String getMed()
{
    return med;
}
public void setMed(String med)
{
    this.med = med;
}
public String getHigh()
{
    return high;
}
public void setHigh(String high)
{
    this.high = high;
}
public String getCategory() { return category; }
public void setCategory(String category) { this.category = category; }
}


