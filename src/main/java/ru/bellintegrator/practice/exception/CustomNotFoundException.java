package ru.bellintegrator.practice.exception;

public class CustomNotFoundException extends RuntimeException{

	public CustomNotFoundException(String msg) {
		super(msg);
	}
}