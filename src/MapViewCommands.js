// @flow
import type { Float } from 'react-native/Libraries/Types/CodegenTypes';
import type { HostComponent } from 'react-native/Libraries/Renderer/shims/ReactNativeTypes';
import codegenNativeCommands from 'react-native/Libraries/Utilities/codegenNativeCommands';

type Coordinates = $ReadOnly<{|
  latitude: Float,
  longitude: Float,
|}>;

type MapViewNativeComponentType = HostComponent<mixed>;
interface NativeCommands {
  +moveTo: (
    viewRef: React.ElementRef<MapViewNativeComponentType>,
    coordinates: Coordinates,
    animated?: boolean
  ) => void;
}

export default (codegenNativeCommands<NativeCommands>({
  supportedCommands: ['moveTo'],
}): NativeCommands);
