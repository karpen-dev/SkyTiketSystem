## SkyUp ticket system
### Only russian locale
Систем заявок с нашего сервера SkyUp   
```json
{
    "name": "filin201077", 
    "age": 16,
    "description": "Я хочу играть на сервер потому что...",
    "status": "in"
}
```
Статусы: ```in``` в работе, ```ok``` заявка одобрена, ```cancel``` заявка не принята.  
Узнать текущие заявки:
```http request
http://localhost:8080/application/all
```