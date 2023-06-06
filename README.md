# bibliotecaNeo4j
### Aplicação de banco não relacional em um software de biblioteca

## Dependências

- Docker
- Java 17

## Subindo o banco de dados

- Realizar o build da imagem do banco de dados no root do projeto com os dados base do projeto na primeira vez de execução
```
docker-compose up --build
```

- Subidas subsequentes devem ser utilizando o comando a seguir na root do projeto
```
docker-compose up -d
```

## Subindo o projeto

Utilizando o IntelliJ, executar a classe BibliotecaDbApplication apertando o botão de debug ou de run.


![Imagem](https://github.com/Fogolar1/bibliotecaNeo4j/assets/70671981/e65e637b-b380-4cc1-9f61-02182ea9e201)
