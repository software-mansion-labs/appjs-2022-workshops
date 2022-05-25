import { Button, StyleSheet, Text, View } from 'react-native';

import React from 'react';
import { Utils } from 'react-native-appjs';

export default function App() {
  const handleGetOrientation = () => {
    console.log('getOrientation', Utils.getOrientation());
  };

  return (
    <View style={styles.container}>
      <Text>App.js</Text>
      <Button title="getOrientation" onPress={handleGetOrientation} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
