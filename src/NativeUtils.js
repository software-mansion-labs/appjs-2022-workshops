// @flow
import type { TurboModule } from 'react-native/Libraries/TurboModule/RCTExport';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  getOrientation(): string;
}

export default (TurboModuleRegistry.get<Spec>('Utils'): ?Spec);
