# 🧪 Prueba Técnica – Registro y Listado de Empresas

## 📋 Descripción

Aplicación web desarrollada como parte de una prueba técnica. Permite registrar y listar empresas, validando que el NIT sea único, y ofreciendo una interfaz clara y fluida. Está construida usando **Spring Boot (Java)** en el backend y **React con Vite + Bootstrap** en el frontend.

---

## 🧱 Estructura del proyecto

```
/backend        → API REST en Spring Boot
/frontend       → Interfaz de usuario en React
/script.sql     → Script SQL para creación de tabla en PostgreSQL
```

---

## ⚙️ Tecnologías utilizadas

### Backend
- Java 21
- Spring Boot 3.2.x
- Spring Data JPA
- PostgreSQL
- Maven

### Frontend
- React + Vite
- Bootstrap 5
- Axios
- SweetAlert2

---

## 🚀 Instrucciones de instalación



### 1. Backend – Spring Boot



#### 🐘  Usar PostgreSQL (recomendado)
Edita `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/empresasdb
spring.datasource.username=postgres
spring.datasource.password=tu_clave
```

#### ▶ Ejecutar backend

```bash
mvn spring-boot:run
```

Esto inicia la API REST en `http://localhost:8080`.
end-point registro: POST`/empresas`  y end-point listado: `/empresas` GET
---

### 3. Frontend – React

```bash 
npm create vite@latest frontend --template react
cd ../frontend
npm install axios react-router-dom
npm install
npm run dev

```

Esto levanta el frontend en `http://localhost:5173`.

---

## 🧪 Script SQL

Para crear la tabla en PostgreSQL:

```sql
CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    nit VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);
```

---

## ✅ Funcionalidades implementadas

### Registro de empresas
- Validación visual en campos requeridos
- NIT único
- Formato de teléfono validado
- Alertas visuales con SweetAlert2
- Bootstrap para diseño amigable

### Listado de empresas
- Tabla de empresas registradas
- Consulta en tiempo real desde el backend

---

## 🧠 Decisiones técnicas destacadas

- **Arquitectura limpia**: separación de capas (Controlador, Servicio, Repositorio, DTO).
- **Validaciones en backend**: aplicadas tanto por anotaciones como manuales.
- **DTOs en lugar de entidades** para desacoplar el modelo de persistencia del API.
- **SweetAlert2** mejora la UX al dar retroalimentación clara.
- **Bootstrap** utilizado por su facilidad de uso y aspecto profesional.
- **Vite** elegido por ser más rápido que CRA (Create React App).
