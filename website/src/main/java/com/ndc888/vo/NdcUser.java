package com.ndc888.vo;

import java.io.Serializable;

public class NdcUser implements Serializable {
	private static final long serialVersionUID = -624623308705595806L;
	
	private int userId;
	
	private String mobile;

    private String nickname;
    
    private String ndcid;

    private String status;
    

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNdcid() {
		return ndcid;
	}

	public void setNdcid(String ndcid) {
		this.ndcid = ndcid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
