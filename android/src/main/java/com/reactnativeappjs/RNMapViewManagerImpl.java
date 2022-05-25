package com.reactnativeappjs;

import com.facebook.react.uimanager.ThemedReactContext;

public class RNMapViewManagerImpl {

    public static final String NAME = "RNMapView";

    public static RNMapView createViewInstance(ThemedReactContext context) {
      return new RNMapView(context);
    }

}
