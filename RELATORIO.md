# Relatório – Desenvolvimento e Testes de Aplicações Spring Boot

## 1. Introdução

Este relatório apresenta o desenvolvimento de uma API REST utilizando Spring Boot, com foco na implementação de operações CRUD, testes automatizados, integração com banco de dados PostgreSQL e execução em ambiente Docker.

A atividade tem como objetivo aplicar os conceitos estudados sobre desenvolvimento, testes, empacotamento e monitoramento de aplicações Spring Boot.

## 2. Objetivo do projeto

O projeto consiste no desenvolvimento de uma API REST para gerenciamento de produtos, permitindo cadastrar, consultar, atualizar e excluir registros.

A aplicação foi organizada em camadas, seguindo uma estrutura que separa as responsabilidades:

- **Model:** representa a entidade Produto.
- **Repository:** realiza o acesso aos dados utilizando Spring Data JPA.
- **Service:** concentra as regras de negócio.
- **Controller:** disponibiliza os endpoints REST para comunicação com a API.

## 3. Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.10
- Spring Web
- Spring Data JPA
- PostgreSQL 16
- Maven
- JUnit 5
- Mockito
- Docker e Docker Compose
- Spring Boot Actuator
- Git e GitHub
- Visual Studio Code

## 4. Desenvolvimento da API

Foi criada a entidade `Produto`, contendo os seguintes atributos:

- ID: identificador único gerado automaticamente.
- Nome: nome do produto.
- Descrição: informações adicionais sobre o produto.
- Preço: valor monetário do produto.
- Quantidade: quantidade disponível em estoque.

Foram utilizadas validações para impedir o cadastro de dados inválidos, como nome vazio, preço igual ou inferior a zero e quantidade negativa.

### 4.1 Operações CRUD

A API disponibiliza as seguintes operações:

| Método HTTP | Endpoint | Funcionalidade |
|---|---|---|
| POST | `/produtos` | Cadastrar produto |
| GET | `/produtos` | Listar produtos |
| GET | `/produtos/{id}` | Consultar produto por ID |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Excluir produto |

### 4.2 Tratamento de erros

Foi implementado o tratamento de registros inexistentes na camada Service.

Quando uma consulta, atualização ou exclusão solicita um produto que não existe, a aplicação lança uma exceção `ResponseStatusException`, retornando o código HTTP 404 (Not Found).

Dessa forma, a API informa corretamente quando o recurso solicitado não foi encontrado.

## 5. Testes automatizados

Foram desenvolvidos testes unitários para a camada Service utilizando JUnit 5 e Mockito.

O Mockito foi utilizado para simular o comportamento do `ProdutoRepository`, permitindo testar as regras de negócio sem depender diretamente do banco de dados.

Foram utilizados:

- `@Mock`: cria um objeto simulado do repositório.
- `@InjectMocks`: injeta o objeto simulado na classe de serviço.
- `assertEquals()`: verifica se os valores retornados são os esperados.
- `assertNotNull()`: verifica se o resultado não é nulo.
- `assertThrows()`: verifica se uma exceção é lançada.
- `verify()`: confirma se determinado método do repositório foi chamado.

### 5.1 Cenários testados

Foram implementados quatro testes unitários na classe `ProdutoServiceTest`:

1. **Sucesso:** salvar um produto e verificar os dados retornados.
2. **Sucesso:** buscar um produto existente pelo ID.
3. **Erro:** tentar buscar um produto inexistente e verificar o erro 404.
4. **Erro:** tentar atualizar um produto inexistente e verificar que o método de salvamento não foi chamado.

Também foi executado o teste de contexto da aplicação.

### 5.2 Padrão Arrange, Act e Assert

Os testes foram organizados seguindo o padrão AAA:

- **Arrange (Preparar):** configurar os objetos e os comportamentos simulados necessários para o teste.
- **Act (Executar):** chamar o método que será testado.
- **Assert (Verificar):** conferir se o resultado corresponde ao comportamento esperado.

