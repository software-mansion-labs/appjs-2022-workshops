import { requireNativeComponent } from 'react-native';

// @ts-ignore
const isFabricEnabled = global.nativeFabricUIManager != null;

export const MapView = isFabricEnabled
  ? require('./MapViewNativeComponent').default
  : requireNativeComponent('RNMapView');
