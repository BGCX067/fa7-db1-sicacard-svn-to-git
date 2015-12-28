/**
 *
 */
package br.com.sicacard.model.entity.exception;

/**
 * PersistenceException.java
 *
 * @author Ebix
 *
 * 19/04/2012
 */
public class PersistenceException extends Exception {

	private static final long serialVersionUID = -8617726407873220368L;

	/**
	 * Default constructor.
	 */
	public PersistenceException() {
		super();
	}

	/**
	 * Default constructor.
	 *
	 * @param message String
	 */
	public PersistenceException(String message) {
		super(message);
	}

	/**
	 * Default constructor.
	 *
	 * @param cause Throwable
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Default constructor.
	 *
	 * @param message String
	 * @param cause Throwable
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
