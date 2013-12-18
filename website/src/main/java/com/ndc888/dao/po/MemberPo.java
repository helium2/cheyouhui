package com.ndc888.dao.po;

import java.util.Date;

public class MemberPo {
    private Integer id;

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

    private Date createdTime;

    private Date updatedTime;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCurrentcartype() {
        return currentcartype;
    }

    public void setCurrentcartype(String currentcartype) {
        this.currentcartype = currentcartype == null ? null : currentcartype.trim();
    }

    public String getDreamcartype() {
        return dreamcartype;
    }

    public void setDreamcartype(String dreamcartype) {
        this.dreamcartype = dreamcartype == null ? null : dreamcartype.trim();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate == null ? null : licensePlate.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee == null ? null : referee.trim();
    }

    public String getNdcid() {
        return ndcid;
    }

    public void setNdcid(String ndcid) {
        this.ndcid = ndcid == null ? null : ndcid.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}