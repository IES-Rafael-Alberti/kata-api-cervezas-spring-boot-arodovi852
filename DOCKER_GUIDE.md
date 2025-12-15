# 🐳 Guía de Docker para Beer API

Esta guía te ayudará a ejecutar la aplicación completa usando Docker y Docker Compose.

## 📋 Prerrequisitos

- [Docker](https://docs.docker.com/get-docker/) instalado
- [Docker Compose](https://docs.docker.com/compose/install/) instalado

## 🚀 Inicio Rápido

### Opción 1: Con Docker Compose (Recomendado)

Esta opción levanta todo el stack: MySQL, la aplicación Spring Boot y Adminer.

```bash
# Construir y levantar todos los servicios
docker-compose up --build

# O en modo detached (segundo plano)
docker-compose up --build -d
```

La aplicación estará disponible en:
- **API**: http://localhost:8080/api
- **Adminer** (Administrador de BD): http://localhost:8888

### Opción 2: Solo Base de Datos

Si prefieres ejecutar la aplicación Spring Boot localmente pero usar MySQL en Docker:

```bash
# Solo levantar la base de datos
docker-compose up db -d
```

Luego ejecuta la aplicación con Maven:
```bash
mvn spring-boot:run
```

## 🛠️ Comandos Útiles

### Ver logs
```bash
# Ver logs de todos los servicios
docker-compose logs

# Ver logs de un servicio específico
docker-compose logs app
docker-compose logs db

# Seguir logs en tiempo real
docker-compose logs -f app
```

### Detener servicios
```bash
# Detener sin eliminar contenedores
docker-compose stop

# Detener y eliminar contenedores
docker-compose down

# Eliminar todo incluyendo volúmenes (¡cuidado: borra la BD!)
docker-compose down -v
```

### Reiniciar servicios
```bash
# Reiniciar todos
docker-compose restart

# Reiniciar solo la aplicación
docker-compose restart app
```

### Reconstruir la aplicación
```bash
# Reconstruir solo si hubo cambios en el código
docker-compose up --build app

# Forzar reconstrucción desde cero
docker-compose build --no-cache app
docker-compose up app
```

## 🗄️ Gestión de Base de Datos

### Acceder a Adminer

1. Abre http://localhost:8888
2. Credenciales:
   - **Sistema**: MySQL
   - **Servidor**: db
   - **Usuario**: root
   - **Contraseña**: root
   - **Base de datos**: kata-api

### Acceder a MySQL desde línea de comandos

```bash
# Conectar al contenedor de MySQL
docker exec -it beer-api-mysql mysql -uroot -proot kata-api

# O usar docker-compose
docker-compose exec db mysql -uroot -proot kata-api
```

### Ejecutar scripts SQL

```bash
# Copiar script al contenedor
docker cp script.sql beer-api-mysql:/tmp/

# Ejecutar el script
docker exec -i beer-api-mysql mysql -uroot -proot kata-api < script.sql
```

### Backup de la base de datos

```bash
# Crear backup
docker exec beer-api-mysql mysqldump -uroot -proot kata-api > backup.sql

# Restaurar backup
docker exec -i beer-api-mysql mysql -uroot -proot kata-api < backup.sql
```

## 📊 Arquitectura de Contenedores

```
┌─────────────────────────────────────────┐
│          Docker Network                 │
│      (beer-api-network)                 │
│                                         │
│  ┌──────────────┐    ┌──────────────┐  │
│  │   MySQL      │◄───│  Spring Boot │  │
│  │  Container   │    │     App      │  │
│  │  Port: 3306  │    │  Port: 8080  │  │
│  └──────────────┘    └──────────────┘  │
│         ▲                               │
│         │                               │
│  ┌──────────────┐                       │
│  │   Adminer    │                       │
│  │  Container   │                       │
│  │  Port: 8888  │                       │
│  └──────────────┘                       │
└─────────────────────────────────────────┘
```

## 🔧 Configuración

### Variables de Entorno

El archivo `docker-compose.yml` incluye estas variables:

**Para MySQL:**
- `MYSQL_ROOT_PASSWORD`: Contraseña del usuario root
- `MYSQL_DATABASE`: Nombre de la base de datos

**Para la aplicación:**
- `SPRING_DATASOURCE_URL`: URL de conexión a MySQL
- `SPRING_DATASOURCE_USERNAME`: Usuario de la BD
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la BD

### Modificar configuración

Para cambiar las credenciales, edita el archivo `docker-compose.yml`:

```yaml
db:
  environment:
    MYSQL_ROOT_PASSWORD: tu_nueva_contraseña
    MYSQL_DATABASE: tu_bd

app:
  environment:
    SPRING_DATASOURCE_USERNAME: tu_usuario
    SPRING_DATASOURCE_PASSWORD: tu_contraseña
```

## 📦 Volúmenes

### Volumen de MySQL

Los datos de MySQL se persisten en un volumen Docker llamado `mysql_data`. Esto significa que:

- ✅ Los datos sobreviven a reinicios de contenedores
- ✅ No se pierden al hacer `docker-compose down`
- ❌ Se eliminan con `docker-compose down -v`

### Ver volúmenes
```bash
# Listar volúmenes
docker volume ls

# Inspeccionar volumen
docker volume inspect kata-api-cervezas-spring-boot-arodovi852_mysql_data
```

### Eliminar volumen (¡CUIDADO: Elimina todos los datos!)
```bash
docker-compose down -v
```

## 🐛 Solución de Problemas

### La aplicación no puede conectar a MySQL

**Problema**: Error de conexión "Connection refused"

**Solución**:
```bash
# Verificar que MySQL está saludable
docker-compose ps

# Ver logs de MySQL
docker-compose logs db

# Verificar que MySQL está aceptando conexiones
docker-compose exec db mysqladmin ping -uroot -proot
```

### La aplicación se reinicia constantemente

**Problema**: El contenedor de la app se reinicia en loop

**Solución**:
```bash
# Ver logs para identificar el error
docker-compose logs app

# Verificar que MySQL está listo
docker-compose exec db mysql -uroot -proot -e "SELECT 1"

# Reconstruir la aplicación
docker-compose build --no-cache app
docker-compose up app
```

### Puerto ocupado

**Problema**: "Port is already allocated"

**Solución 1 - Cambiar puertos en `docker-compose.yml`**:
```yaml
app:
  ports:
    - "8081:8080"  # Cambiar 8080 a 8081
```

**Solución 2 - Detener el servicio que usa el puerto**:
```bash
# En Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# En Linux/Mac
lsof -i :8080
kill -9 <PID>
```

### Los cambios en el código no se reflejan

**Problema**: Modificaste el código pero los cambios no aparecen

**Solución**:
```bash
# Reconstruir la imagen
docker-compose build app

# Reiniciar el contenedor
docker-compose up app
```

### MySQL no inicializa los scripts

**Problema**: Las tablas no se crean automáticamente

**Solución**:
```bash
# Eliminar volumen y recrear
docker-compose down -v
docker-compose up --build

# O ejecutar scripts manualmente
docker-compose exec db mysql -uroot -proot kata-api < initSQL/beers.sql
```

## 🧪 Verificar el Despliegue

### 1. Verificar que los contenedores están corriendo
```bash
docker-compose ps
```

Deberías ver:
```
NAME                 STATUS              PORTS
beer-api-mysql       Up                  0.0.0.0:3306->3306/tcp
beer-api-app         Up                  0.0.0.0:8080->8080/tcp
beer-api-adminer     Up                  0.0.0.0:8888->8080/tcp
```

### 2. Verificar la API
```bash
curl http://localhost:8080/api/beers
```

### 3. Verificar MySQL
```bash
docker-compose exec db mysql -uroot -proot -e "SHOW DATABASES;"
```

### 4. Verificar logs
```bash
docker-compose logs app | grep "Started BeerApiApplication"
```

Deberías ver algo como:
```
beer-api-app | Started BeerApiApplication in X.XXX seconds
```

## 🔄 Flujo de Desarrollo

### Desarrollo con Hot Reload

Para desarrollo activo sin reconstruir constantemente:

1. **Usa solo MySQL en Docker**:
```bash
docker-compose up db adminer -d
```

2. **Ejecuta la app con Maven** (hot reload con spring-boot-devtools):
```bash
mvn spring-boot:run
```

3. **Modifica el código** - Spring DevTools recargará automáticamente

### Desarrollo con Docker

Si prefieres todo en Docker:

1. **Modifica el código**
2. **Reconstruye y reinicia**:
```bash
docker-compose up --build app
```

## 📚 Referencias

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)
- [MySQL Docker Official Image](https://hub.docker.com/_/mysql)

## 💡 Tips

1. **Usa `.dockerignore`** para evitar copiar archivos innecesarios
2. **Monitorea recursos** con `docker stats`
3. **Limpia regularmente** con `docker system prune`
4. **Usa variables de entorno** para configuraciones sensibles
5. **Revisa logs frecuentemente** durante desarrollo

## 🎯 Comandos de Producción

### Levantar en producción
```bash
docker-compose -f docker-compose.yml up -d
```

### Escalar servicios (si fuera necesario)
```bash
docker-compose up -d --scale app=3
```

### Monitorear
```bash
# Stats en tiempo real
docker stats

# Inspect de un contenedor
docker inspect beer-api-app
```

---

**¡Disfruta desarrollando con Docker! 🐳🍺**
