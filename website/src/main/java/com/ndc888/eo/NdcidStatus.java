package com.ndc888.eo;

public enum NdcidStatus {
	
	Avaiable(0), Used(1), Reserved(2), Discarded(3), Temp(9);
	
	
	Integer code;
	
	NdcidStatus(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	
	

}
