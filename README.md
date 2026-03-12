MedStock API

Backend da aplicação MedStock para gerenciamento de estoque médico.

Tecnologias

- Java 17
- Spring Boot 3.4.3
- Maven
- Lombok
- H2 (banco de dados em memória)
- Swagger

Pré-requisitos

- Java 17 ou superior
- Maven 3.8+

Como rodar:

1. Clone o repositório
   git clone https://github.com/seu-usuario/medstock.git
   cd medstock

2. Execute a aplicação
   ./mvnw spring-boot:run

OBS: buildar antes
./mvnw clean install
./mvnw spring-boot:run

A API estará disponível em: http://localhost:8080

Banco de dados H2
O banco é em memória e reinicia a cada execução.

Acesse o console pelo browser:
http://localhost:8080/h2-console

Campo
Valor
JDBC URL
jdbc:h2:mem:testdb
Username
sa
Password
(vazio)

Para acessar ao Swagger:
http://localhost:8080/swagger-ui/index.html

Testes
./mvnw test
