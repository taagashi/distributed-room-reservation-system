# Distributed Room Reservation System

Este projeto simula um sistema distribuído de reservas de salas, composto por múltiplos microsserviços desenvolvidos com **Spring Boot**, **RabbitMQ**, **Eureka**, **Spring Cloud Gateway** e banco de dados **PostgreSQL** / **H2**.

---

## 🌐 Visão Geral

O sistema é composto por:

* **employee-service**: gerencia funcionários e emite eventos ao sistema.
* **room-service**: gerencia salas e também emite eventos.
* **reservation-service**: (em desenvolvimento) responsável por escutar os eventos e criar reservas.
* **gateway**: roteador que centraliza as requisições HTTP.
* **eureka**: servidor de descoberta de serviços.
* **RabbitMQ**: mensageria para integração assíncrona entre microsserviços.

---

## ⚙️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.3**
* **Spring Cloud 2025.0.0**
* **Spring Web, JPA, AMQP, Redis, Cache**
* **Eureka Client / Server**
* **Spring Cloud Gateway**
* **RabbitMQ**
* **H2 / PostgreSQL**
* **Docker & Docker Compose**
* **MapStruct**
* **Lombok**

---

## 🪤 Arquitetura Aplicada

* **Microsserviços independentes** com comunicação por eventos.

* **Event-Driven Architecture** via RabbitMQ.

* **Arquitetura Hexagonal** (Ports & Adapters):

    * Interfaces definem contratos
    * Adapters implementam integrações (RabbitMQ, JPA)
    * Services orquestram a lógica do negócio

* \*\*DTOs com \*\***`record`** para imutabilidade.

* **Mappers com MapStruct** para conversão clara entre camadas.

---

## 📚 Padrões de Projeto Adotados

* **Domain-Driven Design (DDD)** leve
* **Injeção de dependência** por construtor
* **Separacão de responsabilidades** (Controller / Service / Repository / Messaging)
* **Mensageria desacoplada** com configuração de exchange, filas e routing keys

---

## 🚀 Como Executar Localmente

> 🔹 Requisitos: Docker e Docker Compose instalados

1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/distributed-room-reservation-system.git
cd distributed-room-reservation-system
```

2. Inicie todos os serviços via Docker Compose

```bash
docker-compose up --build
```

3. Acesse:

* Gateway: `http://localhost:8080`
* Eureka: `http://localhost:8761`
* RabbitMQ: `http://localhost:15672` (usuário: `admin`, senha: `admin123`)

---

## 🔄 Microsserviços em Detalhe

### employee-service

* CRUD de funcionários
* Publica eventos: `employee.created`, `employee.updated`, `employee.deleted`

### room-service

* CRUD de salas
* Publica eventos: `room.created`, `room.updated`, `room.deleted`

### reservation-service

* **(em progresso)** Vai escutar eventos e criar reservas com consistência eventual

### gateway

* Redireciona chamadas HTTP para os microsserviços registrados no Eureka

### eureka

* Servidor de descoberta e registro de serviços

---

## 📊 Estrutura de Diretórios

```
distributed-room-reservation-system/
├── employee-service/
├── room-service/
├── reservation-service/
├── gateway/
├── eureka/
├── docker-compose.yaml
└── README.md
```

---

## ✨ Autor

> Desenvolvido por **@taagashi** no contexto de estudos práticos com microsserviços. Aluno do IFRN (Pau dos Ferros), integrante do NADIC e colaborador do projeto Legrand.

