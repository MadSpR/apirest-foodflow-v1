# FoodFlow - Backend

**FoodFlow** es una aplicación backend de gestión de pedidos y usuarios, desarrollada con **Spring Boot** y **JWT** para la autenticación. Permite a los usuarios registrarse, iniciar sesión, consultar menús de platos y hacer pedidos.

## Tecnologías utilizadas

- **Java 17** (o superior): Lenguaje de programación.
- **Spring Boot 2.7.x**: Framework para desarrollo de aplicaciones Java.
- **Spring Security**: Para manejar la autenticación y autorización de usuarios.
- **JWT (JSON Web Token)**: Para la autenticación basada en token.
- **Spring Data JPA**: Para la persistencia de datos con Hibernate.
- **MySQL**: Base de datos relacional para almacenar los datos.
- **Maven**: Para gestionar las dependencias y la construcción del proyecto.
- **Spring Boot DevTools**: Para el desarrollo en caliente, facilitando el reinicio automático de la aplicación.
- **BCrypt**: Para la codificación de contraseñas de los usuarios.

## Funcionalidades

1. **Autenticación de Usuarios**: Implementación de JWT para el login y registro de usuarios.
2. **Gestión de Usuarios**: Los administradores pueden crear, actualizar y eliminar usuarios.
3. **Gestión de Menú de Platos**: Los usuarios pueden visualizar los platos disponibles en el menú.
4. **Realización de Pedidos**: Los usuarios pueden hacer pedidos con los platos del menú.
5. **Soft Delete**: Cuando se elimina un usuario, se marca como eliminado sin borrar los datos.
