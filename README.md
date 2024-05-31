# mail-service-project

Проект можно развернуть с помощью docker-compose.

OpenApi http://localhost:8080/swagger-ui/index.html.

Также для работы с api в проекте лежит файл /mail-service-project/answering-service/src/main/resources/answering-service.http,
где можно взять примеры запросов.

Smtp gmail server настроен, работает, рассылает письма группе пользователей, прикладывает файлы, если они есть.

При желании можно настроить свой smpt server (https://pabasararathnayake.medium.com/spring-boot-application-to-send-emails-using-smtp-protocol-c2616d7edf92) и в файле /mail-service-project/.env указать соответствующие переменные окружения, чтобы проверить. Данные переменные применяться к файлу docker-compose при поднятии приложения.
