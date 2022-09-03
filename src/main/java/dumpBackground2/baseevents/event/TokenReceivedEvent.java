package dumpBackground2.baseevents.event;

import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.Context;

public class TokenReceivedEvent implements BaseEvent {

	  /***************************************************************************/
	  /*                        Private attributes                               */
	  /***************************************************************************/
	  
	  /***************************************************************************/
	  /*                            Constructors                                 */
	  /***************************************************************************/
	  
	
	  public TokenReceivedEvent() {
	  
	  }
	  
	
	  public void fireEvent() throws BaseException {
	    
	    Context.getInstance().getKbObserver().fireTokenReceived();
	  }
	  
	  /***************************************************************************/
	  /*                           Private Methods                               */
	  /***************************************************************************/
	}
