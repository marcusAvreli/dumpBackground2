package dumpBackground2.project;

import java.util.LinkedList;

import dumpBackground2.model.base.BaseManager;
import dumpBackground2.model.base.dao.AffiliationDAO;
import dumpBackground2.model.base.dao.FactoryDAO;
import dumpBackground2.model.base.entity.Token;
import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.observer.Observer;
import dumpBackground2.project.observer.StateObserver;

public class Context {
	public boolean loggedIn;

	BaseManager kbm;
	private FactoryDAO factoryDAO;
	private String baseUrl="http://192.168.243.133:8080/iiq/oauth2";
	private Token accessToken = null;
	public Token getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(Token accessToken) {
		this.accessToken = accessToken;
		notfiyTokenReceived(accessToken);
	}
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Lista de observadores del estado de la base de conocimiento.
	 */
	  

	private LinkedList<StateObserver> stateObservers = new LinkedList<StateObserver>();

	private Observer kbObserver = new Observer();
	 public Observer getKbObserver() {
		    return kbObserver;
		  }
	private Context() {

	}

	/***************************************************************************/
	/* Public Methods */
	/***************************************************************************/

	/**
	 * 
	 * @return
	 */
	public static Context getInstance() {
		return ContextSingleton.INSTANCE;
	}
	
	public AffiliationDAO getAffiliationDAO() {
		return new AffiliationDAO();
	}

	/**
	 * 
	 */
	private static class ContextSingleton {
		private static final Context INSTANCE = new Context();
	}

	/**
	 * Añade un nuevo observador del estado de la base de conocimiento.
	 * 
	 * @param observer observador de la base de concimiento.
	 */
	
	  public void newProyect(String folderPath, String filePath) throws BaseException {

		    if (! isKnowledbaseLoaded()) {

		       try {

		       

		        this.kbm = new BaseManager();

		       
		        
		        this.factoryDAO = new FactoryDAO(this.kbm);

		        this.notifyKnowledgeBaseObsever(isKnowledbaseLoaded());
		        this.kbObserver.fireKnowledgeBaseRefresh();

		      } catch (BaseException e) {

		        this.kbm = null;

		        throw e;
		      }
		    }
		  }
	public void addKnowledgeBaseStateObserver(StateObserver observer) {

		this.stateObservers.add(observer);

		observer.stateChanged(isLoggedIn());
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public BaseManager getKnowledgeBase() {

		return this.kbm;
	}

	public FactoryDAO getFactoryDAO() {
		return factoryDAO;
	}
	 public boolean isKnowledbaseLoaded() {
		    return this.kbm != null;
		  }
	 
	 public void notifyKnowledgeBaseObsever(boolean state) {
		  
		    for (int i = 0; i < stateObservers.size(); i++) {
		      stateObservers.get(i).stateChanged(state);
		    }
		  }
	 public void notfiyTokenReceived(Token state) {
		  
		    for (int i = 0; i < stateObservers.size(); i++) {
		      stateObservers.get(i).tokenRefreshed(state);
		    }
		  }
		  
}
