<p align="center">
  <img src="https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green">
</p>

# QueuePass

*Final Degree Project🚀*

Program that includes 2 applications, a REST API for managing the database (Back-End) and a web user interface (Front-End) which connects to the REST API.

This project is focused on creating a web application that allows customers to join virtual queues and explore menus and promotions before arriving at the restaurant, optimizing and improving the experience.

<p align="center">
  <img src="https://github.com/Javisann/QueuePass/assets/66449602/8fc6efba-dbda-4dbb-8231-4e8fbfa3339f">
</p>

## Index

- [Description](#description)

- [Architecture](#architecture)

- [Technologies](#technologies)

- [Features](#features)

- [¿Why QueuePass?](#why-queueass)

- [Installation](#installation-mysql)

- [Author](#author)

## Description

The application, QueuePass, is an innovative solution designed to improve the efficiency of queue management in establishments. It allows customers to reserve their virtual place in line from their mobile devices, thus avoiding long waits at the restaurant entrance. In addition to managing queues, the application offers users the possibility of exploring the menu available in the restaurant before their arrival, contributing to a more comfortable and personalized experience.

## Architecture

![arquitectura](https://github.com/Javisann/QueuePass/assets/66449602/14fa1f9a-5521-486e-94fa-6ae421465450)

## Technologies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white) ![Nuxtjs](https://img.shields.io/badge/Nuxt-002E3B?style=for-the-badge&logo=nuxtdotjs&logoColor=#00DC82) ![Vue.js](https://img.shields.io/badge/vuejs-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D)

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
  
## Features

- :heavy_check_mark: `Virtual Queue Management:` Customers can join the virtual queue from anywhere before arriving at the restaurant.
- :heavy_check_mark: `Exploring Menus and Promotions:` Users can view the full menu and special offers offered by the restaurant.
- :heavy_check_mark: `User Registration and Authentication:` Customers can create an account and save their preferences for future visits.
- :heavy_check_mark: `Manager View:` The administrator will be able to see the queue and manage it as well as change company and menu settings.
 

## ¿Why QueuePass?

QueuePass stands out as an exceptional solution for restaurants that want to streamline their service and improve the customer experience. By providing hassle-free virtual queue management, QueuePass eliminates long waits and allows customers to join the queue from their mobile devices. With an intuitive and easy-to-use interface, this app offers a seamless experience for both customers and restaurants, ensuring greater operational efficiency and optimal customer satisfaction. With QueuePass, restaurants can offer faster and more convenient service, thus improving their competitiveness and attracting a broader clientele.


## MySQL installation

#### Requirements

- **XAMPP** or MySQL Server
- **MySQL Workbench**

Create database
```sql
  CREATE DATABASE nombre;
```
Run the script `prueba.sql`

## Back-End Installation

#### Requirements

- **JDK 17**
- **Development environment** - Recomiendo `IntelliJ` or `EclipseIDE`

Run `XAMPP` and init `MySQL`

Open `queuepass-api-rest` with IDE

Configure the `application.properties` file. src > main > resources > application.properties

```terminal
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/nombreBBDD
    spring.datasource.username=user
    spring.datasource.password=password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql=true
```
Run ***App.java***

## Front -End installation

#### Requirements

- **Node.js**
- **Text editor** - I recommend `Visual Studio Code` with the official `Vue` extension
- **Terminal** - You can use the one from VS Code itself

Open terminal and enter the application
```terminal
  cd queuepass-front
```

Install all libraries
```npm
  npm i
```

Configure Back-End URL. config > variables.js
```javascript
  const BACK_URL = "http://url_back-end";
```

Run the application with the command
```npm
  npm run dev
```
## Author

[<img src="https://avatars.githubusercontent.com/u/66449602?v=4" width=115><br><sub>Javier Sánchez Prieto</sub>](https://github.com/Javisann)



