#import "Utils.h"

// Thanks to this guard, we won't import this header when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNUtilsSpec.h"
#endif

@implementation Utils

RCT_EXPORT_MODULE()

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(getOrientation)
{
  auto orientation = [UIDevice currentDevice].orientation;
  switch (orientation) {
    case UIDeviceOrientationPortrait:
    case UIDeviceOrientationPortraitUpsideDown:
    case UIDeviceOrientationFaceUp:
    case UIDeviceOrientationFaceDown:
      return @"vertical";
    case UIDeviceOrientationLandscapeLeft:
    case UIDeviceOrientationLandscapeRight:
      return @"horizontal";
    default:
      return @"unknown";
  }
}

// Thanks to this guard, we won't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
  return std::make_shared<facebook::react::NativeUtilsSpecJSI>(params);
}
#endif

@end
