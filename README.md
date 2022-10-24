<h1>Ferramentas Usadas</h1>
<ul>
  <li>Java + Spring</li>
  <li>JWT</li>
  <li>Lombok</li>
  <li>Swagger</li>
  <li>Banco H2</li>
</ul>

<h1>Pré-requisitos:</h1>
<ul>
<li>Java</li>
</ul>

<h2>Como Configurar o Projeto?</h2>
<p>A nossa API está pré-configurada rodando em perfil de teste, na porta <code>8081</code>, e caso queira mudar
a porta (é opcional) basta acessar o arquivo application-test.properties e alterar o valor 
na propriedade <code>server.port=</code></p>

<h2>Como Acessar a documentação?</h2>
<p>Simples, basta executar o projeto em alguma IDE, de preferência no IntelliJ, e digitar
 no seu navegador <code>http://localhost:8081/swagger-ui.html</code>. Em seguida, após abrir 
a documentação, vá em <mark>user-controller</mark> na parte de login e informe as credenciais
<code>{"username": "Matheus", "password": "matheus123"}</code> para gerar o Bearer Token. </p>

<h2>Como Rodar o Projeto?</h2>
<ul>
 <li>Baixe ou clone o projeto</li>
 <li>Abra sua IDE</li>
 <li>Importe o nosso projeto (Maven)</li>
 <li>Execute a aplicação springboot</li>
</ul>