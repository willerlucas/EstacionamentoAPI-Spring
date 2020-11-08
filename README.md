# Instruções de uso

## Primeiros passos

Assim que o projeto for importado para a IDE, alterar o arquivo [application.properties](https://github.com/willerlucas/spring-boot-estacionamento/blob/main/estacionamento/src/main/resources/application.properties) nas linhas 6 e 7 para confingurar seus dados de acesso do MySQL.

    spring.datasource.username = seuUser
    spring.datasource.password = suaSenha

No MySQL, importar e executar todas as querys do arquivo [Script SQL](https://github.com/willerlucas/spring-boot-estacionamento/blob/main/instru%C3%A7%C3%B5es/Script%20SQL.sql)

No Insomnia, importar o arquivo [Insomnia_2020-11-08.json](https://github.com/willerlucas/spring-boot-estacionamento/blob/main/instru%C3%A7%C3%B5es/Insomnia_2020-11-08.json) contendo as instruções da API. Para conseguir o token de acesso, basta executar a instrução `'Pegando token'` e copiar o `access_token` que deve ser inserido na autenticação das demais instruções como `Bearer Token`

    {
      "access_token": "s+uSYYpFo+ormhMhpMBhPf9loxg=",
      "token_type": "bearer",
      "refresh_token": "UPYT4uVIPjkGS15wutVEetAaHU4=",
      "expires_in": 43199,
      "scope": "password"
    }

Esse sistema foi desenvolvido pensando nas vagas que existem no banco de dados, ou seja, nao existem métodos que criem novas vagas, apenas métodos que alteram seus status. 

Após os passos citados, API está pronta para uso.

## As entidades

![](https://raw.githubusercontent.com/willerlucas/EstacionamentoAPI-Spring/main/instru%C3%A7%C3%B5es/banco%20de%20dados.png)

## EndPoints - Principais rotas
### Gerenciamento de Tickets

 

1- Lista todos os tickets

     [GET] /ticket


 2- Listar tickets em aberto
 

     [GET] /ticket/aberto

 3- Listar todos os tickets já finalizados

    [GET] /ticket/finalizado

4-  criar um novo ticket

    [POST]  /ticket/adicionar

com o body em formato JSON:

      {
    		"veiculo":{
    			"id": 1
    		},
    		"vaga":{
    			"id": 1
    		}
      }

5- Finalizar o ticket

    [PUT] /ticket/finalizar/{id}

As demais rotas podem ser conferidas nos arquivos encontrados no pacote [Controller](https://github.com/willerlucas/EstacionamentoAPI-Spring/tree/main/estacionamento/src/main/java/io/github/willerlucas/estacionamento/controller)

#### Validações
O sistema faz validações para que algumas regras sejam cumpridas:

 - Um veículo só pode ocupar uma vaga
 - Uma vaga ocupada não pode ser ocupada por outro veículo
 - Não é possível finalizar o mesmo ticket duas vezes
 

#### Futuras melhorias
- Ainda faltam serem implementadas algumas validações tais como aviso de estacionamento cheio
- Os tickets são gerados adicionando os Id's dos veículos e das vagas, quando em uma situação real seria mais convenientes serem manipulados por placa.
- Retornar o objeto para o usuário no body da requisição. 
