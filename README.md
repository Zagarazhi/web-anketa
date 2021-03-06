# Описание
Разработать web приложение, которое должно позволить пользователю с ролью ADMIN (пользователь с данной ролью создается заранее) конструировать анкету проверки знаний. Анкета может содержать любое количество вопросов. Вопрос может содержать от двух и более вариантов ответа (пример вопросов и ответов дан ниже). В зависимости от вида вопроса среди ответов, один обязательно должен быть “правильным”. За каждый верный ответ - начисляется балл. За ошибочный - балл снимается. Необходимо вести общую оценку пользователя (сумма баллов). Количество попыток повторного прохождения анкеты не более 1. Предыдущие ответы перетираются.
Приложение должно иметь возможность регистрации пользователя по email. Пользователь, зарегистрировавшись в приложении, получает роль USER и может выбирать любую доступную анкету, дать ответы на вопросы (в количестве попыток указанных выше), посмотреть общий балл и свое место в рейтинге. Администратор, в свою очередь, может:
- просматривать данные пользователем ответы
- просматривать рейтинг пользователей (какой рейтинг придумайте сами)
# Реализовано:
- создание тестов, ответ на них, просмотр рейтинга пользователей, страницы для промотра результатов пользователей для админов

# Доступ:
- Сайт: http://localhost:8080/
- БД(для админа): http://localhost:8080/h2-console
- Создание тестов(для админа): http://localhost:8080/admin/create
- Просмотр пользователей (для админа): http://localhost:8080/admin/users
- Просмотр пользователей: http://localhost:8080/users
- Просмотр тестов: http://localhost:8080/tests

# Созданные пользователи:
- Логин: admin, почта: admin@mail.ru, пароль: admin (Админ)
- Логин: test, почта: test@mail.ru, пароль: test (Пользовтель)
- Логин: temp, почта: temp@mail.ru, пароль: temp (Пользовтель)

# Примечание:
- В [application.properties](/src/main/resources/application.properties) необходимо прописать настройки исходящего почтвого сервиса, чтобы включить возможность подверждения по почте пользовтелей

# Используемые технологии:
1)
 - Java 17
 - Spring Boot 2.7.0
 - Javax Validation 2.0.1.Final
 - Hibernate Validator 7.0.4.Final
 - Jackson 2.13.3
2) БД H2
3) Обмен между фронтом и бэком ведется через JSON формат. 
4) Фронт: HTML + JS + bootstrap.