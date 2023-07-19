## Шаги для запуска автотестов с mySQL:

1. Открыть [сайт Docker](https://labs.play-with-docker.com/) и запустить сессию;
2. Запустить приложение Docker Desktop на компьютере;
3. В IntelijIDEA в терминале набрать **docker-compose up --build**;
4. Открыть новую вкладку в терминале и запустить jar командой **java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar**;
5. Открыть новую вкладку в терминале и запустить автотесты **./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"**.

## Шаги для запуска автотестов с PostgreSQL:

1. Открыть [сайт Docker](https://labs.play-with-docker.com/) и запустить сессию;
2. Запустить приложение Docker Desktop на компьютере;
3. В IntelijIDEA в терминале набрать **docker-compose up --build**;
4. Открыть новую вкладку в терминале и запустить jar командой **java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar**;
5. Открыть новую вкладку в терминале и запустить автотесты **./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"**.

## Шаги для получения отчета ALLURE:

1. Запустить автотесты по указанной выше инструкции, дождаться их окончания;
2. В IntelijIDEA в терминале набрать **./gradlew allureserve**.

## Отчеты:
- [Plan.md](https://github.com/Pr0901/DiplomErm/blob/main/docs/Plan.md)
- [Report.md](https://github.com/Pr0901/DiplomErm/blob/main/docs/Report.md)
- [Summary.md](https://github.com/Pr0901/DiplomErm/blob/main/docs/Summary.md)
