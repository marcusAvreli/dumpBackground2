package dumpBackground2.baseevents;

import java.util.ArrayList;
import java.util.List;

import dumpBackground2.baseevents.event.refresh.RefreshAffiliationEvent;
import dumpBackground2.baseevents.event.BaseEvent;
import dumpBackground2.model.base.exception.BaseException;

/**
 *
 * @author mjcobo
 */
public class BaseEventsReceiver {

  

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/
  
  private List<BaseEvent> knowledgeBaseEvents;
  
  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/
  
  /**
   * 
   */
  private BaseEventsReceiver() {
    
    this.knowledgeBaseEvents = new ArrayList<BaseEvent>();
  }
  
  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/
  
  /**
   * 
   * @return
   */
  public static BaseEventsReceiver getInstance() {
    return KnowledgeBaseEventsReceiverSingleton.INSTANCE;
  }

  /**
   * 
   */
  private static class KnowledgeBaseEventsReceiverSingleton {
    private static final BaseEventsReceiver INSTANCE = new BaseEventsReceiver();
  }
  
  /**
   * 
   * @param refreshAffiliationEvent 
   */
  public void addEvent(BaseEvent refreshAffiliationEvent) {
      
    this.knowledgeBaseEvents.add(refreshAffiliationEvent);
  }
  
  /**
   * 
   * @throws KnowledgeBaseException 
   */
  public void fireEvents() throws BaseException {
  
    int i;
    
    for (i = 0; i < this.knowledgeBaseEvents.size(); i++) {
      
      this.knowledgeBaseEvents.get(i).fireEvent();
    }
    
    this.knowledgeBaseEvents.clear();
  }
  
  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}