Esse padrão facilita a leitura, organização e manutenção dos testes.

### 5.3 Resultado dos testes

A execução foi realizada pelo Maven Wrapper utilizando o comando:

```powershell
.\mvnw.cmd test
```

O resultado obtido foi:

- Total de testes: 5.
- Falhas: 0.
- Erros: 0.
- Testes ignorados: 0.
- Resultado da execução: BUILD SUCCESS.

Os resultados demonstram que os cenários implementados foram executados com sucesso.

## 6. MockMvc e cobertura de testes

### 6.1 MockMvc

O MockMvc é um recurso do Spring utilizado para testar endpoints HTTP da aplicação sem precisar iniciar um servidor web completo.

Com ele, é possível simular requisições como GET, POST, PUT e DELETE, verificando:

- Código de resposta HTTP.
- Conteúdo retornado pela API.
- Cabeçalhos da resposta.
- Comportamento dos endpoints.

Neste projeto, os testes automatizados implementados foram testes unitários da camada Service, utilizando JUnit e Mockito. O MockMvc é uma alternativa para complementar esses testes com verificações da camada Controller.

### 6.2 Cobertura de testes

A cobertura de testes representa a quantidade de código que foi executada durante os testes automatizados.

Ela pode ser utilizada para identificar partes da aplicação que ainda não foram testadas.

Entretanto, uma cobertura elevada não garante que o sistema esteja livre de erros. Um teste pode executar determinado trecho de código sem verificar corretamente todos os resultados possíveis.

Por isso, além da quantidade de código executada, é importante avaliar a qualidade dos cenários de teste, incluindo situações de sucesso, falha e valores inesperados.

## 7. Empacotamento e execução da aplicação

O empacotamento foi realizado utilizando o Maven Wrapper, por meio do comando:

```powershell
.\mvnw.cmd clean package
```

Esse comando limpa os arquivos de compilação anteriores, executa os testes automatizados e gera o arquivo JAR executável na pasta `target`.

A aplicação foi executada diretamente pelo Java, fora da IDE, utilizando:

```powershell
java -jar target\api-produtos-0.0.1-SNAPSHOT.jar
```

A inicialização foi concluída com sucesso, utilizando a porta 8080 e estabelecendo conexão com o banco PostgreSQL.

### 7.1 Variáveis de ambiente

As configurações de conexão com o banco de dados foram externalizadas por meio de variáveis de ambiente:

- `DB_URL`: endereço de conexão com o PostgreSQL.
- `DB_USERNAME`: usuário do banco.
- `DB_PASSWORD`: senha do banco.

Essa abordagem evita deixar credenciais diretamente no código-fonte.

### 7.2 Perfis Spring

Foram criados dois perfis de configuração:

- **dev:** utilizado durante o desenvolvimento, com exibição de comandos SQL e atualização automática da estrutura do banco.
- **prod:** utilizado em produção, com comandos SQL desabilitados e validação da estrutura do banco.

A seleção do perfil é realizada pela variável de ambiente `SPRING_PROFILES_ACTIVE`.

Os dois perfis foram testados e confirmados durante a execução da aplicação em Docker.

## 8. Monitoramento com Spring Boot Actuator

Foi utilizado o Spring Boot Actuator para monitorar a saúde da aplicação.

O endpoint disponibilizado foi:

```text
http://localhost:8080/actuator/health
```

Durante os testes, o endpoint retornou o status geral `UP`, indicando que a aplicação estava funcionando corretamente.

Também foi confirmada a conexão com o PostgreSQL, cujo componente `db` apresentou status `UP`.

O Actuator permite acompanhar informações importantes sobre a disponibilidade e o funcionamento da aplicação.

## 9. Conteinerização com Docker

A aplicação foi preparada para execução em containers utilizando Docker e Docker Compose.

Foi criado um `Dockerfile` baseado na imagem `eclipse-temurin:21-jre`, responsável por executar o arquivo JAR da aplicação.

