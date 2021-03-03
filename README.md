# Lifehack Studio Test

Простой Android-проект для презентации использования некоторых современных библиотек и технологий.

## Скриншоты

|![Preview](https://github.com/gee12/LifehackStudioTest/raw/master/screenshots/screen1.jpg) | ![Preview](https://github.com/gee12/LifehackStudioTest/raw/master/screenshots/screen2.jpg) | ![Preview](https://github.com/gee12/LifehackStudioTest/raw/master/screenshots/screen3.jpg)|
|:-------------------:|:------------------------:|:-----------------:|
| Список компаний | Карточка компании | Открытие расположения на карте |

## Было использовано/реализовано:
* паттерн dependency injection (Dagger2)
* паттерн MVVM (ViewModel, LiveData, Observer)
* получение данных с помощью REST API (Retrofit2, OkHttp3, Gson)
* поддержка на устройствах с API 19 (TLS 1.2)
* корутины для недопустимости блокировки главного потока
* удобная обертка Result вокруг Response
* управление фрагментами с помощью Navigation controller
* динамическое скачивание изображений (Coil)
* RecyclerView

## Чего не хватает:
* Unit-тестов
* кэширования результатов в БД
