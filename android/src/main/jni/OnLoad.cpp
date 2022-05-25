#include <fbjni/fbjni.h>

#include "AppjsComponentsRegistry.h"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *) {
  return facebook::jni::initialize(vm, [] {
    facebook::react::AppjsComponentsRegistry::registerNatives();
  });
}
