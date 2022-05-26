package com.reactnativeappjs;

import androidx.annotation.Nullable;
import android.content.Context;
import android.util.AttributeSet;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class RNMapView extends MapView {

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
    }

    public final void setMapType(int type) {
      this.getMapAsync((GoogleMap googleMap) -> googleMap.setMapType(type));
    }

}
