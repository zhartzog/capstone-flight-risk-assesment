package edu.unomaha.flightriskassessment.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.models.IFRRiskModel;
import edu.unomaha.flightriskassessment.database.AdminTableRepository;
import edu.unomaha.flightriskassessment.models.AdminTable; 
import edu.unomaha.flightriskassessment.services.AdminTableService;

@Service
public class IFRRiskService {

	//Calculate Risk for IFR Categories
	//UPDATE VALUES WITH getThresholdByGroupNameCategory(String group, String name, String category)
	//usage: group = IFR/VFR, name = ceilingDay, CeilingNight, etc. category = departure, etc.
	public int IFRRiskCalc(IFRRiskModel riskModel)
	{
		int totalRiskLocalorCC = 0;
		int totalRiskEWF = 0;
		int totalRiskDest = 0;
		int totalRiskPrimAlt = 0;
		int totalRiskHumanFactors = 0;
		if(riskModel.getMainCategory() == "IFR local" || riskModel.getMainCategory() == "IFR Cross Country Departure")
		{
			totalRiskLocalorCC += IFRRiskCalcLocalorCC(riskModel);
			return totalRiskLocalorCC;
		}
		if(riskModel.getMainCategory() == "Enroute Weather / Fuel")
		{
			totalRiskEWF += IFRRiskCalcEWF(riskModel);
			return totalRiskEWF;
		}
		if(riskModel.getMainCategory() == "IFR:Destination")
		{
			totalRiskDest += IFRRiskCalcDest(riskModel);
			return totalRiskDest;
		}
		if(riskModel.getMainCategory() == "IFR:Primary Alternate")
		{
			totalRiskPrimAlt += IFRRiskCalcLocalorCC(riskModel);
			return totalRiskPrimAlt;
		}
		if(riskModel.getMainCategory() == "Phsiology/Psychology")
		{
			totalRiskHumanFactors += IFRRiskCalcHF(riskModel);
			return totalRiskHumanFactors;
		}
	}

	//Calculate IFR Local or IFR Cross Country
	public int IFRRiskCalcLocalorCC(IFRRiskModel riskModel)
	{
		AdminTable tempThreshold;
		int low, med, high;
		int risk = 0;
		int categoryValueNum;
		if(riskModel.getCategory() != "Best IAP Available")
		{
			categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
		}
		if(riskModel.getCategory() == "Ceiling (Day)")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Ceiling (Day)", "departure");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Ceiling (Night)")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Ceiling (Night)", "departure");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}

		}
		if(riskModel.getCategory() == "Visibility")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Visibility", "departure");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}

		}
		if(riskModel.getCategory() == "Best IAP Available")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Best IAP Available", "departure");
			if(riskModel.getCategoryValue() == tempThreshold.getLow())
			{
				//risk is low(0)
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getMed())
			{
				//risk is med(1)
				risk++;
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getHigh())
			{
				//risk is high(3)
				risk += 3;
			}
			else
			{
				System.out.print("Given Value did not match threshold table value");	
			}
		}
		if(riskModel.getCategory() == "Total Wind")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Total Wind", "departure");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Gust Increment")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Gust Increment", "departure");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Crosswind")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Crosswind", "departure");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		return risk;
	}

	//Calcualte Enroute Weather/Fuel Risk
	public int IFRRiskCalcEWF(IFRRiskModel riskModel)
	{
		AdminTable tempThreshold;
		int low, med, high;
		int risk = 0;
		int categoryValueNum;
		categoryValueNum = Integer.parseInt(categoryValue);
		if(riskModel.getCategory() == "Ceilings (Day)")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Ceilings (Day)", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Ceilings (Night)")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Ceilings (Night)", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Visibility")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Visibility", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}

		}
		if(riskModel.getCategory() == "Time Enroute")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Time Enroute", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Thunderstorms")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Thunderstorms", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Fuel at Alternate")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Fuel at Alternate", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Alternate Airfields")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Alternate Airfields", "enroute");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == high)
			{
				//risk is high(3)
				risk += 3;
			}
			else
			{	//Auto No Go
				risk += 15;
			}
		}
		return risk;
	}

	//Calculate IFR: Destination
	public int IFRRiskCalcDest(IFRRiskModel riskModel)
	{
		AdminTable tempThreshold;
		int low, med, high;
		int risk = 0;
		int categoryValueNum;
		if(riskModel.getCategory() != "Best IAP Available")
		{
			categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
		}
		if(riskModel.getCategory() == "Ceiling (Day)")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Ceiling (Day)", "destination");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Ceiling (Night)")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Ceiling (Night)", "destination");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}

		}
		if(riskModel.getCategory() == "Visibility")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Visibility", "destination");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}

		}
		if(riskModel.getCategory() == "Best IAP Available")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Best IAP Available", "destination");
			if(riskModel.getCategoryValue() == tempThreshold.getLow())
			{
				//risk is low(0)
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getMed())
			{
				//risk is med(1)
				risk++;
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getHigh())
			{
				//risk is high(3)
				risk += 3;
			}
			else
			{
				System.out.print("Given Value did not match threshold table value");	
			}
		}
		if(riskModel.getCategory() == "Total Wind")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Total Wind", "destination");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Gust Increment")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Gust Increment", "destination");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Crosswind")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Crosswind", "destination");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//Not Allowed, Automatically No Go
				risk += 15;
			}
		}
		return risk;
	}

	//Calculate Student Human Factors Risk
	public int IFRRiskCalcHF(IFRRiskModel riskModel)
	{
		AdminTable tempThreshold;
		int low, med, high;
		int risk = 0;
		if(riskModel.getCategory() == "Time of Flight")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Time of Flight", "physiology");
			if(riskModel.getCategoryValue() == tempThreshold.getLow())
			{
				//risk is low (0)
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getMed())
			{
				//risk is med(1)
				risk++;
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getHigh())
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Flight Duty Period Began")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
			tempThreshold = getThresholdByGroupName("ifr", "Flight Duty Period Began", "physiology");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//no go
				risk += 15;
			}
		}
		if(riskModel.getCategory() == "Previous Flights That Day")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Previous Flights That Day", "physiology");
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(riskModel.getCategoryValue() != "None")
			{
				int categoryValueNum;
				categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
			}
			if(riskModel.getCategoryValue() == tempThreshold.getLow())
			{
				//risk is low (0)
			}
			if(categoryValueNum == med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Type of Syllabus Flight")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Type of Syllabus Flight", "physiology");
			if(riskModel.getCategoryValue() == tempThreshold.getLow())
			{
				//risk is low(0)
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getMed())
			{
				//risk is med(1)
				risk++;
			}
			else if(riskModel.getCategoryValue() == tempThreshold.getHigh())
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Outside Temperatures High")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Outside Temperatures High", "physiology");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
			if(categoryValueNum <= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Outside Temperatures Low")
		{
			tempThreshold = getThresholdByGroupName("ifr", "Outside Temperatures Low", "physiology");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
			if(categoryValueNum >= low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum < high)
			{
				//risk is too high
				risk += 15;
			}
		}
		return risk;
	}
}
