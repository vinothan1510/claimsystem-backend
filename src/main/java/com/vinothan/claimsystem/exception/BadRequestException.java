package com.vinothan.claimsystem.exception;


public class BadRequestException  extends RuntimeException{
		public BadRequestException(String message) {
			super(message);
		}
}