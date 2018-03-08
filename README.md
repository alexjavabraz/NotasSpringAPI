# web
## Application Teste Spring, Spring Security, JPA, Jquery, Bootstrap

### Requirements
* Java 8
* MYSQL 
* Wildfly 10

### Seguem os dados para acesso:

* email: admin@admin.com
* senha: admin@123

### Estão expostos os seguintes serviços: 

* GET "/rest/eth/saldo/{id}" = Consulta do saldo por ID da conta
* POST "/rest/eth/saque" = Cria a transação e faz a contagem das notas
* GET "/rest/listTodasTrancoes" = Consulta das transações efetuadas

Não incluí notas menores de 10, então o saldo abaixo da menor nota (10) ficará travado.

O banco de dados escolhido foi o mysql.

O export do banco está dentro dos resources do projeto, no diretório db_export.

Para compilar e gerar o .war

`$ mvn clean install`

Como já tenho um servidor wildfly, optei por utilizá-lo.

## License
[MIT](https://choosealicense.com/licenses/mit/)
