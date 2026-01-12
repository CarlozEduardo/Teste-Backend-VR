## ▶️ Como Executar o Projeto

### 🔧 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- Java 8
- Maven
- Docker
- Git

---

### 🐳 Subindo os bancos de dados
O projeto disponibiliza um arquivo `docker-compose.yml` para subir os bancos de dados necessários.

No diretório da database, execute:

docker-compose up -d

### Executando a aplicação

Com os containers em execução, inicie a aplicação Spring Boot com um dos comandos abaixo:

mvn spring-boot:run

A aplicação ficará disponível em:

http://localhost:8080

### 🔐 Autenticação
Todos os endpoints são protegidos por Basic Authentication.

Credenciais padrão:

Usuário: username

Senha: password

# 💳 API de Cartões e Transações

API REST desenvolvida em 
- Java 17
- Spring Boot
- Spring Security
- Hibernate
- Maven
- MySQL
- jUnit
- Mockito
- Docker

## 📐 Decisões de Projeto e Boas Práticas

Este projeto foi desenvolvido seguindo boas práticas de desenvolvimento com Spring Boot, priorizando código limpo, organização e facilidade de manutenção.

---

### 🧱 Arquitetura em Camadas
A aplicação foi organizada em camadas bem definidas:
- Controller: responsável por receber e responder requisições HTTP
- Service: contém toda a regra de negócio da aplicação
- Repository: responsável pelo acesso ao banco de dados
- Domain (Entity): representa as entidades do sistema

### ⚠️ Tratamento de Exceções
O tratamento de erros foi centralizado usando @RestControllerAdvice, garantindo:
- Padronização das respostas de erro
- Separação da regra de negócio do tratamento HTTP
- Retornos claros conforme os contratos definidos

---

### 🔐 Segurança
A aplicação utiliza Spring Security com autenticação Basic Auth, protegendo todos os endpoints.

---

## 🧩 Padrões de Projeto Utilizados

---

### 🔹 Service Layer Pattern
A camada de serviço concentra toda a regra de negócio da aplicação, evitando lógica nos controllers e garantindo melhor separação de responsabilidades.

Benefícios:
- Centralização das regras de autorização
- Facilidade para testes unitários
- Código mais limpo e reutilizável

---

### 🔹 Repository Pattern
O acesso a dados foi abstraído por meio de repositórios utilizando Spring Data JPA, desacoplando a lógica de negócio da persistência.

Benefícios:
- Menor acoplamento com a camada de dados
- Facilidade de manutenção
- Integração nativa com o Spring

---

### 🔹 DTO (Data Transfer Object)
Foram utilizados DTOs para transferência de dados entre a API e o cliente, evitando a exposição direta das entidades do domínio.

Benefícios:
- Maior controle dos contratos da API
- Redução do acoplamento entre camadas
- Facilidade para evolução da aplicação

---

### 🔹 Exception Handler Pattern
O tratamento de exceções foi centralizado por meio de um handler global utilizando `@RestControllerAdvice`, permitindo a criação de exceções personalizadas conforme as regras de negócio.

Benefícios:
- Padronização das respostas de erro
- Centralização da lógica de tratamento de exceções
- Código mais organizado e de fácil manutenção

---