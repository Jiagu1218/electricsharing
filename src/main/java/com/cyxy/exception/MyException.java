package com.cyxy.exception;

import com.cyxy.enums.ResultEnum;
import org.springframework.stereotype.Component;

@Component
public class MyException extends RuntimeException {
	
	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public MyException(){}
	
	public MyException(Integer code,String message) {
		super(message);
		this.code = code;
	}
	
	public MyException(ResultEnum resultEnum){
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}
	
	public MyException(ResultEnum resultEnum,String message){
		super(message);
		this.code = resultEnum.getCode();
	}
	
}
