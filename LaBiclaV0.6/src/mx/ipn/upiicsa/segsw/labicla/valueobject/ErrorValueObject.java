package mx.ipn.upiicsa.segsw.labicla.valueobject;

import java.io.Serializable;

public class ErrorValueObject implements Serializable {
	
	private String message;
	private String description;
	private Exception exception;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
