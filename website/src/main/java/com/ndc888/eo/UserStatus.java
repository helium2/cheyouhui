package com.ndc888.eo;

public enum UserStatus {
	
	New(0), Verified(1)
	;
	
	Integer code;
	
	UserStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
	

}
