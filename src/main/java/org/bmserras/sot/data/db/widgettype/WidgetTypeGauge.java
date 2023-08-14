package org.bmserras.sot.data.db.widgettype;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "widget_type_gauge")
@IdClass(WidgetTypeGauge.WidgetTypeGaugeId.class)
public class WidgetTypeGauge implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "widget_type_identifier", referencedColumnName = "identifier")
    private WidgetType widgetType;

    @Id
    @ManyToOne
    @JoinColumn(name = "gauge_identifier", referencedColumnName = "identifier")
    private Gauge gauge;

    public WidgetTypeGauge() {
    }

    public WidgetTypeGauge(WidgetType widgetType, Gauge gauge) {
        this.widgetType = widgetType;
        this.gauge = gauge;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

    public Gauge getGauge() {
        return gauge;
    }

    public void setGauge(Gauge gauge) {
        this.gauge = gauge;
    }

    @Override
    public String toString() {
        return "WidgetTypeGauge{" +
                ", gauge=" + gauge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetTypeGauge that = (WidgetTypeGauge) o;
        return widgetType.equals(that.widgetType) && gauge.equals(that.gauge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(widgetType, gauge);
    }

    public static class WidgetTypeGaugeId implements Serializable {

        private long widgetType;
        private long gauge;

        public long getWidgetType() {
            return widgetType;
        }

        public void setWidgetType(long widgetType) {
            this.widgetType = widgetType;
        }

        public long getGauge() {
            return gauge;
        }

        public void setGauge(long gauge) {
            this.gauge = gauge;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WidgetTypeGaugeId that = (WidgetTypeGaugeId) o;
            return widgetType == that.widgetType && gauge == that.gauge;
        }

        @Override
        public int hashCode() {
            return Objects.hash(widgetType, gauge);
        }
    }
}
