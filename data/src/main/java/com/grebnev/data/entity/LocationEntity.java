package com.grebnev.data.entity;

import com.grebnev.domain.model.LocationDomainModel;

public class LocationEntity {
    private final Double latitude;
    private final Double longitude;

    public LocationEntity(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationDomainModel mapToDomain() {
        return new LocationDomainModel(latitude, longitude);
    }
}
