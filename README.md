# Chat-Android-Kotlin

### README.md

# Chat Application

This is a chat application built using Jetpack Compose for the UI and Kotlin. It features a splash screen, chat interface, and attachment handling. The application is modularized into different components to ensure scalability and maintainability.

![Chat Splash](https://github.com/user-attachments/assets/f50a1b9c-49fe-4fb2-91a2-45d725725041)
![Chat Splash 2](https://github.com/user-attachments/assets/5211fb57-0569-453a-85e5-5097e5d34574)
![Chat Message](https://github.com/user-attachments/assets/74e1ad30-f91b-4033-a959-b3989f7bf4e4)
![Chat Message 2](https://github.com/user-attachments/assets/f1cba4fb-42ec-4dc0-ba85-7c1a5954ae74)


## Features

- Splash Screen: Displays an animated splash screen using Lottie animations.
- Chat: Allows users to send and receive messages, with support for attachments.
- Theming: Custom themes using Material 3.

## API

The application uses a mock API for fetching conversation data.

The API endpoint is:
[Conversation API](https://private-edd460-7egendchallengeandroid.apiary-mock.com/conversation)

## Modules

### 1. App Module
- UI Components: Contains composable functions for UI components like SplashScreen, ChatScreen, and AttachmentScreen.
- Navigation: Manages navigation between different screens using Jetpack Navigation Compose.
- Dependency Injection: Uses Koin for dependency injection.

### 2. Core Module
- Common Utilities: Provides base use cases and view models.
- Theme: Defines color schemes and typography for the application.
- UI Components: Additional reusable UI components such as animated text and loading indicators.

### 3. Network Module
- Networking: Manages API calls using Retrofit and Kotlinx Serialization.
- Interceptors: Includes network interceptors for logging and error handling.

### 4. Storage Module
- Database: Manages local data storage using Room database.
- Entities and DAOs: Defines entities and data access objects for storing chat messages, users, and attachments.

### 5. Features Module
- Chat Data: Handles chat-specific data operations and database interactions.
- Mappers: Maps data between domain models and database entities.
- Repository: Provides a repository implementation for chat data.

## Dependencies

- Jetpack Compose: For building declarative UIs.
- Kotlinx Serialization: For JSON parsing.
- Retrofit: For network requests.
- Koin: For dependency injection.
- Room: For local data storage.
- Lottie: For animations.

## Testing

The application includes unit tests and UI tests to ensure functionality and reliability.

- Unit Tests: Written using MockK.
- UI Tests: Written using Jetpack Compose and JUnit.
