#pragma once

#import <React/RCTViewManager.h>

@interface RNMapViewManager : RCTViewManager <UIGestureRecognizerDelegate>

@property (nonatomic, copy) RCTBubblingEventBlock onPress;
@property (nonatomic, copy) RCTDirectEventBlock onRegionChange;

@end
