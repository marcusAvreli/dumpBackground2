package dumpBackground2.model.base.exception;

public class BaseException extends Exception {

	  /**
	   * Creates a new instance of <code>KnowledgeBaseException</code> without
	   * detail message.
	   */
	  public BaseException() {

	  }

	  /**
	   * Constructs an instance of <code>KnowledgeBaseException</code> with the
	   * specified detail message.
	   * 
	   * @param message the detail message.
	   */
	  public BaseException(String message) {
	    super(message);
	  }

	  /**
	   * Constructs an instance of <code>KnowledgeBaseException</code> with the
	   * specified detail cause.
	   *
	   * @param cause the cause (which is saved for later retrieval by the
	   *              <code>KnowledgeBaseException.getCause()</code> method).
	   *              (A null value is permitted, and indicates that the cause is
	   *              nonexistent or unknown.)
	   */
	  public BaseException(Throwable cause) {
	    super(cause);
	  }

	  /**
	   * Constructs an instance of <code>KnowledgeBaseException</code> with the
	   * specified detail cause.
	   *
	   * @param message the detail message.
	   * @param cause the cause (which is saved for later retrieval by the
	   *              <code>KnowledgeBaseException.getCause()</code> method). (A
	   *              null value is permitted, and indicates that the cause is
	   *              nonexistent or unknown.)
	   */
	  public BaseException(String message, Throwable cause) {
	    super(message, cause);
	  }
	}