package com.reactnativeappjs;

public enum RNMapViewEventType {
  PRESS,
  REGION_CHANGE;

  public static String getJSEventName(RNMapViewEventType type) {
    switch (type) {
      case PRESS:
        return "topPress";
      case REGION_CHANGE:
        return "topRegionChange";
      default:
        throw new IllegalArgumentException("Unsupported RNMapViewEventType: " + type);
    }
  }
}
