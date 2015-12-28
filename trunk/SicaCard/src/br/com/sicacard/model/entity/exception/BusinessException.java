/**
 *
 */
package br.com.sicacard.model.entity.exception;

/**
 * BusinessException.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 18/04/2012
 * @version 1.0
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 59871701786107727L;

	/**
	 * Default constructor.
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Default constructor.
	 *
	 * @param msg String
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * Default constructor.
	 *
	 * @param throwable Throwable
	 */
	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Default constructor.
	 *
	 * @param msg String
	 * @param throwable Throwable
	 */
	public BusinessException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
