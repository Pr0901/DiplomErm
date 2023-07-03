# Перечень автоматизируемых сценариев

| №п/п | Наименование                              | Пункты                                                                                                                                                           | Ожидаемый результат                                                                          |
|------|-------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| 1    | Положительная проверка “Купить”           | 1.Нажать “Купить” 2.Верно заполнить номер карты; 3.Верно заполнить месяц; 4.Верно заполнить год; 5.Верно заполнить владелец; 6.Верно заполнить CVC/CVV;          | В правом верхнем углу высвечивается окно “Успешно Операция одобрена Банком”                  |
| 2    | Положительная проверка “Купить в кредит”  | 1.Нажать “Купить в кредит” 2.Верно заполнить номер карты; 3.Верно заполнить месяц; 4.Верно заполнить год; 5.Верно заполнить владелец; 6.Верно заполнить CVC/CVV; | В правом верхнем углу высвечивается окно “Успешно Операция одобрена Банком”                  |
| 3    | Положительные проверки поля “Владелец”    | 1.Ввести имя/фамилию на русском языке с буквой Ё (Например - Сато Ёко)                                                                                           | Поле принимает значение. Дополнительных надписей не появляется.                              |
|      |                                           | 2. Ввести имя/фамилию на русском языке с дефисом (Мария-Владислава Петрова)                                                                                      | Поле принимает значение. Дополнительных надписей не появляется.                              |
|      |                                           | 3.Ввести имя и фамилию на русском языке из 50 символов в сумме,учитывая пробелы (Баярсайханббадамсэрээжид Хоувеллтерллоукаммингбрюс)                             | Поле принимает значение. Дополнительных надписей не появляется.                              |
|      |                                           | 4.Ввести имя/фамилию на русском языке с апострофом (Шарль Д’Артаньян)                                                                                            | Поле принимает значение. Дополнительных надписей не появляется.                              |
|      |                                           | 5.Ввести имя и фамилию на английском языке (Martin Dustin)                                                                                                       | Поле принимает значение. Дополнительных надписей не появляется.                              |
|      |                                           | 6.Ввести имя и фамилию на китайском языке(爱民 李)                                                                                                               | Поле принимает значение. Дополнительных надписей не появляется.                              |
|      |                                           | 7.Ввести имя и фамилию на арабском языке(العبادي ‏‏عائدة‎)                                                                                                          | Поле принимает значение. Дополнительных надписей не появляется.                              |
| 4    | Отрицательные проверки поля “Номер карты” | 1.Ввести 15 цифр и нажать кнопку “Продолжить”                                                                                                                    | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 2.Ввести 1 цифру                                                                                                                                                 | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 3.Ввести 17 цифр                                                                                                                                                 | Поле не позволяет ввести более 16 цифр                                                       |
|      |                                           | 4.Ввести 4444 4444 4444 4442 и остальные поля заполнить валидными значениями                                                                                     | Высвечивается уведомление в правом верхнем углу “Ошибка! Банк отказал в проведении операции” |
|      |                                           | 5.Ввести буквы                                                                                                                                                   | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 6.Ввести спецсимволы                                                                                                                                             | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 7.Оставить поле пустым и нажать кнопку “Продолжить”                                                                                                              | Под полем появляется надпись “Поле обязательно для заполнения”                               |
| 5    | Отрицательные проверки поля “Месяц”       | 1.Ввести число 13                                                                                                                                                | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 2.Ввести цифру 0                                                                                                                                                 | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 3.Ввести одну цифру                                                                                                                                              | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 4.Ввести три цифры                                                                                                                                               | Поле не позволяет ввести более 2 цифр                                                        |
|      |                                           | 5.Ввести буквы                                                                                                                                                   | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 6.Ввести спецсимволы                                                                                                                                             | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 7.Оставить поле пустым и нажать кнопку “Продолжить”                                                                                                              | Под полем появляется надпись “Поле обязательно для заполнения”                               |
| 6    | Отрицательные проверки поля “Год”         | 1.Ввести год раньше нынешнего                                                                                                                                    | Под полем появляется надпись “Срок действия карты истек”                                     |
|      |                                           | 2.Ввести год на 6 лет позднее нынешнего                                                                                                                          | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 3.Ввести одну цифру                                                                                                                                              | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 4.Ввести цифру 0                                                                                                                                                 | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 5.Ввести три цифры                                                                                                                                               | Поле не позволяет ввести более 2 цифр                                                        |
|      |                                           | 6.Ввести буквы                                                                                                                                                   | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 7.Ввести спецсимволы                                                                                                                                             | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 8.Оставить поле пустым и нажать кнопку “Продолжить”                                                                                                              | Под полем появляется надпись “Поле обязательно для заполнения”                               |
| 7    | Отрицательные проверки поля “Владелец”    | 1.Ввести 51 букву                                                                                                                                                | Под полем появляется надпись “Поле не может содержать более 50 символов”                     |
|      |                                           | 2.Ввести цифры                                                                                                                                                   | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 3.Ввести спецсимволы, исключая дефис                                                                                                                             | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 4.Ввести 1 символ                                                                                                                                                | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 5.Ввести имя                                                                                                                                                     | Под полем появляется надпись “Укажите фамилию                                                |
|      |                                           | 6.Оставить поле пустым и нажать кнопку “Продолжить”                                                                                                              | Под полем появляется надпись “Поле обязательно для заполнения”                               |
| 8    | Отрицательные проверки поля “CVC/CVV”     | 1.Ввести 1 цифру                                                                                                                                                 | Под полем появляется надпись “Неверный формат”                                               |
|      |                                           | 2.Ввести 4 цифры                                                                                                                                                 | Поле не позволяет ввести более 3 цифр                                                        |
|      |                                           | 3.Ввести буквы                                                                                                                                                   | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 4.Ввести спецсимволы                                                                                                                                             | Поле не позволяет ввести символы, отличные от цифр                                           |
|      |                                           | 5.Оставить поле пустым и нажать кнопку “Продолжить”                                                                                                              | Под полем появляется надпись “Поле обязательно для заполнения”                               |

