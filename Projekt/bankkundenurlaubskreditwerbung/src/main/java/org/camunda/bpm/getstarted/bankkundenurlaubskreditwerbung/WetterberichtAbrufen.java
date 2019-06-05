package org.camunda.bpm.getstarted.bankkundenurlaubskreditwerbung;

import java.io.IOException;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.json.JSONException; 


public class WetterberichtAbrufen implements JavaDelegate {

	  private final static Logger LOGGER = Logger.getLogger(WetterberichtAbrufen.class.getName()); 
	  
	  public static WeatherData getWeather() throws JSONException, IOException {
		  
		  return API.apiRequest();
	  }
	  
	  public void execute(DelegateExecution execution) throws Exception {
		
		WeatherData wd = getWeather();  
		Integer Bewoelkung_in_Prozent = new Integer(wd.getCloudiness());
		Double Temperatur_in_Celsius = new Double(wd.getTemp());
		execution.setVariable("Bewoelkung_in_Prozent", Bewoelkung_in_Prozent);
	    execution.setVariable("Temperatur_in_Celsius", Temperatur_in_Celsius);
	    LOGGER.info("Wetterbericht abgerufen");
	       
	  }
	  
}

