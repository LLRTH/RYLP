package org.camunda.bpm.getstarted.bankkundenurlaubskreditwerbung;

import java.util.logging.Logger;

import javax.mail.MessagingException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class WerbungSenden implements JavaDelegate {

	  private final static Logger LOGGER = Logger.getLogger(WerbungSenden.class.getName()); 
	  
	  public static void sendMail(String subject, String content) throws MessagingException {
	    	
	    	GoogleMail.sendMail(subject, content);
	    	
	  }   
	  
	  public void execute(DelegateExecution execution) throws Exception {
		
		Integer Bewoelkung_in_Prozent = (Integer) execution.getVariable("Bewoelkung_in_Prozent"); 
	    Double Temperatur_in_Celsius = (Double) execution.getVariable("Temperatur_in_Celsius");
	    String Werbeprogramm = (String) execution.getVariable("Werbeprogramm");
				  
		String subject = "Urlaubskredit - " + Werbeprogramm;
		String content = "Sehr geehrter Kunde,\n"
						 + "\n"
						 + "nehmen Sie noch heute einen Kredit in Höhe von 500 Euro auf und finanzieren Sie sich ihren nächsten "
						 + Werbeprogramm
						 + " an der Ostsee, bspw. in der Hansestadt Stralsund. Die dortigen Wetterkonditionen in der nächsten Woche \n"
						 + "- durchschnittliche Bewoelkung " + Bewoelkung_in_Prozent +" % \n"
						 + "- durchschnittliche Temperatur " + Temperatur_in_Celsius +" Grad Celsius \n"
						 + "bieten sich für einen "
						 + Werbeprogramm
						 + " an. \n"
						 + "Kontaktieren Sie uns auf unserer Website, per E-Mail oder telefonisch für weitere Informationen.\n"
						 + "\n"
						 + "Mit freundlichen Grüßen\n"
						 + "\n"
						 + "Ihr Service Team";
		
	    sendMail(subject,content);
	    
	    LOGGER.info("Werbung gesendet");
	  }
	  
}

