// @flow
import type {ViewProps} from 'react-native/Libraries/Components/View/ViewPropTypes';
import type {
  BubblingEventHandler,
  DirectEventHandler,
  Float,
  WithDefault,
} from 'react-native/Libraries/Types/CodegenTypes';
import type {HostComponent} from 'react-native';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

type MapType = 'standard' | 'satellite' | 'hybrid';

type Coordinates = $ReadOnly<{|
  latitude: Float,
  longitude: Float,
|}>;

type NativeProps = $ReadOnly<{|
  ...ViewProps,
  mapType?: WithDefault<MapType, 'standard'>,
  onPress?: BubblingEventHandler<Coordinates>,
  onRegionChange?: DirectEventHandler<Coordinates>,
|}>;

export default (codegenNativeComponent<NativeProps>(
  'RNMapView',
): HostComponent<NativeProps>);
