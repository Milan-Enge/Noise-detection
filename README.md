# Noise-detection
Android application that measures and displays real-time sound intensity levels using the device microphone. Prototype implementation focused on basic audio monitoring functionality
<br>
Author - Milan Sharma

# 🔊 Basic Noise Detection Module (Android)

This project is a simple Android-based noise detection module developed to measure environmental sound levels using the device's built-in microphone. The application continuously captures surrounding audio input and processes it to estimate the sound intensity in decibels (dB). It is designed as a foundational implementation of real-time audio monitoring on Android devices.

The primary goal of this module is to demonstrate how sound data can be captured, analyzed, and reflected dynamically in a mobile application interface. It does not include advanced alert systems or cloud-based integrations, as this version focuses purely on the core functionality of detecting environmental noise and visually representing changes in sound intensity within the app interface.

This module serves as a basic technical implementation and represents a simplified component extracted from a broader safety monitoring concept.

---

## 📌 Project Overview

The Noise Detection Module is developed to explore real-time audio processing capabilities in Android using Java and native Android APIs. The system captures raw sound input from the microphone and calculates an approximate decibel level to represent environmental noise intensity.

The application continuously monitors the incoming audio signal and updates the user interface dynamically based on the detected sound level. When the measured decibel value reaches or crosses a predefined threshold, the interface responds visually by changing color. This visual feedback mechanism allows users to easily identify variations in surrounding noise levels without relying on alerts or notifications.

This project is intentionally kept minimal and focused. It does not include features such as notifications, data storage, cloud synchronization, or artificial intelligence processing. Instead, it emphasizes understanding the core mechanics of microphone access, real-time audio analysis, decibel estimation, and responsive UI behavior within an Android environment.

Overall, this module acts as a practical demonstration of how environmental sound detection can be implemented at a basic level in a mobile application.

## 🚀 Features

The Noise Detection Module provides real-time environmental sound monitoring using the device's built-in microphone. The application continuously captures surrounding audio and processes it to estimate the current sound intensity in decibels (dB). This allows users to observe live changes in environmental noise levels directly within the application interface.

One of the core features of this system is dynamic visual feedback. Instead of using alerts or notifications, the application responds to high sound levels by changing the color of the user interface when a predefined decibel threshold is reached. This visual indication makes it easy to understand whether the surrounding environment is relatively quiet or experiencing higher noise intensity.

The system operates continuously while active, ensuring that sound level changes are reflected instantly on the screen. The user interface is designed to be simple, clean, and easy to interpret, focusing only on essential information such as the current noise level and visual status indication. 

All processing is performed locally on the device without sending any data externally. The application strictly uses microphone access for real-time monitoring purposes and does not store or transmit audio recordings. This ensures that the implementation remains lightweight and focused entirely on real-time sound level detection and UI responsiveness.

Overall, the feature set is intentionally minimal and centered around core functionality: capturing environmental sound, estimating decibel levels, and visually representing noise intensity changes within the application interface.

---

## 🧠 How It Works

When the application starts, it requests permission to access the device microphone. Once permission is granted, the system initializes the audio recording component using Android's built-in audio APIs. The microphone continuously captures ambient sound signals from the surrounding environment in real time.

The captured audio input is processed to calculate an approximate sound intensity value, which is represented in decibels (dB). The application continuously updates this calculated value and displays it within the user interface so that users can observe live changes in environmental noise levels.

A predefined decibel threshold is set within the system logic. As long as the detected sound level remains below this threshold, the interface maintains its normal visual state. However, when the sound intensity reaches or exceeds the defined level, the application dynamically changes the UI color to visually indicate elevated noise conditions.

This entire process runs in real time while the application is active. The monitoring continues consistently, updating the displayed decibel value and visual indicators without interruption. No alerts, notifications, or background communications are triggered. The system simply focuses on detecting environmental sound levels and reflecting changes immediately through the user interface.

The implementation relies entirely on Android’s local audio processing capabilities, ensuring that all operations are performed directly on the device without external dependencies.

## 🔐 Permissions

This application requires access to the device microphone in order to perform real-time environmental noise detection. Since the core functionality of the app depends entirely on capturing ambient sound input, microphone permission is essential for the system to operate correctly.

When the application is launched, it requests runtime permission from the user to access the device’s audio recording capability. The app follows Android’s permission handling guidelines, ensuring that microphone access is requested transparently and only when required. Without this permission, the application cannot measure sound intensity or calculate decibel levels.

It is important to note that the application does not record, store, or transmit any audio data. The microphone input is used strictly for real-time sound level estimation. All audio processing occurs locally on the device, and no external servers, cloud services, or third-party systems are involved in handling sound data.

The permission usage is minimal and limited only to what is required for noise detection functionality. The system does not request unnecessary permissions such as storage access, internet access, or background data sharing. This keeps the implementation simple, focused, and privacy-conscious.

Overall, the permission model is designed to ensure transparency, local processing, and user privacy while enabling accurate environmental sound monitoring.

---

## 🛠 Tech Stack

The Noise Detection Module is developed using Android Studio as the primary development environment. The application is built using Java as the core programming language for implementing the application logic and real-time audio processing mechanisms.

The system relies on the Android SDK and native audio handling APIs, such as MediaRecorder or related audio classes, to capture microphone input and process environmental sound data. These APIs allow the application to access raw audio signals and compute approximate sound intensity values in decibels.

The user interface is designed using XML layout files, following Android’s standard UI development practices. The layout components are structured to provide a clean and responsive design that dynamically updates based on the detected sound level.

The entire implementation runs locally on the Android device without requiring external libraries, machine learning frameworks, cloud services, or backend infrastructure. The project focuses on leveraging built-in Android capabilities to demonstrate real-time sound monitoring and dynamic UI behavior.

This tech stack ensures that the application remains lightweight, efficient, and easy to understand while showcasing fundamental Android development concepts such as permission handling, audio processing, and responsive UI updates.

---

## 🎯 Purpose of the Module

The primary purpose of this module is to demonstrate the practical implementation of real-time environmental noise detection within an Android application. It serves as a focused example of how microphone input can be accessed, processed, and reflected dynamically through a user interface.

This project aims to explore the foundational aspects of audio signal handling on mobile devices, including capturing ambient sound, estimating decibel levels, and triggering UI changes based on predefined conditions. By keeping the implementation minimal and targeted, the module emphasizes clarity, learning value, and core functionality rather than feature expansion.

The application highlights how environmental data can influence interface behavior in real time. It showcases the integration between sensor-based input (microphone audio) and responsive UI components, demonstrating how mobile applications can react dynamically to changing external conditions.

Additionally, the module acts as a practical learning implementation for understanding Android permission models, audio APIs, and continuous real-time data monitoring within an activity lifecycle.

Overall, the purpose of this module is to present a clear and structured example of basic sound level monitoring in Android, focusing on technical fundamentals and accurate real-time processing without unnecessary complexity.
