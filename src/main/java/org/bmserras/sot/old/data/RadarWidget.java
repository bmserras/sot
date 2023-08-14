package org.bmserras.sot.old.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.bmserras.sot.zabbix.ZabbixConfig;

@Entity
public class RadarWidget extends Widget {

    private String ipAddress;

    private int port;

    private double latitude;

    private double longitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zabbix_config_identifier", referencedColumnName = "identifier")
    private ZabbixConfig zabbixConfig;

    public RadarWidget() {
    }

    @Override
    public String getPath() {
        return "widget/";
    }

    public RadarWidget(String name, String ipAddress, int port, double latitude, double longitude) {
        super(name);
        this.ipAddress = ipAddress;
        this.port = port;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RadarWidget(int identifier, String name, String ipAddress, int port, double latitude, double longitude) {
        super(identifier, name);
        this.ipAddress = ipAddress;
        this.port = port;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ZabbixConfig getZabbixConfig() {
        return zabbixConfig;
    }

    public void setZabbixConfig(ZabbixConfig zabbixConfig) {
        this.zabbixConfig = zabbixConfig;
    }

    @Override
    public String toString() {
        return "RadarWidget{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zabbixConfig=" + zabbixConfig +
                "} " + super.toString();
    }
}