package com.grebnev.domain.model;

public class LocationDomainModel {
    private final Double latitude;
    private final Double longitude;

    public LocationDomainModel(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
