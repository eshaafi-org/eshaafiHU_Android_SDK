# eShaafiHealthUnit_Android_SDK


# 🧱 Project Structure (Clean Architecture with MVVM)

This SDK follows a Modular Clean Architecture pattern with MVVM, ensuring separation of concerns, testability, and scalability. Below is a breakdown of the structure and responsibilities of each layer and package:

---

## 📚 **Table of Contents**
- [📦 core](#-core)
- [📦 feature.cities (Any Feature)](#-featurecities-any-feature)
- [🧠 MVVM Flow](#-mvvm-flow)
- [✅ Benefits of this Architecture](#-benefits-of-this-architecture)
- [📌 Developer Flow: How to Add a New Feature/API](#-developer-flow-how-to-add-a-new-featureapi)
  - [🔧 Development Steps (Folder Structure + Class Responsibilities)](#-development-steps-folder-structure--class-responsibilities)
  - [🔄 Full Flow for Adding a New Feature/API](#-full-flow-for-adding-a-new-featureapi)
  - [🧱 Folder Naming Convention](#-folder-naming-convention)
  - [📌 Example: Adding a "Doctors" Feature](#-example-adding-a-doctors-feature)

---

## 📦 **core**

This package contains reusable components shared across all features of the SDK.

### **constants**
- `Constants.kt`: Contains static constants used throughout the SDK.

### **network**

#### **dataState**
- `DataState.kt`: Sealed class used for representing API response states (e.g., Success, Error, Loading).

#### **networkConfiguration**
- `NetworkConfigManager.kt`: Manages the configuration for the request header.
- `RequestConfig.kt`: Contains configurations related to API request header setup (e.g., appVersion, deviceType, deviceId, token).

#### **networkInterceptors**
- `HealthUnitInterceptor.kt`, `LoggingInterceptor.kt`, `NetworkInterceptor.kt`, `TokenRefreshInterceptor.kt`: Custom interceptors for header management, logging, internet connection check, and token refreshing respectively.

#### **networkModule**
- `NetworkModule.kt`: Provides Retrofit, OkHttpClient, and other network-related singletons of interceptors using Dependency Injection (e.g., Hilt, Dagger).

#### **networkUtils**
- `NetworkUtils.kt`: Contains utility functions related to checking network connection and Exception parsing.

#### **tokenManager**
- `TokenManager.kt`: Handles token refresh logic and updates it with the old one.

---

## 📦 **feature.cities (Any Feature)**

This package is responsible for the Cities API feature using Clean Architecture principles.

### 🌍 **domain**

Defines core business logic and domain models.

#### **entity**
- `CitiesEntityResponseModel.kt`: Domain entity classes used by the app/UI, separated from API response models.

#### **repository**
- `cities_repository.kt`: Repository interface to abstract data sources (e.g., from API or cache).

#### **usecase**
- `CitiesUseCase.kt`: Interface for cities-related use cases, used in ViewModels.

### 🧩 **data**

Handles API implementation and mapping between DTOs and domain models.

#### **apiService**
- `CitiesApiService.kt`: Retrofit interface defining endpoints for cities-related API calls.

#### **di**
- `CitiesDiModule.kt`: Hilt/Dagger module for injecting the required dependencies for this feature (e.g., repository, use case).

#### **model**
- `CitiesResponseModelDto.kt`: Data Transfer Object (DTO) classes that map raw API responses.

#### **repository**
- `CitiesRepositoryImpl.kt`: Implements the CitiesRepository interface, handles data fetching from the API and transforms data to domain models.

#### **usecase**
- `CitiesUseCaseImpl.kt`: Contains the business logic for fetching cities, abstracts logic from the ViewModel.

---

## 🧠 **MVVM Flow**

- ViewModel calls the UseCase to fetch data.
- The UseCase uses the Repository interface from the domain layer.
- The RepositoryImpl (in the data layer) fetches data via CitiesApiService.
- The raw API response (DTO) is converted to a domain entity.
- The result is returned as a DataState (Success, Error, Loading) to the ViewModel.
- The ViewModel exposes LiveData or StateFlow to the UI.

---

## ✅ **Benefits of this Architecture**

- 🔄 Easily testable and maintainable.
- 🧩 Feature-based modularization.
- 🧼 Separation of concerns.
- ♻️ Reusability of core components like interceptors, token management, and data state handling.

---

## 📌 **Developer Flow: How to Add a New Feature/API**

To maintain consistency and follow Clean Architecture with MVVM, use the following step-by-step guide when creating a new feature (e.g., **Cities**, **Appointments**, etc.).

> 🗂 Every feature lives under `📦 feature/<feature_name>`, and consists of **two main folders**: `domain` and `data`.

---

### 🔧 **Development Steps (Folder Structure + Class Responsibilities)**

```
📦 feature/<feature_name>
├── 1️⃣ domain/
│   ├── 3️⃣ entity/
│   │   └── <Feature>EntityModel.kt         🧠 Clean domain model (used in UI layer)
│   ├── 5️⃣ usecase/
│   │   └── <Feature>UseCase.kt            🔌 Abstract use cases for the feature
│   ├── 7️⃣ repository/
│       └── <Feature>Repository.kt         🔌 Interface to abstract data source
├── 2️⃣ data/
│   ├── 4️⃣ model/
│   │   └── <Feature>ResponseDto.kt        🔄 DTOs for API mapping
│   ├── 6️⃣ usecase/
│   │   └── <Feature>UseCaseImpl.kt        🔁 Implements business logic, calls repo
│   ├── 9️⃣ repository/
│   │   └── <Feature>RepositoryImpl.kt     📡 Implements repository, calls API
│   ├── 8️⃣ apiService/
│   │   └── <Feature>ApiService.kt         🌐 Retrofit interface
│   ├── 🔟 di/
│       └── <Feature>DiModule.kt           💉 Provides UseCase, Repo, Api via DI
```

---

### 🔄 **Full Flow for Adding a New Feature/API**

1️⃣ **Create `feature/<feature_name>` folder**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;📂 e.g., `feature/appointments`

2️⃣ **Inside `domain/` folder:**
- 📁 `entity/` → Create domain model (e.g., `AppointmentEntity.kt`)
- 📁 `usecase/` → Define use case interface (e.g., `AppointmentUseCase.kt`)
- 📁 `repository/` → Define repository interface (e.g., `AppointmentRepository.kt`)

3️⃣ **Inside `data/` folder:**
- 📁 `model/` → Create DTOs for parsing API responses (e.g., `AppointmentResponseDto.kt`)
- 📁 `usecase/` → Implement use case (e.g., `AppointmentUseCaseImpl.kt`)
- 📁 `repository/` → Implement repository logic (e.g., `AppointmentRepositoryImpl.kt`)
- 📁 `apiService/` → Define Retrofit service endpoints (e.g., `AppointmentApiService.kt`)
- 📁 `di/` → Provide dependencies via Dagger/Hilt (e.g., `AppointmentDiModule.kt`)

---

### 🧱 **Folder Naming Convention**

| **Folder**          | **Purpose**                                | **Suffix Suggestion**               |
|---------------------|--------------------------------------------|-------------------------------------|
| `entity`            | Domain model used in ViewModels/UI         | `Entity`, `EntityModel`             |
| `model`             | DTOs for mapping API data                  | `ResponseDto`, `RequestDto`         |
| `usecase`           | Interface for business logic               | `UseCase`                           |
| `usecase` (data)    | Implementation of business logic           | `UseCaseImpl`                       |
| `repository`        | Abstract contract                          | `Repository`                        |
| `repository` (data) | Implementation of repository               | `RepositoryImpl`                    |
| `apiService`        | Retrofit interface                         | `ApiService`                        |
| `di`                | Dagger/Hilt dependency injection setup     | `DiModule`                          |

---

### 📌 **Example: Adding a "Doctors" Feature**

```bash
feature/
└── doctors/
    ├── domain/
    │   ├── entity/DoctorEntity.kt
    │   ├── usecase/DoctorUseCase.kt
    │   └── repository/DoctorRepository.kt
    └── data/
        ├── model/DoctorResponseDto.kt
        ├── usecase/DoctorUseCaseImpl.kt
        ├── repository/DoctorRepositoryImpl.kt
        ├── apiService/DoctorApiService.kt
        └── di/DoctorDiModule.kt
```

