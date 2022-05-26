#import <MapKit/MapKit.h>
#import <React/RCTComponent.h>

@interface AJSMapView : MKMapView

@property (nonatomic, copy) RCTBubblingEventBlock onPress;
@property (nonatomic, copy) RCTDirectEventBlock onRegionChange;

- (void)moveTo:(float)latitude longitude:(float)longitude animated:(BOOL)animated;

@end
