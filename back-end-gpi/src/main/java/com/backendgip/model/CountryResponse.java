package com.backendgip.model;

import java.util.List;

public class CountryResponse {
    private boolean error;
    private String msg;
    private List<CountryApi> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CountryApi> getData() {
        return data;
    }

    public void setData(List<CountryApi> data) {
        this.data = data;
    }
}
