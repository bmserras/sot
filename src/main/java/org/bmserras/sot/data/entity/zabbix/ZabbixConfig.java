package org.bmserras.sot.data.entity.zabbix;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "zabbix_config")
public class ZabbixConfig {

    @Id
    private long identifier;

    private int hostId;

    private int temperatureItemId;

    private int voltageItemId;

    private int batteryItemId;

    public ZabbixConfig() {
        this.identifier = new Date().getTime();
    }

    public ZabbixConfig(int hostId, int temperatureItemId, int voltageItemId, int batteryItemId) {
        this();
        this.hostId = hostId;
        this.temperatureItemId = temperatureItemId;
        this.voltageItemId = voltageItemId;
        this.batteryItemId = batteryItemId;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getTemperatureItemId() {
        return temperatureItemId;
    }

    public void setTemperatureItemId(int temperatureItemId) {
        this.temperatureItemId = temperatureItemId;
    }

    public int getVoltageItemId() {
        return voltageItemId;
    }

    public void setVoltageItemId(int voltageItemId) {
        this.voltageItemId = voltageItemId;
    }

    public int getBatteryItemId() {
        return batteryItemId;
    }

    public void setBatteryItemId(int batteryItemId) {
        this.batteryItemId = batteryItemId;
    }

    @Override
    public String toString() {
        return "ZabbixConfig{" +
                "identifier=" + identifier +
                ", hostId=" + hostId +
                ", temperatureItemId=" + temperatureItemId +
                ", voltageItemId=" + voltageItemId +
                ", batteryItemId=" + batteryItemId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZabbixConfig that = (ZabbixConfig) o;
        return identifier == that.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
