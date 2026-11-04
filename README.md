# Stringly - Guitarist Mobile Application

Stringly is an Android Studio mobile application for guitarists, featuring tools from the **Guitarist Mobile Design System** (Stitch Project `#6681830090372735227`).

## Features
- **Sonic Stage (Home)**: Live dashboard with real-time tuner and metronome pulses, chord of the day with interactive fretboard diagrams, and song practice challenges.
- **Precision Metronome**: Real-time pendulum animation, tempo markings (Largo to Prestissimo), time signatures, and tactile beat pips.
- **Chord Library**: 3,200+ chord voicings, interactive root note selector, fretboard diagrams, and audio strum previews.
- **Interactive Tabs**: Tablature reader, tempo indicators, and song catalog with difficulty ratings.
- **Chromatic Tuner**: Radial pitch gauge, real-time cent-offset needle display, standard guitar peg selection, and microphone listener.
- **Jam Spot Forum**: Community feed for sharing guitar licks, technique inquiries, and gear setups.
- **Virtual DSP Pedalboard**: 48kHz signal chain with stompbox pedals (Tube Screamer, Big Muff Pi, Small Clone Chorus, Flashback Delay) with toggleable bypass and parameter dials.
- **Web Frontend Simulator**: Standalone interactive simulator at `index.html` to preview all Stitch screens.

## Tech Stack
- **Platform**: Android (minSdk 26, targetSdk 35)
- **Language**: Kotlin 2.0
- **UI Framework**: Jetpack Compose (Material3)
- **Navigation**: Jetpack Navigation Compose
- **Design System**: Stitch Mobile Design System

## Getting Started
1. Open Android Studio.
2. Select **File > Open...** and navigate to this directory.
3. Allow Gradle to sync dependencies.
4. Run on an emulator or connected Android device (`Shift + F10`).
