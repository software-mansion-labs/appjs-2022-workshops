package com.reactnativeappjs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;
import android.util.AttributeSet;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

public class RNMapView extends MapView implements GoogleMap.OnMapClickListener, GoogleMap.OnCameraMoveListener {

    private GoogleMap googleMap;

    public RNMapView(Context context) {
        super(context);
        this.init();
    }

    public RNMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public RNMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        this.onCreate(null); // initializes map controls
        this.onResume(); // initializes map tiles
        this.getMapAsync((GoogleMap googleMap) -> {
            this.googleMap = googleMap;
            googleMap.setOnMapClickListener(this);
            googleMap.setOnCameraMoveListener(this);
        }); // initializes event listeners
    }

    public final void setMapType(int type) {
      this.getMapAsync((GoogleMap googleMap) -> googleMap.setMapType(type));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        RNMapViewHelper.emitMapEvent(this, RNMapViewEventType.PRESS, latLng);
    }

    @Override
    public void onCameraMove() {
      LatLng latLng = this.googleMap.getCameraPosition().target;
      RNMapViewHelper.emitMapEvent(this, RNMapViewEventType.REGION_CHANGE, latLng);
    }

}
