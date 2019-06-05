package org.camunda.bpm.getstarted.bankkundenurlaubskreditwerbung;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class VerbindungsausfallLoggen implements JavaDelegate {

	  private final static Logger LOGGER = Logger.getLogger(VerbindungsausfallLoggen.class.getName());
	  
	  public void execute(DelegateExecution execution) throws Exception {
		
		LOGGER.info("Verbindungsausfall");
	    
	  }
	  
}

