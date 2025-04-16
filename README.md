
# 🏋️‍♀️ Fitness Application

A personal fitness tracking Android application built using **Java** and **SQLite**. This app allows users to track their food intake, water consumption, and exercise activities — all in one place. 

## 📱 Features

- ✅ **User Registration & Login**
- 🍽️ **Food Logging**: Add food with calories, category, and description.
- 🚰 **Water Intake Tracking**: Log the time and amount of water consumed.
- 🏃 **Exercise Logging**: Add different exercises with duration and calories burned.
- 📊 **Daily Stats**: View total calories consumed and burned for the day.
- 🧠 **Session Management**: Secure login sessions using SharedPreferences.

---

## 🧩 Tech Stack

| Tech            | Usage                         |
|-----------------|-------------------------------|
| Java            | Main programming language     |
| Android SDK     | Android app development       |
| SQLite          | Local database storage        |
| SharedPreferences | User session management     |

---

## 🗂️ Project Structure

```
FitnessApplication/
├── database/
│   └── AppDatabaseHelper.java
├── repository/
│   └── UserRepository.java
├── ui/
│   ├── LoginActivity.java
│   ├── RegisterActivity.java
│   └── MainActivity.java
├── util/
│   └── SessionManager.java
├── model/
│   └── (Optional: Food, Water, Exercise models)
└── res/
    └── layout/
        ├── activity_login.xml
        ├── activity_register.xml
        └── activity_main.xml
```

---

## 🔑 How Login Works

- The `SessionManager` saves the user ID upon successful login.
- On app startup (`MainActivity`), it checks if the user is logged in.
- If not, it redirects to `LoginActivity`.

```java
SessionManager sessionManager = new SessionManager(this);
if (sessionManager.getUserId() == -1) {
    startActivity(new Intent(this, LoginActivity.class));
    finish();
}
```

---

## 🛠️ Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/vinaymore69/FitnessApplication.git
   ```

2. Open the project in **Android Studio**.

3. Build and run on an emulator or physical device (Android 5.0+).

---

## 🙋‍♂️ Author

**Vinay More**  
📧 [vinaymore69@gmail.com](mailto:vinaymore69@gmail.com)  
📧 [shreayashredhapanhale@gmail.com](mailto:shreyashredhapanhale@gmail.com)  

---

## 📌 License

This project is open-source and free to use. Feel free to modify it for your learning or use in your own projects!

```

---

Let me know if you’d like badges (like Android version, build status, etc.) or screenshots added too!
