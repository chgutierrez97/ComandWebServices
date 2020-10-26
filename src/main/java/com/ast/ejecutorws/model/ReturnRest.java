package com.ast.ejecutorws.model;

public class ReturnRest {
	private String errorMessage;
	private String status;
	private String id;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReturnRest [errorMessage=" + errorMessage + ", status=" + status + ", id=" + id + "]";
	}

	
}
