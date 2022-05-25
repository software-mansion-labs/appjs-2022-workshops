// @flow
import type { TurboModule } from 'react-native/Libraries/TurboModule/RCTExport';
import { TurboModuleRegistry } from 'react-native';

type DeviceInfo = $ReadOnly<{|
  name: string,
  model: string,
  systemName: string,
  systemVersion: string,
|}>;

export interface Spec extends TurboModule {
  getOrientation(): string;
  getLocation(): Array<number>;
  getDeviceInfo(): DeviceInfo;
  encode(data: string): Promise<string>;
}

export default (TurboModuleRegistry.get<Spec>('Utils'): ?Spec);
