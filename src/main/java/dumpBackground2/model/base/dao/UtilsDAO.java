package dumpBackground2.model.base.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UtilsDAO {
	  public static UtilsDAO getInstance() {
		    return UtilsDAOHolder.INSTANCE;
		  }
		  
		  /**
		   * 
		   */
		  private static class UtilsDAOHolder {

		    private static final UtilsDAO INSTANCE = new UtilsDAO();
		  }
		 
}
