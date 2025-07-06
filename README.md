
---

````markdown
# 🎬 MovieFlix — API de Catálogo de Filmes

Projeto em **Java** com **Spring Boot** para desenvolvimento de uma API robusta que gerencia o cadastro e controle de um catálogo de filmes, incluindo categorias, serviços de streaming e usuários.

---

## 🔗 Repositório

[👉 Acesse o repositório no GitHub](#)

---

## 🛠 Tecnologias & Ferramentas

- **Java 17**
- **Spring Boot 3**
- **Spring Web** — Para construção da API RESTful.
- **Spring Security** — Para autenticação e autorização.
- **JWT** — Segurança dos endpoints.
- **Spring Data JPA** — Persistência de dados.
- **PostgreSQL** — Banco de dados relacional.
- **Flyway** — Controle de migrações de banco.
- **Lombok** — Redução de boilerplate.
- **Validation** — Validação de dados de entrada.
- **Swagger/OpenAPI** — Documentação automática da API.
- **Custom Exceptions** — Tratamento centralizado de erros.

---

## ⚙️ Configuração Inicial

### ✅ Criar projeto no Spring Initializr

**Dependências principais:**
- Spring Web
- Spring Security
- Spring Data JPA
- PostgreSQL Driver
- Flyway Migration
- Lombok
- Validation
- Springdoc OpenAPI

**Passos:**
1. Importar no IntelliJ IDEA (ou IDE de sua preferência).
2. Renomear `application.properties` para `application.yaml`.

```yaml
spring:
  application:
    name: movieflix
  datasource:
    url: jdbc:postgresql://localhost:5432/movieflix
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
  flyway:
    enabled: true
````

---

## 📂 Estrutura do Projeto

### 🗂️ Principais Entidades

* **Category**
* **Streaming**
* **Movie**
* **User**

---

## 🚀 Recursos Implementados

### 🎞️ Categoria (Category)

```sql
CREATE TABLE category (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL
);
```

**Endpoints:**

* Listar todas as categorias
* Salvar nova categoria
* Buscar categoria por ID
* Deletar categoria

---

### 📺 Streaming

```sql
CREATE TABLE streaming (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL
);
```

**Endpoints:**

* Listar todos os serviços
* Salvar novo serviço
* Buscar por ID
* Deletar serviço

---

### 🎥 Filme (Movie)

```sql
CREATE TABLE movie (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE movie_category (
    movie_id INTEGER,
    category_id INTEGER,
    FOREIGN KEY(movie_id) REFERENCES movie(id),
    FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE movie_streaming (
    movie_id INTEGER,
    streaming_id INTEGER,
    FOREIGN KEY(movie_id) REFERENCES movie(id),
    FOREIGN KEY(streaming_id) REFERENCES streaming(id)
);
```

**Endpoints:**

* Listar filmes
* Salvar novo filme
* Editar filme
* Buscar por ID
* Deletar filme
* Buscar filmes por categoria

---

### 👤 Usuário (User)

```sql
CREATE TABLE users (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);
```

**Endpoints:**

* Registrar novo usuário

---

## 🔐 Segurança

* Configuração com **Spring Security**.
* Autenticação via **JWT**.
* Classes principais:

  * `SecurityConfig`
  * `TokenService`
  * `AuthService`
  * `SecurityFilter`
  * `JWTUserData`

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

## ⚠️ Tratamento de Erros & Validações

* Validação das requisições com annotations (`@Valid`).
* Exceções customizadas, como `UsernameOrPasswordInvalidException`.
* Classe `ApplicationControllerAdvice` para centralizar tratamento global.

---

## 📑 Documentação com Swagger

### Dependência

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

### Configuração

```java
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("MovieFlix API")
            .version("1.0")
            .description("API para cadastro e controle de filmes, categorias e serviços de streaming."));
    }
}
```

### Acessar

[http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

---

## ✅ Passos Finais

* Testar a API via Swagger ou Postman.
* Validar migrações Flyway e estrutura do banco PostgreSQL.
* Confirmar segurança dos endpoints com JWT.
* Garantir que todos os fluxos (categorias, filmes, streaming, usuários) estejam funcionando corretamente.

---

## 💬 Observações

Esse projeto foi desenvolvido como parte do **curso Java 10x**, servindo como exemplo completo de uma API robusta com Spring Boot, PostgreSQL e segurança JWT.

---
