# XBATIME - Revolutionary Game Performance Optimizer

![XBATIME](https://img.shields.io/badge/XBATIME-Game%20Turbo-00D9FF?style=flat-square)
![Android](https://img.shields.io/badge/Android-24%2B-green?style=flat-square)
![API Level](https://img.shields.io/badge/Target%20API-34-blue?style=flat-square)
![License](https://img.shields.io/badge/License-Proprietary-red?style=flat-square)

## 📱 Overview

XBATIME is a revolutionary game performance optimization and macro automation app for Android that works **WITHOUT ROOT**, using Shizuku for safe system access.

### ⚡ Key Features (Phase 1)

- **Shizuku Integration**: Safe system access without root
- **Splash Screen**: Beautiful loading animation
- **Shizuku Check Flow**: 3-state status checking (Not Installed, No Permission, Ready)
- **Authentication System**: Login/Register foundation
- **Background Services**: Macro, Monitor, Shizuku Connection, Sync
- **HTML5 UI**: Modern WebView-based interface

## 📁 Project Structure

```
app/
├── src/main/
│   ├── java/com/xbatime/app/
│   │   ├── activities/          # All Activities (Splash, Auth, etc.)
│   │   ├── managers/            # ShizukuManager & utility managers
│   │   ├── services/            # Background services
│   │   ├── receivers/           # BroadcastReceivers
│   │   ├── models/              # Data models
│   │   ├── database/            # Room database
│   │   ├── network/             # Retrofit API
│   │   ├── utils/               # Utility classes
│   │   └── XBATIMEApp.kt        # Application class
│   ├── res/
│   │   ├── layout/              # XML layouts
│   │   ├── values/              # Strings, colors, themes
│   │   ├── xml/                 # Network config, file paths
│   │   └── drawable/            # Drawables & vectors
│   ├── assets/frontend/
│   │   ├── splash.html          # Splash screen UI
│   │   ├── shizuku_check.html   # Shizuku status check UI
│   │   ├── login.html           # Login page
│   │   ├── register.html        # Register page
│   │   ├── main.html            # Main container setelah login
│   │   ├── css/                 # Stylesheets
│   │   ├── js/                  # JavaScript files
│   │   ├── fonts/               # Web fonts
│   │   ├── icons/               # Font Awesome icons
│   │   └── images/              # Image assets
│   └── AndroidManifest.xml
├── build.gradle                 # App-level Gradle config
└── proguard-rules.pro           # ProGuard/R8 rules
```

## 🚀 Quick Start

### Prerequisites
- Android Studio Hedgehog+ (2023.1.1+)
- Java 11+
- Android SDK 34
- Gradle 8.1+

### Setup

1. **Clone Repository**
```bash
git clone https://github.com/dikacompany11/XBATIME.git
cd XBATIME
```

2. **Open in Android Studio**
```bash
Open File > Open > Select XBATIME directory
```

3. **Build & Run**
```bash
# Build APK
./gradlew build

# Run on emulator/device
./gradlew installDebug
```

## 📋 Shizuku Integration Flow

### Startup Flow

```
SplashActivity (2-5 sec)
    ↓
    ├─ Check Shizuku status (background)
    ├─ Ping Shizuku service
    ├─ Check permissions
    ↓
ShizukuCheckActivity
    ├─ State A: Not Installed → Show Download button
    ├─ State B: No Permission → Show Grant Permission button
    └─ State C: Ready → Auto-continue to Auth
    ↓
AuthActivity (Login/Register)
```

### State Codes

```javascript
STATUS_CODE = {
  0: 'SHIZUKU_NOT_INSTALLED',
  1: 'SHIZUKU_NOT_RUNNING',
  2: 'SHIZUKU_NO_PERMISSION',
  3: 'SHIZUKU_READY',
  4: 'SHIZUKU_BINDER_DEAD',
  5: 'SHIZUKU_PERMISSION_DENIED'
}
```

## 🔐 Security

- ✅ HTTPS only (NetworkSecurityConfig)
- ✅ No root required (Shizuku)
- ✅ Encrypted SharedPreferences for auth tokens
- ✅ JWT tokens with expiry
- ✅ SQL injection prevention
- ✅ XSS prevention
- ✅ Proguard/R8 obfuscation

## 📚 Technologies

### Android
- **AndroidX**: Core, AppCompat, Navigation, Lifecycle
- **Database**: Room ORM
- **Networking**: Retrofit2, OkHttp, Gson
- **Security**: Encrypted SharedPreferences
- **Background**: WorkManager, Foreground Services
- **Shizuku**: v13.1.5

### Frontend (HTML/CSS/JS)
- **HTML5**: Semantic markup
- **CSS3**: Grid, Flexbox, Animations, Variables
- **JavaScript**: ES6+, WebView Bridge
- **Icons**: Font Awesome 6.4+
- **Fonts**: Google Fonts (Poppins, Inter, JetBrains Mono)

### Backend (Phase 2)
- **Framework**: Spring Boot
- **Database**: PostgreSQL (Railway)
- **Caching**: Redis
- **API**: REST with JWT auth
- **Deployment**: Railway

## 🎨 Design System

### Color Palette
```css
/* Primary */
--primary: #00D9FF       /* Cyan */
--primary-variant: #0099CC

/* Secondary */
--secondary: #FF6B9D    /* Pink */
--secondary-variant: #CC3366

/* Backgrounds */
--background: #0A0E27   /* Dark navy */
--surface: #1A1F3A
--surface-variant: #2A2F4A

/* Status */
--success: #4CAF50
--warning: #FF9800
--error: #F44336
--info: #2196F3
```

### Typography
- **Headings**: Poppins (Bold, 600-700)
- **Body**: Inter (Regular, 400-500)
- **Code**: JetBrains Mono (Monospace)

## 📱 Permissions

### System Access (via Shizuku)
- `PACKAGE_USAGE_STATS` - App usage stats
- `QUERY_ALL_PACKAGES` - List installed apps
- `INJECT_EVENTS` - Input injection

### User Permissions
- `INTERNET` - Network access
- `CAMERA` - Screen recording
- `RECORD_AUDIO` - Audio recording
- `ACCESS_FINE_LOCATION` - Location (optional)
- `SYSTEM_ALERT_WINDOW` - Overlay windows

## 🔄 Services

### MacroService
Foreground service untuk overlay macro dan floating gamepad.

### MonitorService
Foreground service untuk real-time FPS, temperature, RAM monitoring overlay.

### ShizukuConnectionService
Maintain koneksi Shizuku dengan auto-reconnect logic.

### SyncService
Periodic background work untuk sinkronisasi data ke Railway setiap 6 jam.

## 📊 Database Schema (Phase 2)

### Room Entities
- **User**: Auth & profile data
- **Game**: Game metadata & performance stats
- **Macro**: Recorded macro sequences
- **Device**: Device info & sensor calibration
- **Session**: Gaming sessions log

## 🌐 API Endpoints (Phase 2 - Railway)

```
POST   /api/auth/register
POST   /api/auth/login
POST   /api/auth/refresh
POST   /api/auth/logout
GET    /api/user/profile
PUT    /api/user/profile
GET    /api/games
POST   /api/games
GET    /api/macros
POST   /api/macros
GET    /api/analytics
```

## 🛠️ Development

### Code Style
- Kotlin for new code (Java legacy support)
- Google Java Style Guide
- Meaningful variable names
- Comprehensive comments
- MVVM architecture

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

### Debugging
- Timber logging library
- BuildConfig flags
- Logcat filtering

## 📦 Build Variants

### Debug
```bash
./gradlew installDebug
```

### Release
```bash
./gradlew assembleRelease
```

## 🚀 Deployment (Phase 2)

### Backend (Railway)
```bash
railway connect
railway up
```

### Mobile (Google Play Store)
```bash
# Generate signed APK/AAB
./gradlew bundleRelease
```

## 📝 Changelog

### v1.0.0 (Phase 1 - Core Infrastructure)
- ✅ Project structure setup
- ✅ Shizuku integration (check, permission, listeners)
- ✅ Splash screen with animations
- ✅ Shizuku check flow (3 states)
- ✅ Authentication foundation
- ✅ Background services scaffold
- ✅ HTML5 frontend base

### v1.1.0 (Phase 2 - Main Features)
- ⏳ Game Space & Booster
- ⏳ Macro Recorder & Player
- ⏳ Floating Gamepad
- ⏳ Backend API (Railway)
- ⏳ Database synchronization

### v1.2.0 (Phase 3 - Advanced)
- ⏳ Sensor Mapping & Gyro
- ⏳ Thermal Cooler
- ⏳ Developer Dashboard
- ⏳ Advanced Analytics

## 📄 License

Proprietary - XBATIME © 2024 All rights reserved

## 👨‍💻 Authors

- **Developer**: dikacompany11
- **Project**: XBATIME

## 📧 Support

For issues, questions, or suggestions:
- GitHub Issues: [XBATIME Issues](https://github.com/dikacompany11/XBATIME/issues)
- Email: support@xbatime.com

## 🙏 Acknowledgments

- **Shizuku**: For providing safe system access without root
- **AndroidX**: For modern Android development libraries
- **Google**: For Android platform and development tools

---

**Made with ⚡ for gamers who demand performance**
