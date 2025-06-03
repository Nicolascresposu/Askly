# Askly - Social Prediction Game 🎮

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)](https://kotlinlang.org)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)

Askly is an innovative social prediction game designed to strengthen friendships through fun, interactive gameplay. Players answer questions by placing pins on a triangular graph and then try to predict where their friends will place theirs.

## 🌟 Features 🌟

- **Social Gameplay**: Play with up to 8 friends in real-time
- **Unique Prediction Mechanics**: Score points by predicting friends' answers
- **Custom Questions**: Create your own question packs or use built-in ones
- **Visual Feedback**: Triangular graph interface makes responses intuitive
- **Session History**: Review past games and compare scores
- **Quick Sessions**: Fast-paced rounds perfect for social gatherings

## Screenshots

![Askly screenshot 1](https://raw.githubusercontent.com/Nicolascresposu/Nicolascresposu.github.io/refs/heads/main/other_repos_images/Askly/Screenshot1.png)

## Technical Implementation

### Architecture
- **Client-Server Model**: Host creates game, others join via code
- **Android Native**: Built with Kotlin in Android Studio
- **Local Database**: SQLite for user accounts and question storage
- **P2P Networking**: Device-to-device communication for multiplayer

### Key Components
1. **Authentication System** (JWT secured)
2. **Game Service** (Manages rounds, scoring, and synchronization)
3. **Question Engine** (Random question selection and custom question handling)
4. **Player Management** (Profiles and statistics)
5. **Match System** (Active game state management using JSON)

## Game Mechanics

1. **Host creates** a game and selects questions
2. **Players join** and select their colors
3. **Each round**:
    - Question is displayed with 3 possible responses at triangle vertices
    - Players place their "Original" pin representing their answer
    - Players then predict 3 friends' answers by placing prediction pins
4. **Scoring**:
    - Points awarded based on prediction accuracy (closer pins = more points)
    - Score formula: `S = 100 * e^(-d/30)` where d is distance in dp
5. **Game ends** after set number of rounds, final scores displayed

## Getting Started

### Prerequisites
- Android Studio Flamingo or later
- Android SDK 33+
- Kotlin 1.9.0

### Installation

**Option 1: Clone the repository:**
   ```bash
   git clone https://github.com/Nicolascresposu/Askly
   
    Open project in Android Studio

    Build and run on emulator or physical device
   ```

**Option 2: Download from the google Play Store:**

Coming soon. Eventually. I promise.

### Documentation

Coming soon.

### Contributing

Contributions are welcome! Please follow these steps:

    Fork the project

    Create your feature branch (git checkout -b feature/AmazingFeature)

    Commit your changes (git commit -m 'Add your freaky ahh feature')

    Push to the branch (git push origin feature/AmazingFeature)

    Open a Pull Request


