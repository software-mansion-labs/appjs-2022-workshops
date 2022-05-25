package com.reactnativeappjs;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Promise;

class Utils extends NativeUtilsSpec {

  private final ReactApplicationContext context;

  public Utils(ReactApplicationContext context) {
    super(context);
    this.context = context;
  }

  @NonNull
  @Override
  public String getName() {
    return UtilsImpl.NAME;
  }

  @Override
  public WritableArray getLocation() {
    return UtilsImpl.getLocation();
  }

  @Override
  public String getOrientation() {
    return UtilsImpl.getOrientation(context);
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  @Override
  public WritableMap getDeviceInfo() {
    return UtilsImpl.getDeviceInfo();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public void encode(String data, Promise promise) {
    UtilsImpl.encode(data, promise);
  }

}
