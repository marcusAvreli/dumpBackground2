package dumpBackground2.model.base.dao;

import dumpBackground2.model.base.BaseManager;
import dumpBackground2.model.base.exception.BaseException;

/**
 *
 * @author mjcobo
 */
public class FactoryDAO {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  private BaseManager kbm;

  
  private AffiliationDAO affiliationDAO;
 

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  public FactoryDAO(BaseManager kbm) throws BaseException {

    this.kbm = kbm;


    affiliationDAO = new AffiliationDAO(kbm);
   
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/
  public AffiliationDAO getAffiliationDAO() {
	    return this.affiliationDAO;
	  }
  /**
   * @return the kbm
   */
  public BaseManager getKbm() {
    return kbm;
  }

  /**
   * @return the affiliationDAO
   */
 
 
  

 
  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}