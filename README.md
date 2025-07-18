# Mobile Automation Framework

A robust, scalable, and professional mobile automation framework for **Android and iOS applications**, built with **Appium**, **Java**, and **TestNG**. This framework provides comprehensive examples, reusable components, and best practices for automating mobile app testing on real devices and emulators/simulators.

---

## Table of Contents
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Key Components](#key-components)
- [Test Organization](#test-organization)
- [Writing and Running Tests](#writing-and-running-tests)
- [Reports and Logs](#reports-and-logs)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [License](#license)

---

## Project Structure

```
mobile-automation-framework/
├── pom.xml                      # Maven configuration (dependencies, plugins, Java version)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/             # (Reserved for main Java code, currently empty)
│   │   └── resources/           # (Reserved for main resources, currently empty)
│   └── test/
│       ├── java/
│       │   ├── android/         # Android-specific test classes (Appium)
│       │   │   ├── AppiumBasic.java
│       │   │   ├── BaseTest.java
│       │   │   ├── DragDropDemo.java
│       │   │   ├── LongPressTest.java
│       │   │   ├── MisceallanousAppiumAction.java
│       │   │   ├── ScrollDemo.java
│       │   │   └── SwipeDemo.java
│       │   ├── ecommerce/       # E-commerce app test classes (Appium)
│       │   │   ├── BaseTest.java
│       │   │   ├── ecommerce_tc_1.java
│       │   │   ├── ecommerce_tc_2.java
│       │   │   ├── ecommerce_tc_3.java
│       │   │   └── ecommerce_tc_4_Hybrid.java
│       │   ├── ios/             # iOS-specific test classes (Appium)
│       │   │   ├── AppiumClickTest.java
│       │   │   ├── iOSBaseTest.java
│       │   │   ├── iOSBasic.java
│       │   │   ├── iOSLongPress.java
│       │   │   ├── LongPressTap.java
│       │   │   ├── ScrollTest.java
│       │   │   ├── SliderTest.java
│       │   │   └── SwipeTest.java
│       │   └── resources/       # Test APKs and resources (Android)
│       └── resources/           # (Reserved for test resources, currently empty)
├── target/                      # Maven build output (ignored by git)
│   └── surefire-reports/        # TestNG/Surefire test reports (HTML, XML, etc.)
├── .gitignore                   # Git ignore rules
└── README.md                    # Project documentation
```

---

## Setup Instructions

1. **Prerequisites:**
   - Java JDK 11 or above
   - Maven
   - Appium Server (desktop or CLI)
   - 
   **For Android:**
   - Android Studio (for emulator/device management)
   - Android SDK (ensure `adb` is in your PATH)
   
   **For iOS:**
   - macOS with Xcode installed
   - Xcode Command Line Tools
   - iOS Simulator (or real iOS device, properly provisioned)
   - Carthage (if required by your app)
   - Ensure `xcodebuild` and `simctl` are in your PATH

2. **Clone the Repository:**
   ```sh
   git clone <your-repo-url>
   cd mobile-automation-framework
   ```

3. **Install Dependencies:**
   ```sh
   mvn clean install
   ```

4. **Configure Devices/Simulators:**
   - **Android:** Start an emulator or connect a real device. Ensure the device is visible via `adb devices`.
   - **iOS:** Start an iOS simulator via Xcode or CLI, or connect a real device. Ensure the device is visible via `xcrun simctl list`.

5. **Start Appium Server:**
   - Start Appium Desktop or run `appium` in your terminal.

---

## Key Components

### 1. `pom.xml`
- **Dependency Management:**
  - Appium Java Client
  - Selenium Java
  - TestNG
  - SLF4J (logging)
- **Plugins:**
  - Maven Compiler Plugin (Java 11)
  - Maven Surefire Plugin (TestNG integration)

### 2. `src/test/java/android/`
- **AppiumBasic.java:** Basic Appium test example (launch, interact, assert).
- **BaseTest.java:** Common setup/teardown, driver management, and utility methods for Android tests.
- **DragDropDemo.java:** Demonstrates drag-and-drop gesture automation.
- **LongPressTest.java:** Automates long-press gesture and context menu validation.
- **MisceallanousAppiumAction.java:** Advanced actions (activity launch, clipboard, device keys, rotation, etc.).
- **ScrollDemo.java:** Automates scroll actions using Appium gestures.
- **SwipeDemo.java:** Demonstrates swipe gestures and validation.

### 3. `src/test/java/ecommerce/`
- **BaseTest.java:** Setup/teardown and utilities for e-commerce app tests.
- **ecommerce_tc_1.java:** Form validation and toast message handling.
- **ecommerce_tc_2.java:** Product selection, cart operations, and assertions.
- **ecommerce_tc_3.java:** Cart total calculation, terms acceptance, and checkout flow.
- **ecommerce_tc_4_Hybrid.java:** Hybrid app context switching and validation.

### 4. `src/test/java/ios/`
- **iOSBaseTest.java:** Common setup/teardown, driver management, and utility methods for iOS tests.
- **iOSBasic.java:** Basic iOS Appium test example (launch, interact, assert).
- **AppiumClickTest.java:** Demonstrates click actions on iOS elements.
- **iOSLongPress.java / LongPressTap.java:** Automates long-press and tap gestures.
- **ScrollTest.java / iOSScrollTest.java:** Automates scroll actions using Appium gestures.
- **SliderTest.java:** Demonstrates slider interaction.
- **SwipeTest.java:** Demonstrates swipe gestures and validation.

### 5. `src/test/java/resources/`
- **ApiDemos-debug.apk:** Sample Android app for UI automation demos.
- **General-Store.apk:** E-commerce demo app for advanced test scenarios.

### 6. `target/surefire-reports/`
- **Test Reports:**
  - HTML, XML, and text reports generated by TestNG/Surefire after each run.
  - Includes summary, detailed results, and logs for each test class.

### 7. `.gitignore`
- Ignores build outputs, IDE configs, system files, APKs, logs, and generated sources.

---

## Test Organization

- **android/**: Contains generic Android automation examples and advanced Appium actions.
- **ecommerce/**: Contains end-to-end tests for a sample e-commerce app, including form validation, product selection, cart, and checkout flows.
- **ios/**: Contains iOS automation examples and advanced Appium actions for iOS apps.
- **resources/**: Stores APKs used for Android testing. Add new APKs here as needed.

---

## Writing and Running Tests

- **TestNG** is used as the test runner. Annotate test methods with `@Test`.

- **Run all tests:**
  ```sh
  mvn test
  ```

- **Run a specific Android test class:**
  ```sh
  mvn -Dtest=android.MisceallanousAppiumAction test
  mvn -Dtest=ecommerce.ecommerce_tc_1 test
  ```

- **Run a specific iOS test class:**
  ```sh
  mvn -Dtest=ios.iOSBasic test
  mvn -Dtest=ios.iOSScrollTest test
  ```

- **Run a specific test method in a class:**
  ```sh
  mvn -Dtest=ios.iOSScrollTest#scrollAndPickColors test
  ```

- **Test reports** are generated in `target/surefire-reports/` after each run.

---

## Reports and Logs

- **Location:** `target/surefire-reports/`
- **Formats:** HTML, XML, and plain text
- **Contents:**
  - Test summary and detailed results
  - Logs and screenshots (if implemented)
  - Easy navigation for debugging and analysis

---

## Best Practices
- Use the `BaseTest`/`iOSBaseTest` class for driver management and common setup/teardown.
- Organize test data and APKs in the `resources/` folder.
- Use the Page Object Model (POM) for scalable, maintainable test code (recommended for larger projects).
- Keep `.gitignore` updated to avoid committing build outputs, sensitive files, or large binaries.
- Write clear, independent, and reusable test methods.
- Use assertions to validate all critical steps.
- Clean up driver and services in teardown methods to avoid resource leaks.

---

## Contributing
- Fork the repository and create a feature branch.
- Submit a pull request with a clear description of your changes.
- Ensure all tests pass before submitting.
- Follow best practices and keep code/documentation clean and up to date.

---

## License

This project is licensed under the MIT License. 