# Курсовой проект профессии Тестировщик
[![Build status](https://ci.appveyor.com/api/projects/status/koeaghivunv60fab?svg=true)](https://ci.appveyor.com/project/MaksimNosov/aqa-qamid-diplom)

***[Планирование автоматизации](https://github.com/MaksimNosov/Aqa-qamid-diplom/blob/main/documents/Plan.md)***

***[Отчёт о проведенном тестировании](https://github.com/MaksimNosov/Aqa-qamid-diplom/blob/main/documents/Report.md)***

***[Отчёт по итогам автоматизации](https://github.com/MaksimNosov/Aqa-qamid-diplom/blob/main/documents/Summary.md)***

## Необходимое окружение

* JDK версия 11
* GIT
* IntelliJ IDEA Ultimate
* Docker
* Свободные порты 8080, 9999, 5432, 3306
 
## Шаги для локального запуска проекта

1. **Склонировать проект**
* открыть терминал GIT для целевой папки проекта
* склонировать репозиторий командой *git clone https://github.com/MaksimNosov/Aqa-qamid-diplom*

2. **Открыть Docker Desktop**

3. **Открыть проект в IntelliJ IDEA**
* в терминале подключить базу данных MySQL командой *docker-compose up*
* открыть второй терминал и запустить SUT командой *java -jar artifacts/aqa-shop.jar*

4. **Запустить тесты и отчет**
* открыть второй терминал и запустить тесты командой *./gradlew clean test*
* после прохождения тестов создать отчет командой *./gradlew allureserve*