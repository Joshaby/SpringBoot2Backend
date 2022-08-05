SpringBoot2Backend
=====

Repositório do backend do curso [Spring Boot, Hibernate, REST, Ionic, JWT, S3, MySQL, MongoDB](https://www.udemy.com/course/spring-boot-ionic/) na Udemy por Nélio Alves.

Backend feito em Java para promover implementação de um sistema de compra de produtos.

Atualização do projeto https://github.com/Joshaby/SpringBoot2-Ionic-Backend

## Estruturação do projeto

### Estrutura das pastas com classes e interfaces

- Config: Classes de configuração do projeto, como persistência e segurança
- Entities: Classes de domínio que representam objetos do bando de dados
- Dto: Classes que representam JSON das requisições
- Repositories: Interfaces que realizaram consultas no banco de dados
- Controllers: API Rest do projeto junto com classes úteis e tratamento de execeção
- Security: Classes, classes úteis para prover segurança
- Services: Classes com regras de negócio junto com classes úteis e que acessam os repositories

### Pasta resource

#### Arquivos properties

- application.properties: Arquivo base de proprieadades do projeto! Usado para setar o ambiente de execução, definir senha de acesso so SMTP da Google e a secret key de geração de token JWT
- application-dev.properties: Arquivo com propriedades do ambiente dev, que roda localmente e usa MySQL
- application-prod.properties: Arquivo com propriedades do ambiente prod que roda no Heroku
- application-test.properties: Arquivo com propriedades do ambiente test, que roda localmente e usa hH2

#### Templates

- email: Contém um arquivo html populado com Thymeleaf usado para envio de pedidos por email

## Endpoints disponíveis

### H2
- `/h2-console` - GET: Acesso ao bando de dados H2

### Clientes

- `/clientes` - GET: Obtém todos os clientes
- `/clientes` - POST: Cadastro de cliente. Exemplo:

    ```
    {
        "nome": "Donnie Darko",
        "email": "donniedarko@gmail.com",
        "cpfOuCnpj": "294.563.110-58",
        "tipoCliente": 1,
        "senha": "donnie123",
        "logradouro": "Rua",
        "numero": "9696",
        "complemento": "Ao lado da padaria Pão Gostoso",
        "bairro": "Barro duro",
        "cep": "23213-343",
        "telefone1": "5583908490983",
        "telefone2": "5583918798912",
        "cidadeId": 1     
    }
    ``` 

- `/clientes/{id}` - GET: Obtém um cliente por id
- `/clientes/{id}` - PUT: Atualização de cliente por id. Exemplo:

    ```
    {
        "nome": "Donnie Darko",
        "email": "donniedarko@gmail.com"
    }
    ``` 

- `/clientes/{id}` - DELETE: Deleção de cliente por id
- `/clientes/pages?page=0&linesPerPage=24&direction=ASC&orderBy=id` - GET: Obtém clientes em uma página

### Categorias

- `/categorias` - GET: Obtém todos as categorias
- `/categorias` - POST: Cadastro de categoria. Exemplo:

    ```
    {
        "nome": "Informática"
    }
    ```

- `/categorias/{id}` - GET: Obtém uma categoria por id
- `/categorias/{id}` - PUT: Atualização de categoria por id. Exemplo:

    ```
    {
        "nome": "Informática"  
    }
    ```

- `/categorias/{id}` - DELETE: Deleção de categoria por id. Exemplo:
- `/categorias/pages?page=0&linesPerPage=24&direction=ASC&orderBy=id` - GET: Obtém categorias em uma página

### Pedidos

- `/pedidos/{id}` - GET: Obtém todos um pedido por id
- `/pedidos/{id}` - POST: Cadastra um pedido por id. Exemplo:

    ```
    {
        "cliente": {
            "id": 1
        },
        "endereco": {
            "id": 1
        },
        "pagamento": {
            "numeroDeParcelas": 10,
            "@type": "pagamentoComCartao"
        },
        "itens": [
            {
                "quantidade": 2,
                "produto": {
                    "id": 3
                }
            },
            {
                "quantidade": 4,
                "produto": {
                    "id": 2
                }
            }
        ]
    }
    ```

### Produtos

- `/produtos/{id}` - GET: Obtém um pedido
- `/produtos?nome=""&categorias=""&page=0&linesPerPage=24&direction=ASC&orderBy=nome` - GET: Obtém produtos em uma página

### Login
- `/login` - POST: Faz login de um usuário com email e senha. Exemplo:

    ```
    {
        "email": "josehenriquebrito55@gmail.com",
        "senha": "12345"
    }
    ```
  
    O token JWT será retornado no campo Authorization no cabeçalho da resposta da requisição. Exemplo:

    ```
    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb3NlaGVucmlxdWVicml0bzU1QGdtYWlsLmNvbSIsImV4cCI6MTY0NjYxMTYyMX0.d_d0fm5DkHSjdjE8vw6-349pAT8fJTt97Iu7OiMBq_hdpFkzNMmhP5Im6PvRxPhKm9rBynXXvziZeqkKiBpAUg
    ```

### Auth

- `/auth/refresh_token` - POST: Gera um novo token pra um usuário logado com token prestes a expirar(necessita do antigo token no cabeçalho da requisição)

- `/auth/forgot` - POST: Gera um nova senha e a envia por email

  ```
  {
    "email": "donniedarko@gmail.com"
  }
  ```

## Notas

Endpoints `/produtos/**`, `/categorias/**`, `/clientes/**` e `/auth/forgot/**` não necessitam de autenticação. O endpoint `/h2-console` só funciona no ambiente de test.

### Como acessar endpoints autenticados?

Para acessar um endpoint autenticado, sua requisição precisar ter o campo Authorization com o token no cabeçalho. 
O valor do campo deve começar com a palavra Bearer seguido do token.

### Como usar o serviço de envio de email?

Para usar o serviço de envio de email, você precisa liberar o uso de email em aplicativos que não sejam Google(use os links: https://www.google.com/settings/security/lesssecureapps e https://accounts.google.com/b/0/DisplayUnlockCaptcha).
Após isso você precisa definir seu email e senha no arquivo application.properties.

## Tecnologias

- Java 17

- Spring Boot 2.7.2

- Spring Framework 5.13.22

- Spring Data 2.7.2

- Spring Security 5.7.2

- Thymeleaf 3.0.15

- Lombok 1.18.22

- H2 2.1.210

- MySQL Connector 8.0.28

- Java JWT: JSON Web Token for Java and Android 0.11.5

- Apache Commons Lang3 3.12.0

- Swagger com SpringDoc Open API v3 1.6.9