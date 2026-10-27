# GAi BOT Android project (alpha scaffold)

This Android Studio project is a starting point for the requested floating-overlay APK. It includes the supplied browser extension source in `app/src/main/assets/extension/`, a floating overlay permission flow, and a MediaProjection permission/service scaffold.

## Important status
This is **not a finished scanner APK**. The extension relies on Chrome Extension APIs (`chrome.tabs`, content scripts, service worker) and Binance web page context. Those APIs do not run unchanged as a native Android app. The Android scaffold does not yet read pixels/recognize candles or invoke the existing signal engine. Do not use it for trading decisions.

## Build
Open this folder in Android Studio (JDK 17, Android SDK 35), allow Gradle sync, then Build > Build APK(s). The generated debug APK will be under `app/build/outputs/apk/debug/`.

## Remaining work for parity
1. Port the signal engine from `assets/extension/content.js` into a testable Android-compatible module.
2. Implement actual MediaProjection frame capture and chart-area selection / OCR or candle extraction.
3. Connect captured chart data to the same engine, render LONG/SHORT and Entry/TP1/TP2/SL in the overlay.
4. Test Android 13–16 overlay, foreground-service, screen-capture consent, rotation, and battery restrictions.
