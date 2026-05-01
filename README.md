# OviedoWebUD — Registro de Aspirantes Universitarios

> **Universidad Distrital Francisco José de Caldas**  
> Asignatura: Programación Avanzada | Grupo 302 | 2026  
> Autor: **Julián David Oviedo**

---

## Descripción

Aplicación web desarrollada con **Java JSF (Jakarta Faces)** y **Maven** que permite el registro y consulta de aspirantes universitarios. Incluye un simulador de CDT (Certificado de Depósito a Término).

---

## Arquitectura del Proyecto

El proyecto sigue el patrón **MVC (Model-View-Controller)** organizado en capas:

```
OviedoWebUD/
├── src/main/java/ovd/
│   ├── bean/              ← Controladores (Bean JSF - Controller)
│   │   ├── AspiranteBean.java
│   │   └── CDTBean.java
│   └── modelo/            ← Modelo (DTOs y DAOs)
│       ├── Persona.java          (DTO base)
│       ├── Aspirante.java        (DTO)
│       ├── ProgramaAcademico.java (DTO)
│       ├── AspiranteDAO.java     (DAO)
│       ├── ProgramaAcademicoDAO.java (DAO)
│       ├── SimulacionCDT.java    (DTO)
│       └── SimulacionCDTDAO.java (DAO)
└── src/main/webapp/       ← Vista (XHTML - View)
    ├── index.xhtml
    ├── registro.xhtml
    ├── admin.xhtml
    ├── simuladorCDT.xhtml
    └── resultadoCDT.xhtml
```

---

## Módulos

### 1. Registro de Aspirantes
- Formulario de inscripción con validaciones completas
- Campos: Identificación, Nombres, Apellidos, Correo, Teléfono, Programa Académico
- Programas ofertados: Ingeniería de Sistemas, Industrial, Matemática, Administración, Derecho, Telemática
- Vista administrador con tabla de registros

### 2. Simulador CDT
- Cálculo de rendimientos de Certificado de Depósito a Término
- Parámetros: Capital, Tasa EA, Plazo (días/meses/años)
- Resultados: Intereses generados, valor final, tasa efectiva
- Historial de simulaciones

---

## Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje base |
| Jakarta Faces (JSF) | 4.0 | Framework web MVC |
| Weld CDI | 4.0 | Inyección de dependencias |
| Maven | 3.x | Gestión de dependencias |
| Apache Tomcat | 10.1 | Servidor de aplicaciones |
| Bootstrap / CSS | 5 | Estilos y diseño |

---

## Requisitos para ejecutar

- Java JDK 21+
- Apache Maven 3.8+ (o Eclipse con Maven integrado)
- Apache Tomcat 10.1+
- Conexión a internet (primera vez, para descargar dependencias Maven)

---

## Instalación y ejecución

### Con Eclipse IDE

```
1. File → Import → Maven → Existing Maven Projects
2. Seleccionar la carpeta OviedoWebUD
3. Esperar que Maven descargue dependencias
4. Clic derecho en proyecto → Run As → Run on Server → Tomcat v10.1
5. Abrir: http://localhost:8080/OviedoWebUD/
```

### Con Maven desde consola

```bash
cd OviedoWebUD
mvn clean package
# Copiar target/OviedoWebUD.war a la carpeta webapps de Tomcat
```

---

## Commits del proyecto

| Commit | Descripción |
|---|---|
| `origen` | Proyecto inicial — Registro de Aspirantes |
| `jdo_logica_negocio` | Diagrama de clases y DTOs/DAOs del simulador CDT |
| `jdo_mvc` | Diseño en capas MVC — Bean y lógica de negocio CDT |
| `jdo_frontend` | Front-end con validaciones y estilos — vistas XHTML CDT |

---

## Diagrama de Clases

El diagrama de clases se encuentra en el archivo `OviedoWebUD_DiagramaClases.drawio.png` en la raíz del repositorio.

---

## Autor

**Julián David Oviedo**  
Programación Avanzada — Grupo 302  
Universidad Distrital Francisco José de Caldas  
2026
