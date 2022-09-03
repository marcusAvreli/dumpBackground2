package dumpBackground2.baseevents.event;

import dumpBackground2.model.base.exception.BaseException;

/**
 *
 * @author mjcobo
 */
public interface BaseEvent {
  
  public void fireEvent() throws BaseException; 
}