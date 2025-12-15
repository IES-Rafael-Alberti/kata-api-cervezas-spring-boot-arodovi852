#!/bin/bash
# Script de inicio rápido para Beer API (Linux/Mac)
# Este script facilita el inicio del proyecto

show_menu() {
    clear
    echo "========================================"
    echo "   Beer API - Inicio Rápido"
    echo "========================================"
    echo ""
    echo "1. Iniciar con Docker (Recomendado)"
    echo "2. Iniciar sin Docker (requiere MySQL local)"
    echo "3. Detener Docker"
    echo "4. Ver logs de Docker"
    echo "5. Limpiar y reconstruir"
    echo "6. Salir"
    echo ""
}

docker_start() {
    echo ""
    echo "Iniciando con Docker..."
    echo ""
    docker-compose up --build -d
    echo ""
    echo "========================================"
    echo "API disponible en: http://localhost:8080/api"
    echo "Adminer disponible en: http://localhost:8888"
    echo "========================================"
    echo ""
    read -p "Presiona Enter para continuar..."
}

local_start() {
    echo ""
    echo "Iniciando sin Docker..."
    echo "IMPORTANTE: Asegúrate de tener MySQL corriendo localmente"
    echo ""
    mvn spring-boot:run
    read -p "Presiona Enter para continuar..."
}

docker_stop() {
    echo ""
    echo "Deteniendo Docker..."
    echo ""
    docker-compose down
    echo ""
    echo "Servicios detenidos"
    echo ""
    read -p "Presiona Enter para continuar..."
}

docker_logs() {
    echo ""
    echo "Mostrando logs (Ctrl+C para salir)..."
    echo ""
    docker-compose logs -f
}

clean_build() {
    echo ""
    echo "Limpiando y reconstruyendo..."
    echo ""
    docker-compose down -v
    mvn clean install
    docker-compose up --build -d
    echo ""
    echo "Reconstrucción completa"
    echo ""
    read -p "Presiona Enter para continuar..."
}

# Main loop
while true; do
    show_menu
    read -p "Opción: " option
    
    case $option in
        1)
            docker_start
            ;;
        2)
            local_start
            ;;
        3)
            docker_stop
            ;;
        4)
            docker_logs
            ;;
        5)
            clean_build
            ;;
        6)
            echo ""
            echo "¡Hasta pronto!"
            exit 0
            ;;
        *)
            echo "Opción inválida"
            sleep 2
            ;;
    esac
done
