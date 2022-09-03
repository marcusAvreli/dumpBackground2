package dumpBackground2.gui.commands.edit.update;

import java.util.ArrayList;
import javax.swing.undo.CannotUndoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import dumpBackground2.gui.commands.edit.BaseEdit;
import dumpBackground2.gui.components.globalpanel.LoginPanel;
import dumpBackground2.model.base.entity.Affiliation;
import dumpBackground2.model.base.exception.BaseException;
import dumpBackground2.project.Context;
import dumpBackground2.task.AnswerWorker;

/**
 *
 * @author mjcobo
 */
public class UpdateAffiliationEdit extends BaseEdit {
	private static final Logger logger = LoggerFactory.getLogger(UpdateAffiliationEdit.class);
  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/
private String username;
private String password;
  /**
   * The ID of the Affiliation
   */
  private Integer affiliationID;

  /**
   * This attribute contains the complete affiliation.
   */
  private String fullAffilliation;

  /**
   * The elements added
   */
  private ArrayList<Affiliation> affiliationsUpdated;

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  /**
   * 
   * @param fullAffilliation
   */
  public UpdateAffiliationEdit(String username, String password) {
    super();

    this.username = username;
    this.password = password;
    
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   * @throws BaseException
   */
  @Override
  public boolean execute() throws BaseException {

    boolean successful = true;
    new AnswerWorker(this.username,this.password).execute();
   logger.info("execute edit");
   
System.out.println("execute edit");
    return successful;
  
  }
 
  /**
   *
   * @throws CannotUndoException
   */

  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}