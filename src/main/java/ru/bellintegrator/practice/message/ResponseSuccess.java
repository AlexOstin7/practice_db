package ru.bellintegrator.practice.message;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Message {
	private String result;
	private Object data;

	public Response() {

	}

	public Response (String result) {
		this.result = result;
	}

	public Response(String result, Object data) {
		this.result = result;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
