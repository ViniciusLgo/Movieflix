# **MovieFlix - API de Controle de Cadastro de Catálogo de Filmes 🎬**

**Projeto Java com Spring Boot para desenvolvimento de uma API robusta que gerencia o cadastro e controle de um catálogo de filmes, incluindo categorias, serviços de streaming e usuários.**

---

### **🔗 Link do Repositório**

[Repositório no GitHub](https://github.com/renanlessa/miniature-fortnight.git)

---

## **🛠 Tecnologias, Ferramentas e Bibliotecas Utilizadas**

* **Java 17**

* **Spring Boot 3**

* **Spring Web** (para construção da API RESTful)

* **Spring Security** (para autenticação e autorização)

* **JWT** (JSON Web Token para segurança de endpoints)

  ```xml
  <dependency>
      <groupId>com.auth0</groupId>
      <artifactId>java-jwt</artifactId>
      <version>4.4.0</version>
  </dependency>
  ```

* **Spring Data JPA** (para persistência de dados no banco)

* **Validation** (para validação de dados de entrada)

* **PostgreSQL** (banco de dados relacional)

* **Flyway** (para controle de migrações do banco de dados)

* **Lombok** (para reduzir código boilerplate)

* **Exceptions** (tratamento de exceções customizadas)

* **Swagger/OpenAPI** (para documentação automática da API)

  ```xml
  <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.2.0</version>
  </dependency>
  ```

---

## **⚙️ Configuração Inicial do Projeto**

1. **Criar o projeto no Spring Initializr**
   Acesse [Spring Initializr](https://start.spring.io/) e selecione as dependências:

    * **Spring Web**
    * **Spring Security**
    * **Spring Data JPA**
    * **PostgreSQL Driver**
    * **Flyway Migration**
    * **Lombok**
    * **Validation**
    * **Open API (Swagger)**

2. **Importar o projeto no IntelliJ IDEA** (ou outra IDE de sua preferência).

3. **Renomear o arquivo `application.properties` para `application.yaml`**.

4. **Configuração do `application.yaml`**:

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
   ```

---

## **📂 Estrutura do Projeto**

### **🔹 Diagrama de Classe (UML)**

O diagrama de classe define as entidades principais:

* **Category**
* **Streaming**
* **Movie**
* **User**

---

## **🚀 Recursos Implementados**

### **1. Cadastro de Categorias (Category)**

* **Entidade `Category`** criada com a estrutura:

  ```sql
  CREATE TABLE category (
      id serial PRIMARY KEY,
      name varchar(100) NOT NULL
  );
  ```

* **Endpoints:**

    * Listar todas as categorias.
    * Salvar nova categoria.
    * Buscar categoria por ID.
    * Deletar categoria.

---

### **2. Cadastro de Streaming (Streaming)**

* **Entidade `Streaming`** criada com a estrutura:

  ```sql
  CREATE TABLE streaming (
      id serial PRIMARY KEY,
      name varchar(100) NOT NULL
  );
  ```

* **Endpoints:**

    * Listar todos os serviços de streaming.
    * Salvar novo serviço de streaming.
    * Buscar serviço de streaming por ID.
    * Deletar serviço de streaming.

---

### **3. Cadastro de Filmes (Movie)**

* **Entidade `Movie`** criada com a estrutura:

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
      CONSTRAINT fk_movie_category_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
      CONSTRAINT fk_movie_category_category FOREIGN KEY(category_id) REFERENCES category(id)
  );

  CREATE TABLE movie_streaming (
      movie_id INTEGER,
      streaming_id INTEGER,
      CONSTRAINT fk_movie_streaming_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
      CONSTRAINT fk_movie_streaming_streaming FOREIGN KEY(service_id) REFERENCES streaming(id)
  );
  ```

* **Endpoints:**

    * Listar filmes.
    * Salvar filme.
    * Alterar filme.
    * Buscar filme por ID.
    * Deletar filme.
    * Retornar filmes por categoria.

---

### **4. Cadastro de Usuários (User)**

* **Entidade `User`** criada com a estrutura:

  ```sql
  CREATE TABLE users (
      id serial PRIMARY KEY,
      name varchar(255) NOT NULL,
      email varchar(255) NOT NULL,
      password varchar(255) NOT NULL
  );
  ```

* **Endpoints:**

    * Registrar novo usuário.

---

## **🔐 Implementação de Segurança com Spring Security**

1. **Classe `SecurityConfig`** para configuração de segurança.

   ```java
   public class SecurityConfig {

       @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           ...
       }

       @Bean
       public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
           return authenticationConfiguration.getAuthenticationManager();
       }

       @Bean
       public PasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
       }
   }
   ```

2. **Token JWT** para autenticação:

    * **Classe `TokenService`** para criação e validação de tokens.
    * **Classe `JWTUserData`** para armazenar as informações do usuário.
    * **Classe `AuthService`** implementando `UserDetailsService` para o login.

3. **Classe `SecurityFilter`** para interceptação e validação do JWT.

---

## **⚠️ Tratamento de Exceções e Validações**

* **Validações nas requisições**.
* **Exceções customizadas**, como `UsernameOrPasswordInvalidException`.
* **Classe `ApplicationControllerAdvice`** para tratar exceções globais.

---

## **📑 Documentação OpenAPI Swagger**

1. **Dependência do Springdoc OpenAPI**:

   ```xml
   <dependency>
       <groupId>org.springdoc</groupId>
       <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
       <version>2.2.0</version>
   </dependency>
   ```

2. **Configuração do Swagger** para exposição dos endpoints da API:

   ```java
   @Configuration
   @EnableOpenApi
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

3. **Acessar a documentação via Swagger**:

    * Acesse [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui) para visualizar e testar os endpoints da API.

---

## **🔍 Conclusão e Passos Finais**

* **Testar a API** utilizando o Swagger ou Postman.
* **Configuração do banco PostgreSQL** e **migrações com Flyway**.
* **Proteger endpoints com Spring Security e JWT**.
* **Verificar o funcionamento de todos os endpoints**, como categorias, streaming, filmes e usuários.

---

## **📌 Observações**

Esse projeto foi desenvolvido como parte de um curso de **Java 10x** e é um excelente exemplo de como estruturar uma API robusta utilizando **Spring Boot**, integração com **PostgreSQL**, e **autenticação via JWT**.

Para mais informações e contribuições, acesse o repositório no GitHub ou entre em contato para dúvidas sobre a implementação!

