package ru.bellintegrator.practice.advice;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.message.ResponseMsg;

@RestControllerAdvice
public class WebRestControllerAdvice {
	
	@ExceptionHandler(CustomNotFoundException.class)
	public Response handleNotFoundException(CustomNotFoundException ex) {
		Response status = new Response(ex.getMessage());
		return status;
	}
}