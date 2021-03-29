package com.adminTable.model;
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
private String group;
@Column
private String name;
@Column
private String low;
@Column
private String med;
@Column
private String high;
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
    return group;
}
public void setGroup(String group)
{
    this.group = group;
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

}


