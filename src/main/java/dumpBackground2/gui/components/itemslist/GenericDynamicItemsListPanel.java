package dumpBackground2.gui.components.itemslist;


import java.util.ArrayList;
import java.util.List;

import dumpBackground2.gui.components.tablemodel.GenericDynamicTableModel;

/**
 *
 * @author mjcobo
 */
public class GenericDynamicItemsListPanel<E extends Comparable<E>> extends GenericItemsListPanel<E> {

	  /***************************************************************************/
	  /*                        Private attributes                               */
	  /***************************************************************************/
	  
	  /***************************************************************************/
	  /*                            Constructors                                 */
	  /***************************************************************************/
	  
	  public GenericDynamicItemsListPanel(GenericDynamicTableModel<E> tableModel) {
	    super(tableModel);
	  }
	  
	  /***************************************************************************/
	  /*                           Public Methods                                */
	  /***************************************************************************/
	  
	  /**
	   * 
	   * @param items 
	   */
	  public void addItems(List<E> items) {

	    ((GenericDynamicTableModel<E>)getTableModel()).addItems(items);
	  }

	  /**
	   * 
	   * @param items 
	   */
	  public void removeItems(List<E> items) {

	    ((GenericDynamicTableModel<E>)getTableModel()).removeItems(items);
	  }
	  
	  /**
	   * 
	   * @param items 
	   */
	  public void updateItems(List<E> items) {

	    ((GenericDynamicTableModel<E>)getTableModel()).updateItems(items);
	  }
	  
	  /***************************************************************************/
	  /*                           Private Methods                               */
	  /***************************************************************************/
	}
