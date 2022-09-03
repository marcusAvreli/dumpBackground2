package dumpBackground2.gui.components;

import javax.swing.JMenuItem;

import dumpBackground2.model.base.entity.Token;
import dumpBackground2.project.Context;
import dumpBackground2.project.observer.StateObserver;

public class StateObserverMenuItem extends JMenuItem
implements StateObserver {

/***************************************************************************/
/*                        Private attributes                               */
/***************************************************************************/

/***************************************************************************/
/*                            Constructors                                 */
/***************************************************************************/

/**
* 
*/
public StateObserverMenuItem() {
super();

init();
}

/***************************************************************************/
/*                           Public Methods                                */
/***************************************************************************/

/**
* Cuando ocurra un cambio en el estado de la base de conocimiento, este
* objeto sera notificado a traves de este metodo.
*
* @param loaded nuevo estado de la base de conocimiento.
*/


/***************************************************************************/
/*                           Private Methods                               */
/***************************************************************************/

/**
* Initialize the object.
*/
private void init() {

// Insertamos al objeto como observador del estado de la base de conocimiento.
	Context.getInstance().addKnowledgeBaseStateObserver(this);
}

public void stateChanged(boolean loaded) {
	// TODO Auto-generated method stub
	setEnabled(loaded);
	
}

@Override
public void tokenRefreshed(Token token) {
	// TODO Auto-generated method stub
	
}
}
