\# 🛒 ecommerce-api



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



\## 🚀 Como rodar o projeto



\### ✅ Pré-requisitos



\- Java 17 ou superior

\- Maven 3.8+

\- MySQL rodando localmente



---



\### 📁 Configuração do ambiente



Altere o  arquivo `application.properties` na pasta `src/main/resources/` com suas credenciais de banco, para facilitar os teste, não foi criado um .env com as informações mais sensíveis.



```properties

spring.application.name=ecommerce-api



jwt.secret=uma-chave-muito-secreta-e-com-mais-de-32-caracteres-aleatoria

jwt.expirationMs=86400000



\# Config banco de dados MySQL

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce\_db?useSSL=false\&serverTimezone=UTC

spring.datasource.username=root <--seu user do banco

spring.datasource.password=root <--seu password do banco

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver



\# Config do JPA

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format\_sql=true





\## 🗃️ Banco de dados



O projeto inclui um dump SQL (`ecommerce\\\_db\\\_dump.sql`) que pode ser importado para facilitar a criação da base de dados.



✅ Pré-requisitos

MySQL deve estar instalado e acessível via terminal (comando mysql)



O usuário (root) deve ter permissões para criar bancos e importar arquivos



Antes de importar o arquivo `.sql`, crie o banco `ecommerce\_db` executando o seguinte comando no terminal (cmd, PowerShell, Git Bash, ou terminal do VS Code):



```bash

mysql -u root -p -e "CREATE DATABASE ecommerce\_db"





Para importar:



```bash

mysql -u root -p ecommerce\_db < ecommerce\_db\_dump.sql



▶️ Executando

Com o terminal na raiz do projeto, e como o JAVA 17 configurado corretamente, execute:



```bash

./mvnw spring-boot:run

A API estará disponível em: http://localhost:8080

Na raíz do projeto existe uma Collection (Spring Boot E-Commerce - Produtos.postman\_collection.json), você pode fazer a importação no POSTMAN para otimizar o teste dos end-points

obs: Trocar os tokens no Authorization









