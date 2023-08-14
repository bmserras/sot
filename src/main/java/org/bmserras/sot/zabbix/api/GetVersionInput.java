package org.bmserras.sot.zabbix.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetVersionInput {

    private String jsonrpc;

    private String method;

    private List<String> params;

    private int id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "{\"jsonrpc\": \"" + jsonrpc + "\",\"method\": \"" + method + "\",\"params\": {},\"id\": " + id + "}";
    }


}
