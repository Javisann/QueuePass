<p align="center">
  <img src="https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green">
</p>

# QueuePass

*Programa realizado para el Trabajo Fin de Grado.*

Programa que incluye 2 aplicaciones, una API REST para el manejo de la base de datos (Back-End) y interfaz de usuario web (Front-End) la cual se conecta con la API REST.

Este proyecto esta enfocado a la creacion de una aplicacion web que permite a los clientes unirse a colas virtuales y explorar menús y promociones antes de llegar al restaurante, optimizando y mejorando la experiencia.

<p align="center">
  <img src="https://github.com/Javisann/QueuePass/assets/66449602/8fc6efba-dbda-4dbb-8231-4e8fbfa3339f">
</p>

## Índice 

- [Descripción](#descripción)

- [Arquitectura](#arquitectura)

- [Tecnologías](#tecnologías)

- [Características](#características)

- [¿Por qué QueuePass?](#por-qué-queueass)

- [Instalación](#instalación-mysql)

- [Autor](#autor)

## Descripción

La aplicación, QueuePass, es una solución innovadora diseñada para mejorar la eficiencia en la gestión de colas en establecimientos. Permite a los clientes reservar su lugar virtual en la cola desde sus dispositivos móviles, evitando así las largas esperas en la entrada del restaurante. Además de gestionar las colas, la aplicación ofrece a los usuarios la posibilidad de explorar el menú y las promociones disponibles en el restaurante antes de su llegada, lo que contribuye a una experiencia más conveniente y personalizada.

## Arquitectura

![arquitectura](https://github.com/Javisann/QueuePass/assets/66449602/14fa1f9a-5521-486e-94fa-6ae421465450)

## Tecnologías

- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
- ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
- ![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white) ![Nuxtjs](https://img.shields.io/badge/Nuxt-002E3B?style=for-the-badge&logo=nuxtdotjs&logoColor=#00DC82) ![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)
- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
  
## Características

- :heavy_check_mark: `Gestión de Colas Virtuales:` Los clientes pueden unirse a la cola virtual desde cualquier lugar antes de llegar al restaurante.
- :heavy_check_mark: `Exploración de Menús y Promociones:` Los usuarios pueden ver el menú completo y las ofertas especiales ofrecidas por el restaurante.
- :heavy_check_mark: `Registro y Autenticación de Usuarios:` Los clientes pueden crear una cuenta y guardar sus preferencias para futuras visitas.
- :heavy_check_mark: `Visualización del administrador:` El administrador podrá ver la cola y gestionarla además de cambiar ajustes de la empresa y la carta.
 

## ¿Por qué QueuePass?

QueuePass destaca como una solución excepcional para restaurantes que desean agilizar su servicio y mejorar la experiencia del cliente. Al proporcionar una gestión de colas virtuales sin complicaciones, QueuePass elimina las largas esperas y permite a los clientes unirse a la cola desde sus dispositivos móviles. Con una interfaz intuitiva y fácil de usar, esta aplicación ofrece una experiencia sin problemas tanto para los clientes como para los restaurantes, garantizando una mayor eficiencia operativa y una satisfacción del cliente óptima. Con QueuePass, los restaurantes pueden ofrecer un servicio más rápido y conveniente, mejorando así su competitividad y atrayendo a una clientela más amplia.


## Instalación MySQL

#### Requisitos

- **XAMPP** o MySQL Server
- **MySQL Workbench**

Crear la base de datos
```sql
  CREATE DATABASE nombre;
```
Ejecutar el script `prueba.sql`

## Instalación Back-End

#### Requisitos

- **JDK 17**
- **Entorno de desarrollo** - Recomiendo `IntelliJ` o `EclipseIDE`

Ejecutar `XAMPP` e iniciar `MySQL`

Abrir `queuepass-api-rest` con el entorno de desarrollo

Configurar el archivo `application.properties`. src > main > resources > application.properties

```terminal
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/nombreBBDD
    spring.datasource.username=user
    spring.datasource.password=password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql=true
```
Ejecutar ***App.java***

## Instalación Front -End

#### Requisitos

- **Node.js**
- **Text editor** - Recomiendo `Visual Studio Code` con la extension de `Vue` oficial
- **Terminal** - Se puede usar la del propio VS Code

Abrir la terminal y entrar en la aplicación
```terminal
  cd queuepass-front
```

Hacer la instalación de todas las librerias
```npm
  npm i
```

Configurar la URL del Back-End. config > variables.js
```javascript
  const BACK_URL = "http://url_back-end";
```

Ejecutar la aplicación con el comando
```npm
  npm run dev
```
## Autor

[<img src="https://avatars.githubusercontent.com/u/66449602?v=4" width=115><br><sub>Javier Sánchez Prieto</sub>](https://github.com/Javisann)



