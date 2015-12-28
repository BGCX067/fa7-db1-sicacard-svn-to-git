/**
 *
 */
package br.com.sicacard.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * GenericController.java
 * 
 * @author Nayalison <br />
 *         nayalison@gmail.com
 * 
 * @since 06/05/2012
 * @version 1.0
 */
public class GenericController implements Serializable{
	

	private static final long serialVersionUID = -2305677116385989209L;

	protected void addErrorMessage(String componentId, String errorMessage) {
		addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
	}

	protected void addErrorMessage(String errorMessage) {
		addErrorMessage(null, errorMessage);
	}

	protected void addInfoMessage(String componentId, String infoMessage) {
		addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
	}

	protected void addInfoMessage(String infoMessage) {
		addInfoMessage(null, infoMessage);
	}

	private void addMessage(String componentId, String errorMessage,
			Severity severity) {
		FacesMessage message = new FacesMessage(errorMessage);
		message.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(componentId, message);
	}

}
