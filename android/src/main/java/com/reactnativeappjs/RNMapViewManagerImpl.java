package com.reactnativeappjs;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.gms.maps.GoogleMap;

import java.util.HashMap;
import java.util.Map;

public class RNMapViewManagerImpl {

    public static final String NAME = "RNMapView";

    public static RNMapView createViewInstance(ThemedReactContext context) {
      return new RNMapView(context);
    }

    public static void setMapType(RNMapView view, String mapType) {
      view.setMapType(parseMapType(mapType));
    }

    private static int parseMapType(String mapType) {
      switch (mapType) {
        case "standard":
          return GoogleMap.MAP_TYPE_NORMAL;
        case "satellite":
          return GoogleMap.MAP_TYPE_SATELLITE;
        case "hybrid":
          return GoogleMap.MAP_TYPE_HYBRID;
        default:
          return GoogleMap.MAP_TYPE_NORMAL;
      }
    }

    public static @Nullable Map<String, Object> getExportedCustomDirectEventTypeConstants() {
      Map<String, String> press = new HashMap<>();
      press.put("registrationName", "onPress");
      Map<String, String> regionChange = new HashMap<>();
      regionChange.put("registrationName", "onRegionChange");
      Map<String, Object> map = new HashMap<>();
      map.put("topPress", press);
      map.put("topRegionChange", regionChange);
      return map;
    }

}
