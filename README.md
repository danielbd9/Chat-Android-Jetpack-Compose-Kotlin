# Chat Application

#7egend - Challenge

![WhatsApp Image 2024-08-05 at 06 54 09 (1)](https://github.com/user-attachments/assets/7d247e02-6d1c-41cb-bd94-4ef1c67c7758)


## Resume

This is a chat application built using Jetpack Compose for the UI and Kotlin. It features a splash screen, chat interface, and attachment handling. The application is modularized into different components to ensure scalability and maintainability.

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
- UI Components: Contains composable functions for UI components.
- Navigation: Manages navigation between different screens using Jetpack Navigation Compose.
- Dependency Injection: Uses Koin for dependency injection.

### 2. Core Module
- Common Utilities: Provides base use cases and view models.
- Theme: Defines color schemes and typography for the application.
- Components: Additional reusable UI components such as animated text and loading indicators.

### 3. Network Module
- Networking: Manages API calls using Retrofit and Kotlinx Serialization.
- Interceptors: Includes network interceptors for logging and error handling.

### 4. Storage Module
- Database: Manages local data storage using Room database.

### 5. Features Module
#### Chat Feature
- **Data**: Handles chat-specific data operations and database interactions. It is further divided into sub-packages:
  - **local**: Contains classes and interfaces related to local data storage, such as Room database entities and DAOs.
  - **mapper**: Responsible for mapping data between different layers of the application, such as converting database entities to domain models and vice versa.
  - **repository**: Provides implementations for data repositories, which are responsible for handling data operations and providing a clean API for the rest of the application.

- **DI**: The `di` package contains classes and modules related to dependency injection. This package ensures that dependencies are managed and injected properly throughout the Chat feature.
  - **ChatModule.kt**: This file defines the dependency injection module for the Chat feature, providing necessary dependencies like ViewModel, repositories, use cases, etc.

- **Domain**: The `domain` package contains the business logic of the Chat feature. It is further divided into sub-packages:
  - **interactor**: Contains use cases or interactors that encapsulate specific business rules or operations related to the chat functionality.
  - **interfaces**: Defines interfaces for the various components within the domain layer, such as repository interfaces.
  - **model**: Contains domain models representing the core entities of the chat feature, such as `Message`, `User`, and `Attachment`.
  - **useCase**: Houses the use case classes which contain the business logic to perform specific tasks.

- **Presentation**: The `presentation` package handles the user interface and presentation logic of the Chat feature. It contains composables, ViewModels, and other UI-related components.
  - **ui**: This package includes composable functions and UI components specific to the Chat feature, such as `ChatScreen`, `MessageBubble`, and `AttachmentScreen`.
  - **ChatViewModel**: The ViewModel class for managing UI-related data in a lifecycle-conscious way. It communicates with the domain layer to fetch and update chat data.


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
