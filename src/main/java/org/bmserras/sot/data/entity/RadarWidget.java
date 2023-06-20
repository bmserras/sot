package org.bmserras.sot.data.entity;

import jakarta.persistence.Entity;

@Entity
public class RadarWidget extends Widget {

    private String ipAddress;
    private String latitude;
    private String longitude;

    public RadarWidget() {
    }

    public RadarWidget(String name, String ipAddress, String latitude, String longitude) {
        super(name);
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RadarWidget(int identifier, String name, String ipAddress, String latitude, String longitude) {
        super(identifier, name);
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "RadarWidget{" +
                "ipAddress='" + ipAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                "} " + super.toString();
    }
}
