package edu.unomaha.flightriskassessment.services;

import edu.unomaha.flightriskassessment.models.Form.RiskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.models.Form.VFRRiskModel;
import edu.unomaha.flightriskassessment.models.AdminTable;

@Service
public class VFRRiskService
{
    @Autowired
    private AdminTableService adminTableService;

    private VFRRiskModel riskModel;

    /*This variable will keep track of the low, medium, and high risk for each threshold. Avoids having so many local variables.*/
    private int[] riskLevel;

    private RiskResponse response;

    //Calculate Risk for VFR Categories
    //UPDATE VALUES WITH getThresholdByGroupNameCategory(String group, String name, String category)
    public RiskResponse VFRRiskCalc(VFRRiskModel riskModel)
    {
        //TODO: Some of the risk in the opposite direction. compare risk level function is not going to work for stuff like gusts. Must make new method.
        response = new RiskResponse();

        set_human_factor_risk();

        if ( !riskModel.isDual() )
            set_solo_risk();

        if ( riskModel.isLocal() )
            return VFRRiskCalc_local();
        else
            return VFRRiskCalc_XC();
    }


    /*Sets risk level given a threshold from database*/
    private void setRiskLevels(AdminTable input)
    {
        riskLevel[0] = Integer.parseInt(input.getLow());
        riskLevel[1] = Integer.parseInt(input.getMed());
        riskLevel[2] = Integer.parseInt(input.getHigh());
    }

    private int compareRiskToLimit(int risk)
    {
        if ( risk >= riskLevel[0] )
        {
            //risk is low (0)
            return 0;
        }
        else if ( risk >= riskLevel[1] )
        {
            //risk is med(1)
            return 1;
        }
        else if ( risk >= riskLevel[2] )
        {
            //risk is high(3)
            return 3;
        }
        else if ( risk < riskLevel[2] )
        {
            //Not Allowed, Automatically No Go
            return 15;
        }
        //this should not happen;
        System.out.println("ERROR: No statement connected in compareRiskToLimit(risk = " + risk + ")");
        return -1;
    }

    private int compareStringRiskLevel(String risk, AdminTable threshold)
    {
        if(risk.equals(threshold.getHigh()))
            return 3;
        else if(risk.equals(threshold.getMed()))
            return 1;
        else
            return 0;
    }

    private void set_human_factor_risk()
    {
        AdminTable tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Time of Flight", "physiology");

       //Set time of flight risk
        response.setTime_of_flight_risk(compareStringRiskLevel(riskModel.getType_of_flight(), tempThreshold));

        //set risk associated with flight duty perios
       setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Flight Duty Period Began", "physiology"));
       response.setFlight_duty_risk(compareRiskToLimit(riskModel.getFlight_duty_period()));

       //Set Previous flights risk
       setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Previous Flights that Day", "physiology"));
       response.setPrevious_flight_risk(compareRiskToLimit(riskModel.getPrevious_flights()));

       //Set Syllabus flight type risk
        tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Type of Syllabus Flight", "physiology");
        response.setType_of_flight_risk(compareStringRiskLevel(riskModel.getType_of_flight(), tempThreshold));

        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Outside Temperatures Low", "physiology"));
        //Temperature is above the highest low temperature risk.
        if(riskModel.getTemperature() > riskLevel[1])
        {
            //check the high temperature risk
            setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Outside Temperatures High", "physiology"));
            if(riskModel.getTemperature() < riskLevel[1])//If colder than low risk category, there is no temperature risk
                response.setTemperature_risk(0);
            else if( riskModel.getTemperature() < riskLevel[2]) //medium risk
                response.setTemperature_risk(1);
            else if( riskModel.getTemperature() < riskLevel[3]) //high risk
                response.setTemperature_risk(3);
            else //no go
                response.setTemperature_risk(15);

        }
        else //Colder than low temp. some increase in risk will occur.
        {
            if(riskModel.getTemperature() < riskLevel[2])
            {
                if(riskModel.getTemperature() < riskLevel[3])
                    response.setTemperature_risk(15);
                else
                    response.setTemperature_risk(3);
            }
            else
                response.setTemperature_risk(1);
        }


    }

    //Calculate VFR Local Pattern Risk
    private RiskResponse VFRRiskCalc_local()
    {

        //Select which limit we will compare against.
        if ( riskModel.isDual() && !riskModel.isNight() )
            setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling (Day Dual)", "localPattern"));
        else if ( !riskModel.isDual() && !riskModel.isNight() )
            setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling (Day Solo)", "localPattern"));
        else if ( riskModel.isNight() && riskModel.isDual() )
            setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling (Night Dual)", "localPattern"));
        //TODO: What about Solo night risk level?
        //Set departure risk
        this.response.setDeparture_ceiling_risk(compareRiskToLimit(riskModel.getDeparture_ceilings()));

        //Set departure visibility risk
        if ( !riskModel.isNight() )
            setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Visibility (Day)", "localPattern"));
        else
            setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Visibility (Night)", "localPattern"));

        this.response.setDeparture_vis_risk(compareRiskToLimit(riskModel.getDeparture_vis()));

        //Set departure wind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Total Wind", "localPattern"));
        this.response.setDeparture_wind_risk(compareRiskToLimit(riskModel.getDeparture_winds()));

        //Set departure wind gust risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Gust Increment", "localPattern"));
        this.response.setDeparture_gust_risk(compareRiskToLimit(riskModel.getDeparture_winds()));

        //Set departure crosswind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Crosswind", "localPattern"));
        this.response.setDeparture_crosswind_risk(compareRiskToLimit(riskModel.getDeparture_winds()));

        return response;
    }


