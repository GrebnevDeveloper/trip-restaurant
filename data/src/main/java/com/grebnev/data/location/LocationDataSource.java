package com.grebnev.data.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.grebnev.data.entity.LocationEntity;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

public class LocationDataSource {
    private static final int LOCATION_REQUEST_INTERVAL = 10000;
    private static final int LOCATION_REQUEST_FASTEST_INTERVAL = 5000;

    private Context context;

    @Inject
    public LocationDataSource(Context context) {
        this.context = context;
    }

    private PublishSubject<LocationEntity> locationSubject =
            PublishSubject.create();
    private FusedLocationProviderClient fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context);

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(LOCATION_REQUEST_INTERVAL);
        locationRequest.setFastestInterval(LOCATION_REQUEST_FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                setLocation(location);
            }
        }
    };

    public Flowable<LocationEntity> getLocationObservable() {
        return locationSubject.toFlowable(BackpressureStrategy.MISSING)
                .doOnSubscribe(disposable -> startLocationUpdates())
                .doOnCancel(this::stopLocationUpdates);
    }

    private void startLocationUpdates() {
        if (isPermissionCheck()) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this::setLocation);
            fusedLocationClient.requestLocationUpdates(createLocationRequest(),
                    locationCallback,
                    Looper.getMainLooper());
        }
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void setLocation(Location location) {
        locationSubject.onNext(
                new LocationEntity(
                        location.getLatitude(),
                        location.getLongitude()
                ));
    }

    private boolean isPermissionCheck() {
        boolean isFineLocationCheck =
                ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED;
        boolean isCoarseLocationCheck =
                ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED;
        return isFineLocationCheck && isCoarseLocationCheck;
    }
}
