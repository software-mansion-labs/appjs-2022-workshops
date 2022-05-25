import { Button, StyleSheet, Text, View } from 'react-native';
import { MapType, MapView, MapViewEvent, Utils } from 'react-native-appjs';
import React, { useState } from 'react';

const MAP_TYPES: MapType[] = ['standard', 'satellite', 'hybrid'];

export default function App() {
  const [mapType, setMapType] = useState<MapType>('standard');

  const handleGetOrientation = () => {
    console.log('getOrientation', Utils.getOrientation());
  };

  const handleGetLocation = () => {
    console.log('getLocation', Utils.getLocation());
  };

  const handleGetDeviceInfo = () => {
    console.log('getDeviceInfo', Utils.getDeviceInfo());
  };

  const handleEncodeData = async () => {
    const result = await Utils.encode('App.js');
    console.log('encode', result);
  };

  const handleOnPress = (event: MapViewEvent) => {
    console.log('press', event.nativeEvent);
  };

  const handleOnRegionChange = (event: MapViewEvent) => {
    console.log('region change', event.nativeEvent);
  };

  return (
    <View style={styles.container}>
      <Text>App.js</Text>
      <Button title="getOrientation" onPress={handleGetOrientation} />
      <Button title="getLocation" onPress={handleGetLocation} />
      <Button title="getDeviceInfo" onPress={handleGetDeviceInfo} />
      <Button title="encode" onPress={handleEncodeData} />
      <MapView
        style={styles.map}
        mapType={mapType}
        onPress={handleOnPress}
        onRegionChange={handleOnRegionChange}
      />
      {MAP_TYPES.map((item) => (
        <Button key={item} title={item} onPress={() => setMapType(item)} />
      ))}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  map: {
    width: 300,
    height: 300,
  },
});
