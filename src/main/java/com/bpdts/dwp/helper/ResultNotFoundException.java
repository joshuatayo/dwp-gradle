package com.bpdts.dwp.helper;

@SuppressWarnings("serial")
public class ResultNotFoundException extends RuntimeException {
	
	private String msg;

    public ResultNotFoundException(String msg) {
        this.msg = msg;
    }
    
    public String getMsg()  {
        return msg;
    }    
}
