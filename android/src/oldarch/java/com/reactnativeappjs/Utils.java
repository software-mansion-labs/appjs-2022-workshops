package com.reactnativeappjs;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;

public class Utils extends ReactContextBaseJavaModule {

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

  @ReactMethod(isBlockingSynchronousMethod = true)
  public WritableArray getLocation() {
    return UtilsImpl.getLocation();
  }

  @ReactMethod(isBlockingSynchronousMethod = true)
  public String getOrientation() {
    return UtilsImpl.getOrientation(context);
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  @ReactMethod(isBlockingSynchronousMethod = true)
  public WritableMap getDeviceInfo() {
    return UtilsImpl.getDeviceInfo();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @ReactMethod()
  public void encode(String data, Promise promise) {
    UtilsImpl.encode(data, promise);
  }

}
