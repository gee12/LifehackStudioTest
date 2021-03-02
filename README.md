# Lifehack Studio Test

## Было использовано/реализовано:
* паттерн dependency injection (Dagger2)
* паттерн MVVM (ViewModel, LiveData, Observer)
* получение данных с помощью REST API (Retrofit2, OkHttp3, Gson)
* корутины для недопустимости блокировки главного потока
* удобная обертка Result вокруг Response
* управление фрагментами с помощью Navigation controller
* динамическое скачивание изображений (Coil)
* RecyclerView

## Чего не хватает:
* поддержки на устройствах API 19-20 (проблема с OkHttp и протоколом TLS)
* Unit-тестов
* кэширования результатов в БД
