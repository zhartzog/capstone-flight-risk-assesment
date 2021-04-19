package edu.unomaha.flightriskassessment.services;

import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.ElementScanner14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.models.VFRRiskModel;
import edu.unomaha.flightriskassessment.database.AdminTableRepository;
import edu.unomaha.flightriskassessment.models.AdminTable; 
import edu.unomaha.flightriskassessment.services.AdminTableService;

@Service
public class VFRRiskService {

	//Calculate Risk for VFR Categories
	//UPDATE VALUES WITH getThresholdByGroupNameCategory(String group, String name, String category)
	public int VFRRiskCalc(VFRRiskModel riskModel)
	{
		int totalRiskLocal = 0;
		int totalRiskCCAF = 0;
		int totalRiskHF = 0;
		int totalRiskHFSolo = 0;
		if(riskModel.getMainCategory() == "VFR: Local Pattern")
		{
			totalRiskLocal += VFRRiskCalcLocal(riskModel);
			return totalRiskLocal;
		}
		if(riskModel.getMainCategory() == "VFR Cross Country or Auxilary Field (Day or Night)")
		{
			totalRiskCCAF += VFRRiskCalcCCAF(riskmodel);
			return totalRiskCCAF;
		}
		if(riskModel.getMainCategory() == "Physiology/Psychology")
		{
			totalRiskHF += VFRRiskCalcHF(riskModel);
			return totalRiskHF;
		}
		if(riskModel.getMainCategory() == "Solo Factors")
		{
			totalRiskHFSolo += VFRRiskCalcHFSolo(riskModel);
			return totalRiskHFSolo;
		}
	}

