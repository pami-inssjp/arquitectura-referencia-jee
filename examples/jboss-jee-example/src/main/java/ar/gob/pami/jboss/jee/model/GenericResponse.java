package ar.gob.pami.jboss.jee.model;

import java.util.Collection;

public class GenericResponse<T> {

	private String statusCode;
	private String description;
	private T data;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getSize() {
		if (data instanceof Collection) {
			return ((Collection) data).size();
		} else if (data != null) {
			return 1;
		} else {
			return 0;
		}
	}

}
