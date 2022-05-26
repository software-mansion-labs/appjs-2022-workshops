package com.reactnativeappjs;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.gms.maps.model.LatLng;

public class RNMapViewHelper {
  public static void emitMapEvent(@NonNull RNMapView rnMapView, RNMapViewEventType eventType, @NonNull LatLng latLng) {
    ReactContext reactContext = (ReactContext) rnMapView.getContext();
    EventDispatcher eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, rnMapView.getId());
    RNMapViewEvent event = RNMapViewEvent.obtain(rnMapView.getId(), eventType, latLng);
    eventDispatcher.dispatchEvent(event);
  }
}
