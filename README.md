# Noise-detection
Android application that measures and displays real-time sound intensity levels using the device microphone. Prototype implementation focused on basic audio monitoring functionality
<br>
Author - Milan Sharma

# 🔊 Basic Noise Detection Module (Android)

This is a simple Android-based noise detection application that measures environmental sound levels using the device microphone.

The application monitors sound intensity in real-time and visually indicates changes when a specific decibel level is reached.

---

## 📌 Project Overview

This project represents a basic implementation of real-time sound level monitoring on Android.  

It focuses only on detecting environmental noise and updating the UI based on the measured decibel level.

---

## 🚀 Features

- 🎙 Real-time environmental noise detection
- 📊 Live decibel (dB) level calculation
- 🎨 UI color changes when a defined sound level threshold is reached
- 📱 Simple and clean user interface
- 🔄 Continuous sound monitoring

---

## 🧠 How It Works

1. The application requests microphone permission.
2. The device microphone captures audio input.
3. The system calculates the approximate sound intensity (in dB).
4. When the sound crosses a predefined level:
   - The UI color changes to indicate high noise.

---

## 🔐 Permissions

- Microphone permission is required for detecting environmental sound.
- All processing is done locally on the device.

---

## 🛠 Tech Stack

- Android Studio
- Java
- Android SDK
- MediaRecorder API
- XML for UI design

---

## 📈 Purpose of This Module

This project demonstrates:

- Basic real-time audio processing in Android
- Decibel level estimation
- UI response based on sensor input
