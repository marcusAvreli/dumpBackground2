package dumpBackground2.project.observer;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import dumpBackground2.model.base.entity.Affiliation;
import dumpBackground2.model.base.entity.Token;
import dumpBackground2.model.base.exception.BaseException;
public class Observer {
	private static final Logger logger = LoggerFactory.getLogger(Observer.class);
	  private List<EntityObserver<Affiliation>> affiliationObservers = new ArrayList<EntityObserver<Affiliation>>();
	  private List<EntityObserver<Token>> tokenObservers = new ArrayList<EntityObserver<Token>>();
	  private ArrayList<EntityObserver<Affiliation>> journalObservers = new ArrayList<EntityObserver<Affiliation>>();
	  // ------------------------------------------------------------------------

	  /**
	   * Add a new observer to the journals.
	   *
	   * @param observer the new observer
	   */
	  public void addJournalObserver(EntityObserver<Affiliation> observer) {
		  logger.info("addJournalObserver");
	    this.journalObservers.add(observer);
	  }
	  public void addTokenObserver(EntityObserver<Token> observer) {
		  logger.info("addJournalObserver");
	    this.tokenObservers.add(observer);
	  }
	  public void removeTokenObserver(EntityObserver<Token> observer) {
		  logger.info("addJournalObserver");
	    this.tokenObservers.remove(observer);
	  }
	  /**
	   * Delete a observer from the journals
	   *
	   * @param observer the observer to remove
	   */
	  public void removeJournalObserver(EntityObserver<Affiliation> observer){

	    this.journalObservers.remove(observer);
	  }

	  /**
	   * Notify to the journal's observer that a change has happened in it.
	   */
	  public void fireJournalAdded(List<Affiliation> items)
	          throws BaseException {

	    int i;

	    for (i = 0; i < this.journalObservers.size(); i++) {

	      this.journalObservers.get(i).entityAdded(items);
	    }
	  }

	  /**
	   * Notify to the journal's observer that a change has happened in it.
	   */
	  public void fireJournalRemoved(List<Affiliation> items)
	          throws BaseException {
		  logger.info("fireJournalRemoved");
	    int i;

	    for (i = 0; i < this.journalObservers.size(); i++) {

	      this.journalObservers.get(i).entityRemoved(items);
	    }
	  }

	  /**
	   * Notify to the journal's observer that a change has happened in it.
	   */
	  public void fireJournalUpdated(List<Affiliation> items)
	          throws BaseException {
		  logger.info("fireJournalUpdated");
	    int i;

	    for (i = 0; i < this.journalObservers.size(); i++) {

	      this.journalObservers.get(i).entityUpdated(items);
	    }
	  }

	  /**
	   * Notify to the journal's observer that a change has happened in it.
	   */
	  public void fireJournalRefresh() throws BaseException {

	    int i;
	    logger.info("fireJournalRefresh");
	    for (i = 0; i < this.journalObservers.size(); i++) {

	      this.journalObservers.get(i).entityRefresh();
	    }
	  }
	  
	  public void fireKnowledgeBaseRefresh() throws BaseException {

		  logger.info("fireKnowledgeBaseRefresh");
		    fireJournalRefresh();
		    
		  }
	  public void addAffiliationObserver(EntityObserver<Affiliation> observer) {

		    this.affiliationObservers.add(observer);
		  }
 
	  public void removeAffiliationObserver(EntityObserver<Affiliation> observer){

		    this.affiliationObservers.remove(observer);
		  }
	  
	  public void fireAffiliationAdded(ArrayList<Affiliation> items)
	          throws BaseException {

	    int i;

	    for (i = 0; i < this.affiliationObservers.size(); i++) {

	      this.affiliationObservers.get(i).entityAdded(items);
	    }
	  }
	  /**
	   * Notify to the affiliation's observer that a change has happened in it.
	   */
	  public void fireAffiliationRemoved(ArrayList<Affiliation> items)
	          throws BaseException {

	    int i;

	    for (i = 0; i < this.affiliationObservers.size(); i++) {

	      this.affiliationObservers.get(i).entityRemoved(items);
	    }
	  }

	  /**
	   * Notify to the affiliation's observer that a change has happened in it.
	   */
	  public void fireAffiliationUpdated(ArrayList<Affiliation> items)
	          throws BaseException {

	    

	    for (int i = 0; i < this.affiliationObservers.size(); i++) {

	      this.affiliationObservers.get(i).entityUpdated(items);
	    }
	  }

	 
	  public void fireAffiliationRefresh() throws BaseException {
		for (int i = 0; i < this.affiliationObservers.size(); i++) {
			  this.affiliationObservers.get(i).entityRefresh();
	    }
	  }

	  public void fireTokenReceived() throws BaseException {
		  for (int i = 0; i < this.tokenObservers.size(); i++) {
			  this.tokenObservers.get(i).entityRefresh();
	    } 
	  }

}
