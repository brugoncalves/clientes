<h1>Cadastro de Clientes</h1>

Essa é uma aplicação backend restfull responsável pelo cadastro de clientes de uma empresa, que nos permite buscar, cadastrar, atualizar e excluir um cliente.

Segue o padrão de requisições implementado

<strong>GET</strong>: http://localhost:8080/api/clientes

<strong>GET (por id)</strong>: http://localhost:8080/api/clientes/{id}

<strong>POST</strong>: http://localhost:8080/api/clientes

<strong>PUT</strong>: http://localhost:8080/api/clientes/{id}

<strong>DELETE</strong>: http://localhost:8080/api/clientes/{id}


<h2>Base de dados</h2>

O banco de dados utilizado é o H2, que funciona em memória.
Para acessá-lo, é necessário subir a aplicação pelo Spring Boot e no browser digite http://localhost:8080/h2-console.
E na tela de login, informe as credenciais abaixo e clique no botão <strong>Connect</strong>.

<strong>Username</strong>: sa

<strong>Password</strong>: 


<h2>Especificações</h2>

<strong>IDE</strong>: Spring Tools Suit 4.3.0

<strong>Linguagem</strong>: Java 11

<strong>Framework</strong>: Spring Boot 2.3.3

<h2>Dependências</h2>
<ul>
  <li>Spring Boot Starter Web</li>
  <li>Spring Boot Devtools</li>
  <li>Spring Boot Starter Test</li>
  <li>Spring Boot Starter Data Jpa</li>
  <li>Validation Api</li>
  <li>Hibernate Validator</li>
  <li>H2</li>
</ul>
