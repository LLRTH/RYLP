package org.camunda.bpm.getstarted.bankkundenurlaubskreditwerbung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class WeatherData{
	
	private double temp;
	private int cloudiness;
	
	public WeatherData(double temp, int cloudiness) {
		this.temp = temp;
		this.cloudiness = cloudiness;
	}
	
	public double getTemp() {
		return this.temp;
	}
	
	public int getCloudiness() {
		return this.cloudiness;
	}
}

public class API {

	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  public static WeatherData apiRequest() throws JSONException, IOException {
		  
		  //reads cloudiness and temperature from web
		  String url = "https://api.openweathermap.org/data/2.5/forecast?q=Stralsund&units=metric&appid=aValidAPIKey";
		  JSONObject json = readJsonFromUrl(url);
		  System.out.println(json);
		  JSONArray list = json.getJSONArray("list");
		  int n = list.length();
		  int m = 0; //count of weather vectors which time stamp is between 6 and 22 o'clock
		  			 //we only want to consider the weather vectors which are during the day (not in the night)
		  Double averageCloudiness = 0.0;
		  Double averageTemp = 0.0;
		  for (int i = 0; i < n; i++) {
			JSONObject listEntry = list.getJSONObject(i);
			//extract time of future
			long unixTimeStamp = listEntry.getLong("dt");
			java.util.Date dt = new java.util.Date(unixTimeStamp*1000);
			int hour = dt.getHours();
			if (hour >= 6 && hour <= 22) {
				//extract cloudiness
				JSONObject clouds = listEntry.getJSONObject("clouds");
				averageCloudiness += clouds.getDouble("all");
				//extract temperature
				JSONObject main = listEntry.getJSONObject("main");
				averageTemp += main.getDouble("temp");
				m++;
			}
	      }
		  averageTemp = averageTemp / m;
		  averageTemp = ((double) Math.round(averageTemp*100))/100;
		  int averageCloudiness_int = (int) Math.round(averageCloudiness / m);
		  
		  return new WeatherData(averageTemp, averageCloudiness_int);
	  }
}
