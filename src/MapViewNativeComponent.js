// @flow
import type {ViewProps} from 'react-native/Libraries/Components/View/ViewPropTypes';
import type {WithDefault} from 'react-native/Libraries/Types/CodegenTypes';
import type {HostComponent} from 'react-native';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

type MapType = 'standard' | 'satellite' | 'hybrid';

type NativeProps = $ReadOnly<{|
  ...ViewProps,
  mapType?: WithDefault<MapType, 'standard'>,
|}>;

export default (codegenNativeComponent<NativeProps>(
  'RNMapView',
): HostComponent<NativeProps>);