    //Set risk for VFR cross countries or flights to auxillary fields.
    private RiskResponse VFRRiskCalc_XC()
    {
        /*---Set Departure Risk---*/

        //Set departure ceiling risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling", "departure"));
        this.response.setDeparture_ceiling_risk(compareRiskToLimit(riskModel.getDeparture_ceilings()));

        //Set departure visibility risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Visibility", "departure"));
        this.response.setDeparture_vis_risk(compareRiskToLimit(riskModel.getDeparture_vis()));

        //Set Departure wind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Total Wind", "departure"));
        this.response.setDeparture_wind_risk(compareRiskToLimit(riskModel.getDeparture_winds()));

        //Set Departure wind gust risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Gust Increment", "departure"));
        this.response.setDeparture_gust_risk(compareRiskToLimit(riskModel.getDeparture_gusts()));

        //Set Departure crosswind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Crosswind", "departure"));
        this.response.setDeparture_crosswind_risk(compareRiskToLimit(riskModel.getDeparture_crosswind()));

        /*----Set Enroute Risk----*/

        //Set enroute ceiling risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling", "enroute"));
        this.response.setEnroute_ceiling_risk(compareRiskToLimit(riskModel.getEnroute_ceilings()));

        //Set enroute visibility risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Visibility", "enroute"));
        this.response.setEnroute_vis_risk(compareRiskToLimit(riskModel.getEnroute_vis()));

        //set VFR checkpoint risks
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "VFR Checkpoints", "enroute"));
        this.response.setVfr_checkpoint_risk(compareRiskToLimit(riskModel.getVfrCheckpoints()));

        //set time enroute risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Time Enroute", "enroute"));
        this.response.setTime_enroute_risk(compareRiskToLimit(riskModel.getTimeEnroute()));

        //set fuel at alternate risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Fuel at Alternate", "enroute"));
        this.response.setFuel_at_alternate_risk(compareRiskToLimit(riskModel.getFuelAtAlternate()));

        /*----Set Destination Risk----*/
        //Set departure ceiling risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling", "destination"));
        this.response.setDestination_ceiling_risk(compareRiskToLimit(riskModel.getDestination_ceilings()));

        //Set destination visibility risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Visibility", "destination"));
        this.response.setDestination_vis_risk(compareRiskToLimit(riskModel.getDestination_vis()));

        //Set Departure wind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Total Wind", "destination"));
        this.response.setDestination_wind_risk(compareRiskToLimit(riskModel.getDestination_winds()));

        //Set Departure wind gust risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Gust Increment", "destination"));
        this.response.setDestination_gust_risk(compareRiskToLimit(riskModel.getDestination_gusts()));

        //Set Departure crosswind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Crosswind", "destination"));
        this.response.setDestination_crosswind_risk(compareRiskToLimit(riskModel.getDestination_crosswind()));

        /*----Set Alternate Risk----*/
        //Set departure ceiling risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Ceiling", "alternate"));
        this.response.setAlternate_ceiling_risk(compareRiskToLimit(riskModel.getAlternate_ceilings()));

        //Set alternate visibility risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Visibility", "alternate"));
        this.response.setAlternate_vis_risk(compareRiskToLimit(riskModel.getAlternate_vis()));

        //Set Departure wind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Total Wind", "alternate"));
        this.response.setAlternate_wind_risk(compareRiskToLimit(riskModel.getAlternate_winds()));

        //Set Departure wind gust risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Gust Increment", "alternate"));
        this.response.setAlternate_gust_risk(compareRiskToLimit(riskModel.getAlternate_gusts()));

        //Set Departure crosswind risk
        setRiskLevels(adminTableService.getThresholdByGroupNameCategory("vfr", "Crosswind", "alternate"));
        this.response.setAlternate_crosswind_risk(compareRiskToLimit(riskModel.getAlternate_crosswind()));

        return response;
    }

    //Calculate SoloFactors
    public int VFRRiskCalcHFSolo(VFRRiskModel riskModel)
    {
        AdminTable tempThreshold;
        int risk = 0;
        int low, med, high;
        if ( riskModel.getCategory() == "Flight Location" )
        {
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Flight Location", "soloFactors");
            if ( riskModel.getCategoryValue() == tempThreshold.getLow() )
            {
                //risk is low (0)
            }
            else if ( riskModel.getCategoryValue() == tempThreshold.getMed() )
            {
                //risk is med(1)
                risk++;
            }
            else if ( riskModel.getCategoryValue() == tempThreshold.getHigh() )
            {
                //risk is high(3)
                risk += 3;
            }
        }
        if ( riskModel.getCategory() == "Gnd Reference Manuevers" )
        {
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Gnd Reference Manuevers", "soloFactors");
            low = Integer.parseInt(tempThreshold.getLow());
            med = Integer.parseInt(tempThreshold.getMed());
            high = Integer.parseInt(tempThreshold.getHigh());
            int categoryValueNum;
            categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
            if ( categoryValueNum == low )
            {
                //risk is low (0)
            }
            else if ( categoryValueNum == med )
            {
                //risk is med(1)
                risk++;
            }
            else if ( categoryValueNum == high ) //risk is 2+
            {
                //risk is high(3)
                risk += 3;
            }
        }
        if ( riskModel.getCategory() == "Experience in Airplane Type" )
        {
            int categoryValueNum;
            categoryValueNum = Integer.parseInt(categoryValue);
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Experience in Airplane Type", "soloFactors");
            low = Integer.parseInt(tempThreshold.getLow());
            med = Integer.parseInt(tempThreshold.getMed());
            high = Integer.parseInt(tempThreshold.getHigh());
            if ( categoryValueNum > low )
            {
                //risk is low (0)
            }
            else if ( categoryValueNum >= high && categoryValueNum <= med )
            {
                //risk is med(1)
                risk++;
            }
            else if ( categoryValueNum < high )
            {
                //risk is high(3)
                risk += 3;
            }
        }
        if ( riskModel.getCategory() == "Last Dual Landing (Private)" )
        {
            int categoryValueNum;
            categoryValueNum = Integer.parseInt(categoryValue);
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Last Dual Landing (Private)", "soloFactors");
            low = Integer.parseInt(tempThreshold.getLow());
            med = Integer.parseInt(tempThreshold.getMed());
            high = Integer.parseInt(tempThreshold.getHigh());
            if ( categoryValueNum < low )
            {
                //risk is low (0)
            }
            else if ( categoryValueNum > low && categoryValueNum <= med )
            {
                //risk is med(1)
                risk++;
            }
            else if ( categoryValueNum > med && categoryValueNum <= high )
            {
                //risk is high(3)
                risk += 3;
            }
        }
        if ( riskModel.getCategory() == "Last Dual Landing (Comm)" )
        {
            int categoryValueNum;
            categoryValueNum = Integer.parseInt(categoryValue);
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Last Dual Landing (Comm)", "soloFactors");
            low = Integer.parseInt(tempThreshold.getLow());
            med = Integer.parseInt(tempThreshold.getMed());
            high = Integer.parseInt(tempThreshold.getHigh());
            if ( categoryValueNum < low )
            {
                //risk is low (0)
            }
            else if ( categoryValueNum >= low && categoryValueNum <= med )
            {
                //risk is med(1)
                risk++;
            }
            else if ( categoryValueNum > med && categoryValueNum <= high )
            {
                //risk is high(3)
                risk += 3;
            }
        }
        if ( riskModel.getCategory() == "Last Dual Stalls (Private)" )
        {
            int categoryValueNum;
            categoryValueNum = Integer.parseInt(categoryValue);
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Last Dual Stalls (Private)", "soloFactors");
            low = Integer.parseInt(tempThreshold.getLow());
            med = Integer.parseInt(tempThreshold.getMed());
            high = Integer.parseInt(tempThreshold.getHigh());
            if ( categoryValueNum < low )
            {
                //risk is low (0)
            }
            else if ( categoryValueNum > low && categoryValueNum <= med )
            {
                //risk is med(1)
                risk++;
            }
            else if ( categoryValueNum > med && categoryValueNum <= high )
            {
                //risk is high(3)
                risk += 3;
            }
        }
        if ( riskModel.getCategory() == "Last Dual Stalls (Comm)" )
        {
            int categoryValueNum;
            categoryValueNum = Integer.parseInt(categoryValue);
            tempThreshold = adminTableService.getThresholdByGroupNameCategory("vfr", "Last Dual Stalls (Comm)", "soloFactors");
            low = Integer.parseInt(tempThreshold.getLow());
            med = Integer.parseInt(tempThreshold.getMed());
            high = Integer.parseInt(tempThreshold.getHigh());
            if ( categoryValueNum < low )
            {
                //risk is low (0)
            }
            else if ( categoryValueNum >= low && categoryValueNum <= med )
            {
                //risk is med(1)
                risk++;
            }
            else if ( categoryValueNum > med && categoryValueNum <= high )
            {
                //risk is high(3)
                risk += 3;
            }
        }
        return risk;
    }
}
