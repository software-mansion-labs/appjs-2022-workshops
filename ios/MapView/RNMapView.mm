// This guard prevent the code from being compiled in the old architecture
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNMapView.h"

#import <react/renderer/components/RNMapViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNMapViewSpec/EventEmitters.h>
#import <react/renderer/components/RNMapViewSpec/Props.h>
#import <react/renderer/components/RNMapViewSpec/RCTComponentViewHelpers.h>

#import <MapKit/MapKit.h>

#import "RCTFabricComponentsPlugins.h"

using namespace facebook::react;

@interface RNMapView () <RCTRNMapViewViewProtocol, UIGestureRecognizerDelegate>

@end

@implementation RNMapView {
  MKMapView * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
  return concreteComponentDescriptorProvider<RNMapViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const RNMapViewProps>();
    _props = defaultProps;

    _view = [[MKMapView alloc] init];

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

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
  const auto &oldViewProps = *std::static_pointer_cast<RNMapViewProps const>(_props);
  const auto &newViewProps = *std::static_pointer_cast<RNMapViewProps const>(props);

  if (oldViewProps.mapType != newViewProps.mapType) {
    _view.mapType = parseMapType(newViewProps.mapType);
  }

  [super updateProps:props oldProps:oldProps];
}

static inline MKMapType parseMapType(const RNMapViewMapType &value) {
  switch (value) {
    case RNMapViewMapType::Standard: return MKMapTypeStandard;
    case RNMapViewMapType::Satellite: return MKMapTypeSatellite;
    case RNMapViewMapType::Hybrid: return MKMapTypeHybrid;
  }
}

- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer {
  return YES;
}

- (void)handleTap:(UITapGestureRecognizer *)recognizer
{
  CLLocationCoordinate2D point = [self getCoordinates: recognizer];
  if (_eventEmitter) {
    std::dynamic_pointer_cast<const facebook::react::RNMapViewEventEmitter>(_eventEmitter)
      ->onPress(facebook::react::RNMapViewEventEmitter::OnPress{
        .latitude = point.latitude,
        .longitude = point.longitude
      });
  }
}

- (void)handlePan:(UIPanGestureRecognizer *)recognizer
{
  CLLocationCoordinate2D point = [self getCoordinates: recognizer];
  if (_eventEmitter) {
    std::dynamic_pointer_cast<const facebook::react::RNMapViewEventEmitter>(_eventEmitter)
      ->onRegionChange(facebook::react::RNMapViewEventEmitter::OnRegionChange{
        .latitude = point.latitude,
        .longitude = point.longitude
      });
  }
}

- (CLLocationCoordinate2D)getCoordinates:(UIGestureRecognizer *)recognizer
{
  CGPoint point = [recognizer locationInView:_view];
  return [_view convertPoint:point toCoordinateFromView:_view];
}

#pragma mark - Native commands

- (void)handleCommand:(const NSString *)commandName args:(const NSArray *)args
{
  RCTMapViewHandleCommand(self, commandName, args);
}

inline void RCTMapViewHandleCommand(RNMapView *componentView, NSString const *commandName, NSArray const *args)
{
  if ([commandName isEqualToString:@"moveTo"]) {
#if RCT_DEBUG
    if ([args count] != 2) {
      RCTLogError(
        @"%@ command %@ received %d arguments, expected %d.", @"MapView", commandName, (int)[args count], 2);
      return;
    }
#endif
    NSObject *arg0 = args[0];
#if RCT_DEBUG
    if (!([[arg0 class] isSubclassOfClass:[NSDictionary class]])) {
      RCTLogError(@"error");
      return;
    }
#endif
    NSDictionary *coordinates = (NSDictionary *)arg0;
    NSObject *arg0latitude = coordinates[@"latitude"];
#if RCT_DEBUG
    if (!RCTValidateTypeOfViewCommandArgument(arg0latitude, [NSNumber class], @"float", @"MapView", commandName, @"1st")) {
      return;
    }
#endif
    NSObject *arg0longitude = coordinates[@"longitude"];
#if RCT_DEBUG
    if (!RCTValidateTypeOfViewCommandArgument(arg0longitude, [NSNumber class], @"float", @"MapView", commandName, @"1st")) {
      return;
    }
#endif

    NSObject *arg1 = args[1];
#if RCT_DEBUG
    if (!RCTValidateTypeOfViewCommandArgument(arg1, [NSNumber class], @"boolean", @"MapView", commandName, @"2nd")) {
      return;
    }
#endif

    float latitude = [(NSNumber *)arg0latitude floatValue];
    float longitude = [(NSNumber *)arg0longitude floatValue];
    BOOL animated = [(NSNumber *)arg1 boolValue];

    [componentView moveTo:latitude longitude:longitude animated:animated];
    return;
  }
}

- (void)moveTo:(float)latitude longitude:(float)longitude animated:(BOOL)animated
{
  CLLocationCoordinate2D center;
  center.latitude = latitude;
  center.longitude = longitude;
  [_view setCenterCoordinate:center animated:animated];
}

Class<RCTComponentViewProtocol> RNMapViewCls(void)
{
  return RNMapView.class;
}

@end
#endif
