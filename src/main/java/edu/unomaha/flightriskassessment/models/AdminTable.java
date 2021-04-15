package edu.unomaha.flightriskassessment.models;

public class AdminTable
{

/**
 * The ID of the threshold
 */
private int adminThresholdId;

/**
 * The group type of the threshold
 */
private String groupType;

/**
 * The name of the threshold
 */
private String name;

/**
 * The low value of the threshold
 */
private String low;

/**
 * The med value of the threshold
 */
private String med;

/**
 * The high value of the threshold
 */
private String high;
  
/**
 * The category of the threshold
 */
private String category;

/**
 * 
 * @return
 */
public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

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
}

