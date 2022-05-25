package com.reactnativeappjs;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class AppjsComponentsRegistry {
  static {
    SoLoader.loadLibrary("fabricjni");
    SoLoader.loadLibrary("reactnativeappjs_modules");
  }

  @DoNotStrip private final HybridData mHybridData;

  @DoNotStrip
  private native HybridData initHybrid(ComponentFactory componentFactory);

  @DoNotStrip
  private AppjsComponentsRegistry(ComponentFactory componentFactory) {
    mHybridData = initHybrid(componentFactory);
  }

  @DoNotStrip
  public static AppjsComponentsRegistry register(ComponentFactory componentFactory) {
    return new AppjsComponentsRegistry(componentFactory);
  }
}
