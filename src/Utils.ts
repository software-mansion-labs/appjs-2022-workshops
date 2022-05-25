import { NativeModules } from 'react-native';

// @ts-ignore
const isTurboModuleEnabled = global.__turboModuleProxy != null;

export const Utils = isTurboModuleEnabled
  ? require('./NativeUtils').default
  : NativeModules.Utils;
