# Documentación de la API de Cervezas

## Índice
- [Descripción General](#descripción-general)
- [Configuración](#configuración)
- [Endpoints de Cervezas (CRUD Completo)](#endpoints-de-cervezas-crud-completo)
- [Endpoints de Cerveceras (Solo Lectura)](#endpoints-de-cerveceras-solo-lectura)
- [Endpoints de Categorías (Solo Lectura)](#endpoints-de-categorías-solo-lectura)
- [Endpoints de Estilos (Solo Lectura)](#endpoints-de-estilos-solo-lectura)
- [Manejo de Errores](#manejo-de-errores)

## Descripción General

API REST para la gestión de cervezas, cerveceras, categorías y estilos. Desarrollada con Spring Boot 3.2.0 y Java 17.

**Base URL**: `http://localhost:8080/api`

## Configuración

### Prerrequisitos
- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+

### Configuración de Base de Datos
Actualiza el archivo `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kata-api
spring.datasource.username=root
spring.datasource.password=root
```

### Ejecución
```bash
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

---

## Endpoints de Cervezas (CRUD Completo)

### 1. Obtener todas las cervezas
**GET** `/api/beers`

**Respuesta exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "breweryId": 1,
    "name": "IPA Artesanal",
    "catId": 1,
    "styleId": 12,
    "abv": 6.5,
    "ibu": 65.0,
    "srm": 12.0,
    "upc": 123456,
    "filepath": "",
    "descript": "Una IPA con notas cítricas",
    "addUser": 1,
    "lastMod": "2025-12-15T10:30:00"
  }
]
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/beers
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/beers
```

---

### 2. Obtener una cerveza por ID
**GET** `/api/beer/{id}`

**Parámetros:**
- `id` (path): ID de la cerveza

**Respuesta exitosa (200 OK):**
```json
{
  "id": 1,
  "breweryId": 1,
  "name": "IPA Artesanal",
  "catId": 1,
  "styleId": 12,
  "abv": 6.5,
  "ibu": 65.0,
  "srm": 12.0,
  "upc": 123456,
  "filepath": "",
  "descript": "Una IPA con notas cítricas",
  "addUser": 1,
  "lastMod": "2025-12-15T10:30:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/beer/1
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/beer/1
```

---

### 3. Crear una nueva cerveza
**POST** `/api/beer`

**Headers:**
- `Content-Type: application/json`

**Body:**
```json
{
  "breweryId": 1,
  "name": "Stout Imperial",
  "catId": 2,
  "styleId": 15,
  "abv": 9.0,
  "ibu": 45.0,
  "srm": 40.0,
  "upc": 789012,
  "filepath": "",
  "descript": "Cerveza oscura con notas de chocolate y café",
  "addUser": 1
}
```

**Respuesta exitosa (201 Created):**
```json
{
  "id": 2,
  "breweryId": 1,
  "name": "Stout Imperial",
  "catId": 2,
  "styleId": 15,
  "abv": 9.0,
  "ibu": 45.0,
  "srm": 40.0,
  "upc": 789012,
  "filepath": "",
  "descript": "Cerveza oscura con notas de chocolate y café",
  "addUser": 1,
  "lastMod": "2025-12-15T10:35:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X POST http://localhost:8080/api/beer \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "Stout Imperial",
    "catId": 2,
    "styleId": 15,
    "abv": 9.0,
    "ibu": 45.0,
    "srm": 40.0,
    "upc": 789012,
    "descript": "Cerveza oscura con notas de chocolate y café",
    "addUser": 1
  }'
```

**Ejemplo con HTTPie:**
```bash
http POST http://localhost:8080/api/beer \
  breweryId:=1 \
  name="Stout Imperial" \
  catId:=2 \
  styleId:=15 \
  abv:=9.0 \
  ibu:=45.0 \
  srm:=40.0 \
  upc:=789012 \
  descript="Cerveza oscura con notas de chocolate y café" \
  addUser:=1
```

---

### 4. Actualizar completamente una cerveza
**PUT** `/api/beer/{id}`

**Parámetros:**
- `id` (path): ID de la cerveza

**Headers:**
- `Content-Type: application/json`

**Body:** (Todos los campos son requeridos)
```json
{
  "breweryId": 1,
  "name": "IPA Artesanal Modificada",
  "catId": 1,
  "styleId": 12,
  "abv": 7.0,
  "ibu": 70.0,
  "srm": 15.0,
  "upc": 123456,
  "filepath": "/images/ipa.jpg",
  "descript": "Una IPA mejorada con más lúpulo",
  "addUser": 1
}
```

**Respuesta exitosa (200 OK):**
```json
{
  "id": 1,
  "breweryId": 1,
  "name": "IPA Artesanal Modificada",
  "catId": 1,
  "styleId": 12,
  "abv": 7.0,
  "ibu": 70.0,
  "srm": 15.0,
  "upc": 123456,
  "filepath": "/images/ipa.jpg",
  "descript": "Una IPA mejorada con más lúpulo",
  "addUser": 1,
  "lastMod": "2025-12-15T10:40:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X PUT http://localhost:8080/api/beer/1 \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "IPA Artesanal Modificada",
    "catId": 1,
    "styleId": 12,
    "abv": 7.0,
    "ibu": 70.0,
    "srm": 15.0,
    "upc": 123456,
    "filepath": "/images/ipa.jpg",
    "descript": "Una IPA mejorada con más lúpulo",
    "addUser": 1
  }'
```

**Ejemplo con HTTPie:**
```bash
http PUT http://localhost:8080/api/beer/1 \
  breweryId:=1 \
  name="IPA Artesanal Modificada" \
  catId:=1 \
  styleId:=12 \
  abv:=7.0 \
  ibu:=70.0 \
  srm:=15.0 \
  upc:=123456 \
  filepath="/images/ipa.jpg" \
  descript="Una IPA mejorada con más lúpulo" \
  addUser:=1
```

---

### 5. Actualizar parcialmente una cerveza
**PATCH** `/api/beer/{id}`

**Parámetros:**
- `id` (path): ID de la cerveza

**Headers:**
- `Content-Type: application/json`

**Body:** (Solo los campos que deseas actualizar)
```json
{
  "name": "IPA Artesanal Premium",
  "abv": 7.5
}
```

**Respuesta exitosa (200 OK):**
```json
{
  "id": 1,
  "breweryId": 1,
  "name": "IPA Artesanal Premium",
  "catId": 1,
  "styleId": 12,
  "abv": 7.5,
  "ibu": 70.0,
  "srm": 15.0,
  "upc": 123456,
  "filepath": "/images/ipa.jpg",
  "descript": "Una IPA mejorada con más lúpulo",
  "addUser": 1,
  "lastMod": "2025-12-15T10:45:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X PATCH http://localhost:8080/api/beer/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "IPA Artesanal Premium",
    "abv": 7.5
  }'
```

**Ejemplo con HTTPie:**
```bash
http PATCH http://localhost:8080/api/beer/1 \
  name="IPA Artesanal Premium" \
  abv:=7.5
```

---

### 6. Eliminar una cerveza
**DELETE** `/api/beer/{id}`

**Parámetros:**
- `id` (path): ID de la cerveza

**Respuesta exitosa (204 No Content):**
Sin contenido en el cuerpo de la respuesta.

**Ejemplo con cURL:**
```bash
curl -X DELETE http://localhost:8080/api/beer/1
```

**Ejemplo con HTTPie:**
```bash
http DELETE http://localhost:8080/api/beer/1
```

---

## Endpoints de Cerveceras (Solo Lectura)

### 1. Obtener todas las cerveceras
**GET** `/api/breweries`

**Respuesta exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Cervecera Artesanal del Norte",
    "address1": "Calle Principal 123",
    "address2": "Local 5",
    "city": "Madrid",
    "state": "Madrid",
    "code": "28001",
    "country": "España",
    "phone": "+34 912 345 678",
    "website": "https://cervecerianorte.com",
    "filepath": "",
    "descript": "Cervecera artesanal especializada en IPAs",
    "addUser": 1,
    "lastMod": "2025-12-15T09:00:00"
  }
]
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/breweries
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/breweries
```

---

### 2. Obtener una cervecera por ID
**GET** `/api/brewerie/{id}`

**Parámetros:**
- `id` (path): ID de la cervecera

**Respuesta exitosa (200 OK):**
```json
{
  "id": 1,
  "name": "Cervecera Artesanal del Norte",
  "address1": "Calle Principal 123",
  "address2": "Local 5",
  "city": "Madrid",
  "state": "Madrid",
  "code": "28001",
  "country": "España",
  "phone": "+34 912 345 678",
  "website": "https://cervecerianorte.com",
  "filepath": "",
  "descript": "Cervecera artesanal especializada en IPAs",
  "addUser": 1,
  "lastMod": "2025-12-15T09:00:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/brewerie/1
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/brewerie/1
```

---

## Endpoints de Categorías (Solo Lectura)

### 1. Obtener todas las categorías
**GET** `/api/categories`

**Respuesta exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "catName": "Ale",
    "lastMod": "2025-12-15T08:00:00"
  },
  {
    "id": 2,
    "catName": "Lager",
    "lastMod": "2025-12-15T08:00:00"
  }
]
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/categories
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/categories
```

---

### 2. Obtener una categoría por ID
**GET** `/api/categorie/{id}`

**Parámetros:**
- `id` (path): ID de la categoría

**Respuesta exitosa (200 OK):**
```json
{
  "id": 1,
  "catName": "Ale",
  "lastMod": "2025-12-15T08:00:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/categorie/1
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/categorie/1
```

---

## Endpoints de Estilos (Solo Lectura)

### 1. Obtener todos los estilos
**GET** `/api/styles`

**Respuesta exitosa (200 OK):**
```json
[
  {
    "id": 1,
    "catId": 1,
    "styleName": "American IPA",
    "lastMod": "2025-12-15T08:00:00"
  },
  {
    "id": 2,
    "catId": 1,
    "styleName": "English Pale Ale",
    "lastMod": "2025-12-15T08:00:00"
  }
]
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/styles
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/styles
```

---

### 2. Obtener un estilo por ID
**GET** `/api/style/{id}`

**Parámetros:**
- `id` (path): ID del estilo

**Respuesta exitosa (200 OK):**
```json
{
  "id": 1,
  "catId": 1,
  "styleName": "American IPA",
  "lastMod": "2025-12-15T08:00:00"
}
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/api/style/1
```

**Ejemplo con HTTPie:**
```bash
http GET http://localhost:8080/api/style/1
```

---

## Manejo de Errores

### Recurso no encontrado (404)
```json
{
  "timestamp": "2025-12-15T10:50:00",
  "status": 404,
  "error": "Not Found",
  "message": "Cerveza no encontrada con id: 999"
}
```

### Error de validación (400)
```json
{
  "timestamp": "2025-12-15T10:51:00",
  "status": 400,
  "error": "Bad Request",
  "errors": {
    "name": "El nombre de la cerveza es obligatorio",
    "abv": "El valor debe ser positivo"
  }
}
```

### Error interno del servidor (500)
```json
{
  "timestamp": "2025-12-15T10:52:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Error al procesar la solicitud"
}
```

---

## Pruebas con Postman

### Importar colección
Puedes crear una colección de Postman con todos estos endpoints:

1. Crea una nueva colección llamada "Beer API"
2. Configura la variable de entorno `baseUrl = http://localhost:8080/api`
3. Añade cada endpoint como una nueva petición

### Variables de entorno sugeridas
```
baseUrl: http://localhost:8080/api
beerId: 1
breweryId: 1
categoryId: 1
styleId: 1
```

---

## Notas Adicionales

### Campos obligatorios en Beer
- `name`: Nombre de la cerveza
- `breweryId`: ID de la cervecera
- `catId`: ID de la categoría
- `styleId`: ID del estilo
- `abv`: Grado alcohólico
- `ibu`: Amargor
- `srm`: Color
- `upc`: Código de producto

### Campos opcionales en Beer
- `filepath`: Ruta de la imagen
- `descript`: Descripción
- `addUser`: Usuario que añadió el registro

### Timestamps
Todos los campos `lastMod` se actualizan automáticamente al crear o modificar un registro.

---

## Resumen de Endpoints

| Endpoint           | Método | Descripción                       | Funcionalidad |
|--------------------|--------|-----------------------------------|---------------|
| `/api/beers`       | GET    | Obtener todas las cervezas        | READ          |
| `/api/beer`        | POST   | Crear una cerveza                 | CREATE        |
| `/api/beer/{id}`   | GET    | Obtener una cerveza               | READ          |
| `/api/beer/{id}`   | PUT    | Actualizar completamente          | UPDATE        |
| `/api/beer/{id}`   | PATCH  | Actualizar parcialmente           | UPDATE        |
| `/api/beer/{id}`   | DELETE | Eliminar una cerveza              | DELETE        |
| `/api/breweries`   | GET    | Obtener todas las cerveceras      | READ          |
| `/api/brewerie/{id}` | GET  | Obtener una cervecera             | READ          |
| `/api/categories`  | GET    | Obtener todas las categorías      | READ          |
| `/api/categorie/{id}` | GET | Obtener una categoría             | READ          |
| `/api/styles`      | GET    | Obtener todos los estilos         | READ          |
| `/api/style/{id}`  | GET    | Obtener un estilo                 | READ          |
