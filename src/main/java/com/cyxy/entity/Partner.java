package com.cyxy.entity;

import java.io.Serializable;

public class Partner implements Serializable {
    private Integer partnerId;

    private String partnerName;

    private String partnerPassword;

    private Double partnerLng;

    private Double partnerLat;

    private String partnerPhone;

    private String partnerCity;

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerPassword() {
        return partnerPassword;
    }

    public void setPartnerPassword(String partnerPassword) {
        this.partnerPassword = partnerPassword;
    }

    public Double getPartnerLng() {
        return partnerLng;
    }

    public void setPartnerLng(Double partnerLng) {
        this.partnerLng = partnerLng;
    }

    public Double getPartnerLat() {
        return partnerLat;
    }

    public void setPartnerLat(Double partnerLat) {
        this.partnerLat = partnerLat;
    }

    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone;
    }

    public String getPartnerCity() {
        return partnerCity;
    }

    public void setPartnerCity(String partnerCity) {
        this.partnerCity = partnerCity;
    }
}