	//Calculate VFR Local Pattern Risk
	public int VFRRiskCalcLocal(VFRRiskModel riskModel)
	{
		int risk = 0;
		int categoryValueNum;
		categoryValueNum = Integer.parseInt(categoryValue);
		AdminTable tempThreshold;
		int low, med, high;
		if(riskModel.getCategory() == "Ceiling (Day Dual)")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Ceiling (Day Dual)", "localPattern");
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
		if(riskModel.getCategory() == "Ceiling (Day Solo)")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Ceiling (Day Solo)", "localPattern");
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
		if(riskModel.getCategory() == "Ceiling (Night Dual)")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Ceiling (Night Dual)", "localPattern");
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
		if(riskModel.getCategory() == "Visibility (Day)")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Visibility (Day)", "localPattern");
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
		if(riskModel.getCategory() == "Visibility (Night)")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Visibility (Night)", "localPattern");
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
		if(riskModel.getCategory() == "Total Wind")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Total Wind", "localPattern");
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
			tempThreshold = getThresholdByGroupName("vfr", "Gust Increment", "localPattern");
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
			tempThreshold = getThresholdByGroupName("vfr", "Crosswind", "localPattern");
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

	//Calculate VFR Risk for CCAF
	public int VFRRiskCalcCCAF(VFRRiskModel riskModel)
	{
		int risk = 0;
		AdminTable tempThreshold;
		int low, med, high;
		if(riskModel.getCategory() != "VFR Checkpoints")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(riskModel.getCategory() == "Ceiling" && riskModel.getSubcategory() == "departure")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Ceiling", "departure");
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
			if(riskModel.getCategory() == "Ceiling" && riskModel.getSubcategory() == "destination")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Ceiling", "destination");
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
			if(riskModel.getCategory() == "Ceiling" && riskModel.getSubcategory() == "alternate")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Ceiling", "alternate");
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
			if(riskModel.getCategory() == "Visibility" && riskModel.getSubcategory() == "departure")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Visibility", "departure");
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
			if(riskModel.getCategory() == "Visibility" && riskModel.getSubcategory() == "destination")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Visibility", "destination");
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
			if(riskModel.getCategory() == "Visibility" && riskModel.getSubcategory() == "alternate")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Visibility", "alternate");
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
			if(riskModel.getCategory() == "Total Wind" && riskModel.getSubcategory() == "departure")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Total Wind", "departure");
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
			if(riskModel.getCategory() == "Total Wind" && riskModel.getSubcategory() == "destination")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Total Wind", "destination");
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
			if(riskModel.getCategory() == "Total Wind" && riskModel.getSubcategory() == "alternate")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Total Wind", "alternate");
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
			if(riskModel.getCategory() == "Gust Increment" && riskModel.getSubcategory() == "departure")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Gust Increment", "departure");
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
			if(riskModel.getCategory() == "Gust Increment" && riskModel.getSubcategory() == "destination")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Gust Increment", "destination");
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
			if(riskModel.getCategory() == "Gust Increment" && riskModel.getSubcategory() == "alternate")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Gust Increment", "alternate");
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
			if(riskModel.getCategory() == "Crosswind" && riskModel.getSubcategory() == "departure")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Crosswind", "departure");
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
			if(riskModel.getCategory() == "Crosswind" && riskModel.getSubcategory() == "destination")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Crosswind", "destination");
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
			if(riskModel.getCategory() == "Crosswind" && riskModel.getSubcategory() == "alternate")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Crosswind", "alternate");
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
		}
		if(riskModel.getSubcategory() == "Enroute")
		{
			if(riskModel.getCategory() != "VFR Checkpoints")
			{
				int categoryValueNum;
				categoryValueNum = Integer.parseInt(categoryValue);
			
			}
			if(riskModel.getCategory() == "Ceiling")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Ceiling", "enroute");
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
				tempThreshold = getThresholdByGroupName("vfr", "Visibility", "enroute");
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
			if(riskModel.getCategory() == "VFR Checkpoints")
			{
				tempThreshold = getThresholdByGroupName("vfr", "VFR Checkpoints", "enroute");
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
			if(riskModel.getCategory() == "Time Enroute")
			{
				tempThreshold = getThresholdByGroupName("vfr", "Time Enroute", "enroute");
				low = Integer.parseInt(tempThreshold.getLow());
				med = Integer.parseInt(tempThreshold.getMed());
				if(categoryValueNum < low)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= low && categoryValueNum <= med)
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
				tempThreshold = getThresholdByGroupName("vfr", "Fuel at Alternate", "enroute");
				low = Integer.parseInt(tempThreshold.getLow());
				med = Integer.parseInt(tempThreshold.getMed());
				high = Integer.parseInt(tempThreshold.getHigh());
				if(categoryValueNum > low)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= med && categoryValueNum <= low)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= high && categoryValueNum < med)
				{
					//risk is high(3)
					risk += 3;
				}
				else
				{
					//Auto no go
					risk += 15;
				}
			}
		}
		return risk;
	}

	//Calculate Human Factors 
	public int VFRRiskCalcHF(VFRRiskModel riskModel)
	{
		int risk = 0;
		AdminTable tempThreshold;
		int low, med, high;
		if(riskModel.getCategory() == "Time of Flight")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Time of Flight", "physiology");
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
			tempThreshold = getThresholdByGroupName("vfr", "Flight Duty Period Began", "physiology");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med && categoryValueNum < high)
			{
				//risk is high(3)
				risk += 3;
			}
			else if(categoryValueNum > high)
			{
				//risk is not allowed, no go
				risk += 10;
			}
		}
		if(riskModel.getCategory() == "Previous Flights that Day")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Previous Flights that Day", "physiology");
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
			tempThreshold = getThresholdByGroupName("vfr", "Type of Syllabus Flight", "physiology");
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
		if(category == "Outside Temperatures High")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Outside Temperatures High", "physiology");
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
			tempThreshold = getThresholdByGroupName("vfr", "Outside Temperatures Low", "physiology");
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

	//Calculate SoloFactors
	public int VFRRiskCalcHFSolo(VFRRiskModel riskModel)
	{
		AdminTable tempThreshold;
		int risk = 0;
		int low, med, high;
		if(riskModel.getCategory() == "Flight Location")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Flight Location", "soloFactors");
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
		if(riskModel.getCategory() == "Gnd Reference Manuevers")
		{
			tempThreshold = getThresholdByGroupName("vfr", "Gnd Reference Manuevers", "soloFactors");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(riskModel.getCategoryValue());
			if(categoryValueNum == low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == high) //risk is 2+
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Experience in Airplane Type")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			tempThreshold = getThresholdByGroupName("vfr", "Experience in Airplane Type", "soloFactors");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum > low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= high && categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Last Dual Landing (Private)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			tempThreshold = getThresholdByGroupName("vfr", "Last Dual Landing (Private)", "soloFactors");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > low && categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med && categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Last Dual Landing (Comm)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			tempThreshold = getThresholdByGroupName("vfr", "Last Dual Landing (Comm)", "soloFactors");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= low && categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med && categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Last Dual Stalls (Private)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			tempThreshold = getThresholdByGroupName("vfr", "Last Dual Stalls (Private)", "soloFactors");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum > low && categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med && categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(riskModel.getCategory() == "Last Dual Stalls (Comm)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			tempThreshold = getThresholdByGroupName("vfr", "Last Dual Stalls (Comm)", "soloFactors");
			low = Integer.parseInt(tempThreshold.getLow());
			med = Integer.parseInt(tempThreshold.getMed());
			high = Integer.parseInt(tempThreshold.getHigh());
			if(categoryValueNum < low)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= low && categoryValueNum <= med)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum > med && categoryValueNum <= high)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		return risk;
	}
}
