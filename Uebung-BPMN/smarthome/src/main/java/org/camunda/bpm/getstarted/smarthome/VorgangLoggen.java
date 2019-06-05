package org.camunda.bpm.getstarted.smarthome;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class VorgangLoggen implements JavaDelegate{

	 private final static Logger LOGGER = Logger.getLogger(VorgangLoggen.class.getName()); 
	
	 public void execute(DelegateExecution execution) throws Exception {
		 String logEntry = "[LogEntry: "+
			 "Cloudiness: " + execution.getVariable("cloudiness") + "% ;"+
			 "blendClosedDegree: " + execution.getVariable("blendClosedDegree") +"%]";
		 LOGGER.info(logEntry);
	 }
	 
}
