import { Button, StyleSheet, Text, View } from 'react-native';
import { MapView, Utils } from 'react-native-appjs';

import React from 'react';

export default function App() {
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

  return (
    <View style={styles.container}>
      <Text>App.js</Text>
      <Button title="getOrientation" onPress={handleGetOrientation} />
      <Button title="getLocation" onPress={handleGetLocation} />
      <Button title="getDeviceInfo" onPress={handleGetDeviceInfo} />
      <Button title="encode" onPress={handleEncodeData} />
      <MapView style={styles.map} />
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
