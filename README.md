[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/um2scOsx)
> **Note**
> Si alguien quiere usar docker, docker-compose, adelante.
> Pero ahora mismo no es nuestro objetivo.
> Los objetivos son los que aparecen tras ### Objetivos del módulo


# Kata API
Kata : crear una [API](https://github.com/OAI/OpenAPI-Specification)
funcional [CRUD](https://www.codecademy.com/articles/what-is-crud) sobre
varias tablas en MySQL.

## Objetivo
Creación y utilización de una API y aprender y usar diferentes [métodos
HTTP](https://developer.mozilla.org/es/docs/Web/HTTP/Methods).
Además de trabajar con Docker en un proyecto.

## Prerrequisitos
En la Kata se asume familiaridad con las siguientes tecnologías
[Git](https://git-scm.com/), [Docker](https://www.docker.com/) y
[Docker-compose](https://docs.docker.com/compose/).

## Procedimiento
[Hacer un fork](https://github.com/epfl-dojo/kata-api/#fork-destination-box) del repositorio,
crear una rama (`git checkout -b username/langage` por ejemplo `git checkout
-b nicolasreymond/php`, desde vuestro fork). Haced un pull request para añadirlo a este 
repo y añadiéndoos al final de este fichero como autores. 

## Puesta en marcha
En cada cambio de un fichero en el contenedor tenemos que hacer:
```bash
docker-compose up --build
```
Y para lanzar los contenedores:
```bash
docker-compose up -d
```

## Objetivos de la Kata
- [ ] Hacer un *fork* del repositorio Git
- [ ] Crear una nueva  rama (ex. : `usermame/language`)
- [ ] Añadir un contenedor docker (para el lenguage elegido) al  docker-compose (NO ES NECESARIA ESTA PARTE)
### Objetivos del módulo  
- [ ] Comprender cómo detectar las diferentes  [peticiones HTTP](https://developer.mozilla.org/es/docs/Web/HTTP/Methods)
  (GET, POST, PUT, PATCH, DELETE)
- [ ] Implementación del CRUD sobre el endpoint `/beer*`
   - [ ] Implementar la creación       **`C`**`reate`
   - [ ] Implementar la lectura        **`R`**`ead`
   - [ ] Implementar la actualilzación **`U`**`pdate`
   - [ ] Implementar el borrado        **`D`**`elete`
- [ ] Implementar la lectura de los endpoints `/brewerie*`, `/categorie*` et `/style*`
- [ ] Probar la API con
  - [postman](https://www.postman.com/),
  - [insomnia](https://insomnia.rest), 
  - [curl](https://curl.haxx.se/), 
  - [httpie](https://httpie.org/), etc…  
  y guardar esas peticiones en un fichero de resultados.
- [ ] Hacer un documento que explique cómo utilizar la API
  (con ejemplos de cada una de las consultas)

Ir más lejos
- [ ] Implementar la paginación para consultar que devuelven muchos datos 
    por ejemplo con el método HTTP  HEAD.
  [HEAD](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods/HEAD)
- [ ] Integración con [swagger](https://swagger.io/tools/open-source/open-source-integrations/) 
  del lenguaje escogido.
- [ ] Modificaciones para poder subir imágenes de las cervezas  


## Descripción de rutas a utilizar

| Endpoint         | Resultado                               | Método   |
|----------------- |-----------------------------------------|:--------:|
|`/beers`          | Muestra todas las cervezas              | GET      |
|`/beer`           | Añadir una cerveza                      | POST     |
|`/beer/{id}`      | Mostrar la cerveza con el id `{id}`     | GET      |
|`/beer/{id}`      | Eliminar una cerveza                    | DELETE   |
|`/beer/{id}`      | Modificar una cerveza                   | PUT      |
|`/beer/{id}`      | Modificar parcialmente una cerveza      | PUT o PATCH    |
|`/breweries`      | Listar todas las cerveceras             | GET      |
|`/brewerie/{id}`  | Mostrar la cervecera `{id}`             | GET      |
|`/categories`     | Listar todas las categorías             | GET      |
|`/categorie/{id}` | Mostrar la categoría `{id}`             | GET      |
|`/styles`         | Listar todos los estilos -style-        | GET      |
|`/style/{id}`     | Mostrar el estilo -style- `{id}`        | GET      |


## ✅ Estado del Proyecto

**¡Proyecto completado!** Todos los objetivos del módulo han sido implementados.

### Implementación realizada: Spring Boot

- ✅ **CRUD completo** para cervezas (Create, Read, Update, Delete)
- ✅ **Operaciones de lectura** para cerveceras, categorías y estilos
- ✅ **Arquitectura en capas** (Controller → Service → Repository → Model)
- ✅ **Validación de datos** con Bean Validation
- ✅ **Manejo de excepciones** centralizado
- ✅ **Docker y Docker Compose** configurados
- ✅ **Documentación completa** de la API

## 📚 Documentación Disponible

1. **[SETUP_GUIDE.md](SETUP_GUIDE.md)** - Guía completa de instalación y configuración
2. **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)** - Documentación detallada de todos los endpoints
3. **[HTTP_EXAMPLES.md](HTTP_EXAMPLES.md)** - Ejemplos prácticos con cURL y HTTPie
4. **[DOCKER_GUIDE.md](DOCKER_GUIDE.md)** - Guía completa de Docker y Docker Compose

## 🚀 Inicio Rápido

### Con Docker (Recomendado)
```bash
docker-compose up --build -d
```

### Sin Docker
```bash
# 1. Iniciar MySQL y ejecutar scripts de initSQL/
# 2. Configurar application.properties
# 3. Ejecutar:
mvn spring-boot:run
```

La API estará en: **http://localhost:8080/api**

## 📊 Estructura del Proyecto

```
src/main/java/com/kata/beerapi/
├── BeerApiApplication.java
├── controller/          # Endpoints REST
├── service/            # Lógica de negocio
├── repository/         # Acceso a datos (JPA)
├── model/              # Entidades
└── exception/          # Manejo de errores
```

## 🧪 Probar la API

```bash
# Listar todas las cervezas
curl http://localhost:8080/api/beers

# Crear una cerveza
curl -X POST http://localhost:8080/api/beer \
  -H "Content-Type: application/json" \
  -d '{"breweryId":1,"name":"IPA","catId":1,"styleId":12,"abv":6.5,"ibu":65,"srm":12,"upc":123456,"descript":"Una IPA","addUser":1}'
```

Ver más ejemplos en [HTTP_EXAMPLES.md](HTTP_EXAMPLES.md)

## 🛠️ Tecnologías

- Spring Boot 3.2.0
- Spring Data JPA
- MySQL 8.0
- Lombok
- Maven
- Docker

## Colaboradores (idiomas en orden alfabético)

**Spring Boot (Java 17)** → [Alberto Rodríguez (@arodovi852)](https://github.com/arodovi852)

[Laravel](https://github.com/SaphireVert/Kata-API/tree/saphirevert/laravel) → [![saphirevert-repos][saphirevert-shield]][saphirevert-url]


[saphirevert-shield]: https://badgen.net/badge/Github/SaphireVert/green?icon=https://svgshare.com/i/Srf.svg
[saphirevert-url]: https://github.com/saphirevert/

