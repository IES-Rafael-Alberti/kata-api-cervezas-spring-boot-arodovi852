# 🍺 Beer API - Kata Spring Boot

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)

API REST completa para la gestión de cervezas, cerveceras, categorías y estilos, desarrollada con Spring Boot.

## 📋 Descripción

Este proyecto implementa una API REST funcional con operaciones CRUD sobre múltiples tablas relacionadas con el mundo de las cervezas artesanales.

## ✨ Características

- ✅ **CRUD completo** para cervezas (Create, Read, Update, Delete)
- ✅ **Operaciones de lectura** para cerveceras, categorías y estilos
- ✅ **Validación de datos** con Bean Validation
- ✅ **Manejo global de excepciones**
- ✅ **Arquitectura en capas** (Controller, Service, Repository)
- ✅ **JPA/Hibernate** para persistencia
- ✅ **Documentación completa** de la API

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **MySQL 8.0**
- **Lombok**
- **Maven**
- **Java 17**

## 📁 Estructura del Proyecto

```
src/main/java/com/kata/beerapi/
├── BeerApiApplication.java          # Clase principal
├── controller/                       # Controladores REST
│   ├── BeerController.java
│   ├── BreweryController.java
│   ├── CategoryController.java
│   └── StyleController.java
├── service/                          # Lógica de negocio
│   ├── BeerService.java
│   ├── BreweryService.java
│   ├── CategoryService.java
│   └── StyleService.java
├── repository/                       # Repositorios JPA
│   ├── BeerRepository.java
│   ├── BreweryRepository.java
│   ├── CategoryRepository.java
│   └── StyleRepository.java
├── model/                            # Entidades JPA
│   ├── Beer.java
│   ├── Brewery.java
│   ├── Category.java
│   └── Style.java
└── exception/                        # Manejo de excepciones
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- Git

### Paso 1: Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd kata-api-cervezas-spring-boot-arodovi852
```

### Paso 2: Configurar la base de datos

1. Inicia MySQL y ejecuta los scripts de la carpeta `initSQL/`:

```bash
mysql -u root -p < initSQL/01-create-db.sql
mysql -u root -p kata-api < initSQL/categories.sql
mysql -u root -p kata-api < initSQL/styles.sql
mysql -u root -p kata-api < initSQL/breweries.sql
mysql -u root -p kata-api < initSQL/beers.sql
```

2. Actualiza las credenciales en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kata-api
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### Paso 3: Compilar el proyecto

```bash
mvn clean install
```

### Paso 4: Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080/api`

## 📚 Documentación de la API

La documentación completa con ejemplos de todas las peticiones está disponible en:

👉 **[API_DOCUMENTATION.md](API_DOCUMENTATION.md)**

### Resumen de Endpoints

| Endpoint           | Método | Descripción                       |
|--------------------|--------|-----------------------------------|
| `/api/beers`       | GET    | Listar todas las cervezas         |
| `/api/beer`        | POST   | Crear una cerveza                 |
| `/api/beer/{id}`   | GET    | Obtener una cerveza por ID        |
| `/api/beer/{id}`   | PUT    | Actualizar completamente          |
| `/api/beer/{id}`   | PATCH  | Actualizar parcialmente           |
| `/api/beer/{id}`   | DELETE | Eliminar una cerveza              |
| `/api/breweries`   | GET    | Listar todas las cerveceras       |
| `/api/brewerie/{id}` | GET  | Obtener una cervecera por ID      |
| `/api/categories`  | GET    | Listar todas las categorías       |
| `/api/categorie/{id}` | GET | Obtener una categoría por ID      |
| `/api/styles`      | GET    | Listar todos los estilos          |
| `/api/style/{id}`  | GET    | Obtener un estilo por ID          |

## 🧪 Probar la API

### Con cURL

```bash
# Obtener todas las cervezas
curl -X GET http://localhost:8080/api/beers

# Crear una cerveza
curl -X POST http://localhost:8080/api/beer \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "IPA Artesanal",
    "catId": 1,
    "styleId": 12,
    "abv": 6.5,
    "ibu": 65.0,
    "srm": 12.0,
    "upc": 123456,
    "descript": "Una IPA con notas cítricas",
    "addUser": 1
  }'
```

### Con HTTPie

```bash
# Obtener todas las cervezas
http GET http://localhost:8080/api/beers

# Crear una cerveza
http POST http://localhost:8080/api/beer \
  breweryId:=1 \
  name="IPA Artesanal" \
  catId:=1 \
  styleId:=12 \
  abv:=6.5 \
  ibu:=65.0 \
  srm:=12.0 \
  upc:=123456 \
  descript="Una IPA con notas cítricas" \
  addUser:=1
```

### Con Postman o Insomnia

Importa la colección desde [API_DOCUMENTATION.md](API_DOCUMENTATION.md) y configura:
- Base URL: `http://localhost:8080/api`
- Headers: `Content-Type: application/json`

## 📊 Modelo de Datos

### Beer (Cerveza)
- `id`: Identificador único
- `breweryId`: ID de la cervecera
- `name`: Nombre de la cerveza
- `catId`: ID de la categoría
- `styleId`: ID del estilo
- `abv`: Alcohol by volume
- `ibu`: International Bitterness Units
- `srm`: Standard Reference Method (color)
- `upc`: Código de producto
- `filepath`: Ruta de imagen
- `descript`: Descripción
- `addUser`: Usuario que añadió
- `lastMod`: Última modificación

### Brewery (Cervecera)
- Información de cerveceras: nombre, dirección, contacto, etc.

### Category (Categoría)
- Categorías de cervezas: Ale, Lager, etc.

### Style (Estilo)
- Estilos específicos: IPA, Stout, Pilsner, etc.

## ✅ Checklist de Objetivos Completados

- [x] Fork del repositorio Git
- [x] Crear una nueva rama
- [x] Comprender peticiones HTTP (GET, POST, PUT, PATCH, DELETE)
- [x] Implementar CRUD completo en `/beer*`
  - [x] Create (POST)
  - [x] Read (GET)
  - [x] Update (PUT/PATCH)
  - [x] Delete (DELETE)
- [x] Implementar lectura en `/brewerie*`, `/categorie*`, `/style*`
- [x] Documentación completa de la API con ejemplos
- [x] Arquitectura en capas con Spring Boot
- [x] Validación de datos
- [x] Manejo de excepciones

## 🔧 Desarrollo

### Compilar sin ejecutar tests

```bash
mvn clean package -DskipTests
```

### Ejecutar en modo desarrollo

```bash
mvn spring-boot:run
```

### Ver logs

Los logs se muestran en la consola. Para SQL queries, verifica que en `application.properties` esté:

```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## 🐛 Solución de Problemas

### Error de conexión a MySQL

Verifica que MySQL esté ejecutándose:
```bash
# En Windows
net start MySQL80

# En Linux/Mac
sudo service mysql start
```

### La base de datos no existe

Ejecuta el script de creación:
```bash
mysql -u root -p < initSQL/01-create-db.sql
```

### Puerto 8080 ocupado

Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

## 📝 Notas

- La API utiliza JSON para todas las peticiones y respuestas
- Los timestamps (`lastMod`) se actualizan automáticamente
- Todas las validaciones se manejan con Bean Validation
- Los errores devuelven respuestas JSON estructuradas

## 👥 Autor

- Alberto Rodríguez - [@arodovi852](https://github.com/arodovi852)

## 📄 Licencia

Este proyecto está bajo la licencia especificada en el archivo [LICENSE](LICENSE).

---

**Kata desarrollada para el módulo de Desarrollo Web en Entornos Servidor** 🎓
