# Distributed Room Reservation System

Sistema distribuído de reservas de salas, projetado como laboratório prático de microsserviços modernos. A aplicação foi construída com foco em mensageria assíncrona, descoberta de serviços, roteamento inteligente e arquitetura hexagonal orientada a contratos.

---

## 🌐 Visão Geral

O sistema é composto por múltiplos microsserviços independentes que se comunicam por eventos:

- **employee-service**: gerencia funcionários e publica eventos de CRUD.
- **room-service**: gerencia salas e também publica eventos.
- **reservation-service** *(em desenvolvimento)*: escuta eventos e cria reservas.
- **gateway**: roteador de requisições HTTP usando Spring Cloud Gateway.
- **eureka**: servidor de descoberta e registro de microsserviços.
- **RabbitMQ**: mensageria para integração assíncrona entre serviços.

---

## ⚙️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Cloud 2025.0.0**
- **Spring Web, JPA, AMQP, Redis, Cache**
- **Eureka Client / Server**
- **Spring Cloud Gateway**
- **RabbitMQ**
- **H2 / PostgreSQL**
- **Docker & Docker Compose**
- **MapStruct**
- **Lombok**

---

## 🧱 Arquitetura Aplicada

Este projeto adota uma **Arquitetura Hexagonal (Ports & Adapters)**, com forte separação entre domínio e infraestrutura:

- **Core** define os **ports** (interfaces) de serviços, repositórios e eventos.
- **Domain** representa os modelos de negócio puros, sem anotações de framework.
- **Adapters** implementam os ports para:
  - **HTTP** (controladores e DTOs)
  - **Persistence** (repositórios JPA)
  - **Mensageria** (RabbitMQ)
- **DTOs** são definidos como `record`, promovendo imutabilidade e clareza.
- **Conversões** entre camadas são realizadas com **MapStruct**, evitando acoplamento e boilerplate.

A lógica de negócios foi propositalmente mantida simples, priorizando o estudo de infraestrutura, integração e arquitetura limpa.

---

## 📚 Padrões de Projeto

- **Arquitetura Hexagonal**
- **Event-Driven Architecture**
- **Domain-Driven Design (DDD) leve**
- **Separação de responsabilidades**: cada camada conhece apenas seu papel
- **Programação orientada a interfaces**
- **Injeção de dependência por construtor**
- **Publicação de eventos assíncronos desacoplada**
- **Fallback via REST (em desenvolvimento)**

---

## 🚀 Execução Local

> 🔹 Requisitos: Docker + Docker Compose

```bash
git clone https://github.com/seu-usuario/distributed-room-reservation-system.git
cd distributed-room-reservation-system
docker-compose up --build
```

### Acesse os serviços principais:

- **Gateway**: [http://localhost:8080](http://localhost:8080)  
- **Eureka**: [http://localhost:8761](http://localhost:8761)  
- **RabbitMQ**: [http://localhost:15672](http://localhost:15672)  
  *(usuário: `admin` | senha: `admin123`)*

---

## 🔄 Microsserviços

### `employee-service`

- CRUD de funcionários
- Publica eventos:
  - `employee.created`
  - `employee.updated`
  - `employee.deleted`

### `room-service`

- CRUD de salas
- Publica eventos:
  - `room.created`
  - `room.updated`
  - `room.deleted`

### `reservation-service`

- *(em construção)*
- Vai escutar os eventos de `employee` e `room` para criar reservas com consistência eventual.

### `gateway`

- Redirecionamento de requisições HTTP para os serviços registrados no Eureka.
- Pode aplicar filtros, rotas dinâmicas e políticas de fallback em chamadas REST.

### `eureka`

- Responsável pelo **Service Discovery**: cada microsserviço se registra nele automaticamente.
- Interface web para monitorar status dos serviços.

---

## 📁 Estrutura de Diretórios

```bash
distributed-room-reservation-system/
├── docker-compose.yaml
├── README.md
├── employee-service/
│   └── src/main/java/com/br/thaua/employee_service/...
├── room-service/
│   └── src/main/java/com/br/thaua/room_service/...
├── reservation-service/
│   └── src/main/java/com/br/thaua/reservation_service/...
├── gateway/
│   └── src/main/java/com/br/thaua/gateway/...
└── eureka/
    └── src/main/java/com/br/thaua/eureka/...
```

---

## 🔧 Futuras Implementações

- [ ] Finalizar o `reservation-service` como consumidor dos eventos de `employee` e `room`
- [ ] Implementar fallback com Spring Cloud Gateway e Resilience4j
- [ ] Adicionar testes unitários e de integração
- [ ] Incluir autenticação e autorização com JWT
- [ ] Gerar documentação com Swagger/OpenAPI

---

## ✨ Autor

> Desenvolvido por **@taagashi**  
> Aluno do IFRN (Pau dos Ferros)

- GitHub: [https://github.com/taagashi](https://github.com/taagashi)  
- Currículo Lattes: [http://lattes.cnpq.br/7048245634223145](http://lattes.cnpq.br/7048245634223145)

