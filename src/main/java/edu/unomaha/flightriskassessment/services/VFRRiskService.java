package edu.unomaha.flightriskassessment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unomaha.flightriskassessment.models.IFRRiskModel;;

@Service
public class VFRRiskService {

	//Calculate Risk for VFR Categories
	public int VFRRiskCalc(String mainCategory, String category, String subcategory, String categoryValue)
	{
		int totalRiskLocal = 0;
		int totalRiskCCAF = 0;
		int totalRiskHF = 0;
		int totalRiskHFSolo = 0;
		if(mainCategory == "VFR: Local Pattern")
		{
			totalRiskLocal += VFRRiskCalcLocal(category, categoryValue);
			return totalRiskLocal;
		}
		if(mainCategory == "VFR Cross Country or Auxilary Field (Day or Night)")
		{
			totalRiskCCAF += VFRRiskCalcCCAF(category, subcategory, categoryValue);
			return totalRiskCCAF;
		}
		if(mainCategory == "Physiology/Psychology")
		{
			totalRiskHF += VFRRiskCalcHF(category, categoryValue);
			return totalRiskHF;
		}
		if(mainCategory == "Solo Factors")
		{
			totalRiskHFSolo += VFRRiskCalcHFSolo(category, categoryValue);
			return totalRiskHFSolo;
		}
	}

	//Calculate VFR Local Pattern Risk
	public int VFRRiskCalcLocal(String category, String categoryValue)
	{
		int risk = 0;
		int categoryValueNum;
		categoryValueNum = Integer.parseInt(categoryValue);
		if(category == "Ceiling(Day Dual)")
		{
			if(categoryValueNum >= 2500)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 2500 && categoryValueNum > 1999)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 2000 && categoryValueNum > 1499)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Ceiling(Day Solo)")
		{
			if(categoryValueNum >= 3000)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 3000 && categoryValueNum > 2499)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 2500 && categoryValueNum > 1999)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Ceiling(Night Dual)")
		{
			if(categoryValueNum >= 4000)
			{
				//risk is low (0)
			}
			else if(categoryValueNum < 4000 && categoryValueNum > 3499)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 3500 && categoryValueNum > 2999)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Visibility (Day)")
		{
			if(categoryValueNum >= 5)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == 4)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == 3)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Visibility (Night)")
		{
			if(categoryValueNum >= 7)
			{
				//risk is low (0)
			}
			else if(categoryValueNum == 6)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum == 5)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Total Wind")
		{
			if(categoryValueNum >= 0 && categoryValueNum <= 15)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 16 && categoryValueNum <= 20)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 21 && categoryValueNum <= 25)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Gust Increment")
		{
			if(categoryValueNum >= 0 && categoryValueNum <= 5)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 6 && categoryValueNum <= 8)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 9 && categoryValueNum <= 10)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Crosswind")
		{
			if(categoryValueNum >= 0 && categoryValueNum <= 5)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 6 && categoryValueNum <= 10)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 11 && categoryValueNum <= 15)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		return risk;
	}

	//Calculate VFR Risk for CCAF
	public int VFRRiskCalcCCAF(String category, String subcategory,String categoryValue)
	{
		int risk = 0;
		if(subcategory == "Departure" || subcategory == "Destination" ||
		 subcategory == "Alternate")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(category == "Ceiling")
			{
				if(categoryValueNum >= 4000)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 3500 && categoryValueNum <= 3999)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 3000 && categoryValueNum <= 3499)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Visibility")
			{
				if(categoryValueNum >= 7)
				{
					//risk is low (0)
				}
				else if(categoryValueNum == 6)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum == 5)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Total Wind")
			{
				if(categoryValueNum >= 0 && categoryValueNum <= 15)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 16 && categoryValueNum <= 20)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 21 && categoryValueNum <= 25)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Gust Increment")
			{
				if(categoryValueNum >= 0 && categoryValueNum <= 5)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 6 && categoryValueNum <= 8)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 9 && categoryValueNum <= 10)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Crosswind")
			{
				if(categoryValueNum >= 0 && categoryValueNum <= 5)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 6 && categoryValueNum <= 10)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 11 && categoryValueNum <= 15)
				{
					//risk is high(3)
					risk += 3;
				}
			}
		}
		if(subcategory == "Enroute")
		{
			if(category != "VFR Checkpoints")
			{
				int categoryValueNum;
				categoryValueNum = Integer.parseInt(categoryValue);
			
			}
			if(category == "Ceiling")
			{
				if(categoryValueNum >= 4000)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 3500 && categoryValueNum <= 3999)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 3000 && categoryValueNum <= 3499)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Visibility")
			{
				if(categoryValueNum >= 7)
				{
					//risk is low (0)
				}
				else if(categoryValueNum == 6)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum == 5)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "VFR Checkpoints")
			{
				if(categoryValue == "Multiple")
				{
					//risk is low (0)
				}
				else if(categoryValue == "Moderate")
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValue == "Few to none")
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Time enroute")
			{
				if(categoryValueNum < 60)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 60 && categoryValueNum <= 120)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 120)
				{
					//risk is high(3)
					risk += 3;
				}
			}
			if(category == "Fuel at Alternate")
			{
				if(categoryValueNum > 60)
				{
					//risk is low (0)
				}
				else if(categoryValueNum >= 46 && categoryValueNum <= 60)
				{
					//risk is med(1)
					risk++;
				}
				else if(categoryValueNum >= 30 && categoryValueNum <= 45)
				{
					//risk is high(3)
					risk += 3;
				}
			}
		}
		return risk;
	}

	//Calculate Human Factors 
	public int VFRRiskCalcHF(String category, String categoryValue)
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

	public int VFRRiskCalcHFSolo(String category, String categoryValue)
	{
		int risk = 0;
		if(category == "Flight Location")
		{
			if(categoryValue == "Local Area")
			{
				//risk is low (0)
			}
			else if(categoryValue = "Aux Field")
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValue = "Cross Cntry")
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Gnd Reference Maneuvers")
		{
			if(categoryValue == "None")
			{
				//risk is low (0)
			}
			else if(categoryValue = "One")
			{
				//risk is med(1)
				risk++;
			}
			else //risk is 2+
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Experience in Airplane Type")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum > 15)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 5 && categoryValueNum <= 10)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum < 5)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Last dual landing(Private)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum < 4)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 5 && categoryValueNum <= 9)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 10 && categoryValueNum <= 14)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Last dual landing(Comm)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum < 14)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 14 && categoryValueNum <= 28)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 29 && categoryValueNum <= 45)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Last dual stalls(Private)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum < 4)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 5 && categoryValueNum <= 9)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 10 && categoryValueNum <= 14)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		if(category == "Last dual stalls(Comm)")
		{
			int categoryValueNum;
			categoryValueNum = Integer.parseInt(categoryValue);
			if(categoryValueNum < 14)
			{
				//risk is low (0)
			}
			else if(categoryValueNum >= 14 && categoryValueNum <= 28)
			{
				//risk is med(1)
				risk++;
			}
			else if(categoryValueNum >= 29 && categoryValueNum <= 45)
			{
				//risk is high(3)
				risk += 3;
			}
		}
		return risk;
	}
}
