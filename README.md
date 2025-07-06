MovieFlix - API de Controle de Cadastro de Catálogo de Filmes
Projeto Java com Spring Boot para desenvolvimento de uma API para controle de cadastro de catálogo de filmes.

Link do Repositório
Repositório no GitHub

Tecnologias, Ferramentas e Bibliotecas Utilizadas
Java 17

Spring Boot 3

Spring Web

Spring Security

JWT (JSON Web Token)

xml
Copiar
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>4.4.0</version>
</dependency>
Spring Data JPA

Validation (para validações de dados)

PostgreSQL (banco de dados)

Flyway (para controle de migrações do banco de dados)

Lombok (para redução de boilerplate code)

Exceptions (tratamento de exceções customizadas)

Documentação Open API Swagger

xml
Copiar
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
Configuração Inicial do Projeto
Criar o projeto no Spring Initializr

Acesse Spring Initializr e configure as dependências:

Spring Web

Spring Security

Spring Data JPA

PostgreSQL Driver

Flyway Migration

Lombok

Validation

Open API (Swagger)

Importar no IntelliJ IDEA (ou outra IDE).

Renomear o arquivo application.properties para application.yaml.

Configurar application.yaml com as seguintes configurações:

yaml
Copiar
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
Estrutura do Projeto
Diagrama de Classe (se você deseja criar diagramas UML, esse seria o padrão):

Category

Streaming

Movie

User

Recursos Implementados
1. Cadastro de Categorias (Category)
Criar a entidade Category.

Criar arquivo de migração SQL:

sql
Copiar
CREATE TABLE category (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL
);
Criar a interface CategoryRepository para operações CRUD.

Criar a classe CategoryService.

Criar a classe CategoryController com os seguintes endpoints:

Listar todas as categorias.

Salvar nova categoria.

Buscar categoria por ID.

Deletar categoria.

2. Cadastro de Streaming
Criar a entidade Streaming.

Criar arquivo de migração SQL:

sql
Copiar
CREATE TABLE streaming (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL
);
Criar a interface StreamingRepository.

Criar a classe StreamingService.

Criar a classe StreamingController com os seguintes endpoints:

Listar todos os serviços de streaming.

Salvar novo serviço de streaming.

Buscar serviço de streaming por ID.

Deletar serviço de streaming.

3. Cadastro de Filme (Movie)
Criar a entidade Movie.

Criar arquivo de migração SQL:

sql
Copiar
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
Criar a interface MovieRepository.

Criar a classe MovieService.

Criar a classe MovieController com os seguintes endpoints:

Listar filmes.

Salvar filme.

Alterar filme.

Buscar filme por ID.

Deletar filme.

Retornar filmes por categoria.

4. Cadastro de Usuários (User)
Criar a entidade User.

Criar arquivo de migração SQL:

sql
Copiar
CREATE TABLE users (
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);
Criar a interface UserRepository.

Criar a classe UserService.

Criar a classe UserController:

Endpoint para registrar novo usuário.

Implementação de Segurança com Spring Security
Criar a classe SecurityConfig:

java
Copiar
public class SecurityConfig {

    private final SecurityFilter securityFilter;

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
Criar a classe TokenService para criar e validar o token JWT.

Criar a classe JWTUserData para armazenar as informações do usuário.

Ajustar a entidade User para implementar UserDetails.

Criar o AuthService que implementa UserDetailsService.

Criar o SecurityFilter para interceptar as requisições e validar o token JWT.

Tratamento de Exceções e Validações
Adicionar validação nas requisições.

Criar o ApplicationControllerAdvice para tratar exceções globais.

Criar exceções customizadas, como UsernameOrPasswordInvalidException.

Documentação OpenAPI Swagger
Adicionar dependência do Springdoc OpenAPI:

xml
Copiar
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
Configurar Swagger para expor os endpoints da API:

java
Copiar
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("MovieFlix API").version("1.0")
            .description("API para cadastro e controle de filmes, categorias e serviços de streaming."));
    }
}
Acessar a documentação via Swagger: http://localhost:8080/swagger-ui

Conclusão e Passos Finais
Testar a API utilizando Swagger ou Postman.

Configuração do banco PostgreSQL e migrações com Flyway.

Proteger endpoints com Spring Security e JWT.

Verificar funcionamento de todos os endpoints com categorias, streaming, filmes e usuários.

Observação
Esse projeto foi desenvolvido como parte de um curso de Java 10x e é um exemplo de como estruturar uma API robusta utilizando Spring Boot e integração com um banco de dados PostgreSQL.

Para mais informações, acesse o repositório no GitHub ou entre em contato para dúvidas sobre a implementação! 🚀






Perguntar ao ChatGPT
