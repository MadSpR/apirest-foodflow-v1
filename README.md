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

## Modificaciones a la Base de Datos

Aunque al ejecutar el programa Spring Boot se encarga de crear la estructura de la base de datos, es necesario modificar las FK para añadir "ON DELETE CASCADE".

#### Paso 1: Ver nombres de las claves foráneas actuales (opcional pero útil)
```
-- Esto te muestra las claves foráneas de cada tabla
SHOW CREATE TABLE pedidos;
SHOW CREATE TABLE detalle_pedidos;
```

#### Paso 2: Eliminar las claves foráneas existentes


```
-- Eliminar FK de usuario_id en pedidos
ALTER TABLE pedidos DROP FOREIGN KEY pedidos_ibfk_1;

-- Eliminar FKs en detalle_pedidos (pedido_id y plato_id)
ALTER TABLE detalle_pedidos DROP FOREIGN KEY detalle_pedidos_ibfk_1;
ALTER TABLE detalle_pedidos DROP FOREIGN KEY detalle_pedidos_ibfk_2;
```

#### Paso 3: Añadir de nuevo las FKs con `ON DELETE CASCADE`


```
-- FK con ON DELETE CASCADE en pedidos
ALTER TABLE pedidos
ADD CONSTRAINT fk_pedidos_usuario
FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
ON DELETE CASCADE;

-- FK con ON DELETE CASCADE en detalle_pedidos
ALTER TABLE detalle_pedidos
ADD CONSTRAINT fk_detalle_pedido
FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
ON DELETE CASCADE;

ALTER TABLE detalle_pedidos
ADD CONSTRAINT fk_detalle_plato
FOREIGN KEY (plato_id) REFERENCES plato(id)
ON DELETE CASCADE;
```

