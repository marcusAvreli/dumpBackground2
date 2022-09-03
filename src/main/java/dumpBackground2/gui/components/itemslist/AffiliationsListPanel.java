package dumpBackground2.gui.components.itemslist;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpBackground2.App;
import dumpBackground2.gui.components.tablemodel.AffiliationsTableModel;
import dumpBackground2.model.base.dao.FactoryDAO;
import dumpBackground2.model.base.entity.Affiliation;
import dumpBackground2.model.base.entity.Token;
import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.Context;
import dumpBackground2.project.observer.EntityObserver;
import dumpBackground2.project.observer.StateObserver;

/**
 *
 * @author mjcobo
 */
public class AffiliationsListPanel 
extends GenericDynamicItemsListPanel<Affiliation> 
implements EntityObserver<Affiliation>,StateObserver{
	private static final Logger logger = LoggerFactory.getLogger(AffiliationsListPanel.class);
	private Token token; 
  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/
  
  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/  

  public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

/**
   * 
   * @param tableModel 
   */
  public AffiliationsListPanel() {
    super(new AffiliationsTableModel());
    
    Context.getInstance().getKbObserver().addAffiliationObserver(this);
    Context.getInstance().addKnowledgeBaseStateObserver(this);
    try {
		entityRefresh();
	} catch (BaseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     }
  
  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/



  /**
   * 
   * @throws BaseException 
   */
  public void entityRefresh() throws BaseException {
	  logger.info("entity_refresh:"+getToken());
	  Token localToken = getToken();
	  FactoryDAO factory = Context.getInstance().getFactoryDAO();
	  if(null != factory) {
		  refreshItems(factory.getAffiliationDAO().getAffiliations(localToken));
		  
	  }else {
		  logger.info("factory is null");
	  }
	  refreshItems(Context.getInstance().getAffiliationDAO().getAffiliations(localToken));
  }

	@Override
	public void entityUpdated(List<Affiliation> items) throws BaseException {
		// TODO Auto-generated method stub
		 updateItems(items);
	}
	
	@Override
	public void entityAdded(List<Affiliation> items) throws BaseException {
		// TODO Auto-generated method stub
		 addItems(items);
	}
	
	@Override
	public void entityRemoved(List<Affiliation> items) throws BaseException {
		// TODO Auto-generated method stub
		removeItems(items);
	}

	@Override
	public void stateChanged(boolean loaded) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tokenRefreshed(Token token) {
		// TODO Auto-generated method stub
		if(null != token) {
			try {
				setToken(token);
				logger.info("token refreshed from list panel: "+token.getAccessToken());
				entityRefresh();
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	  
  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}