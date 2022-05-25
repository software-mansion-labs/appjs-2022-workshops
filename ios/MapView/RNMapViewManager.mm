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

+ (BOOL)requiresMainQueueSetup
{
    return NO;
}

@end
