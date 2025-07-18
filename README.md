\# 🛒 E-commerce API



API RESTful para gerenciamento de um e-commerce, desenvolvida em Java com Spring Boot, utilizando MySQL como banco de dados e autenticação JWT.



---



\## 📦 Tecnologias



\- Java 17+

\- Spring Boot

\- Spring Data JPA

\- MySQL

\- JWT (Autenticação)

\- Maven



---



\## 🚀 Como executar o projeto



\### ✅ Pré-requisitos



\- Java 17 ou superior

\- Maven 3.8+

\- MySQL instalado e em execução localmente



---



\### ⚙️ Configuração do ambiente



1\. Edite o arquivo `application.properties` na pasta `src/main/resources/` com suas credenciais de banco de dados:



```properties

spring.application.name=ecommerce-api



jwt.secret=uma-chave-muito-secreta-e-com-mais-de-32-caracteres-aleatoria

jwt.expirationMs=86400000



\# Configuração do banco de dados MySQL

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce\_db?useSSL=false\&serverTimezone=UTC

spring.datasource.username=root # seu usuário do banco

spring.datasource.password=root # sua senha do banco

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver



\# Configuração do JPA

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format\_sql=true





---





\### 🗃️ Configuração do banco de dados

Crie o banco de dados:



bash

mysql -u root -p -e "CREATE DATABASE ecommerce\_db"

Importe o dump SQL:



bash

mysql -u root -p ecommerce\_db < ecommerce\_db\_dump.sql

▶️ Executando a aplicação

Na raiz do projeto, execute:



bash

./mvnw spring-boot:run

A API estará disponível em: http://localhost:8080



---



\###🛠️ Testando a API



Importe a collection do Postman (Spring Boot E-Commerce - Produtos.postman\_collection.json) para testar os endpoints.



Observação: Atualize os tokens no cabeçalho Authorization ao testar endpoints protegidos.

