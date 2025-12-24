# 🎵 Playlist Maker v6.0

Учебный Android-проект на **Jetpack Compose**, реализующий базовый функционал музыкального приложения: навигацию между экранами и поиск треков.  
Проект выполнен в рамках обучения и демонстрирует переход на архитектуру **MVVM**.

---

## 📱 Функциональность

- Главный экран с навигацией
- Экран поиска треков
- Имитация сетевого поиска
- Обработка состояний (Initial / Loading / Success / Error)
- Навигация через `NavHost`
- UI на **Jetpack Compose**

---

## 🧱 Архитектура

Проект построен по принципам **MVVM**:

- **UI** — Jetpack Compose
- **ViewModel** — `StateFlow`
- **Domain** — интерфейсы репозиториев
- **Data** — имитация сетевого слоя и локального хранилища
- **Navigation** — `Navigation Compose`

---

## 🛠️ Технологии

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Coroutines + Flow
- MVVM

---

## ⚙️ Требования к окружению

- **Android Studio**: Hedgehog | Iguana (или новее)
- **JDK**: 17
- **minSdk**: 24
- **compileSdk**: 34

---

## ▶️ Запуск проекта

1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/gitbobeek/playlist_maker_android_vladimirovaleksei.git
