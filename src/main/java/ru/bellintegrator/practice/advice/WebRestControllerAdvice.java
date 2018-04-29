package com.javasampleapproach.restcontrolleradvice.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javasampleapproach.restcontrolleradvice.exception.CustomNotFoundException;
import com.javasampleapproach.restcontrolleradvice.model.ResponseMsg;

@RestControllerAdvice
public class WebRestControllerAdvice {
	
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseMsg handleNotFoundException(CustomNotFoundException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
		return responseMsg;
	}
}