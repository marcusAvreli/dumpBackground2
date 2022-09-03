package dumpBackground2.gui.components.manager;

import java.util.ArrayList;

import dumpBackground2.gui.components.globalpanel.AffiliationGlobalPanel;
import dumpBackground2.gui.components.itemslist.AffiliationsListPanel;
import dumpBackground2.model.base.entity.Affiliation;

/**
*
* @author mjcobo
*/
public class AffiliationManager extends GenericItemManagerPanel<Affiliation> {

 /***************************************************************************/
 /*                        Private attributes                               */
 /***************************************************************************/

 /***************************************************************************/
 /*                            Constructors                                 */
 /***************************************************************************/

 /**
  * 
  */
 public AffiliationManager() {
   super(new AffiliationsListPanel(),
         new AffiliationGlobalPanel());

   setMasterPanelTitle("Affiliations list");
   setSlavePanelTitle("Affiliation detail");
 }

 /***************************************************************************/
 /*                           Public Methods                                */
 /***************************************************************************/

 /**
  *
  */
 @Override
 public void addAction() {
  // AddDialogManager.getInstance().showAddAffiliationDialog();
 }

 /**
  *
  * @param items
  */
/* @Override
 public void moveToAction(ArrayList<Affiliation> items) {
  JoinEntitiesDialogManager.getInstance().showAffiliationsJoinDialog(items);
 }
*/
 /**
  * 
  * @param items
  */
/* @Override
 public void removeAction(ArrayList<Affiliation> items) {

   (new PerformKnowledgeBaseEditTask(new DeleteAffiliationEdit(items), this)).execute();
 }*/

 /***************************************************************************/
 /*                           Private Methods                               */
 /***************************************************************************/
}