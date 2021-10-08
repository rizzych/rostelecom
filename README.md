# rostelecom
Для запуска приложения необходимо собрать jar файл. Для этого необходимо стянуть проект, выполнить maven cmd: mvn clean install
В следствии перейти в директорию с проектом : ..\rostelecom\target и забрать оттуда jar файл(demo-0.0.1-SNAPSHOT).
Также необходимо скопировать файл application.properties из директории: ..\rostelecom\src\main\resources и поместить рядом с jar файлом.

# Запуск

1. Скопированные файлы поместить в одну директорию,
2. Поднять базу(postgres) локально или подключится к удаленной. Настройки подключения к базе указаны в файле application.properties, можно их изменять.
3. Запустить командную строку из директории и вызвать команду: java -jar demo-0.0.1-SNAPSHOT.jar --spring.config.location=application.properties

# Как пользоваться? 

Для пользователя доступны следующие запросы:

# 1. [POST] ../api/create - для создания нового кластера и его нод. 

Пример запроса в формате JSON: 
{
    "description" :"Hello",
    "node":[
        {
        "name":"world"
        },
        {
        "name":"hello"
        },
        {
        "name":"yoop"
        }
    ]
}

- Такой запрос создат 1 кластер, у которого есть 3 ноды.

# 2. [PUT] ../api/update - для обновления кластера и его нод. 
Для того что бы изменить какой-то кластер, нам необходимо знать его уникальный индефикатор(что бы узнать id кластера, я предусмотрел метод /clusters, но о нём чуть позже)
Зная id кластера, нужно составить следующий http-request: ../api/update?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)
В теле запроса также необходимо передать json. 

Пример запроса в формате JSON: 
{
    "description" :"Hello",
    "node":[
        {
        "id":1,
        "name":"bye"
        }
    ]
}

- Такой запрос обновит 1 кластер по id и его ноды.

# 3. [DELETE] ../api/delete - для удаления кластера и его нод. 
Для того что бы удалить какой-то кластер и его ноды, нам необходимо знать его уникальный индефикатор(что бы узнать id кластера, я предусмотрел метод /clusters, но о нём чуть позже)
Зная id кластера, нужно составить следующий http-request: ../api/dekete?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)
В теле ничего передавать не нужно!

- Такой запрос удалит 1 кластер по id и его ноды.

# 4. [PUT] ../api/addNodes - для добавления нод к уже существующему кластеру. 
//TODO serialize string (json parse) or put here ClusterDto 
в процессе доработки, временно не работает
  
# 5. [GET] ../api/clusters - отдаст все существующие кластеры. 
достаточно сформировать обычный http запрос без передачи каких-либо параметров..
Пример запроса: ../api/clusters

# 6. [GET] ../api/nodes - отдаст все существующие ноды определенного кластера. 
достаточно сформировать обычный http запрос и передать уникальный индефикатор любого кластера.
Пример запроса: ../api/nodes?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)
