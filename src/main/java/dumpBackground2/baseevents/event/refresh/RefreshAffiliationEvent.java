package dumpBackground2.baseevents.event.refresh;


import dumpBackground2.baseevents.event.BaseEvent;
import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.Context;


/**
 *
 * @author mjcobo
 */
public class RefreshAffiliationEvent implements BaseEvent {

 
  public void fireEvent() throws BaseException {
    
    Context.getInstance().getKbObserver().fireAffiliationRefresh();
  }
  

}