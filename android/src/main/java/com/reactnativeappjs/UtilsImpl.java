package com.reactnativeappjs;

import android.content.res.Configuration;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.Base64;

public class UtilsImpl {

  public static final String NAME = "Utils";

  public static WritableArray getLocation() {
    WritableArray coords = new WritableNativeArray();
    coords.pushDouble(50.04);
    coords.pushDouble(19.96);
    return coords;
  }

  public static String getOrientation(ReactApplicationContext context) {
    int orientation = context.getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      return "horizontal";
    } else {
      return "vertical";
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  public static WritableMap getDeviceInfo() {
    WritableMap deviceInfo = new WritableNativeMap();
    deviceInfo.putString("name", Build.DEVICE);
    deviceInfo.putString("model", Build.MODEL);
    deviceInfo.putString("systemName", "android");
    deviceInfo.putString("systemVersion", String.valueOf(Build.VERSION.SDK_INT));
    return deviceInfo;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static void encode(String data, Promise promise) {
    String encodedString = Base64.getEncoder().encodeToString(data.getBytes());
    promise.resolve(encodedString);
  }

}
