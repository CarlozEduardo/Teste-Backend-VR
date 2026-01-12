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