Também foi criado o arquivo `compose.yaml`, responsável por configurar e iniciar dois serviços:

- **API:** executa a aplicação Spring Boot na porta 8080.
- **PostgreSQL:** disponibiliza o banco de dados na porta 5432.

### 9.1 Inicialização dos containers

Os serviços foram iniciados utilizando o comando:

```powershell
docker compose up -d --build
```

A verificação dos containers foi realizada com:

```powershell
docker compose ps
```

Foi confirmado que a API estava em execução e que o PostgreSQL apresentava estado saudável (`healthy`).

### 9.2 Persistência dos dados

Foi configurado um volume Docker chamado `postgres_data`, responsável por armazenar os dados do PostgreSQL fora do ciclo de vida dos containers.

Para verificar a persistência, foi realizado o seguinte procedimento:

1. Cadastrar um produto na API.
2. Parar e remover os containers com `docker compose down`, sem utilizar a opção `-v`.
3. Iniciar novamente os serviços.
4. Consultar o produto anteriormente cadastrado.

O produto continuou disponível após a reinicialização, comprovando o funcionamento do volume persistente.

### 9.3 Segurança das configurações

As credenciais do banco foram configuradas por variáveis de ambiente, utilizando o arquivo `.env` local.

O arquivo `.env` foi incluído no `.gitignore` para evitar o envio de credenciais ao GitHub.

Também foi criado o arquivo `.env.example`, contendo os nomes das variáveis necessárias para configurar o ambiente sem expor senhas reais.

## 10. Evidências da execução

Durante o desenvolvimento e os testes, foram registradas evidências das seguintes etapas:

1. Inicialização da aplicação Spring Boot.
2. Verificação do Actuator com status `UP`.
3. Cadastro de produtos pela API.
4. Listagem de produtos.
5. Consulta de produto por ID.
6. Atualização de produto.
7. Exclusão de produto.
8. Tratamento de erro HTTP 404.
9. Execução dos testes automatizados.
10. Construção da imagem Docker.
11. Containers em execução.
12. Logs da aplicação no Docker.
13. Funcionamento do perfil `prod`.
14. Execução do arquivo JAR fora da IDE.

As capturas de tela demonstram o funcionamento das operações e das configurações implementadas.

## 11. Repositório GitHub

O código-fonte completo do projeto está disponível publicamente no GitHub:

**Link do repositório:**

https://github.com/Raphaelcso/api-produtos

O repositório contém o código-fonte, os testes automatizados, os arquivos de configuração do Docker, o README e o exemplo de configuração das variáveis de ambiente.

## 12. Conclusão

O desenvolvimento deste projeto permitiu aplicar, na prática, os principais conceitos relacionados à construção e execução de aplicações Spring Boot.

Foram implementadas operações CRUD, integração com PostgreSQL, validação de dados, tratamento de erros e testes unitários utilizando JUnit e Mockito.

Também foram realizadas a geração e execução do arquivo JAR fora da IDE, a configuração de perfis Spring, a conteinerização com Docker e o monitoramento da aplicação utilizando Actuator.

Os testes automatizados foram executados com sucesso, sem falhas ou erros. A aplicação também apresentou funcionamento correto tanto em execução local quanto em ambiente Docker.

Conclui-se que os objetivos de desenvolvimento e validação da API foram alcançados, proporcionando experiência prática com ferramentas e conceitos importantes para o desenvolvimento de aplicações Java.

## 13. Referências

- Spring Boot – Documentação oficial: https://docs.spring.io/spring-boot/
- Spring Data JPA – Documentação oficial: https://docs.spring.io/spring-data/jpa/reference/
- JUnit 5 – Documentação oficial: https://junit.org/junit5/docs/current/user-guide/
- Mockito – Documentação oficial: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html
- Docker – Documentação oficial: https://docs.docker.com/
- PostgreSQL – Documentação oficial: https://www.postgresql.org/docs/
- Maven – Documentação oficial: https://maven.apache.org/guides/