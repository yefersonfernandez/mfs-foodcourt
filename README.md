# 🍽️ mfs-plazoleta

Microservicio encargado de gestionar los restaurantes que hacen parte de la plaza de comidas, así como sus respectivos menús y platos.

## 👤 Roles implicados
- **Administrador**: crea restaurantes y propietarios.
- **Propietario**: gestiona platos y empleados.
- **Empleado**: prepara y entrega pedidos.
- **Cliente**: visualiza menús, hace pedidos.

## 🔧 Funcionalidades
- Crear, modificar y eliminar restaurantes
- Crear cuenta para el propietario del restaurante
- Crear, modificar y eliminar platos
- Crear cuentas para empleados del restaurante
- Listar pedidos pendientes y asignarlos
- Cambiar estados del pedido (EN_PREPARACION, LISTO, ENTREGADO)

## 📦 Tecnologías
- Java 17
- Spring Boot
- PostgreSQL / MySQL
- Arquitectura Hexagonal
- OpenAPI / Swagger
- JUnit + Mockito (pruebas unitarias)

## 🧪 Requisitos
- Documentación OpenAPI
- Pruebas unitarias por HU
- Cada HU en rama independiente

## 🔗 Documentación
- [Documentación OpenAPI](#)
- [Guía de instalación](#)
- [Endpoints principales](#)

## 📌 Estados del pedido
- `PENDIENTE`
- `EN_PREPARACION`
- `LISTO`
- `ENTREGADO`
- `CANCELADO`
