# Teste de Backend Klab

Este projeto consiste na criação de uma REST API em conjunto com a API deckofcardsapi.

## Documentação da API

#### Retorna o vencedor do jogo com todos os dados já salvos no banco
```http
  Post http://localhost:8080/decks
```
## Ferramentas Utilizadas

- Java 17
- Spring 3.0.5
- IntelliJ IDEA
- PostgreSQL
- Maven
- Git
- Insomnia
- JUnit
- JPA
- Lombok

## Configuração do Ambiente

1. Certifique-se de ter o Java 17 instalado.
2. Instale o IntelliJ IDEA (ou outra IDE de sua preferência) e configure o projeto.
3. Instale o PostgreSQL e crie um banco de dados para a aplicação.
4. Importe o projeto para o IntelliJ IDEA.
5. Execute as migrações do banco de dados. (Script esta dentro do projeto)
6. Execute a aplicação.

## Como Utilizar

1. Faça uma requisição POST para o endpoint `http://localhost:8080/decks`.
2. Aguarde a resposta da API, que retornará o vencedor do jogo com todos os dados já salvos no banco.
