# Proyecto API Spring Boot Pico y Placa

Este proyecto es una API básica desarrollada en Spring Boot con funcionalidades CRUD. Se conecta a una base de datos MySQL y utiliza Gradle para la gestión de dependencias.
Requisitos

Antes de levantar el proyecto, asegúrate de tener lo siguiente instalado:

    - JDK 17+
    - Gradle
    - MySQL
    - Git


1. Configurar la Base de Datos

    Crea la base de datos en MySQL:

    ```sql
	CREATE DATABASE nombre_de_la_base;
	```
	

	Configura el archivo application.properties en src/main/resources/ con tus credenciales de MySQL:

	```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base?useSSL=false&serverTimezone=UTC
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
	```

3. Construir el Proyecto

Ejecuta el siguiente comando para construir el proyecto con Gradle:

```bash
./gradlew build
```

Ejecutar la Aplicación

Una vez que el proyecto esté construido, puedes levantar la aplicación con:

```bash
./gradlew bootRun
```

O ejecutando directamente el archivo .jar generado en build/libs:

```bash
java -jar build/libs/nombre-del-proyecto-0.0.1-SNAPSHOT.jar
```

Uso

La API estará disponible en http://localhost:8080. Puedes interactuar con los endpoints usando Postman o cURL.