GitHub: [https://github.com/taagashi](https://github.com/taagashi)
Currículo Lattes: [http://lattes.cnpq.br/7048245634223145](http://lattes.cnpq.br/7048245634223145)

---

\<?xml version="1.0" encoding="UTF-8"?>

\<project xmlns="[http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0)" xmlns\:xsi="[http://www.w3.org/2001/XMLSchema-instance](http://www.w3.org/2001/XMLSchema-instance)"

&#x9;xsi\:schemaLocation="[http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0) [https://maven.apache.org/xsd/maven-4.0.0.xsd](https://maven.apache.org/xsd/maven-4.0.0.xsd)">

&#x9;\<modelVersion>4.0.0\</modelVersion>

&#x9;\<parent>

&#x9;	\<groupId>org.springframework.boot\</groupId>

&#x9;	\<artifactId>spring-boot-starter-parent\</artifactId>

&#x9;	\<version>3.5.3\</version>

&#x9;	\<relativePath/> \<!-- lookup parent from repository -->

&#x9;\</parent>

&#x9;\<groupId>com.br.thaua\</groupId>

&#x9;\<artifactId>reservation-service\</artifactId>

&#x9;\<version>0.0.1-SNAPSHOT\</version>

&#x9;\<name>reservation-service\</name>

&#x9;\<description>Demo project for Spring Boot\</description>

&#x9;\<url/>

&#x9;\<licenses>

&#x9;	\<license/>

&#x9;\</licenses>

&#x9;\<developers>

&#x9;	\<developer/>

&#x9;\</developers>

&#x9;\<scm>

&#x9;	\<connection/>

&#x9;	\<developerConnection/>

&#x9;	\<tag/>

&#x9;	\<url/>

&#x9;\</scm>

&#x9;\<properties>

&#x9;	\<java.version>21\</java.version>

&#x9;	\<spring-cloud.version>2025.0.0\</spring-cloud.version>

&#x9;\</properties>

&#x9;\<dependencies>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.boot\</groupId>

&#x9;		\<artifactId>spring-boot-starter-amqp\</artifactId>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.boot\</groupId>

&#x9;		\<artifactId>spring-boot-starter-cache\</artifactId>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.boot\</groupId>

&#x9;		\<artifactId>spring-boot-starter-data-jpa\</artifactId>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.boot\</groupId>

&#x9;		\<artifactId>spring-boot-starter-data-redis\</artifactId>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.boot\</groupId>

&#x9;		\<artifactId>spring-boot-starter-web\</artifactId>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.cloud\</groupId>

&#x9;		\<artifactId>spring-cloud-starter-netflix-eureka-client\</artifactId>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>com.h2database\</groupId>

&#x9;		\<artifactId>h2\</artifactId>

&#x9;		\<scope>runtime\</scope>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.postgresql\</groupId>

&#x9;		\<artifactId>postgresql\</artifactId>

&#x9;		\<scope>runtime\</scope>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.projectlombok\</groupId>

&#x9;		\<artifactId>lombok\</artifactId>

&#x9;		\<optional>true\</optional>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.boot\</groupId>

&#x9;		\<artifactId>spring-boot-starter-test\</artifactId>

&#x9;		\<scope>test\</scope>

&#x9;	\</dependency>

&#x9;	\<dependency>

&#x9;		\<groupId>org.springframework.amqp\</groupId>

&#x9;		\<artifactId>spring-rabbit-test\</artifactId>

&#x9;		\<scope>test\</scope>

&#x9;	\</dependency>

&#x9;\</dependencies>

&#x9;\<dependencyManagement>

&#x9;	\<dependencies>

&#x9;		\<dependency>

&#x9;			\<groupId>org.springframework.cloud\</groupId>

&#x9;			\<artifactId>spring-cloud-dependencies\</artifactId>

&#x9;			\<version>\${spring-cloud.version}\</version>

&#x9;			\<type>pom\</type>

&#x9;			\<scope>import\</scope>

&#x9;		\</dependency>

&#x9;	\</dependencies>

&#x9;\</dependencyManagement>

&#x9;\<build>

&#x9;	\<plugins>

&#x9;		\<plugin>

&#x9;			\<groupId>org.apache.maven.plugins\</groupId>

&#x9;			\<artifactId>maven-compiler-plugin\</artifactId>

&#x9;			\<configuration>

&#x9;				\<annotationProcessorPaths>

&#x9;					\<path>

&#x9;						\<groupId>org.projectlombok\</groupId>

&#x9;						\<artifactId>lombok\</artifactId>

&#x9;					\</path>

&#x9;				\</annotationProcessorPaths>

&#x9;			\</configuration>

&#x9;		\</plugin>

&#x9;		\<plugin>

&#x9;			\<groupId>org.springframework.boot\</groupId>

&#x9;			\<artifactId>spring-boot-maven-plugin\</artifactId>

&#x9;			\<configuration>

&#x9;				\<excludes>

&#x9;					\<exclude>

&#x9;						\<groupId>org.projectlombok\</groupId>

&#x9;						\<artifactId>lombok\</artifactId>

&#x9;					\</exclude>

&#x9;				\</excludes>

&#x9;			\</configuration>

&#x9;		\</plugin>

&#x9;	\</plugins>

&#x9;\</build>

\</project>

## 🔧 Futuras Implementações

*

---

## 📄 Licença

Este repositório é de livre uso para fins educacionais.

