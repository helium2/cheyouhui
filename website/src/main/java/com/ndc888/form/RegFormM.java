package com.ndc888.form;

import java.io.Serializable;

public class RegFormM implements Serializable {

	private static final long serialVersionUID = 3234539749254616637L;

	private String mobile;

	private String passwd;
	
	private String nickname;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	



}
