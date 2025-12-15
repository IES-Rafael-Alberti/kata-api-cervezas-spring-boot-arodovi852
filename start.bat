@echo off
REM Script de inicio rápido para Beer API (Windows)
REM Este script facilita el inicio del proyecto

echo ========================================
echo    Beer API - Inicio Rapido
echo ========================================
echo.

:menu
echo Selecciona una opcion:
echo.
echo 1. Iniciar con Docker (Recomendado)
echo 2. Iniciar sin Docker (requiere MySQL local)
echo 3. Detener Docker
echo 4. Ver logs de Docker
echo 5. Limpiar y reconstruir
echo 6. Salir
echo.
set /p option="Opcion: "

if "%option%"=="1" goto docker_start
if "%option%"=="2" goto local_start
if "%option%"=="3" goto docker_stop
if "%option%"=="4" goto docker_logs
if "%option%"=="5" goto clean_build
if "%option%"=="6" goto end

:docker_start
echo.
echo Iniciando con Docker...
echo.
docker-compose up --build -d
echo.
echo ========================================
echo API disponible en: http://localhost:8080/api
echo Adminer disponible en: http://localhost:8888
echo ========================================
echo.
pause
goto menu

:local_start
echo.
echo Iniciando sin Docker...
echo IMPORTANTE: Asegurate de tener MySQL corriendo localmente
echo.
mvn spring-boot:run
pause
goto menu

:docker_stop
echo.
echo Deteniendo Docker...
echo.
docker-compose down
echo.
echo Servicios detenidos
echo.
pause
goto menu

:docker_logs
echo.
echo Mostrando logs (Ctrl+C para salir)...
echo.
docker-compose logs -f
pause
goto menu

:clean_build
echo.
echo Limpiando y reconstruyendo...
echo.
docker-compose down -v
mvn clean install
docker-compose up --build -d
echo.
echo Reconstruccion completa
echo.
pause
goto menu

:end
echo.
echo Hasta pronto!
exit
