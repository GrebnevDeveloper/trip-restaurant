package com.grebnev.domain.interactor;

import com.grebnev.domain.model.LocationDomainModel;
import com.grebnev.domain.repository.LocationRepository;

import io.reactivex.Flowable;

public class LocationInteractor {
    private final LocationRepository locationRepository;

    public LocationInteractor(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Flowable<LocationDomainModel> getLocation() {
        return locationRepository.getLocation();
    }
}
