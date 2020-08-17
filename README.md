<h1>Cadastro de Clientes</h1>

Essa é uma aplicação backend restfull responsável pelo cadastro de clientes de uma empresa, que nos permite buscar, cadastrar, atualizar e excluir um cliente.

<h2>Estrutura</h2>

A aplicação está dividida da seguinte forma.

<strong>Pacote: com.bruna.clientes</strong>

Contém a classe DesafioApplication que iniciará a nossa aplicação. Para inicia-la, clique com o botão direito do mouse, selecione a opção Run As e em seguida, Spring Boot App.

<strong>Pacote: com.bruna.clientes.controller</strong>

Contém a classe ClienteController, onde os endpoints da aplicação foram implementados. Endpoints são URLs onde os serviços podem ser acessados.
Essas URLs devem ser acessadas, temporariamente, pelo Postman, pois a aplicação ainda não foi hospedada.

Segue os endpoints disponíveis:

<strong>GET</strong>: http://localhost:8080/api/clientes

Este link retorna todos os clientes cadastrados.

<strong>GET (por id)</strong>: http://localhost:8080/api/clientes/{id}

Retorna o cliente pelo ID informado no link

<strong>POST</strong>: http://localhost:8080/api/clientes

Permite cadastrar um novo cliente.
Para isso, é necessário acessar o Postman, selecionar a opção Body e deixar as opções raw e JSON selecionadas.
O JSON deve conter os seguintes dados:

{

    "cpf": "11111111111",
    
    "nome": "Nazaré Tedesco",
    
    "email": "nazare@gmail.com"
    
}

 
O CPF acima é apenas um exemplo, pois a aplicação aceita somente CPFs válidos.


<strong>PUT</strong>: http://localhost:8080/api/clientes/{id}

Através deste link é possível alterar informações do registro. É permitido alterar o e-mail e o nome do cliente. Deve-se informar na URL o id do cliente que terá os seus dados alterados.

<strong>DELETE</strong>: http://localhost:8080/api/clientes/{id}

Permite a exclusão de um cliente. Também é necessário informar o id do cliente que será excluído.


<strong>Pacote: com.bruna.clientes.domain</strong>

Este pacote contém a classe Cliente, onde estão registrados os atributos de um cliente. Essa classe possui os seguintes atributos:


private Long id;

private String cpf;

private String nome;

private String email;

<strong>Pacote: com.bruna.clientes.domain.dto</strong>

Neste pacote estão as classes ClienteDTO e ClienteUpdateDTO.
Ambas foram criadas para otimizar o transporte dos dados do cliente e contém as regras de validação, como por exemplo, o preenchimento do CPF do cliente ser obrigatório ao cadastrar um cliente ou não permitir cadastrar um cliente com um e-mail já existente.
As anotações @ClienteInsert e @ClienteUpdate foram criadas manualmente para controlar essas validações estão no pacote br.com.bruna.service.validations


<strong>Pacote: br.com.bruna.clientes.repository</strong>

Contém a interface ClienteRepository que herda de JpaRepository do Spring Data para utilizar métodos já prontos para realizar transações com o banco de dados. Neste caso, foram criados os métodos findByCpf(String cpf) e findByEmail(String email) para auxiliar na criação das validações mencionadas no parágrafo anterior.

<strong>Pacote: com.bruna.clientes.service</strong>

A classe ClienteService contém as operações que aplicação executará. As operações disponíveis são: Buscar um cliente pelo ID, buscar todos os clientes, cadastrar um cliente, atualizar informações de um cliente e excluir um cliente. Essas operações são chamadas pelos endpoints criados e mapeados na classe ClienteController.

<strong>Pacote: com.bruna.clientes.service.exceptions</strong>

Em algumas situações, a aplicação pode ter comportamentos inesperados, por exemplo, quando tentamos excluir ou buscar um objeto inexistente. As exceções nos ajudam a detectar e tratar possíveis erros. Neste momento, temos a classe ObjectNotFoundException que detecta e notifica o usuário que o objeto informado não existe.

<strong>Pacote: com.bruna.clientes.controller.exceptions</strong>

Este pacote contém as classes FieldMessage, ResourceExceptionHandler, StandardError e ValidationError, que são responsáveis por deixar o retorno das exceções mais legíveis, retornando data e hora, path, mensagem de erro e o status de resposta específico(400, 404, etc).

<strong>Pacote: com.bruna.clientes.service.validations</strong>

Aqui contém as classes ClienteInsert, ClienteInsertValidator, ClienteUpdate e ClienteUpdateValidator. 
As classes ClienteInsert e ClienteInsertValidator foram utilizadas para criar a anotação customizada ClienteInsert. Esta anotação evita que um cliente seja cadastrado com CPF e email já existentes.
E as classes ClienteUpdate ClienteUpdateValidator foram utilizadas para criar a anotação customizada ClienteUpdate. Com esta anotação, conseguimos atualizar um cliente alterando nome e email, somente nome ou somente o e-mail. Porém, não é permitido alterar o e-mail de um cliente informando o e-mail de outro cliente que já está cadastrado.


<h2>Base de dados</h2>

O banco de dados utilizado é o H2, que funciona em memória.
Para acessá-lo, é necessário subir a aplicação pelo Spring Boot e no browser digite http://localhost:8080/h2-console.
E na tela de login, informe as credenciais abaixo e clique no botão <strong>Connect</strong>.

<strong>Username</strong>: sa

<strong>Password</strong>: 

As configurações do banco de dados estão no arquivo <strong>application.properties</strong> em src/main/resources.


<h2>Especificações</h2>

<strong>IDE</strong>: Spring Tools Suit 4.3.0

<strong>Linguagem</strong>: Java 11

<strong>Framework</strong>: Spring Boot 2.3.3

<strong>Ferramenta de testes para requisições</strong>: Postman

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
