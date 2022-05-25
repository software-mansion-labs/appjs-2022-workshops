import { requireNativeComponent } from 'react-native';

// @ts-ignore
const isFabricEnabled = global.nativeFabricUIManager != null;

export const MapView = isFabricEnabled
  ? require('./MapViewNativeComponent').default
  : requireNativeComponent('RNMapView');

export type MapType = 'standard' | 'satellite' | 'hybrid';

export type MapViewEvent = {
  nativeEvent: {
    latitude: number;
    longitude: number;
    target: number;
  };
};
