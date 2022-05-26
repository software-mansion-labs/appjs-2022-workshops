#import <MapKit/MapKit.h>

@interface AJSMapView : MKMapView

- (void)moveTo:(float)latitude longitude:(float)longitude animated:(BOOL)animated;

@end
