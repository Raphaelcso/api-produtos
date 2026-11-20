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