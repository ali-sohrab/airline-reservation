/**
 * 
 */
package edu.sjsu.cmpe275.lab2.error;

/**
 * @author Sohrab-PC
 *
 */
public class TransactionException extends RuntimeException {

	private String code;

	public TransactionException() {
	}

	public TransactionException(String msg) {
		super(msg);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
