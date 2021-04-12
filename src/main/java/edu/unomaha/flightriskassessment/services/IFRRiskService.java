package edu.unomaha.flightriskassessment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.models.IFRRiskModel;;

@Service
public class IFRRiskService {

	//Calculate Risk for IFR Categories
	public int IFRRiskCalc(String mainCategory, String category, String categoryValue)
	{
		int totalRiskLocalorCC = 0;
		int totalRiskEWF = 0;
		int totalRiskDest = 0;
		int totalRiskPrimAlt = 0;
		int totalRiskHumanFactors = 0;
		if(mainCategory == "IFR local" || mainCategory == "IFR Cross Country Departure")
		{
			totalRiskLocalorCC += IFRRiskCalcLocalorCC(category, categoryValue);
			return totalRiskLocalorCC;
		}
		if(mainCategory == "Enroute Weather / Fuel")
		{
			totalRiskEWF += IFRRiskCalcEWF(category, categoryValue);
			return totalRiskEWF;
		}
		if(mainCategory == "IFR:Destination")
		{
			totalRiskDest += IFRRiskCalcLocalorCC(category, categoryValue);
			return totalRiskDest;
		}
		if(mainCategory == "IFR:Primary Alternate")
		{
			totalRiskPrimAlt += IFRRiskCalcLocalorCC(category, categoryValue);
			return totalRiskPrimAlt;
		}
		if(mainCategory == "Phsiology/Psychology")
		{
			totalRiskHumanFactors += IFRRiskCalcHF(category, categoryValue);
			return totalRiskHumanFactors;
		}
	}

	//Calculate IFR Local or IFR Cross Country
	public int IFRRiskCalcLocalorCC(String category, String categoryValue)
	{
		int risk = 0;
		if(category != "Best IAP Available")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
		
		}
		if(category == "Ceiling(Day)")
		{
			if(categoryValueNum >= 1000)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 1000 && categoryValueNum > 799)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 800 && categoryValueNum > 599)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Ceiling(Night)")
		{
			if(categoryValueNum >= 1500)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 1500 && categoryValueNum > 1199)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 1200 && categoryValueNum > 999)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Visibility")
		{
			if(categoryValueNum >= 4)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == 3)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == 2)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Best IAP Available")
		{
			if(categoryValue == "Precision")
			{
				//risk is low(0)
			}
			else if(categoryValue == "Non-Prec")
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValue == "Circling")
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Total Wind")
		{
			if(categoryValueNum > 0 && categoryValueNum < 16)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > 15 && categoryValueNum < 21)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > 20 && categoryValueNum < 26)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Gust Increment")
		{
			if(categoryValueNum > 0 && categoryValueNum < 6)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > 5 && categoryValueNum < 9)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > 8 && categoryValueNum < 11)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Crosswind")
		{
			if(categoryValueNum > 0 && categoryValueNum < 6)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > 5 && categoryValueNum < 11)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > 10 && categoryValueNum < 16)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		return risk;
	}

	//Calcualte Enroute Weather/Fuel Risk
	public int IFRRiskCalcEWF(String category, String categoryValue)
	{
		int risk = 0;
		int categoryValueNum;
		categoryValueNum = Integer.parseInt(categoryValue);
		if(category == "Ceiling(Day)")
		{
			if(categoryValueNum >= 1000)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 1000 && categoryValueNum > 799)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 800 && categoryValueNum > 599)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Ceiling(Night)")
		{
			if(categoryValueNum >= 1500)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 1500 && categoryValueNum > 1199)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 1200 && categoryValueNum > 999)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Visibility")
		{
			if(categoryValueNum >= 4)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == 3)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == 2)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Time Enroute")
		{
			if(categoryValueNum < 45)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >=45 || categoryValueNum <= 90)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > 90)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Thunderstorms")
		{
			if(categoryValueNum > 0 && categoryValueNum <= 10)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > 10 && categoryValueNum < 30)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 30)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Fuel at Alternate")
		{
			if(categoryValueNum < 90)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > 75 && categoryValueNum <= 90)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > 59 && categoryValueNum <= 75)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Alternate Airfields")
		{
			if(categoryValueNum >= 3)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == 2)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == 1)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		return risk;
	}

	//Calculate Student Human Factors Risk
	public int IFRRiskCalcHF(String category, String categoryValue)
	{
		int risk = 0;
		if(category == "Time of Flight")
		{
			if(categoryValue == "Day")
			{
				//risk is low (0)
			}
			else if(categoryValue = "Dusk")
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValue = "Night")
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Flight Duty Period began")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum < 9)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 12 && categoryValueNum >= 9)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum <= 11 && categoryValueNum >= 13)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Previous flights that day")
		{
			if(categoryValue != "None")
			{
				int categoryValueNum;
				categoryValueNum = Integer.parseInt(categoryValue);
			}
			if(categoryValue == "None")
			{
				//risk is low (0)
			}
			if(categoryValueNum == 1)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == 2)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Type of Syllabus Flight")
		{
			if(categoryValue == "Normal")
			{
				//risk is low(0)
			}
			else if(categoryValue == "Stage Check")
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValue == "FAA Check")
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Outside Temperatures")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum >= 55 && categoryValueNum <= 75)
			{
				//risk is low (0)
			}
			else if((categoryValueNum >= 76 && categoryValueNum <= 89) || 
			(categoryValueNum >= 30 && categoryValueNum <= 54))
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 90 || (categoryValueNum >= 10 && 
			categoryValueNum <= 29))
			{
				//risk is high(3)
				risk += 3;
			}
		}
		return risk;
	}
}
