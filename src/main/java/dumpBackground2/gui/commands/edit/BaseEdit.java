package dumpBackground2.gui.commands.edit;

import dumpBackground2.model.base.exception.BaseException;

public abstract class BaseEdit {

	  /***************************************************************************/
	  /*                        Private attributes                               */
	  /***************************************************************************/

	  /**
	   *
	   */
	  protected String errorMessage;

	  /***************************************************************************/
	  /*                            Constructors                                 */
	  /***************************************************************************/

	  public BaseEdit() {
	    super();

	    this.errorMessage = "";
	  }

	  /***************************************************************************/
	  /*                           Public Methods                                */
	  /***************************************************************************/

	  /**
	   *
	   * @return
	   */
	  public String getErrorMessage() {
	    return errorMessage;
	  }

	  /**
	   *
	   */
	  public abstract boolean execute() throws BaseException;

	 
	  /***************************************************************************/
	  /*                           Private Methods                               */
	  /***************************************************************************/
	}