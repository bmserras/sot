package org.bmserras.sot.zabbix.api;

import java.util.List;

public class Event {

    // This has to be removed...
    private int hostId;

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

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
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
