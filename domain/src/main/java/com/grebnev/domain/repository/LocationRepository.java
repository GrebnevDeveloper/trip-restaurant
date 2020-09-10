package com.grebnev.domain.repository;

import com.grebnev.domain.model.LocationDomainModel;

import io.reactivex.Flowable;

public interface LocationRepository {
    Flowable<LocationDomainModel> getLocation();
}
