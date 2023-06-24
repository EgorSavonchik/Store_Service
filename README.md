# Store Service
Тестовое задание для Digital Chief.
# Предметная область
<p align="justify">
Предметную область составляют классы: магазин, работник, продукт, имеющие свойства: магазин(название, адресс, наличие доставки, контактный телефон, владелец), работник магазина(место работы, имя, фамилия, возраст, зарплата), продукт магазина(магазин(в котором продается данный продукт), название, описание, цена, производитель). Сущности состоят в композиции, "один ко многим": магазин - работники, работюащие в этом магазине; магазин - продукты, продающиеся в этом магазине.
</p>

# Функциональные возможности
Программа реализует API для выполнения CRUD(создание, прочтение, изменение, удаление) над описанными выше сущностями.
# Зависимости
* Java 17
* Spring Boot
* Spring Data JPA
* Spring Validation
* Docker
* Liquibase
* PostgreSql
* Spring Data Rest
* Lombok
* Model Mapper
# Запуск приложения
* Склонировать гит репозиторий(git clone https://github.com/EgorSavonchik/Store_Service/).
* Запустить Docker Desktop(если запуск выполняется на Windows).
* Запустить PostgreSQL на компьютере.
* Ввести свои данные для входа от PostgreSQl(имя пользователя и пароль в соответствующие поля: POSTGRES_USER, POSTGRES_PASSWORD) в [compose.yaml](https://github.com/EgorSavonchik/Store_Service/blob/main/test-project/compose.yaml).
* Перейти в папку Store_Service\test-project(cd Store_Service, потом cd test-project).
* Выполнить команду mvn compile.
* Выполнить команду mvn exec:java -Dexec.mainClass="com.test.testproject.TestProjectApplication".
# Postman запросы
Импортировать [коллекцию запросов](https://github.com/EgorSavonchik/Store_Service/blob/main/test-project/Digital%20Chief%20project.postman_collection.json) в postman.
