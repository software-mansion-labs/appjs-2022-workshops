#import "RNMapViewManager.h"
#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>

@implementation RNMapViewManager {
  MKMapView *_view;
}

RCT_EXPORT_MODULE(RNMapView)

- (instancetype)init
{
  if (self = [super init]) {
    _view = [[MKMapView alloc] init];
  }

  return self;
}

- (UIView *)view
{
  return _view;
}

RCT_CUSTOM_VIEW_PROPERTY(mapType, NSString, UIView)
{
  // TODO: Paper implementation
}

RCT_EXPORT_VIEW_PROPERTY(onPress, RCTBubblingEventBlock)

RCT_EXPORT_VIEW_PROPERTY(onRegionChange, RCTDirectEventBlock)

RCT_EXPORT_METHOD(moveTo:(nonnull NSNumber *)reactTag
             coordinates:(NSDictionary *)coordinates
                animated:(BOOL)animated)
{
  // TODO: Paper implementation
}

+ (BOOL)requiresMainQueueSetup
{
    return NO;
}

@end
