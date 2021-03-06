# Запуск
Для запуска приложения необходимо:

- стянуть приложение из репозитория
- установить последнюю версию docker с оффициального сайта https://www.docker.com/get-started
- следовать инструкциям ниже и верить в магию виртуализации!

Пример стягивания и запуска:

- Откройте командную строку вашей ОС
- cmd cd /desktop/folder - перейдите в желаемую директорию для распаковки
- git clone https://github.com/rizzych/rostelecom.git - размещаем данную команду в нашу командную строку и стягиваем образ по ссылочке
- cd rostelecom - переходим в директорию с ресурсами и кодом проекта
- docker-compose up --build - устанавливаем за одну команду и пользуемся :)

# Как пользоваться? 

Для пользователя доступны следующие запросы:

# 1. [POST] ../api/create - для создания нового кластера и его нод. 

    Пример запроса в формате JSON: 

    {
        "description" :"clusterOne",
        "nodes":[
            {
            "name":"nodeOne"
            },
            {
            "name":"nodeTwo"
            },
            {
            "name":"nodeThree"
            }
        ]
    }

- Такой запрос создат 1 кластер, у которого есть 3 ноды.

# 2. [PUT] ../api/update - для обновления кластера и его нод. 
Для того что бы изменить какой-то кластер, нам необходимо знать его уникальный индефикатор(что бы узнать id кластера, я предусмотрел метод /clusters, но о нём чуть позже)
- Зная id кластера, нужно составить следующий http-request:

**../api/update?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)**
    
- В теле запроса также необходимо передать json. 

Пример запроса в формате JSON:

    {
        "description" :"changedClusterName",
        "node":[
            {
            "id":1,
            "name":"changedNodeName"}
        ]
    }

- Такой запрос обновит 1 кластер по id и его ноды.

# 3. [DELETE] ../api/delete - для удаления кластера и его нод. 
Для того что бы удалить какой-то кластер и его ноды, нам необходимо знать его уникальный индефикатор(что бы узнать id кластера, я предусмотрел метод /clusters, но о нём чуть позже)
- Зная id кластера, нужно составить следующий http-request:

**../api/dekete?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)**

В теле ничего передавать не нужно!

- Такой запрос удалит 1 кластер по id и его ноды.

# 4. [PUT] ../api/addNode - для добавления ноды к уже существующему кластеру. 
Для того что бы добавить ноду к определенному кластеру, нам необходимо знать его уникальный индефикатор(что бы узнать id кластера, я предусмотрел метод /clusters, но о нём чуть позже)
- Зная id кластера, нужно составить следующий http-request:
**../api/addNode?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)**

        В теле запроса также необходимо передать json.
        {
            "name":"addNode"
        }
    
На текущий момент, пользователь может добавлять кластеры без нод. Если в дальнейшем есть необходимость добавления нод, можно сделать это поштучно в текущем методе.
  
# 5. [GET] ../api/clusters - отдаст все существующие кластеры. 
достаточно сформировать обычный http запрос без передачи каких-либо параметров..
- Пример запроса: 

**../api/clusters**

# 6. [GET] ../api/nodes - отдаст все существующие ноды определенного кластера. 
достаточно сформировать обычный http запрос и передать уникальный индефикатор любого кластера.
- Пример запроса: 

**../api/nodes?id=(id кластера, который необходимо обновить. БЕЗ СКОБОК!!!)**
