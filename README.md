# Mobile Automation Framework

This project is a professional mobile automation framework built using **Appium**, **Java**, and **TestNG**. It is designed for automating Android application testing, providing robust examples and reusable components for scalable test automation.

---

## Table of Contents
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Key Components](#key-components)
- [Writing and Running Tests](#writing-and-running-tests)
- [Reports and Logs](#reports-and-logs)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [License](#license)

---

## Project Structure

```
mobile-automation-framework/
├── pom.xml                     # Maven configuration file
├── src/
│   ├── main/
│   │   ├── java/               # (Reserved for main Java code, if needed)
│   │   └── resources/          # (Reserved for main resources)
│   └── test/
│       ├── java/
│       │   ├── android/        # Android-specific test classes
│       │   │   ├── AppiumBasic.java
│       │   │   ├── BaseTest.java
│       │   │   ├── LongPressTest.java
│       │   │   ├── MisceallanousAppiumAction.java
│       │   │   ├── ScrollDemo.java
│       │   │   └── SwipeDemo.java
│       │   └── resources/      # Test resources (e.g., APKs)
│       │       └── ApiDemos-debug.apk
│       └── resources/          # (Reserved for test resources)
├── target/                     # Build output (ignored by git)
└── .gitignore                  # Git ignore rules
```

---

## Setup Instructions

1. **Prerequisites:**
   - Java JDK 8 or above
   - Maven
   - Android Studio (for emulator/device management)
   - Appium Server (desktop or CLI)
   - Android SDK (ensure `adb` is in your PATH)

2. **Clone the Repository:**
   ```sh
   git clone <your-repo-url>
   cd mobile-automation-framework
   ```

3. **Install Dependencies:**
   ```sh
   mvn clean install
   ```

4. **Configure Devices/Emulators:**
   - Start an Android emulator or connect a real device.
   - Ensure the device is visible via `adb devices`.

5. **Start Appium Server:**
   - Start Appium Desktop or run `appium` in your terminal.

---

## Key Components

### 1. `pom.xml`
- Maven configuration file specifying dependencies (Appium Java client, Selenium, TestNG, etc.).

### 2. `src/test/java/android/`
- **AppiumBasic.java**: Basic Appium test example.
- **BaseTest.java**: Base class for test setup/teardown, driver initialization, and common utilities.
- **LongPressTest.java**: Demonstrates long-press gesture automation.
- **ScrollDemo.java**: Shows how to automate scroll actions.
- **SwipeDemo.java**: Example for swipe gestures.
- **MisceallanousAppiumAction.java**: Advanced actions (activity launch, clipboard, device keys, etc.).

### 3. `src/test/java/resources/`
- **ApiDemos-debug.apk**: Sample Android app used for testing.

### 4. `target/`
- Maven build output (ignored by git).

### 5. `.gitignore`
- Specifies files/folders to be ignored by git (build outputs, IDE configs, system files, etc.).

---

## Writing and Running Tests

- **TestNG** is used as the test runner. Test methods are annotated with `@Test`.
- To run all tests:
  ```sh
  mvn test
  ```
- To run a specific test class:
  ```sh
  mvn -Dtest=android.MisceallanousAppiumAction test
  ```

---

## Reports and Logs

- Test reports are generated in the `target/surefire-reports/` directory after each run.
- Includes HTML, XML, and text reports for easy analysis.

---

## Best Practices
- Use the `BaseTest` class for driver management and common setup/teardown.
- Keep test data and resources organized in the `resources/` folders.
- Use page object pattern for scalable test code (can be added as the project grows).
- Keep `.gitignore` updated to avoid committing build outputs or sensitive files.

---

## Contributing
- Fork the repository and create a feature branch.
- Submit a pull request with a clear description of your changes.
- Ensure all tests pass before submitting.

---

## License
This project is licensed under the MIT License. 