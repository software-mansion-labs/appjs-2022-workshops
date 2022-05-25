#pragma once

#include <ComponentFactory.h>
#include <fbjni/fbjni.h>
#include <react/renderer/componentregistry/ComponentDescriptorProviderRegistry.h>
#include <react/renderer/componentregistry/ComponentDescriptorRegistry.h>

namespace facebook {
namespace react {

class AppjsComponentsRegistry
   : public facebook::jni::HybridClass<AppjsComponentsRegistry> {
 public:
  constexpr static auto kJavaDescriptor =
      "Lcom/reactnativeappjs/AppjsComponentsRegistry;";

  static void registerNatives();

  AppjsComponentsRegistry(ComponentFactory *delegate);

 private:
  friend HybridBase;

  static std::shared_ptr<ComponentDescriptorProviderRegistry const> sharedProviderRegistry();

  const ComponentFactory *delegate_;

  static jni::local_ref<jhybriddata> initHybrid(
      jni::alias_ref<jclass>,
      ComponentFactory *delegate);
};

} // namespace react
} // namespace facebook
