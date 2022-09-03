package dumpBackground2.project.observer;

import java.util.List;

import dumpBackground2.model.base.exception.BaseException;



/**
 *
 * @author mjcobo
 */
public interface EntityObserver<T extends Comparable<T>> {

  public void entityUpdated(List<T> items) throws BaseException;
  public void entityAdded(List<T> items) throws BaseException;
  public void entityRemoved(List<T> items) throws BaseException;
  public abstract void entityRefresh() throws BaseException;
}