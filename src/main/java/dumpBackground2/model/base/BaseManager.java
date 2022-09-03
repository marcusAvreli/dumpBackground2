package dumpBackground2.model.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.observer.Observer;
import dumpBackground2.project.observer.StateObserver;


public class BaseManager {
	private Connection conn;

	 public BaseManager() {

		    this.conn = null;
		  }
	  public void createKnowledgeBase(String filePath) throws BaseException {

		   
	  }
	  public Connection getConnection() {

		    return this.conn;
		  }
	  
	  public boolean isClosed() {

		    return this.conn == null;
		  }
	  
	  
	  public void close() throws BaseException {

		    try {

		      if (this.conn != null) {

		        this.conn.close();

		        this.conn = null;
		      }

		    } catch (SQLException e) {

		      throw new BaseException(e.getMessage(), e.getCause());

		    }
		  }
	  public void loadKnowledgeBase() throws BaseException {

		    try {

		      Class.forName("org.apache.derby.jdbc.ClientDriver");

		      // Connect to the database
		   
		      this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/C:/derby/demo/databases/MyDbTest;create=true","admin","admin");
		      //this.conn.setAutoCommit(false);

		      // Check if the database is incorrect.

		    
		    } catch (ClassNotFoundException e1) {

		      close();

		      throw new BaseException(e1.getMessage(), e1.getCause());

		    } catch (SQLException e2) {

		      close();

		      throw new BaseException(e2.getMessage(), e2.getCause());
		    }
		  }

	  
	  public void commit() throws BaseException {

		    try {

		      if (!isClosed()) {

		        this.conn.commit();

		      } else {

		        throw new BaseException("The knowledge base must be loaded.");
		      }

		    } catch (SQLException e) {

		      throw new BaseException(e);
		    }
		  }

		  /**
		   * 
		   * @throws BaseException if an error occurs or the knowledge base is closed.
		   */
		  public void rollback() throws BaseException {

		    try {

		      if (!isClosed()) {

		        this.conn.rollback();

		      } else {

		        throw new BaseException("The knowledge base must be loaded.");
		      }

		    } catch (SQLException e) {

		      throw new BaseException(e);
		    }
		  }
}
