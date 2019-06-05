package org.camunda.bpm.getstarted.smarthome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class zAPI {

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
	  
	  public static int apiRequest() throws JSONException, IOException {
		  
		  //reads cloudiness from web
		  String url = "https://api.openweathermap.org/data/2.5/weather?q=Potsdam&appid=aValidAPIKey";
		  JSONObject json = readJsonFromUrl(url);
		  JSONObject clouds = json.getJSONObject("clouds");
		  int cloudinessResult = clouds.getInt("all");
		  
		  return cloudinessResult;
	  }
	  	
}
