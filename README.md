  <h1 align="center"> Challenge ForoHub Poquitas Dudas, practicando Spring Boot 3 y Seguridad. </h1>

## Descripción del Proyecto
Poquitas Dudas es una API diseñada para gestionar foros en los que los usuarios pueden crear tópicos, responder a ellos y consultar información relacionada. Este proyecto utiliza Spring Boot, Hibernate, y una base de datos relacional para ofrecer una experiencia robusta y escalable.


## Características
- Gestión de tópicos:
  - Crear, listar, actualizar, y eliminar tópicos.
  - Marcar tópicos como respondidos.
- Gestión de respuestas:
  - Asociar respuestas a tópicos.
  - Consultar respuestas de un tópico.
- Paginación y ordenamiento en las consultas.
- Filtro de datos mediante parámetros personalizados.
- Seguridad:
  - Implementación de autenticación con tokens JWT.
    
## Requisitos Previos
- **Java 17** o superior instalado.
- Una base de datos MySQL configurada.
- Insomnia (opcional para pruebas de API).

## Endpoints de la API

### Autenticación
Los endpoints están protegidos por autenticación JWT. Antes de consumirlos, debes obtener un token válido utilizando el endpoint de login.

- **POST** `/login`  
  **Descripción**: Genera un token JWT para autenticar solicitudes.  
  **Body**:
  ```json
  {
    "nombre": "usuario",
    "contrasena": "contraseña"
  }
  Respuesta
   {
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  
### Tópicos
- **POST** `/topicos`: Crear un nuevo tópico.
- **GET** `/topicos`: Listar todos los tópicos (con paginación y ordenamiento).
- **PUT** `/topicos/{id}`: Actualizar un tópico existente.
- **DELETE** `/topicos/{id}`: Eliminar un tópico.

 ### Respuestas
- **POST** `/respuestas`: Crear una respuesta asociada a un tópico y a un usuario (autor).
- **GET** `/respuestas`: Listar todas las respuestas  (con paginación y ordenamiento por fecha.
- **GET** `/respuestas/{id}`: Listar todas las respuestas   de un solo tópico  ordenados por fecha desde el mas reciente.

 ### Cursos 
- **POST** `/cursos`: Crear un nuevo curso.
- **GET** `/topicos`: Listar todos los cursos (con paginación y ordenamiento).
- **PUT** `/topicos/{id}`: Actualizar un curos existente.
- **DELETE** `/topicos/{id}`: Eliminar un curso por su id.

### Estructura del Proyecto
- `Controller`:  Controladores para manejar las solicitudes HTTP.
- `Domain`: Interfaces de repositorio para acceder a la base de datos, entidades y DTOs.
- `Security`: Configuración de seguridad.

  
##  Tecnologías utilizadas
|Herramientas|Lenguaje|IDE y Software|
|:----------:|:--------:|:---:|
|JPA, SpringBoot3, flywaydb y lombook |Java |IntelliJ 2024, Insomnia|

##  Conclusión
El challenge de desarrollado, fue la mejor forma de mostrar alguno de los conocimientos obtenidos en esta formacion, fue una parte indespensable por que su implementacion fue de forma personal,
demostrando el aprendizaje adquirido y  y asugurame de que 


##  Personas-Desarrolladores del Proyecto
-  Desarrollado por Alejandra López
