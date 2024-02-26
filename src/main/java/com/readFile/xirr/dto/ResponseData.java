package com.readFile.xirr.dto;

public class ResponseData {
    private Integer responseCode;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private String responseMessage;

    @Override
    public String toString() {
        return "ResponseData{" +
                "responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
