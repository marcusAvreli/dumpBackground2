package dumpBackground2.gui.components.tablemodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dumpBackground2.gui.components.itemslist.AffiliationsListPanel;
import dumpBackground2.model.base.entity.Affiliation;

/**
 *
 * @author mjcobo
 */
public class AffiliationsTableModel extends GenericDynamicTableModel<Affiliation> {
	private static final Logger logger = LoggerFactory.getLogger(AffiliationsTableModel.class);

  /***************************************************************************/
  /*                        Private attributes                               */
  /***************************************************************************/

  /***************************************************************************/
  /*                            Constructors                                 */
  /***************************************************************************/

  /**
   *
   */
  public AffiliationsTableModel() {
    super(new String[] {"ID", "Full affiliation", "Documents", "Authors"});
   
  }

  /***************************************************************************/
  /*                           Public Methods                                */
  /***************************************************************************/

  /**
   *
   * @param rowIndex
   * @param columnIndex
   * @return
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {

    if ((rowIndex >= 0) && (rowIndex < getRowCount())) {

      Affiliation affiliation = getItem(rowIndex);

      switch (columnIndex) {

        case 0:
          return affiliation.getAffiliationID();

        case 1:
          return affiliation.getFullAffiliation();

        case 2:
          return affiliation.getDocumentsCount();

        case 3:
          return affiliation.getAuthorsCount();

        default:
          return "";
      }

    } else {

      return "";

    }
  }

  /***************************************************************************/
  /*                           Private Methods                               */
  /***************************************************************************/
}