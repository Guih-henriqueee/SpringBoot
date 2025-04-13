# Spring Boot Application - User Management

Este projeto é uma aplicação Spring Boot que oferece funcionalidades para gerenciamento de usuários. A aplicação permite a inserção e recuperação de dados de usuários em um banco de dados, além de possuir um sistema flexível de configuração para se conectar ao banco, carregando as propriedades de conexão a partir de um arquivo de configuração. 

### Principais Funcionalidades:

- **Inserção de Usuários**: A aplicação pode adicionar novos usuários no banco de dados com dados como nome, função, idade e CPF.
- **Recuperação de Usuários**: Permite buscar usuários já cadastrados no banco de dados através de seu ID.
- **Configuração Flexível de Banco de Dados**: A conexão com o banco de dados pode ser configurada de maneira dinâmica, permitindo alterar facilmente as credenciais e a URL de conexão.
- **Criação e Validação de Tabela e Colunas**: A aplicação valida se a tabela de usuários existe no banco e, caso não exista, cria a tabela automaticamente. Além disso, verifica se todas as colunas necessárias estão presentes, criando novas colunas se necessário.

## Arquitetura da Aplicação

A arquitetura da aplicação segue a estrutura clássica do Spring Boot e é organizada em pacotes para uma melhor modularização. A seguir estão as principais classes e suas responsabilidades:

### 1. **Configuração de Banco de Dados** (`DatabaseProperties.java`)

A classe `DatabaseProperties` é responsável por carregar as propriedades de conexão com o banco de dados a partir do arquivo `application.properties`. Ela permite definir as credenciais de acesso (URL, nome de usuário e senha) de forma centralizada e fácil de configurar.

Exemplo de configuração no arquivo `application.properties`:

```properties
# Configurações do banco de dados
database.url=jdbc:postgresql://localhost:5432/seu_banco
database.username=seu_usuario
database.password=sua_senha
```

### 2. **Inicialização da Aplicação** (`Application.java`)

A classe `Application.java` é o ponto de entrada da aplicação Spring Boot. Ela inicializa a aplicação e carrega as propriedades de banco de dados através da anotação `@EnableConfigurationProperties(DatabaseProperties.class)`.

A anotação `@ComponentScan` é usada para garantir que o Spring encontre e registre os componentes necessários, como controllers e serviços.

### 3. **Anotação Customizada** (`EnableConfigurationProperties.java`)

Essa anotação customizada é usada para tornar a classe `DatabaseProperties` gerenciável pelo Spring, permitindo que as propriedades do banco de dados sejam carregadas automaticamente e injetadas nas classes necessárias.

### 4. **Gerenciamento de Usuários** (`Controller.java`, `InsertUser.java`, `GetUser.java`)

- **`Controller.java`**: O controlador principal da aplicação, com endpoints para inserir e recuperar usuários. 
  - **GET** `/SpringApi/Users?UserId={id}`: Recupera os dados de um usuário a partir do seu ID.
  - **POST** `/SpringApi/Users`: Adiciona um novo usuário no banco de dados com as informações fornecidas no corpo da requisição.

- **`InsertUser.java`**: Responsável pela lógica de inserção de um novo usuário no banco de dados. Ela usa o `JdbcTemplate` para executar a query SQL de inserção e retorna o ID gerado para o usuário.

- **`GetUser.java`**: Lida com a consulta ao banco de dados para recuperar os dados de um usuário com base no ID fornecido.

### 5. **Gerenciamento Dinâmico da Tabela de Usuários** (`QueryController.java`)

A classe `QueryController.java` é responsável por verificar se a tabela de usuários existe no banco de dados e, caso não exista, cria a tabela com as colunas necessárias. Ela também valida se todas as colunas essenciais estão presentes e, se necessário, adiciona novas colunas.

### Fluxo da Aplicação

1. **Inicialização da aplicação**: Quando a aplicação é iniciada, o Spring Boot carrega as configurações do banco de dados e verifica a existência da tabela de usuários.
   
2. **Inserção de usuário**:
   - O endpoint `POST /SpringApi/Users` recebe os dados do usuário no corpo da requisição.
   - O `InsertUser` executa a inserção no banco de dados e retorna o ID gerado para o novo usuário.

3. **Recuperação de usuário**:
   - O endpoint `GET /SpringApi/Users?UserId={id}` consulta o banco de dados e retorna os dados do usuário correspondente ao ID fornecido.

## Requisitos

- **Java 17 ou superior**
- **Spring Boot 2.x**
- **Banco de Dados PostgreSQL** (ou outro banco compatível com JDBC)
- **Maven ou Gradle** para gerenciamento de dependências

## Instalação

### Passo 1: Clonar o repositório

Clone o repositório em sua máquina local:

```bash
git clone https://github.com/seu-usuario/spring-boot-application.git
```

### Passo 2: Configuração do Banco de Dados

Crie um banco de dados PostgreSQL (ou use um banco de dados compatível) e configure as propriedades de acesso no arquivo `src/main/resources/application.properties`:

```properties
# Configurações do banco de dados
database.url=jdbc:postgresql://localhost:5432/seu_banco
database.username=seu_usuario
database.password=sua_senha
```

### Passo 3: Build e Execução

Com o Maven, execute o seguinte comando para compilar e rodar a aplicação:

```bash
mvn clean install
mvn spring-boot:run
```

Se estiver usando o Gradle, execute:

```bash
gradle build
gradle bootRun
```

## Endpoints

A aplicação expõe os seguintes endpoints:

### 1. **POST /SpringApi/Users**

Insere um novo usuário no banco de dados. O corpo da requisição deve conter os seguintes dados no formato JSON:

```json
{
  "userName": "John Doe",
  "userFunction": "Developer",
  "userAge": 30,
  "userCpf": "12345678900"
}
```

**Resposta**:

- **201 Created**: Caso o usuário seja criado com sucesso.
- **500 Internal Server Error**: Caso ocorra um erro durante a inserção.

### 2. **GET /SpringApi/Users?UserId={id}**

Recupera os dados de um usuário a partir do seu ID.

**Resposta**:

- **200 OK**: Caso o usuário seja encontrado.
- **404 Not Found**: Caso o usuário não seja encontrado.

## Contribuições

Se você deseja contribuir para o projeto, siga os passos abaixo:

1. Fork o repositório.
2. Crie uma branch para a sua funcionalidade (`git checkout -b feature/nova-funcionalidade`).
3. Realize o commit das suas alterações (`git commit -am 'Adiciona nova funcionalidade'`).
4. Faça push para a branch (`git push origin feature/nova-funcionalidade`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

