package ru.bellintegrator.practice.advice;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.message.ResponseError;
import ru.bellintegrator.practice.message.ResponseSuccess;

import java.security.NoSuchAlgorithmException;

@RestControllerAdvice
public class WebRestControllerAdvice {
	
	@ExceptionHandler(CustomNotFoundException.class)
	public Response handleNotFoundException(CustomNotFoundException ex) {
		Response result = new ResponseSuccess(ex.getMessage());
		return result;
	}
	@ExceptionHandler(CustomErrorException.class)
	public Response handleResponseException(CustomErrorException ex) {
		Response error = new ResponseError(ex.getMessage());
		return error;
	}

}