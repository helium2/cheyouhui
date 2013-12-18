package com.ndc888.vo;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -218798771579517985L;

	private String mobile;

    private String nickname;

    private String realname;

    private String gender;

    private String job;

    private String qq;
    
    private String email;

    private String currentcartype;

    private String dreamcartype;
    
    private String licensePlate;

    private String hobby;

    private String referee;

    private String ndcid;

    private String status;
    
    private String passwd;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCurrentcartype() {
		return currentcartype;
	}

	public void setCurrentcartype(String currentcartype) {
		this.currentcartype = currentcartype;
	}

	public String getDreamcartype() {
		return dreamcartype;
	}

	public void setDreamcartype(String dreamcartype) {
		this.dreamcartype = dreamcartype;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public String getNdcid() {
		return ndcid;
	}

	public void setNdcid(String ndcid) {
		this.ndcid = ndcid;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    

}
