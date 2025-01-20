<h1>Doa Leite - API de doação de Leite em Pó com Fórmulas Infantis</h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=java&message=language&color=blue&style=for-the-badge&logo=JAVA"/>  
  <img src="https://img.shields.io/static/v1?label=spring&message=framework&color=blue&style=for-the-badge&logo=SPRING"/>   
  <img src="https://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge"/>
</p>

<p align="center">
  <img alt="Logo" title="#Logo" src="https://github.com/rafaelcloud83/assets-repository/blob/main/appDoacao/LeiteLogo.png?raw=true" width="150px">
</p>

# Introdução

<h2>Sabe-se da importância do aleitamento materno até os seis meses, porém tem Mães que por algum motivo não produz, ou produz pouco Leite Materno, ou também não possui recursos financeiros para comprar o Leite em pó com fórmulas infantis. Isso me motivou a desenvolver este aplicativo para a atividade extensionista da faculdade.<br>
O aplicativo tem o objetivo de fazer a conexão entre o Recebedor e o Doador de maneira simples e eficaz.</h2>

## Sumário

* [Regras de Negócio](#regras-de-negócio)
* [Aplicativo Mobile](#aplicativo-mobile)
* [Tecnologias e Ferramentas utilizadas](#tecnologias-e-ferramentas-utilizadas)
* [Configuração das variáveis de ambiente localmente](#configuração-das-variáveis-de-ambiente-localmente)
* [Configuração do application.properties](#configuração-do-applicationproperties)
* [Execução do projeto localmente](#execução-do-projeto-localmente)
    * [com execução manual do jar](#com-execução-manual-do-jar)
    * [com imagem no Docker Hub](#com-imagem-no-docker-hub)
* [Endpoints](#endpoints)
    * [Públicos](#públicos)
    * [Usuários](#usuários)
    * [Pedidos](#pedidos)

## Regras de Negócio

[voltar ao início](#sumário)

- Existe 3 perfis de usuários: Recebedor, Doador e Admin;
- Existe 3 status da doação: Aguardando, Doado e Concluído;
- O usuário Recebedor pode criar um pedido de doação, este pedido receberá o status de Aguardando e aparecerá para todos os usuários Doador;
- O usuário Doador escolhe um pedido de doação para fazer a doação, este pedido receberá o status de Doado e ficará aguardando a confirmação de recebimento do Leite pelo usuário Recebedor;
- Após o usuário Doador enviar o Leite, o usuário Recebedor ao receber o Leite deve confirmar o recebimento do Leite, depois de confirmar o status mudará para Concluido finalizando o processo;
- O usuário Admin tem informações básicas sobre os usuários e doações.

## Aplicativo Mobile

[voltar ao início](#sumário)

Para visualizar o repositório do aplicativo em Flutter, acesse: https://github.com/rafaelcloud83/app-doacao-leite

## Tecnologias e Ferramentas utilizadas

[voltar ao início](#sumário)

* **Java 17**
* **Spring Boot 3**
* **Spring Security 6**
* **API REST**
* **IntelliJ IDEA Community Edition 2024**
* **PostgreSQL 15**
* **DBGate ou DBeaver**
* **Insomnia**
* **Maven**
* **Docker**
* **Git e GitHub**

## Configuração das variáveis de ambiente localmente

[voltar ao início](#sumário)

### Intellij IDEA:

Conforme a imagem abaixo, clique em "Edit Configurations...".

<p align="center">
<img alt="Logo" title="#Logo" src="https://github.com/rafaelcloud83/assets-repository/blob/main/appDoacao/ambiente01.png?raw=true">
</p>

Ao abrir a imagem abaixo, na linha "Environment variables", clique para editar as variáveis.

<p align="center">
<img alt="Logo" title="#Logo" src="https://github.com/rafaelcloud83/assets-repository/blob/main/appDoacao/ambiente02.png?raw=true">
</p>

Ao abrir a imagem abaixo crie 4 variáveis.
- DB_USER - com o usuário do PostgreSQL;
- DB_PASSWORD - com a senha do PostgreSQL;
- DB_URL - com o endereço do PostgreSQL;
- JWTSECRET - com a chave para gerar o token JWT;

<p align="center">
<img alt="Logo" title="#Logo" src="https://github.com/rafaelcloud83/assets-repository/blob/main/appDoacao/ambiente03.png?raw=true">
</p>

Clique em OK, Apply e OK para fechar.

## Configuração do application.properties

[voltar ao início](#sumário)

Foram criados 3 arquivos `application.properties`. Um em comum com todos, um para ambiente de desenvolvimento e um para ambiente de produção.

### application.properties

IMPORTANTE: Para trocar o profile que será executado, basta alterar a linha "spring.profiles.active=dev" e alterar entre dev ou prod.

```text
spring.application.name=doacao-leite
spring.profiles.active=dev

jwt.secret=${JWTSECRET:mysecretkey}
jwt.expiration=${JWTEXPIRATION:168}

logging.level.org.springframework.web=DEBUG
server.tomcat.max-http-header-size=16384
```

### application-dev.properties

Executar com o banco de dados PostgreSQL.

```text
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/doacao}
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
```

### application-prod.properties

IMPORTANTE: Executar esse profile quando for subir o projeto pra produção, lembre-se de criar as 3 variáveis de ambiente no servidor de acordo com os dados do PostgreSQL no servidor.

```text
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=update

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

## Execução do projeto localmente

[voltar ao início](#sumário)

IMPORTANTE: Por padrão o repositório está com o arquivo [application.properties](#applicationproperties) com o profile dev, lembre-se de alterar caso queira executar outro profile.

Para rodar a aplicação, será necessário ter instalado:

* **Git**
* **Java 17**
* **Maven - utilizei a versão 3.9.9**
* **Docker - utilizei a versão 27.3.1**
* **Docker Compose - utilizei a versão 2.15.1**

### com execução manual do jar

Primeiramente clone o projeto com o comando:

- Clonar com HTTPS

```shell
git clone https://github.com/rafaelcloud83/backend-doacao-leite.git
```

Depois de clonar o projeto, realize o `build` sem os testes da aplicação indo no diretório raiz e executando o comando:

```shell
./mvnw clean install -DskipTests
```

Depois de realizar o `build da aplicação`, será necessário subir o PostgreSQL com o Docker Compose utilizando o comando:

```shell
docker-compose -f docker-compose-postgresql.yaml up -d
```

Depois de subir o PostgreSQL, execute a aplicação com o comando:

```shell
java -jar target/doacao-leite-0.0.1.jar
```

### com imagem no Docker Hub

Para executar o projeto com a imagem no Docker Hub, basta executar o comando:

```shell
docker-compose up -d
```

<br>

## Endpoints

[voltar ao início](#sumário)

No diretório raiz do projeto, existe um arquivo `Insomnia.json` com o backup dos endpoints.

URL base(baseURL): http://localhost:8080

<br>

### Públicos

[voltar ao início](#sumário)

Nos endpoints públicos, nenhuma autenticação é necessária.

<br>

**POST** - baseURL/users/create
- Cria um novo usuário.

Requisição:
```json
{
  "name" : "Maria",
  "email" : "maria@email.com",
  "password" : "123",
  "phone": "19999999999",
  "address": "rua do rio",
  "role" : "RECEBEDOR"
}
```
Resposta: 201

<br>

**POST** - baseURL/auth/login
- Realiza o login do usuário e retorna um token.

Requisição:
```json
{
  "email" : "maria@email.com",
  "password" : "123"
}
```
Resposta: 201

<br>

### Usuários

[voltar ao início](#sumário)

Nos endpoints de Usuários ou Users, a autenticação por token é necessária.

<br>

**PUT** - baseURL/users/update
- Altera um usuário.
- Requer autenticação com role Recebedor ou Doador.

Requisição:
```json
{
  "id" : 3,
  "name" : "Maria Silva",
  "email" : "maria@email.com",
  "password" : "$2a$10$nRq7whojCsDWOayhjEvYZevt/BtvvX7IPSfb5EyD7WqoW3fz4iGTu",
  "phone": "19981821234",
  "address": "rua braga",
  "active": true,
  "role" : "RECEBEDOR"
}
```
Resposta: 200

<br>

**GET** - baseURL/users/{id}
- Retorna um usuário pelo id.
- Requer autenticação com role Recebedor ou Doador.

Resposta: 200

<br>

**GET** - baseURL/users
- Retorna todos os usuários.
- Requer autenticação com role Admin.

Resposta: 200

<br>

**GET** - baseURL/users/count
- Retorna o total de usuários.
- Requer autenticação com role Admin.

Resposta: 200

<br>

**GET** - baseURL/users/count/{role}
- Retorna o total de usuários pelo perfil de acordo com a role.
- Requer autenticação com role Admin.

Resposta: 200

<br>

**DELETE** - baseURL/users/{id}
- Deleta um usuário pelo id.
- Requer autenticação com role Admin.

Resposta: 204

<br>

### Pedidos

[voltar ao início](#sumário)

Nos endpoints de Pedidos ou Orders, a autenticação por token é necessária.

<br>

**POST** - baseURL/orders/create
- Cria um novo pedido de doação. Só usuário Recebedor pode criar um pedido de doação.
- Requer autenticação com role Recebedor.

Requisição:
```json
{
  "productName" : "leite nan",
  "estimatedPrice": "70,90",
  "receiver" : {
    "id" : 3
  }
}
```
Resposta: 201

<br>

**PUT** - baseURL/orders/update
- Altera um pedido de doação. 
- Requer autenticação com role Recebedor ou Doador.

Requisição:
- Alterado pelo Recebedor. Aguardando doação.
```json
{
  "id" : 1,
  "productName" : "leite nan",
  "estimatedPrice": "79,90",
  "status": "AGUARDANDO",
  "receiver" : {
    "id" : 3
  },
  "donor" : {
    "id" : 1
  }
}
```
Resposta: 200

<br>

Requisição:
- Alterado pelo Doador. Doado mas ainda aguardando confirmação do Recebedor.
```json
{
  "id" : 1,
  "productName" : "leite nan",
  "estimatedPrice": "79,90",
  "status": "DOADO",
  "receiver" : {
    "id" : 3
  },
  "donor" : {
    "id" : 4
  }
}
```
Resposta: 200

<br>

Requisição:
- Alterado pelo Recebedor. Recebedor confirmou o recebimento do Leite.
```json
{
  "id" : 1,
  "productName" : "leite nan",
  "estimatedPrice": "79,90",
  "status": "CONCLUIDO",
  "receiver" : {
    "id" : 3
  },
  "donor" : {
    "id" : 4
  }
}
```
Resposta: 200

<br>

**GET** - baseURL/orders/{id}
- Retorna um pedido de doação pelo id.
- Requer autenticação com role Recebedor ou Doador.

Resposta: 200

<br>

**GET** - baseURL/orders/receiver/{id}
- Retorna todos os pedido de doação pelo id do Recebedor.
- Requer autenticação com role Recebedor.

Resposta: 200

<br>

**GET** - baseURL/orders/donor/{id}
- Retorna todos os pedido de doação pelo id do Doador.
- Requer autenticação com role Doador.

Resposta: 200

<br>

**GET** - baseURL/orders/status/{status}
- Retorna todos os pedido de doação com o status (AGUARDANDO, DOADO ou CONCLUIDO).
- Requer autenticação com role Doador.

Resposta: 200

<br>

**GET** - baseURL/orders
- Retorna todos os pedido de doação.
- Requer autenticação com role Admin.

Resposta: 200

<br>

**GET** - baseURL/orders/count
- Retorna o total de pedidos de doação.
- Requer autenticação com role Admin.

Resposta: 200

<br>

**GET** - baseURL/orders/count/{status}
- Retorna o total de pedidos de doação de acordo com o status.
- Requer autenticação com role Admin.

Resposta: 200

<br>

**DELETE** - baseURL/orders/{id}
- Deleta um pedido de doação pelo id.
- Requer autenticação com role Admin.

Resposta: 204

<br>