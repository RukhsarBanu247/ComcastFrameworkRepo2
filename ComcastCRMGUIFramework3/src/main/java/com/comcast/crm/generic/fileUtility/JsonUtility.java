package com.comcast.crm.generic.fileUtility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import oracle.jdbc.driver.configuration.JsonParser;

public class JsonUtility
{
	public String getDataFromJsonFile(String key) throws Exception,ParseException
	{
		FileReader fr=new FileReader(".\\configAppData\\JsonCommonData.json");
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fr);
		JSONObject map=(JSONObject)obj;
		String data=(String) map.get(key);
		return data;
		
		
	}
	

}
