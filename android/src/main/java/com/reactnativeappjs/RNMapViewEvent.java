package com.reactnativeappjs;

import androidx.annotation.Nullable;
import androidx.core.util.Pools;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.gms.maps.model.LatLng;

public class RNMapViewEvent extends Event<RNMapViewEvent> {

  private static final Pools.SynchronizedPool<RNMapViewEvent> EVENTS_POOL =
    new Pools.SynchronizedPool<>(3);

  private LatLng mLatLng;
  private @Nullable RNMapViewEventType mEventType;

  public static RNMapViewEvent obtain(
    int viewTag,
    RNMapViewEventType eventType,
    LatLng latLng) {
    RNMapViewEvent event = EVENTS_POOL.acquire();
    if (event == null) {
      event = new RNMapViewEvent();
    }
    event.init(viewTag, eventType, latLng);
    return event;
  }

  @Override
  public void onDispose() {
    EVENTS_POOL.release(this);
  }

  private RNMapViewEvent() {}

  private void init(int viewTag, RNMapViewEventType eventType, LatLng latLng) {
    super.init(viewTag);
    mEventType = eventType;
    mLatLng = latLng;
  }

  @Override
  public String getEventName() {
    return RNMapViewEventType.getJSEventName(Assertions.assertNotNull(mEventType));
  }

  @Override
  public short getCoalescingKey() {
    // All map events for a given view can be coalesced
    return 0;
  }

  @Override
  public boolean canCoalesce() {
    // Only REGION_CHANGE events can be coalesced, all others can not be
    if (mEventType == RNMapViewEventType.REGION_CHANGE) {
      return true;
    }
    return false;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap eventData = Arguments.createMap();
    eventData.putInt("target", getViewTag());
    eventData.putDouble("latitude", mLatLng.latitude);
    eventData.putDouble("longitude", mLatLng.longitude);
    return eventData;
  }

}
