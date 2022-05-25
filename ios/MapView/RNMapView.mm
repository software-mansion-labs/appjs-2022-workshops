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

@interface RNMapView () <RCTRNMapViewViewProtocol>

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

Class<RCTComponentViewProtocol> RNMapViewCls(void)
{
  return RNMapView.class;
}

@end
#endif
