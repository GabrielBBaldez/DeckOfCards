# Teste de Backend Klab

Este projeto consiste na criação de uma REST API em conjunto com a API deckofcardsapi.

## Documentação da API

#### Retorna o vencedor do jogo com todos os dados já salvos no banco, caso tenha empate é mostrado os jogadores empatados
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
5. Criar o banco de dados no Postgre, CREATE DATABASE "Klab"
6. As tabelas são criadas automaticamente após o primeiro start na aplicação.
7. Execute a aplicação.
8. Script do banco de dados no projeto.

## Como Utilizar

1. Faça uma requisição POST para o endpoint `http://localhost:8080/decks` usando Insomnia ou Postman
2. Aguarde a resposta da API, que retornará o vencedor do jogo com todos os dados já salvos no banco.
