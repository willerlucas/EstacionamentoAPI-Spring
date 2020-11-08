# Instruções de uso

Instruções de uso:
Assim que o projeto for importado para a IDE, alterar o arquivo [application.properties](https://github.com/willerlucas/spring-boot-estacionamento/blob/main/estacionamento/src/main/resources/application.properties) nas linhas 6 e 7 para confingurar seus dados de acesso do MySQL.

spring.datasource.username = seuUser
  
spring.datasource.password = suaSenha




No MySQL, importar e executar todas as querys do arquivo [Script SQL](https://github.com/willerlucas/spring-boot-estacionamento/blob/main/instru%C3%A7%C3%B5es/Script%20SQL.sql)

No Insomnia, importar o arquivo [Insomnia_2020-11-08.json](https://github.com/willerlucas/spring-boot-estacionamento/blob/main/instru%C3%A7%C3%B5es/Insomnia_2020-11-08.json) contendo as instruções da API. Para conseguir o token de acesso, basta executar a instrução 'Pegando token' e copiar o access_token que deve ser inserido na autenticação das demais instruções como "Bearer Token"

Esse sistema foi desenvolvido pensando nas vagas que existem no banco de dados, ou seja, nao existem métodos que criem novas vagas, apenas métodos que alteram seus status. 

Após os passos citados, API está pronta para uso.
