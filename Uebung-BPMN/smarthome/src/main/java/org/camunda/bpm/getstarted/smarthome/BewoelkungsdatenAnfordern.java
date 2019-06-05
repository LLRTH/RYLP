package org.camunda.bpm.getstarted.smarthome;

import java.io.IOException;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.json.JSONException;
import org.xml.sax.SAXException;

public class BewoelkungsdatenAnfordern implements JavaDelegate{

	private final static Logger LOGGER = Logger.getLogger(BewoelkungsdatenAnfordern.class.getName()); 
	
	public static void generateUserOutput(String pathToImage, int cloudiness, int blendClosedDegree)
	throws MessagingException, ParserConfigurationException, SAXException, IOException, TransformerException {
		
		zGenerateUserOutput.generateUserOutput(pathToImage, ""+cloudiness, ""+blendClosedDegree);	
	}
	
	public static int getCloudiness() throws JSONException, IOException {
		return zAPI.apiRequest();
	}
		
	public void execute(DelegateExecution execution) throws JSONException, IOException, MessagingException, ParserConfigurationException, SAXException, TransformerException{		
		
		int x = 0; //x means which path is chosen. 80% path is 1; 50% path is 2 and 3 is default path.
		
		String pathToImage= "";
		int cloudiness = getCloudiness();//Values for Output
		int blendClosedDegree = 0;
		
		//Set route through the process model regarding the cloudiness
		if(cloudiness <= 20){
			x = 1;
			blendClosedDegree=80;
			pathToImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPW5q1VFixy9OyShwsdizlkzE3SHjSSx5MzhifOPkyaaNXLRwC";
		}
		else if(cloudiness <= 40) {
			x = 2;
			blendClosedDegree=50;
			pathToImage = "https://rolladen-elsa.de/wp-content/uploads/2017/03/4.jpg";
		}
		else {
			x = 3;
			blendClosedDegree=0;
			pathToImage = "https://www.fenster-heitkaemper.de/wp-content/uploads/2014/03/Heitkaemper-Startseite_Fenster.jpeg";
		}
		execution.setVariable("x", x);
		execution.setVariable("cloudiness", cloudiness);
		execution.setVariable("blendClosedDegree", blendClosedDegree);
		
		//generate Form "DatenEinsehen"
		generateUserOutput(pathToImage,cloudiness,blendClosedDegree);
		
		LOGGER.info("**** BewoelkungsdatenAnfordern !!!!!!");
	}
	
}

