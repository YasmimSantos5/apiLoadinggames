# API Loading Games

API REST desenvolvida em Java com Spring Boot para apoiar o projeto **Loading Games**. Atualmente, o projeto oferece funcionalidades básicas de cadastro, autenticação e listagem de usuários, além de um endpoint simples para teste da aplicação.

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Banco de dados H2
- Maven
- Docker

## Funcionalidades

- Cadastro de usuários
- Autenticação de usuários
- Listagem de usuários cadastrados
- Endpoint de teste da API
- Persistência de dados com JPA/Hibernate
- Console web do banco H2
- Execução da aplicação em container Docker

## Estrutura do projeto

```text
apiLoadinggames/
├── src/
│   ├── main/
│   │   ├── java/com/loadinggames/api/
│   │   │   ├── ApiApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── LoginController.java
│   │   │   │   └── TesteController.java
│   │   │   ├── model/
│   │   │   │   ├── Jogo.java
│   │   │   │   └── Login.java
│   │   │   └── repository/
│   │   │       └── LoginRepository.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── Dockerfile
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## Arquitetura

O projeto segue uma organização baseada nos principais componentes de uma aplicação Spring Boot:

- **Controller**: recebe as requisições HTTP e disponibiliza os endpoints da API.
- **Model**: representa as entidades persistidas no banco de dados.
- **Repository**: fornece acesso aos dados usando Spring Data JPA.
- **ApiApplication**: classe responsável por iniciar a aplicação.

### Entidades

#### Login

A entidade `Login` possui os seguintes campos:

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | Identificador gerado automaticamente |
| `usuario` | `String` | Nome ou identificador do usuário; deve ser único |
| `senha` | `String` | Senha utilizada na autenticação |

#### Jogo

A entidade `Jogo` possui os seguintes campos:

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | Identificador gerado automaticamente |
| `nome` | `String` | Nome do jogo |

> A entidade `Jogo` já está definida no modelo, mas ainda não possui controller ou repository próprio no estado atual do projeto.

## Endpoints

A aplicação utiliza a porta `8080` por padrão.

### Testar a API

```http
GET /teste
```

Resposta:

```text
Login
```

### Cadastrar usuário

```http
POST /cadastro
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "usuario": "usuario@example.com",
  "senha": "minha-senha"
}
```

A resposta contém o usuário salvo, incluindo o identificador gerado:

```json
{
  "id": 1,
  "usuario": "usuario@example.com",
  "senha": "minha-senha"
}
```

### Autenticar usuário

```http
POST /autenticar
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "usuario": "usuario@example.com",
  "senha": "minha-senha"
}
```

Respostas possíveis:

```text
sucesso
```

ou

```text
erro
```

### Listar usuários

```http
GET /listausuario
```

Retorna a lista de usuários cadastrados:

```json
[
  {
    "id": 1,
    "usuario": "usuario@example.com",
    "senha": "minha-senha"
  }
]
```

## Banco de dados

O projeto utiliza o H2 em modo arquivo. Os dados são armazenados no caminho:

```text
./data/loadinggames
```

Configurações principais:

- Usuário: `sa`
- Senha: vazia
- Porta da aplicação: `8080`
- `ddl-auto`: `update`
- Console H2: habilitado

### Acessar o console H2

Com a aplicação em execução, acesse:

```text
http://localhost:8080/h2-console
```

Utilize os seguintes dados:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:file:./data/loadinggames` |
| User Name | `sa` |
| Password | deixe vazio |

> Em ambientes compartilhados ou de produção, o console H2 e o acesso sem senha devem ser reconfigurados.

## Pré-requisitos

Para executar o projeto localmente, é necessário ter instalado:

- JDK 17 ou superior
- Maven 3.9 ou superior, ou utilizar o Maven Wrapper incluído
- Docker, caso queira executar a aplicação em container

## Executando localmente

Clone o repositório:

```bash
git clone https://github.com/YasmimSantos5/apiLoadinggames.git
cd apiLoadinggames
```

Execute usando o Maven Wrapper no Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```cmd
mvnw.cmd spring-boot:run
```

Também é possível executar com Maven instalado:

```bash
mvn spring-boot:run
```

Após iniciar, a API estará disponível em:

```text
http://localhost:8080
```

## Gerando o arquivo JAR

Para compilar o projeto e gerar o artefato executável:

```bash
./mvnw clean package
```

Para executar o JAR gerado:

```bash
java -jar target/api-0.0.1-SNAPSHOT.jar
```

## Executando com Docker

O projeto possui um `Dockerfile` com duas etapas:

1. Compilação usando Maven e Eclipse Temurin 17.
2. Execução usando uma imagem JRE do Eclipse Temurin 17.

Para criar a imagem:

```bash
docker build -t api-loadinggames .
```

Para iniciar o container:

```bash
docker run --name api-loadinggames -p 8080:8080 api-loadinggames
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Testando com cURL

Cadastrar usuário:

```bash
curl -X POST http://localhost:8080/cadastro \
  -H "Content-Type: application/json" \
  -d '{"usuario":"usuario@example.com","senha":"minha-senha"}'
```

Autenticar usuário:

```bash
curl -X POST http://localhost:8080/autenticar \
  -H "Content-Type: application/json" \
  -d '{"usuario":"usuario@example.com","senha":"minha-senha"}'
```

Listar usuários:

```bash
curl http://localhost:8080/listausuario
```

Testar a aplicação:

```bash
curl http://localhost:8080/teste
```

## Testes

O projeto possui dependências de teste do Spring Boot e pode ser validado com:

```bash
./mvnw test
```

No estado atual, o Dockerfile utiliza `-DskipTests` durante a criação da imagem para gerar o pacote sem executar os testes.

## Pontos de atenção

- As senhas são armazenadas e comparadas em texto simples. Para uso real, recomenda-se utilizar hash seguro, como BCrypt.
- O endpoint `/listausuario` retorna os dados de senha dos usuários e deve ser protegido ou alterado.
- A autenticação retorna apenas as mensagens `sucesso` ou `erro`; ainda não há token ou sessão.
- O endpoint `/cadastro` deve tratar tentativas de cadastro com usuário duplicado.
- Recomenda-se adicionar validação de entrada, tratamento global de exceções e códigos HTTP apropriados.
- O endpoint de jogos e a persistência da entidade `Jogo` ainda podem ser implementados.

## Possíveis evoluções

- Criar `JogoRepository` e `JogoController`.
- Implementar operações CRUD para jogos.
- Adicionar autenticação com JWT.
- Criptografar senhas com BCrypt.
- Criar DTOs para evitar exposição direta das entidades.
- Adicionar documentação OpenAPI/Swagger.
- Criar testes unitários e de integração.
- Configurar perfis separados para desenvolvimento, testes e produção.

## Licença

Nenhuma licença foi definida no repositório até o momento.

## Repositório

[github.com/YasmimSantos5/apiLoadinggames](https://github.com/YasmimSantos5/apiLoadinggames)
