package ru.bellintegrator.practice.message;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseErrMsg implements Response{
	private String error;

	public ResponseErrMsg(String msg){
		this.error = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
