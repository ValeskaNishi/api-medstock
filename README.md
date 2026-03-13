**MedStock API**

Backend da aplicação MedStock, sistema de controle de insumos médicos desenvolvido para gerenciar produtos como medicamentos, EPIs e Materiais cirúrgicos. 

Tecnologias

- Java 17
- Spring Boot 3.4.3
- Maven
- Lombok
- H2 (banco de dados em memória)
- Swagger

**Pré-requisitos**

- Java 17
- Maven 3.8+
  

Como rodar:

*1. Clone o repositório:*

   git clone https://github.com/ValeskaNishi/api-medstock.git

*2. Entre na pasta do projeto e execute o comando de build/ instalação:*
   
   ./mvnw clean install

*3. Rode o comando para inicializar o projeto:*

   ./mvnw spring-boot:run


*A API estará disponível em: http://localhost:8080

*Para acessar ao Swagger:
http://localhost:8080/swagger-ui/index.html

*Banco de dados H2
O banco é em memória e reinicia a cada execução!

*Acesse o console pelo browser:
http://localhost:8080/h2-console

**Após subir a api, clone e configure o Frontend**: https://github.com/ValeskaNishi/app-medstock.git

Comando para teste do projeto:

./mvnw test
