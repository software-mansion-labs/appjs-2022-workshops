#import "AJSMapView.h"

@implementation AJSMapView

- (void)moveTo:(float)latitude longitude:(float)longitude animated:(BOOL)animated
{
  CLLocationCoordinate2D center;
  center.latitude = latitude;
  center.longitude = longitude;
  [self setCenterCoordinate:center animated:animated];
}

@end
