# Distributed Room Reservation System

Sistema distribuído de reservas de salas, projetado como um laboratório prático de microsserviços modernos. A aplicação adota uma arquitetura hexagonal (ports/adapters) com foco em mensageria assíncrona, cache distribuído, descoberta de serviços e integração desacoplada com ambiente completamente dockerizado.

---

## 🌐 Visão Geral

Este sistema é composto por múltiplos microsserviços independentes que se comunicam através de eventos via RabbitMQ e REST fallback(RestTemplate + Redis). Ele gerencia funcionários, salas e reservas de forma distribuída, escalável e resiliente.

### Serviços

- **employee-service**: CRUD de funcionários e publicação de eventos via RabbitMQ.
- **room-service**: gerenciamento de salas e publicação de eventos.
- **reservation-service**: consumo de eventos, cache com Redis e gerenciamento de reservas.
- **gateway**: roteador de requisições HTTP com Spring Cloud Gateway.
- **eureka**: service discovery centralizado.
- **RabbitMQ**: middleware de mensageria assíncrona.
- **Redis**: cache de entidades (salas e funcionários) no reservation-service.

---

## ⚙️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.x
- Spring Cloud 2025.0.x
- Spring Web, JPA, AMQP, Redis
- Eureka Client/Server
- Spring Cloud Gateway
- RabbitMQ
- PostgreSQL
- Redis
- Docker & Docker Compose
- MapStruct
- Lombok

---

## 🧱 Arquitetura Aplicada

A aplicação segue o padrão da **Arquitetura Hexagonal (Ports & Adapters)** com separação clara entre domínio e infraestrutura.

### Camadas

- **Core**: define interfaces (`Ports`) de serviços, repositórios e mensageria.
- **Domain**: contém os modelos puros do negócio (sem dependência de frameworks).
- **Adapters**:
  - HTTP: controladores REST e mapeamento de DTOs.
  - Persistence: repositórios JPA com PostgreSQL.
  - Messaging: publicação/consumo de eventos RabbitMQ.
  - Cache: integração com Redis.
- **DTOs**: criados como `record`, promovendo imutabilidade e clareza.
- **Conversores**: uso do **MapStruct** para evitar boilerplate.

---

## 📚 Padrões de Projeto

- Arquitetura Hexagonal
- Event-Driven Architecture
- Domain-Driven Design (DDD leve)
- Separação de responsabilidades
- Programação orientada a interfaces
- Injeção de dependência por construtor
- Cache com validade configurável
- Fallback via REST com load balancer (Eureka + RestTemplate)

---

## 🚀 Execução Local

> 🔹 Requisitos: Docker + Docker Compose

```bash
git clone https://github.com/taagashi/distributed-room-reservation-system.git
cd distributed-room-reservation-system
docker-compose up --build
```

### Acesse os serviços principais:

- **Gateway**: [http://localhost:8080](http://localhost:8080)
- **Eureka**: [http://localhost:8761](http://localhost:8761)
- **RabbitMQ (UI)**: [http://localhost:15672](http://localhost:15672)  
  *(usuário: `admin` | senha: `admin123`)*

---

## 🔄 Microsserviços

### `employee-service`

- CRUD de funcionários
- Publica eventos:
  - `employee.created`
  - `employee.updated`
  - `employee.deleted`
- Endpoints:
  - `POST /api/v1/employee`
  - `GET /api/v1/employee/{id}`
  - `PUT /api/v1/employee/update/{id}`
  - `DELETE /api/v1/employee/delete/{id}`

---

### `room-service`

- CRUD de salas
- Publica eventos:
  - `room.created`
  - `room.updated`
  - `room.deleted`
- Endpoints:
  - `POST /api/v1/room`
  - `GET /api/v1/room/{id}`
  - `PUT /api/v1/room/update/{id}`
  - `DELETE /api/v1/room/delete/{id}`

---

### `reservation-service`

- Consome eventos de `employee-service` e `room-service` via RabbitMQ
- Atualiza/evita o cache de funcionários e salas com **Redis**
- Garante consistência eventual antes de persistir reservas
- Endpoints:
  - `POST /api/v1/reservation`
  - `GET /api/v1/reservation/{id}`
  - `PUT /api/v1/reservation/update/{id}`
  - `GET /api/v1/reservation/list`
  - `GET /api/v1/reservation/list/employee/{employeeId}`
  - `DELETE /api/v1/reservation/delete/{id}`

---

### `gateway`

- Redireciona requisições HTTP para os microsserviços usando rotas:
  - `/api/v1/employee/**` → `employee-service`
  - `/api/v1/room/**` → `room-service`
  - `/api/v1/reservation/**` → `reservation-service`

---

### `eureka`

- Discovery Server para que todos os microsserviços se registrem dinamicamente
- Interface Web: [http://localhost:8761](http://localhost:8761)

---

## 📁 Estrutura de Diretórios

```bash
distributed-room-reservation-system/
├── docker-compose.yaml
├── README.md
├── employee-service/
├── room-service/
├── reservation-service/
├── gateway/
└── eureka/
```

---

## 📦 Docker e Serviços

Cada serviço possui seu próprio `Dockerfile`, seguindo dois estágios: **build com Maven** e **execução com Eclipse Temurin**. O `docker-compose.yaml` orquestra os serviços com nomes de containers explícitos, volume de dados e configuração via variáveis de ambiente.

---

## 📌 Observações

- O sistema foi desenvolvido com foco em infraestrutura e arquitetura limpa.
- Futuros aprimoramentos podem incluir:
  - Banco de dados externo (MySQL, MongoDB)
  - Testes automatizados (unitários e de integração)

---

## ✨ Autor

> Desenvolvido por **@taagashi**  
> Aluno do IFRN (Pau dos Ferros)

- GitHub: [https://github.com/taagashi](https://github.com/taagashi)
- Currículo Lattes: [http://lattes.cnpq.br/7048245634223145](http://lattes.cnpq.br/7048245634223145)