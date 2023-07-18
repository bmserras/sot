package org.bmserras.sot.data.entity.widget;

import jakarta.persistence.Entity;

@Entity
public class RadarWidget extends Widget {

    private String ipAddress;
    private double latitude;
    private double longitude;

    public RadarWidget() {
    }

    public RadarWidget(String name, String ipAddress, double latitude, double longitude) {
        super(name);
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RadarWidget(int identifier, String name, String ipAddress, double latitude, double longitude) {
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

    @Override
    public String toString() {
        return "RadarWidget{" +
                "ipAddress='" + ipAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                "} " + super.toString();
    }
}