# Перечень используемых инструментов:
* IntelliJ IDEA : отличается обширным набором инструментов для рефакторинга (перепроектирования) и оптимизации кода. Доступна функция Run Targets, позволяющая запускать, тестировать и отлаживать приложения в Docker-контейнерах, на удаленных SSH-серверах и в WSL. Доступны инструменты для проведения автоматических тестов и формирования аналитики, которая показывает, какой объем кода протестирован, отладчик, показывающий значения переменных прямо в исходном коде, возможность выбрать метод отладки, встроенный декомпилятор — инструмент для преобразования исполняемого двоичного кода из jar-файлов в читаемый Java-код.
* Java : удобное написание тестов в IntelliJ IDEA. Много инструментов для автотестирования, множество фреймворков и библиотек, покрывающих самые популярные протоколы и сервисы.
* Junit 5 : фреймворк для Java, полностью совместимый с самим языком и его инструментами. Гибкие настройки. Позволяет параллельно запускать несколько тестов или объединять разные тестовые программы в набор. Есть встроенное правило, которое позволяет задать тайм-аут. Возможность создавать и запускать динамические тесты.
* Lombok : библиотека для сокращения кода в классах и расширения функциональности языка Java. Избавляет от ручного набора однотипных фрагментов кода и предотвращает появление ошибок. Экономит время. Улучшает читаемость кода.
* Selenide : предлагает лаконичный API для использования Selenium WebDriver в UI тестах: умные ожидания, автоматическое управление браузером, предлагает лаконичный и мощный API, автоматические скриншоты, удобные методы для заполнения полей, выбора чекбоксов, выпадающих списков, поиска элементов по тексту и т.д.
* Gradle : удобная и гибкая настройка, более производительный запуск тестов.
* Git и GitHub : получение информации о репозитории, сравнение файлов, переключение между различными ветками, откат изменений, настраивание удобной и информативной консоли, решение конфликтов кода, кроссплатформенность, эффективные code-review, реализование CI / CD.
* Appveyor : непрерывная интеграция, которая создает и тестирует код каждый раз, когда он отправляется в репозиторий Git, гарантируя, что ошибки будут быстро обнаружены. Может быть интегрирован с проектами GitHub и поддерживает частные проекты.
* Allure : построение понятной и прозрачной автоматизации с нуля: от написания теста до аналитики по результатам десятков прогонов.
* Docker : гарантирует, что приложение будет работать одинаково на разных устройствах и операционных системах. Отсутствие конфликтов между ОС и ПО.Платформа позволяет автоматизировать монотонные и повторяющиеся задачи. Поскольку в Docker происходит виртуализация на уровне операционной системы, при запуске экземпляров контейнеров время загрузки отсутствует. Таким образом, можно выполнить развёртывание за считанные секунды на виртуальном сервере. Контейнеры намного легче и меньше, чем виртуальные машины. Они занимают меньше памяти и не требуют больших физических серверов.


## Интервальная оценка с учётом рисков в часах: 
60 часов.
## План сдачи работ: первая версия всех тестов будет готова:
 до 10.07.2023 г.



# Перечень и описание возможных рисков при автоматизации:
1. Учитывая, что объем работы не является большим, настройка проекта и его автоматизации могут повлечь бОльшие временные и финансовые затраты в сравнении с ручным тестированием;
2. Отсутствие data-test id на странице может привести к увеличению сроков выполнения работы, ошибкам и, при наличии изменений в будущем, появлению недействительного кода;
3. Невозможность проверки действительности одобрения/неодобрения Банком покупки/кредита может повлечь пропущенные ошибки в данном взаимодействии.