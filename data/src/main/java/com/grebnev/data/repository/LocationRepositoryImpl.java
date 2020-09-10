package com.grebnev.data.repository;

import com.grebnev.data.entity.LocationEntity;
import com.grebnev.data.location.LocationDataSource;
import com.grebnev.domain.model.LocationDomainModel;
import com.grebnev.domain.repository.LocationRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class LocationRepositoryImpl implements LocationRepository {
    private LocationDataSource locationDataSource;

    @Inject
    public LocationRepositoryImpl(LocationDataSource locationDataSource) {
        this.locationDataSource = locationDataSource;
    }

    @Override
    public Flowable<LocationDomainModel> getLocation() {
        return locationDataSource.getLocationObservable()
                .map(LocationEntity::mapToDomain);
    }
}
