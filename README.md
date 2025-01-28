# 💊 Afya - Community Medical Sharing App

[![Kotlin Version](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg)](https://kotlinlang.org)
[![Compose Version](https://img.shields.io/badge/Jetpack%20Compose-1.10.0-brightgreen)](https://developer.android.com/jetpack/compose)

Afya is a community-driven Android application that enables users to share unneeded medical supplies (OTC medications, unused prescriptions*), and locate hard-to-find drugs through a trusted network. *Always consult healthcare providers before sharing prescription medications.

## Features ✨
- **Drug Listings**: Post unused medications with details (name, expiry, dosage, quantity).
- **Smart Search**: Filter medications by name, location, expiry date, and availability.
- **Donation System**: Coordinate safe pickups/deliveries with built-in chat.
- **Expiration Alerts**: Automatic notifications for near-expiry medications.
- **Community Trust**: Rating system for donors/recipients and report functionality.
- **Interactive Map**: Locate nearby available medications with GPS integration.

## Technologies Used 🛠️
- **Jetpack Compose** - Modern Android UI toolkit
- **Kotlin Coroutines** - Asynchronous programming
- **ViewModel & LiveData** - Lifecycle-aware data management
- **Room Database** - Local data persistence
- **Firebase** - Authentication and cloud Datastore (if used)
- **Material Design 3** - UI components and theming

## Installation & Setup 🚀

### Prerequisites
- Android Studio Ladybug Feature Drop (2024.2.2)
- Android SDK 33+
- Kotlin 2.0.0

### Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/drLacheheb/Afya.git
   ```
2. **Open in Android Studio**
    - Select "Open an Existing Project"
    - Choose the cloned directory

3. **Build and Run**
    - Click the ▶️ Run button in Android Studio
    - Choose a connected device or emulator

### Configuration ⚙️

#### Firebase Setup (if applicable)
not yet

#### TPs
You can see the `TPs` folder for the tasks description.

## Contributing 🤝
We welcome contributions! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Commit changes**
    - Follow Conventional Commits format
    - Example: `feat: add user registration screen`
4. **Push and create Pull Request**
    - Ensure code follows Kotlin style guide
    - Include test coverage for new features
    - Update documentation when applicable

### Code Style
- Use meaningful variable names
- Follow official Kotlin coding conventions
- Keep composables small and focused
- Use ViewModel for business logic

## License 📄
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact 📧
For questions or suggestions:

**Project Maintainer**: [Ahmed Yacine drLacheheb]   
**GitHub Issues**: [Create New Issue](#)
