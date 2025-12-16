# Ejemplos de Peticiones HTTP para Beer API

Este archivo contiene ejemplos prácticos de todas las peticiones disponibles en la API.

## 🔧 Variables de Entorno

```bash
export BASE_URL="http://localhost:8080/api"
```

---

## 🍺 CERVEZAS (CRUD Completo)

### 1. Listar todas las cervezas
```bash
# cURL
curl -X GET ${BASE_URL}/beers

# HTTPie
http GET ${BASE_URL}/beers
```

### 2. Obtener cerveza por ID
```bash
# cURL
curl -X GET ${BASE_URL}/beer/1

# HTTPie
http GET ${BASE_URL}/beer/1
```

### 3. Crear nueva cerveza
```bash
# cURL
curl -X POST ${BASE_URL}/beer \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "Pale Ale Casera",
    "catId": 1,
    "styleId": 10,
    "abv": 5.5,
    "ibu": 40.0,
    "srm": 8.0,
    "upc": 555666,
    "filepath": "",
    "descript": "Cerveza pale ale de elaboración casera con balance perfecto",
    "addUser": 1
  }'

# HTTPie
http POST ${BASE_URL}/beer \
  breweryId:=1 \
  name="Pale Ale Casera" \
  catId:=1 \
  styleId:=10 \
  abv:=5.5 \
  ibu:=40.0 \
  srm:=8.0 \
  upc:=555666 \
  filepath="" \
  descript="Cerveza pale ale de elaboración casera con balance perfecto" \
  addUser:=1
```

### 4. Actualizar cerveza completamente (PUT)
```bash
# cURL
curl -X PUT ${BASE_URL}/beer/1 \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "IPA Premium Actualizada",
    "catId": 1,
    "styleId": 12,
    "abv": 7.2,
    "ibu": 75.0,
    "srm": 14.0,
    "upc": 123789,
    "filepath": "/images/ipa-premium.jpg",
    "descript": "IPA premium con notas cítricas intensas y amargor pronunciado",
    "addUser": 1
  }'

# HTTPie
http PUT ${BASE_URL}/beer/1 \
  breweryId:=1 \
  name="IPA Premium Actualizada" \
  catId:=1 \
  styleId:=12 \
  abv:=7.2 \
  ibu:=75.0 \
  srm:=14.0 \
  upc:=123789 \
  filepath="/images/ipa-premium.jpg" \
  descript="IPA premium con notas cítricas intensas y amargor pronunciado" \
  addUser:=1
```

### 5. Actualizar cerveza parcialmente (PATCH)
```bash
# cURL - Solo actualizar nombre y ABV
curl -X PATCH ${BASE_URL}/beer/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "IPA Doble Hop",
    "abv": 8.0
  }'

# HTTPie
http PATCH ${BASE_URL}/beer/1 \
  name="IPA Doble Hop" \
  abv:=8.0

# cURL - Solo actualizar descripción
curl -X PATCH ${BASE_URL}/beer/1 \
  -H "Content-Type: application/json" \
  -d '{
    "descript": "Nueva descripción mejorada de la cerveza"
  }'

# HTTPie
http PATCH ${BASE_URL}/beer/1 \
  descript="Nueva descripción mejorada de la cerveza"
```

### 6. Eliminar cerveza
```bash
# cURL
curl -X DELETE ${BASE_URL}/beer/1

# HTTPie
http DELETE ${BASE_URL}/beer/1
```

---

## 🏭 CERVECERAS (Solo lectura)

### 1. Listar todas las cerveceras
```bash
# cURL
curl -X GET ${BASE_URL}/breweries

# HTTPie
http GET ${BASE_URL}/breweries
```

### 2. Obtener cervecera por ID
```bash
# cURL
curl -X GET ${BASE_URL}/brewerie/1

# HTTPie
http GET ${BASE_URL}/brewerie/1
```

---

## 📂 CATEGORÍAS (Solo lectura)

### 1. Listar todas las categorías
```bash
# cURL
curl -X GET ${BASE_URL}/categories

# HTTPie
http GET ${BASE_URL}/categories
```

### 2. Obtener categoría por ID
```bash
# cURL
curl -X GET ${BASE_URL}/categorie/1

# HTTPie
http GET ${BASE_URL}/categorie/1
```

---

## 🎨 ESTILOS (Solo lectura)

### 1. Listar todos los estilos
```bash
# cURL
curl -X GET ${BASE_URL}/styles

# HTTPie
http GET ${BASE_URL}/styles
```

### 2. Obtener estilo por ID
```bash
# cURL
curl -X GET ${BASE_URL}/style/1

# HTTPie
http GET ${BASE_URL}/style/1
```

