package org.bmserras.sot.api;

import java.util.List;

public class Event {

    private String jsonrpc;

    private List<Result> result;

    private int id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", results=" + result +
                ", id=" + id +
                '}';
    }
}
