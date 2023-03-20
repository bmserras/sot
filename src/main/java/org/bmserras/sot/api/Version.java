package org.bmserras.sot.api;

public class Version {

    private String jsonrpc;

    private String result;

    private String id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Version{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", result='" + result + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
