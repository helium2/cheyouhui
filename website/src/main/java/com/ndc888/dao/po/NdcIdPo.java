package com.ndc888.dao.po;

public class NdcIdPo {
    private Integer id;

    private String ndcid;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNdcid() {
        return ndcid;
    }

    public void setNdcid(String ndcid) {
        this.ndcid = ndcid == null ? null : ndcid.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}