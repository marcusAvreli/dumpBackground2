package dumpBackground2.gui.commands.task;

import java.awt.Window;
import javax.swing.JComponent;


import dumpBackground2.gui.commands.edit.BaseEdit;
import dumpBackground2.model.base.exception.BaseException;

/**
 *
 * @author mjcobo
 */
public class PerformKnowledgeBaseEditTask {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  /**
   * 
   */
  private BaseEdit edit;
  private JComponent component;

  private boolean successful;


  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  /**
   * 
   * @param edit
   * @param compenent
   */
  public PerformKnowledgeBaseEditTask(BaseEdit edit, JComponent compenent) {

    this.edit = edit;
    this.component = compenent;
    this.successful = false;
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   */
  public void execute() {

    try {
      
     // CursorManager.getInstance().setWaitCursor();
      this.successful = this.edit.execute();
      //CursorManager.getInstance().setNormalCursor();

      if (! this.successful) {

      //  ErrorDialogManager.getInstance().showError(edit.getErrorMessage());
      }

    } catch (BaseException e) {

      this.successful = false;

     // ErrorDialogManager.getInstance().showException(e);

      e.printStackTrace(System.err);

    }
  }

  /**
   * 
   * @return
   */
  public boolean isSuccessful() {
    return successful;
  }

  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}