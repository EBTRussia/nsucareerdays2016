# rest-app-example
## API
[get] "/"
- вернёт все значения в виде json
- пример: {
    "имяРесурса": false,
    "имяРесурса2": true
}

[delete] "/"
- удалит все ресурсы

[delete] "/{resource}"
- удалит ресурс

[get] "/{resource}"
- вернёт true || false
- если такого ресурса нет 404

[put] "/{resource}"
- запишет в ресурс значение из тела запроса value.equalsIgnoreCase("true")
- вернёт true || false  в зависимости от переданого значения
- если такого ресурса нет 404

[post] "/{resource}"
- создаст в ресурс со значением из тела запроса value.equalsIgnoreCase("true")
- вернёт true || false  в зависимости от переданого значения