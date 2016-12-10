package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class DataProvider_Search {
	
	@DataProvider(name="dp_InvalidSearch")
	public static Iterator<Object[]> getInvalidSearchdata() throws IOException
	{
		
		List<Object[]> Obj = flagRowCount("Invalid_Search");
		return Obj.iterator();
		
		
	}
	
	@DataProvider(name="dp_ValidSearch")
	public static Iterator<Object[]> getValidSearchdata() throws IOException
	{
		
		List<Object[]> Obj = flagRowCount("Valid_Search");
		return Obj.iterator();
		
	}
	
	
	public static List<Object[]> flagRowCount(String Scriptname) throws IOException
	{
		ExcelReadWrite xl= new  ExcelReadWrite("D:\\September_BB_Proj\\TestData\\TestData.xls");
		HSSFSheet Scenario_Search = xl.Setsheet("Scenario_Search");
		
		int RowCount = xl.getrowcount(Scenario_Search);
		int ColCount = xl.getcolcount(Scenario_Search, 0);
		
		
		List<Object[]> arrlist_Search= new ArrayList<Object[]>();
		
		for(int irow=1;irow<=RowCount;irow++)
		{
			String Execute_Flag = xl.Readvalue(Scenario_Search, irow, "Execute_Flag");
			String Script_name = xl.Readvalue(Scenario_Search, irow, "Script_name");
			
			if((Execute_Flag.equals("Y")) && (Script_name.equals(Scriptname)))
			{
				Map<String, String> dSMap= new HashMap<String, String>();
				
				for(int jcol=0;jcol<=ColCount;jcol++)
				{
					
					String Key = xl.Readvalue(Scenario_Search, 0, jcol);
					String Value = xl.Readvalue(Scenario_Search, irow, jcol);
					
					dSMap.put(Key, Value);			
					
					
				}//end of col loop		
				
				Object[] x= new Object[1];
				x[0]=dSMap;
				
				arrlist_Search.add(x);
				
			}//end of if
			
		}//end of row loop
		
		
		return arrlist_Search;
		
		
	}

}
