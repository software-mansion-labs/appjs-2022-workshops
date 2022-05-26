package com.reactnativeappjs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNMapViewManagerInterface;
import com.facebook.react.viewmanagers.RNMapViewManagerDelegate;

import java.util.Map;

@ReactModule(name = RNMapViewManagerImpl.NAME)
public class RNMapViewManager extends SimpleViewManager<RNMapView>
        implements RNMapViewManagerInterface<RNMapView> {

    private final ViewManagerDelegate<RNMapView> mDelegate;

    public RNMapViewManager(ReactApplicationContext context) {
        mDelegate = new RNMapViewManagerDelegate<>(this);
    }

    @Nullable
    @Override
    protected ViewManagerDelegate<RNMapView> getDelegate() {
        return mDelegate;
    }

    @NonNull
    @Override
    public String getName() {
        return RNMapViewManagerImpl.NAME;
    }

    @NonNull
    @Override
    protected RNMapView createViewInstance(@NonNull ThemedReactContext context) {
        return RNMapViewManagerImpl.createViewInstance(context);
    }

    @Override
    @ReactProp(name = "mapType")
    public void setMapType(RNMapView view, @Nullable String mapType) {
        RNMapViewManagerImpl.setMapType(view, mapType);
    }

    @Override
    public @Nullable Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return RNMapViewManagerImpl.getExportedCustomDirectEventTypeConstants();
    }

    @Override
    public void receiveCommand(@NonNull RNMapView view, String commandId, @Nullable ReadableArray args) {
      RNMapViewManagerImpl.receiveCommand(view, commandId, args);
    }

}
