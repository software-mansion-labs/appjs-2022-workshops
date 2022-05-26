#import "RNMapViewManager.h"
#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>
#import "AJSMapView.h"

@implementation RNMapViewManager {
  AJSMapView *_view;
  NSString *_mapType;
}

RCT_EXPORT_MODULE(RNMapView)

- (instancetype)init
{
  if (self = [super init]) {
    _view = [[AJSMapView alloc] init];
    _mapType = @"standard";

    UITapGestureRecognizer *tapRecognizer = [
      [UITapGestureRecognizer alloc] initWithTarget:self action:@selector(handleTap:)
    ];
    tapRecognizer.numberOfTapsRequired = 1;
    tapRecognizer.numberOfTouchesRequired = 1;
    [_view addGestureRecognizer:tapRecognizer];

    UIPanGestureRecognizer *panRecognizer = [
      [UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(handlePan:)
    ];
    panRecognizer.delegate = self;
    [_view addGestureRecognizer:panRecognizer];
  }

  return self;
}

- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer {
  return YES;
}

- (void)handleTap:(UITapGestureRecognizer *)recognizer
{
  CLLocationCoordinate2D point = [self getCoordinates:recognizer];
  _view.onPress(@{@"latitude": @(point.latitude), @"longitude": @(point.longitude)});
}

- (void)handlePan:(UIPanGestureRecognizer *)recognizer
{
  CLLocationCoordinate2D point = [self getCoordinates:recognizer];
  _view.onRegionChange(@{@"latitude": @(point.latitude), @"longitude": @(point.longitude)});
}

- (CLLocationCoordinate2D)getCoordinates:(UIGestureRecognizer *)recognizer
{
  CGPoint point = [recognizer locationInView:_view];
  return [_view convertPoint:point toCoordinateFromView:_view];
}

- (UIView *)view
{
  return _view;
}

RCT_CUSTOM_VIEW_PROPERTY(mapType, NSString, UIView)
{
  if (![_mapType isEqual:json]) {
    _mapType = json;
    _view.mapType = parseMapType(_mapType);
  }
}

static inline MKMapType parseMapType(NSString *value) {
  if ([@"standard" isEqual:value]) {
    return MKMapTypeStandard;
  }
  else if ([@"satellite" isEqual:value]) {
    return MKMapTypeSatellite;
  }
  else if ([@"hybrid" isEqual:value]) {
    return MKMapTypeHybrid;
  }
  return MKMapTypeStandard;
}

RCT_EXPORT_VIEW_PROPERTY(onPress, RCTBubblingEventBlock)

RCT_EXPORT_VIEW_PROPERTY(onRegionChange, RCTDirectEventBlock)

RCT_EXPORT_METHOD(moveTo:(nonnull NSNumber *)reactTag
             coordinates:(NSDictionary *)coordinates
                animated:(BOOL)animated)
{
  float latitude = [(NSNumber *)coordinates[@"latitude"] floatValue];
  float longitude = [(NSNumber *)coordinates[@"longitude"] floatValue];
  [_view moveTo:latitude longitude:longitude animated:animated];
}

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

@end
