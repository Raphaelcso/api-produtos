# API de Produtos

API REST desenvolvida com Spring Boot para realizar o gerenciamento de produtos.

O projeto permite cadastrar, listar, consultar, atualizar e excluir produtos, utilizando PostgreSQL como banco de dados.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4
- Spring Data JPA
- PostgreSQL
- Maven
- Docker e Docker Compose
- Spring Boot Actuator
- JUnit 5 e Mockito

## Estrutura do projeto

```text
api-produtos/
├── .mvn/
│   └── wrapper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/exemplo/produtos/
│   │   │       ├── controller/
│   │   │       │   └── ProdutoController.java
│   │   │       ├── model/
│   │   │       │   └── Produto.java
│   │   │       ├── repository/
│   │   │       │   └── ProdutoRepository.java
│   │   │       ├── service/
│   │   │       │   └── ProdutoService.java
│   │   │       └── ApiProdutosApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/
│           └── com/exemplo/produtos/
│               ├── service/
│               │   └── ProdutoServiceTest.java
│               └── ApiProdutosApplicationTests.java
├── .env.example
├── .gitignore
├── compose.yaml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── RELATORIO.md
```

### Organização das camadas

- **controller:** recebe as requisições HTTP e disponibiliza os endpoints da API.
- **model:** contém a entidade Produto e suas propriedades.
- **repository:** realiza a comunicação com o banco de dados por meio do Spring Data JPA.
- **service:** concentra as regras de negócio e operações dos produtos.
- **resources:** armazena as configurações da aplicação e dos perfis de execução.
- **test:** contém os testes automatizados da aplicação e da camada de serviço.

## Funcionalidades

- Cadastrar produtos.
- Listar todos os produtos.
- Consultar produto por ID.
- Atualizar produtos.
- Excluir produtos.
- Validar os dados recebidos.
- Retornar erro HTTP 404 quando um produto não for encontrado.
- Monitorar a saúde da aplicação pelo Actuator.

## Configuração do banco de dados

O projeto utiliza PostgreSQL. As credenciais são configuradas por variáveis de ambiente, evitando deixar informações sensíveis diretamente no código.

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```properties
DB_NAME=produtos
DB_USERNAME=postgres
DB_PASSWORD=postgres
DB_URL=jdbc:postgresql://postgres:5432/produtos
```

Esses valores são destinados ao ambiente local de desenvolvimento.

## Executando com Docker

Com o Docker Desktop aberto, execute na raiz do projeto:

```bash
docker compose up -d --build
```

Para verificar os containers:

```bash
docker compose ps
```

Para consultar os logs da API:

```bash
docker compose logs -f api
```

Para encerrar os containers:

```bash
docker compose down
```

O banco de dados utiliza um volume persistente para preservar os dados entre reinicializações.

## Endpoints da API

A API disponibiliza os seguintes endpoints:

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| POST | `/produtos` | Cadastrar um produto |
| GET | `/produtos` | Listar todos os produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| PUT | `/produtos/{id}` | Atualizar um produto |
| DELETE | `/produtos/{id}` | Excluir um produto |

## Monitoramento

O Spring Boot Actuator disponibiliza o endpoint de saúde da aplicação:

```text
http://localhost:8080/actuator/health
```

O resultado permite verificar o estado da API e a conexão com o PostgreSQL.

## Executando os testes

Para executar os testes automatizados com Maven Wrapper:

No Windows PowerShell:

```powershell
.\mvnw.cmd test
```

Para gerar o pacote JAR:

```powershell
.\mvnw.cmd clean package
```

O arquivo executável será gerado na pasta `target`.

## Execução do JAR

Com o PostgreSQL disponível e as variáveis de ambiente configuradas, execute:

```powershell
java -jar target\api-produtos-0.0.1-SNAPSHOT.jar
```