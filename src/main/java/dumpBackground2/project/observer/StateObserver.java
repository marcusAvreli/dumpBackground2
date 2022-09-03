package dumpBackground2.project.observer;

import dumpBackground2.model.base.entity.Token;

/**
	 * Representa a un observador que observa el estado de la base de conocimiento
	 * asociada al proyecto. El estado puede ser cargada o no cargada.
	 * 
	 * Los observadores que necesiten observar dicho estado, deberan implementar
	 * esta interfaz
	 * 
	 * @author Manuel Jesus Cobo Martin.
	 */
	public interface StateObserver {

	  /**
	   * Cuando ocurra un cambio en el estado de la base de conocimiento, se 
	   * notificara a las clases que implementen esta interfaz a traves de este
	   * metodo, por lo tanto bajo este metodo se tienen que realizar las acciones
	   * oportunas al cambio de estado.
	   * 
	   * @param loaded true en caso de que la base de conocimiento este cargada.
	   */
	  public void stateChanged(boolean loaded);
	  public void tokenRefreshed(Token token);
	}
