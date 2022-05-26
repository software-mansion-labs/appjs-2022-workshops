import {
  UIManager,
  ViewProps,
  findNodeHandle,
  requireNativeComponent,
} from 'react-native';

import React from 'react';

// @ts-ignore
const isFabricEnabled = global.nativeFabricUIManager != null;

const MapViewNativeComponent = isFabricEnabled
  ? require('./MapViewNativeComponent').default
  : requireNativeComponent('RNMapView');

const MapViewCommands = isFabricEnabled
  ? require('./MapViewCommands').default
  : { moveTo: () => {} };

export type MapType = 'standard' | 'satellite' | 'hybrid';

export type MapViewEvent = {
  nativeEvent: {
    latitude: number;
    longitude: number;
    target: number;
  };
};

interface Coordinates {
  latitude: number;
  longitude: number;
}

interface MapViewProps extends ViewProps {
  mapType: MapType;
  onPress: (event: MapViewEvent) => void;
  onRegionChange: (event: MapViewEvent) => void;
}

export class MapView extends React.Component<MapViewProps> {
  _ref: React.ElementRef<typeof MapViewNativeComponent>;

  moveTo = (coordinates: Coordinates, animated: boolean = true) => {
    if (this._ref != null) {
      if (isFabricEnabled) {
        MapViewCommands.moveTo(this._ref, coordinates, animated);
      } else {
        UIManager.dispatchViewManagerCommand(
          // @ts-ignore
          findNodeHandle(this._ref),
          // @ts-ignore commands should be numbers
          'moveTo',
          [coordinates, animated]
        );
      }
    }
  };

  _captureRef = (ref: React.ElementRef<typeof MapViewNativeComponent>) => {
    this._ref = ref;
  };

  render() {
    return <MapViewNativeComponent {...this.props} ref={this._captureRef} />;
  }
}
