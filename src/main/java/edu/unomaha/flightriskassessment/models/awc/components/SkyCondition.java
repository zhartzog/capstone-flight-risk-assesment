package edu.unomaha.flightriskassessment.models.awc.components;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "sky_condition")
public class SkyCondition
{
    /* Cloud coverage. Allowed values will depend on the type of call.
    *       METARs:  SKC|CLR|CAVOK|FEW|SCT|BKN|OVC|OVX
    *       TAF:     NSC|SKC|CLR|SKT|BKN|FEW|OVC|OVCX
    *       PIREP:   VMC|VFR|SKC|CLEAR|CAVOC|FEW|SCT|BKN|OVC|OVX|IFR|IMC
    * */
    private String coverage;
    //Lowest reported altitude of the clouds. Measured in MSL.
    private int base;
    //Highest reported altitude of the clouds. Measured in MSL. Will be NULL for METARs and TAFs
    private int top;
    //Types of clouds. TAFs only. Allowed values: CB|TCU|CU
    private String type;

    public SkyCondition(){}

    public SkyCondition(String coverage, int base)
    {
        this.coverage = coverage;
        this.base = base;
    }

    public String getCoverage()
    {
        return coverage;
    }

    @XmlAttribute(name = "sky_cover")
    public void setCoverage(String coverage)
    {
        this.coverage = coverage;
    }

    public int getBase()
    {
        return base;
    }

    @XmlAttribute(name = "cloud_base_ft_agl")
    public void setBase(int base)
    {
        this.base = base;
    }

    public int getTop()
    {
        return top;
    }

    @XmlAttribute( name = "cloud_top_ft_msl" )
    public void setTop(int top)
    {
        this.top = top;
    }

    public String getType()
    {
        return type;
    }

    @XmlAttribute( name = "cloud_type" )
    public void setType(String type)
    {
        this.type = type;
    }
}
