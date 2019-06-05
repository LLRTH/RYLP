package org.camunda.bpm.getstarted.smarthome;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Rolladen50herunterfahren implements JavaDelegate{

	 private final static Logger LOGGER = Logger.getLogger(Rolladen50herunterfahren.class.getName()); 
	
	 public void execute(DelegateExecution execution) throws Exception {
		 execution.setVariable("endeAktorbewegung", true);
		 LOGGER.info("**** Rolladen50herunterfahren !!!!!!");
	 }
	 
}
