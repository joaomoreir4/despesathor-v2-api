# Despesathor v2 - API RESTful (Backend)

[](https://www.oracle.com/java/technologies/downloads/)
[](https://spring.io/projects/spring-boot)
[](https://spring.io/projects/spring-data-jpa)
[](https://www.google.com/search?q=https://github.com/joaomoreir4/despesathor-v2-api)

Backend (API RESTful) para a aplicação de gestão de despesas pessoais Despesathor v2.

## 1\. Sobre o Projeto

O objetivo principal deste projeto foi aprender e aplicar os fundamentos do desenvolvimento de backend profissional. O projeto original (um protótipo que usava `localStorage` do navegador) foi completamente redesenhado.

Esta nova versão migra toda a lógica de dados para uma API RESTful robusta, com um banco de dados SQL, tornando a aplicação escalável e persistente.

**Esta API servirá como o backend para o novo frontend da aplicação (atualmente em desenvolvimento), que substituirá a antiga lógica de `localStorage` por chamadas HTTP a estes endpoints.**

## 2\. Arquitetura e Decisões de Design

O projeto segue uma arquitetura de serviços desacoplada:

  * **Controller Layer (`/controller`):** Expõe os endpoints RESTful (`@RestController`). Responsável exclusivamente pela gestão do tráfego HTTP, validação de entrada (ex: `@PathVariable`, `@RequestBody`) e delegação para a camada de serviço.
  * **Service Layer (`/business`):** Contém toda a lógica de negócio (ex: validação de regras de atualização, filtragem de dados). Orquestra as operações, mas não interage diretamente com o banco de dados.
  * **Repository Layer (`/infrastructure/repository`):** Camada de persistência de dados. Utiliza Spring Data JPA (`JpaRepository`) para abstrair operações CRUD e consultas derivadas (ex: `findByCategoria`).
  * **Entity Layer (`/infrastructure/entitys`):** Define o mapeamento Objeto-Relacional (ORM) através de anotações JPA (`@Entity`).

### Decisões Técnicas Chave

  * **Tipos de Dados Financeiros:** `java.math.BigDecimal` é utilizado para todos os campos monetários (`valor`), garantindo precisão decimal exata e evitando os erros de arredondamento de tipos `float` ou `double`.
  * **Tipos de Dados de Data:** `java.time.LocalDate` é utilizado para datas, fornecendo um tipo de dado moderno e sem fuso horário.
  * **Segurança de Tipo de Categoria:** Um `Enum` Java (`Categoria`) é usado para o campo `categoria`, garantindo integridade dos dados ao restringir as entradas a um conjunto predefinido (ex: `LAZER`, `SAUDE`).
  * **Padrão Builder:** O Lombok (`@Builder`) é usado para a criação de objetos, permitindo uma implementação limpa de lógica de atualização parcial (PATCH) na camada de serviço.

## 3\. Tecnologias Utilizadas

  * **Java 24**
  * **Spring Boot 3**
  * **Spring Data JPA:** Persistência de dados e ORM.
  * **Spring Web:** Criação de endpoints RESTful.
  * **Lombok:** Redução de código boilerplate.
  * **H2 Database:** Banco de dados em memória para ambiente de desenvolvimento.
  * **Maven:** Gestão de dependências.

## 4\. Documentação dos Endpoints da API

A URL base para todos os endpoints é **`/despesas`**.

| Método | URL | Descrição | Exemplo de Body (JSON) |
| :--- | :--- | :--- | :--- |
| **`GET`** | `/` | Lista todas as despesas. | N/A |
| **`GET`** | `?categoria={nome}` | Filtra despesas por categoria (`Enum`). | N/A |
| **`GET`** | `?data={data}` | Filtra despesas por data (formato `AAAA-MM-DD`). | N/A |
| **`GET`** | `/{id}` | Busca uma única despesa pelo seu ID. | N/A |
| **`POST`** | `/` | Cria uma nova despesa. | `{ "descricao": "Jantar", "valor": 80.50, "categoria": "ALIMENTACAO", "data": "2025-10-31" }` |
| **`PUT`** | `/{id}` | Atualiza uma despesa (parcial ou total). | `{ "valor": 120.00 }` (Campos são opcionais) |
| **`DELETE`** | `/{id}` | Apaga uma despesa pelo seu ID. | N/A |

## 5\. Configuração e Execução Local

### Pré-requisitos

  * [JDK 24 (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/)
  * [Apache Maven](https://maven.apache.org/download.cgi) (opcional, se não usar o wrapper)

### Passos para Execução

1.  **Clonar o repositório:**

    ```bash
    git clone https://github.com/joaomoreir4/despesathor-2.0-crud-api.git
    cd despesathor-2.0-crud-api
    ```

2.  **Executar a aplicação:**
    O projeto utiliza o Maven Wrapper, que instala o Maven automaticamente.

      * No Windows (PowerShell ou CMD):
        ```bash
        ./mvnw.cmd spring-boot:run
        ```
      * No Linux/Mac:
        ```bash
        ./mvnw spring-boot:run
        ```

3.  **Acesso aos Serviços:**

      * **API:** A aplicação estará disponível em `http://localhost:8080/despesas`.
      * **H2 Database Console:** O console do banco em memória estará disponível em `http://localhost:8080/h2-console`.
          * **JDBC URL:** `jdbc:h2:mem:testdb` (Verifique o `application.properties` se este valor for diferente)
          * **Username:** `sa`
          * **Password:** (deixe em branco)

## 6\. Objetivos Futuros (Roadmap)

  * [ ] Efetuar o deploy da API no Google Cloud Run.
  * [ ] Migrar a configuração de persistência de H2 para Cloud SQL (PostgreSQL).
  * [ ] **Desenvolver o novo frontend:** A próxima fase é refatorar o frontend original (disponível em `https://github.com/joaomoreir4/despesathor-app-orcamento-pessoal`) para consumir esta API, completando a migração para uma arquitetura full-stack.
  * [ ] Fazer o deploy do frontend no Firebase Hosting.
