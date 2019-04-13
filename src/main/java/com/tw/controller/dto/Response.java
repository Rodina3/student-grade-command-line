package com.tw.controller.dto;

public class Response {

    private String status;
    private String page;
    private boolean inputRequired;

    public Response(String status, String page, boolean inputRequired) {
        this.status = status;
        this.page = page;
        this.inputRequired = inputRequired;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isInputRequired() {
        return inputRequired;
    }

    public void setInputRequired(boolean inputRequired) {
        this.inputRequired = inputRequired;
    }
}
