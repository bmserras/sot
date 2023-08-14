package org.bmserras.sot.zabbix.api;

public class Params {

    private int hostids;
    private String sortfield;
    private String sortorder;
    private int limit;

    public int getHostids() {
        return hostids;
    }

    public void setHostids(int hostids) {
        this.hostids = hostids;
    }

    public String getSortfield() {
        return sortfield;
    }

    public void setSortfield(String sortfield) {
        this.sortfield = sortfield;
    }

    public String getSortorder() {
        return sortorder;
    }

    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Params{" +
                "hostids=" + hostids +
                ", sortfield='" + sortfield + '\'' +
                ", sortorder='" + sortorder + '\'' +
                ", limit=" + limit +
                '}';
    }
}