---

## 📊 Ejemplos de Respuestas

### Respuesta exitosa - GET /beers
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

### Respuesta exitosa - POST /beer
```json
{
  "id": 2,
  "breweryId": 1,
  "name": "Pale Ale Casera",
  "catId": 1,
  "styleId": 10,
  "abv": 5.5,
  "ibu": 40.0,
  "srm": 8.0,
  "upc": 555666,
  "filepath": "",
  "descript": "Cerveza pale ale de elaboración casera con balance perfecto",
  "addUser": 1,
  "lastMod": "2025-12-15T11:00:00"
}
```

### Respuesta de error - 404 Not Found
```json
{
  "timestamp": "2025-12-15T11:05:00",
  "status": 404,
  "error": "Not Found",
  "message": "Cerveza no encontrada con id: 999"
}
```

### Respuesta de error - 400 Bad Request
```json
{
  "timestamp": "2025-12-15T11:10:00",
  "status": 400,
  "error": "Bad Request",
  "errors": {
    "name": "El nombre de la cerveza es obligatorio"
  }
}
```

---

## 🧪 Testing Workflow Completo

### Flujo de prueba completo de CRUD:

```bash
# 1. Ver todas las cervezas iniciales
curl -X GET ${BASE_URL}/beers

# 2. Crear una nueva cerveza
curl -X POST ${BASE_URL}/beer \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "Test Stout",
    "catId": 2,
    "styleId": 20,
    "abv": 8.5,
    "ibu": 50.0,
    "srm": 35.0,
    "upc": 999888,
    "filepath": "",
    "descript": "Stout de prueba",
    "addUser": 1
  }'

# 3. Obtener la cerveza creada (usar el ID de la respuesta anterior)
curl -X GET ${BASE_URL}/beer/2

# 4. Actualizar completamente
curl -X PUT ${BASE_URL}/beer/2 \
  -H "Content-Type: application/json" \
  -d '{
    "breweryId": 1,
    "name": "Test Stout Mejorada",
    "catId": 2,
    "styleId": 20,
    "abv": 9.0,
    "ibu": 55.0,
    "srm": 40.0,
    "upc": 999888,
    "filepath": "/img/stout.jpg",
    "descript": "Stout mejorada con más cuerpo",
    "addUser": 1
  }'

# 5. Actualizar parcialmente solo el nombre
curl -X PATCH ${BASE_URL}/beer/2 \
  -H "Content-Type: application/json" \
  -d '{"name": "Super Stout Final"}'

# 6. Verificar cambios
curl -X GET ${BASE_URL}/beer/2

# 7. Eliminar la cerveza de prueba
curl -X DELETE ${BASE_URL}/beer/2

# 8. Verificar eliminación (debería dar 404)
curl -X GET ${BASE_URL}/beer/2
```

---

## 💡 Tips y Trucos

### Formatear respuestas JSON con jq
```bash
curl -X GET ${BASE_URL}/beers | jq '.'
```

### Ver headers de respuesta
```bash
curl -i -X GET ${BASE_URL}/beers
```

### Verbose mode para debugging
```bash
curl -v -X GET ${BASE_URL}/beers
```

### Guardar respuesta en archivo
```bash
curl -X GET ${BASE_URL}/beers -o beers.json
```

### HTTPie con formato más legible
```bash
http --pretty=all GET ${BASE_URL}/beers
```

---

## 🔐 Autenticación (Futuro)

Actualmente la API no requiere autenticación, pero en el futuro podría usar:

```bash
# Ejemplo con Bearer Token
curl -X GET ${BASE_URL}/beers \
  -H "Authorization: Bearer <token>"

# HTTPie
http GET ${BASE_URL}/beers \
  Authorization:"Bearer <token>"
```

---

## 📝 Notas

- Todos los ejemplos asumen que la API está corriendo en `http://localhost:8080`
- Los IDs utilizados son ejemplos; usa IDs reales de tu base de datos
- Los campos `lastMod` se actualizan automáticamente
- Para PATCH, solo envía los campos que quieres actualizar
- Para PUT, debes enviar todos los campos obligatorios

---

## 🚀 Colección Postman/Insomnia

Puedes importar estos endpoints en Postman o Insomnia creando:

1. Una nueva colección "Beer API"
2. Variable de entorno: `baseUrl = http://localhost:8080/api`
3. Copia cada endpoint con su método HTTP correspondiente
4. Configura los headers: `Content-Type: application/json` para POST/PUT/PATCH

---

**¡Feliz testing! 🍻**
