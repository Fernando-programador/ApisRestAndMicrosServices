package com.asl.SpringJava.MsgErro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceMsg extends RuntimeException{
  	
	public ResourceMsg(String msg) {
		super(msg);
	}  
}
