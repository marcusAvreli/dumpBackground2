package dumpBackground2.gui.components.globalpanel;


import javax.swing.JPanel;

/**
 * 
 * @author mjcobo
 * @param <M> the master {@link Entity}.
 */
public abstract class GlobalPanel<M extends Comparable<M>> extends JPanel {

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  private M masterItem;

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  public GlobalPanel() {

    this.masterItem = null;
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   * 
   * @param masterItem
   */
  public abstract void refresh(M masterItem);
  
  /**
   *
   * @return
   */
  public M getMasterItem() {

    return this.masterItem;
  }

  /**
   *
   * @param masterItem
   */
  protected void setMasterItem(M masterItem) {
    this.masterItem = masterItem;
  }

  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/

}