package com.cyxy.entity;

public class Hub {
    private Integer hubId;

    private Double hubLng;

    private Double hubLat;

    private String hubState;

    private Integer hubPort;

    private Integer partnerId;

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public Double getHubLng() {
        return hubLng;
    }

    public void setHubLng(Double hublLng) {
        this.hubLng = hublLng;
    }

    public Double getHubLat() {
        return hubLat;
    }

    public void setHubLat(Double hubLat) {
        this.hubLat = hubLat;
    }

    public String getHubState() {
        return hubState;
    }

    public void setHubState(String hubState) {
        this.hubState = hubState;
    }

    public Integer getHubPort() {
        return hubPort;
    }

    public void setHubPort(Integer hubPort) {
        this.hubPort = hubPort;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }
}