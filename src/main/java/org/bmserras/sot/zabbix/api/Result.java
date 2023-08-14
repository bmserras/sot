package org.bmserras.sot.zabbix.api;

public class Result {

    private String itemid;
    private String clock;
    private String value;
    private String ns;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    @Override
    public String toString() {
        return "Result{" +
                "itemid='" + itemid + '\'' +
                ", clock='" + clock + '\'' +
                ", value='" + value + '\'' +
                ", ns='" + ns + '\'' +
                '}';
    }
}
